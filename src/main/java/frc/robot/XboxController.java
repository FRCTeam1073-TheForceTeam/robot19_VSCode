package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XboxController extends Joystick {

	public JoystickButton a;
	public JoystickButton b;
	public JoystickButton x; 
	public JoystickButton y;
	public JoystickButton start;
	public JoystickButton select;
	public JoystickButton leftBumper;
	public JoystickButton rightBumper;
	public JoystickButton leftJoyButton;
	public JoystickButton rightJoyButton;
	
	public XboxController(int port) {
		super(port);
		a = new JoystickButton(this, 1);
		b = new JoystickButton(this, 2);
		x = new JoystickButton(this, 3);
		y = new JoystickButton(this, 4);
		start = new JoystickButton(this, 8);
		select = new JoystickButton(this, 7);
		leftBumper = new JoystickButton(this, 5);
		rightBumper = new JoystickButton(this, 6);
		leftJoyButton = new JoystickButton(this, 9);
		rightJoyButton = new JoystickButton(this, 10);
	}
	
	/**
	 * Gets the X axis of the left Xbox joystick.
	 * @return The X axis of the left Xbox joystick.
	 */
	public double getX1() {
		return getRawAxis(1);
	}
	
	/**
	 * Gets the Y axis of the left Xbox joystick.
	 * @return The Y axis of the left Xbox joystick.
	 */
	public double getY1() {
		return getRawAxis(2);
	}
	
	/**
	 * Gets the X axis of the right Xbox joystick.
	 * @return The X axis of the right Xbox joystick.
	 */
	public double getX2() {
		return getRawAxis(4);
	}
	
	/**
	 * Gets the Y axis of the right Xbox joystick.
	 * @return The Y axis of the right Xbox joystick.
	 */
	public double getY2() {
		return getRawAxis(5);
	}

	
	/**
	 * Gets the value of the left trigger.
	 * @return The value of the left trigger.
	 */
	public double getLeftTrigger() {
		return getRawAxis(2);
	}
	
	/**
	 * Gets the value of the right trigger.
	 * @return The value of the right trigger.
	 */
	public double getRightTrigger() {
		return getRawAxis(3);
	}
	
	/**
	 * Makes the controller rumble.
	 * @param left The left rumble value.
	 * @param right The right rumble value.
	 * 
	 */
	public void rumble(double left, double right) {
		setRumble(RumbleType.kLeftRumble, left);
		setRumble(RumbleType.kRightRumble, right);
	}
	
	/**
	 * Makes the controller rumble.
	 * @param val The rumble value.
	 */
	public void rumble(double val) {
		setRumble(RumbleType.kLeftRumble, val);
		setRumble(RumbleType.kRightRumble, val);
	}
	
	/**
	 * Makes the controller rumble for a period of time
	 * (Precondition: <code>milis</code> should be < 500)
	 * @param mag The rumble value.
	 * @param milis The duration of the rumble
	 */
	public void rumbleTime(double mag, double milis){
		setRumble(RumbleType.kLeftRumble,mag);
		setRumble(RumbleType.kRightRumble,mag);
		try {
			Thread.sleep((long) milis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setRumble(RumbleType.kLeftRumble,0);
		setRumble(RumbleType.kRightRumble,0);
	}
	
	/**
	 * Makes the controller rumble for a period of time repeated <code>times</code> times
	 * (Precondition: <code>milis</code> should be < 500)
	 * @param mag The rumble value.
	 * @param milis The duration of the rumble
	 * @param times The amount of repeats before end
	 */
	public void rumbleTimeRep(double mag, double milis, double times)
	{
		for(int i = 0; i < times; i++)
		{
			setRumble(RumbleType.kLeftRumble,mag);
			setRumble(RumbleType.kRightRumble,mag);
				try {
					Thread.sleep((long) milis);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			setRumble(RumbleType.kLeftRumble,0);
			setRumble(RumbleType.kRightRumble,0);
		}
	}
}