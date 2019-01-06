package frc.robot.commands.AutonomousTools;

import frc.robot.*;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@SuppressWarnings("deprecation")
public class VisionerCuberTrackerer extends Command{

	// Defines speed and slow down markers	
	private final Vision vision = Robot.vision;
	private final double speed = .75;
	private double completionWidth;
	private double validZone; // Marks the reasonable area around the center
	
	/** Stays about 2 feet away from a cube. Will back up or move forwards and turn as necessary.
	 * @category Autonomous
	 * @param completionWidth (Default 110) How close to the robot the cube will be. Bigger is closer.
	 * @author Nathaniel
	 */
	public VisionerCuberTrackerer(double width, double validZone) {
		this.completionWidth = width;
		this.validZone = validZone;
	}

	/** Stays about 2 feet away from a cube. Will back up or move forwards and turn as necessary.
	 * @category Autonomous
	 * @param completionWidth (Default 110) How close to the robot the cube will be. Bigger is closer.
	 * @author Nathaniel
	 */
	public VisionerCuberTrackerer() {
		this.completionWidth = 110;
		this.validZone = 15;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.difDrive.tankDrive(vision.blockCheck() * turnMultiplier("right"), vision.blockCheck() * turnMultiplier("left"));
	}
	
	private double turnMultiplier(String side) {
		if ((side.equals("right") && vision.xDelta >= validZone) || (side.equals("left") && vision.xDelta <= validZone)) {
			if ((side.equals("right") && vision.xDelta >= validZone * 2) || (side.equals("left") && vision.xDelta <= validZone * 2)) return -.5;
			return .5;
		}
		return 1;
	}
	
	protected boolean isFinished() {
		if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true || completionWidth < vision.xWidth) return true;
		return false;
	}
}