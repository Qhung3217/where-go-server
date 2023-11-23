package com.wherego.wheregoserver.service.impl;

import com.wherego.wheregoserver.constant.FileConstant;
import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.auth.AuthenticateResponseDto;
import com.wherego.wheregoserver.dto.auth.ChangePasswordDto;
import com.wherego.wheregoserver.dto.auth.CredentialDto;
import com.wherego.wheregoserver.dto.writer.DetailWriterDto;
import com.wherego.wheregoserver.dto.writer.WriterRegisterDto;
import com.wherego.wheregoserver.dto.writer.WriterUpdateDto;
import com.wherego.wheregoserver.exception.InvalidFieldNameException;
import com.wherego.wheregoserver.exception.InvalidFieldValueException;
import com.wherego.wheregoserver.exception.UserNotFoundException;
import com.wherego.wheregoserver.mapper.WriterMapper;
import com.wherego.wheregoserver.repository.WriterRepository;
import com.wherego.wheregoserver.repository.entity.Writer;
import com.wherego.wheregoserver.service.JwtService;
import com.wherego.wheregoserver.service.WriterService;
import com.wherego.wheregoserver.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
                User user = new User(writer.getEmail(), writer.getPassword(), writer.getAuthorities());
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
        } catch (UserNotFoundException e) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    @Override
    public UserDetails loadByUserEmail(String email) {
        try {
            Writer writer = writerRepository.getByEmail(email);
            return new User(writer.getEmail(), writer.getPassword(), writer.getAuthorities());
        } catch (UserNotFoundException e) {
            throw new BadCredentialsException("Invalid credentials");
        }

    }

    @Override
    public ResponseMessageDto register(WriterRegisterDto register) {
        try {
            Writer writer = writerMapper.toWriterIgnoreAvatarField(register);
            writer.setPassword(passwordEncoder.encode(writer.getPassword()));
            String avatarFileName = null;
            if (FileUtils.isValidFile(register.getAvatarFile())) {
                avatarFileName = FileUtils.generateUniqueFilename(register.getAvatarFile());
                writer.setAvatar(avatarFileName);
            } else {
                writer.setAvatar(FileConstant.DEFAULT_IMAGE);
            }
            writerRepository.create(writer);
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
    public ResponseMessageDto checkUsernameExist(String username) {
        try {
            writerRepository.getByUsername(username);
            return ResponseMessageDto
                    .builder()
                    .message("Username already exist")
                    .status(HttpStatus.OK)
                    .build();
        } catch (UserNotFoundException e) {
            return ResponseMessageDto
                    .builder()
                    .message("Username not exist")
                    .status(HttpStatus.OK)
                    .build();
        }
    }

    @Override
    public ResponseMessageDto checkEmailExist(String email) {
        try {
            writerRepository.getByEmail(email);
            return ResponseMessageDto
                    .builder()
                    .message("Email already exist")
                    .status(HttpStatus.OK)
                    .build();
        } catch (UserNotFoundException e) {
            return ResponseMessageDto
                    .builder()
                    .message("Email not exist")
                    .status(HttpStatus.OK)
                    .build();
        }
    }

    @Override
    public DetailWriterDto getDetail(String token) {
        String email = jwtService.extractUsername(token);
        Writer writer = writerRepository.getByEmail(email);
        return writerMapper.toDetailWriterDto(writer);
    }

    @Override
    public ResponseMessageDto changePassword(String token, ChangePasswordDto password) {
        try {
            String email = jwtService.extractUsername(token);
            Writer writer = writerRepository.getByEmail(email);
            if (passwordEncoder.matches(password.getCurrentPassword(), writer.getPassword())) {
                writer.setPassword(passwordEncoder.encode(password.getNewPassword()));
                writerRepository.update(writer);
                return ResponseMessageDto
                        .builder()
                        .message("Change password successfully")
                        .status(HttpStatus.OK)
                        .build();
            } else
                throw new InvalidFieldValueException(new String[]{"currentPassword"});
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
    public ResponseMessageDto update(String token, WriterUpdateDto newWriter, MultipartFile newAvatarFile) {
        try {
            String email = jwtService.extractUsername(token);
            Writer writer = writerRepository.getByEmail(email);
            String oldFileName = null;
            String newAvatarFileName = null;
            if (FileUtils.isValidFile(newAvatarFile) && !newAvatarFile.getOriginalFilename().equals(writer.getAvatar())) {
                oldFileName =  writer.getAvatar();
                newAvatarFileName = FileUtils.generateUniqueFilename(newAvatarFile);
                writer.setAvatar(newAvatarFileName);
            }
            writerRepository.update(mergeWriter(writer, newWriter));
            if (oldFileName != null) {
                if (!oldFileName.equals(FileConstant.DEFAULT_IMAGE))
                    FileUtils.removeOldFile(oldFileName);
                FileUtils.uploadFile(newAvatarFile, newAvatarFileName);
            }
            return ResponseMessageDto
                    .builder()
                    .message("Update account successfully")
                    .status(HttpStatus.OK)
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

    private Writer mergeWriter(Writer originWriter, WriterUpdateDto writerUpdate) {
        if (writerUpdate.getName() != null)
            originWriter.setName(writerUpdate.getName());
        if (writerUpdate.getTels() != null)
            originWriter.setTels(writerUpdate.getTels());
        if (writerUpdate.getDob() != null)
            originWriter.setDob(writerUpdate.getDob());
        if (writerUpdate.getUsername() != null)
            originWriter.setUsername(writerUpdate.getUsername());
        return originWriter;
    }

}
