package org.frc.team2579.subsystems;

import org.frc.team2579.Robot;
import org.frc.team2579.RobotMap;
import org.frc.team2579.subsystems.Intake;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {

	private CANTalon wheel;
	
	public static double mFlywheelOnTargetTolerance = 100;
	public static double mFlywheelKp = 0.1;
    public static double mFlywheelKi = 0.001;
    public static double mFlywheelKd = 1.0;
    public static double mFlywheelKf = 0.0;
    public static int mFlywheelIZone = (int) (1023.0 / mFlywheelKp);
    public static double mFlywheelRampRate = 0;
    public static int mFlywheelAllowableError = 0;

	public Shooter() {
		try {
			wheel = new CANTalon(RobotMap.SHOOTER_MOTOR_CAN_ID);
			wheel.enableBrakeMode(false);
			wheel.reverseSensor(true);
			
			wheel.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			wheel.setPID(mFlywheelKp, mFlywheelKi, mFlywheelKd,mFlywheelKf,mFlywheelIZone,mFlywheelRampRate,0);
			wheel.setProfile(0);
			wheel.reverseSensor(false);
	        wheel.reverseOutput(false);
	        wheel.setVoltageRampRate(36.0);
		} catch (Exception e) {
			System.err.println("An error occurred in the Shooter constructor");
		}
	}

	public boolean getBallStopIn(){
		return Intake.getBallStop();
	}

	public void setWheelSpeed(double speed) {
		wheel.changeControlMode(CANTalon.TalonControlMode.Speed);
		wheel.set(speed);
	}

	public void setOpenLoop(double speed) {
        wheel.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        wheel.set(speed);
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
		SmartDashboard.putNumber("flywheel_setpoint", getSetpoint());
        SmartDashboard.putBoolean("flywheel_on_target", isOnTarget());
		if (operationMode == Robot.OperationMode.TEST) {
		}
	}

	private boolean isOnTarget() {
		return (wheel.getControlMode() == CANTalon.TalonControlMode.Speed
                && Math.abs(getWheelVelocity() - getSetpoint()) < mFlywheelOnTargetTolerance);
	}

	private double getSetpoint() {
		return wheel.getSetpoint();
	}
}