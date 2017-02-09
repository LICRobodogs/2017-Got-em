package org.frc.team2579.subsystems;

import java.util.*;


//import org.frc.team2579.utility.CANTalonEncoder;
import org.frc.team2579.OI;
import org.frc.team2579.RobotMap;

import com.kauailabs.navx.frc.AHRS;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import org.frc.team2579.utility.ControlLoopable;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem implements ControlLoopable
{
	public static enum DriveTrainControlMode { JOYSTICK, AUTON, HOLD, TEST };

	
	// Input Device Constants
	
	public static final double DRIVER_JOY1_C1 = .0089;
	public static final double DRIVER_JOY1_C2 = .0737;
	public static final double DRIVER_JOY1_C3 = 2.4126;
	
	private double m_moveInput = 0.0;
	private double m_steerInput = 0.0;

	private double m_moveOutput = 0.0;
	private double m_steerOutput = 0.0;
	
	//NavX
	
	private AHRS mxp = new AHRS(SPI.Port.kMXP);
	
	// Robot Intrinsics
	
	public static final double ENCODER_TICKS_TO_INCHES = 4096*Math.PI*4.0;
	private DriveTrainControlMode controlMode = DriveTrainControlMode.JOYSTICK;
	
	public static final double LEFT_P = .1;
	public static final double LEFT_I = 0.0;
	public static final double LEFT_D = 0.0;
	
	public static final double RIGHT_P = .1;
	public static final double RIGHT_I = 0.0;
	public static final double RIGHT_D = 0.0;
	
	public static final double HEADING_CONTROL_P = .1;
	public static final double HEADING_CONTROL_I = 0.0;
	public static final double HEADING_CONTROL_D = 0.0;
	public static final double HEADING_CONTROL_F = 1.0;
	public static final double HEADING_CONTROL_PERIOD = 50;
	
	

    private final PIDOutput headingControlPIDOut = new PIDOutput() {
        public void pidWrite(double d) {
        }
    };

	PIDController headingControlPID = new PIDController(HEADING_CONTROL_P, HEADING_CONTROL_I, HEADING_CONTROL_D, mxp, 
			headingControlPIDOut, HEADING_CONTROL_PERIOD);
	
	
	// Motor controllers
	//private ArrayList<CANTalonEncoder> motorControllers = new ArrayList<CANTalonEncoder>();

	private CANTalon leftDrive1;
	private CANTalon leftDrive2;

	private CANTalon rightDrive1;
	private CANTalon rightDrive2;

	private RobotDrive m_drive;

	

	private List<double[]> autonDesiresList = new ArrayList<double[]>(); 
	
	public DriveTrain() {
		try {
			leftDrive1 = new CANTalon(RobotMap.DRIVETRAIN_LEFT_MOTOR1_CAN_ID);
			leftDrive2 = new CANTalon(RobotMap.DRIVETRAIN_LEFT_MOTOR2_CAN_ID);

			rightDrive1 = new CANTalon(RobotMap.DRIVETRAIN_RIGHT_MOTOR1_CAN_ID);
			rightDrive2 = new CANTalon(RobotMap.DRIVETRAIN_RIGHT_MOTOR2_CAN_ID);

			leftDrive2.changeControlMode(TalonControlMode.Follower);
			leftDrive2.set(leftDrive1.getDeviceID());

			rightDrive2.changeControlMode(TalonControlMode.Follower);
			rightDrive2.set(rightDrive1.getDeviceID());

			leftDrive1.reverseSensor(true);
			leftDrive1.reverseOutput(false);
			
			rightDrive1.reverseSensor(false);
			rightDrive1.reverseOutput(true);

			leftDrive1.enableBrakeMode(true);
			leftDrive2.enableBrakeMode(true);

			rightDrive1.enableBrakeMode(true);
			rightDrive2.enableBrakeMode(true);
			
			leftDrive1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
			rightDrive1.setFeedbackDevice(FeedbackDevice.QuadEncoder);


			m_drive = new RobotDrive(leftDrive1, rightDrive1);
			m_drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
			m_drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
			m_drive.setSafetyEnabled(false);

		} catch (Exception e) {
			System.err
					.println("An error occurred in the DriveTrain constructor");
		}
	}

	public void setSpeed(double speed) {
		//if (speed == 0) {
			setControlMode(DriveTrainControlMode.JOYSTICK);
		/*}
		else {
			setControlMode(DriveTrainControlMode.TEST);
			rightDrive1.set(speed);
			leftDrive1.set(speed);
		}*/
	}
	
	public void driveWithJoystick() {
		if(controlMode != DriveTrainControlMode.JOYSTICK || m_drive == null) return;
		
		m_moveInput = OI.getInstance().getOperatorXBox().getLeftYAxis();
		m_steerInput = OI.getInstance().getOperatorXBox().getRightXAxis();

		m_moveOutput = joystickSensitivityAdjust(m_moveInput, DRIVER_JOY1_C1, DRIVER_JOY1_C2, DRIVER_JOY1_C3);
		m_steerOutput = joystickSensitivityAdjust(m_steerInput, DRIVER_JOY1_C1, DRIVER_JOY1_C2, DRIVER_JOY1_C3);
		
		m_drive.arcadeDrive(m_moveOutput, m_steerOutput);
		
		// m_drive.arcadeDrive(-m_moveInput, -m_steerInput);
	}
	
	public void setControlMode(DriveTrainControlMode controlMode) {
 		this.controlMode = controlMode;
		if (controlMode == DriveTrainControlMode.JOYSTICK) {
			leftDrive1.changeControlMode(TalonControlMode.PercentVbus);
			rightDrive1.changeControlMode(TalonControlMode.PercentVbus);
		}
		else if (controlMode == DriveTrainControlMode.TEST) {
			leftDrive1.changeControlMode(TalonControlMode.PercentVbus);
			rightDrive1.changeControlMode(TalonControlMode.PercentVbus);
		}
		else if (controlMode == DriveTrainControlMode.HOLD) {
			
		}
		else if (controlMode == DriveTrainControlMode.AUTON) {
			leftDrive1.changeControlMode(TalonControlMode.MotionProfile);
			rightDrive1.changeControlMode(TalonControlMode.MotionProfile);
		}
	}
	
	@Override
	public void initDefaultCommand() {
		// This needs to be here for reasons.
	}
	
	
	private double joystickSensitivityAdjust(double rawInput, double C1, double 
			C2, double C3) {
		// Accepts raw joystick input, outputs adjusted values based on
		// nonlinear, 2nd order polynomial mapping.
		
		double adjustedOutput = 0.0;
		
		if (rawInput < 0){
			adjustedOutput = (C1 * Math.pow(rawInput, 2) + C2 * rawInput + C3);
		}
		else if (rawInput > 0){
			adjustedOutput = (C1 * Math.pow(rawInput, 2) + C2 * rawInput + C3);
		}

		return adjustedOutput;
		
	}
		
	public void addMoveDistance(double distanceDesire){
		// accepts distance (inches), adds move distance command to auton routine
		// note that distance gets transformed to ticks
		double distanceDesireTicks = distanceDesire * ENCODER_TICKS_TO_INCHES;
		double[] waypoint = new double[]{distanceDesire, 0};
		autonDesiresList.add(waypoint);
	}
	
	public void addTurnAngle(double angleDesire){
		// accepts angle (degrees), adds turn command to auton routine
		// note that angle gets transformed to radians
		double angleDesireRad = angleDesire * Math.PI/180;
		double[] waypoint = new double[]{0, angleDesireRad};
		autonDesiresList.add(waypoint);
	}	
	
	public void executeMovement(){
		
	}
		

	public void resetGyro(){
		//mxp.reset();
	}
	
	@Override
	public void controlLoopUpdate() {
		if (controlMode == DriveTrainControlMode.JOYSTICK) {
			driveWithJoystick();
		}
		else if (controlMode == DriveTrainControlMode.AUTON) {
			executeMovement();
		}

	}

	@Override
	public void setPeriodMs(long periodMs) {
		// TODO Auto-generated method stub
		
	}

}