/*----------------------------------------------------------------------------*/

/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6957.robot.subsystems;

import org.usfirst.frc.team6957.robot.DashboardData;
import org.usfirst.frc.team6957.robot.commandgroup.ElevatorControl;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
Elevator Subsystem
*/
public class Elevator extends Subsystem {
	
	//Instantiates Elevator Lock Sparks
	private SpeedController elevatorLock = new Spark(6);
	
	//Instantiates Elevator Sparks
	private SpeedController elevatorRight = new Spark(4);
	private SpeedController elevatorLeft = new Spark(5);
	
	/**
	Constructor for Elevator
	*/
	public Elevator() {}
	
	
	/**
	Moves the elevator lock up or down
	. Take one Parameter: Speed of Lock
	@param speed
	*/
	public void elevetorLockUpDown(XboxController Xbox) {
		elevatorLock.set(DashboardData.intakeAngleSpeed*Xbox.getRawAxis(5));
	}
	
	/**
	Moves the elevator up and down
	. Takes one Parameter: Speed of Elevator
	@param speed
	*/
	public void elevatorUpDown(double speed){
		elevatorRight.set(speed);
		elevatorLeft.set(speed);
	}
	
	/**
	Controls the elevator with Xbox Controller
	. Take one Parameter: Xbox Controller
	@param Xbox
	*/
	public void elevatorDownJoystick(XboxController Xbox) {
		elevatorRight.set(Math.abs(Xbox.getRawAxis(1))); // Right motor running at determined value
		elevatorLeft.set(Math.abs(Xbox.getRawAxis(1)));	// Left motor running at determined value
	}
	
	public void elevatorUpJoystick(XboxController Xbox) {
		elevatorRight.set(-(Math.abs(Xbox.getRawAxis(1)))); // Right motor running at determined value
		elevatorLeft.set(-(Math.abs(Xbox.getRawAxis(1))));	// Left motor running at determined value
	}
	
	/**
	Stops Elevator Motors
	*/
	public void stopElevator() {
		elevatorRight.stopMotor(); // Stop right motor
		elevatorLeft.stopMotor(); // Stop left motor
	}
	
	/*
	Sets the default command for this subsystem
	*/
	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorControl());
	}
}
