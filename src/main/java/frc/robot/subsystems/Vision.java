package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.Vision.LineHandler;

/**
 * @author Nathaniel
 */
public class Vision extends Subsystem {
	
	public double xDelta, xWidth, yDelta, yWidth, blockCount, width;

	private double[] lines;

	/**
 	 * @author Nathaniel
 	 */
	public Vision() {
		Robot.debugPrint("\"Where we droppin' boys?\"");
	}

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new LineHandler(6, 3));
	}

	/** Checks if vision sees anything */
	public double blockCheck() {
		if (blockCount > 0) return 1;
		return 0;
	}
	
	/** Pulls variables from Network Tables */
	public void refreshLines() {
		lines = Robot.networktable.table.getEntry("cam_#_lineseg").getDoubleArray(new double[0]);
	}

	public double[] getLines() {
		refreshLines();
		return lines;
	}
}