package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.CargoControls;

/**
 * @author Nathaniel
 */
public class Cargo extends Subsystem {

	/**
 	 * @author Nathaniel
 	 */
  public Cargo() {
		
	}

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new CargoControls());
  }

  public void bucketUp() {
    Robot.pnuematic.bucketExtend();
  }

  public void bucketDown() {
    Robot.pnuematic.bucketRetract();
  }
}