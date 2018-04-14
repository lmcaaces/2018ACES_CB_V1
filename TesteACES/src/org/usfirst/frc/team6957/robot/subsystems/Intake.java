/*----------------------------------------------------------------------------*/

/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6957.robot.subsystems;


import org.usfirst.frc.team6957.robot.commands.IntakeControlWithJoystick;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Intake extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	private SpeedController intakeRight = new Spark(2);
	private SpeedController intakeLeft = new Spark(3);
	private static DigitalInput cubeDetector = new DigitalInput(8);
	
	public Intake() {}
	
	public static boolean cubeInside() {
		// Is there a cube in the robot?
		return cubeDetector.get(); // Returns 1 for yes and 0 for no		
	}
	
	public void startIntake(double speed) {
		intakeRight.set(-speed); // Set speed right motor determined value
		intakeLeft.set(speed); // Set speed left motor determined value
	}
	
	public void stopIntake() {
		intakeRight.stopMotor(); // Stop right motor
		intakeLeft.stopMotor(); // Stop left motor
	}
	
	public void fixPositionCube(double speed) {
		intakeRight.set(speed);
		intakeLeft.set(speed);
	}
	
	
	public void intakeAnalog(XboxController Xbox) {
		intakeRight.set(-Xbox.getRawAxis(2));
		intakeLeft.set(Xbox.getRawAxis(3));
	}

	public void initDefaultCommand() {
		setDefaultCommand(new IntakeControlWithJoystick());
	}
}
