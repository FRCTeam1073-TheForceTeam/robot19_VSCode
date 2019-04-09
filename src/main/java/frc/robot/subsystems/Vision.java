package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.Vision.*;

/**
 * @author Nathaniel
 */
public class Vision extends Subsystem {
	
	public double xDelta, xWidth, yDelta, yWidth, blockCount, width;
	
	private String[] camArray;
	
	public Point[] points;

	public Command handler;

	/**
 	 * @author Nathaniel
 	 */
	public Vision() {
		Robot.debugPrint("\"Where we droppin' boys?\"");
	}

	@Override
	protected void initDefaultCommand() {
	}

	/** Checks if vision sees anything */
	public double blockCheck() {
		if (blockCount > 0) return 1;
		return 0;
	}

	public double[] getLines(int val) {
		return Robot.networktable.visionTable.getEntry("cam_" + val + "_lineseg").getDoubleArray(new double[0]);
	}

	public double[] getBlobs(int val) {
		return Robot.networktable.visionTable.getEntry("cam_" + val + "_cargo").getDoubleArray(new double[0]);
	}

	public double[] getPoints(int val) {
		return Robot.networktable.visionTable.getEntry("cam_0_hatch").getDoubleArray(new double[0]);
	}
}