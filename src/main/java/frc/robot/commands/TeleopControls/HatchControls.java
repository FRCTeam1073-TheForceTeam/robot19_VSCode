/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.TeleopControls;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
public class HatchControls extends Command {
  public HatchControls() {
    requires(Robot.hatch);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.hatch.setFlipper(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double flipperValue=Robot.oi.operatorControl.getRawAxis(1);
    double intakeValue=Robot.oi.operatorControl.getRawAxis(5);
    System.out.println(flipperValue+"\t"+intakeValue);
    if(Robot.oi.operatorControl.y.get()){
      Robot.hatch.fingerRaise();
    }
    else if(Robot.oi.operatorControl.x.get()){
      Robot.hatch.fingerRaise();
    }

    if(!Robot.hatch.getLimitSwitchState()){
      Robot.hatch.setFlipper(flipperValue);
    }
    Robot.hatch.setIntake(intakeValue);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}