package org.usfirst.frc.team6957.robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardData {
	
	//Preference-UserInput Variables
	public Preferences prefs;
	public static int driveMode;
	//public static int autonomousMode;
	public static double drivespeed;
	public static double collectcube;
	public static double ejectcube;
	public static double elevatorspeed;
	public static double diameterWheel;
	public static double lmaxEncpulses;
	public static double rmaxEncpulses;
	public static double shootauto;
	public static double circumferenceWheel;
	public static double ldistPulse;
	public static double rdistPulse;
	
	
	
	/**Constructor for DashboardData*/
	public DashboardData() {
	}
	
	/**Displays information for disabled periodic*/
	public void DisabledDash() {
		UniversalDash();
		RobotSettings();
	}
	
	/**Displays information for autonomous periodic*/
	public void AutonomousDash() {
		UniversalDash();
	}
	
	/**Displays information for teleop periodic*/
	public void TeleopDash() {
		UniversalDash();
		CheckRobotSettings();
	}
	
	/**Universal Dashboard information*/
	public void UniversalDash() {
		EncoderData();
		ElevatorData();
	}
	
	/**Checks for updated robot setting by pushing the start button in teleop*/
	public void CheckRobotSettings() {
		if (OI.driver.getStartButton() || OI.operator.getStartButton()) {
			RobotSettings();
		}
	}
	
	/**Gets user input from smartdashboard*/
	public void RobotSettings() {
		prefs = Preferences.getInstance();
		//autonomousMode = prefs.getInt("Autonomous Mode", 0);
		driveMode = prefs.getInt("Drive Mode", 0);
		drivespeed = (prefs.getDouble("Drive Speed", 100) * .01);
		collectcube = (prefs.getDouble("Collect Cube Speed", 60) * .01);
		ejectcube = (prefs.getDouble("Eject Cube Speed", 100) * .01);
		elevatorspeed = (prefs.getDouble("Elevator Speed", 50) * .01);
		diameterWheel = prefs.getDouble("Diameter Wheel Size", 6.0);
		lmaxEncpulses = prefs.getDouble("Max Pulses for Left Encoder", 0);
		rmaxEncpulses = prefs.getDouble("Max Pulses for Right Encoder", 0);
		circumferenceWheel = diameterWheel * Math.PI;
		ldistPulse = circumferenceWheel / lmaxEncpulses;
		rdistPulse = circumferenceWheel / rmaxEncpulses;
		
		
		shootauto = (prefs.getDouble("DriveSpeed", 100) * .01);
		
		
		//DELETE AFTER VERIFYING ROBOT MAP
		//RobotMap.drivespeed = DashboardData.SDdrivespeed; // Setting speed limit for the drive train
		//RobotMap.leftdriveinhibitor = DashboardData.SDleftdriveinhibitor;
		//RobotMap.collectcube = DashboardData.SDcollectcube; // Setting speed limit for the intake when collecting
		//RobotMap.ejectcube = - DashboardData.SDejectcube; // Setting speed limit for the intake when ejecting
		//RobotMap.elevatorspeed = DashboardData.SDelevatorspeed; // Setting speed limit for the elevator 
		//RobotMap.diameterWheel = DashboardData.SDdiameterWheel; // Diameter of wheel used in the drive train in inches
		//RobotMap.circumferenceWheel = RobotMap.diameterWheel*Math.PI;// Circumference of the wheel used in the drive train in inches
		//RobotMap.lmaxEncpulses = DashboardData.SDlmaxEncpulses; // Max pulses for LEncoder
		//RobotMap.rmaxEncpulses = DashboardData.SDrmaxEncpulses; // Max pulses for REncoder
		//RobotMap.ldistPulse = RobotMap.circumferenceWheel / RobotMap.lmaxEncpulses; // Distance pulse in inches
		//RobotMap.rdistPulse = RobotMap.circumferenceWheel / RobotMap.rmaxEncpulses; // Distance pulse in inches
		//Autonomous Variables
		//RobotMap.shootauto = 0.8; // Setting speed for the intake when scoring in autonomous
	}
	
	/*
	/**Changes drive type based on driveMode variable/
	public void RobotDriveMode() {
		if (driveMode == 1) {
			//Arcade Drive
		} else if (driveMode == 2) {
			//Tank Drive
		} else {
			//Stop Motor
		}
	}
	*/
	
	/*
	/**Changes autonomous type based on autonomousMode variable/
	public void RobotAutonomousMode() {
		if (autonomousMode == 1) {
			//Example Autonomous Code
		} else if (autonomousMode == 2) {
			//Example Autonomous Code
		} else {
			//Stop Motor
		}
	}
	*/
	
	/**Displays Encoder Data*/
	public void EncoderData() {
		//Left Encoder Data
		SmartDashboard.putNumber(
				"LENC Count", Robot.drivetrain.encLeft.get());
		SmartDashboard.putNumber(
				"LENC Raw Distance", Robot.drivetrain.encLeft.getRaw());
		SmartDashboard.putNumber(
				"LENC Distance", Robot.drivetrain.encLeft.getDistance());
		SmartDashboard.putNumber(
				"LENC Rate", Robot.drivetrain.encLeft.getRate());
		SmartDashboard.putBoolean(
				"LENC Direction", Robot.drivetrain.encLeft.getDirection());
		SmartDashboard.putBoolean(
				"LENC Stopped", Robot.drivetrain.encLeft.getStopped());
		
		//Right Encoder Data
		SmartDashboard.putNumber(
				"RENC Count", Robot.drivetrain.encRight.get());
		SmartDashboard.putNumber(
				"RENC Raw Distance", Robot.drivetrain.encRight.getRaw());
		SmartDashboard.putNumber(
				"RENC Distance", Robot.drivetrain.encRight.getDistance());
		SmartDashboard.putNumber(
				"RENC Rate", Robot.drivetrain.encRight.getRate());
		SmartDashboard.putBoolean(
				"RENC Direction", Robot.drivetrain.encRight.getDirection());
		SmartDashboard.putBoolean(
				"RENC Stopped", Robot.drivetrain.encRight.getStopped());
	}
	
	/**Displays Elevator Data*/
	public void ElevatorData() {
		SmartDashboard.putBoolean(
				"Elevator is High", Robot.elevator.elevatorHigh());
		SmartDashboard.putBoolean(
				"Elevator is Low", Robot.elevator.elevatorLow());
	}
	
}
