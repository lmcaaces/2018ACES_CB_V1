package org.usfirst.frc.team6957.robot.commands;

import org.usfirst.frc.team6957.robot.Autonomous;
import org.usfirst.frc.team6957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
Drives Straight for 'distance' distance and 'speed' speed
. Takes two parameters 'distance' and 'speed'
*/
public class DriveStraight extends Command {
	
	private double dist;
	private double spd;
	private boolean finished;
	
	//Constructor
    public DriveStraight(double distance, double speed) {
        requires(Robot.drivetrain);
        dist = distance;
        spd = speed;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Autonomous.DriveDistance(dist, spd);
    	finished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stopDriveTrain();
    }
}
