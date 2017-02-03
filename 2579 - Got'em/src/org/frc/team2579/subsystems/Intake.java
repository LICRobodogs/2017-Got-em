package org.frc.team2579.subsystems;

import edu.rhhs.frc.RobotMain;
import edu.rhhs.frc.RobotMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	public static enum LiftState {
		UP, DOWN
	};

	public static final double OUTER_INTAKE_LOAD_SPEED = 1.0;
	public static final double INNER_INTAKE_LOAD_SPEED = 0.8;
	public static final double OUTER_INTAKE_EJECT_SPEED = -1.0;
	public static final double INNER_INTAKE_EJECT_SPEED = -1.0;

	private Solenoid outerLift, innerLift;
	private CANTalon outerRoller, innerRoller;

	public Intake() {
		try {
			outerRoller = new CANTalon(
					RobotMap.INTAKE_OUTER_ROLLER_MOTOR_CAN_ID);
			innerRoller = new CANTalon(
					RobotMap.INTAKE_INNER_ROLLER_MOTOR_CAN_ID);

			outerRoller.enableBrakeMode(true);
			innerRoller.enableBrakeMode(true);

			outerLift = new Solenoid(RobotMap.INTAKE_OUTER_LIFT_PCM_ID);
			innerLift = new Solenoid(RobotMap.INTAKE_INNER_LIFT_PCM_ID);
		} catch (Exception e) {
			System.err.println("An error occurred in the Intake constructor");
		}
	}

	public void setSpeedOuter(double speed) {
		outerRoller.set(-speed);
	}

	public void setSpeedInner(double speed) {
		innerRoller.set(speed);
	}

	public void setPositionOuter(LiftState state) {
		// Add implementation
		if (state == LiftState.UP) {
			outerLift.set(false);
		} else if (state == LiftState.DOWN) {
			outerLift.set(true);
		}
	}

	public void setPositionInner(LiftState state) {
		if (state == LiftState.DOWN) {
			innerLift.set(false);
		} else if (state == LiftState.UP) {
			innerLift.set(true);
		}
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void updateStatus(RobotMain.OperationMode operationMode) {
		if (operationMode == RobotMain.OperationMode.TEST) {
		}
	}
}