package org.usfirst.frc.team6957.robot.commandgroup;

import org.usfirst.frc.team6957.robot.commands.AutoTurnGyro;
import org.usfirst.frc.team6957.robot.commands.DriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousDefault extends CommandGroup {

    public AutonomousDefault() {
    	addSequential(new AutoTurnGyro(90));
    	addSequential(new AutoTurnGyro(-90));
    	addSequential(new AutoTurnGyro(90));
    	addSequential(new AutoTurnGyro(-90));
    	addSequential(new AutoTurnGyro(90));
    	addSequential(new AutoTurnGyro(-90));
    }
}
