package org.usfirst.frc.team6957.robot.commandgroup;

import org.usfirst.frc.team6957.robot.Robot;
import org.usfirst.frc.team6957.robot.commands.AutoTurn;
import org.usfirst.frc.team6957.robot.commands.DriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeft extends CommandGroup {

	private char switchLoc;
	
    public AutonomousLeft() {
    	switchLoc = Robot.oi.getSwitchLoc();
    	if (switchLoc == 'L') {
    		
    	} else if (switchLoc == 'R') {
    		
    	}
    }
}
