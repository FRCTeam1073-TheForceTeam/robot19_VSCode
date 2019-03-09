/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.LiDAR;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.LiDAR.*;

/**
 * Controls for LiDAR Localization/GPS
 * @author AlexHill and CamH
 * @param radius uses metric system
 */
public class LidarDrive extends Command {
    public static final double initialLatitude = Robot.networktable.table.getEntry("XCoord").getDouble(0);
	public static final double initialLongitude = Robot.networktable.table.getEntry("YCoord").getDouble(0);
	public static final double speed = Robot.networktable.table.getEntry("robotVelocity").getDouble(0); // ~0.05m - 0.1m per step
	public static final double radius = 18401.945/2;  // 9200.9725 mm (feild dimentions the GPS is following)
    private String name = SmartDashboard.getString("Waypoint Name", "test");
	private double xCoord = SmartDashboard.getNumber("X", 0);
    private double yCoord = SmartDashboard.getNumber("Y", 0);
    private double initGyro;
	public Location currentLocation = new Location("currentLocation", initialLatitude, initialLongitude);
    public int waypointCounter = 0;
    public Route destination;

    
	public LidarDrive(String name, double xCoord, double yCoord) {
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        destination = new Route(new Location(name, xCoord, yCoord)); //makes a new "route" based on initial and final values
    }
    

    protected void initialize(){
        initGyro = Math.toRadians(RobotMap.headingGyro.getAngle()); //Starts gyro, converts data given to radians, considers angle.

        
    }
    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        super.end();
    }
}
