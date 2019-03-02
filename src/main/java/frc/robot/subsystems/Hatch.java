package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Presets;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.hatchCommands.*;

public class Hatch extends Subsystem {
  
	public final WPI_TalonSRX hatchLift = RobotMap.hatchLift;
	public final WPI_TalonSRX hatchCollect = RobotMap.hatchCollect;

	public final DigitalInput limitUp = RobotMap.hatchFlipLimitSwitchUp;
	public final DigitalInput limitDown = RobotMap.hatchFlipLimitSwitchDown;

	public final DigitalInput collectIn = RobotMap.collectorInSensor;
	public final DigitalInput duckIn = RobotMap.duckInSensor;

	private double lift;
	private double collect;
	private final double DIST=100;
	public final double collectorPower=0.5;
	private double P = .7;
	private double I = 0.004;
	private double D = 0;
	private double K = 0;
	private int IZ = 300;
	private double PO = 1;
	private int CLE = 0;

	public Hatch() {
		/* Reset all motors */
		hatchLift.configFactoryDefault();
		hatchCollect.configFactoryDefault();

  	hatchLift.setSafetyEnabled(false);
		hatchCollect.setSafetyEnabled(false);
		/* Set Neutral Mode */
		hatchLift.setNeutralMode(NeutralMode.Brake);
		hatchCollect.setNeutralMode(NeutralMode.Brake);
		
		/* Configure the left Talon's selected sensor to a Quad Encoder*/
		hatchLift.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Presets.timeoutMS);
		
		/* Configure the right Talon's selected sensor to a Quad Encoder*/
		hatchCollect.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Presets.timeoutMS);
		/* Configure output and sensor direction */
		hatchLift.setInverted(false);
		hatchCollect.setInverted(false);

		/* FPID Gains for a lot of things. */
		hatchLift.config_kP(0, P);
		hatchLift.config_kI(0, I);
		hatchLift.config_kD(0, D);
		hatchLift.config_kF(0, K, Presets.timeoutMS);
		//rightMaster.config_IntegralZone(0, IZ, Presets.timeoutMS);
		hatchLift.configClosedLoopPeakOutput(0, PO, Presets.timeoutMS);
		hatchLift.configAllowableClosedloopError(0, CLE, Presets.timeoutMS);

		hatchCollect.config_kP(0, P);
		hatchCollect.config_kI(0, I);
		hatchCollect.config_kD(0, D);
		hatchCollect.config_kF(0, K, Presets.timeoutMS);
		//hatchCollect.config_IntegralZone(0, IZ, Presets.timeoutMS);
		hatchCollect.configClosedLoopPeakOutput(0, PO, Presets.timeoutMS);
		hatchCollect.configAllowableClosedloopError(0, CLE, Presets.timeoutMS);

		/**
		 * Max out the peak output (for all modes). 
		 * However you can limit the output of a given PID object with configClosedLoopPeakOutput().
		 */

		hatchLift.configPeakOutputForward(1.0, Presets.timeoutMS);
		hatchLift.configPeakOutputReverse(-1.0, Presets.timeoutMS);

		hatchCollect.configPeakOutputForward(1.0, Presets.timeoutMS);
		hatchCollect.configPeakOutputReverse(-1.0, Presets.timeoutMS);

	}
    
    @Override
  public void initDefaultCommand() {
    setDefaultCommand(new HatchControls(Presets.deadzone));
	}

  public void periodic() {
		lift = hatchLift.getSelectedSensorPosition();
		collect = hatchCollect.getSelectedSensorPosition();
		// boolean[] state = getLimitSwitchState();
		// double velocity = hatchLift.getSelectedSensorVelocity();
		/*if (Robot.networktable.table.getEntry("changeP").getDouble(P) != P ||
		Robot.networktable.table.getEntry("changeI").getDouble(I) != I ||
		Robot.networktable.table.getEntry("changeD").getDouble(D) != D ||
		Robot.networktable.table.getEntry("changeK").getDouble(K) != K ||
		(int)Robot.networktable.table.getEntry("changeIZ").getDouble(IZ) != IZ ||
		Robot.networktable.table.getEntry("changePO").getDouble(PO) != PO ||
		(int)Robot.networktable.table.getEntry("changeCLE").getDouble(CLE) != CLE) PIDChange();*/
	}

	public void setFlipper(double value) {
		hatchLift.set(ControlMode.PercentOutput, value);
	}

	public void setFlipperPosition(double value) {
		hatchLift.set(ControlMode.Position, value);
	}

	public void setCollector(double value) {
		hatchCollect.set(ControlMode.PercentOutput, value);
	}

	public void setCollectorPosition(double value) {
		hatchCollect.set(ControlMode.Position, value);
	}

	public void setCollectorVelocity(double value) {
		hatchCollect.set(ControlMode.Velocity, value);
	}

	public void collectorIntake() {
		setCollector(collectorPower);
	}

	public void collectorPurge() {
		setCollector(-collectorPower);
	}

	public void collectorZero() {
		setCollectorVelocity(0);
	}
	
	public void setFlipperUp() {
		setFlipper(0);
	}

	public void setFlipperCenter() {
		setFlipper(DIST);
	}

	public void setFlipperDown() {
		setFlipper(DIST * 2);
	}

	public void fingerLower() {
		Robot.pnuematic.fingerLower();
	}

	public void fingerRaise() {
		Robot.pnuematic.fingerRaise();
		//NATHANIEL please fill this out with the correct value for "Fingers"!
	}
	
	public boolean getLimitSwitchState() {
		return false;
		//Can return whatever NATHANIEL would like. NATHANIEL please fill this out with the apropos value
	
	}

	public void hatchExtend() {
		Robot.pnuematic.hatchExtend();
	}

	public void hatchRetract() {
		Robot.pnuematic.hatchRetract();
	}

	public void liftCollect(double lift, double collect) {
		hatchLift.set(ControlMode.PercentOutput, lift);
		hatchCollect.set(ControlMode.PercentOutput, collect);
	}

}