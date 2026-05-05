package edu.up.cg.raytracer.lights;

import edu.up.cg.raytracer.tools.Intersection;
import edu.up.cg.raytracer.tools.Vector3D;

import java.awt.*;

public class DirectionalLight extends Light {
    private Vector3D direction;

    public DirectionalLight(Vector3D direction, Color color, double intensity) {
        super(Vector3D.ZERO(), color, intensity);
        setDirection(direction);
    }

    public Vector3D getDirection() {
        return direction;
    }

    public void setDirection(Vector3D direction) {
        this.direction = Vector3D.normalize(direction);
    }

    @Override
    public double getNDotL(Intersection intersection) {
        return Math.max(Vector3D.dotProduct(intersection.getNormal(), Vector3D.scalarMultiplication(getDirection(), -1.0)), 0.0);
    }

}