package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import frc.robot.*;

public class Feedback extends Subsystem {

    private final Accelerometer accl = RobotMap.accelerometer;
    public static double mt;
    public Feedback() {

    }

    public void initDefaultCommand() {

    }

    public void periodic() {
        mt = Robot.networktable.ds.getMatchTime();

        //Rumbles left/right/left/right at the start of endgame to warn drivers
        if (mt == 30) {
            Robot.oi.driverControl.rumble(percentRumble(accl.getX()), 0);
            Robot.oi.driverControl.rumble(0, percentRumble(accl.getX()));
            Robot.oi.driverControl.rumble(percentRumble(accl.getX()), 0);
            Robot.oi.driverControl.rumble(0, percentRumble(accl.getX()));
        }
        //Getting hit from the left
        if (accl.getX() > .2) {
            Robot.oi.driverControl.rumble(percentRumble(accl.getX()), 0);
            Robot.oi.operatorControl.rumble(percentRumble(accl.getX()), 0);
        }
        //Getting hit from the right
        else if (accl.getX() < -.2) {
            Robot.oi.driverControl.rumble(0, percentRumble(accl.getX()));
            Robot.oi.operatorControl.rumble(0, percentRumble(accl.getX()));
        }
        //Getting hit from the back or front
        else if (Math.abs(accl.getY()) > .2) {
            Robot.oi.driverControl.rumbleTime(percentRumble(accl.getY()), 10);
            Robot.oi.operatorControl.rumbleTime(percentRumble(accl.getY()), 10);
        }
        //nOThInG hIt uS No pRoBlEm wE CaN sTiLl hAvE RuMbLe
        else {
            Robot.oi.driverControl.rumbleTime(0, 0);
            Robot.oi.operatorControl.rumbleTime(0, 0);
        }
        //Got velocity?
        //System.out.println(accl.getZ());
    }

    private double percentRumble(double val) {
        if (rumbleCalc(val) > 1) return 1;
        if (rumbleCalc(val) < 0.3) return 0;
        return rumbleCalc(val);
    }

    private double rumbleCalc(double val) {
        return Math.abs((-0.0068202 * (val * val)) + (0.69219 * (val)) + -0.017037);
    }
}
