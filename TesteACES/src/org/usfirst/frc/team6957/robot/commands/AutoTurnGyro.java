package org.usfirst.frc.team6957.robot.commands;

import org.usfirst.frc.team6957.robot.Autonomous;
import org.usfirst.frc.team6957.robot.DashboardData;
import org.usfirst.frc.team6957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
Turns robot with two parameters, degrees and speed
 */
public class AutoTurnGyro extends Command {

	private double deg;
	private boolean finished;
	
    public AutoTurnGyro(double degrees) {
        requires(Robot.drivetrain);
        deg = degrees;
    }
	
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (deg != 0) {
    		Autonomous.TurnWithGyroPID(deg);
    		finished = true;
    	} else {
    		DashboardData.AddGameError("Turn Error");
    		finished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stopDriveTrain();
    	Robot.drivetrain.ResetGyro();
    }
}
