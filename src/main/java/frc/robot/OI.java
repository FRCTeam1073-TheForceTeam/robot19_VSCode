package frc.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.hatchCommands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public XboxController driverControl;
	public XboxController operatorControl;
	
	public JoystickButton RobotTeleInit;
	public JoystickButton visionButton;
	public JoystickButton driverCancel;
	public JoystickButton lowGearHold;
	public JoystickButton highGearHold;
	public JoystickButton motorTest;

	public JoystickButton operatorCancel;
	public JoystickButton cargoIn;
	public JoystickButton cargoOut;

	public JoystickButton duck;
	public JoystickButton flipper;
	public JoystickButton hatchDown;
	public JoystickButton hatchSpin;
	public JoystickButton hatchWheels;
	public JoystickButton lidarUp;
	public JoystickButton foldIn;
	public JoystickButton precisionMode;
	
    public OI() {
    	
    	driverControl = new XboxController(0);
		operatorControl = new XboxController(1);
		
		lowGearHold = driverControl.leftBumper;
		highGearHold = driverControl.rightBumper;
		
		driverCancel = driverControl.a;


		flipper = operatorControl.leftBumper;
		hatchDown = operatorControl.select;

		duck = operatorControl.rightBumper;
		duck.whileHeld(new HatchExtend());
		duck.whenReleased(new HatchRetract());

		foldIn = operatorControl.start;

		//hatchWheels = operatorControl.x;

		lidarUp = operatorControl.y;
		//Insert Do Thing

		precisionMode = operatorControl.b;
		//Insert Do Thing

		operatorCancel = operatorControl.a;


	}
}