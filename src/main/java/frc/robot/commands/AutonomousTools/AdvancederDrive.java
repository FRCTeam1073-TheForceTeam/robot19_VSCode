package frc.robot.commands.AutonomousTools;

import frc.robot.*;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Command;

/*** Straight drive command 
 * @author Nathaniel 
 */
public class AdvancederDrive extends Command {

	/* Class wide variable declaration */
	
	/** Distance to be traveled */
	private double distance;
	
	/** Encoder values */
	private double initialLeft, initialRight, currentLeft, currentRight;
	
	/** Current gyro value */
	private double currentDegrees;
	
	/** Initial gyro value */
	private double initialDegrees;
	
	/** Gyro adjustment deadzone */
	private double deadzone = 0.25;
	
	/** Normal running speed */
	private double normalSpeed = 1;
	
	/** Modifier to speed when adjusting for drift */
	private double adjustSpeed = .85;
	
	/** Near end running speed */
	private double slowSpeed = .75;
	
	/** Percent completion for slow down */
	private double slowPercent = .75;
	
	/** Timeout values */
	private double timeStart, timeout;

	/** Direction modifier */
	private double direction = 0;

	/** PID, but not, because this actually works
	 * @author Nathaniel
	 * @param distance in inches 
	 * Note: must be positive (like no literally we make it positive (this is for the idiots that might not read this but still it's extremely bad practice to assume negativity will change something when it won't so just do it right and don't use a negative please and thank you))
	 * @param direction "forward" or "backward"
	 * Note: will not run if string is incorrect or misspelled
	 * @param timeout in milliseconds
	 * Note: timeout should be slightly larger than the assumed runtime 
	 * and will finish early if less than the time taken to travel the requested distances
	 * Also: timeouts of 0 make the timeout not happen
	 * @category Drive Command
	 */
	public AdvancederDrive(double distance, String direction, double timeout) {
		this.distance = Math.abs(distance);
		
		if (direction.equals("forward")) this.direction = -1;
		else if (direction.equals("backward")) this.direction = 1;
		else System.out.println("WARN: robot19.commands.AutonomousTools.AdvancederDrive says\n"
				+ "\"Invalid string!\n"
				+ "Must be: \"forward\" or \"backward\"\n"
				+ "Your input: " + direction + "\"");
		
		if (timeout != 0) this.timeout = timeout;
		else this.timeout = 214748364.9999999;
	}

	protected void initialize() {
		/* Sets up encoders */
		RobotMap.leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		RobotMap.rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		
		/* Grabs initial robot encoder positions */
		initialLeft = RobotMap.leftMotor1.getSelectedSensorPosition(0);
		initialRight = RobotMap.rightMotor1.getSelectedSensorPosition(0);
		
		/* Grabs current heading to use for comparison during drive */
		initialDegrees = RobotMap.headingGyro.getAngle(); 
		
		/* Converts inches to encoder ticks */
		distance *= (118);
		
		/* Grabs a start time for timeout */
		timeStart = System.currentTimeMillis();
	}

	protected void execute() {
		/* Checks current heading */
		currentDegrees = RobotMap.headingGyro.getAngle();
		
		/* Grabs current robot encoder positions */
		currentLeft = RobotMap.leftMotor1.getSelectedSensorPosition(0);
		currentRight = RobotMap.rightMotor1.getSelectedSensorPosition(0);
		
		/* Sets the motor with their respective offsets based on heading adjustment */ 
		Robot.drivetrain.difDrive.tankDrive(direction * encoderCheck() * lidarCheck() * gyroCheck("left"), direction * encoderCheck() * lidarCheck() * gyroCheck("right"));
	}
	
	/** Uses encoders to check whether or not to start slowing down */
	private double encoderCheck() {
		if (percentCheck() >= slowPercent) return slowSpeed;
		return normalSpeed;
	}
	
	/** Checks percent of the distance traveled */
	private double percentCheck() {
		System.out.println("LOG: robot19.commands.AutonomousTools.AdvancederDrive says:\n"
				+ "\"percentCheck: TD " + (int)(Math.abs(currentLeft - initialLeft) + Math.abs(currentRight - initialRight) / (2 * distance)) + "\t CL" + (int)currentLeft + "\t CR" + (int)currentRight + "\"");
		if (Math.abs(initialLeft - currentLeft) == 0) return Math.abs(initialRight - currentRight) / (distance);
		if (Math.abs(initialRight - currentRight) == 0) return Math.abs(initialLeft - currentLeft) / (distance);
		return Math.abs(initialLeft - currentLeft) + Math.abs(initialRight - currentRight) / (2 * distance);
	}

	/** Modifier to stop the robot if lidar sees a robot */
	private double lidarCheck() {
		if (Robot.notClear) {
			System.out.println("LOG: robot19.commands.AutonomousTools.AdvancederDrive says:\n"
					+ "\"Lidar sees a robot!\"");
			return 0;
		}
		return 1;
	}

	/** Modifier to realign the robot if it drifts from a straight path */
	private double gyroCheck(String side) {
		System.out.println("LOG: robot19.commands.AutonomousTools.AdvancederDrive says:\n"
				+ "\"gyroCheck: CD " + currentDegrees + "\t ID " + initialDegrees + "\"");
		if (direction == 1 && side.equals("left") && currentDegrees > initialDegrees + deadzone) return adjustSpeed;
		if (direction == 1 && side.equals("right") && currentDegrees < initialDegrees - deadzone) return adjustSpeed;
		if (direction == -1 && side.equals("left") && currentDegrees < initialDegrees - deadzone) return adjustSpeed;
		if (direction == -1 && side.equals("right") && currentDegrees > initialDegrees + deadzone) return adjustSpeed;
		return 1;
	}
	
	/** Finishes 
	 * if either of the cancel buttons are pressed
	 * or if either the timeout or distance is traveled
	 */
	protected boolean isFinished() {
		if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true) return true;
		if (System.currentTimeMillis() - timeStart >= timeout || direction == 0 || percentCheck() >= 1) return true;
		return false;
	}
}