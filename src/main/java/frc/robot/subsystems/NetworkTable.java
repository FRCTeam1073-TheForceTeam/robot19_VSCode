/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class NetworkTable extends Subsystem {

  
  public static NetworkTableInstance inst;
  public DriverStation ds = DriverStation.getInstance();
  public edu.wpi.first.networktables.NetworkTable table;

  public NetworkTable() {
		inst = NetworkTableInstance.getDefault();
    table = inst.getTable("1073Table");
  }

  @Override
  public void initDefaultCommand() {
  }

  public void periodic() {
    table.getEntry("Voltage").setDouble(RobotController.getBatteryVoltage());
    table.getEntry("Time").setDouble(ds.getMatchTime());
    table.getEntry("isBrowned").setBoolean(RobotController.isBrownedOut());
    table.getEntry("robotVelocity").setDouble((Math.sqrt(Math.pow(RobotMap.accelerometer.getX(), 2) + Math.pow(RobotMap.accelerometer.getY(), 2))));
  }

  public void refresh() {
    table = inst.getTable("1073Table");
    if (ds.isAutonomous()) table.getEntry("Period").setString("Auto");
    else table.getEntry("Period").setString("Tele");
  }
}