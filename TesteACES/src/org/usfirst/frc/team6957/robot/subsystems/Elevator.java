/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6957.robot.subsystems;

import org.usfirst.frc.team6957.robot.DashboardData;
import org.usfirst.frc.team6957.robot.commands.ElevatorControlWithJoystick;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
Elevator Subsystem
*/
public class Elevator extends Subsystem {
	
	//Instantiates Elevator Sparks
	private SpeedController elevatorRight = new Spark(4);
	private SpeedController elevatorLeft = new Spark(5);
	
	//Instantiates Elevator Sensors/Switches
	private DigitalInput levelLowest = new DigitalInput(5);
	private DigitalInput levelHighest = new DigitalInput(6);
	private DigitalInput levelSwitch = new DigitalInput(7);
	
	public boolean lowestlevel;
	public boolean highestlevel;
	public boolean switchlevel;
	
	/**
	Constructor for Elevator
	*/
	public Elevator() {}
	
	/**
	Returns whether or not the elevator is in the lowest position
	(Intake Position)
	@return
	*/
	public boolean elevatorLow() {
		// Is the elevator in the lowest level?
		return !levelLowest.get(); // Return 1 for Yes or 0 for No
		
	}
	
	/**
	Returns whether or not the elevator is in the highest position
	(Scale Position)
	@return
	*/
	public boolean elevatorHigh() {
		// Is the elevator in the highest level?
		return !levelHighest.get(); // Return 1 for Yes or 0 for No
	}
	
	/**
	Returns whether or not the elevator is in the middle/switch position
	(Switch Position)
	@return
	*/
	public boolean elevatorSwitch() {
		// Is the elevator in the switch level?
		return !levelSwitch.get(); // Return 1 for Yes or 0 for No
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
	public void movingElevator(XboxController Xbox) {
		elevatorRight.set(Xbox.getRawAxis(1)*DashboardData.elevatorspeed); // Right motor running at determined value
		elevatorLeft.set(Xbox.getRawAxis(1)*DashboardData.elevatorspeed);	// Left motor running at determined value
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
		setDefaultCommand(new ElevatorControlWithJoystick());
	}
}
