package org.frc.team2579.subsystems;

import org.frc.team2579.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem{
	
	public static final double CLIMB_SPEED = 1;
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
		climber.set(speed);
	}
	
	protected void initDefaultCommand() {
	}

	public boolean isAtTarget() {
		return false;
		// return isAtTarget;
	}

}
