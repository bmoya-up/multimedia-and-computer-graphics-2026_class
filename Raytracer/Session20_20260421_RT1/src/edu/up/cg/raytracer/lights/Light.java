package edu.up.cg.raytracer.lights;

import edu.up.cg.raytracer.tools.Intersection;
import edu.up.cg.raytracer.tools.Ray;
import edu.up.cg.raytracer.tools.Vector3D;
import edu.up.cg.raytracer.objects.Object3D;

import java.awt.*;

public abstract class Light extends Object3D {
    private double intensity;

    public Light(Vector3D position, Color color, double intensity) {
        super(position, color);
        setIntensity(intensity);
    }

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    public abstract double getNDotL(Intersection intersection);

    @Override
    public Intersection getIntersection(Ray ray) {
        return new Intersection(Vector3D.ZERO(), -1, Vector3D.ZERO(), null);
    }
}