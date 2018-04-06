package org.usfirst.frc.team6957.robot.commands;


import org.usfirst.frc.team6957.robot.DashboardData;
import org.usfirst.frc.team6957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetElevatorClimb extends Command {
	
	private boolean status;

    public SetElevatorClimb() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	status = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (!Robot.elevator.elevatorHigh() && (Robot.elevator.elevatorLow() || Robot.elevator.elevatorSwitch())) {
    		while (!Robot.elevator.elevatorHigh()) {
    			Robot.elevator.elevatorUpDown(1.0);
    		}
    	}
    		
    	else if (Robot.elevator.elevatorHigh() && !Robot.elevator.elevatorLow() && !Robot.elevator.elevatorSwitch()) {
    		Robot.elevator.stopElevator();
    		status = true;
    	}
    		    			
    	else {
    		Robot.elevator.stopElevator();
    		status = true;
    		DashboardData.AddGameError("Elevator move to Climb Failed");
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
