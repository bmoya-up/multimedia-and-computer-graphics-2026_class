package edu.up.cg.images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
//        image.setRGB(200, 200, Color.yellow.getRGB());
        
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                image.setRGB(x, y, Color.red.getRGB());
            }
        }

        File outputImage = new File("image.jpg");
        try {
            ImageIO.write(image, "jpg", outputImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
