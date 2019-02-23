package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  
  /** If you are using multiple modules, make sure to define both the port
  * number and the module. For example you with a rangefinder:
  * public static int rangefinderPort = 1;
  * public static int rangefinderModule = 1;
  */
  
	public static ADXRS450_Gyro headingGyro;
	public static WPI_TalonSRX rightMaster;
	public static WPI_VictorSPX rightSlave;
	public static WPI_VictorSPX rightSlave2;
	public static WPI_TalonSRX leftMaster;
	public static WPI_VictorSPX leftSlave;
	public static WPI_VictorSPX leftSlave2;

	public static WPI_VictorSPX leftClimber;
	public static WPI_TalonSRX rightClimber;

	public static WPI_TalonSRX lidar;

	public static WPI_TalonSRX hatchCollect;
	public static WPI_TalonSRX hatchLift;

	public static WPI_TalonSRX cargoCollect;
	public static WPI_TalonSRX cargoLift;
	public static WPI_VictorSPX cargoLift2;

	public static Solenoid high;
	public static Solenoid low;

	public static BuiltInAccelerometer accelerometer;

	public static Solenoid finger;
	public static Solenoid hatchExtender;
	public static WPI_TalonSRX hatchIntake;
	public static WPI_TalonSRX hatchFlipper;
	public static DigitalInput hatchFlipLimitSwitchUp;
	public static DigitalInput hatchFlipLimitSwitchDown;
	public static DigitalInput cargoFlipLimitSwitchUp;
	public static DigitalInput cargoFlipLimitSwitchDown;

  	public static void init() {
		headingGyro = new ADXRS450_Gyro();
		accelerometer = new BuiltInAccelerometer();

		rightSlave2 = new WPI_VictorSPX(3);
		rightSlave = new WPI_VictorSPX(2);
		rightMaster = new WPI_TalonSRX(4);
		leftSlave2 = new WPI_VictorSPX(7);
		leftSlave = new WPI_VictorSPX(9);
		leftMaster = new WPI_TalonSRX(8);

		rightMaster = new WPI_TalonSRX(4);
		rightSlave = new WPI_VictorSPX(2);

		headingGyro = new ADXRS450_Gyro();
		accelerometer = new BuiltInAccelerometer();

		lidar = new WPI_TalonSRX(15);

		hatchCollect = new WPI_TalonSRX(5);
		hatchLift = new WPI_TalonSRX(6);

		rightClimber = new WPI_TalonSRX(10);
		leftClimber = new WPI_VictorSPX(11);

		cargoLift2 = new WPI_VictorSPX(12);
		cargoLift = new WPI_TalonSRX(13);
		cargoCollect = new WPI_TalonSRX(14);

		high = new Solenoid (1, 7);
		low = new Solenoid (1, 5);
		// finger = new Solenoid(2, 7);
		// hatchExtender = new Solenoid(2, 5);

		/**HERE'S WHERE THE LIMIT SWITCHES WILL GO
		hatchFlipLimitSwitchUp = new DigitalInput(WHATEVER THE ID IS);
		hatchFlipLimitSwitchDown = new DigitalInput(WHATEVER THE ID IS);
		cargoFlipLimitSwitchUp = new DigitalInput(WHATEVER THE ID IS);
		cargoFlipLimitSwitchDown = new DigitaInput(WHATEVER THE ID IS);
		*/
	}
}