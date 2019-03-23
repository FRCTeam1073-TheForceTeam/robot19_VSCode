package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.NetworkTable;
import frc.robot.subsystems.Vision;

public class LineHandler extends Command {

    private Vision vision = Robot.vision;
    private NetworkTable table = Robot.networktable;
    private double[] incomingLines;
    private Line[] choosenLines;

    private int searcher, lines;

    /**
     * A vision processing line detection processor for use in autonomous alignment among other things
     * @author Nathaniel
     */
    public LineHandler(int searcher, int lines) {
        this.searcher = searcher;
        this.lines = lines;
    }

    protected void execute() {
        incomingLines = vision.getLines();
        lineChooser(searcher, lines);
    }

    /** 
     * Chooses which item to prioritize and creates an array of line objects
     * 
     * searcher: 
     * 0 = x1
     * 1 = y1
     * 2 = x2
     * 3 = y2
     * 4 = theta
     * 5 = score
     * 6 = length
     * 
     * lines: 
     * the number of lines to be created
     */
    private void lineChooser(int searcher, int lines) {
        for (int i = searcher; i < incomingLines.length; i += 7) {
            
        }
    }

    protected boolean isFinished() {
        return false;
    }
}