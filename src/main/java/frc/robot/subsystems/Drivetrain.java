package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Presets;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveControls;

/**
 * @author Nathaniel
 */
public class Drivetrain extends Subsystem {
  
  	public final WPI_TalonSRX rightMaster = RobotMap.rightMaster;
	public final WPI_VictorSPX rightSlave = RobotMap.rightSlave;
	public final WPI_VictorSPX rightSlaveTwo = RobotMap.rightSlaveTwo;
  	public final WPI_TalonSRX leftMaster = RobotMap.leftMaster;
	public final WPI_VictorSPX leftSlave = RobotMap.leftSlave;
	public final WPI_VictorSPX leftSlaveTwo = RobotMap.leftSlaveTwo;
	
	public double leftEncoder;
	public double rightEncoder;
	private double P = .7;
	private double I = 0.004;
	private double D = 0;
	private double K = 0;
	private int IZ = 300;
	private double PO = 1;
	private int CLE = 0;

	public boolean sixSim = true;
  
	/**
 	 * @author Nathaniel
 	 */
	public Drivetrain() {
		/* Reset all motors */
		rightMaster.configFactoryDefault();
		leftMaster.configFactoryDefault();
		rightSlave.configFactoryDefault();
		leftSlave.configFactoryDefault();
		if (sixSim) {
			rightSlaveTwo.configFactoryDefault();
			leftSlaveTwo.configFactoryDefault();
		}

  		rightMaster.setSafetyEnabled(false);
		rightSlave.setSafetyEnabled(false);
		if (sixSim) rightSlaveTwo.setSafetyEnabled(false);
  		leftMaster.setSafetyEnabled(false);
		leftSlave.setSafetyEnabled(false);
		if (sixSim) leftSlaveTwo.setSafetyEnabled(false);

		/* Set Neutral Mode */
		leftMaster.setNeutralMode(NeutralMode.Brake);
		rightMaster.setNeutralMode(NeutralMode.Brake);
		leftSlave.setNeutralMode(NeutralMode.Brake);
		rightSlave.setNeutralMode(NeutralMode.Brake);
		if (sixSim) {
			leftSlaveTwo.setNeutralMode(NeutralMode.Brake);
			rightSlaveTwo.setNeutralMode(NeutralMode.Brake);
		}
		
		/* Configure the left Talon's selected sensor to a Quad Encoder*/
		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Presets.timeoutMS);
		
		/* Configure the right Talon's selected sensor to a Quad Encoder*/
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Presets.timeoutMS);
		
		/* Configure output and sensor direction */
		leftMaster.setInverted(false);
		rightMaster.setInverted(true);
		leftSlave.setInverted(false);
		rightSlave.setInverted(true);
		if (sixSim) {
			leftSlaveTwo.setInverted(false);
			rightSlaveTwo.setInverted(true);
		}

		/* 
		 * Max out the peak output (for all modes). 
		 * However you can limit the output of a given PID object with configClosedLoopPeakOutput().
		 */
		leftMaster.configPeakOutputForward(1.0, Presets.timeoutMS);
		leftMaster.configPeakOutputReverse(-1.0, Presets.timeoutMS);
		rightMaster.configPeakOutputForward(1.0, Presets.timeoutMS);
		rightMaster.configPeakOutputReverse(-1.0, Presets.timeoutMS);

		/* FPID Gains for velocity servo */
		rightMaster.config_kP(0, P);
		rightMaster.config_kI(0, I);
		rightMaster.config_kD(0, D);
		rightMaster.config_kF(0, K, Presets.timeoutMS);
		//rightMaster.config_IntegralZone(0, IZ, Presets.timeoutMS);
		rightMaster.configClosedLoopPeakOutput(0, PO, Presets.timeoutMS);
		rightMaster.configAllowableClosedloopError(0, CLE, Presets.timeoutMS);

		leftMaster.config_kP(0, P);
		leftMaster.config_kI(0, I);
		leftMaster.config_kD(0, D);
		leftMaster.config_kF(0, K, Presets.timeoutMS);
		//leftMaster.config_IntegralZone(0, IZ, Presets.timeoutMS);
		leftMaster.configClosedLoopPeakOutput(0, PO, Presets.timeoutMS);
		leftMaster.configAllowableClosedloopError(0, CLE, Presets.timeoutMS);

		rightSlave.config_kP(0, P);
		rightSlave.config_kI(0, I);
		rightSlave.config_kD(0, D);
		rightSlave.config_kF(0, K, Presets.timeoutMS);
		//rightSlave.config_IntegralZone(0, IZ, Presets.timeoutMS);
		rightSlave.configClosedLoopPeakOutput(0, PO, Presets.timeoutMS);
		rightSlave.configAllowableClosedloopError(0, CLE, Presets.timeoutMS);

		leftSlave.config_kP(0, P);
		leftSlave.config_kI(0, I);
		leftSlave.config_kD(0, D);
		leftSlave.config_kF(0, K, Presets.timeoutMS);
		//leftSlave.config_IntegralZone(0, IZ, Presets.timeoutMS);
		leftSlave.configClosedLoopPeakOutput(0, PO, Presets.timeoutMS);
		leftSlave.configAllowableClosedloopError(0, CLE, Presets.timeoutMS);

		if (sixSim) {
			rightSlaveTwo.config_kP(0, P);
			rightSlaveTwo.config_kI(0, I);
			rightSlaveTwo.config_kD(0, D);
			rightSlaveTwo.config_kF(0, K, Presets.timeoutMS);
			//rightSlaveTwo.config_IntegralZone(0, IZ, Presets.timeoutMS);
			rightSlaveTwo.configClosedLoopPeakOutput(0, PO, Presets.timeoutMS);
			rightSlaveTwo.configAllowableClosedloopError(0, CLE, Presets.timeoutMS);

			leftSlaveTwo.config_kP(0, P);
			leftSlaveTwo.config_kI(0, I);
			leftSlaveTwo.config_kD(0, D);
			leftSlaveTwo.config_kF(0, K, Presets.timeoutMS);
			//leftSlaveTwo.config_IntegralZone(0, IZ, Presets.timeoutMS);
			leftSlaveTwo.configClosedLoopPeakOutput(0, PO, Presets.timeoutMS);
			leftSlaveTwo.configAllowableClosedloopError(0, CLE, Presets.timeoutMS);
		}

		rightSlave.follow(rightMaster);
		leftSlave.follow(leftMaster);

		if (sixSim) {
			rightSlaveTwo.follow(rightMaster);
			leftSlaveTwo.follow(leftMaster);
		}

		/* Table Data Setup */
		Robot.networktable.table.getEntry("changeP").setDouble(P);
		Robot.networktable.table.getEntry("changeI").setDouble(I);
		Robot.networktable.table.getEntry("changeD").setDouble(D);
		Robot.networktable.table.getEntry("changeK").setDouble(K);
		Robot.networktable.table.getEntry("changeIZ").setDouble(IZ);
		Robot.networktable.table.getEntry("changeD").setDouble(PO);
		Robot.networktable.table.getEntry("changeD").setDouble(CLE);
		Robot.networktable.table.getEntry("PIDReadout").setString("P: " + P + "\tI: " + I + "\tD: " + D + "\tK: " + K + "\tIZ: " + IZ + "\tPO: " + PO + "\tCLE: " + CLE);
	}
  
  @Override
  public void initDefaultCommand() {
  	setDefaultCommand(new DriveControls(Presets.deadzone));
	}

  public void periodic() {
		leftEncoder = leftMaster.getSelectedSensorPosition();
		rightEncoder = rightMaster.getSelectedSensorPosition();
		Robot.networktable.table.getEntry("Gyro").setString("Gyro: " + RobotMap.headingGyro.getAngle());
		Robot.networktable.table.getEntry("RawGyroValue").setDouble(RobotMap.headingGyro.getAngle());
		Robot.networktable.table.getEntry("RawAccel").setDoubleArray(new double[]{RobotMap.accelerometer.getX(),RobotMap.accelerometer.getY(),RobotMap.accelerometer.getZ()});

		/*if (Robot.networktable.table.getEntry("changeP").getDouble(P) != P ||
		Robot.networktable.table.getEntry("changeI").getDouble(I) != I ||
		Robot.networktable.table.getEntry("changeD").getDouble(D) != D ||
		Robot.networktable.table.getEntry("changeK").getDouble(K) != K ||
		(int)Robot.networktable.table.getEntry("changeIZ").getDouble(IZ) != IZ ||
		Robot.networktable.table.getEntry("changePO").getDouble(PO) != PO ||
		(int)Robot.networktable.table.getEntry("changeCLE").getDouble(CLE) != CLE) PIDChange();*/
	}

	public void PIDChange() {
		P = Robot.networktable.table.getEntry("changeP").getDouble(P);
		I = Robot.networktable.table.getEntry("changeI").getDouble(I);
		D = Robot.networktable.table.getEntry("changeD").getDouble(D);
		K = Robot.networktable.table.getEntry("changeK").getDouble(K);
		IZ = (int)Robot.networktable.table.getEntry("changeIZ").getDouble(IZ);
		PO = Robot.networktable.table.getEntry("changePO").getDouble(PO);
		CLE = (int)Robot.networktable.table.getEntry("changeCLE").getDouble(CLE);
		Robot.networktable.table.getEntry("PIDReadout").setString("P: " + P + "\tI: " + I + "\tD: " + D + "\tK: " + K + "\tIZ: " + IZ + "\tPO: " + PO + "\tCLE: " + CLE);
	

		rightMaster.config_kP(0, P);
		rightMaster.config_kI(0, I);
		rightMaster.config_kD(0, D);
		rightMaster.config_kF(0, K, Presets.timeoutMS);
		//rightMaster.config_IntegralZone(0, IZ, Presets.timeoutMS);
		rightMaster.configClosedLoopPeakOutput(0, PO, Presets.timeoutMS);
		rightMaster.configAllowableClosedloopError(0, CLE, Presets.timeoutMS);

		leftMaster.config_kP(0, P);
		leftMaster.config_kI(0, I);
		leftMaster.config_kD(0, D);
		leftMaster.config_kF(0, K, Presets.timeoutMS);
		//leftMaster.config_IntegralZone(0, IZ, Presets.timeoutMS);
		leftMaster.configClosedLoopPeakOutput(0, PO, Presets.timeoutMS);
		leftMaster.configAllowableClosedloopError(0, CLE, Presets.timeoutMS);

		rightSlave.config_kP(0, P);
		rightSlave.config_kI(0, I);
		rightSlave.config_kD(0, D);
		rightSlave.config_kF(0, K, Presets.timeoutMS);
		//rightSlave.config_IntegralZone(0, IZ, Presets.timeoutMS);
		rightSlave.configClosedLoopPeakOutput(0, PO, Presets.timeoutMS);
		rightSlave.configAllowableClosedloopError(0, CLE, Presets.timeoutMS);

		leftSlave.config_kP(0, P);
		leftSlave.config_kI(0, I);
		leftSlave.config_kD(0, D);
		leftSlave.config_kF(0, K, Presets.timeoutMS);
		//leftSlave.config_IntegralZone(0, IZ, Presets.timeoutMS);
		leftSlave.configClosedLoopPeakOutput(0, PO, Presets.timeoutMS);
		leftSlave.configAllowableClosedloopError(0, CLE, Presets.timeoutMS);

		if (sixSim) {
			rightSlaveTwo.config_kP(0, P);
			rightSlaveTwo.config_kI(0, I);
			rightSlaveTwo.config_kD(0, D);
			rightSlaveTwo.config_kF(0, K, Presets.timeoutMS);
			//rightSlaveTwo.config_IntegralZone(0, IZ, Presets.timeoutMS);
			rightSlaveTwo.configClosedLoopPeakOutput(0, PO, Presets.timeoutMS);
			rightSlaveTwo.configAllowableClosedloopError(0, CLE, Presets.timeoutMS);

			leftSlaveTwo.config_kP(0, P);
			leftSlaveTwo.config_kI(0, I);
			leftSlaveTwo.config_kD(0, D);
			leftSlaveTwo.config_kF(0, K, Presets.timeoutMS);
			//leftSlaveTwo.config_IntegralZone(0, IZ, Presets.timeoutMS);
			leftSlaveTwo.configClosedLoopPeakOutput(0, PO, Presets.timeoutMS);
			leftSlaveTwo.configAllowableClosedloopError(0, CLE, Presets.timeoutMS);
		}
	}

	public void tank(double left, double right) {
		leftMaster.set(ControlMode.PercentOutput, left);
		rightMaster.set(ControlMode.PercentOutput, right);
	}

	public void tank(double left, double right, double threshhold) {
		if (Math.abs(leftMaster.getSelectedSensorVelocity()) < threshhold && Math.abs(left) > .75) left = Math.copySign(left * (((Math.abs(leftMaster.getSelectedSensorVelocity()) / threshhold) + .75) / 2), left);
		if (Math.abs(rightMaster.getSelectedSensorVelocity()) < threshhold && Math.abs(right) > .75) right = Math.copySign(right * (((Math.abs(rightMaster.getSelectedSensorVelocity()) / threshhold) + .75) / 2), right);
		tank(left, right);
	}

	public void velocity(double left, double right) {
		leftMaster.set(ControlMode.Velocity, left);
		rightMaster.set(ControlMode.Velocity, right);
	}

	public void distance(double left, double right) {
		leftMaster.set(ControlMode.Position, left);
		rightMaster.set(ControlMode.Position, right);
	}

	public void zero() {
		leftMaster.set(ControlMode.Velocity, 0);
		rightMaster.set(ControlMode.Velocity, 0);
	}
}