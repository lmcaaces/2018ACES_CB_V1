package org.usfirst.frc.team6957.robot.commands;

import org.usfirst.frc.team6957.robot.DashboardData;
import org.usfirst.frc.team6957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithJoystick extends Command {

    public DriveWithJoystick() {
        requires(Robot.drivetrain);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (DashboardData.driveMode == 1) {
    		Robot.drivetrain.driveAsArcade(Robot.oi.getDriver());
    	} else if (DashboardData.driveMode == 2) {
    		Robot.drivetrain.driveAsTank(Robot.oi.getDriver());
    	} else {
    		SmartDashboard.putString("Drive Mode Error", "Invalid Drive Mode");
    	}
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
