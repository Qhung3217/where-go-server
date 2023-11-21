package com.wherego.wheregoserver.service;

import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.auth.AuthenticateResponseDto;
import com.wherego.wheregoserver.dto.auth.ChangePasswordDto;
import com.wherego.wheregoserver.dto.auth.CredentialDto;
import com.wherego.wheregoserver.dto.traveler.TravelerDto;
import com.wherego.wheregoserver.dto.traveler.TravelerRegisterDto;
import com.wherego.wheregoserver.dto.traveler.TravelerUpdateDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface TravelerService {
    ResponseMessageDto register(TravelerRegisterDto register);

    AuthenticateResponseDto authenticate(CredentialDto credential);

    UserDetails loadByUserEmail(String email);

    ResponseMessageDto checkUsernameExist(String username);

    ResponseMessageDto checkEmailExist(String email);

    TravelerDto getDetail(String token);

    ResponseMessageDto changePassword(String token, ChangePasswordDto password);

    ResponseMessageDto update(String token, TravelerUpdateDto updateTraveler);
}
