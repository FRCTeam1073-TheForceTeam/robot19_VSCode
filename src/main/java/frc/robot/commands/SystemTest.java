/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class SystemTest extends Command {
  public SystemTest() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
  }

  private edu.wpi.first.networktables.NetworkTable table = Robot.networktable.table;
  private final WPI_TalonSRX leftMaster = RobotMap.leftMaster;
  private final WPI_TalonSRX rightMaster = RobotMap.rightMaster;
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
      if (stepsLeft == 4) motorTest(leftMaster, "low", 2000);
      if (stepsLeft == 3) motorTest(rightMaster, "low", 2000);
      if (stepsLeft == 2) motorTest(leftMaster, "high", 2000);
      if (stepsLeft == 1) motorTest(rightMaster, "high", 2000);
      if (complete) {
        stepsLeft--;
        complete = false;
        firstTime = true;
      }
    }
    else if (state.equals("motors")) {
      if (stepsLeft == 2) motorTest(leftMaster, "low", 2000);
      if (stepsLeft == 1) motorTest(rightMaster, "low", 2000);
      if (complete) {
        stepsLeft--;
        complete = false;
        firstTime = true;
      }
    }
    else if (state.equals("gearbox")) {
      if (stepsLeft == 4) motorTest(leftMaster, "low", 2000);
      if (stepsLeft == 3) motorTest(rightMaster, "low", 2000);
      if (stepsLeft == 2) motorTest(leftMaster, "high", 2000);
      if (stepsLeft == 1) motorTest(rightMaster, "high", 2000);
      if (complete) {
        stepsLeft--;
        complete = false;
        firstTime = true;
      }
      stepsLeft--;
    }
  }

  private void motorTest(WPI_TalonSRX side, String gear, double duration) {
    if (gear.equals("low")) Robot.pnuematic.setLowGear();
    else if (gear.equals("high")) Robot.pnuematic.setHighGear();
    if (firstTime) {
      speed = 0.01;
      initialPos = side.getSelectedSensorPosition();
      firstTime = false;
      str = "loop stuck";
    }

    side.set(ControlMode.PercentOutput, speed);

    if (speed > .99 && speed < 1) {
      startTime = System.currentTimeMillis();
      table.getEntry("DebugState").setString("Motor Test: " + side.getName() + "\tPower Sent: " + speed + "\tVelocity: " + side.getSelectedSensorVelocity() + "\tStatus: " + str);
    }
    if (speed < 1) {
      if (side.getSelectedSensorPosition() != initialPos) str = "Working";
      else str = "Failed";
      table.getEntry("DebugState").setString("Motor Test: " + side.getName() + "\tGear: " + gear + "\tPower Sent: " + speed + "\tVelocity: " + side.getSelectedSensorVelocity() + "\tStatus: " + str);
      speed += 0.01;
    } 
    if (speed > 1 && System.currentTimeMillis() - startTime > duration) {
      complete = true;
      side.set(ControlMode.PercentOutput, 0);
    }
  }


  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (stepsLeft == 0) return true;
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }
}
