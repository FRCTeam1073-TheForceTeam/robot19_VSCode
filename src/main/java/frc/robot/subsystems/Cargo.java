/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Presets;
import frc.robot.RobotMap;
import frc.robot.commands.*;

/**
 * Add your docs here.
 */
public class Cargo extends Subsystem {
  
  public final WPI_TalonSRX cargoCollect = RobotMap.cargoCollect;
	public final WPI_TalonSRX cargoLift = RobotMap.cargoLift;
	public final WPI_VictorSPX cargoLift2 = RobotMap.cargoLift2;
	
	public DigitalInput switchDown = RobotMap.cargoFlipLimitSwitchDown;
	public DigitalInput switchUp = RobotMap.cargoFlipLimitSwitchUp;

  private double lift;
	private double collect;
	private boolean liftTop;
	private boolean liftBottom;

	private double P = .7;
	private double I = 0.004;
	private double D = 0;
	private double K = 0;
	private double PO = 1;
	private int CLE = 0;

  public Cargo(){
    /* Reset all motors */
		cargoLift.configFactoryDefault();
		cargoCollect.configFactoryDefault();

    cargoLift.setSafetyEnabled(false);
		cargoCollect.setSafetyEnabled(false);

		/* Set Neutral Mode */
		cargoLift.setNeutralMode(NeutralMode.Brake);
		cargoCollect.setNeutralMode(NeutralMode.Brake);
		
		/* Configure the lift Talon's selected sensor to a Quad Encoder*/
		cargoLift.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Presets.timeoutMS);
		
		/* Configure the collector Talon's selected sensor to a Quad Encoder*/
		cargoCollect.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Presets.timeoutMS);
		
		/* Configure output and sensor direction */
		cargoLift.setInverted(false);
		cargoCollect.setInverted(false);

		/**
		 * Max out the peak output (for all modes).  
		 * However you can limit the output of a given PID object with configClosedLoopPeakOutput().
		 */
		cargoLift.configPeakOutputForward(1.0, Presets.timeoutMS);
		cargoCollect.configPeakOutputReverse(-1.0, Presets.timeoutMS);

		/* FPID Gains for velocity servo */

		cargoLift.config_kP(0,P);
		cargoLift.config_kI(0,I);
		cargoLift.config_kD(0,D);
		cargoLift.config_kF(0,K, Presets.timeoutMS);
		cargoLift.configClosedLoopPeakOutput(0,PO, Presets.timeoutMS);
		cargoLift.configAllowableClosedloopError(0,CLE, Presets.timeoutMS);
	
		cargoCollect.config_kP(0,P);
		cargoCollect.config_kI(0,I);
		cargoCollect.config_kD(0,D);
		cargoCollect.config_kF(0,K, Presets.timeoutMS);
		cargoCollect.configClosedLoopPeakOutput(0,PO, Presets.timeoutMS);
		cargoCollect.configAllowableClosedloopError(0,CLE, Presets.timeoutMS);
	}
	

  public void periodic(){
    lift = cargoLift.getSelectedSensorPosition();
		collect = cargoCollect.getSelectedSensorPosition();

		liftTop = switchUp.get();
		liftBottom = switchDown.get();
  }

	public boolean getLimitTop(){
		return liftTop;
	}

	public boolean getLimitBottom(){
		return liftBottom;
	}

	public void liftDrive(double speed) {
		cargoLift.set(ControlMode.PercentOutput, speed);
	}

	public void collectorSpin(double speed){
		cargoCollect.set(ControlMode.PercentOutput, speed);
	}

	public void velocity(double left, double right) {
		cargoLift.set(ControlMode.Velocity, left);
		cargoCollect.set(ControlMode.Velocity, right);
	}

	public void distance(double left, double right) {
		cargoLift.set(ControlMode.Position, left);
		cargoCollect.set(ControlMode.Position, right);
	}

	public void zero() {
		cargoLift.set(ControlMode.Velocity, 0);
		cargoCollect.set(ControlMode.Velocity, 0);
	}

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new CargoControls());
  }
}