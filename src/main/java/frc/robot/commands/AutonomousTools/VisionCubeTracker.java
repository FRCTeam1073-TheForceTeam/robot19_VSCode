package frc.robot.commands.AutonomousTools;

import frc.robot.*;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionCubeTracker extends Command{

	edu.wpi.first.networktables.NetworkTable netTable;
	NetworkTableInstance netTableInst;
	public double xDelta, xWidth, yDelta, yWidth, blockCount, driveDir, v, width;
	public String dir;
	public boolean fullDir;
	
	/** Stays about 2 feet away from a cube. Will back up or move forwards and turn as necessary.
	 * @category Autonomous
	 * @param width (Default 110) How close to the robot the cube will be. Bigger is closer.
	 * @author Nathaniel
	 */
	public VisionCubeTracker(double width) {
		this.width = width;
		netTableInst = NetworkTableInstance.getDefault();
		netTable = netTableInst.getTable("TurretTable");
	}

	/** Stays about 2 feet away from a cube. Will back up or move forwards and turn as necessary.
	 * @category Autonomous
	 * @param width (Default 110) How close to the robot the cube will be. Bigger is closer.
	 * @author Nathaniel
	 */
	public VisionCubeTracker() {
		this.width = 110;
		netTableInst = NetworkTableInstance.getDefault();
		netTable = netTableInst.getTable("TurretTable");
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Sees if the command is running 
		//Robot.bling.sendFindingCube();
		v = 0;
		driveDir = 0;
		dir = "not set";
		fullDir = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Pulls variables from Network Tables
		xDelta = netTable.getEntry("centerDistX").getDouble(0);
		xWidth = netTable.getEntry("AverageWidth").getDouble(0);
		yDelta = netTable.getEntry("centerDistY").getDouble(0);
		yWidth = netTable.getEntry("AverageHeight").getDouble(0);
		blockCount = netTable.getEntry("Blocks").getDouble(0);

		// Defines speed and slow down markers
		double speed = 1;
		double side = 30; // Marks the reasonable area around the center	

		// Puts variables from Network Tables on SmartDashboard
		SmartDashboard.putNumber("xDelta", xDelta);
		SmartDashboard.putNumber("xWidth", xWidth);
		SmartDashboard.putNumber("yDelta", yDelta);
		SmartDashboard.putNumber("yWidth", yWidth);
		SmartDashboard.putNumber("Block Count", blockCount);
		SmartDashboard.putBoolean("I see you", false);
		// BlockCount asks the Pixy how many things it sees
		// when it sees something, we track it
		if (blockCount > 0) {
			SmartDashboard.putBoolean("I see you", true);
			// This code handles the left and right motion of the bot
			// based on the Pixy's values
			if (xDelta > 1.75 * side) {
				
				if (xDelta > 3 * side) {
					dir = "Very Right";
				}
				else {
					dir = "Right";
				}
			}
			else if (xDelta < -(1.75 * side)) {
				if (xDelta < -(3 * side)) {
					dir = "Very Left";
				}
				else {
					dir = "Left";
				}
			}
			else {
				dir = "Center";
			}

			// If block is far away: sets motor directions
			if (xWidth < width) {
				if (xWidth < width * .9) {
					if (xWidth < width * .775) {
						if (xWidth < width * .65) {
							if (xWidth < width * .4) {
								driveDir = 1.2;
							}
							else {
								driveDir = 1.15;
							}
						}
						else {
							driveDir = 1;
						}
					}
					else {
						driveDir = .9;	
					}
				}
				else {
					driveDir = .75;
				}
			}
			else {
				driveDir = 0;
				v++;
			}
			if (dir.equals("Right") && driveDir >= 0) {
				Robot.drivetrain.difDrive.tankDrive(-speed * driveDir / 1.0, 0);
				SmartDashboard.putString("visionDir", "right");
			}
			else if (dir.equals("Left") && driveDir >= 0) {
				Robot.drivetrain.difDrive.tankDrive(0, -speed * driveDir / 1.0);
				SmartDashboard.putString("visionDir", "left");
			}
			else if (dir.equals("Very Right") && driveDir >= 0) {
				Robot.drivetrain.difDrive.tankDrive(-speed * driveDir / 1.0, speed * .65);
				SmartDashboard.putString("visionDir", "very right");
			}
			else if (dir.equals("Very Left") && driveDir >= 0) {
				Robot.drivetrain.difDrive.tankDrive(speed * .65, -speed * driveDir / 1.0);
				SmartDashboard.putString("visionDir", "very left");
			}
			else if (dir.equals("Center")) {
				SmartDashboard.putString("visionDir", "center");
				Robot.drivetrain.difDrive.tankDrive(-speed * driveDir * 1.0, -speed * driveDir * 1.0);
			}
		}
		// When no blocks are seen, we strafe back and forth, and up and down,
		// while the bot looks for the target
		else {
			Robot.drivetrain.difDrive.tankDrive(0, 0);
			SmartDashboard.putString("visionDir", "HELP!");
		}
		SmartDashboard.putBoolean("clawBool", Robot.clawBool);
	}
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		boolean finished = false;

		if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true || width < xWidth) {
			if (v > 10 && Robot.clawBool == true) {
				SmartDashboard.putBoolean("clawBool", Robot.clawBool);
				finished = true;
			}
			if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true) {
				finished = true;
			}
		}
		
		return finished;
	}
}