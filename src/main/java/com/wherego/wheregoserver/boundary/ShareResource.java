package com.wherego.wheregoserver.boundary;

import com.wherego.wheregoserver.constant.FileConstant;
import com.wherego.wheregoserver.exception.ResourceNotFoundException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("render")
public class ShareResource {

    @GetMapping(value="/{fileName}",produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<Resource> render(@PathVariable String fileName) throws HttpMediaTypeNotSupportedException, IOException {
        Path filePath = Paths.get(FileConstant.UPLOAD_DIR, fileName);
        if (!filePath.toFile().exists())
            throw new ResourceNotFoundException("File","fileName", fileName);

        String contentType = Files.probeContentType(filePath);
        if (!FileConstant.ALLOWED_IMAGE_TYPES.contains(contentType)) {
            String imageTypes = String.join(", ", FileConstant.ALLOWED_IMAGE_TYPES);
            throw new HttpMediaTypeNotSupportedException(
                    "Only supported image types: " + imageTypes
            );
        }
        byte[] fileBytes = Files.readAllBytes(filePath);

        return ResponseEntity.ok(new ByteArrayResource(fileBytes));
    }
}
