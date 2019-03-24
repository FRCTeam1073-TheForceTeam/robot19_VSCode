package frc.robot.commands.Vision;

public class Blob {

    private double camera, x, y, width, height, pixels, color;
    private double[] blob;

    public Blob(double camera, double[] blob) {
        this.camera = camera;
        this.blob = blob;
        setup();
    }

    private void setup() {
        x = blob[0];
        y = blob[1];
        width = blob[2];
        height = blob[3];
        pixels = blob[4];
        color = blob[5];
    }

    public double[] getRawBlob() {
        return blob;
    }

    public double camera() {
        return camera;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double width() {
        return width;
    }

    public double heigth() {
        return height;
    }

    public double pixels() {
        return pixels;
    }

    public double color() {
        return color;
    }
}