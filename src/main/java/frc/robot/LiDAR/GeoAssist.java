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
import java.lang.Math;

/**
 * Controls for LiDAR Localization
 * @author AlexHill and CamH
 */
public class GeoAssist {
	//Field radius is in milimeters
	public static final double fieldRadius = 18401.945/2;
	


	public static double calcDistance(double lat1, double lat2, double long1, double long2){
		//change in latitude
		double dlat = Math.abs(lat1-lat2);
		//change in longitude
		double dlong = Math.abs(long1-long2);
		//don't worry about it
		double a = Math.pow((Math.sin(dlat/2)), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow((Math.sin(dlong/2)),2);
		//addresses the quadrant
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		//distance to move
		return fieldRadius * c;
	}

	public static double calcDistance(Location location1, Location location2){
		return calcDistance(location1.latitude, location2.latitude, location1.longitude, location2.longitude);
	}

	public static double calcAngle(double lat1, double lat2, double long1, double long2) {
		double dlat = lat2-lat1;
		double dlong = long2-long1;
		double angle = (Math.atan2(dlat,dlong) * 180) / Math.PI;
		return Math.toRadians(angle);
	}

	public static double calcAngle(Location location1, Location location2) {
		return calcAngle(location1.latitude, location2.latitude, location1.longitude, location2.longitude);
	}

}
