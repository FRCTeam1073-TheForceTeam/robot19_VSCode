package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Presets;
import frc.robot.Robot;

/**
 * @author Nathaniel
 */
public class Cargo extends Subsystem {

	/**
 	 * @author Nathaniel
 	 */
	public Cargo() {
	}

	public boolean isBucketUp() {
		return Robot.pnuematic.isBucketUp();
	}

	public boolean isBucketDown() {
		return Robot.pnuematic.isBucketDown();
	}

	public void bucketUp() {
		Robot.pnuematic.bucketUp();
	}

	public void bucketDown() {
		Robot.pnuematic.bucketDown();
	}

  	@Override
  	public void initDefaultCommand() {

	}
}