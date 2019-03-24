package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
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
	public static WPI_VictorSPX rightSlaveTwo;
	public static WPI_TalonSRX leftMaster;
	public static WPI_VictorSPX leftSlave;
	public static WPI_VictorSPX leftSlaveTwo;

	public static WPI_TalonSRX leftClimber;
	public static WPI_TalonSRX rightClimber;
	
	public static DigitalInput climberLeftLim;
	public static DigitalInput climberRightLim;

	public static WPI_TalonSRX lidar;

	public static WPI_TalonSRX hatchCollect;
	public static WPI_TalonSRX hatchLift;
	public static WPI_VictorSPX hatchLiftSlave;

	public static Solenoid bucketUp;
	public static Solenoid bucketDown;

	public static Solenoid high;
	public static Solenoid low;

	public static BuiltInAccelerometer accelerometer;

	public static Solenoid hatchRaiser;
	public static Solenoid hatchLowerer;
	public static DigitalInput flipperTopLim;
	public static DigitalInput flipperMidLim;
	public static DigitalInput flipperBotLim;

	public static DigitalInput collectorInSensor;
	public static DigitalInput duckInSensor;

	public static void init() {

		leftMaster = new WPI_TalonSRX(8);
		leftSlave = new WPI_VictorSPX(9);
		leftSlaveTwo = new WPI_VictorSPX(7);

		rightMaster = new WPI_TalonSRX(4);
		rightSlave = new WPI_VictorSPX(2);
		rightSlaveTwo = new WPI_VictorSPX(3);

		headingGyro = new ADXRS450_Gyro();
		accelerometer = new BuiltInAccelerometer();

		lidar = new WPI_TalonSRX(15);

		hatchCollect = new WPI_TalonSRX(5);
		hatchLift = new WPI_TalonSRX(6);
		hatchLiftSlave = new WPI_VictorSPX(12);

		rightClimber = new WPI_TalonSRX(10);
		leftClimber = new WPI_TalonSRX(13);

		bucketUp = new Solenoid(0);
		bucketDown = new Solenoid(1);

		high = new Solenoid(7);
		low = new Solenoid(6);
		hatchRaiser = new Solenoid(5);
		hatchLowerer = new Solenoid(4);

		climberLeftLim = new DigitalInput(4);
		climberRightLim = new DigitalInput(5);
		flipperBotLim = new DigitalInput(6);
		flipperMidLim = new DigitalInput(7);
		flipperTopLim = new DigitalInput(8);
	}
}