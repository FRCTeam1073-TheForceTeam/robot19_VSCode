package frc.robot;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Feedback;
import frc.robot.subsystems.GearBox;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Lidar;
import frc.robot.subsystems.NetworkTable;
import frc.robot.subsystems.Pnuematic;
import frc.robot.subsystems.Vision;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
	public double initialBootTime, teleopStartTime, autoStartTime;
	public static OI oi;
	public static NetworkTable networktable;
	public static Drivetrain drivetrain;
	public static Pnuematic pnuematic;
  	public static Feedback feedback;
	public static GearBox gearbox;
	public static Climber climber;
	public static Vision vision;
	public static Lidar lidar;
	public static Hatch hatch;
	public static Bling bling;
	public static String FMS;
	public static SendableChooser<AutoObject> autonomousPosition, autonomousMatchType, debugChooser;
	public AutoObject left, center, right, other, quals, elims, experimental, debugAll, debugMotors, debugGearbox, debugBling;
	public static boolean notClear;
	public static boolean debugMode, autoBox;
	public static Command debugRunner;
	public Command autonomousCommand;
	public static boolean canceled;

	protected Robot() {
		super(0.03); //cycle time
	}

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
		debugPrint("Robot Initializing");

		RobotMap.init();
		
		debugMode = false;
		autoBox = false;
		notClear = false;
		canceled = false;
		RobotMap.headingGyro.reset();
		RobotMap.headingGyro.calibrate();
    
		networktable = new NetworkTable();

		drivetrain = new Drivetrain();

		pnuematic = new Pnuematic();

		//feedback = new Feedback();

		gearbox = new GearBox();

		climber = new Climber();

		vision = new Vision();

		lidar = new Lidar();
			
		hatch = new Hatch();

		//bling = new Bling();

		oi = new OI();
		
		boolean threadLock = true;

		new Thread(() -> {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
			camera.setResolution(640, 480);

			UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
			camera2.setResolution(640, 480);
			
			while(threadLock) {}
		}).start();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
}
  
  /**
   * This function is called when the disabled button is hit.
   * You can use it to reset subsystems before shutting down.
   */
	public void disabledInit() {
		debugPrint("At " + ((System.currentTimeMillis() - initialBootTime) * 1000) + ", robot19.robot says \n" 
			+ "\" WE ARE DISABLED WHAT THE HECK?\"");
		
		debugPrint(RobotMap.headingGyro.getAngle());
		
		networktable.refresh();
		
		Robot.oi.driverControl.rumbleTimeRep(1, 250, 2);
		Robot.oi.driverControl.rumbleTimeRep(.2, 250, 2);
		Robot.oi.driverControl.rumbleTimeRep(1, 250, 2);
		Robot.oi.driverControl.rumbleTimeRep(.2, 250, 2);
	}

	public void disabledPeriodic() {
		networktable.refresh();
	}

	public void autonomousInit() {
		networktable.table.getEntry("DebugMode").setBoolean(false);
		debugMode = false;

		debugPrint("Auto Setting Up");
		RobotMap.headingGyro.reset();
		autoStartTime = System.currentTimeMillis();
		
		networktable.refresh();

		Scheduler.getInstance().run();
		
		debugPrint("Auto Starting");
	}

	/** This function is called periodically during autonomous */
	public void autonomousPeriodic() {
		//lidar.refresh();
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		teleopStartTime = System.currentTimeMillis();
		
		networktable.refresh();

		if (networktable.table.getEntry("DebugMode").getBoolean(false)) {
			debugMode = true;
			debugRunner.start();
		}
		else debugMode = false;

		Scheduler.getInstance().run();
	}
	
	/** This function is called periodically during operator control */
	public void teleopPeriodic() {
		//lidar.refresh();
		Scheduler.getInstance().run();
	}

	/** This function is called periodically during test mode */
	public void testPeriodic() {
		debugPrint("Test Mode.");

		Scheduler.getInstance().run();
	}

	/** Enables or disables logging */
	private static boolean logging = false;

	/** Used instead of Console Prints */
	public static void debugPrint(String str) {
		if (logging) System.out.println(str);
	}
	/** Used instead of Console Prints */
	public static void debugPrint(Object obj) {
		if (logging) System.out.println(obj);
	}
	/** Used instead of Console Prints */
	public static void debugPrint(int num) {
		if (logging) System.out.println(num);
	}
	/** Used instead of Console Prints */
	public static void debugPrint(double num) {
		if (logging) System.out.println(num);
	}
	/** Used instead of Console Prints */
	public static void debugPrint(float num) {
		if (logging) System.out.println(num);
	}
	/** Used instead of Console Prints */
	public static void debugPrint(boolean bool) {
		if (logging) System.out.println(bool);
	}
}