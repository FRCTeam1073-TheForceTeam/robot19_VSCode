/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveControls extends Command {
  WPI_TalonSRX left_motor;
  WPI_TalonSRX right_motor;
  public DriveControls() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    left_motor = Robot.drivetrain.leftMaster;
    right_motor = Robot.drivetrain.rightMaster;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    left_motor.set(0);
    right_motor.set(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    left_motor.set(Robot.oi.driverControl.getY1());
    right_motor.set(Robot.oi.driverControl.getY2());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.oi.driverCancel.get();
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
