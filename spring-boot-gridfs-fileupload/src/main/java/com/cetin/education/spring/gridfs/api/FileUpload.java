package com.cetin.education.spring.gridfs.api;

import com.cetin.education.spring.gridfs.service.FileUploadService;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
@Data
public class FileUpload {

    private final FileUploadService fileUploadService;

    @PostMapping
    public void fileUpload(@RequestParam MultipartFile multipartFile) throws IOException {
        fileUploadService.uploadFile(multipartFile);
    }
}
