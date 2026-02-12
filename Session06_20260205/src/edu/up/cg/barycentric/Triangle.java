package edu.up.cg.barycentric;

public class Triangle {

    private Point[] vertices = new Point[3];

    public Triangle(Point v0, Point v1, Point v2) {
        setVertices(v0, v1, v2);
    }

    public Point[] getVertices() {
        return vertices;
    }

    public void setVertices(Point v0, Point v1, Point v2) {
        vertices[0] = v0;
        vertices[1] = v1;
        vertices[2] = v2;
    }
}
