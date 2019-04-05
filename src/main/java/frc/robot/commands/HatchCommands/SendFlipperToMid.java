package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SendFlipperToMid extends Command {

    private double speed;

    private DigitalInput flipperMidLim = RobotMap.flipperMidLim;

    public SendFlipperToMid(double speed) {
        this.speed = speed;
    }

    protected void initialize() {
        Robot.bling.sendHatchToMid();
    }

    protected void execute() {
        Robot.hatch.setFlipper(-speed);
    }

    protected boolean isFinished() {
        return !RobotMap.flipperMidLim.get() || Robot.oi.driverCancel.get() || Robot.oi.operatorCancel.get();
    }

    protected void end() {
        Robot.hatch.setFlipper(0);
		Robot.bling.sendDefaultPattern();
    }
}