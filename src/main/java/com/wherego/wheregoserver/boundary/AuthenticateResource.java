package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.auth.AuthenticateResponseDto;
import com.wherego.wheregoserver.dto.auth.CredentialDto;
import com.wherego.wheregoserver.dto.traveler.TravelerRegisterDto;
import com.wherego.wheregoserver.dto.writer.WriterRegisterDto;
import com.wherego.wheregoserver.exception.InvalidFieldNameException;
import com.wherego.wheregoserver.exception.MissingParamsException;
import com.wherego.wheregoserver.exception.ResourceInvalidException;
import com.wherego.wheregoserver.service.TravelerService;
import com.wherego.wheregoserver.service.WriterService;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthenticateResource {

    private final WriterService writerService;
    private final TravelerService travelerService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticateResponseDto> login(
            @PathParam("role") String role,
            @RequestBody CredentialDto credential
    ) {
        if (role == null)
            throw new MissingParamsException(new String[]{"role"});

        switch (role) {
            case "writer":
                return ResponseEntity.ok(writerService.authenticate(credential));
            case "traveler":
                return ResponseEntity.ok(travelerService.authenticate(credential));
            default:
                throw new ResourceInvalidException("Role", role);
        }
    }

    //    ----------------------WRITER AUTH APIs --------------------
    @PostMapping(
            value = "/writer/register",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @Transactional
    public ResponseEntity<ResponseMessageDto> register(
            @RequestParam("email") String email,
            @RequestParam("name") String name,
            @RequestParam("tels") String tels,
            @RequestParam("dob") Date dob,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam(value = "avatar", required = false) MultipartFile avatarFile
    ) {
        WriterRegisterDto register =
                new WriterRegisterDto(
                        email, name, tels,
                        avatarFile, dob,
                        username, password
                );
        return new ResponseEntity<ResponseMessageDto>(
                writerService.register(register),
                HttpStatus.CREATED
        );
    }

    @PostMapping(value = "/writer/check-username-exist")
    public ResponseEntity<ResponseMessageDto> checkUsernameExist(
            @RequestBody MultiValueMap<String, String> payload
    ) {
        String username = payload.getFirst("username");
        if (username == null)
            throw new InvalidFieldNameException("Missing field username or value is null");
        return new ResponseEntity<ResponseMessageDto>(writerService.checkUsernameExist(username),
                HttpStatus.OK);
    }

    @PostMapping(value = "/writer/check-email-exist")
    public ResponseEntity<ResponseMessageDto> checkEmailExist(
            @RequestBody MultiValueMap<String, String> payload
    ) {
        String email = payload.getFirst("email");
        if (email == null)
            throw new InvalidFieldNameException("Missing field email or value is null");
        return new ResponseEntity<ResponseMessageDto>(writerService.checkEmailExist(email),
                HttpStatus.OK);
    }

    //    ----------------------END WRITER AUTH APIs --------------------
    //    ---------------------------------------------------------------
    //    ----------------------TRAVELER AUTH APIs ----------------------

    @PostMapping(
            value = "/traveler/register",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @Transactional
    public ResponseEntity<ResponseMessageDto> registerTraveler(
            @RequestParam("email") String email,
            @RequestParam("name") String name,
            @RequestParam("tels") String tels,
            @RequestParam("dob") Date dob,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam(value = "avatar", required = false) MultipartFile avatarFile
    ) {
        TravelerRegisterDto register =
                new TravelerRegisterDto(
                        email, name, tels,
                        avatarFile, dob,
                        username, password
                );
        return new ResponseEntity<ResponseMessageDto>(
                travelerService.register(register),
                HttpStatus.CREATED
        );
    }

    @GetMapping(value = "/traveler/check-username-exist")
    public ResponseEntity<ResponseMessageDto> checkTravelerUsernameExist(
            @PathParam("username") String username
    ) {
        if (username == null)
            throw new MissingParamsException(new String[]{"username"});
        return new ResponseEntity<ResponseMessageDto>(
                travelerService.checkUsernameExist(username),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/traveler/check-email-exist")
    public ResponseEntity<ResponseMessageDto> checkTravelerEmailExist(
            @PathParam("email") String email
    ) {
        if (email == null)
            throw new MissingParamsException(new String[]{"email"});
        return new ResponseEntity<ResponseMessageDto>(
                travelerService.checkEmailExist(email),
                HttpStatus.OK
        );
    }

    //    ----------------------END TRAVELER AUTH APIs ------------------

}
