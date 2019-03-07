package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.NetworkTable;
import frc.robot.subsystems.Vision;

public class LineHandler extends Command {

    private Vision vision = Robot.vision;
    private NetworkTable table = Robot.networktable;
    private double[] lines;

    public boolean processed;

    /**
     * A vision processing line detection processor for use in autonomous alignment among other things
     * @author Nathaniel
     */
    public LineHandler() {
        processed = false;
    }

    protected void execute() {
        lines = vision.getLines();
    }



    protected boolean isFinished() {
        if (processed) return true;
        return false;
    }
}