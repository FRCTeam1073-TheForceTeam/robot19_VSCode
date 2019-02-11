package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import frc.robot.*;

public class Feedback extends Subsystem {

    private final Accelerometer accl = RobotMap.accelerometer;

    public Feedback() {

    }

    public void initDefaultCommand() {

    }

    public void periodic() {
        if (Math.abs(accl.getX()) > .2) {
            Robot.oi.driverControl.rumbleTime(percentRumble(accl.getX()), 10);
            Robot.oi.operatorControl.rumbleTime(percentRumble(accl.getX()), 10);
        }
        else if (Math.abs(accl.getY()) > .2) {
            Robot.oi.driverControl.rumbleTime(percentRumble(accl.getY()), 10);
            Robot.oi.operatorControl.rumbleTime(percentRumble(accl.getY()), 10);
        }
    }

    private double percentRumble(double val) {
        if (rumbleCalc(val) > 1) return 1;
        if (rumbleCalc(val) < 0.3) return 0;
        return rumbleCalc(val);
    }

    private double rumbleCalc(double val) {
        return (-0.0068202 * (val * val)) + (0.69219 * (val)) + -0.017037;
    }
}