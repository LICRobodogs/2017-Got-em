package org.frc.team2579.subsystems;

import org.frc.team2579.RobotMain;
import org.frc.team2579.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {

	private static final double ENCODER_TICKS_TO_WORLD = 4096 / (0.8 * Math.PI)
			* (54.0 / 11.0);
	public static final double WINCH_SAFE_RELEASE_SPEED = 0.5;
	public static final double WINCH_RETRACT_SPEED = 1.0;
	public static final double WINCH_SPOOLOUT_SPEED = -1.0;
	public static final double MAX_WINCH_CURRENT = 50.0;
	public static final double SAFE_RELEASE_WINCH_CURRENT = 10.0;
	public static final double WINCH_SPOOLOUT_DISTANCE = -9.3;

	private CANTalon wheel;
	private Solenoid shotPosition, carriageRelease;

	public Shooter() {
		try {
			wheel = new CANTalon(RobotMap.SHOOTER_MOTOR_CAN_ID);
			wheel.enableBrakeMode(false);
			wheel.reverseSensor(true);

			shotPosition = new Solenoid(RobotMap.SHOOTER_POSITION_PCM_ID);

		} catch (Exception e) {
			System.err.println("An error occurred in the Shooter constructor");
		}
	}


	public void setWheelSpeed(double speed) {
		wheel.set(-speed);
	}

	public void resetWinchEncoder() {
		wheel.setPosition(0);
	}

	public double getWheelVelocity() {
		return wheel.getEncVelocity();
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void updateStatus(RobotMain.OperationMode operationMode) {
		SmartDashboard.putNumber("Winch Retracted", getWheelVelocity());
		if (operationMode == RobotMain.OperationMode.TEST) {
		}
	}
}