package edu.up.cg.raytracer.lights;

import edu.up.cg.raytracer.tools.Intersection;
import edu.up.cg.raytracer.tools.Vector3D;

import java.awt.*;

public class PointLight extends Light {
    public PointLight(Vector3D position, Color color, double intensity) {
        super(position, color, intensity);
    }

    @Override
    public double getNDotL(Intersection intersection) {
        return Math.max(
                Vector3D.dotProduct(intersection.getNormal(),
                        Vector3D.normalize(Vector3D.substract(getPosition(), intersection.getPosition()))),
                0.0);
    }
}