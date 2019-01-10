package frc.robot.commands.AutonomousTools;

import frc.robot.*;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Command;

/*** Straight drive command 
 * @author Nathaniel 
 */
public class AdvancedDrive extends Command {

	/** Class wide variable declaration */
	private double speed, currentSpeed, finalSpeed, finalSpeedL, finalSpeedR, 
	dist, toBeTraveled, inch, leftEncDif, rightEncDif, startleftEncDif, percentComplete, 
	avgEncDif, startrightEncDif, originalDegrees, currentDegrees;

	/* Ramp? */
	private double ramp, rampStart, rampEnd;

	/* Timer variables */
	private double timeout, timer, timeEnd;
	
	//New Timer
	
	private double timeStart, timerEnd;

	/** PID, but not because this actually works
	 * @author Nathaniel
	 * @param speed
	 * @param distance in inches (must be positive(like no literally we make it positive(this is for the idiots)))
	 * @param timeout in milliseconds * 5 (200 in a second) 
	 * Note: 0 = no timeout
	 * Note: Minimum of 5 millisecond run time
	 * @category Drive Command
	 */
	public AdvancedDrive(double speed, double dist, double timeout) {
		this.speed = speed;
		this.dist = Math.abs(dist);
		this.timeout = timeout;

		/* Sets up encoders */
		RobotMap.leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		RobotMap.rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

	}

	protected void initialize() {
		/* Timer setup and check for if used */
		timeEnd = timeout;
		timer = 0;

		/* Grabs initial robot encoder position */
		startleftEncDif = RobotMap.leftMotor1.getSelectedSensorPosition(0);
		startrightEncDif = RobotMap.rightMotor1.getSelectedSensorPosition(0);

		/* Sets speed to an editable value and zeros out values */
		currentSpeed = speed;
		avgEncDif = 0;
		

		/* Grabs current heading to use for comparison during drive */
		originalDegrees = RobotMap.headingGyro.getAngle();

		/* Variables for the math of the encoder tick to distance */
		//double rotation = 1440;
		//double circumference = 12.25221134900019363000430919479;
		inch = 117.52980412939963256779108679819;
		toBeTraveled = (dist * inch * 1.045); // Distance to be traveled as used in the code

		/* Ramp? vars */
		ramp = currentSpeed / 2;
		rampStart = 0;
		rampEnd = 2;
		
		//New Timer
		timerEnd = ((timeout/60) * 1000) + 1000;
		timeStart = System.currentTimeMillis();
	}

	protected void execute() {
		
		double deltaTime = (timerEnd + timeStart) - System.currentTimeMillis();
		
		System.out.println("timerEnd: " + (long)timerEnd + " timeStart: " + (long)timeStart + " CurrentTime: " + System.currentTimeMillis() + " DeltaTime: " + (long)deltaTime);
		
		
		/** Heading Checks */
		/* Checks how far the robot has gone from the initial position */
		leftEncDif = Math.abs(startleftEncDif - RobotMap.leftMotor1.getSelectedSensorPosition(0));
		rightEncDif = Math.abs(startrightEncDif - RobotMap.rightMotor1.getSelectedSensorPosition(0));

		/* Checks current heading */
		currentDegrees = RobotMap.headingGyro.getAngle();

		/** Code used to adjust the heading for straighter travel */
		/* If going straight */
		if (currentSpeed > 0) {
			if (1 < originalDegrees - currentDegrees) {
				finalSpeedL = 1;
				finalSpeedR = .8;
			}
			else if (-1 > originalDegrees - currentDegrees) {
				finalSpeedL = .8;
				finalSpeedR = 1;
			}
			else {
				finalSpeedL = 1;
				finalSpeedR = 1;
			}
		}
		/* If going backwards */
		if (currentSpeed < 0) {
			if (1 < originalDegrees - currentDegrees) {
				finalSpeedL = .8;
				finalSpeedR = 1;
			}
			else if (-1 > originalDegrees - currentDegrees) {
				finalSpeedL = 1;
				finalSpeedR = .8;
			}
			else {
				finalSpeedL = 1;
				finalSpeedR = 1;
			}
		}

		/** Grabs a distance traveled based on the average of the two encoders */
		/* Both are working */
		if (leftEncDif != 0 && rightEncDif != 0) {
			avgEncDif = (leftEncDif + rightEncDif) / 2;
		}
		/* Left is working */
		else if (leftEncDif != 0 && rightEncDif == 0) {
			avgEncDif = (leftEncDif);
		}
		/* Right is working */
		else if (leftEncDif == 0 && rightEncDif != 0) {
			avgEncDif = (rightEncDif);
		}
		/* Neither are working */
		else {
			avgEncDif = 0;
		}

		/** Variable update code */
		/* Uses that average and the original distance to be traveled to make a percentage total completed */
		percentComplete = avgEncDif/toBeTraveled;

		/** Uses lidar to check if path is clear */
		if (Robot.notClear == false) {
			/* Ramp? */
			/* Sets up a final speed */
			if (rampStart < rampEnd) {
				if (rampStart < rampEnd / 2) {
					finalSpeed = ramp / 2;
				}
				else {
					finalSpeed = ramp;
				}
				rampStart++;
			}
			else {
				finalSpeed = currentSpeed;
			}

			/* Sets the motor with their respective offsets based on heading adjustment */ 
			Robot.drivetrain.difDrive.tankDrive(-finalSpeed * finalSpeedL, -finalSpeed * finalSpeedR);

			/* Timer step for if timed */
			timer++;
		}
		else {
			/* Stops the robot */
			Robot.drivetrain.difDrive.tankDrive(0, 0);

			/* Resets Ramp? */
			rampStart = 0;
		}
	}

	protected boolean isFinished() {
		boolean isFinished = false;

		/** Decides if code should use timeout */
		/* If already set */
		//Encoder not working
		if (Robot.EncoderBoolSet == true && Robot.EncoderBool == false) {
			if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true || timer >= timeEnd) {
				Robot.EncoderBool = false;
				isFinished = true;
			}
		}
		/* If not set */
		//Encoder not working
		if (timer > 5 && avgEncDif == 0) {
			if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true || timer >= timeEnd) {
				Robot.EncoderBoolSet = true;
				Robot.EncoderBool = false;
				isFinished = true;
			}
		}
		//Encoder working
		else if (timer > 5 && avgEncDif != 0) {
			if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true || percentComplete >= .99) {
				Robot.EncoderBoolSet = true;
				Robot.EncoderBool = true;
				isFinished = true;
			}
		}
		//New Timer
		if (System.currentTimeMillis() - timeStart >= timerEnd)
		{
			Robot.EncoderBoolSet = true;
			Robot.EncoderBool = true;
			isFinished = true;
		}
		//Cancel button
		else if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true) {
			isFinished = true;
		}
		
		return isFinished;
	}
}