package frc.robot.commands.AutonomousTools;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTest extends CommandGroup {

    public AutoTest() {
		addSequential(new DataTester());
    	//addSequential(new AdvancederDrive(24, "forward", 15000));
    	//addSequential(new AdvancedTurn(90, "right", 3000));
    	//addSequential(new AdvancedTurn(90, "left", 3000));
    	//addSequential(new AdvancedTurn(0, "point", 3000));
    }
}