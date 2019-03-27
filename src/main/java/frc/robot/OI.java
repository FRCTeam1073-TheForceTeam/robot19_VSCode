package frc.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Autonomous.PerfectDropoff;
import frc.robot.commands.AutonomousTools.Align;
import frc.robot.commands.HatchCommands.HatchGrabberDown;
import frc.robot.commands.HatchCommands.HatchGrabberUp;
import frc.robot.commands.HatchCommands.SendFlipperToMid;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	/** User Controllers */
	public XboxController driverControl, operatorControl;

	/** Driver Controls */
	public JoystickButton driverCancel, grab, drop;

	/** Operator Controls */
	public JoystickButton operatorCancel, operatorRight, bucketButton, modeSwitch, hatchDownToMid;
	
    public OI() {
		/* Controller Assignment */
    	driverControl = new XboxController(0);
		operatorControl = new XboxController(1);

		/* Cancel Buttons */
		driverCancel = driverControl.a;
		operatorCancel = operatorControl.a;

		grab = driverControl.leftBumper;
		grab.whenPressed(new Align(1, 1, 1));

		drop = driverControl.rightBumper;
		drop.whenPressed(new PerfectDropoff());

		/*bucketButton = operatorControl.b;
		bucketButton.whenPressed(new CargoBucketUp());
		bucketButton.whenReleased(new CargoBucketDown());*/

		operatorRight = operatorControl.rightBumper;
		operatorRight.whenPressed(new HatchGrabberDown());
		operatorRight.whenReleased(new HatchGrabberUp());

		
		hatchDownToMid = operatorControl.b;
		hatchDownToMid.whenPressed(new SendFlipperToMid(.5));
	}
}