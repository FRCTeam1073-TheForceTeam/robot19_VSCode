package frc.robot.commands.AutonomousTools;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.Vision.*;

public class Align extends Command {

	private double distance, trigger, speed;

	private final double validZone = 5;

	private Point[] points;

	private Point point;

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
		distance = 80;
		trigger = 40;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		choosePoint();
	}

	private void choosePoint() {
		points = Robot.vision.points;

		double closest = 240;

		for (int i = 0; i < points.length; i++) {
			if (Math.abs(points[i].x()) < closest) {
				closest = Math.abs(points[i].x());
				point = points[i];
			}
			System.out.println(points[i].getRawPoint());
		}

		System.out.println(point);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (point.x() > trigger && point.x() < distance) {
			if (Math.abs(point.x()) > validZone) getAligned(point.x());
			else stayCentered();
		}
		choosePoint();
	}

	private void getAligned(double x) {
		if (x > validZone) Robot.drivetrain.tank(speed, speed * .85);
		else if (x < -validZone) Robot.drivetrain.tank(speed * .85, speed);
	}

	private void stayCentered() {
		Robot.drivetrain.tank(speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Robot.oi.driverCancel.get() || Robot.oi.operatorCancel.get()) {
			Robot.canceled = true;
			return true;
		}

		if (distance <= point.distance()) return true;
    	return false;
  	}
}