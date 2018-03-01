package org.usfirst.frc.team6957.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {
	
	public Autonomous() {}
	
	/**
	Drives straight for x distance.
	@param distance
	@param speed
	*/
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
	
	/**
	Turns right using encoders.
	@param degrees
	@param speed
	*/
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
	
	/**
	Turns left using encoders.
	@param degrees
	@param speed
	*/
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
	
	/**
	Turns both directions using the Gyro.
	@param degrees
	@param speed
	*/
	public static void TurnWithGyro(double degrees, double speed) {
		Robot.drivetrain.ResetGyro();
		double angle = Robot.drivetrain.GetGyro();
		
		if (degrees < 0) { //Turns left
			DashboardData.AddGameMessage("Turning Right");
			while (angle > (degrees * 0.8) && angle < 5) {
				angle = Robot.drivetrain.GetGyro();
				Robot.drivetrain.drivetrain.arcadeDrive(0, -speed);
			}
		} else if (degrees > 0) { //Turns right
			DashboardData.AddGameMessage("Turning Right");
			while (angle < (degrees * 0.8) && angle > -5) {
				angle = Robot.drivetrain.GetGyro();
				Robot.drivetrain.drivetrain.arcadeDrive(0, speed);
			}
		} else { //If an error
			DashboardData.AddGameError("Invalid Degrees for Turn");
		}
		DashboardData.AddGameMessage("Turn is Finished");
	}
	
	/**
	Turns both directions using the Gyro with a P value.
	@param degrees
	*/
	public static void TurnWithGyroPID(double degrees) {
		Robot.drivetrain.ResetGyro();
		double angle = Robot.drivetrain.GetGyro();
		
		if (degrees > 0) {
			DashboardData.AddGameMessage("Turning Right");
			while (angle < degrees && angle > -5) {
				angle = Robot.drivetrain.GetGyro();
				Robot.drivetrain.drivetrain.arcadeDrive(0, ((degrees - Robot.drivetrain.GetGyro()) * (1/90)));
				SmartDashboard.putNumber("Angle", angle);
				SmartDashboard.putNumber("Turning Value", ((degrees - angle) * (1/90)));
			}
		} else if (degrees < 0) {
			DashboardData.AddGameMessage("Turning Left");
			while (angle > degrees && angle > 5) {
				angle = Robot.drivetrain.GetGyro();
				Robot.drivetrain.drivetrain.arcadeDrive(0, ((degrees - Robot.drivetrain.GetGyro()) * (1/90)));
				SmartDashboard.putNumber("Angle", angle);
				SmartDashboard.putNumber("Turning Value", ((degrees - angle) * (1/90)));
			}
		} else {
			DashboardData.AddGameError("Invalid Degrees for Turn");
		}
		DashboardData.AddGameMessage("Turn is Finished");
	}
}
