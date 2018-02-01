/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6957.robot;
import edu.wpi.first.wpilibj.IterativeRobot;

import org.usfirst.frc.team6957.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6957.robot.subsystems.Elevator;
import org.usfirst.frc.team6957.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	
	public static DriveTrain drivetrain;
	public static OI oi;
	public static Elevator elevator;
	public static Intake intake;
	
	public String teste;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		drivetrain = new DriveTrain();
		elevator = new Elevator();
		intake = new Intake();
		oi = new OI();
		
		SmartDashboard.putBoolean("Cube all the way in: ", Robot.intake.cubeInside());
		SmartDashboard.putBoolean("Lowest Level: ", Robot.elevator.elevatorLow());
		SmartDashboard.putBoolean("Highest Level: ", Robot.elevator.elevatorHigh());
		SmartDashboard.putBoolean("Switch Level: ", Robot.elevator.elevatorSwitch());
		SmartDashboard.putNumber("Right Encoder Distance: ", 0);
		SmartDashboard.putNumber("Left Encoder Distance: ", 0);
		
	}
	
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {

	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		drivetrain.driveAsArcade();
		elevator.movingElevator();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
