package com.example.qr_code_service.controller;

import com.example.qr_code_service.service.QRCodeService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

//Code tested and working
@RestController
public class QrCodeController {
    private final QRCodeService qrCodeService;

    public QrCodeController(QRCodeService qrCodeService)
    {
        this.qrCodeService=qrCodeService;
    }

    @GetMapping("/api/qrcode")
    public ResponseEntity<?> qrCode(@RequestParam int size,@RequestParam String type) {
        //Response<Entity<byte[]> not used so as to simplify error handling
        try {
            byte[] bytes=qrCodeService.getImage(size,type);
            String contentType="image/"+type;// example for content-type:image/png
            return ResponseEntity.status(200).contentType(MediaType.valueOf(contentType)).body(bytes);

        }
        catch(IOException e)//line 32
            {
               String errorMsg = "Some error occured in generating the image";
                return ResponseEntity.status(500).contentType(MediaType.APPLICATION_JSON)
                        .body(Map.of("error",errorMsg));

            }

        catch(IllegalArgumentException e)
        {

            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("error",e.getMessage()));//return json data containing the error
        }



    }

}
