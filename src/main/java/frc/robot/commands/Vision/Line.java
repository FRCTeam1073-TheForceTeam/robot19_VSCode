package frc.robot.commands.Vision;

public class Line {

    private double x1, y1, x2, y2, theta, score, length;
    private double[] line;

    public Line(double[] line) {
        this.line = line;
        setup();
    }

    public void setup() {
        x1 = line[0];
        y1 = line[1];
        x2 = line[2];
        y2 = line[3];
        theta = line[4];
        score = line[5];
        length = line[6];
    }

    public double[] getLine() {
        return line;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getTheta() {
        return theta;
    }

    public double getScore() {
        return score;
    }

    public double getLength() {
        return length;
    }
}