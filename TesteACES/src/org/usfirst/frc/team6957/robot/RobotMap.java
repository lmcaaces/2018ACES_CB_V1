/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6957.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static double drivespeed = 1.0; // Setting speed limit for the drive train
	public static double collectcube = 0.3; // Setting speed limit for the intake when collecting
	public static double ejectcube = - 0.5; // Setting speed limit for the intake when ejecting
	public static double elevatorspeed = 1.0; // Setting speed limit for the elevator 
	
	public static double diameterWheel = 6.0; // Diameter of wheel used in the drive train in inches
	public static double circumferenceWheel = diameterWheel*Math.PI;// Circumference of the wheel used in the drive train in inches
	public static double maxEncpulses = 0; // Max pulses per Encoder
	public static double distPulse = circumferenceWheel / maxEncpulses; // Distance pulse in inches

	//Autonomous Variables
	public static double shootauto = 0.8; // Setting speed for the intake when scoring in autonomous
}
