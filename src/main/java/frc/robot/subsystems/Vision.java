package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.Vision.VisionHandler;

/**
 * @author Nathaniel
 */
public class Vision extends Subsystem {
	
	public double xDelta, xWidth, yDelta, yWidth, blockCount, width;
	
	private String[] camArray;

	/**
 	 * @author Nathaniel
 	 */
	public Vision() {
		Robot.debugPrint("\"Where we droppin' boys?\"");
	}

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new VisionHandler(2));
	}

	public String[] cameras() {
		camArray[0] = "none";
		camArray[1] = "lines blobs";
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
		return Robot.networktable.table.getEntry("cam_" + val + "_hatch").getDoubleArray(new double[0]);
	}
}