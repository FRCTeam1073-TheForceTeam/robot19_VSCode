package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Nathaniel
 */
public class NetworkTable extends Subsystem {

  public static NetworkTableInstance inst;
  public DriverStation ds = DriverStation.getInstance();
  public edu.wpi.first.networktables.NetworkTable table;

  /**
   * @author Nathaniel
   */
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
  }

  public void refresh() {
    table = inst.getTable("1073Table");
    if (ds.isAutonomous()) table.getEntry("Period").setString("Auto");
    else table.getEntry("Period").setString("Tele");
  }
}