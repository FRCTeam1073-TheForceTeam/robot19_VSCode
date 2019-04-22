package frc.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Autonomous.PerfectDropoff;
import frc.robot.commands.Autonomous.PerfectPickup;
import frc.robot.commands.AutonomousTools.Align;
import frc.robot.commands.HatchCommands.HatchGrabberDown;
import frc.robot.commands.HatchCommands.HatchGrabberUp;
import frc.robot.commands.HatchCommands.SendFlipperToMid;
import frc.robot.commands.Lidar.LidarAlign;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	/** User Controllers */
	public XboxController driverControl, operatorControl;

	/** Driver Controls */
	public JoystickButton driverCancel, grab, drop, align;

	/** Operator Controls */
	public JoystickButton operatorCancel, operatorRight, bucketButton, modeSwitch, hatchDownToMid;
	
    public OI() {
		SmartDashboard.putData("Lidar Align", new LidarAlign());
		/* Controller Assignment */
    	driverControl = new XboxController(0);
		operatorControl = new XboxController(1);

		/* Cancel Buttons */
		driverCancel = driverControl.a;
		operatorCancel = operatorControl.a;

		operatorRight = operatorControl.rightBumper;
		operatorRight.whenPressed(new HatchGrabberDown());
		operatorRight.whenReleased(new HatchGrabberUp());

		
		hatchDownToMid = operatorControl.b;
		hatchDownToMid.whenPressed(new SendFlipperToMid(.5));
	}
}