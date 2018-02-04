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

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
//Instantiations//
	//Instantiates the subsystems
	/**DriveTrain Subsystem*/
	public static DriveTrain drivetrain = new DriveTrain();
	/**Elevator Subsystem*/
	public static Elevator elevator = new Elevator();
	/**Intake Subsystem*/
	public static Intake intake = new Intake();
	
	//Instantiates the OI & SmartDashboard-SD (DashboardData class)
	/**Operator Input*/
	public static OI oi = new OI();
	/**SmartDashboard*/
	public DashboardData SD = new DashboardData();
	
	
//Main Programs//
	/**
	This function is run when the robot is first started up and should be used for any initialization code.
	*/
	@Override
	public void robotInit() {
	}
	
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SD.DisabledDash();
	}

	@Override
	public void autonomousInit() {
		//AutonomousCommand.start(); //Strts Autonomous Command
		
		//Resets encoders when autonomous is enabled
		drivetrain.resetEncoders();
	}

	/**
	This function is called periodically during autonomous.
	*/
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SD.AutonomousDash();
		
		//TEMP Testing driving with encoders
		double encL = Robot.drivetrain.encLeft.getDistance();
		double encR = Robot.drivetrain.encRight.getDistance();
		if (encL <= (12*5) && encR <= (12*5) && encL == encR) {
			Robot.drivetrain.tankDrive.arcadeDrive(0.6, 0.0);
		} else if (encL <= (12*5) && encR <= (12*5) && encL < encR){
			Robot.drivetrain.tankDrive.arcadeDrive(0.6, 0.4);
		} else if (encL <= (12*5) && encR <= (12*5) && encL > encR) {
			Robot.drivetrain.tankDrive.arcadeDrive(0.6, -0.4);
		} else {
			Robot.drivetrain.tankDrive.stopMotor();
		}
	}

	@Override
	public void teleopInit() {
		//AutonomousCommand.cancel(); //Ensures that the Autonomous is stopped
	}

	/**
	This function is called periodically during operator control.
	*/
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SD.TeleopDash();
		
		//TEMP Resets Encoders and checks for any new params
		if (OI.driver.getAButton()) {
			drivetrain.resetEncoders();
			drivetrain.setEncParameter();
		}
			
	}

	/**
	This function is called periodically during test mode.
	*/
	@Override
	public void testPeriodic() {
	}
}
