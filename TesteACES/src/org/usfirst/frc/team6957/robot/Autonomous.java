package org.usfirst.frc.team6957.robot;

import edu.wpi.first.wpilibj.DriverStation;

public class Autonomous {
	
	public Autonomous() {}
	
	public static void DriveDistance(double distance, double speed) {
		Robot.drivetrain.resetEncoders();
		double encL = Robot.drivetrain.encLeft.getDistance();
		double encR = Robot.drivetrain.encRight.getDistance();
		//double angle = Robot.drivetrain.GetGyro();
		while (((encL + encR)/2) <= (distance * 12)) {
			Robot.drivetrain.drivetrain.arcadeDrive(-speed, 0);
			encL = Robot.drivetrain.encLeft.getDistance();
			encR = Robot.drivetrain.encRight.getDistance();
			DashboardData.EncoderData();
		}
		DashboardData.AddAutoMessage("Drive Was A Success!");
	}
	
	public static void TurnRight(double degrees, double speed) {
		Robot.drivetrain.resetEncoders();
		double encL = Robot.drivetrain.encLeft.getDistance();
		double encR = Robot.drivetrain.encRight.getDistance();
		while ((encL >= -(degrees / 4)) && (encR <= (degrees / 4))) {
			Robot.drivetrain.drivetrain.tankDrive(speed, -speed);
			encL = Robot.drivetrain.encLeft.getDistance();
			encR = Robot.drivetrain.encRight.getDistance();
			DashboardData.EncoderData();
		}
		DashboardData.AddAutoMessage("Turn Was a Success!");
	}
	
	public static void TurnLeft(double degrees, double speed) {
		Robot.drivetrain.resetEncoders();
		double encL = Robot.drivetrain.encLeft.getDistance();
		double encR = Robot.drivetrain.encRight.getDistance();
		while ((encL >= -(degrees / 4)) && (encR <= (degrees / 4))) {
			Robot.drivetrain.drivetrain.tankDrive(-speed, speed);
			encL = Robot.drivetrain.encLeft.getDistance();
			encR = Robot.drivetrain.encRight.getDistance();
			DashboardData.EncoderData();
		}
		DashboardData.AddAutoMessage("Turn Was a Success!");
	}
	
	public static void TurnWithGyro(double degrees, double speed) {
		Robot.drivetrain.ResetGyro();
		double angle = Robot.drivetrain.GetGyro();
		if (degrees < 0) {
			while (angle > degrees) {
				angle = Robot.drivetrain.GetGyro();
				Robot.drivetrain.drivetrain.arcadeDrive(0, -speed);
			}
		} else if (degrees > 0) {
			while (angle < degrees) {
				angle = Robot.drivetrain.GetGyro();
				Robot.drivetrain.drivetrain.arcadeDrive(0, speed);
			}
		} else {
			DashboardData.AddAutoError("Invalid Degrees for Turn");
		}
		DashboardData.AddAutoMessage("Turn Was a Success");
	}
	
	public static void TurnWithGyroPID(double degrees) {
		if (degrees > 0) {
			double turningValue = (degrees - Robot.drivetrain.GetGyro()) * DashboardData.gyroKp;
			Robot.drivetrain.drivetrain.arcadeDrive(0, turningValue);
		} else if (degrees < 0) {
			double turningValue = (Math.abs(degrees) - Robot.drivetrain.GetGyro()) * DashboardData.gyroKp;
			Robot.drivetrain.drivetrain.arcadeDrive(0, -turningValue);
		} else {
			Robot.drivetrain.stopDriveTrain();
		}
	}
}
