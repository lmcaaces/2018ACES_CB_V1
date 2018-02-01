package org.usfirst.frc.team6957.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

class DashboardData {

	
	public DashboardData() {
	}
	
	public void DisabledDash() {
	}
	
	public void TeleopDash() {
		SmartDashboard.putBoolean("Elevator is High", Robot.elevator.elevatorHigh());
		SmartDashboard.putBoolean("Elevator is Low", Robot.elevator.elevatorLow());
	}
	
}
