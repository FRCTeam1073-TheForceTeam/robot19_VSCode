/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.*;

/**
 * Add your docs here.
 */
public class Cargo extends Subsystem {
  
  public final WPI_TalonSRX cargoCollect = RobotMap.cargoCollect;
	public final WPI_TalonSRX cargoLift = RobotMap.cargoLift;
  public final WPI_VictorSPX cargoLift2 = RobotMap.cargoLift2;


  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new CargoControls());
  }
}