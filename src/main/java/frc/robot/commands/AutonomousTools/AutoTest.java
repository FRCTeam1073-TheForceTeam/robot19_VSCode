package frc.robot.commands.AutonomousTools;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTest extends CommandGroup {

    public AutoTest() {


	    
		System.out.println("JRJR AutoTest constructor");
		addSequential(new TurnWithGyroPID(.6, 45, "clockwise"));
		addSequential(new Wait(3));
		addSequential(new TurnWithGyroPID(.6, 45, "counterclockwise"));

    }
}