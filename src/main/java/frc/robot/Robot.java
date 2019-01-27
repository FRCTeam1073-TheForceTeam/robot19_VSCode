/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutonomousTools.AutoTest;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GearBox;
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
	public static GearBox gearbox;
	public static Vision vision;
	public static Lidar lidar;
	public static String FMS;
	public static SendableChooser<AutoObject> autonomousPosition, autonomousMatchType, debugChooser;
	public AutoObject left, center, right, other, quals, elims, experimental, debugAll, debugMotors, debugGearbox, debugBling;
	public static boolean clawBool, EncoderBool, EncoderBoolSet, notClear;
	public static boolean selectedCamera;
	public static boolean debugMode;
  	Command autonomousCommand;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    	System.out.println("Robot Initializing");
		
		RobotMap.init();
		
		debugMode = false;

		RobotMap.leftMotor1.configFactoryDefault();
		RobotMap.rightMotor1.configFactoryDefault();

		RobotMap.headingGyro.reset();
		RobotMap.headingGyro.calibrate();

		networktable = new NetworkTable();
		
		drivetrain = new Drivetrain();

		pnuematic = new Pnuematic();

		gearbox = new GearBox();

		vision = new Vision();

		lidar = new Lidar();

		oi = new OI();

		FMS = "";

		/* Position Objects */
		left = new AutoObject(1);
		center = new AutoObject(2);
		right = new AutoObject(3);
		other = new AutoObject(4);
		quals = new AutoObject(5);
		elims = new AutoObject(6);
		experimental = new AutoObject(7);
		debugAll = new AutoObject(59);
		debugMotors = new AutoObject(60);
		debugGearbox = new AutoObject(61);
		debugBling = new AutoObject(62);

		
		/* The Position Chooser */
		autonomousPosition = new SendableChooser<AutoObject>();
		autonomousPosition.setDefaultOption("None", other);
		autonomousPosition.addOption("Left", left);
		autonomousPosition.addOption("Center", center);
		autonomousPosition.addOption("Right", right);
		SmartDashboard.putData("Position", autonomousPosition);

		/* The MatchType Chooser */
		autonomousMatchType = new SendableChooser<AutoObject>();
		autonomousMatchType.setDefaultOption("None", other);
		autonomousMatchType.addOption("Qualifications", quals);
		autonomousMatchType.addOption("Eliminations", elims);
		autonomousMatchType.addOption("Experimental", experimental);
		SmartDashboard.putData("Match Type", autonomousMatchType);

		/* The Debug Chooser */
		debugChooser = new SendableChooser<AutoObject>();
		debugChooser.setDefaultOption("All", debugAll);
		debugChooser.addOption("Motors", debugMotors);
		debugChooser.addOption("Gearbox", debugGearbox);
		debugChooser.addOption("Bling", debugBling);
		SmartDashboard.putData("Debug", debugChooser);
		
		autonomousCommand = new AutoTest();
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
	lidar.refresh();
	vision.refresh();
  }
  
  /**
   * This function is called when the disabled button is hit.
   * You can use it to reset subsystems before shutting down.
   */
	public void disabledInit() {
		System.out.println("At " + ((System.currentTimeMillis() - initialBootTime) * 1000) + ", robot19.robot says \n" 
			+ "\" WE ARE DISABLED WHAT THE HECK?\"");
		
		System.out.println(RobotMap.headingGyro.getAngle());
		
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
		System.out.println("Auto Setting Up");
		RobotMap.headingGyro.reset();
		autoStartTime = System.currentTimeMillis();
		
		networktable.refresh();

		Scheduler.getInstance().run();
		
		Robot.notClear = lidar.stop;
		
		System.out.println("Auto Starting");
		if (autonomousCommand != null) autonomousCommand.start();
	}

	/** This function is called periodically during autonomous */
	public void autonomousPeriodic() {
		
		Scheduler.getInstance().run();

		Robot.notClear = lidar.stop;
	}

	public void teleopInit() {
		teleopStartTime = System.currentTimeMillis();
		
		networktable.refresh();

		if (networktable.table.getEntry("DebugMode").getBoolean(false)) debugMode = true;
		else debugMode = false;

		Scheduler.getInstance().run();

		if (autonomousCommand != null) autonomousCommand.cancel();
	}
	
	/** This function is called periodically during operator control */
	public void teleopPeriodic() {
		if (networktable.table.getEntry("DebugMode").getBoolean(false)) debugMode = true;
		else debugMode = false;

		Scheduler.getInstance().run();
	}

	/** This function is called periodically during test mode */
	public void testPeriodic() {
		System.out.println("Test Mode.");

		Scheduler.getInstance().run();
	}
}
