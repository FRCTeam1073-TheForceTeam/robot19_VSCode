package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OperatorMode;
import frc.robot.Robot;

/**
 * Controls for hatch manipulation.
 * PID and non-PID, to make sure the robot doesn't break anything (including itself).
 * Deadzones, because frankly, why not?
 * Checked with operator (Jack), and controls are compatible with rest of operator OI.
 * @author Ben
 */
public class HatchControls extends Command {

	/** Controller Dead Zone */
	private double deadzone;

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

	/** Sets flipper after checking limit switches */
	public void flipper(double val) {
		/*if (!Robot.hatch.topLim.get() && !Robot.hatch.bottomLim.get()) Robot.hatch.setFlipper(val);
		else if (Robot.hatch.topLim.get() && val < 0) Robot.hatch.setFlipper(val);
		else if (Robot.hatch.bottomLim.get() && val > 0) Robot.hatch.setFlipper(val);
		else */Robot.hatch.setFlipper(val);
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
	 * This command should never finish as it 
	 * must remain active for the duration of
	 * any teleoperated period, and is only run
	 * during the teleoperated period.
	 */
	protected boolean isFinished() {
		return false;
	}
}