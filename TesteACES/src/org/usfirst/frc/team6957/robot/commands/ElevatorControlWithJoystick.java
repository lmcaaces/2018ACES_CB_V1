package org.usfirst.frc.team6957.robot.commands;

import org.usfirst.frc.team6957.robot.OI;
import org.usfirst.frc.team6957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
Operates Elevator with Joysticks
 */
public class ElevatorControlWithJoystick extends Command {
	public ElevatorControlWithJoystick() {
        requires(Robot.elevator);
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (OI.getOperator().getRawAxis(1) < 0 && !Robot.elevator.elevatorHigh()) {
    		Robot.elevator.elevatorUpJoystick(OI.getOperator());
    	} else if (OI.getOperator().getRawAxis(1) > 0 && !Robot.elevator.elevatorLow()) {
    		Robot.elevator.elevatorDownJoystick(OI.getOperator());
    	} else {
    		Robot.elevator.stopElevator();
    	}	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stopElevator();
    }
}
