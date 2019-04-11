package frc.robot.commands.AutonomousTools;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.Lidar.LidarAlign;

public class AutoTest extends CommandGroup {

    public AutoTest() {
        addSequential(new LidarAlign());
   	}
}