package frc.robot.commands;

import frc.robot.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This is the driver movement controls
 * for the teleoperated period of a match.
 * It is also the default command for 
 * the robotDrivetrain.java subsystem.
 * 
 * This command does not finish.
 * 
 * @author Nathaniel
 * @see ./subsystems/robotDriveTrain.java
 * @category Drive Command
 */
public class DriveControls extends Command {
	
	/** Controller Dead Zone */
	private double deadzone;

	/** Controller Data: Left Y */
	public double fwd;
	
	/** Controller Data: Right X */
	public double rot;

	/**
	 * This is the driver movement controls
	 * for the teleoperated period of a match.
	 * It is also the default command for
	 * the robotDrivetrain.java subsystem.
	 * 
	 * This command requires the drivetrain subsystem
	 * so as to give it priority over other commands 
	 * accessing the drivetrain.
	 * 
	 * This command does not finish.
	 * 
	 * @author Nathaniel
	 * @see ./subsystems/robotDriveTrain.java
	 * @category Drive Command
	 */
	public DriveControls(double deadzone) {
		requires(Robot.drivetrain);
		this.deadzone = deadzone;
	}

	/** Called Repeatedly */
	protected void execute() {
		/* Controller Data */
		fwd = Robot.oi.driverControl.getRawAxis(1);
		rot = Robot.oi.driverControl.getRawAxis(4);
		
		/* Outputs Checked Controller Data to Motors */
		Robot.drivetrain.difDrive.arcadeDrive(deadZoneCheck(fwd), -deadZoneCheck(rot));
	}
	
	/** 
	 * @param val Input to check against dead zone
	 * @return If within dead zone return 0, Else return val
	 */
	private double deadZoneCheck(double val) {
		if(Math.abs(val) < deadzone) return 0;
		return val;
	}

	/** 
	 * This command should never finish as it 
	 * must remain active for the duration of
	 * any teleoperated period, and is only run
	 * during the teleoperated period.
	 */
	protected boolean isFinished() {
		return false;
	}
}