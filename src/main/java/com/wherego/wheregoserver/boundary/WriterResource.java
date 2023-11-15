package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.constant.UserRole;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value="/writer")
@PreAuthorize("hasRole("+UserRole.WRITER+")")
public class WriterResource {
    @GetMapping(value="/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("Ping");
    }
}
