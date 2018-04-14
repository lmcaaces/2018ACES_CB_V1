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
	//private static double turn3 = DashboardData.turn3;
	private static double distance1 = DashboardData.distance1;
	private static double distance2 = DashboardData.distance2;
	private static double distance3 = DashboardData.distance3;
	private static double time1 = DashboardData.time1;
	private static double time2 = DashboardData.time2;
	private static double time3 = DashboardData.time3;
	public static Timer autoDriveTimer = new Timer();
	public static Timer autoShootTimer = new Timer();
	private static Timer ADT = autoDriveTimer;
	private static Timer AST = autoShootTimer;
	private static char switchLoc = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
	private static double stage = 1;
	private static double ejectTime = 3;
	private static boolean timerStart;
	private static double ejectDelay = 0.5;
	
	public AutonomousModes() {}
	
	/**
	Resets and starts autonomous timers
	 */
	public static void AutonomousInit() {
		switchLoc = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
		mode = DashboardData.autoMode;
		enc = DashboardData.autoType;
		delay = DashboardData.autoDelay;
		speed = DashboardData.autoSpeed;
		turnspeed = DashboardData.autoTurnSpeed;
		turn1 = DashboardData.turn1;
		turn2 = DashboardData.turn2;
		//turn3 = DashboardData.turn3;
		distance1 = DashboardData.distance1;
		distance2 = DashboardData.distance2;
		distance3 = DashboardData.distance3;
		time1 = DashboardData.time1;
		time2 = DashboardData.time2;
		time3 = DashboardData.time3;
		ejectTime = 1;
		Robot.elevator.elevatorUpDown(0);
		Robot.intake.startIntake(0);
		stage = 1;
		autoShootTimer.reset();
		autoDriveTimer.reset();
		autoDriveTimer.start();
		timerStart = true;
	}
	
	/**
	Chooses an autonomous to run based on auto mode
	*/
	public static void Autonomous() {
		switch (mode.charAt(0)) {
			default:
				Default();
			case 'D': //Default
				if (enc) {
					EncDefault(); //With Encoders
				} else {
					Default(); //Time based
				}
				break;
			case 'L': //Left
				if (enc) {
					EncLeft(); //With Encoders
				} else {
					Left(); //Time based
				}
				break;
			case 'C': //Center
				if (enc) {
					EncCenter(); //With Encoders
				} else {
					Center(); //Time based
				}
				break;
			case 'R': //Right
				if (enc) {
					EncRight(); //With Encoders
				} else {
					Right(); //Time based
				}
				break;
			case 'd': //Default
				if (enc) {
					EncDefault(); //With Encoders
				} else {
					Default(); //Time based
				}
				break;
			case 'l': //Left
				if (enc) {
					EncLeft(); //With Encoders
				} else {
					Left(); //Time based
				}
				break;
			case 'c': //Center
				if (enc) {
					EncCenter(); //With Encoders
				} else {
					Center(); //Time based
				}
				break;
			case 'r': //Right
				if (enc) {
					EncRight(); //With Encoders
				} else {
					Right(); //Time based
				}
				break;
		}
	}
	
	/**
	Default autonomous (time based)
	*/
	private static void Default() {
		//Drive
		if (ADT.get() < delay) {
			DriveTrain.drivetrain.stopMotor();
			if (ADT.get() < 1) {
				Robot.elevator.elevatorUpDown(-1.0);
			} else {
				Robot.elevator.elevatorUpDown(0);
			}
		} else if (ADT.get() < delay + 0.8) {
			DriveTrain.drivetrain.arcadeDrive(1.0, 0);
			if (ADT.get() < 1) {
				Robot.elevator.elevatorUpDown(-1.0);
			} else {
				Robot.elevator.elevatorUpDown(0);
			}
		} else {
			DriveTrain.drivetrain.stopMotor();
			if (ADT.get() < 1) {
				Robot.elevator.elevatorUpDown(-1.0);
			} else {
				Robot.elevator.elevatorUpDown(0);
			}
		}
	}
	
	/**
	Left autonomous (time based)
	*/
	private static void Left() {
		if (switchLoc == 'L') { //If switch is on left side
			if (ADT.get() < delay) {
				DriveTrain.drivetrain.stopMotor();
				Robot.intake.startIntake(0.4);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1) {
				DriveTrain.drivetrain.arcadeDrive(speed, 0);
				Robot.intake.startIntake(0.4);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn1) {
				DriveTrain.drivetrain.arcadeDrive(0, turnspeed);
				Robot.intake.startIntake(0.4);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn1 + time2) {
				DriveTrain.drivetrain.arcadeDrive(speed, 0);
				Robot.intake.startIntake(0.4);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else {
				DriveTrain.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
					if (timerStart) {
						AST.start();
						timerStart = false;
					}
					if (AST.get() < ejectDelay) {
						Robot.intake.stopIntake();
					} else if (AST.get() < ejectDelay + ejectTime) {
						Robot.intake.startIntake(-1.0);
					} else if (AST.get() >= ejectDelay + ejectTime) {
						Robot.intake.stopIntake();
					} else {
						Robot.intake.stopIntake();
					}
				}
			}
		} else if (switchLoc == 'R') { //If switch is on right side
			if (ADT.get() < delay) {
				DriveTrain.drivetrain.stopMotor();
				Robot.intake.startIntake(0.4);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1) {
				DriveTrain.drivetrain.arcadeDrive(speed, 0);
				Robot.intake.startIntake(0.4);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else {
				DriveTrain.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			}
		} else { //If there is an error
			DashboardData.AddGameError("Did not recieve switch location");
			DriveTrain.drivetrain.stopMotor();
		}
	}
	
	/**
	Center autonomous (time based)
	*/
	private static void Center() {
		if (switchLoc == 'L') { //If switch is on left side
			if (ADT.get() < delay) {
				DriveTrain.drivetrain.stopMotor();
				Robot.intake.startIntake(0.2);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1) {
				DriveTrain.drivetrain.arcadeDrive(speed, 0);
				Robot.intake.startIntake(0.2);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn1) {
				DriveTrain.drivetrain.arcadeDrive(0, -turnspeed);
				Robot.intake.startIntake(0.2);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn1 + time2) {
				DriveTrain.drivetrain.arcadeDrive(speed, 0);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn1 + time2 + turn2) {
				DriveTrain.drivetrain.arcadeDrive(0, turnspeed);
				Robot.intake.startIntake(0.2);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn1 + time2 + turn2 + distance1) {
				DriveTrain.drivetrain.arcadeDrive(speed, 0);
				Robot.intake.startIntake(0.2);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else {
				DriveTrain.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
					if (timerStart) {
						AST.start();
						timerStart = false;
					}
					if (AST.get() < ejectDelay) {
						Robot.intake.stopIntake();
					} else if (AST.get() < ejectDelay + ejectTime) {
						Robot.intake.startIntake(-1.0);
					} else if (AST.get() >= ejectDelay + ejectTime) {
						Robot.intake.stopIntake();
					} else {
						Robot.intake.stopIntake();
					}
				}
			}
		} else if (switchLoc == 'R') { //If switch is on right side
			if (ADT.get() < delay) {
				DriveTrain.drivetrain.stopMotor();
				Robot.intake.startIntake(0.2);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1) {
				DriveTrain.drivetrain.arcadeDrive(speed, 0);
				Robot.intake.startIntake(0.2);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn1) {
				DriveTrain.drivetrain.arcadeDrive(0, turnspeed);
				Robot.intake.startIntake(0.2);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn1 + time2) {
				DriveTrain.drivetrain.arcadeDrive(speed, 0);
				Robot.intake.startIntake(0.2);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn2 + time2 + turn2) {
				DriveTrain.drivetrain.arcadeDrive(0, -turnspeed);
				Robot.intake.startIntake(0.2);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + time1 + turn2 + time2 + turn2 + distance1) {
				DriveTrain.drivetrain.arcadeDrive(speed, 0);
				Robot.intake.startIntake(0.2);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else {
				DriveTrain.drivetrain.stopMotor();
				Robot.elevator.elevatorLockUp(0);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
					if (timerStart) {
						AST.start();
						timerStart = false;
					}
					if (AST.get() < ejectDelay) {
						Robot.intake.stopIntake();
					} else if (AST.get() < ejectDelay + ejectTime) {
						Robot.intake.startIntake(-1.0);
					} else if (AST.get() >= ejectDelay + ejectTime) {
						Robot.intake.stopIntake();
					} else {
						Robot.intake.stopIntake();
					}
				}
			}
		} else { //If there is an error
			DashboardData.AddGameError("Did not recieve switch location");
			DriveTrain.drivetrain.stopMotor();
		}
	}
	
	/**
	Right autonomous (time based)
	*/
	private static void Right() {
		if (switchLoc == 'L') { //If switch is on left side
			if (ADT.get() < delay) {
				DriveTrain.drivetrain.stopMotor();
				Robot.intake.startIntake(0.2);
				if (ADT.get() < 1) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + 0.8) {
				DriveTrain.drivetrain.arcadeDrive(-1, 0);
				Robot.intake.startIntake(0.2);
				if (ADT.get() < 1) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else {
				DriveTrain.drivetrain.stopMotor();
				Robot.intake.startIntake(0.2);
				if (ADT.get() < 1) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			}
		} else if (switchLoc == 'R') { //If switch is on right side
			if (ADT.get() < delay) {
				DriveTrain.drivetrain.stopMotor();
				Robot.intake.startIntake(0.2);
				if (ADT.get() < 3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + 1.55) {
				DriveTrain.drivetrain.arcadeDrive(-1.0, 0);
				Robot.intake.startIntake(0.2);
				if (ADT.get() < 3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + 1.55 + 1.25) {
				DriveTrain.drivetrain.arcadeDrive(0, -0.7);
				if (ADT.get() < 3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (ADT.get() < delay + 1.55 + 1.25 + 0.19) {
				DriveTrain.drivetrain.arcadeDrive(-1.0, 0);
				Robot.intake.startIntake(0.2);
				if (ADT.get() < 3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else {
				DriveTrain.drivetrain.stopMotor();
				if (ADT.get() < 3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
					if (timerStart) {
						AST.start();
						timerStart = false;
					}
					if (AST.get() < ejectDelay) {
						Robot.intake.stopIntake();
					} else if (AST.get() < ejectDelay + ejectTime) {
						Robot.intake.startIntake(-1.0);
					} else if (AST.get() >= ejectDelay + ejectTime) {
						Robot.intake.stopIntake();
					} else {
						Robot.intake.stopIntake();
					}
				}
			}
		} else { //If there is an error
			DashboardData.AddGameError("Did not recieve switch location");
			DriveTrain.drivetrain.stopMotor();
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
				Robot.elevator.elevatorUpDown(-1.0);
			} else {
				Robot.elevator.elevatorUpDown(0);
			}
		} else if (stage == 2) {
			if (Robot.drivetrain.encLeft.getDistance() < distance1) {
				DriveTrain.drivetrain.arcadeDrive(speed, 0);
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else {
				DriveTrain.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
					if (timerStart) {
						AST.start();
						timerStart = false;
					}
					if (AST.get() < ejectDelay) {
						Robot.intake.stopIntake();
					} else if (AST.get() < ejectDelay + ejectTime) {
						Robot.intake.startIntake(-1.0);
					} else if (AST.get() >= ejectDelay + ejectTime) {
						Robot.intake.stopIntake();
					} else {
						Robot.intake.stopIntake();
					}
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
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (stage == 2) {
				Autonomous.DriveDistance(distance1, speed);
				stage = 3;
			} else if (stage == 3) {
				Autonomous.TurnRight(90, speed);
				stage = 4;
			} else if (stage == 4) {
				DriveTrain.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
					if (timerStart) {
						AST.start();
						timerStart = false;
					}
					if (AST.get() < ejectDelay) {
						Robot.intake.stopIntake();
					} else if (AST.get() < ejectDelay + ejectTime) {
						Robot.intake.startIntake(-1.0);
					} else if (AST.get() >= ejectDelay + ejectTime) {
						Robot.intake.stopIntake();
					} else {
						Robot.intake.stopIntake();
					}
				}
			} else {
				DriveTrain.drivetrain.stopMotor();
				DashboardData.AddAutoError("Incorrect Auto Stage");
			}
		} else if (switchLoc == 'R') { //If switch is on right side
			if (stage == 1) {
				if (ADT.get() >= delay) {
					stage = 2;
				}
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (stage == 2) {
				Autonomous.DriveDistance(distance1, speed);
				stage = 3;
			} else if (stage == 3) {
				DriveTrain.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
					if (timerStart) {
						AST.start();
						timerStart = false;
					}
					if (AST.get() < ejectDelay) {
						Robot.intake.stopIntake();
					} else if (AST.get() < ejectDelay + ejectTime) {
						Robot.intake.startIntake(-1.0);
					} else if (AST.get() >= ejectDelay + ejectTime) {
						Robot.intake.stopIntake();
					} else {
						Robot.intake.stopIntake();
					}
				}
			} else {
				DriveTrain.drivetrain.stopMotor();
				DashboardData.AddAutoError("Incorrect Auto Stage");
			}
		} else { //If there is an error
			DashboardData.AddGameError("Did not recieve switch location");
			DriveTrain.drivetrain.stopMotor();
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
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
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
				DriveTrain.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
					if (timerStart) {
						AST.start();
						timerStart = false;
					}
					if (AST.get() < ejectDelay) {
						Robot.intake.stopIntake();
					} else if (AST.get() < ejectDelay + ejectTime) {
						Robot.intake.startIntake(-1.0);
					} else if (AST.get() >= ejectDelay + ejectTime) {
						Robot.intake.stopIntake();
					} else {
						Robot.intake.stopIntake();
					}
				}
			} else {
				DriveTrain.drivetrain.stopMotor();
				DashboardData.AddAutoError("Incorrect Auto Stage");
			}
		} else if (switchLoc == 'R') { //If switch is on right side
			if (stage == 1) {
				if (ADT.get() >= delay) {
					stage = 2;
				}
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
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
				DriveTrain.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
					if (timerStart) {
						AST.start();
						timerStart = false;
					}
					if (AST.get() < ejectDelay) {
						Robot.intake.stopIntake();
					} else if (AST.get() < ejectDelay + ejectTime) {
						Robot.intake.startIntake(-1.0);
					} else if (AST.get() >= ejectDelay + ejectTime) {
						Robot.intake.stopIntake();
					} else {
						Robot.intake.stopIntake();
					}
				}
			} else {
				DriveTrain.drivetrain.stopMotor();
				DashboardData.AddAutoError("Incorrect Auto Stage");
			}
		} else { //If there is an error
			DashboardData.AddGameError("Did not recieve switch location");
			DriveTrain.drivetrain.stopMotor();
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
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (stage == 2) {
				Autonomous.DriveDistance(distance1, speed);
				stage = 3;
			} else if (stage == 3) {
				DriveTrain.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
					if (timerStart) {
						AST.start();
						timerStart = false;
					}
					if (AST.get() < ejectDelay) {
						Robot.intake.stopIntake();
					} else if (AST.get() < ejectDelay + ejectTime) {
						Robot.intake.startIntake(-1.0);
					} else if (AST.get() >= ejectDelay + ejectTime) {
						Robot.intake.stopIntake();
					} else {
						Robot.intake.stopIntake();
					}
				}
			} else {
				DriveTrain.drivetrain.stopMotor();
				DashboardData.AddAutoError("Incorrect Auto Stage");
			}
		} else if (switchLoc == 'R') { //If switch is on right side
			if (stage == 1) {
				if (ADT.get() >= delay) {
					stage = 2;
				}
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
				}
			} else if (stage == 2) {
				Autonomous.DriveDistance(distance1, speed);
				stage = 3;
			} else if (stage == 3) {
				Autonomous.TurnLeft(90, speed);
				stage = 4;
			} else if (stage == 4) {
				DriveTrain.drivetrain.stopMotor();
				if (ADT.get() < time3) {
					Robot.elevator.elevatorUpDown(-1.0);
				} else {
					Robot.elevator.elevatorUpDown(0);
					if (timerStart) {
						AST.start();
						timerStart = false;
					}
					if (AST.get() < ejectDelay) {
						Robot.intake.stopIntake();
					} else if (AST.get() < ejectDelay + ejectTime) {
						Robot.intake.startIntake(-1.0);
					} else if (AST.get() >= ejectDelay + ejectTime) {
						Robot.intake.stopIntake();
					} else {
						Robot.intake.stopIntake();
					}
				}
			} else {
				DriveTrain.drivetrain.stopMotor();
				DashboardData.AddAutoError("Incorrect Auto Stage");
			}
		} else { //If there is an error
			DashboardData.AddGameError("Did not recieve switch location");
			DriveTrain.drivetrain.stopMotor();
		}
	}
	
}
