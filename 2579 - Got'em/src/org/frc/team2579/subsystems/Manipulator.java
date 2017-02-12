package org.frc.team2579.subsystems;

import org.frc.team2579.Robot;
import org.frc.team2579.RobotMap;
import org.frc.team2579.utility.ControlLoopable;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Manipulator extends Subsystem implements ControlLoopable
{
	public static enum LiftState { UP, DOWN };
	public static final double INTAKE_LOAD_SPEED = 1;
	public static final double INTAKE_EJECT_SPEED = -1;
	public static String currState = "UP";
	private CANTalon roller;
	private DoubleSolenoid manipulator;
	
	public Manipulator() {
		try {
			manipulator = new DoubleSolenoid(
					RobotMap.INTAKE_UP_PCM_ID,
					RobotMap.INTAKE_DOWN_PCM_ID);
			roller = new CANTalon(RobotMap.MANIPULATOR_MOTOR_CAN_ID);
			roller.enableBrakeMode(true);
		} catch (Exception e) {
			System.err
					.println("An error occurred in the Manipulator constructor");
		}
	}
	
	public void setZeroPosition(){
		setPosition(LiftState.UP);
	}

	public void setPosition(LiftState state) {
		if(state == LiftState.UP) {
			manipulator.set(Value.kForward);
			currState = "UP";
		} else if(state == LiftState.DOWN) {
			manipulator.set(Value.kReverse);
			currState = "DOWN";
		}
	}
	
	public void setSpeed(double speed){
		roller.set(speed);
	}
	
	protected void initDefaultCommand() {
	}

	public boolean isAtTarget() {
		return false;
		// return isAtTarget;
	}

	public String getState(){
		return currState;
	}
	public void updateStatus(Robot.OperationMode operationMode) {
		// SmartDashboard.putString("Left Arm deg", leftArm.getPositionWorld());
		if (operationMode == Robot.OperationMode.TEST) {
			SmartDashboard.putString("Gear Intake State: ", getState());
			// MotionProfilePoint mpPoint = mpController.getCurrentPoint();
			// double delta = mpPoint != null ? rightArm.getPositionWorld() -
			// mpController.getCurrentPoint().position : 0;
			// SmartDashboard.putNumber("Right Arm Delta", delta);
		}
	}

	@Override
	public void controlLoopUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPeriodMs(long periodMs) {
		// TODO Auto-generated method stub
		
	}

}