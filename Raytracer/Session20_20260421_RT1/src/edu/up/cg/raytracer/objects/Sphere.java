package edu.up.cg.raytracer.objects;

import edu.up.cg.raytracer.tools.Intersection;
import edu.up.cg.raytracer.tools.Ray;
import edu.up.cg.raytracer.tools.Vector3D;

import java.awt.*;

public class Sphere extends Object3D {
    private double radius;

    public Sphere(Vector3D position, double radius, Color color) {
        super(position, color);
        setRadius(radius);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public Intersection getIntersection(Ray ray) {
        Vector3D L = Vector3D.substract(getPosition(),ray.getOrigin());
        double tca = Vector3D.dotProduct(L, ray.getDirection());

        // Behind camera
        if (tca < 0) {
            return null;
        }

        double L2 = Math.pow(Vector3D.magnitude(L), 2);
        double d2 = L2 - Math.pow(tca, 2);
        double r2 = Math.pow(getRadius(), 2);

        if (d2 > r2) {
            return null;
        }

        double d = Math.sqrt(d2);
        double t0 = tca - Math.sqrt(Math.pow(getRadius(), 2) - Math.pow(d, 2));
        double t1 = tca + Math.sqrt(Math.pow(getRadius(), 2) - Math.pow(d, 2));

        double distance = Math.min(t0, t1);
        Vector3D position = Vector3D.add(ray.getOrigin(), Vector3D.scalarMultiplication(ray.getDirection(), distance));
        Vector3D normal = Vector3D.normalize(Vector3D.substract(position, getPosition()));
        return new Intersection(position, distance, normal, this);
    }
}
