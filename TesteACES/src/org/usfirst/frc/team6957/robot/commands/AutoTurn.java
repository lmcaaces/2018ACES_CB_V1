package org.usfirst.frc.team6957.robot.commands;

import org.usfirst.frc.team6957.robot.Autonomous;
import org.usfirst.frc.team6957.robot.DashboardData;
import org.usfirst.frc.team6957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
Turns robot with two parameters, degrees and speed
 */
public class AutoTurn extends Command {

	private double deg;
	private double spd;
	private boolean finished;
	
    public AutoTurn(double degrees, double speed) {
        requires(Robot.drivetrain);
        deg = degrees;
        spd = speed;
    }
	
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (deg < 0) {
    		Autonomous.TurnLeft(deg, spd);
    		finished = true;
    	} else if (deg > 0) {
    		Autonomous.TurnRight(deg, spd);
    		finished = true;
    	} else {
    		DashboardData.AddAutoError("Invalid Degrees for turn");
    	}
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
