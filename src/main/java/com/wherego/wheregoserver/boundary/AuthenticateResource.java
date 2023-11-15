package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.dto.AuthenticateResponseDto;
import com.wherego.wheregoserver.dto.CredentialDto;
import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.WriterDto;
import com.wherego.wheregoserver.exception.MissingParamsException;
import com.wherego.wheregoserver.exception.ResourceInvalidException;
import com.wherego.wheregoserver.service.WriterService;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/auth")
@RequiredArgsConstructor
public class AuthenticateResource {

    private final WriterService writerService;

    @PostMapping(value="/login")
    public ResponseEntity<AuthenticateResponseDto> login(@PathParam("role") String role,
                                                         @RequestBody CredentialDto credential){
        if (role == null){
            String[] params = {"role"};
            throw new MissingParamsException(params);
        }

        switch (role) {
            case "writer":
                return ResponseEntity.ok(writerService.authenticate(credential));
            case "traveler":
            default: throw new ResourceInvalidException("Role", role);
        }
    }

    @PostMapping(value ="/writer/register")
    @Transactional
    public ResponseEntity<ResponseMessageDto> register(@RequestBody WriterDto register){
        return new ResponseEntity<ResponseMessageDto>(writerService.register(register),
                HttpStatus.CREATED);
    }

}
