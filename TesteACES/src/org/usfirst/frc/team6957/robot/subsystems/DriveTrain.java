/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6957.robot.subsystems;

import org.usfirst.frc.team6957.robot.DashboardData;
import org.usfirst.frc.team6957.robot.commands.ArcadeDriveWithJoystick;
import org.usfirst.frc.team6957.robot.commands.StopDriveTrain;
import org.usfirst.frc.team6957.robot.commands.TankDriveWithJoystick;

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
	
	//Instantiates Sparks
	private SpeedController rightTank = new Spark(0);
	private SpeedController leftTank = new Spark(1);
	
	//Instantiates DifferentialDrive
	public DifferentialDrive tankDrive
			= new DifferentialDrive(leftTank, rightTank);
	
	//Instantiates Encoders (DriveTrain)
	public Encoder encLeft = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	public Encoder encRight = new Encoder (2, 3, false, Encoder.EncodingType.k4X);
	
	/**
	Constructor for DriveTrain
	*/
	public DriveTrain() {	
	}
	
	/**
	Sets the Encoder Parameters
	: MinRate, DistancePerPulse, ReverseDirection, Samples To Average
	*/
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
	
	/**
	Resets Encoders
	*/
	public void resetEncoders() {
		encRight.reset();
		encLeft.reset();
	}
	
	/**
	Drives Robot with TankDrive
	. Takes one parameter: Controller
	@param Xbox
	*/
	public void driveAsTank(XboxController Xbox) {
		tankDrive.tankDrive((Xbox.getRawAxis(1)*DashboardData.drivespeed), (Xbox.getRawAxis(5)*DashboardData.drivespeed));		
	}
	
	/**
	Drives Robot with ArcadeDrive
	. Takes one parameter: Controller
	@param Xbox
	*/
	public void driveAsArcade(XboxController Xbox) {
		tankDrive.arcadeDrive((Xbox.getRawAxis(1)*DashboardData.drivespeed), (Xbox.getRawAxis(4)*DashboardData.drivespeed));
	}
	
	/**
	Stops DriveTrain
	*/
	public void stopDriveTrain() {
		rightTank.stopMotor();
		leftTank.stopMotor();
	}
	
	/*
	Default command (Set to either drive modes)
	@see edu.wpi.first.wpilibj.command.Subsystem#initDefaultCommand()
	*/
	public void initDefaultCommand() {
		if (DashboardData.driveMode == 1) {
			//Arcade Drive
			setDefaultCommand(new ArcadeDriveWithJoystick());
		} else if (DashboardData.driveMode == 2) {
			//Tank Drive
			setDefaultCommand(new TankDriveWithJoystick());
		} else {
			//Stops Motor
			setDefaultCommand(new StopDriveTrain());
		}
	}
}
