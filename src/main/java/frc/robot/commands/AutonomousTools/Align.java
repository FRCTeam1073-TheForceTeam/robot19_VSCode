package frc.robot.commands.AutonomousTools;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.Vision.VisionHandler;

public class Align extends Command {

	private double distance, trigger, speed;

	/**
	 * @category Autonomous
	 * @author Nathaniel
	 */
	public Align(double speed, double distance, double trigger) {
    this.distance = distance;
    this.speed = speed;
	}

	/** Stays about 2 feet away from a cube. Will back up or move forwards and turn as necessary.
	 * @category Autonomous
	 * @param width (Default 110) How close to the robot the cube will be. Bigger is closer.
	 * @author Nathaniel
	 */
	public Align() {
		speed = .8;
		distance = 85;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		for (int i = 0; i < Robot.vision.points.length; i++) {
			System.out.println(Robot.vision.points[i].getRawPoint());
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
    return false;
  }
}