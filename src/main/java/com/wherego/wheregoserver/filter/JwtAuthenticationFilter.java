package com.wherego.wheregoserver.filter;

import com.wherego.wheregoserver.constant.UserRole;
import com.wherego.wheregoserver.exception.UserNotFoundException;
import com.wherego.wheregoserver.service.JwtService;
import com.wherego.wheregoserver.service.TravelerService;
import com.wherego.wheregoserver.service.WriterService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;

//@Component
@RequiredArgsConstructor //Create constructor which parameters are private final args
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    //Auto-inject attributes which will be created on the constructor
    @Autowired
    private JwtService jwtService;
    @Autowired
    private  WriterService writerService;
    @Autowired
    private TravelerService travelerService;
    private HandlerExceptionResolver exceptionResolver;

    @Autowired
    public JwtAuthenticationFilter(HandlerExceptionResolver exceptionResolver) {
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            final String authHeader = request.getHeader("Authorization");
            final String TOKEN_PREFIX = "Bearer ";
            final String jwtToken;
            final String username;
            final List<GrantedAuthority> authorities;
            System.out.println("JwtAuthenticationFilter executing");
            if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)){
                filterChain.doFilter(request, response);
                return;
            }
            jwtToken = authHeader.substring(TOKEN_PREFIX.length());
            authorities = jwtService.extractAuthorities(jwtToken);
            username = jwtService.extractUsername(jwtToken);
            System.out.println("Jwt Token: " + jwtToken);
            System.out.println("Username: " + username);
            System.out.println("Authorities: " + authorities);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = loadUserDetailsByUsernameAndAuthorities(username,authorities);
                System.out.println("UserDetails: " + userDetails);
                if (jwtService.isTokenValid(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    System.out.println("JwtAuthenticationFilter executing, "+ authToken);
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request,response);
        } catch (Exception e) {
            if (e instanceof UserNotFoundException)
                 exceptionResolver.resolveException(request,response,null,
                        new BadCredentialsException("Invalid credentials"));
            else
                exceptionResolver.resolveException(request,response,null,e);
        }
    }

    private UserDetails loadUserDetailsByUsernameAndAuthorities(String username,List<GrantedAuthority> authorities) {
        final boolean isWriter =
                authorities.contains(UserRole.ROLE_WRITER);
        final boolean isTraveler =
                authorities.contains(UserRole.ROLE_TRAVELER);
        if (isWriter) {
           return writerService.loadByUserEmail(username);
        }else if(isTraveler)
            return travelerService.loadByUserEmail(username);
        else
            throw new BadCredentialsException("Invalid user");
    }
}
