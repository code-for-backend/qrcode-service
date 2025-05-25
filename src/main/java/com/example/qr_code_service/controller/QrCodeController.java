package com.example.qr_code_service.controller;

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

@RestController
public class QrCodeController {

    @GetMapping("/api/qrcode")
    public ResponseEntity<byte[]> qrCode() {
        BufferedImage image = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        g.setColor(Color.WHITE);//it's like picking up the white paint brus
        g.fillRect(0, 0, 250, 250);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", byteArrayOutputStream);//writes the image to the byteArrayOutuptStream
            //rather than to disk
            byte[] bytes = byteArrayOutputStream.toByteArray();//serialize this image to send over the network
            return ResponseEntity.status(200).contentType(MediaType.IMAGE_PNG).body(bytes);
        }

        catch(IOException e)
            {
                String errorMsg = "Some error occured";
                return ResponseEntity.status(500).contentType(MediaType.TEXT_PLAIN).body(errorMsg.getBytes());

            }



    }

}
