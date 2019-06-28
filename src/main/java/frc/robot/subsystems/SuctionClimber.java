/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.Presets;
import frc.robot.RobotMap;
import frc.robot.commands.SuctionControls;

/**
 * @author Jack
 */
public class SuctionClimber extends Subsystem {
    public final WPI_TalonSRX suctionArm = RobotMap.suctionArm;
    public final WPI_TalonSRX suction = RobotMap.suction;

    public SuctionClimber() {
        /* Reset all motors */
        suctionArm.configFactoryDefault();
        suction.configFactoryDefault();

        suctionArm.setSafetyEnabled(false);
		    suction.setSafetyEnabled(false);

        /* Set Neutral Mode */
        suctionArm.setNeutralMode(NeutralMode.Brake);
        suction.setNeutralMode(NeutralMode.Brake);

        suctionArm.setInverted(false);
		    suction.setInverted(false);
        }

    public void arm(double val) {
      suctionArm.set(ControlMode.PercentOutput, val);
    }

    public void latch() {
      suction.set(ControlMode.PercentOutput, 1);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new SuctionControls(Presets.deadzone));
    }
}
