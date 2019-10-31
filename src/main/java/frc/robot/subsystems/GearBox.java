package frc.robot.subsystems;

import java.text.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

/**
 * @author Nathaniel
 */
public class GearBox extends Subsystem {
  
  public double leftVelocity, rightVelocity, speedL, speedR;
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
    if (Robot.autoBox) shiftCheck();
  }

  public void shiftCheck() {
    if (speedL >= 400 || speedR >= 400) {
      pnuematic.setHighGear();
    } else {
       pnuematic.setLowGear();
    }
  }

  public void update() {
    leftVelocity = Robot.drivetrain.leftMaster.getSelectedSensorVelocity();
    rightVelocity = Robot.drivetrain.rightMaster.getSelectedSensorVelocity();
    speedL = (Math.abs(leftVelocity));
    speedR = (Math.abs(rightVelocity));
    if (pnuematic.isHighGear()) gear = "high";
    else if (pnuematic.isLowGear()) gear = "low";
    Robot.networktable.table.getEntry("GearBoxReadout").setString("\tLeft velocity: " + dec.format(leftVelocity) + "\tRight velocity: " + dec.format(rightVelocity) + "\tSpeed: " + dec.format(((speedL + speedR) / 2) / 141.6) + "\tGear: " + gear);
  }
}