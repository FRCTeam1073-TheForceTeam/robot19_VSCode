/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * This is the hatch movement controls
 * for the teleoperated period of a match.
 * It is also the default command for 
 * the hatch.java subsystem.
 * 
 * This command does not finish.
 * 
 * @author Cam
 * @see /subsystems/hatch.java
 * @category Climb Command
 */
public class HatchControls extends Command {
	
	/** Controller Dead Zone */
	private double deadzone;

	/** Controller Data: Left Y */
	private double lift;
	private double collect;
	
	/** Controller Data: Right X */
	
	/** Output for Motor Power */
	private double HatchLiftOutput, HatchClimbOutput;

	/** Just a delay */
	private double executes = 0;


	/**
	 * This is the hatch movement controls
	 * for the teleoperated period of a match.
	 * It is also the default command for
	 * the hatch.java subsystem.
	 * 
	 * This command requires the drivetrain subsystem
	 * so as to give it priority over other commands 
	 * accessing the drivetrain.
	 * 
	 * This command does not finish.
	 * 
	 * @author Cam
	 * @see /subsystems/hatch.java
	 * @category Drive Command
	 */
	public HatchControls(double deadzone) {
		requires(Robot.hatch);
		this.deadzone = deadzone;
	}

	/** Called Repeatedly */
	protected void execute() {
		/* Controller Data */
		//left y axis
		lift = Robot.oi.operatorControl.getRawAxis(1);
		//left x axis
		collect = Robot.oi.operatorControl.getRawAxis(0);
		/* Outputs Checked Controller Data to Motors */
		tankHatch((deadZoneCheck(lift)), (deadZoneCheck(collect)));
	}

	/**
   	 * Tank climb method for differential drive platform.
   	 *
   	 * @param fwd The hatch's speed along the X axis [-1.0..1.0]. Forward is positive.
   	 */
	public void tankHatch(double UpDown, double Spin) {
		
		Robot.hatch.liftCollect(limit(UpDown), limit(Spin));

	}

	/** 
	 * @param val Input to check against dead zone
	 * @return If within dead zone return 0, Else return val
	 */
	private double deadZoneCheck(double val) {
		if (Math.abs(val) < deadzone) return 0;
		return val;

	}

  	/**
   	 * Limit motor values to the -1.0 to +1.0 range.
   	 */
  	private double limit(double value) {
    	if (Math.abs(value) > 1.0) return Math.copySign(1, value);
    	return value;
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