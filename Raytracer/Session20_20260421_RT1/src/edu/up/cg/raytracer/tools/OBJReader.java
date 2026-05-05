package edu.up.cg.raytracer.tools;

import edu.up.cg.raytracer.objects.Model3D;
import edu.up.cg.raytracer.objects.Triangle;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class OBJReader {

    public static Model3D getModel3D(String path, Vector3D origin, Color color) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));

            List<Triangle> triangles = new ArrayList<>();
            List<Vector3D> vertices = new ArrayList<>();
            List<Vector3D> normals = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("v ") || line.startsWith("vn ")) {
                    String[] vertexComponents = line.split("(\\s)+");
                    if (vertexComponents.length >= 4) {
                        double x = Double.parseDouble(vertexComponents[1]);
                        double y = Double.parseDouble(vertexComponents[2]);
                        double z = Double.parseDouble(vertexComponents[3]);
                        Vector3D vec = new Vector3D(x, y, z);
                        if (line.startsWith("v ")) {
                            vertices.add(vec);
                        } else {
                            normals.add(vec);
                        }
                    }
                } else if (line.startsWith("f ")) {
                    String[] faceComponents = line.split("(\\s)+");
                    List<Integer> faceVertex = new ArrayList<>();
                    List<Integer> faceNormals = new ArrayList<>();

                    for (int i = 1; i < faceComponents.length; i++) {
                        String[] infoVertex = faceComponents[i].split("/");
                        if (infoVertex.length >= 3) {
                            int vertexIndex = Integer.parseInt(infoVertex[0]);
                            int normalIndex = Integer.parseInt(infoVertex[2]);
                            faceVertex.add(vertexIndex);
                            faceNormals.add(normalIndex);
                        }
                    }

                    if (faceVertex.size() >= 3) {
                        Vector3D[] triangleVertices = new Vector3D[faceVertex.size()];
                        for (int i = 0; i < faceVertex.size(); i++) {
                            triangleVertices[i] = vertices.get(faceVertex.get(i) - 1);
                        }
                        triangles.add(new Triangle(triangleVertices[1], triangleVertices[0], triangleVertices[2]));
                        if (faceVertex.size() == 4) {
                            triangles.add(new Triangle(triangleVertices[2], triangleVertices[0], triangleVertices[3]));
                        }
                    }
                }
            }
            reader.close();
            return new Model3D(origin, triangles.toArray(new Triangle[triangles.size()]), color);
        } catch (IOException e) {
            System.err.println(e);
        }
        return null;
    }
}