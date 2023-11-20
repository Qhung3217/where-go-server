package com.wherego.wheregoserver.service.impl;

import com.wherego.wheregoserver.constant.FileConstant;
import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.auth.AuthenticateResponseDto;
import com.wherego.wheregoserver.dto.auth.CredentialDto;
import com.wherego.wheregoserver.dto.traveler.TravelerRegisterDto;
import com.wherego.wheregoserver.exception.InvalidFieldNameException;
import com.wherego.wheregoserver.exception.UserNotFoundException;
import com.wherego.wheregoserver.mapper.TravelerMapper;
import com.wherego.wheregoserver.repository.TravelerRepository;
import com.wherego.wheregoserver.repository.entity.Traveler;
import com.wherego.wheregoserver.repository.entity.Writer;
import com.wherego.wheregoserver.service.JwtService;
import com.wherego.wheregoserver.service.TravelerService;
import com.wherego.wheregoserver.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;

@Service
public class TravelerServiceImpl implements TravelerService {
    @Autowired
    private TravelerRepository travelerRepository;
    @Autowired
    private TravelerMapper travelerMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Override
    public ResponseMessageDto register(TravelerRegisterDto register) {
        try {
            Traveler traveler = travelerMapper.toTraveler(register);
            traveler.setPassword(passwordEncoder.encode(traveler.getPassword()));
            String avatarFileName = null;
            if (FileUtils.isValidFile(register.getAvatarFile())) {
                avatarFileName = FileUtils.generateUniqueFilename(register.getAvatarFile());
                traveler.setAvatar(avatarFileName);
            } else {
                traveler.setAvatar(FileConstant.DEFAULT_IMAGE);
            }
            travelerRepository.create(traveler);
            if (avatarFileName != null) {
                FileUtils.uploadFile(register.getAvatarFile(), avatarFileName);
            }
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
    public AuthenticateResponseDto authenticate(CredentialDto credential) {
        try {
            Traveler traveler = travelerRepository.getByEmail(credential.getEmail());
            if (
                    traveler != null
                            &&
                            passwordEncoder.matches(
                                    credential.getPassword(),
                                    traveler.getPassword()
                            )
            ) {
                User user = new User(
                        traveler.getEmail(),
                        traveler.getPassword(),
                        traveler.getAuthorities()
                );
                String token = jwtService.generateToken(user);
                return AuthenticateResponseDto
                        .builder()
                        .username(traveler.getUsername())
                        .token(token)
                        .build();
            } else {
                throw new BadCredentialsException("Invalid credentials");
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidFieldNameException();
        } catch (UserNotFoundException e) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }
}
