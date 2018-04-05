package org.usfirst.frc.team6957.robot.commands;

import org.usfirst.frc.team6957.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousElevatorUp extends Command {

	Timer time = new Timer();
	private boolean finished;
	private double dur;
	private double spd;
	
    public AutonomousElevatorUp(double duration, double speed) {
        requires(Robot.elevator);
        dur = duration;
        spd = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time.reset();
    	time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (time.get() < dur) {
    		Robot.elevator.elevatorUpDown(spd);
    	} else {
    		Robot.elevator.stopElevator();
    		finished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }
}
