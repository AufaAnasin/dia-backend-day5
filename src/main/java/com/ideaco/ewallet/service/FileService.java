package com.ideaco.ewallet.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileService {

    private final Path root = Paths.get("upload"); //linux

    public String saveFile(MultipartFile file) throws IOException {
//        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            // untuk code

            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));

            return file.getOriginalFilename();
    }
}
