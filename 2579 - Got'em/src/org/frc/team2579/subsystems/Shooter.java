package org.frc.team2579.subsystems;

import org.frc.team2579.OI;
import org.frc.team2579.Robot;
import org.frc.team2579.RobotMap;
import org.frc.team2579.subsystems.Intake;
import org.frc.team2579.subsystems.DriveTrain.DriveTrainControlMode;
import org.frc.team2579.utility.ControlLoopable;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem implements ControlLoopable{

	public static enum ShooterControlMode { MANUAL, SENSORED, HOLD, TEST };
	
	private ShooterControlMode controlMode = ShooterControlMode.MANUAL;
	
	private CANTalon wheel;
	
	private static final double NATIVE_TO_RPM_FACTOR = 10 * 60 / 12;
	public double mSpeed;
	public static final double BOILER_RPM_SETPOINT = 3800*3;
	public static double mFlywheelOnTargetTolerance = 200;
	public static double mFlywheelKp = 4;
    public static double mFlywheelKi = 0.01875;
    public static double mFlywheelKd = 10;
    public static double mFlywheelKf = 2.8875;
    public static int mFlywheelIZone = (int) (1023.0 / mFlywheelKp);
    public static double mFlywheelRampRate = 0;
    public static int mFlywheelAllowableError = 0;

	public Shooter() {
		try {
			wheel = new CANTalon(RobotMap.SHOOTER_MOTOR_CAN_ID);
			wheel.enableBrakeMode(false);
			
			wheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
			wheel.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			//wheel.setPID(mFlywheelKp, mFlywheelKi, mFlywheelKd);
			wheel.setPID(mFlywheelKp, mFlywheelKi, mFlywheelKd,mFlywheelKf,mFlywheelIZone,mFlywheelRampRate,0);
			wheel.configEncoderCodesPerRev(3);
			wheel.setProfile(0);
			wheel.configNominalOutputVoltage(+0.0f, -0.0f);
			wheel.configPeakOutputVoltage(+0.0f, -12.0f);
			wheel.reverseSensor(true);
	        wheel.reverseOutput(false);
	        wheel.setVoltageRampRate(36.0);
	        resetWheelEncoder();
		} catch (Exception e) {
			System.err.println("An error occurred in the Shooter constructor");
		}
	}

	public boolean getBallStopIn(){
		return Intake.getBallStop();
	}

	public void setWheelSpeed(ShooterControlMode mode, double speed) {
		this.mSpeed = speed;
		if(mode == ShooterControlMode.SENSORED){
			setMode(ShooterControlMode.SENSORED);
			wheel.changeControlMode(CANTalon.TalonControlMode.Speed);
			wheel.setSetpoint(-mSpeed);
			wheel.set(-mSpeed/*NATIVE_TO_RPM_FACTOR*/);
		}else if(mode == ShooterControlMode.MANUAL){
			setMode(ShooterControlMode.MANUAL);
			wheel.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
	        wheel.set(-mSpeed);
		}else{
			wheel.set(0);
		}
		
	}

	/*public void setOpenLoop(double speed) {
        wheel.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        wheel.set(speed);
    }
	*/
	
	public void resetWheelEncoder() {
		wheel.setPosition(0);
	}

	public double getWheelVelocity() {
		//return (wheel.getEncVelocity()) * (NATIVE_TO_RPM_FACTOR);
		return wheel.getSpeed();
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

	public boolean isOnTarget() {
		System.out.println("AT TARGET");
		return (wheel.getControlMode() == CANTalon.TalonControlMode.Speed
                && Math.abs(getWheelVelocity() - Math.abs(getSetpoint() /* Shooter.NATIVE_TO_RPM_FACTOR*/)) < mFlywheelOnTargetTolerance);
	}

	private double getSetpoint() {
		return wheel.getSetpoint();
	}

	@Override
	public void controlLoopUpdate() {
		if (controlMode == ShooterControlMode.MANUAL) {
			shootWithJoystick();
		}
		else if (controlMode == ShooterControlMode.SENSORED) {
			shootWithFeedBack();
		}
		
	}

	public void shootWithFeedBack() {
		setWheelSpeed(ShooterControlMode.SENSORED,mSpeed);
		
	}
	
	private void shootWithJoystick() {
		setWheelSpeed(ShooterControlMode.MANUAL,OI.getInstance().getOperatorXBox().getLeftTriggerAxis());
		
	}
	
	public void setMode(ShooterControlMode mode){
		this.controlMode = mode;
	}
	
	public ShooterControlMode getMode(){
		return controlMode;
	}

	@Override
	public void setPeriodMs(long periodMs) {
		// TODO Auto-generated method stub
		
	}
}