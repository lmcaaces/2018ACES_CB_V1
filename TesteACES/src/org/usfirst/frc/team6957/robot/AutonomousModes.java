package org.usfirst.frc.team6957.robot;

import org.usfirst.frc.team6957.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6957.robot.subsystems.Elevator;
import org.usfirst.frc.team6957.robot.subsystems.Intake;

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
	//private static double turn3 = DashboardData.turn3;
	private static double distance1 = DashboardData.distance1;
	private static double distance2 = DashboardData.distance2;
	private static double distance3 = DashboardData.distance3;
	private static double time1 = DashboardData.time1;
	private static double time2 = DashboardData.time2;
	private static double time3 = DashboardData.time3;
	public static Timer autoDriveTimer = new Timer();
	public static Timer autoElevTimer = new Timer();
	private static Timer ADT = autoDriveTimer;
	private static DriveTrain DT = new DriveTrain();
	private static Elevator EL = new Elevator();
	private static Intake IN = new Intake();
	private static char switchLoc = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
	private static double stage = 1;
	private static double ejectTime = 3;
	
	public AutonomousModes() {}
	
	/**
	Resets and starts autonomous timers
	 */
	public static void AutonomousInit() {
		stage = 1;
		autoDriveTimer.reset();
		autoDriveTimer.start();
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
			if (ADT.get() < time3) {
				EL.elevatorUpDown(1.0);
			} else {
				EL.elevatorUpDown(0);
			}
		} else if (ADT.get() < delay + time1) {
			DT.drivetrain.arcadeDrive(speed, 0);
			if (ADT.get() < time3) {
				EL.elevatorUpDown(1.0);
			} else {
				EL.elevatorUpDown(0);
			}
		} else {
			DT.drivetrain.stopMotor();
			if (ADT.get() < time3) {
				EL.elevatorUpDown(1.0);
			} else if (ADT.get() < time3 + ejectTime) {
				EL.elevatorUpDown(0);
				IN.startIntake(-1.0);
			} else {
				IN.startIntake(0);
			}
		}
	}
	
	/**
	Left autonomous (time based)
	*/
	private static void Left() {
		if (switchLoc == 'L') { //If switch is on left side
			if (ADT.get() < delay) {
				DT.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1) {
				DT.drivetrain.arcadeDrive(speed, 0);
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn1) {
				DT.drivetrain.arcadeDrive(0, turnspeed);
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn1 + time2) {
				DT.drivetrain.arcadeDrive(speed, 0);
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else {
				DT.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else if (ADT.get() < time3 + ejectTime) {
					EL.elevatorUpDown(0);
					IN.startIntake(-1.0);
				} else {
					IN.startIntake(0);
				}
			}
		} else if (switchLoc == 'R') { //If switch is on right side
			if (ADT.get() < delay) {
				DT.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1) {
				DT.drivetrain.arcadeDrive(speed, 0);
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else {
				DT.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else if (ADT.get() < time3 + ejectTime) {
					EL.elevatorUpDown(0);
					IN.startIntake(-1.0);
				} else {
					IN.startIntake(0);
				}
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
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1) {
				DT.drivetrain.arcadeDrive(speed, 0);
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn1) {
				DT.drivetrain.arcadeDrive(0, -turnspeed);
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn1 + time2) {
				DT.drivetrain.arcadeDrive(speed, 0);
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn2 + time2 + turn2) {
				DT.drivetrain.arcadeDrive(0, -turnspeed);
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else {
				DT.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else if (ADT.get() < time3 + ejectTime) {
					EL.elevatorUpDown(0);
					IN.startIntake(-1.0);
				} else {
					IN.startIntake(0);
				}
			}
		} else if (switchLoc == 'R') { //If switch is on right side
			if (ADT.get() < delay) {
				DT.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1) {
				DT.drivetrain.arcadeDrive(speed, 0);
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn1) {
				DT.drivetrain.arcadeDrive(0, turnspeed);
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn1 + time2) {
				DT.drivetrain.arcadeDrive(speed, 0);
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn2 + time2 + turn2) {
				DT.drivetrain.arcadeDrive(0, -turnspeed);
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else {
				DT.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else if (ADT.get() < time3 + ejectTime) {
					EL.elevatorUpDown(0);
					IN.startIntake(-1.0);
				} else {
					IN.startIntake(0);
				}
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
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1) {
				DT.drivetrain.arcadeDrive(speed, 0);
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else {
				DT.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else if (ADT.get() < time3 + ejectTime) {
					EL.elevatorUpDown(0);
					IN.startIntake(-1.0);
				} else {
					IN.startIntake(0);
				}
			}
		} else if (switchLoc == 'R') { //If switch is on right side
			if (ADT.get() < delay) {
				DT.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1) {
				DT.drivetrain.arcadeDrive(speed, 0);
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn1) {
				DT.drivetrain.arcadeDrive(0, -turnspeed);
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn1 + time2) {
				DT.drivetrain.arcadeDrive(speed, 0);
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else {
				DT.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else if (ADT.get() < time3 + ejectTime) {
					EL.elevatorUpDown(0);
					IN.startIntake(-1.0);
				} else {
					IN.startIntake(0);
				}
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
		if (stage == 1) {
			if (ADT.get() >= delay) {
				stage = 2;
			}
			if (ADT.get() < time3) {
				EL.elevatorUpDown(1.0);
			} else {
				EL.elevatorUpDown(0);
			}
		} else if (stage == 2) {
			if (Robot.drivetrain.encLeft.getDistance() < distance1) {
				DT.drivetrain.arcadeDrive(speed, 0);
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else {
				DT.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else if (ADT.get() < time3 + ejectTime) {
					EL.elevatorUpDown(0);
					IN.startIntake(-1.0);
				} else {
					IN.startIntake(0);
				}
			}
		}
	}
	
	/**
	Left autonomous (encoder based)
	*/
	private static void EncLeft() {
		if (switchLoc == 'L') { //If switch is on left side
			if (stage == 1) {
				if (ADT.get() >= delay) {
					stage = 2;
				}
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (stage == 2) {
				Autonomous.DriveDistance(distance1, speed);
				stage = 3;
			} else if (stage == 3) {
				Autonomous.TurnRight(90, speed);
				stage = 4;
			} else if (stage == 4) {
				DT.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else if (ADT.get() < time3 + ejectTime) {
					EL.elevatorUpDown(0);
					IN.startIntake(-1.0);
				} else {
					IN.startIntake(0);
				}
			} else {
				DT.drivetrain.stopMotor();
				DashboardData.AddAutoError("Incorrect Auto Stage");
			}
		} else if (switchLoc == 'R') { //If switch is on right side
			if (stage == 1) {
				if (ADT.get() >= delay) {
					stage = 2;
				}
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (stage == 2) {
				Autonomous.DriveDistance(distance1, speed);
				stage = 3;
			} else if (stage == 3) {
				DT.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else if (ADT.get() < time3 + ejectTime) {
					EL.elevatorUpDown(0);
					IN.startIntake(-1.0);
				} else {
					IN.startIntake(0);
				}
			} else {
				DT.drivetrain.stopMotor();
				DashboardData.AddAutoError("Incorrect Auto Stage");
			}
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
			if (stage == 1) {
				if (ADT.get() >= delay) {
					stage = 2;
				}
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (stage == 2) {
				Autonomous.DriveDistance(distance1, speed);
				stage = 3;
			} else if (stage == 3) {
				Autonomous.TurnLeft(turn1, speed);
				stage = 4;
			} else if (stage == 4) {
				Autonomous.DriveDistance(distance2, speed);
				stage = 5;
			} else if (stage == 5) {
				Autonomous.TurnRight(turn2, speed);
				stage = 6;
			} else if (stage == 6) {
				Autonomous.DriveDistance(distance3, speed);
				stage = 7;
			} else if (stage == 7) {
				DT.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else if (ADT.get() < time3 + ejectTime) {
					EL.elevatorUpDown(0);
					IN.startIntake(-1.0);
				} else {
					IN.startIntake(0);
				}
			} else {
				DT.drivetrain.stopMotor();
				DashboardData.AddAutoError("Incorrect Auto Stage");
			}
		} else if (switchLoc == 'R') { //If switch is on right side
			if (stage == 1) {
				if (ADT.get() >= delay) {
					stage = 2;
				}
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (stage == 2) {
				Autonomous.DriveDistance(distance1, speed);
				stage = 3;
			} else if (stage == 3) {
				Autonomous.TurnRight(turn1, speed);
				stage = 4;
			} else if (stage == 4) {
				Autonomous.DriveDistance(distance2, speed);
				stage = 5;
			} else if (stage == 5) {
				Autonomous.TurnLeft(turn2, speed);
				stage = 6;
			} else if (stage == 6) {
				Autonomous.DriveDistance(distance3, speed);
				stage = 7;
			} else if (stage == 7) {
				DT.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else if (ADT.get() < time3 + ejectTime) {
					EL.elevatorUpDown(0);
					IN.startIntake(-1.0);
				} else {
					IN.startIntake(0);
				}
			} else {
				DT.drivetrain.stopMotor();
				DashboardData.AddAutoError("Incorrect Auto Stage");
			}
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
			if (stage == 1) {
				if (ADT.get() >= delay) {
					stage = 2;
				}
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (stage == 2) {
				Autonomous.DriveDistance(distance1, speed);
				stage = 3;
			} else if (stage == 3) {
				DT.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else if (ADT.get() < time3 + ejectTime) {
					EL.elevatorUpDown(0);
					IN.startIntake(-1.0);
				} else {
					IN.startIntake(0);
				}
			} else {
				DT.drivetrain.stopMotor();
				DashboardData.AddAutoError("Incorrect Auto Stage");
			}
		} else if (switchLoc == 'R') { //If switch is on right side
			if (stage == 1) {
				if (ADT.get() >= delay) {
					stage = 2;
				}
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else {
					EL.elevatorUpDown(0);
				}
			} else if (stage == 2) {
				Autonomous.DriveDistance(distance1, speed);
				stage = 3;
			} else if (stage == 3) {
				Autonomous.TurnLeft(90, speed);
				stage = 4;
			} else if (stage == 4) {
				DT.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					EL.elevatorUpDown(1.0);
				} else if (ADT.get() < time3 + ejectTime) {
					EL.elevatorUpDown(0);
					IN.startIntake(-1.0);
				} else {
					IN.startIntake(0);
				}
			} else {
				DT.drivetrain.stopMotor();
				DashboardData.AddAutoError("Incorrect Auto Stage");
			}
		} else { //If there is an error
			DashboardData.AddGameError("Did not recieve switch location");
			DT.drivetrain.stopMotor();
		}
	}
	
}
