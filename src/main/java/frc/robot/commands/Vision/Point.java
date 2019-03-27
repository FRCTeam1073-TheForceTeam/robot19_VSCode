package frc.robot.commands.Vision;

public class Point {

    private double camera, x, distance;
    private double[] point;

    public Point(double camera, double[] point) {
        this.camera = camera;
        this.point = point;
        setup();
    }

    private void setup() {
        x = point[0];
        distance = point[1];
    }

    public double[] getRawPoint() {
        return point;
    }

    public double camera() {
        return camera;
    }

    public double x() {
        return x;
    }

    public double distance() {
        return distance;
    }

    public String toString() {
        return "Point: " + getRawPoint() + " From Cam: " + camera();
    }
}