/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6957.robot;

import org.usfirst.frc.team6957.robot.commands.AutoTurn;
import org.usfirst.frc.team6957.robot.commands.AutoTurnGyro;
import org.usfirst.frc.team6957.robot.commands.CollectCube;
import org.usfirst.frc.team6957.robot.commands.DriveStraight;
import org.usfirst.frc.team6957.robot.commands.EjectCube;
import org.usfirst.frc.team6957.robot.commands.FixCube;
import org.usfirst.frc.team6957.robot.commands.SetElevatorClimb;
import org.usfirst.frc.team6957.robot.commands.SetElevatorPickup;
import org.usfirst.frc.team6957.robot.commands.SetElevatorSwitch;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static XboxController driver = new XboxController(0);
	public static XboxController operator = new XboxController(1);
	
	public OI(){
		
		//Driver Controller
		//Button driver_A = new JoystickButton(driver, 1);
		Button driver_B = new JoystickButton(driver, 2);
		Button driver_X = new JoystickButton(driver, 3);
		Button driver_Y = new JoystickButton(driver, 4);
		//Button driver_lb = new JoystickButton(driver, 5);
		//Button driver_rb = new JoystickButton(driver, 6);
		//Button driver_select = new JoystickButton(driver, 7);
		//Button driver_start = new JoystickButton(driver, 8);
		//Button driver_ljb = new JoystickButton(driver, 9);
		//Button driver_rjb = new JoystickButton(driver, 10);
		
		//Operator Controller
		Button operator_A = new JoystickButton(operator, 1);
		Button operator_B = new JoystickButton(operator, 2);
		Button operator_X = new JoystickButton(operator, 3);
		Button operator_Y = new JoystickButton(operator, 4);
		Button operator_lb = new JoystickButton(operator, 5);
		Button operator_rb = new JoystickButton(operator, 6);
		//Button operator_select = new JoystickButton(operator, 7);
		//Button operator_start = new JoystickButton(operator, 8);
		//Button operator_ljb = new JoystickButton(operator, 9);
		//Button operator_rjb = new JoystickButton(operator, 10);
		
		operator_X.whileHeld(new FixCube());
		operator_rb.whileHeld(new CollectCube()); // Collector on
		operator_lb.whileHeld(new EjectCube()); // Shooter on
		operator_A.whenPressed(new SetElevatorPickup()); // Get elevator ready to pick up cubes
		operator_B.whenPressed(new SetElevatorSwitch()); // Get elevator ready to score cubes in the switch
		operator_Y.whenPressed(new SetElevatorClimb()); // Get elevator ready to climb
		//driver_A.whenPressed(new ResetGyro());
		driver_B.whenPressed(new AutoTurn(90, 0.7));
		driver_X.whenPressed(new AutoTurn(-90, 0.7));
		driver_Y.whenPressed(new DriveStraight(3, 0.7));
	}
	
	public String getDriverStationData() {
		return DriverStation.getInstance().getGameSpecificMessage();
		
	}
	
	public char getSwitchLoc() {
		String gamedata = getDriverStationData();
		return gamedata.charAt(0);
	}
	
	public char getScaleLoc() {
		String gamedata = getDriverStationData();
		return gamedata.charAt(1);
	}
	
	/**Returns Driver Controller*/
	public static XboxController getDriver() {
		return driver;
	}
	
	/**Returns Operator Controller*/
	public static XboxController getOperator() {
		return operator;
	}
	
}
