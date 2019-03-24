package frc.robot.commands.Vision;

public class Line {

    private double camera, x1, y1, x2, y2, theta, score, length;
    private double[] line;

    public Line(double camera, double[] line) {
        this.camera = camera;
        this.line = line;
        setup();
    }

    private void setup() {
        x1 = line[0];
        y1 = line[1];
        x2 = line[2];
        y2 = line[3];
        theta = line[4];
        score = line[5];
        length = line[6];
    }

    public double[] getRawLine() {
        return line;
    }

    public double camera() {
        return camera;
    }

    public double x1() {
        return x1;
    }

    public double y1() {
        return y1;
    }

    public double x2() {
        return x2;
    }

    public double y2() {
        return y2;
    }

    public double theta() {
        return theta;
    }

    public double score() {
        return score;
    }

    public double length() {
        return length;
    }
}