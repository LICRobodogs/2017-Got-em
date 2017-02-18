package org.frc.team2579.subsystems;

import org.frc.team2579.RobotMap;
import org.frc.team2579.utility.ControlLoopable;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem implements ControlLoopable{
	
	public static final double CLIMB_SPEED = 1;
	public static double directionMod = 1;
	private CANTalon climber;
	
	public Climber() {
		try {
			climber = new CANTalon(RobotMap.CLIMBER_MOTOR_CAN_ID);
			climber.enableBrakeMode(true);
		} catch (Exception e) {
			System.err
					.println("An error occurred in the Manipulator constructor");
		}
	}
	
	public void setSpeed(double speed){
		climber.set(speed*directionMod);
	}
	
	protected void initDefaultCommand() {
	}

	public boolean isAtTarget() {
		return false;
		// return isAtTarget;
	}
	
	public void setBackward(){
		directionMod = -1;
	}
	
	public void setForward(){
		directionMod = 1;
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
