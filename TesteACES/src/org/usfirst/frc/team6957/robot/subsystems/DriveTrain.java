/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6957.robot.subsystems;

import org.usfirst.frc.team6957.robot.DashboardData;
import org.usfirst.frc.team6957.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
	
	//Instantiates Gyro
	public Gyro gyro = new AnalogGyro(0);
	
	//Instantiates Sparks
	private SpeedController rightDrive = new Spark(0);
	private SpeedController leftDrive = new Spark(1);
	
	//Instantiates DifferentialDrive
	public DifferentialDrive drivetrain
			= new DifferentialDrive(leftDrive, rightDrive);
	
	//Instantiates Encoders (DriveTrain)
	public Encoder encLeft = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	public Encoder encRight = new Encoder (2, 3, false, Encoder.EncodingType.k4X);
	
	
	
	/**
	Constructor for DriveTrain
	*/
	public DriveTrain() {
		drivetrain.setSafetyEnabled(true);
	}
	
	/**
	Sets the Encoder Parameters
	: MinRate, DistancePerPulse, ReverseDirection, Samples To Average
	*/
	public void setEncParameter() {
		encRight.setMinRate(1);
		encRight.setDistancePerPulse((6 * Math.PI) / 2048);
		encRight.setReverseDirection(true);
		encRight.setSamplesToAverage(7);
		encLeft.setMinRate(1);
		encLeft.setDistancePerPulse((6 * Math.PI) / 2048);
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
	
	
	public void CalibrateGyro() {
		gyro.calibrate();
	}
	
	public double GetGyro() {
		return gyro.getAngle();
	}
	
	public void ResetGyro() {
		gyro.reset();
	}
	
	/**
	Drives Robot with TankDrive
	. Takes one parameter: Controller
	@param Xbox
	*/
	public void driveAsTank(XboxController Xbox) {
		drivetrain.tankDrive((Xbox.getRawAxis(1)*DashboardData.drivespeed), (Xbox.getRawAxis(5)*DashboardData.drivespeed));		
	}
	
	/**
	Drives Robot with ArcadeDrive
	. Takes one parameter: Controller
	@param Xbox
	*/
	public void driveAsArcade(XboxController Xbox) {
		drivetrain.arcadeDrive((Xbox.getRawAxis(1)*DashboardData.drivespeed), (Xbox.getRawAxis(4)*DashboardData.drivespeed));
	}
	
	/**
	Stops DriveTrain
	*/
	public void stopDriveTrain() {
		rightDrive.stopMotor();
		leftDrive.stopMotor();
	}
	
	public void driveForward(double speed)
	{
		rightDrive.set(speed);
		leftDrive.set(-speed);
	}
	/*
	Default command (Set to either drive modes)
	@see edu.wpi.first.wpilibj.command.Subsystem#initDefaultCommand()
	*/
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}
}
