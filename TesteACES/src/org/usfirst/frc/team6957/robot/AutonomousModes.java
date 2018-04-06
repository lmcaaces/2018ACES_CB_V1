package org.usfirst.frc.team6957.robot;

import org.usfirst.frc.team6957.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class AutonomousModes {
	
	private static String mode = DashboardData.autoMode;
	private static boolean enc = DashboardData.autoType;
	private static double delay = DashboardData.autoDelay;
	private static double speed = DashboardData.autoSpeed;
	private static double turnspeed = DashboardData.autoTurnSpeed;
	private static double turn1 = DashboardData.turn1;
	private static double turn2 = DashboardData.turn2;
	private static double turn3 = DashboardData.turn3;
	private static double distance1 = DashboardData.distance1;
	private static double distance2 = DashboardData.distance2;
	private static double distance3 = DashboardData.distance3;
	private static double time1 = DashboardData.time1;
	private static double time2 = DashboardData.time2;
	private static double time3 = DashboardData.time3;
	public static Timer autoDriveTimer = new Timer();
	public static Timer autoElevTimer = new Timer();
	private static Timer ADT = autoDriveTimer;
	private static Timer AET = autoElevTimer;
	private static DriveTrain DT = new DriveTrain();
	private static char switchLoc = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
	
	public AutonomousModes() {}
	
	/**
	Resets and starts autonomous timers
	 */
	public static void AutonomousInit() {
		autoDriveTimer.reset();
		autoDriveTimer.start();
		autoElevTimer.reset();
		autoElevTimer.start();
	}
	
	/**
	Chooses an autonomous to run based on auto mode
	*/
	public static void Autonomous() {
		switch (mode.charAt(0)) {
			case 'D': //Default
				if (enc) {
					EncDefault(); //With Encoders
				} else {
					Default(); //Time based
				}
			case 'L': //Left
				if (enc) {
					EncLeft(); //With Encoders
				} else {
					Left(); //Time based
				}
			case 'C': //Center
				if (enc) {
					EncCenter(); //With Encoders
				} else {
					Center(); //Time based
				}
			case 'R': //Right
				if (enc) {
					EncRight(); //With Encoders
				} else {
					Right(); //Time based
				}
			case 'd': //Default
				if (enc) {
					EncDefault(); //With Encoders
				} else {
					Default(); //Time based
				}
			case 'l': //Left
				if (enc) {
					EncLeft(); //With Encoders
				} else {
					Left(); //Time based
				}
			case 'c': //Center
				if (enc) {
					EncCenter(); //With Encoders
				} else {
					Center(); //Time based
				}
			case 'r': //Right
				if (enc) {
					EncRight(); //With Encoders
				} else {
					Right(); //Time based
				}
		}
	}
	
	/**
	Default autonomous (time based)
	*/
	private static void Default() {
		//Drive
		if (ADT.get() < delay) {
			DT.drivetrain.stopMotor();
		} else if (ADT.get() < delay + time1) {
			DT.drivetrain.arcadeDrive(speed, 0);
		} else {
			DT.drivetrain.stopMotor();
		}
	}
	
	/**
	Left autonomous (time based)
	*/
	private static void Left() {
		if (switchLoc == 'L') { //If switch is on left side
			if (ADT.get() < delay) {
				DT.drivetrain.stopMotor();
			} else if (ADT.get() < delay + time1) {
				DT.drivetrain.arcadeDrive(speed, 0);
			} else if (ADT.get() < delay + time1 + turn1) {
				DT.drivetrain.arcadeDrive(0, turnspeed);
			} else if (ADT.get() < delay + time1 + turn1 + time2) {
				DT.drivetrain.arcadeDrive(speed, 0);
			} else {
				DT.drivetrain.stopMotor();
			}
		} else if (switchLoc == 'R') { //If switch is on right side
			if (ADT.get() < delay) {
				DT.drivetrain.stopMotor();
			} else if (ADT.get() < delay + time1) {
				DT.drivetrain.arcadeDrive(speed, 0);
			} else {
				DT.drivetrain.stopMotor();
			}
		} else { //If there is an error
			DashboardData.AddGameError("Did not recieve switch location");
			DT.drivetrain.stopMotor();
		}
	}
	
	/**
	Center autonomous (time based)
	*/
	private static void Center() {
		if (switchLoc == 'L') { //If switch is on left side
			if (ADT.get() < delay) {
				DT.drivetrain.stopMotor();
			} else if (ADT.get() < delay + time1) {
				DT.drivetrain.arcadeDrive(speed, 0);
			} else if (ADT.get() < delay + time1 + turn1) {
				DT.drivetrain.arcadeDrive(0, -turnspeed);
			} else if (ADT.get() < delay + time1 + turn1 + time2) {
				DT.drivetrain.arcadeDrive(speed, 0);
			} else if (ADT.get() < delay + time1 + turn2 + time2 + turn2) {
				DT.drivetrain.arcadeDrive(0, -turnspeed);
			} else {
				DT.drivetrain.stopMotor();
			}
		} else if (switchLoc == 'R') { //If switch is on right side
			if (ADT.get() < delay) {
				DT.drivetrain.stopMotor();
			} else if (ADT.get() < delay + time1) {
				DT.drivetrain.arcadeDrive(speed, 0);
			} else if (ADT.get() < delay + time1 + turn1) {
				DT.drivetrain.arcadeDrive(0, turnspeed);
			} else if (ADT.get() < delay + time1 + turn1 + time2) {
				DT.drivetrain.arcadeDrive(speed, 0);
			} else if (ADT.get() < delay + time1 + turn2 + time2 + turn2) {
				DT.drivetrain.arcadeDrive(0, -turnspeed);
			} else {
				DT.drivetrain.stopMotor();
			}
		} else { //If there is an error
			DashboardData.AddGameError("Did not recieve switch location");
			DT.drivetrain.stopMotor();
		}
	}
	
	/**
	Right autonomous (time based)
	*/
	private static void Right() {
		if (switchLoc == 'L') { //If switch is on left side
			if (ADT.get() < delay) {
				DT.drivetrain.stopMotor();
			} else if (ADT.get() < delay + time1) {
				DT.drivetrain.arcadeDrive(speed, 0);
			} else {
				DT.drivetrain.stopMotor();
			}
		} else if (switchLoc == 'R') { //If switch is on right side
			if (ADT.get() < delay) {
				DT.drivetrain.stopMotor();
			} else if (ADT.get() < delay + time1) {
				DT.drivetrain.arcadeDrive(speed, 0);
			} else if (ADT.get() < delay + time1 + turn1) {
				DT.drivetrain.arcadeDrive(0, -turnspeed);
			} else if (ADT.get() < delay + time1 + turn1 + time2) {
				DT.drivetrain.arcadeDrive(speed, 0);
			} else {
				DT.drivetrain.stopMotor();
			}
		} else { //If there is an error
			DashboardData.AddGameError("Did not recieve switch location");
			DT.drivetrain.stopMotor();
		}
	}
	
	/**
	Default autonomous (encoder based)
	*/
	private static void EncDefault() {
		if (Robot.drivetrain.encLeft.getDistance() < distance1) {
			DT.drivetrain.arcadeDrive(speed, 0);
		} else {
			DT.drivetrain.stopMotor();
		}
	}
	
	/**
	Left autonomous (encoder based)
	*/
	private static void EncLeft() {
		if (switchLoc == 'L') { //If switch is on left side
			//Put Code Here
		} else if (switchLoc == 'R') { //If switch is on right side
			//Put Code Here
		} else { //If there is an error
			DashboardData.AddGameError("Did not recieve switch location");
			DT.drivetrain.stopMotor();
		}
	}
	
	/**
	Center autonomous (encoder based)
	*/
	private static void EncCenter() {
		if (switchLoc == 'L') { //If switch is on left side
			//Put Code Here
		} else if (switchLoc == 'R') { //If switch is on right side
			//Put Code Here
		} else { //If there is an error
			DashboardData.AddGameError("Did not recieve switch location");
			DT.drivetrain.stopMotor();
		}
	}
	
	/**
	Right autonomous (encoder based)
	*/
	private static void EncRight() {
		if (switchLoc == 'L') { //If switch is on left side
			//Put Code Here
		} else if (switchLoc == 'R') { //If switch is on right side
			//Put Code Here
		} else { //If there is an error
			DashboardData.AddGameError("Did not recieve switch location");
			DT.drivetrain.stopMotor();
		}
	}
	
}
