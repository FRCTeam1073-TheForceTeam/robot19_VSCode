package frc.robot.commands.AutonomousTools;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTest extends CommandGroup {

    public AutoTest() {
        addSequential(new AdvancederDrive(50, "forward", 1500));
   	}
}