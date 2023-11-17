package com.wherego.wheregoserver.service;

import com.wherego.wheregoserver.dto.*;
import com.wherego.wheregoserver.dto.writer.WriterDto;
import com.wherego.wheregoserver.dto.writer.WriterRegisterDto;
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
}

