package edu.up.cg.barycentric;

public class Barycentric {

    private float[] areaCoords = new float[3];

    private Barycentric(float areaCoords0, float areaCoords1, float areaCoords2) {
        setAreaCoords(areaCoords0, areaCoords1, areaCoords2);
    }

    public float[] getAreaCoords() {
        return areaCoords;
    }

    public void setAreaCoords(float areaCoords0, float areaCoords1, float areaCoords2) {
        areaCoords[0] = areaCoords0;
        areaCoords[1] = areaCoords1;
        areaCoords[2] = areaCoords2;
    }

    public static Barycentric calculateCoords(Point point, Triangle triangle){
        Point[] vert = triangle.getVertices();
        float denom = ((vert[1].getY() - vert[2].getY())*(vert[0].getX() - vert[2].getX()) + (vert[2].getX() - vert[1].getX())*(vert[0].getY() - vert[2].getY()));
        float areaCoord1 = ((vert[1].getY() - vert[2].getY())*(point.getX() - vert[2].getX()) + (vert[2].getX() - vert[1].getX())*(point.getY() - vert[2].getY())) / denom;
        float areaCoord2 = ((vert[2].getY() - vert[0].getY())*(point.getX() - vert[2].getX()) + (vert[0].getX() - vert[2].getX())*(point.getY() - vert[2].getY())) / denom;
        float areaCoord3 = 1 - areaCoord1 - areaCoord2;

        return new Barycentric(areaCoord1, areaCoord2, areaCoord3);
    }

    public boolean isInTriangle(){
        float[] coord = getAreaCoords();
        return coord[0] >= 0 && coord[1] >= 0 && coord[2] >= 0;
    }
}
