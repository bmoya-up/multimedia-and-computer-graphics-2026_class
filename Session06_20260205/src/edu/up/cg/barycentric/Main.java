package edu.up.cg.barycentric;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        float width = 400;
        float height = 400;

        Triangle triangle = new Triangle(new Point(0f, height), new Point(width / 2f, 0f), new Point(width, height));
        Color[] colorPoints = new Color[]{Color.RED, Color.GREEN, Color.BLUE};
//        Color[] colorPoints = new Color[]{new Color(0.8f,0.1f,0.0f), new Color(0.2f,0.9f,1.0f), new Color(1.0f,1.0f,1.0f)};

        BufferedImage image = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color color = Color.BLACK;
                Barycentric barycentric = Barycentric.calculateCoords(new Point(x, y), triangle);
                float[] areaCoords = barycentric.getAreaCoords();
                if (barycentric.isInTriangle()) {
                    int r = (int) ((colorPoints[0].getRed() * areaCoords[0]) + (colorPoints[1].getRed() * areaCoords[1]) + (colorPoints[2].getRed() * areaCoords[2]));
                    int g = (int) ((colorPoints[0].getBlue() * areaCoords[0]) + (colorPoints[1].getBlue() * areaCoords[1]) + (colorPoints[2].getBlue() * areaCoords[2]));
                    int b = (int) ((colorPoints[0].getGreen() * areaCoords[0]) + (colorPoints[1].getGreen() * areaCoords[1]) + (colorPoints[2].getGreen() * areaCoords[2]));
                    color = new Color(Integer.max(Integer.min(r, 255), 0), Integer.max(Integer.min(g, 255), 0), Integer.max(Integer.min(b, 255), 0));
                }

                image.setRGB(x, y, color.getRGB());
            }
        }

        saveImage(image, "Barycentric", "png");
    }

    public static void saveImage(BufferedImage image, String filename, String type) {
        File outputImage = new File(filename + "." + type);
        try {
            ImageIO.write(image, type, outputImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
