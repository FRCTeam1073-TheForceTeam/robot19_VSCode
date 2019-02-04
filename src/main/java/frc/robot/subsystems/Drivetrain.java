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

public class Drivetrain extends Subsystem {
    
    public final WPI_TalonSRX rightMaster = RobotMap.rightMaster;
    public final WPI_VictorSPX rightSlave = RobotMap.rightSlave;
    public final WPI_TalonSRX leftMaster = RobotMap.leftMaster;
	public final WPI_VictorSPX leftSlave = RobotMap.leftSlave;
	
	public double leftEncoder;
	public double rightEncoder;
	private double P = .7;
	private double I = 0.005;
	private double D = 0;
	private double K = 0;
	private int IZ = 300;
	private double PO = 1;
	private int CLE = 0;
    
	public Drivetrain() {
		/* Reset all motors */
		rightMaster.configFactoryDefault();
		leftMaster.configFactoryDefault();
		rightSlave.configFactoryDefault();
		leftSlave.configFactoryDefault();

    	rightMaster.setSafetyEnabled(false);
    	rightSlave.setSafetyEnabled(false);
    	leftMaster.setSafetyEnabled(false);
		leftSlave.setSafetyEnabled(false);

		/* Set Neutral Mode */
		leftMaster.setNeutralMode(NeutralMode.Brake);
		rightMaster.setNeutralMode(NeutralMode.Brake);
		leftSlave.setNeutralMode(NeutralMode.Brake);
		rightSlave.setNeutralMode(NeutralMode.Brake);
		
		/* Configure the left Talon's selected sensor to a Quad Encoder*/
		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Presets.kTimeoutMs);
		
		/* Configure Sum [Sum of both QuadEncoders] to be used for Primary PID Index */
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Presets.kTimeoutMs);
		
		/* Configure output and sensor direction */
		leftMaster.setInverted(true);
		rightMaster.setInverted(false);

		leftSlave.setInverted(true);
		rightSlave.setInverted(false);
		/**
		 * Max out the peak output (for all modes).  
		 * However you can limit the output of a given PID object with configClosedLoopPeakOutput().
		 */
		leftMaster.configPeakOutputForward(1.0, Presets.kTimeoutMs);
		leftMaster.configPeakOutputReverse(-1.0, Presets.kTimeoutMs);
		rightMaster.configPeakOutputForward(1.0, Presets.kTimeoutMs);
		rightMaster.configPeakOutputReverse(-1.0, Presets.kTimeoutMs);

		/* FPID Gains for velocity servo */
		rightMaster.config_kP(0,P);
		rightMaster.config_kI(0,I);
		rightMaster.config_kD(0,D);
		rightMaster.config_kF(0,K, Presets.kTimeoutMs);
		//rightMaster.config_IntegralZone(0,IZ, Presets.kTimeoutMs);
		rightMaster.configClosedLoopPeakOutput(0,PO, Presets.kTimeoutMs);
		rightMaster.configAllowableClosedloopError(0,CLE, Presets.kTimeoutMs);

		leftMaster.config_kP(0,P);
		leftMaster.config_kI(0,I);
		leftMaster.config_kD(0,D);
		leftMaster.config_kF(0,K, Presets.kTimeoutMs);
		//leftMaster.config_IntegralZone(0,IZ, Presets.kTimeoutMs);
		leftMaster.configClosedLoopPeakOutput(0,PO, Presets.kTimeoutMs);
		leftMaster.configAllowableClosedloopError(0,CLE, Presets.kTimeoutMs);

		rightSlave.config_kP(0,P);
		rightSlave.config_kI(0,I);
		rightSlave.config_kD(0,D);
		rightSlave.config_kF(0,K, Presets.kTimeoutMs);
		//rightSlave.config_IntegralZone(0,IZ, Presets.kTimeoutMs);
		rightSlave.configClosedLoopPeakOutput(0,PO, Presets.kTimeoutMs);
		rightSlave.configAllowableClosedloopError(0,CLE, Presets.kTimeoutMs);

		leftSlave.config_kP(0,P);
		leftSlave.config_kI(0,I);
		leftSlave.config_kD(0,D);
		leftSlave.config_kF(0,K, Presets.kTimeoutMs);
		//leftSlave.config_IntegralZone(0,IZ, Presets.kTimeoutMs);
		leftSlave.configClosedLoopPeakOutput(0,PO, Presets.kTimeoutMs);
		leftSlave.configAllowableClosedloopError(0,CLE, Presets.kTimeoutMs);

		rightSlave.follow(rightMaster);
		leftSlave.follow(leftMaster);

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

		/*if (Robot.networktable.table.getEntry("changeP").getDouble(P)  != P ||
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
	

		rightMaster.config_kP(0,P);
		rightMaster.config_kI(0,I);
		rightMaster.config_kD(0,D);
		rightMaster.config_kF(0,K, Presets.kTimeoutMs);
		//rightMaster.config_IntegralZone(0,IZ, Presets.kTimeoutMs);
		rightMaster.configClosedLoopPeakOutput(0,PO, Presets.kTimeoutMs);
		rightMaster.configAllowableClosedloopError(0,CLE, Presets.kTimeoutMs);

		leftMaster.config_kP(0,P);
		leftMaster.config_kI(0,I);
		leftMaster.config_kD(0,D);
		leftMaster.config_kF(0,K, Presets.kTimeoutMs);
		//leftMaster.config_IntegralZone(0,IZ, Presets.kTimeoutMs);
		leftMaster.configClosedLoopPeakOutput(0,PO, Presets.kTimeoutMs);
		leftMaster.configAllowableClosedloopError(0,CLE, Presets.kTimeoutMs);

		rightSlave.config_kP(0,P);
		rightSlave.config_kI(0,I);
		rightSlave.config_kD(0,D);
		rightSlave.config_kF(0,K, Presets.kTimeoutMs);
		//rightSlave.config_IntegralZone(0,IZ, Presets.kTimeoutMs);
		rightSlave.configClosedLoopPeakOutput(0,PO, Presets.kTimeoutMs);
		rightSlave.configAllowableClosedloopError(0,CLE, Presets.kTimeoutMs);

		leftSlave.config_kP(0,P);
		leftSlave.config_kI(0,I);
		leftSlave.config_kD(0,D);
		leftSlave.config_kF(0,K, Presets.kTimeoutMs);
		//leftSlave.config_IntegralZone(0,IZ, Presets.kTimeoutMs);
		leftSlave.configClosedLoopPeakOutput(0,PO, Presets.kTimeoutMs);
		leftSlave.configAllowableClosedloopError(0,CLE, Presets.kTimeoutMs);
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