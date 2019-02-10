/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class DataTester extends Command {
    public DataTester() {
      requires(Robot.drivetrain);
  }
  // Robot.drivetrain.headingGyro.reset();
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.oi.driverControl.b.get()) Robot.drivetrain.leftMaster.set(1);
    else if (Robot.oi.driverControl.x.get()) Robot.drivetrain.leftMaster.set(-1);
    else if (Robot.oi.driverControl.y.get()) Robot.drivetrain.rightMaster.set(1);
    else if (Robot.oi.driverControl.start.get()) Robot.drivetrain.rightMaster.set(-1);
    else {
      Robot.drivetrain.leftMaster.set(0);
      Robot.drivetrain.rightMaster.set(0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }
}
