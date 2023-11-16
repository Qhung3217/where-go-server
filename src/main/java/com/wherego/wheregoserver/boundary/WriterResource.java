package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.constant.UserRole;
import com.wherego.wheregoserver.dto.writer.WriterDto;
import com.wherego.wheregoserver.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
        String token = authorizationHeader.substring(7);
        return ResponseEntity.ok(writerService.getDetail(token));
    }
}
