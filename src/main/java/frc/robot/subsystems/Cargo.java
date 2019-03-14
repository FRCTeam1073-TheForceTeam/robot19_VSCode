package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Presets;
import frc.robot.RobotMap;
import frc.robot.commands.CargoControls;

/**
 * @author Nathaniel
 */
public class Cargo extends Subsystem {
  
  public final WPI_TalonSRX cargoCollect = RobotMap.cargoCollect;
	public final WPI_TalonSRX cargoLift = RobotMap.cargoLift;
	
	public DigitalInput switchDown = RobotMap.cargoFlipLimitSwitchDown;
	public DigitalInput switchUp = RobotMap.cargoFlipLimitSwitchUp;

	private boolean liftTop;
	private boolean liftBottom;

	/**
 	 * @author Nathaniel
 	 */
  public Cargo() {
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
	}

	public double getEncoder() {
		return cargoLift.getSelectedSensorPosition();
	}

	public boolean getLimitTop() {
		return liftTop;
	}

	public boolean getLimitBottom() {
		return liftBottom;
	}

	public void lift(double val) {
		cargoLift.set(ControlMode.PercentOutput, val);
	}

	public void collector(double val) {
		cargoCollect.set(ControlMode.PercentOutput, val);
	}

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new CargoControls(Presets.deadzone));
  }
}