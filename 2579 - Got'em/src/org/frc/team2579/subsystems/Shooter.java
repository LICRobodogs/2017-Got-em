package org.frc.team2579.subsystems;

import org.frc.team2579.Robot;
import org.frc.team2579.RobotMap;
import org.frc.team2579.subsystems.Intake;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {

	private CANTalon wheel;

	public Shooter() {
		try {
			wheel = new CANTalon(RobotMap.SHOOTER_MOTOR_CAN_ID);
			wheel.enableBrakeMode(false);
			wheel.reverseSensor(true);

		} catch (Exception e) {
			System.err.println("An error occurred in the Shooter constructor");
		}
	}

	public boolean getBallStopIn(){
		return Intake.getBallStop();
	}

	public void setWheelSpeed(double speed) {
		wheel.set(-speed);
	}

	public void resetWheelEncoder() {
		wheel.setPosition(0);
	}

	public double getWheelVelocity() {
		return wheel.getEncVelocity();
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void updateStatus(Robot.OperationMode operationMode) {
		SmartDashboard.putNumber("Shooter Speed: ", getWheelVelocity());
		if (operationMode == Robot.OperationMode.TEST) {
		}
	}
}