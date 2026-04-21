package edu.up.cg.raytracer.objects;

import edu.up.cg.raytracer.tools.Intersection;
import edu.up.cg.raytracer.tools.Ray;
import edu.up.cg.raytracer.tools.Vector3D;

import java.awt.*;

public abstract class Object3D {
    private Color color;
    private Vector3D position;

    public Object3D(Vector3D position, Color color) {
        setPosition(position);
        setColor(color);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vector3D getPosition() {
        return position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    public abstract Intersection getIntersection(Ray ray);
}
