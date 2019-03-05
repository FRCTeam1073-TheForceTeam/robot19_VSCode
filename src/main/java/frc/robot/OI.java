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
		operatorLeft.whenPressed(new operatorLeftAction());

		operatorRight = operatorControl.rightBumper;
		operatorRight.whenPressed(new operatorRightAction());

		modeSwitch = operatorControl.select;
		modeSwitch.whenPressed(new ModeSwitch());

		climberOverride = operatorControl.start;
		climberOverride.whenPressed(new ClimbOverride());
	}
}