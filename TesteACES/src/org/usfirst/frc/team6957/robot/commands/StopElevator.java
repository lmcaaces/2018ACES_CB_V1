package org.usfirst.frc.team6957.robot.commands;

import org.usfirst.frc.team6957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopElevator extends Command {

    public StopElevator() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.stopElevator();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stopElevator();
    }
}
