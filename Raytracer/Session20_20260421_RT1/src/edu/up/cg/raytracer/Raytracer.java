package edu.up.cg.raytracer;

import edu.up.cg.raytracer.lights.DirectionalLight;
import edu.up.cg.raytracer.lights.Light;
import edu.up.cg.raytracer.objects.*;
import edu.up.cg.raytracer.tools.Intersection;
import edu.up.cg.raytracer.tools.OBJReader;
import edu.up.cg.raytracer.tools.Ray;
import edu.up.cg.raytracer.tools.Vector3D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Raytracer {
    public static void main(String[] args) {
        System.out.println(new Date());
        Scene scene01 = new Scene(Color.BLACK);

        // Clipping example
//        scene01.setCamera(new Camera(new Vector3D(0, 0, -4), 60, 60, 800,800, 9.7, 20.0));

        scene01.setCamera(new Camera(new Vector3D(0, 0, -4), 60, 60, 800,800, 0.6, 50.0));
        scene01.addLight(new DirectionalLight(new Vector3D(0.0, 0.0, 1.0), Color.WHITE, 1.0));
        scene01.addObject(new Sphere(new Vector3D(0.5, 1, 8), 0.8, Color.RED));
        scene01.addObject(new Sphere(new Vector3D(0.1, 1, 6), 0.5, Color.BLUE));
        scene01.addObject(new Model3D(new Vector3D(-1, -1, 3),
                new Triangle[]{
                        new Triangle(Vector3D.ZERO(), new Vector3D(1, 0, 0), new Vector3D(1,-1,0)),
                        new Triangle(Vector3D.ZERO(), new Vector3D(1,-1,0), new Vector3D(0,-1,0))},
                Color.GREEN));
        scene01.addObject(OBJReader.getModel3D("src/edu/up/cg/raytracer/models/SmallTeapot.obj",new Vector3D(0,-2.5,1), Color.CYAN));

        BufferedImage image = raytrace(scene01);
        File outputImage = new File("image.png");
        try {
            ImageIO.write(image, "png", outputImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(new Date());
    }

    public static BufferedImage raytrace(Scene scene) {
        Camera mainCamera = scene.getCamera();
        double[] nearFarPlanes = mainCamera.getNearFarPlanes();
        BufferedImage image = new BufferedImage(mainCamera.getResolutionWidth(), mainCamera.getResolutionHeight(), BufferedImage.TYPE_INT_RGB);
        List<Object3D> objects = scene.getObjects();
        List<Light> lights = scene.getLights();
        Vector3D[][] posRaytrace = mainCamera.calculatePositionsToRay();
        Vector3D pos = mainCamera.getPosition();
        double cameraZ = pos.getZ();

        for (int i = 0; i < posRaytrace.length; i++) {
            for (int j = 0; j < posRaytrace[i].length; j++) {
                double x = posRaytrace[i][j].getX() + pos.getX();
                double y = posRaytrace[i][j].getY() + pos.getY();
                double z = posRaytrace[i][j].getZ() + pos.getZ();

                Ray ray = new Ray(mainCamera.getPosition(), new Vector3D(x, y, z));
                Intersection closestIntersection = raycast(ray, objects, null,
                        new double[]{cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]});

                Color pixelColor = scene.getDefaultColor();
                if (closestIntersection != null) {
                    Color objColor = closestIntersection.getObject().getColor();

                    for (Light light : lights) {
                        double nDotL = light.getNDotL(closestIntersection);
                        Color lightColor = light.getColor();
                        double intensity = light.getIntensity() * nDotL;

                        double[] lightColors = new double[]{lightColor.getRed() / 255.0, lightColor.getGreen() / 255.0, lightColor.getBlue() / 255.0};
                        double[] objColors = new double[]{objColor.getRed() / 255.0, objColor.getGreen() / 255.0, objColor.getBlue() / 255.0};
                        for (int colorIndex = 0; colorIndex < objColors.length; colorIndex++) {
                            objColors[colorIndex] *= intensity * lightColors[colorIndex];
                        }

                        Color diffuse = new Color((float) Math.clamp(objColors[0], 0.0, 1.0),
                                (float) Math.clamp(objColors[1], 0.0, 1.0),
                                (float) Math.clamp(objColors[2], 0.0, 1.0));

                        pixelColor = addColor(pixelColor, diffuse);
                    }
                }
                image.setRGB(i, j, pixelColor.getRGB());
            }
        }

        return image;
    }

    public static Color addColor(Color original, Color otherColor) {
        float red = (float) Math.clamp((original.getRed() / 255.0) + (otherColor.getRed() / 255.0), 0.0, 1.0);
        float green = (float) Math.clamp((original.getGreen() / 255.0) + (otherColor.getGreen() / 255.0), 0.0, 1.0);
        float blue = (float) Math.clamp((original.getBlue() / 255.0) + (otherColor.getBlue() / 255.0), 0.0, 1.0);
        return new Color(red, green, blue);
    }

    public static Intersection raycast(Ray ray, List<Object3D> objects, Object3D caster, double[] clippingPlanes) {
        Intersection closestIntersection = null;

        for (int i = 0; i < objects.size(); i++) {
            Object3D currObj = objects.get(i);
            if (caster == null || !currObj.equals(caster)) {
                Intersection intersection = currObj.getIntersection(ray);
                if (intersection != null) {
                    double distance = intersection.getDistance();
                    double intersectionZ = intersection.getPosition().getZ();

                    if (distance >= 0 &&
                            (closestIntersection == null || distance < closestIntersection.getDistance()) &&
                            (clippingPlanes == null || (intersectionZ >= clippingPlanes[0] &&
                                    intersectionZ <= clippingPlanes[1]))) {
                        closestIntersection = intersection;
                    }
                }
            }
        }

        return closestIntersection;
    }
}