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
		handler = new VisionHandler(2);
		setDefaultCommand(handler);
	}

	public String[] cameras() {
		camArray[0] = "point";
		camArray[1] = "none";
		camArray[2] = "none";
		camArray[3] = "none";
		camArray[4] = "none";
		return camArray;
	}

	/** Checks if vision sees anything */
	public double blockCheck() {
		if (blockCount > 0) return 1;
		return 0;
	}

	public double[] getLines(int val) {
		return Robot.networktable.table.getEntry("cam_" + val + "_lineseg").getDoubleArray(new double[0]);
	}

	public double[] getBlobs(int val) {
		return Robot.networktable.table.getEntry("cam_" + val + "_cargo").getDoubleArray(new double[0]);
	}

	public double[] getPoints(int val) {
		return Robot.networktable.table.getEntry("cam_" + val + "_hatch").getDoubleArray(new double[0]);
	}
}