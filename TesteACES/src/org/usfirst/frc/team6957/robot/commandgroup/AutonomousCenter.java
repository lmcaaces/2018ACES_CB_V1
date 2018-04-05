package org.usfirst.frc.team6957.robot.commandgroup;

import org.usfirst.frc.team6957.robot.DashboardData;
import org.usfirst.frc.team6957.robot.Robot;
import org.usfirst.frc.team6957.robot.commands.AutoTurn;
import org.usfirst.frc.team6957.robot.commands.AutonomousElevatorUp;
import org.usfirst.frc.team6957.robot.commands.DriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
Autonomous Command for Center Position
*/
public class AutonomousCenter extends CommandGroup {
	
	private char switchLoc;
	
    public AutonomousCenter() {
    	switchLoc = Robot.oi.getSwitchLoc();
    	if (switchLoc == 'L') {							//If the switch is on the left side, then:
    		addSequential(new DriveStraight(1, 0.5));		//Drives Straight for 1 foot at Y speed
    		addSequential(new AutoTurn(-26.6, 0.5)); 	//Turns 26.6 degrees left at Y speed
    		addSequential(new DriveStraight(11.18, 0.5)); 	//Drives Straight for 11.18 feet at Y speed
    		addSequential(new AutoTurn(26.6, 0.5)); 	//Turns 26.6 degrees right at Y speed
    		addSequential(new DriveStraight(0.25, 0.5)); //Drives Straight for 0.25 feet at Y speed
    		//addSequential(new AutonomousElevatorUp(5, 0.8)); //Lifts elevator for 5 seconds at Y speed
    		//addSequential(new EjectCube()); //Ejects cube
    	} else if (switchLoc == 'R') {					//If the switch is on the right side, then:
    		addSequential(new DriveStraight(1, 0.5));		//Drives Straight for 1 foot at Y speed
    		addSequential(new AutoTurn(14, 0.5)); 		//Turns 14 degrees right at Y speed
    		addSequential(new DriveStraight(10.3, 0.5)); 	//Drives Straight for 10.3 feet at Y speed
    		addSequential(new AutoTurn(-14, 0.5)); 		//Turns 14 degrees left at Y speed
    		addSequential(new DriveStraight(0.25, 0.5));	//Drives Straight for 0.25 feet at Y speed
    		//addSequential(new AutonomousElevatorUp(5, 0.8)); 
    		//addSequential(new EjectCube()); //Ejects cube
    	} else {
    		DashboardData.AddAutoError("switchLoc error");
    	}
    }
}
