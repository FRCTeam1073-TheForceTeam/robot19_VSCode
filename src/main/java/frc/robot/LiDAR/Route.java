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
 * Controls for LiDAR Localization/GPS
 * @author AlexHill and CamH
 */

public class Route {

    public Location[] waypoints;

    public Route(Location... waypoints) {
        this.waypoints = waypoints;
    }
}
