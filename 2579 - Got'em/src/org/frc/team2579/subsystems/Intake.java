package org.frc.team2579.subsystems;

import org.frc.team2579.Robot;
import org.frc.team2579.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	public static enum BallStopState {
		IN, OUT
	};

	public static final double OUTER_INTAKE_LOAD_SPEED = .75;
	public static final double INNER_INTAKE_LOAD_SPEED = 1;
	public static final double OUTER_INTAKE_EJECT_SPEED = -0.4;
	public static final double INNER_INTAKE_EJECT_SPEED = -0.3;

	private static DoubleSolenoid ballStop;
	private CANTalon outerRoller, innerRoller;

	public Intake() {
		try {
			outerRoller = new CANTalon(
					RobotMap.INTAKE_OUTER_ROLLER_MOTOR_CAN_ID);
			innerRoller = new CANTalon(
					RobotMap.INTAKE_INNER_ROLLER_MOTOR_CAN_ID);

			outerRoller.enableBrakeMode(true);
			outerRoller.setSafetyEnabled(false);
			innerRoller.enableBrakeMode(true);
			innerRoller.setSafetyEnabled(false);
			
			ballStop = new DoubleSolenoid(RobotMap.SHOOTER_POSITION_OUT_PCM_ID,RobotMap.SHOOTER_POSITION_IN_PCM_ID);
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

	@Override
	protected void initDefaultCommand() {

	}

	public void updateStatus(Robot.OperationMode operationMode) {
		if (operationMode == Robot.OperationMode.TEST) {
		}
	}

	public static boolean getBallStop() {
		return ballStop.get() == Value.kForward;
	}

	public void setBallStop(BallStopState state) {
		if(state == BallStopState.IN) {
			ballStop.set(Value.kForward);
		} else if(state == BallStopState.OUT) {
			ballStop.set(Value.kReverse);
		}
	}
	
	public void feedWithCheck(){
		if(Robot.shooter.isOnTarget() && getBallStop())
			setSpeedInner(-1);
		else 
			setSpeedInner(0);
	}
}