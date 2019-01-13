/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class NetworkTable extends Subsystem {

  
	public static NetworkTableInstance inst;
  public edu.wpi.first.networktables.NetworkTable table;

  public NetworkTable() {
    
		inst = NetworkTableInstance.getDefault();
    table = inst.getTable("1073Table");
  }

  @Override
  public void initDefaultCommand() {
  }

  public static void init() {
  }

  public void periodic() {
  }

  public void refresh() {
    table = inst.getTable("1073Table");
  }
}
