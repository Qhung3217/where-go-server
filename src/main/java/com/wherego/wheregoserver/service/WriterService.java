package com.wherego.wheregoserver.service;

import com.wherego.wheregoserver.dto.AuthenticateResponseDto;
import com.wherego.wheregoserver.dto.CredentialDto;
import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.WriterRegisterDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface WriterService {
    AuthenticateResponseDto authenticate(CredentialDto credential);

    UserDetails loadByUserEmail(String email);

    ResponseMessageDto register(WriterRegisterDto register);
    ResponseMessageDto checkUsernameExist(String username);
    ResponseMessageDto checkEmailExist(String email);
    Writer
}

