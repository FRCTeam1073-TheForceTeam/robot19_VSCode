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
	
	/** Controller Data: Left Y */
	private double lift;
	private double collect;
	
	/** Controller Data: Right X */
	
	/** Output for Motor Power */
	private double HatchLiftOutput, HatchClimbOutput;

	/** Just a delay */
	private double executes = 0;

	public boolean pidMode = false;

	private double deadzone;


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
		pidMode=Robot.oi.operatorControl.y.get();
		if(pidMode){
			if(Robot.oi.operatorControl.leftBumper.get()){
				Robot.hatch.setFlipperUp();
			}else if(Robot.oi.operatorControl.start.get()){
				Robot.hatch.setFlipperDown();
			}else{
				Robot.hatch.setFlipperCenter();
			}
			if(Robot.oi.operatorControl.x.get()){
				Robot.hatch.collectorIntake();
			}else{
				Robot.hatch.collectorZero();
			}
		}else{
			lift=deadZoneCheck(Robot.oi.operatorControl.getRawAxis(1));
			Robot.hatch.setFlipperPower(lift);
		}
}

	private double deadZoneCheck(double val) {
		if (Math.abs(val) < deadzone) return 0;
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