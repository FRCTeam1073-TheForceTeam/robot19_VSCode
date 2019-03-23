package frc.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.HatchCommands.*;
import frc.robot.commands.CargoCommands.*;

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
	public JoystickButton operatorCancel, operatorRight, bucketButton, modeSwitch, hatchDownToLim;
	
    public OI() {
		/* Controller Assignment */
    	driverControl = new XboxController(0);
		operatorControl = new XboxController(1);

		/* Cancel Buttons */
		driverCancel = driverControl.a;
		operatorCancel = operatorControl.a;

		lowGearHold = driverControl.leftBumper;
		highGearHold = driverControl.rightBumper;

		hatchDownToLim = operatorControl.x;
		hatchDownToLim.whenPressed(new SendFlipperToLim(.5));

		bucketButton = operatorControl.b;
		bucketButton.whenPressed(new CargoBucketUp());
		bucketButton.whenReleased(new CargoBucketDown());

		operatorRight = operatorControl.rightBumper;
		operatorRight.whenPressed(new HatchGrabberDown());
		operatorRight.whenReleased(new HatchGrabberUp());
	}
}