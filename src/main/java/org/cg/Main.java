package org.cg;

public class Main {
    public static void main(String[] args) {
        // create points for segment s1
        Point3D s1A = new Point3D(1, 2, 3);
        Point3D s1B = new Point3D(5, 6, 7);
        Segment s1 = new Segment(s1A, s1B);

        // create points for segment s2
        Point3D s2A = new Point3D(2, 3, 4);
        Point3D s2B = new Point3D(6, 7, 8);
        Segment s2 = new Segment(s2A, s2B);

        // to det the relationship between s1 and s2
        int result = Segment.above(s1, s2);

        // Print the result
        switch (result) {
            case 0:
                System.out.println("Segments intersect.");
                break;
            case 1:
                System.out.println("Segment s1 is above segment s2.");
                break;
            case 2:
                System.out.println("Segment s2 is above segment s1.");
                break;
            case 3:
                System.out.println("Segments do not intersect.");
                break;
            default:
                System.out.println("Unexpected result.");
        }
    }
}