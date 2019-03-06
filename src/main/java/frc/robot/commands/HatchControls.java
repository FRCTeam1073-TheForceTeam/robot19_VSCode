/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OperatorMode;
import frc.robot.Robot;

/**
 * Controls for hatch manipulation.
 * PID and non-PID, to make sure the robot doesn't break anything (including itself).
 * Deadzones, because frankly, why not?
 * Checked with operator (Jack), and controls are compatible with rest of operator OI.
 * @author BenWertz
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
	 * @author Nathaiel, Jack, Ben
	 * @see /subsystems/hatch.java
	 * @category Drive Command
	 */
	
	public HatchControls(double deadzone) {
		requires(Robot.hatch);
		this.deadzone = deadzone;
	}

	/** Called Repeatedly */
	protected void execute() {
		if (Robot.operatorMode.equals(OperatorMode.HATCH)) {
			flipper(-deadZoneCheck(Robot.oi.operatorControl.getRawAxis(1)));
			if (deadZoneCheck(Robot.oi.operatorControl.getRightTrigger()) > 0 || deadZoneCheck(Robot.oi.operatorControl.getLeftTrigger()) > 0) 
			Robot.hatch.setCollector(deadZoneCheck(Robot.oi.operatorControl.getRightTrigger()) - deadZoneCheck(Robot.oi.operatorControl.getLeftTrigger()));
			else Robot.hatch.setCollector(0);
		} else {
			flipper(0);
			Robot.hatch.setCollector(0);
		}
	}

	public void flipper(double val) {
		/*if (!Robot.hatch.topLim.get() && !Robot.hatch.bottomLim.get()) Robot.hatch.setFlipper(val);
		else if (Robot.hatch.topLim.get() && val < 0) Robot.hatch.setFlipper(val);
		else if (Robot.hatch.bottomLim.get() && val > 0) Robot.hatch.setFlipper(val);
		else */Robot.hatch.setFlipper(val);
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