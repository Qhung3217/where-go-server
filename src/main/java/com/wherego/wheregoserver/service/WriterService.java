package com.wherego.wheregoserver.service;

import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.auth.AuthenticateResponseDto;
import com.wherego.wheregoserver.dto.auth.ChangePasswordDto;
import com.wherego.wheregoserver.dto.auth.CredentialDto;
import com.wherego.wheregoserver.dto.writer.WriterDto;
import com.wherego.wheregoserver.dto.writer.WriterRegisterDto;
import com.wherego.wheregoserver.dto.writer.WriterUpdateDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface WriterService {
    AuthenticateResponseDto authenticate(CredentialDto credential);

    UserDetails loadByUserEmail(String email);

    ResponseMessageDto register(WriterRegisterDto register);

    ResponseMessageDto checkUsernameExist(String username);

    ResponseMessageDto checkEmailExist(String email);

    WriterDto getDetail(String token);

    ResponseMessageDto changePassword(String token, ChangePasswordDto password);

    ResponseMessageDto update(String token, WriterUpdateDto newWriter, MultipartFile newAvatarFile);
}

