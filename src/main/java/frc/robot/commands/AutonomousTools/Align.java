package frc.robot.commands.AutonomousTools;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.Vision.*;

public class Align extends Command {

	/** Controller Dead Zone */
	private double deadzone;

	/** Controller Data: Left Y */
	private double forward;
	
	/** Controller Data: Right X */
	private double rotational;

	/** Output for Motor Power */
	private double leftMotorOutput, rightMotorOutput;

	/** Just a delay */
	private double executes = 0;

	private double distance, trigger, speed;

	private final double validZone = 10;

	private double[] data;

	private double[] point = new double[2];

	/**
	 * @category Autonomous
	 * @author Nathaniel
	 */
	public Align(double speed, double distance, double trigger) {
		requires(Robot.drivetrain);
    	this.distance = distance;
		this.speed = speed;
		this.trigger = trigger;
	}

	/** 
	 * @author Nathaniel
	 */
	public Align() {
		requires(Robot.drivetrain);
		speed = -.6;
		distance = 75;
		trigger = 30;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		choosePoint();
		Robot.bling.sendAlign();
	}

	private void choosePoint() {
		data = Robot.vision.getPoints(0);

		String str = "";
		for (int i = 0; i < data.length; i++) {
			str += data[i] + " ";
		}

		System.out.println(str);

		double closest = 240;

		for (int i = 0; i < data.length; i += 2) {
			if (Math.abs(data[i]) < closest) {
		 		closest = Math.abs(data[i]);
				point[0] = data[i];
				point[1] = data[i + 1];
			}
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		/* Controller Data */
		forward = Robot.oi.driverControl.getRawAxis(1);
		rotational = Robot.oi.driverControl.getRawAxis(4);
		if (Robot.oi.driverControl.select.get()) Robot.autoBox = !Robot.autoBox;
		if (!Robot.autoBox && Robot.oi.driverControl.x.get() && !Robot.pnuematic.isLowGear()) Robot.pnuematic.setLowGear();
		else if (!Robot.autoBox && Robot.oi.driverControl.x.get() && !Robot.pnuematic.isHighGear()) Robot.pnuematic.setHighGear();

		choosePoint();
		if (data.length > 0) {
			if (point[1] < distance && point[1] > trigger) {
				System.out.println(point[0]);
				if (Math.abs(point[0]) > validZone) getAligned(point[0]);
				else stayCentered();
			} else {
				/* Outputs Checked Controller Data to Motors */
				arcaderDrive(limit(deadZoneCheck(forward)), limit(deadZoneCheck(rotational)));
			}
		} else {
			/* Resets point */
			point = new double[2];
			/* Outputs Checked Controller Data to Motors */
			arcaderDrive(limit(deadZoneCheck(forward)), limit(deadZoneCheck(rotational)));
		}
	}

	private void getAligned(double x) {
		if (x < -validZone) Robot.drivetrain.tank(speed, speed * .5);
		else if (x > validZone) Robot.drivetrain.tank(speed * .5, speed);
	}

	private void stayCentered() {
		Robot.drivetrain.tank(speed);
	}

	private boolean endCheck(double pointDist) {
		if (pointDist >= distance || (pointDist > distance - 15 && data.length == 0)) return true;
		return false;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (point != null) return endCheck(point[1]) || Robot.oi.driverCancel.get() || Robot.oi.operatorCancel.get();
    	return Robot.oi.driverCancel.get() || Robot.oi.operatorCancel.get();
	}
	
	protected void end() {
		Robot.drivetrain.tank(0);
		Robot.bling.sendDefaultPattern();
	}










	/**************************************Backup Drive*******************************************/
	/**
   	 * Arcade drive method for differential drive platform.
  	 *
  	 * @param fwd The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
  	 * @param rot The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is positive.
  	 */
	public void arcaderDrive(double fwd, double rot) {
		double maxInput = Math.copySign(Math.max(Math.abs(fwd), Math.abs(rot)), fwd);
			if (fwd >= 0.0) {
			if (rot >= 0.0) {
				leftMotorOutput = maxInput;
				rightMotorOutput = fwd - rot;
			} else {
				leftMotorOutput = fwd + rot;
				rightMotorOutput = maxInput;
			}
		} else {
			if (rot >= 0.0) {
				leftMotorOutput = fwd + rot;
				rightMotorOutput = maxInput;
			} else {
				leftMotorOutput = maxInput;
				rightMotorOutput = fwd - rot;
			}
		}
		output(leftMotorOutput, rightMotorOutput, "Competition");
	}
	
	/** Affects the style of output to motors */
	private void output(double left, double right, String mode) {
		if (mode.equals("Zeroing")) {
			if (forward == 0 && rotational == 0) {
				if (executes > 20) Robot.drivetrain.zero();
				else Robot.drivetrain.tank(0, 0);
				executes++;
			}
			else {
				Robot.drivetrain.tank(limit(left), (limit(right)));
				executes = 0;
			}
		}
		else if (mode.equals("Ramped Zeroing")) {
			if (forward == 0 && rotational == 0) {
				if (executes > 20) Robot.drivetrain.zero();
				else Robot.drivetrain.tank(0, 0);
				executes++;
			}
			else {
				Robot.drivetrain.tank(limit(left), (limit(right)), 600);
				executes = 0;
			}
		}
		else if (mode.equals("Competition")) {
			double multiplier = .25;
			Robot.debugPrint(Robot.oi.driverControl.getRightTrigger());
			multiplier += deadZoneCheck(Robot.oi.driverControl.getRightTrigger() * .75);
			Robot.drivetrain.tank(limit(multiplier * left), (limit(multiplier * right)));	
		}
		else if (mode.equals("PID")) Robot.drivetrain.velocity(speedModifier(limit(left)), speedModifier(limit(right)));
		else Robot.drivetrain.tank(limit(left), (limit(right)));
	}
	
	/** 
	 * @param val Input to check against dead zone
	 * @return If within dead zone return 0, Else return val
	 */
	private double deadZoneCheck(double val) {
		if (Math.abs(val) < deadzone) return 0;
		return val;
	}
	
	/** A velocity  */
	private double speedModifier(double val) {
		return Math.copySign(Math.pow(Math.abs(val), 3) * 1300, val);
	}
	
	/**
	 * Limit motor values to the -1.0 to +1.0 range.
	 */
	private double limit(double value) {
		if (Math.abs(value) > 1.0) return Math.copySign(1, value);
		return value;
	}
	/**************************************Backup Drive*******************************************/
}