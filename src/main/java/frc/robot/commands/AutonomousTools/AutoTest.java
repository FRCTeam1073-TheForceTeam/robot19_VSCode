package frc.robot.commands.AutonomousTools;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.LiDAR.LidarDrive;

public class AutoTest extends CommandGroup {

    public AutoTest() {
        addSequential(new LidarDrive("test", 100, 100));
   	}
}