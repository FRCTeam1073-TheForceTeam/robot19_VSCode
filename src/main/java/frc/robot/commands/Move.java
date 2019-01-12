/*---------------------------------------------------- - - - - - - - - - - - -*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
/**
 * An example command.  You can replace me with your own command.
 */
public class Move extends Command {
  public Move() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drive);
  
  }
  public Timer getTimer(){
    Timer timer = new Timer();
    return timer;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
    getTimer().reset();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drive.getrightMotor1().set(0.5);
    Robot.drive.getrightMotor2().set(0.5);
    Robot.drive.getleftMotor1().set(0.5);
    Robot.drive.getleftMotor2().set(0.5);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (getTimer().get() >= 5){
    Robot.drive.getrightMotor1().set(0);
    Robot.drive.getrightMotor2().set(0);
    Robot.drive.getleftMotor1().set(0);
    Robot.drive.getleftMotor2().set(0);
    return true;
    }
    else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.drive.getrightMotor1().set(0);
    Robot.drive.getrightMotor2().set(0);
    Robot.drive.getleftMotor1().set(0);
    Robot.drive.getleftMotor2().set(0);
  }
}