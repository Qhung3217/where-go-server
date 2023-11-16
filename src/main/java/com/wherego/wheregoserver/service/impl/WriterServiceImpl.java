package com.wherego.wheregoserver.service.impl;

import com.wherego.wheregoserver.dto.AuthenticateResponseDto;
import com.wherego.wheregoserver.dto.CredentialDto;
import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.WriterDto;
import com.wherego.wheregoserver.exception.InvalidFieldNameException;
import com.wherego.wheregoserver.exception.UserNotFoundException;
import com.wherego.wheregoserver.mapper.WriterMapper;
import com.wherego.wheregoserver.repository.WriterRepository;
import com.wherego.wheregoserver.repository.entity.Writer;
import com.wherego.wheregoserver.service.JwtService;
import com.wherego.wheregoserver.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.text.ParseException;

@Service
public class WriterServiceImpl implements WriterService {

    @Autowired
    private WriterRepository writerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private WriterMapper writerMapper;


    @Override
    public AuthenticateResponseDto authenticate(CredentialDto credential) {
        try {
            Writer writer = writerRepository.getByEmail(credential.getEmail());
            if (writer != null && passwordEncoder.matches(credential.getPassword(), writer.getPassword())) {
                User user = new User(writer.getEmail(), writer.getPassword(),writer.getAuthorities());
                String token = jwtService.generateToken(user);
                return AuthenticateResponseDto
                        .builder()
                        .username(writer.getUsername())
                        .token(token)
                        .build();
            } else {
                throw new BadCredentialsException("Invalid credentials");
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidFieldNameException();
        }
        catch(UserNotFoundException e){
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    @Override
    public UserDetails loadByUserEmail(String email) {
        try {
            Writer writer = writerRepository.getByEmail(email);
           return new User(writer.getEmail(), writer.getPassword(),writer.getAuthorities());
        } catch (UserNotFoundException e) {
            throw new BadCredentialsException("Invalid credentials");
        }

    }

    @Override
    public ResponseMessageDto register(WriterDto register) {
        try {
            Writer writer = writerMapper.toWriter(register);
            writer.setPassword(passwordEncoder.encode(writer.getPassword()));
            writerRepository.create(writer);
            return ResponseMessageDto
                    .builder()
                    .message("Create account successfully")
                    .status(HttpStatus.CREATED)
                    .build();
        } catch (IOException e) {
            return ResponseMessageDto
                    .builder()
                    .message("Error when trying to upload file")
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .build();
        } catch (ParseException e) {
            return ResponseMessageDto
                    .builder()
                    .message("Incorrect date format")
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .build();
        } catch (Exception e) {
            if (e.getMessage() == null) {
                return ResponseMessageDto
                        .builder()
                        .message("Not enough required fields")
                        .status(HttpStatus.BAD_REQUEST)
                        .build();
            } else {
                return ResponseMessageDto
                        .builder()
                        .message(e.getMessage())
                        .status(HttpStatus.CONFLICT)
                        .build();
            }

        }
    }

    @Override
    public ResponseMessageDto checkUsernameExist( String username) {
        try {
            writerRepository.getByUsername(username);
            return ResponseMessageDto
                    .builder()
                    .message("Username invalid")
                    .status(HttpStatus.OK)
                    .build();
        } catch (UserNotFoundException e) {
            return ResponseMessageDto
                    .builder()
                    .message("Username is valid")
                    .status(HttpStatus.OK)
                    .build();
        }


    }
}
