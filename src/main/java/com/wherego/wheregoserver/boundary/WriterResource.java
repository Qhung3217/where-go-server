package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.constant.UserRole;
import com.wherego.wheregoserver.dto.ChangePasswordDto;
import com.wherego.wheregoserver.dto.ResponseMessageDto;
import com.wherego.wheregoserver.dto.writer.WriterDto;
import com.wherego.wheregoserver.dto.writer.WriterUpdateDto;
import com.wherego.wheregoserver.service.WriterService;
import com.wherego.wheregoserver.utils.HeaderUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@RestController
@RequestMapping(value="/writer")
@PreAuthorize("hasRole("+UserRole.WRITER+")")
public class WriterResource {
    @Autowired
    private WriterService writerService;
    @GetMapping(value="/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("Ping");
    }

    @GetMapping(value="/detail")
    public ResponseEntity<WriterDto> getDetail(@RequestHeader("Authorization") String authorizationHeader){
        String token = HeaderUtils.getToken(authorizationHeader);
        return ResponseEntity.ok(writerService.getDetail(token));
    }
    @PostMapping(value="/change-password")
    @Transactional
    public ResponseEntity<ResponseMessageDto> changePassword(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody ChangePasswordDto password
    ){
        String token = HeaderUtils.getToken(authorizationHeader);
        return ResponseEntity.ok(writerService.changePassword(token, password));
    }

    @PostMapping(value="/update")
    @Transactional
    public ResponseEntity<ResponseMessageDto> update(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "tels", required = false) String tels,
            @RequestParam(value = "dob", required = false) Date dob,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "avatar", required = false) MultipartFile avatarFile
    ){
        String token = HeaderUtils.getToken(authorizationHeader);
        WriterUpdateDto updateWriter = new WriterUpdateDto(name,tels,dob,username);
        return ResponseEntity.ok(writerService.update(token, updateWriter, avatarFile));
    }
}
