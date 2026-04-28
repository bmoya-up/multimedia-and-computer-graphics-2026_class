package edu.up.cg.raytracer.tools;

public interface IIntersectable {
    public abstract Intersection getIntersection(Ray ray);
}