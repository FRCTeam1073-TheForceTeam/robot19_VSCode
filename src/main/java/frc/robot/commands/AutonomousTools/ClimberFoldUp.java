package frc.robot.commands.AutonomousTools;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimberFoldUp extends Command {
  public ClimberFoldUp() {
    requires(Robot.climber);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.climber.rightClimber.set(.5);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;

    //THIS SHOULD BE REPLACED WITH THE LIMIT SWITCH CHECK
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.climber.rightClimber.set(0);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
