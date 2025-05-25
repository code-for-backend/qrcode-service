package com.example.qr_code_service.service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class QRCodeService {
    private static List<String> imageFormats = List.of("jpeg", "png", "gif","jpg");

    public byte[] getImage(int size, String type) throws IllegalArgumentException, IOException {
        if (size < 150 || size > 350)
            throw new IllegalArgumentException("Image size must be between 150 and 350 pixels");
        else if (!imageFormats.contains(type))
            throw new IllegalArgumentException("Only png, jpeg and gif image types are supported");
        else {
            BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.setColor(Color.WHITE);//it's like picking up a white paint brush
            g.fillRect(0, 0, size, size);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, type, byteArrayOutputStream);//writes the image to the byteArrayOutuptStream
            //rather than saving it to a file in the specified format
            byte[] bytes = byteArrayOutputStream.toByteArray();//serialize this image to send over the network
            return bytes;


        }

    }

}



