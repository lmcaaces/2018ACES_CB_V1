package org.usfirst.frc.team6957.robot;

import java.util.ArrayList;

import org.usfirst.frc.team6957.robot.commandgroup.AutonomousCenter;
import org.usfirst.frc.team6957.robot.commandgroup.AutonomousDefault;
import org.usfirst.frc.team6957.robot.commandgroup.AutonomousLeft;
import org.usfirst.frc.team6957.robot.commandgroup.AutonomousRight;
import org.usfirst.frc.team6957.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardData {
	
	//Preference-UserInput Variables
	public Preferences prefs;
	public static int driveMode;
	public static String autonomousMode;
	public static double drivespeed;
	public static double collectcube;
	public static double ejectcube;
	public static double elevatorspeed;
	public static double shootauto;
	public static double gyroKp;
	public static SendableChooser<CommandGroup> autoMode = new SendableChooser<CommandGroup>();
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
		ElevatorData();
		GyroData();
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
		autonomousMode = prefs.getString("Autonomous Mode", "D");
		driveMode = prefs.getInt("Drive Mode", 0);
		drivespeed = (prefs.getDouble("Drive Speed", 100) * .01);
		collectcube = (prefs.getDouble("Intake Speed", 60) * .01);
		ejectcube = (prefs.getDouble("Outtake Speed", 100) * -.01);
		elevatorspeed = (prefs.getDouble("Elevator Speed", 50) * .01);
		shootauto = (prefs.getDouble("Auto Shoot Speed", 100) * .01);
		gyroKp = (prefs.getDouble("Gyro Kp", 0.03));
	}
	
	public static void AutoModeInit() {
		autoMode.addDefault("Default", new AutonomousDefault());
		autoMode.addObject("Right", new AutonomousRight());
		autoMode.addObject("Center", new AutonomousCenter());
		autoMode.addObject("Left", new AutonomousLeft());
		SmartDashboard.putData("Autonomous Modes", autoMode);
	}
	
	public static CommandGroup GetAutoMode() {
		return autoMode.getSelected();
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
	Displays Elevator Data
	*/
	public void ElevatorData() {
		SmartDashboard.putBoolean(
				"Elevator is High", Robot.elevator.elevatorHigh());
		SmartDashboard.putBoolean(
				"Elevator is in the Middle", Robot.elevator.elevatorSwitch());
		SmartDashboard.putBoolean(
				"Elevator is Low", Robot.elevator.elevatorLow());
	}
	
	/**
	Displays Intake Data
	*/
	public void IntakeData() {
		SmartDashboard.putBoolean(
				"Cube Inside", Intake.cubeInside());
	}
	
	
	public void GyroData() {
		SmartDashboard.putNumber("Gyro", Robot.drivetrain.GetGyro());
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
