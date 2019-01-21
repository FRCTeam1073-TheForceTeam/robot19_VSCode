/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.text.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class GearBox extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public double leftVelocity, rightVelocity, speed;
  private String gear;
  private DecimalFormat dec = new DecimalFormat("#.##");
  private final Pnuematic pnuematic = Robot.pnuematic;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void periodic() {
    update();
    shiftCheck();
  }

  public void shiftCheck() {
    if (speed >= 400 && !Robot.oi.lowGearHold.get()) pnuematic.setHighGear();
    else if (Robot.oi.highGearHold.get()) pnuematic.setHighGear();
    else pnuematic.setLowGear();
  }

  public void update() {
    leftVelocity = RobotMap.leftMotor1.getSelectedSensorVelocity();
    rightVelocity = RobotMap.rightMotor1.getSelectedSensorVelocity();
    speed = (Math.abs(leftVelocity) + Math.abs(rightVelocity)) / 2;
    if (pnuematic.isHighGear()) gear = "high";
    else if (pnuematic.isLowGear()) gear = "low";
    Robot.networktable.table.getEntry("GearBoxReadout").setString("\tLeft velocity: " + dec.format(leftVelocity / 141.6) + "\tRight velocity: " + dec.format(rightVelocity / 141.6) + "\tSpeed: " + dec.format(speed / 141.6) + "\tGear: " + gear);
    Robot.networktable.table.getEntry("Gyro").setString("Gyro: " + RobotMap.headingGyro.getAngle());
  }
}