/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6957.robot.subsystems;

import org.usfirst.frc.team6957.robot.OI;
import org.usfirst.frc.team6957.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Elevator extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private SpeedController elevatorRight = new Spark(4);
	private SpeedController elevatorLeft = new Spark(5);
	private DigitalInput levelLowest = new DigitalInput(5);
	private DigitalInput levelHighest = new DigitalInput(6);
	private DigitalInput levelSwitch = new DigitalInput(7);
	
	public boolean lowestlevel;
	public boolean highestlevel;
	public boolean switchlevel;
	
	public Elevator() {
		// Put everything to the LiveWindow for testing.
		addChild("Elevator Right: ", (Spark) elevatorRight);
		addChild("Elevator Left: ", (Spark) elevatorLeft);
		addChild("Lowest Level: ", levelLowest);
		addChild("Highest Level: ", levelHighest);
		addChild("Level Switch: ", levelSwitch);
	}
	
	public boolean elevatorLow() {
		// Is the elevator in the lowest level?
		return !levelLowest.get(); // Return 1 for Yes or 0 for No
		
	}
	
	public boolean elevatorHigh() {
		// Is the elevator in the highest level?
		return !levelHighest.get(); // Return 1 for Yes or 0 for No
	}
	
	public boolean elevatorSwitch() {
		// Is the elevator in the switch level?
		return !levelSwitch.get(); // Return 1 for Yes or 0 for No
	}
	
	public void elevatorUpDown(double speed){
		elevatorRight.set(speed);
		elevatorLeft.set(speed);
	}
	
	public void movingElevator() {
		elevatorRight.set(OI.operator.getRawAxis(1)*RobotMap.elevatorspeed); // Right motor running at determined value
		elevatorLeft.set(OI.operator.getRawAxis(1)*RobotMap.elevatorspeed);	// Left motor running at determined value
	}
	
	public void stopElevator() {
		elevatorRight.stopMotor(); // Stop right motor
		elevatorLeft.stopMotor(); // Stop left motor
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
