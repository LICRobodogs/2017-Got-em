package org.frc.team2579;

import com.ctre.CANTalon;

import org.frc.team2579.RobotMain.OperationMode;
import org.frc.team2579.subsystems.Camera;
import org.frc.team2579.subsystems.DriveTrain;
import org.frc.team2579.subsystems.Intake;
import org.frc.team2579.subsystems.Manipulator;
import org.frc.team2579.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Scheduler;

import com.ctre.CANTalon.TalonControlMode;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot {

	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Shooter shooter = new Shooter();
	public static final Intake intake = new Intake();
	public static final Manipulator manipulator = new Manipulator();
	public static final Camera camera = new Camera();
	public static final PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public static OI oi;

	public static enum OperationMode {
		TEST, COMPETITION
	};

	public static OperationMode operationMode = OperationMode.COMPETITION;

	/* talons for arcade drive */
	CANTalon _frontLeftMotor = new CANTalon(3); /* device IDs here (1 of 2) */
	CANTalon _rearLeftMotor = new CANTalon(4);
	CANTalon _frontRightMotor = new CANTalon(7);
	CANTalon _rearRightMotor = new CANTalon(0);

	CANTalon controlMotor = new CANTalon(1);

	RobotDrive _drive = new RobotDrive(_frontLeftMotor, _rearLeftMotor,
			_frontRightMotor, _rearRightMotor);

	Joystick _joy = new Joystick(0);
	Joystick operatorControl = new Joystick(3); // control

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {

		/*
		 * the Talons on the left-side of my robot needs to drive reverse(red)
		 * to move robot forward. Since _leftSlave just follows frontLeftMotor,
		 * no need to invert it anywhere.
		 */
		_drive.setInvertedMotor(MotorType.kFrontRight, true);
		_drive.setInvertedMotor(MotorType.kRearRight, true);
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		driveTrain.checkForGyroCalibration();
		updateStatus();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		double forward = _joy.getRawAxis(0); // logitech gampad left X, positive
												// is forward
		double turn = _joy.getRawAxis(1); // logitech gampad right X, positive
											// means turn right
		_drive.arcadeDrive(forward, turn);

		if (operatorControl.getRawButton(3)) {
			// ropeSpinner.set(operatorControl.getY());
			controlMotor.set(1);
		} else if (operatorControl.getRawButton(2)) {
			// ropeSpinner.set(operatorControl.getY());
			controlMotor.set(-1);
		} else {
			// ropeSpinner.set(0);
			controlMotor.set(0);
		}

	}

	public void updateStatus() {
		driveTrain.updateStatus(operationMode);
		intake.updateStatus(operationMode);
		manipulator.updateStatus(operationMode);
		shooter.updateStatus(operationMode);
		camera.updateStatus(operationMode);
	}
}