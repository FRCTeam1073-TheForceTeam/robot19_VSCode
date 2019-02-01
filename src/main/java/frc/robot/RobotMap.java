package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {}
  
  /** If you are using multiple modules, make sure to define both the port
  * number and the module. For example you with a rangefinder:
  * public static int rangefinderPort = 1;
  * public static int rangefinderModule = 1;
  */
  
	public static PowerDistributionPanel pdp;
	public static ADXRS450_Gyro headingGyro;
	public static WPI_TalonSRX rightMaster;
	public static WPI_VictorSPX rightSlave;
	public static WPI_TalonSRX leftMaster;
	public static WPI_VictorSPX leftSlave;
	public static Solenoid high;
	public static Solenoid low;
	public static BuiltInAccelerometer accelerometer;
  
  	public static void init() {
		headingGyro = new ADXRS450_Gyro();
		accelerometer = new BuiltInAccelerometer();

		rightSlave = new WPI_VictorSPX(2);
		rightMaster = new WPI_TalonSRX(3);
		leftSlave = new WPI_VictorSPX(9);
		leftMaster = new WPI_TalonSRX(8);

		high = new Solenoid (1, 7);
		low = new Solenoid (1, 5);
	}
}