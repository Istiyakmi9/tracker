package com.bottrack.controller;

import com.bottrack.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api/ImageUploader")
public class ImageUploadController {

    @PostMapping("/uploadImage")
    public ResponseEntity<ApiResponse> uploadImage(@RequestParam("file")MultipartFile file){
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File("D:\\" + fileName));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(ApiResponse.Ok("Working successfully.."));
    }

}
