package org.frc.team2579.subsystems;

import org.frc.team2579.utility.CANTalonEncoder;

import edu.rhhs.frc.RobotMain;
import edu.rhhs.frc.RobotMap;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {
	public static enum CarriageState {
		RELEASED, LOCKED
	};

	public static enum ShotPosition {
		SHORT, LONG
	};

	private static final double ENCODER_TICKS_TO_WORLD = 4096 / (0.8 * Math.PI)
			* (54.0 / 11.0);
	public static final double WINCH_SAFE_RELEASE_SPEED = 0.5;
	public static final double WINCH_RETRACT_SPEED = 1.0;
	public static final double WINCH_SPOOLOUT_SPEED = -1.0;
	public static final double MAX_WINCH_CURRENT = 50.0;
	public static final double SAFE_RELEASE_WINCH_CURRENT = 10.0;
	public static final double WINCH_SPOOLOUT_DISTANCE = -9.3;

	private CANTalonEncoder winch;
	private Solenoid shotPosition, carriageRelease;
	private DigitalInput carriageRetracted;

	public Shooter() {
		try {
			winch = new CANTalonEncoder(RobotMap.SHOOTER_WINCH_MOTOR_CAN_ID,
					ENCODER_TICKS_TO_WORLD, FeedbackDevice.QuadEncoder);
			winch.enableBrakeMode(true);
			winch.reverseSensor(true);

			shotPosition = new Solenoid(RobotMap.SHOOTER_POSITION_PCM_ID);
			carriageRelease = new Solenoid(RobotMap.CARRIAGE_RELEASE_PCM_ID);

			carriageRetracted = new DigitalInput(
					RobotMap.CARRIAGE_RETRACTED_DIO_PORT_ID);
		} catch (Exception e) {
			System.err.println("An error occurred in the Shooter constructor");
		}
	}

	public boolean isCarriageRetracted() {
		return !carriageRetracted.get();
	}

	public boolean isWinchCurrentAtMax() {
		return winch.getOutputCurrent() > MAX_WINCH_CURRENT;
	}

	public boolean isWinchCurrentAtSafeRelease() {
		return winch.getOutputCurrent() > SAFE_RELEASE_WINCH_CURRENT;
	}

	public void setWinchSpeed(double speed) {
		winch.set(-speed);
	}

	public void resetWinchEncoder() {
		winch.setPosition(0);
	}

	public boolean isWinchSpooledOut() {
		return winch.getPositionWorld() < WINCH_SPOOLOUT_DISTANCE;
	}

	public void setCarriageRelease(CarriageState state) {
		if (state == CarriageState.LOCKED) {
			carriageRelease.set(false);
		} else if (state == CarriageState.RELEASED) {
			carriageRelease.set(true);
		}
	}

	public void setShotPosition(ShotPosition position) {
		if (position == ShotPosition.LONG) {
			shotPosition.set(true);
		} else if (position == ShotPosition.SHORT) {
			shotPosition.set(false);
		}
	}

	public double getWinchPositionWorld() {
		return winch.getPositionWorld();
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void updateStatus(RobotMain.OperationMode operationMode) {
		SmartDashboard.putBoolean("Winch Retracted", isCarriageRetracted());
		if (operationMode == RobotMain.OperationMode.TEST) {
			SmartDashboard.putNumber("Winch Pos", winch.getPositionWorld());
			SmartDashboard.putNumber("Winch Amps", winch.getOutputCurrent());
			SmartDashboard.putBoolean("Winch Max Amps", isWinchCurrentAtMax());
		}
	}
}