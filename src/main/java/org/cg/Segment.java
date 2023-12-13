package org.cg;


class Segment {
    public Point3D A, B; // the coordinates of the endpoints of the segment

    public Segment(Point3D A, Point3D B) {
        this.A = A;
        this.B = B;
    }

    /**
     * Determines the relative positioning of two segments.
     *
     * @param s1 Segment s1.
     * @param s2 Segment s2.
     * @return 0 if s1 and s2 intersect, 1 if s1 is above s2, 2 if s2 is above s1, and 3 otherwise.
     */
    public static int above(Segment s1, Segment s2) {
        // to get projections of segments s1 and s2 on the xoy plane
        Segment s1Projection = new Segment(new Point3D(s1.A.x, s1.A.y, 0), new Point3D(s1.B.x, s1.B.y, 0));
        Segment s2Projection = new Segment(new Point3D(s2.A.x, s2.A.y, 0), new Point3D(s2.B.x, s2.B.y, 0));

        // to check if s1Projecion and s2Projection intersect
        Point3D intersectionPoint = intersect(s1Projection, s2Projection);

        if (intersectionPoint != null) {
            // to calculate the z-coordinates of the intersection points on segments s1 and s2
            double t1 = (intersectionPoint.x - s1Projection.A.x) / (s1Projection.B.x - s1Projection.A.x);
            double t2 = (intersectionPoint.x - s2Projection.A.x) / (s2Projection.B.x - s2Projection.A.x);

            // to check the relative positionng based on z-coordinates
            if (t1 >= 0 && t2 >= 0) {
                if (t1 > t2) {
                    return 1; // s1 is above s2
                } else if (t1 < t2) {
                    return 2; // s2 is above s1
                } else {
                    return 0; // s1 and s2 intersect
                }
            }
        }

        return 3;
    }

    // helper method to find the intersection point of two line segments on the xoy plane
    private static Point3D intersect(Segment s1, Segment s2) {
        double x1 = s1.A.x, y1 = s1.A.y;
        double x2 = s1.B.x, y2 = s1.B.y;
        double x3 = s2.A.x, y3 = s2.A.y;
        double x4 = s2.B.x, y4 = s2.B.y;

        double det = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (det == 0) {
            return null; // segments are parallel or coincident
        }

        double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / det;
        double u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / det;

        if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {
            double x = x1 + t * (x2 - x1);
            double y = y1 + t * (y2 - y1);
            return new Point3D(x, y, 0);
        }

        return null; // no intersection point found
    }
}