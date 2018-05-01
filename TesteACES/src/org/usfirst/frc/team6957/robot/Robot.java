/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6957.robot;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;

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
	
	public static DoubleSolenoid RampDrop = new DoubleSolenoid(0, 1);
	public static Timer RampDropTimer = new Timer();
	
	//Instantiates the OI, SmartDashboard-SD (DashboardData class), and Autonomous Command
	/**Operator Input*/
	public static OI oi = new OI();
	/**SmartDashboard*/
	public DashboardData SD = new DashboardData();
	
	public Autonomous Auton = new Autonomous();
	
	public Timer autoTimer = new Timer();
	
//Main Programs//
	/**
	This function is run when the robot is first started up and should be used for any initialization code.
	*/
	@Override
	public void robotInit() {
		SD.CheckRobotSettings();
		NetworkTableInstance.getDefault().getTable("limelight");
		
		
		NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
		RampDrop.set(DoubleSolenoid.Value.kForward);
	}
	
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SD.DisabledDash();
		drivetrain.resetEncoders();
	}

	@Override
	public void autonomousInit() {
		DashboardData.AddAutoMessage("Starting");
		AutonomousModes.AutonomousInit();
	}

	/**
	This function is called periodically during autonomous.
	*/
	@Override
	public void autonomousPeriodic() {
		SD.AutonomousDash();
		AutonomousModes.Autonomous();
		
		/*EMERGENCY AUTONOMOUS
		if (autoTimer.get() < 4) {
			drivetrain.stopDriveTrain();
		} else if (autoTimer.get() < 7) {
			drivetrain.drivetrain.arcadeDrive(0.5, 0);
		} else {
			drivetrain.stopDriveTrain();
		}
		*/
	}
	@Override
	public void teleopInit() {
		DashboardData.AddTeleopMessage("Starting");
		RampDropTimer.reset();
		RampDropTimer.start();
	}
	
	/**
	This function is called periodically during operator control.
	*/
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SD.TeleopDash();
		//Merged
		//Elevator Control
    	if (OI.getOperator().getRawAxis(1) < 0) {
    		Robot.elevator.elevatorUpJoystick(OI.getOperator());
    	} else if (OI.getOperator().getRawAxis(1) > 0) {
    		Robot.elevator.elevatorDownJoystick(OI.getOperator());
    	} else {
    		Robot.elevator.stopElevator();
    	}	
		
    	//Ramp Drop Control
    	if (OI.getOperator().getYButtonPressed()/* && RampDropTimer.get() >= 105*/) {
    		RampDrop.set(DoubleSolenoid.Value.kReverse);
    	}
    	
    	if (OI.getOperator().getBButtonPressed()) {
    		RampDrop.set(DoubleSolenoid.Value.kForward);
    	}
    	
	}

	/**
	This function is called periodically during test mode.
	*/
	@Override
	public void testPeriodic() {
	}
}
