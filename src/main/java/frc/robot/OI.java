package frc.robot;

import frc.robot.commands.AutonomousTools.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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
	public JoystickButton operatorCancel;
	public JoystickButton lowGearHold;
	public JoystickButton highGearHold;
	
    public OI() {
		
    	
    	driverControl = new XboxController(0);
		operatorControl = new XboxController(1);
		
		lowGearHold = driverControl.leftBumper;
		highGearHold = driverControl.rightBumper;
    	
    	driverCancel = driverControl.a;
    	operatorCancel = operatorControl.a;

    	visionButton = driverControl.b;
    	visionButton.whenPressed(new VisionCubeTracker());
    }
}