package frc.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
import frc.robot.commands.HatchCommands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	/** User Controllers */
	public XboxController driverControl, operatorControl;

	/** Driver Controls */
	public JoystickButton driverCancel, lowGearHold, highGearHold;

	/** Operator Controls */
	public JoystickButton operatorCancel, operatorRight, operatorLeft, modeSwitch, climberOverride;
	
    public OI() {
		/* Controller Assignment */
    	driverControl = new XboxController(0);
		operatorControl = new XboxController(1);

		/* Cancel Buttons */
		driverCancel = driverControl.a;
		operatorCancel = operatorControl.a;

		lowGearHold = driverControl.leftBumper;
		highGearHold = driverControl.rightBumper;

		operatorLeft = operatorControl.leftBumper;
		operatorRight = operatorControl.rightBumper;
		operatorLeft.whenPressed(new HatchGrab());
		operatorRight.whenPressed(new HatchPlace());
		
		modeSwitch = operatorControl.select;
		modeSwitch.whenPressed(new ModeSwitch(0));

		climberOverride = operatorControl.start;
		climberOverride.whenPressed(new ModeSwitch(1));

		operatorControl.x.whenPressed(new AutoHatchFloor());
	}
	public boolean getLeftBumperCargo() {
		return (operatorControl.leftBumper.get() && Robot.operatorMode.equals("Cargo"));
	}
	public boolean getLeftBumperHatch() {
		return (operatorControl.leftBumper.get() && Robot.operatorMode.equals("Hatch"));
	}
	public boolean getLeftBumperClimb() {
		return (operatorControl.leftBumper.get() && Robot.operatorMode.equals("Climb"));
	}
	public boolean getRightBumperCargo() {
		return (operatorControl.rightBumper.get() && Robot.operatorMode.equals("Cargo"));
	}
	public boolean getRightBumperHatch() {
		return (operatorControl.rightBumper.get() && Robot.operatorMode.equals("Hatch"));
	}
	public boolean getRightBumperClimb() {
		return (operatorControl.rightBumper.get() && Robot.operatorMode.equals("Climb"));
	}
	public double getOperatorY1Cargo() {
		if(Robot.operatorMode.equals("Cargo")) return (operatorControl.getY1());
		return 0;
	}
	public double getOperatorY1Hatch() {
		if(Robot.operatorMode.equals("Hatch")) return (operatorControl.getY1());
		return 0;
	}
	public double getOperatorY1Climb() {
		if(Robot.operatorMode.equals("Climb")) return (operatorControl.getY1());
		return 0;
	}
}