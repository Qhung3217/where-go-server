package com.wherego.wheregoserver.utils;

import com.wherego.wheregoserver.constant.FileConstant;
import com.wherego.wheregoserver.exception.InvalidFieldValueException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUtils {
    public static String uploadFile(MultipartFile file) throws IOException, NullPointerException {
        if (!isImageFile(file))
            throw new InvalidFieldValueException("Invalid image file");
        String originFileName = file.getOriginalFilename();
        String fileName = generateUniqueFilename() +
                originFileName.substring(originFileName.lastIndexOf('.'));
        // Create the upload directory if it doesn't exist
        Path uploadPath = Paths.get(FileConstant.UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the file to the server
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
        return fileName;
    }

    public static void removeOldFile(String fileName) throws IOException {
        if (fileName.equals(FileConstant.DEFAULT_IMAGE))
            return;
        Path oldFilePath = Paths.get(FileConstant.UPLOAD_DIR, fileName);
        Files.delete(oldFilePath);
    }

    private static String generateUniqueFilename() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());

        String randomValue = UUID.randomUUID().toString().replace("-", "");

        return timestamp + "_" + randomValue;
    }

    private static boolean isImageFile(MultipartFile file) {
        return file.getContentType() != null && FileConstant.ALLOWED_IMAGE_TYPES.contains(file.getContentType());
    }

}
