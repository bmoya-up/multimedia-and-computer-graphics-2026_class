import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    
    public static void main(String[] args) {
        drawDiagonal("diagonal_image", "png");
        drawClock("clock_image", "png");
        drawMeadow("meadow_image", "png");
        drawGradient(Color.blue, Color.red);
    }
    
    public static void drawDiagonal(String filename, String fileType) {
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if ((x / 4) >= (y / 3)) image.setRGB(x, y, Color.red.getRGB());
                else image.setRGB(x, y, Color.blue.getRGB());
            }
        }
        
        saveImage(image, filename, fileType);
    }
    
    public static void drawClock(String filename, String fileType) {
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        int centerX = 400;
        int centerY = 300;
//        drawCircle(image, 400, 300, 200, 50, Color.white);
        drawCircle1(image, centerX, centerY, 200, 10, 0.05f, Color.white);
        drawCircle1(image, centerX, centerY, 180, 10, (360f / 12), Color.white);
        
        // Minute hand
        int minuteHandLength = 150;
        int minutes = 45;
        int minuteX = centerX + (int) (minuteHandLength * Math.cos(Math.toRadians((minutes / 60.0f) * 360 - 90f)));
        int minuteY = centerY + (int) (minuteHandLength * Math.sin(Math.toRadians((minutes / 60.0f) * 360 - 90f)));
        drawLine(image, centerX, centerY, minuteX, minuteY, Color.white);
        
        // Hour hand
        int hourHandLength = 100;
        int hour = 10;
        int hourX = centerX + (int) (hourHandLength * Math.cos(Math.toRadians((hour + (minutes / 60f) / 12.0f) * 360 - 90f)));
        int hourY = centerY + (int) (hourHandLength * Math.sin(Math.toRadians((hour + (minutes / 60f) / 12.0f) * 360 - 90f)));
        drawLine(image, centerX, centerY, hourX, hourY, Color.white);
        
        saveImage(image, filename, fileType);
    }
    
    public static void drawCircle(BufferedImage image, int centerX, int centerY, int radius, int thickness, Color color) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int dx = x - centerX;
                int dy = y - centerY;
                
                if (dx * dx + dy * dy <= radius * radius && dx * dx + dy * dy >= radius * radius - (thickness * thickness)) {
                    image.setRGB(x, y, color.getRGB());
                }
            }
        }
    }
    
    public static void drawCircle1(BufferedImage image, int centerX, int centerY, int radius, int thickness, float skip, Color color) {
        for (float angle = 0; angle < 360; angle += skip) {
            for (int t = 0; t < thickness; t++) {
                int x = (int) (centerX + (radius - t) * Math.cos(Math.toRadians(angle)));
                int y = (int) (centerY + (radius - t) * Math.sin(Math.toRadians(angle)));
                
                if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
                    image.setRGB(x, y, color.getRGB());
                }
            }
        }
    }
    
    public static void drawLine(BufferedImage image, int x1, int y1, int x2, int y2, Color color) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        
        for (float i = 0; i <= 1; i += 0.001f) {
            int x = (int) (x1 + i * dx);
            int y = (int) (y1 + i * dy);
            
            if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
                image.setRGB(x, y, color.getRGB());
            }
        }
    }
    
    public static void drawMeadow (String filename, String fileType) {
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        
        // Background
        fillImage(image, Color.white);
        // Grass
        drawWave(image, 150, 6, 20, Color.green);
        // Sun
        drawLine(image, 150, 15, 150, 245, Color.RED);
        drawLine(image, 35, 130, 265, 130, Color.RED);
        drawCircle1(image, 150, 130, 100, 100, 0.2f, Color.YELLOW);
        
        saveImage(image, filename, fileType);
    }
    
    public static void drawWave(BufferedImage image, int height, int frequency, int amplitude, Color color) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int currentHeight = (image.getHeight() - height) + (int) (amplitude * Math.sin((2 * Math.PI * frequency * x) / image.getWidth()));
                if (y >= currentHeight && y < image.getHeight()) {
                    image.setRGB(x, y, color.getRGB());
                }
            }
        }
    }
    
    public static void drawGradient(Color color1, Color color2) {
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                float ratio = (float) y / image.getHeight();
                int red = (int) (color1.getRed() * (1 - ratio) + color2.getRed() * ratio);
                int green = (int) (color1.getGreen() * (1 - ratio) + color2.getGreen() * ratio);
                int blue = (int) (color1.getBlue() * (1 - ratio) + color2.getBlue() * ratio);
                Color blendedColor = new Color(red, green, blue);
                image.setRGB(x, y, blendedColor.getRGB());
            }
        }
        
        saveImage(image, "gradient_image", "png");
    }
    
    public static void fillImage(BufferedImage image, Color color) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                image.setRGB(x, y, color.getRGB());
            }
        }
    }
    
    public static void saveImage(BufferedImage image, String fileName, String fileType) {
        File file = new File(fileName + "." + fileType);
        try {
            ImageIO.write(image, fileType, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
