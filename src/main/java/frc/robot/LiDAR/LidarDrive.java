/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.LiDAR;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.LiDAR.*;

/**
 * Controls for LiDAR Localization
 * @author AlexHill and CamH
 */
public class LidarDrive {
    public static final double initialLatitude = 48.138083;
	public static final double initialLongitude = 11.561102;
	public static final double SIMULATOR_MOVEMENT_SPEED = 0.000015; // ~0.05m - 0.1m per step
	public static final double ARRIVAL_RADIUS_IN_KM = 0.05 / 1000;  // 0.05m

	public Location currentLocation = new Location("currentLocation", initialLatitude, initialLongitude);
    
    public int waypointCounter = 0;
	
    public Route simulatedRoute = new Route(
    		new Location("Waypoint 1", 48.137413, 11.561020),
			new Location("Waypoint 2", 48.137370, 11.564539),
			new Location("Waypoint 3", 48.137449, 11.565000),
			new Location("Waypoint 4", 48.137578, 11.565311));
    
	public void move(){
    	Location nextWaypoint = simulatedRoute.waypoints[waypointCounter];
    	if (GeoAssist.calcDistance(currentLocation, nextWaypoint) < ARRIVAL_RADIUS_IN_KM) {
    		waypointCounter++;
    		if (waypointCounter > simulatedRoute.waypoints.length-1) {
    			currentLocation = new Location("currentLocation", initialLatitude, initialLongitude);
    			waypointCounter = 0;
    		}
    		nextWaypoint = simulatedRoute.waypoints[waypointCounter];
    	}
    	System.out.println("Moving to " + nextWaypoint.geoLoca + ". Distance = " + GeoAssist.calcDistance(currentLocation, nextWaypoint) * 1000 + "m");
    	double angle = GeoAssist.calcAngle(currentLocation, nextWaypoint);
    	double newLat = currentLocation.latitude + Math.sin(angle) * SIMULATOR_MOVEMENT_SPEED;
    	double newLon = currentLocation.longitude + Math.cos(angle) * SIMULATOR_MOVEMENT_SPEED;
    	currentLocation = new Location("currentLocation", newLat, newLon);
    }

    
}
