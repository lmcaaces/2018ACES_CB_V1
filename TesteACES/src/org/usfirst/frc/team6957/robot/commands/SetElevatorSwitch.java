package org.usfirst.frc.team6957.robot.commands;

import org.usfirst.frc.team6957.robot.DashboardData;
import org.usfirst.frc.team6957.robot.Robot;
import org.usfirst.frc.team6957.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetElevatorSwitch extends Command {
	
	private boolean status;

    public SetElevatorSwitch() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	status = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.elevator.elevatorSwitch()==false && Robot.elevator.elevatorLow() == true && Robot.elevator.elevatorHigh() == false) {     		
    		while (!Robot.elevator.elevatorSwitch()) {
    			Robot.elevator.elevatorUpDown(DashboardData.elevatorspeed);
    		}
    	}
    		
    	else if (Robot.elevator.elevatorSwitch()==true && Robot.elevator.elevatorLow() == false && Robot.elevator.elevatorSwitch() == false) {
    		System.out.println("The elevator is ready to score!");
    		Robot.elevator.stopElevator();
    		status = true;
    	}
    	
    	else if (Robot.elevator.elevatorSwitch()==false && Robot.elevator.elevatorLow() == false && Robot.elevator.elevatorHigh() == true) {
    		while (!Robot.elevator.elevatorSwitch()) {
    			Robot.elevator.elevatorUpDown(-DashboardData.elevatorspeed);
    		}
    	}
    	
    	else {
    		System.out.println("Somethings is not right");
    		Robot.elevator.stopElevator();
    		status = true;
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return status;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stopElevator();
    }
    
    protected void interrupted() {
    	Robot.elevator.stopElevator();
    }

}
