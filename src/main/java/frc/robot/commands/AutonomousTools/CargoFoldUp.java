package frc.robot.commands.AutonomousTools;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoFoldUp extends Command {
  public CargoFoldUp() {
    requires(Robot.cargo);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.cargo.cargoLift.set(.5);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.cargo.getLimitTop();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.cargo.cargoLift.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
