package edu.up.cg.raytracer.objects;

import edu.up.cg.raytracer.tools.IIntersectable;
import edu.up.cg.raytracer.tools.Intersection;
import edu.up.cg.raytracer.tools.Ray;
import edu.up.cg.raytracer.tools.Vector3D;

public class Triangle implements IIntersectable {
    public static final double EPSILON = 0.0000000000001;
    private Vector3D[] vertices;
    private Vector3D[] normals;

    public Triangle(Vector3D v0, Vector3D v1, Vector3D v2) {
        setVertices(v0, v1, v2);
        setNormals(null);
    }

    public Vector3D[] getVertices() {
        return vertices;
    }

    private void setVertices(Vector3D[] vertices) {
        this.vertices = vertices;
    }

    public void setVertices(Vector3D v0, Vector3D v1, Vector3D v2) {
        setVertices(new Vector3D[]{v0, v1, v2});
    }

    public Vector3D getNormal(){
        Vector3D normal = Vector3D.ZERO();
        Vector3D[] normals = getNormals();

        if(normals == null) {
            Vector3D[] vertices = getVertices();
            Vector3D v = Vector3D.substract(vertices[1], vertices[0]);
            Vector3D w = Vector3D.substract(vertices[0], vertices[2]);
            normal = Vector3D.normalize(Vector3D.crossProduct(v, w));
        }
        return normal;
    }

    public Vector3D[] getNormals() {
        return normals;
    }

    private void setNormals(Vector3D[] normals) {
        this.normals = normals;
    }

    @Override
    public Intersection getIntersection(Ray ray) {
        Intersection intersection = new Intersection(null, -1, null, null);

        Vector3D[] vert = getVertices();
        Vector3D v2v0 = Vector3D.substract(vert[2], vert[0]);
        Vector3D v1v0 = Vector3D.substract(vert[1], vert[0]);
        Vector3D vectorP = Vector3D.crossProduct(ray.getDirection(), v1v0);
        double det = Vector3D.dotProduct(v2v0, vectorP);
        double invDet = 1.0 / det;
        Vector3D vectorT = Vector3D.substract(ray.getOrigin(), vert[0]);
        double u = invDet * Vector3D.dotProduct(vectorT, vectorP);

        if (!(u < 0 || u > 1)) {
            Vector3D vectorQ = Vector3D.crossProduct(vectorT, v2v0);
            double v = invDet * Vector3D.dotProduct(ray.getDirection(), vectorQ);
            if (!(v < 0 || (u + v) > (1.0 + EPSILON))) {
                double t = invDet * Vector3D.dotProduct(vectorQ, v1v0);
                intersection.setDistance(t);
            }
        }

        return intersection;
    }
}