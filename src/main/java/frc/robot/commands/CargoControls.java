/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;

public class CargoControls extends Command {

  public double deadzone = .6;

  public CargoControls() {
    requires(Robot.cargo);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.oi.operatorControl.getRightTrigger() >= deadzone){
      if(!Robot.cargo.getLimitTop()){
        Robot.cargo.liftDrive(1);
      }
      if(Robot.cargo.getLimitTop()){
        Robot.cargo.collectorSpin(1);
      }
    }
    else if(Robot.oi.operatorControl.getLeftTrigger() >= deadzone){
      if(!Robot.cargo.getLimitBottom()){
        Robot.cargo.liftDrive(-1);
      }
      if(Robot.cargo.getLimitBottom()){
        Robot.cargo.collectorSpin(-1);
      }
    }
    //AHHHHHHH ELSE STATEMENT HERE FOR MAKING NEUTRAL DEFAULT
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }
}
