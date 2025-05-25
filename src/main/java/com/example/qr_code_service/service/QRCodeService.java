package com.example.qr_code_service.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class QRCodeService {
    private static final List<String> imageFormats = List.of("jpeg", "png", "gif","jpg");
    private static final List<String> correctionLevel=List.of("L","M","Q","H");

    public byte[] getImage(int size, String type,String content,String correction) throws IllegalArgumentException, IOException, WriterException {
           if(content.isBlank())
            throw new IllegalArgumentException("Contents cannot be null or blank");

        else if (size < 150 || size > 350)
            throw new IllegalArgumentException("Image size must be between 150 and 350 pixels");
        else if (!imageFormats.contains(type))
            throw new IllegalArgumentException("Only png, jpeg and gif image types are supported");
        else if(!correctionLevel.contains(correction))
            throw new IllegalArgumentException("Permitted error correction levels are L, M, Q, H");
        else {

               QRCodeWriter qrCodeWriter=new QRCodeWriter();
               Map<EncodeHintType,?> errorCorrectionHints=Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.valueOf(correction));
               BitMatrix bitMatrix=qrCodeWriter.encode(content, BarcodeFormat.QR_CODE,size,size,errorCorrectionHints);
               BufferedImage image= MatrixToImageWriter.toBufferedImage(bitMatrix);
               ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
               ImageIO.write(image, type, byteArrayOutputStream);//writes the image to the byteArrayOutuptStream
               //rather than saving it to a file in the specified format
               byte[] bytes = byteArrayOutputStream.toByteArray();//serialize this image to send over the network
               return bytes;



        }

    }

}



