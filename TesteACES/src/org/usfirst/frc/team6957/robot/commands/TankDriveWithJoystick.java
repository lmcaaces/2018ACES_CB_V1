package org.usfirst.frc.team6957.robot.commands;

import org.usfirst.frc.team6957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDriveWithJoystick extends Command {

    public TankDriveWithJoystick() {
        requires(Robot.drivetrain);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.driveAsTank(Robot.oi.getDriver());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stopDriveTrain();
    }
}
