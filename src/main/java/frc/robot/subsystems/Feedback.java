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

    private double percentRumble(double val) {
        if (rumbleCalc(val) > 1) return 1;
        if (rumbleCalc(val) < 0.3) return 0;
        return rumbleCalc(val);
    }

    private double rumbleCalc(double val) {
        return Math.abs((-0.0068202 * (val * val)) + (0.69219 * (val)) + -0.017037);
    }
}
