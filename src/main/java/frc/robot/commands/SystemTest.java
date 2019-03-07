package frc.robot.commands;

import java.text.DecimalFormat;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SystemTest extends Command {
  public SystemTest() {
    requires(Robot.drivetrain);
  }

  private edu.wpi.first.networktables.NetworkTable table = Robot.networktable.table;
  private final WPI_TalonSRX leftMaster = RobotMap.leftMaster;
  private final WPI_TalonSRX rightMaster = RobotMap.rightMaster;
  private DecimalFormat dec = new DecimalFormat("#.####");
  private String state, str;
  private int stepsLeft;
  private double speed;
  private double initialPos;
  private boolean complete;
  private double startTime;
  private boolean firstTime;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    table.getEntry("DebugState" + leftMaster.getName() + "Low").setString("");
    table.getEntry("DebugState" + rightMaster.getName() + "Low").setString("");
    table.getEntry("DebugState" + leftMaster.getName() + "High").setString("");
    table.getEntry("DebugState" + rightMaster.getName() + "High").setString("");
    state = Robot.debugChooser.getSelected().getString();

    firstTime = true;
    complete = false;

    if (state.equals("all")) {
      stepsLeft = 4;
    }
    else if (state.equals("motors")) {
      stepsLeft = 2;
    }
    else if (state.equals("gearbox")) {
      stepsLeft = 4;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (state.equals("all")) {
      if (stepsLeft == 4) motorTest(leftMaster, "Low", 1000);
      if (stepsLeft == 3) motorTest(rightMaster, "Low", 1000);
      if (stepsLeft == 2) motorTest(leftMaster, "High", 1000);
      if (stepsLeft == 1) motorTest(rightMaster, "High", 1000);
      if (complete) {
        stepsLeft--;
        complete = false;
        firstTime = true;
      }
    }
    else if (state.equals("motors")) {
      if (stepsLeft == 2) motorTest(leftMaster, "Low", 2000);
      if (stepsLeft == 1) motorTest(rightMaster, "Low", 2000);
      if (complete) {
        stepsLeft--;
        complete = false;
        firstTime = true;
      }
    }
    else if (state.equals("gearbox")) {
      if (stepsLeft == 4) motorTest(leftMaster, "Low", 2000);
      if (stepsLeft == 3) motorTest(rightMaster, "Low", 2000);
      if (stepsLeft == 2) motorTest(leftMaster, "High", 2000);
      if (stepsLeft == 1) motorTest(rightMaster, "High", 2000);
      if (complete) {
        stepsLeft--;
        complete = false;
        firstTime = true;
      }
      stepsLeft--;
    }
  }

  private void motorTest(WPI_TalonSRX side, String gear, double duration) {
    if (gear.equals("Low")) Robot.pnuematic.setLowGear();
    else if (gear.equals("High")) Robot.pnuematic.setHighGear();
    if (firstTime) {
      speed = 0.01;
      initialPos = side.getSelectedSensorPosition();
      firstTime = false;
      str = "loop stuck";
    }

    if (side.getSelectedSensorPosition() != initialPos) str = "Working";
    else str = "Failed";

    table.getEntry("DebugState" + side.getName() + gear).setString("Motor Test: " + side.getName() + "\tGear: " + gear + "\tPower Sent: " + dec.format(speed) + "\tVelocity: " + side.getSelectedSensorVelocity() + "\tStatus: " + str);
    
    side.set(ControlMode.PercentOutput, speed);

    if (speed > .99 && speed < 1) {
      startTime = System.currentTimeMillis();
    }
    if (speed < 1) {
      speed += 0.005;
    } 
    if (speed > 1 && System.currentTimeMillis() - startTime > duration) {
      complete = true;
      side.set(ControlMode.PercentOutput, 0);
    }
  }


  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (stepsLeft == 0) {
      table.getEntry("DebugMode").setBoolean(false);
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }
}
