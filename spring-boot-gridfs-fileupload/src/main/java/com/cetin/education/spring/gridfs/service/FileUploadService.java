package com.cetin.education.spring.gridfs.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.Data;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Data
public class FileUploadService {

    private final GridFsTemplate gridFsTemplate;

    public void uploadFile(MultipartFile multipartFile) throws IOException {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("fileName",multipartFile.getName());
        dbObject.put("contentType",multipartFile.getContentType());
        dbObject.put("size",multipartFile.getSize());
        dbObject.put("userId","12345");

        gridFsTemplate.store(multipartFile.getInputStream(),multipartFile.getOriginalFilename(), dbObject);
    }
}
