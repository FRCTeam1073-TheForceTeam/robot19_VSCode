package frc.robot.commands.Vision;

public class Point {

    private double camera, x, distance;
    private double[] point;

    public Point(double camera, double x, double distance) {
        this.camera = camera;
        this.x = x;
        this.distance = distance;
        point[0] = x;
        point[1] = distance;
    }

    public Point () {
        camera = -1;
        x = 0;
        distance = -1;
        point[0] = x;
        point[1] = distance;
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