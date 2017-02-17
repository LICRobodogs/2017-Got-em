package org.frc.team2579;

import org.frc.team2579.subsystems.Camera;
import org.frc.team2579.subsystems.Climber;
import org.frc.team2579.subsystems.DriveTrain;
import org.frc.team2579.subsystems.Intake;
import org.frc.team2579.subsystems.Manipulator;
import org.frc.team2579.subsystems.Shooter;
import org.frc.team2579.subsystems.DriveTrain.DriveTrainControlMode;
import org.frc.team2579.utility.ControlLooper;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Shooter shooter = new Shooter();
	public static final Intake intake = new Intake();
	public static final Manipulator manipulator = new Manipulator();
	public static final Climber climber = new Climber();
	//public static final Camera camera = new Camera();
	public static final PowerDistributionPanel pdp = new PowerDistributionPanel();
	public static final ControlLooper controlLoop = new ControlLooper("Main control loop", 10);
	
	public static OI oi;

	public static enum OperationMode {
		TEST, COMPETITION
	};

	public static OperationMode operationMode = OperationMode.TEST;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		controlLoop.addLoopable(driveTrain);
    	controlLoop.addLoopable(manipulator);
    	controlLoop.addLoopable(shooter);
		/*
		 * the Talons on the left-side of my robot needs to drive reverse(red)
		 * to move robot forward. Since _leftSlave just follows frontLeftMotor,
		 * no need to invert it anywhere.
		 */
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateStatus();
	}
	
	public void autonomousInit() {
	        // Schedule the autonomous command (example)
		Robot.driveTrain.setControlMode(DriveTrainControlMode.AUTON);
		driveTrain.setPeriodMs(50);
	    controlLoop.start();
	 }
	 
	public void teleopInit() {
		Robot.driveTrain.setControlMode(DriveTrainControlMode.JOYSTICK);
		driveTrain.setPeriodMs(10);
		controlLoop.start();
	}
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateStatus();
	}

	public void updateStatus() {
		driveTrain.updateStatus(operationMode);
		intake.updateStatus(operationMode);
		manipulator.updateStatus(operationMode);
		shooter.updateStatus(operationMode);

	}
}