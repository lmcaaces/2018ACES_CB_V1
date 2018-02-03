/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6957.robot.subsystems;

import org.usfirst.frc.team6957.robot.OI;
import org.usfirst.frc.team6957.robot.Robot;
import org.usfirst.frc.team6957.robot.RobotMap;
import org.usfirst.frc.team6957.robot.DashboardData;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	private SpeedController rightTank = new Spark(0);
	private SpeedController leftTank = new Spark(1);
	public DifferentialDrive tankDrive = new DifferentialDrive(leftTank, rightTank);
	
	public Encoder encLeft = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	public Encoder encRight = new Encoder (2, 3, false, Encoder.EncodingType.k4X);
	
	public DriveTrain() {
		// Put everything to the LiveWindow for testing.
		addChild("Right Tank: ", (Spark) rightTank);
		addChild("Left Tank: ", (Spark) leftTank);
		addChild("Right Encoder: ", encRight);
		addChild("Left Encoder: ", encLeft);
		addChild("Tank Drive: ", tankDrive);		
	}
	
	public void setEncParameter() {
		encRight.setMinRate(1);
		encRight.setDistancePerPulse(DashboardData.rdistPulse);
		encRight.setReverseDirection(true);
		encRight.setSamplesToAverage(5);
		encLeft.setMinRate(1);
		encLeft.setDistancePerPulse(DashboardData.ldistPulse);
		encLeft.setReverseDirection(false);
		encLeft.setSamplesToAverage(7);
	}
	
	public void resetEncoders() {
		encRight.reset();
		encLeft.reset();
	}
	
	public void driveAsTank(XboxController Xbox) {
		tankDrive.tankDrive((Xbox.getRawAxis(1)*DashboardData.drivespeed), (Xbox.getRawAxis(5)*DashboardData.drivespeed));		
	}
	
	public void driveAsArcade(XboxController Xbox) {
		tankDrive.arcadeDrive((Xbox.getRawAxis(1)*DashboardData.drivespeed), (Xbox.getRawAxis(4)*DashboardData.drivespeed));
	}
	
	public void stopDriveTrain() {
		rightTank.stopMotor();
		leftTank.stopMotor();
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		
	}
}
