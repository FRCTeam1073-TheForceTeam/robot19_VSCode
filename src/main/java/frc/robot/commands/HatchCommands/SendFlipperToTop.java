package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SendFlipperToTop extends Command {

    private double speed;

    private DigitalInput flipperTopLim = RobotMap.flipperTopLim;

    public SendFlipperToTop(double speed) {
        this.speed = speed;
    }

    protected void initialize() {
        Robot.bling.sendFlipUp();
    }

    protected void execute() {
        Robot.hatch.setFlipper(speed);
    }

    protected boolean isFinished() {
        return !RobotMap.flipperTopLim.get() || Robot.oi.driverCancel.get() || Robot.oi.operatorCancel.get();
    }

    protected void end() {
        Robot.hatch.setFlipper(0);
		Robot.bling.sendDefaultPattern();
    }
}