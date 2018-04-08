package org.usfirst.frc.team6957.robot;

import java.util.ArrayList;


import org.usfirst.frc.team6957.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardData {
	
	//Preference-UserInput Variables
	public Preferences prefs;
	public static int driveMode;
	public static double distance1;
	public static double distance2;
	public static double distance3;
	public static double turn1;
	public static double turn2;
	public static double turn3;
	public static double time1;
	public static double time2;
	public static double time3;
	public static double autoSpeed;
	public static double autoTurnSpeed;
	public static double autoDelay;
	public static double intakeAngleSpeed;
	public static String autoMode;
	public static boolean autoType;
	public static ArrayList<String> autonomousMessages = new ArrayList<String>();
	public static ArrayList<String> autonomousErrors = new ArrayList<String>();
	public static ArrayList<String> teleopMessages = new ArrayList<String>();
	public static ArrayList<String> teleopErrors = new ArrayList<String>();
	
	/**
	Constructor for DashboardData
	*/
	public DashboardData() {}
	
	/**
	Displays information for disabled periodic
	*/
	public void DisabledDash() {
		UniversalDash();
		RobotSettings();
		ClearMessages();
		Robot.drivetrain.setEncParameter();
	}
	
	/**
	Displays information for autonomous periodic
	*/
	public void AutonomousDash() {
		UniversalDash();
		GetAutonomousMessages();
	}
	
	/**
	Displays information for teleop periodic
	*/
	public void TeleopDash() {
		UniversalDash();
		CheckRobotSettings();
		GetTeleopMessages();
	}
	
	/**
	Universal Dashboard information
	*/
	public void UniversalDash() {
		EncoderData();
		//ElevatorData();
	}
	
	/**
	Checks for updated robot setting by pushing the start button in teleop
	*/
	public void CheckRobotSettings() {
		if (OI.driver.getStartButton() || OI.operator.getStartButton()) {
			RobotSettings();
		}
	}
	
	/**
	Gets user input from SmartDashboard
	*/
	public void RobotSettings() {
		prefs = Preferences.getInstance();
		driveMode = prefs.getInt("Drive Mode", 0);
		autoMode = prefs.getString("Autonomous Mode", "Default");
		autoType = prefs.getBoolean("Use Encoders for Autonomous", false);
		distance1 = (prefs.getDouble("Distance 1", 1));
		distance2 = (prefs.getDouble("Distance 2", 1));
		distance3 = (prefs.getDouble("Distance 3", 1));
		turn1 = (prefs.getDouble("Turn 1", 1));
		turn2 = (prefs.getDouble("Turn 2", 1));
		turn3 = (prefs.getDouble("Turn 3", 1));
		time1 = (prefs.getDouble("Time 1", 1));
		time2 = (prefs.getDouble("Time 2", 1));
		time3 = (prefs.getDouble("Time 3", 1));
		autoSpeed = (prefs.getDouble("Auto Speed", 0.6));
		autoTurnSpeed = (prefs.getDouble("Auto Turn Speed", 0.6));
		intakeAngleSpeed = (prefs.getDouble("Intake Angle Speed", 1.0));
	}
	
	/**
	Displays Encoder Data
	*/
	public static void EncoderData() {
		//Left Encoder Data
		SmartDashboard.putNumber(
				"LENC Distance", Robot.drivetrain.encLeft.getDistance());
		//Right Encoder Data
		SmartDashboard.putNumber(
				"RENC Distance", Robot.drivetrain.encRight.getDistance());
	}
	
	/**
	Displays Intake Data
	*/
	public void IntakeData() {
		SmartDashboard.putBoolean(
				"Cube Inside", Intake.cubeInside());
	}
	
	
	/**
	Gets any messages from autonomous (Messages & Errors)
	*/
	public void GetAutonomousMessages() {
		String[] autoMessages = autonomousMessages.toArray(new String[autonomousMessages.size()]);
		String[] autoErrors = autonomousErrors.toArray(new String[autonomousErrors.size()]);
		SmartDashboard.putStringArray("Autonomous Messages", autoMessages);
		SmartDashboard.putStringArray("Autonomous Errors", autoErrors);
	}
	
	/**
	Gets any messages from teleop (Messages & Errors)
	*/
	public void GetTeleopMessages() {
		String[] telMessages = teleopMessages.toArray(new String[teleopMessages.size()]);
		String[] telErrors = teleopErrors.toArray(new String[teleopErrors.size()]);
		SmartDashboard.putStringArray("Teleop Messages", telMessages);
		SmartDashboard.putStringArray("Teleop Errors", telErrors);
	}
	
	/**
	Adds a message to autonomous
	@param message
	*/
	public static void AddAutoMessage(String message) {
		autonomousMessages.add("(" + message + ")");
	}
	
	/**
	Adds an error to autonomous
	@param error
	*/
	public static void AddAutoError(String error) {
		autonomousErrors.add("(" + error + ")");
	}
	
	/**
	Adds a message to teleop
	@param message
	*/
	public static void AddTeleopMessage(String message) {
		teleopMessages.add("(" + message + ")");
	}
	
	/**
	Adds an error to teleop
	@param error
	*/
	public static void AddTeleopError(String error) {
		teleopErrors.add("(" + error + ")");
	}
	
	/**
	Adds a message to either teleop or auto based on which is enabled
	@param message
	*/
	public static void AddGameMessage(String message) {
		if (DriverStation.getInstance().isAutonomous()) {
			AddAutoMessage(message);
		} else if (DriverStation.getInstance().isOperatorControl()) {
			AddTeleopMessage(message);
		}
	}
	
	/**
	Adds an error to either teleop or auto based on which is enables
	@param error
	 */
	public static void AddGameError(String error) {
		if (DriverStation.getInstance().isAutonomous()) {
			AddAutoError(error);
		} else if (DriverStation.getInstance().isOperatorControl()) {
			AddTeleopError(error);
		}
	}
	
	/**
	Clears all messages and errors
	*/
	public void ClearMessages() {
		autonomousMessages.clear();
		autonomousErrors.clear();
		teleopMessages.clear();
		teleopErrors.clear();
	}
	
}
