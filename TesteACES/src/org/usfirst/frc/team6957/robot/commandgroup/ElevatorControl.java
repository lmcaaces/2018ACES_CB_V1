package org.usfirst.frc.team6957.robot.commandgroup;

import org.usfirst.frc.team6957.robot.commands.ElevatorControlWithJoystick;
import org.usfirst.frc.team6957.robot.commands.ElevatorLockUpDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElevatorControl extends CommandGroup {

    public ElevatorControl() {
        addSequential(new ElevatorLockUpDown());
        addParallel(new ElevatorControlWithJoystick());
    }
}
