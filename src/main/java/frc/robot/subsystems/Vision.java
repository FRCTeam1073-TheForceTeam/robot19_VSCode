package frc.robot.subsystems;

import frc.robot.*;

public class Vision {
	
	public double xDelta, xWidth, yDelta, yWidth, blockCount, width;
	
	public Vision() {
		System.out.println("\"Where we droppin' boys?\"");
	}

	/** Checks if vision sees anything */
	public double blockCheck() {
		if (blockCount > 0) return 1;
		return 0;
	}
	
	/** Pulls variables from Network Tables */
	public void refresh() {
		xDelta = Robot.networktable.table.getEntry("centerDistX").getDouble(0);
		xWidth = Robot.networktable.table.getEntry("AverageWidth").getDouble(0);
		yDelta = Robot.networktable.table.getEntry("centerDistY").getDouble(0);
		yWidth = Robot.networktable.table.getEntry("AverageHeight").getDouble(0);
		blockCount = Robot.networktable.table.getEntry("Blocks").getDouble(0);
		
		Robot.networktable.table.getEntry("VisionReadout").setString("BlockCount: " + blockCount + "\txDelta: " + xDelta + "\txWidth: " + xWidth + "\tyDelta: " + yDelta + "\tyWidth: " + yWidth);
	}
}