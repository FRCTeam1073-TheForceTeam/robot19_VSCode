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
    //System.out.println(RobotMap.headingGyro.isConnected());
    System.out.println("DataTester says: \"Gyro is @ " + RobotMap.headingGyro.getAngle() + ", LeftEncoder is @" + Robot.drivetrain.leftEncoder + ", RightEncoder is @" + Robot.drivetrain.rightEncoder + "\"");
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }
}
