package com.example.qr_code_service.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QrCodeController {

    @GetMapping("/api/qrcode")
    public ResponseEntity<String> qrcode()
    {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Feature not impl yet!");
    }
}
