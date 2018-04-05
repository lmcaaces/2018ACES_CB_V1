package org.usfirst.frc.team6957.robot.commands;

import org.usfirst.frc.team6957.robot.OI;
import org.usfirst.frc.team6957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorLockUpDown extends Command {

	
    public ElevatorLockUpDown() {
        requires(Robot.elevator);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.elevetorLockUpDown(OI.getOperator());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
}
