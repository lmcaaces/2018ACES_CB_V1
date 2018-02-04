package org.usfirst.frc.team6957.robot.commands;

import org.usfirst.frc.team6957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
Stops Drive Train
*/
public class StopDriveTrain extends Command {

    public StopDriveTrain() {
        requires(Robot.drivetrain);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.stopDriveTrain();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
}
