package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * @author Nathaniel
 */
public class NetworkTable extends Subsystem {

  public static NetworkTableInstance inst;
  public DriverStation ds = DriverStation.getInstance();
  public edu.wpi.first.networktables.NetworkTable table, visionTable;

  /**
   * @author Nathaniel
   */
  public NetworkTable() {
		inst = NetworkTableInstance.getDefault();
    table = inst.getTable("1073Table");
    visionTable = inst.getTable("CameraFeedback");
  }

  @Override
  public void initDefaultCommand() {
  }

  private String gyroCheck() {
    double val = (RobotMap.headingGyro.getAngle() % 360) - 0;
    if (val > 180) return "-" + (360 - val);
    return "+" + val;
  }

  public void periodic() {
    table.getEntry("Voltage").setDouble(RobotController.getBatteryVoltage());
    table.getEntry("Time").setDouble(ds.getMatchTime());
    table.getEntry("isBrowned").setBoolean(RobotController.isBrownedOut());
    table.getEntry("Gyro").setString(gyroCheck());
  }

  public void refresh() {
    table = inst.getTable("1073Table");
    visionTable = inst.getTable("CameraFeedback");
    if (ds.isAutonomous()) table.getEntry("Period").setString("Auto");
    else table.getEntry("Period").setString("Tele");
  }
}