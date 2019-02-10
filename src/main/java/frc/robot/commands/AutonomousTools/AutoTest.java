package frc.robot.commands.AutonomousTools;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTest extends CommandGroup {

    public AutoTest() {

		double P=0.05;
		double I=0.002;
		double D=0;
	    
		System.out.println("JRJR AutoTest constructor");
    	addSequential(new TurnWithGyroPID(1, 90, "clockwise", P, I, D));

    }
}