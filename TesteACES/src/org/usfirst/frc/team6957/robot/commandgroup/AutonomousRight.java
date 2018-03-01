package org.usfirst.frc.team6957.robot.commandgroup;

import org.usfirst.frc.team6957.robot.Robot;
import org.usfirst.frc.team6957.robot.commands.AutoTurn;
import org.usfirst.frc.team6957.robot.commands.DriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousRight extends CommandGroup {
	
	private char switchLoc;
	
    public AutonomousRight() {
    	switchLoc = Robot.oi.getSwitchLoc();
    	if (switchLoc == 'L') {
    		addSequential(new DriveStraight(20, 0.5));
    		addSequential(new AutoTurn(-90, 0.5));
    		addSequential(new DriveStraight(12.5, 0.5));
    		addSequential(new AutoTurn(-90, 0.5));
    	} else if (switchLoc == 'R') {
    		addSequential(new DriveStraight(16, 0.5));
    		addSequential(new AutoTurn(-90, 0.5));
    		addSequential(new DriveStraight(1.5, 0.5));
    	}
    }
}
