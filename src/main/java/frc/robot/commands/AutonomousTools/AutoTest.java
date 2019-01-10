package frc.robot.commands.AutonomousTools;

import frc.robot.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTest extends CommandGroup {

    public AutoTest() {
    	addSequential(new AdvancederDrive(24, "forward", 15000));
    	//addSequential(new AdvancedTurn(90, "right", 3000));
    	//addSequential(new AdvancedTurn(90, "left", 3000));
    	//addSequential(new AdvancedTurn(0, "point", 3000));
    }
}