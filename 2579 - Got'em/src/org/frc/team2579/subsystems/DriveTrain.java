package org.frc.team2579.subsystems;

import java.util.ArrayList;
//import org.frc.team2579.utility.CANTalonEncoder;
import org.frc.team2579.OI;
import org.frc.team2579.RobotMain;
import org.frc.team2579.RobotMap;

import com.kauailabs.navx.frc.AHRS;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {
	// Input Device Constants
	
	public static final double DRIVER_JOY1_C1 = .0089;
	public static final double DRIVER_JOY1_C2 = .0737;
	public static final double DRIVER_JOY1_C3 = 2.4126;
	public static final double DRIVER_JOY1_DEADBAND = 10.0;
	
	// Robot Intrinsics
	
	public static final double ENCODER_TICKS_TO_INCHES = 4096*Math.PI*4.0;
	
	// Motor controllers
	//private ArrayList<CANTalonEncoder> motorControllers = new ArrayList<CANTalonEncoder>();

	private CANTalon leftDrive1;
	private CANTalon leftDrive2;

	private CANTalon rightDrive1;
	private CANTalon rightDrive2;

	private RobotDrive m_drive;

	private AHRS MXP = new AHRS(SPI.Port.kMXP);

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


			//motorControllers.add(leftDrive1);
			//motorControllers.add(rightDrive1);

			m_drive = new RobotDrive(leftDrive1, rightDrive1);
			m_drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
			m_drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
			m_drive.setSafetyEnabled(false);

		} catch (Exception e) {
			System.err
					.println("An error occurred in the DriveTrain constructor");
		}
	}

	
	@Override
	public void initDefaultCommand() {
		// This needs to be here for reasons.
	}
	
	
	private double joystickSensitivityAdjust(double rawInput, double C1, double 
			C2, double C3, double deadband) {
		// Accepts raw joystick input, outputs adjusted values based on
		// nonlinear, 2nd order polynomial mapping.
		
		double adjustedOutput;
		
		if (rawInput < deadband){
			adjustedOutput = 0.0;
		} else {
			adjustedOutput = C1 * Math.pow(rawInput, 2) + C2 * rawInput + C3;
		}
		
		return adjustedOutput;
		
	}
		
	public void moveDistAtAngle(double distanceDesire, double angleDesire){
		// Moves robot distanceDesire at angleDesire from current position and heading
		// distanceDesire in inches, angleDesire in degrees where CCW is positive
		
		
		
		
		
		
		
	}

}