/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutonomousTools;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchFoldUp extends Command {
  public HatchFoldUp() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.hatch.hatchLift.set(.5);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.hatch.topLim.get();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.hatch.hatchLift.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
