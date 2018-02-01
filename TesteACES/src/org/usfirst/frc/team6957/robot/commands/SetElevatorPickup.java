package org.usfirst.frc.team6957.robot.commands;

import org.usfirst.frc.team6957.robot.Robot;
import org.usfirst.frc.team6957.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetElevatorPickup extends Command {
	
	private boolean status;

    public SetElevatorPickup() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	status = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (Robot.elevator.elevatorLow()==false && (Robot.elevator.elevatorHigh() ==true || Robot.elevator.elevatorSwitch() ==true )) {     		
    		Robot.elevator.elevatorUpDown(-RobotMap.elevatorspeed);    		
    	}
    		
    	else if (Robot.elevator.elevatorLow()==true && Robot.elevator.elevatorHigh() ==false && Robot.elevator.elevatorSwitch() == false) { 
    		System.out.println("The elevator is ready to pickup the cube!");
    		Robot.elevator.stopElevator();
    		status = true;
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

}
