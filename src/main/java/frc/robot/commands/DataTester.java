/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class DataTester extends Command {
    public DataTester() {
      requires(Robot.drivetrain);
  }
  // RobotMap.headingGyro.reset();
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.oi.driverControl.b.get()) RobotMap.leftMotor1.set(1);
    else if (Robot.oi.driverControl.x.get()) RobotMap.leftMotor1.set(-1);
    else if (Robot.oi.driverControl.y.get()) RobotMap.rightMotor1.set(1);
    else if (Robot.oi.driverControl.start.get()) RobotMap.rightMotor1.set(-1);
    else {
      RobotMap.leftMotor1.set(0);
      RobotMap.rightMotor1.set(0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }
}
