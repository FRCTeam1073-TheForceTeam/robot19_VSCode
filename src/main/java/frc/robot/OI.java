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
	public JoystickButton operatorCancel, hookDown;
	
    public OI() {
		/* Controller Assignment */
    	driverControl = new XboxController(0);
		operatorControl = new XboxController(1);

		/* Cancel Buttons */
		driverCancel = driverControl.a;
		operatorCancel = operatorControl.a;

		lowGearHold = driverControl.leftBumper;
		highGearHold = driverControl.rightBumper;

		hookDown = operatorControl.rightBumper;
		hookDown.whenPressed(new HatchGrabberDown());
		hookDown.whenReleased(new HatchGrabberUp());

		operatorControl.x.whenPressed(new AutoHatchFloor());
	}
}