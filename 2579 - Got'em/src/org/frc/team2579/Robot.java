package org.frc.team2579;

import org.frc.team2579.commands.auton.CenterPegDropOff;
import org.frc.team2579.commands.auton.DriveForwardMP;
import org.frc.team2579.commands.auton.SidePegDropOff;
import org.frc.team2579.subsystems.Camera;
import org.frc.team2579.subsystems.Climber;
import org.frc.team2579.subsystems.DriveTrain;
import org.frc.team2579.subsystems.Intake;
import org.frc.team2579.subsystems.Manipulator;
import org.frc.team2579.subsystems.Shooter;
import org.frc.team2579.subsystems.DriveTrain.DriveTrainControlMode;
import org.frc.team2579.utility.ControlLooper;
import org.frc.team2579.utility.MiddleGearForwardProfile;

import com.ctre.CANTalon.SetValueMotionProfile;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	public static final PowerDistributionPanel pdp = new PowerDistributionPanel();
	public static final ControlLooper controlLoop = new ControlLooper("Main control loop", 10);
	
	public static OI oi;
	
    Command autonomousCommand;
	public static SendableChooser<Command> autonChooser;
	 
	public static enum OperationMode {
		TEST, COMPETITION
	};

	public static OperationMode operationMode = OperationMode.COMPETITION;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture();
		controlLoop.addLoopable(driveTrain);
    	controlLoop.addLoopable(manipulator);
    	controlLoop.addLoopable(shooter);
		/*
		 * the Talons on the left-side of my robot needs to drive reverse(red)
		 * to move robot forward. Since _leftSlave just follows frontLeftMotor,
		 * no need to invert it anywhere.
		 */
    	setupAutonChooser();
	}

	public void disabledInit(){

	}
	
	public void disabledPeriodic() {
		DriveTrain.resetMP();
		DriveTrain.resetPosition();
		Scheduler.getInstance().run();
		//updateStatus();
	}
	
	public void autonomousInit() {
	        // Schedule the autonomous command (example)
		DriveTrain.resetMP();
		DriveTrain.resetPosition();
	
		Robot.driveTrain.setControlMode(DriveTrainControlMode.AUTON);
		driveTrain.setPeriodMs(50);
	    controlLoop.start();
	    
	    autonomousCommand = autonChooser.getSelected();
        if (autonomousCommand != null) {
            autonomousCommand.start();
        }
	    /*
	    new DriveForwardMP();
	    DriveTrain.startFilling(GeneratedMotionProfile.Points, GeneratedMotionProfile.kNumPoints);
		DriveTrain.setSetValue(SetValueMotionProfile.Enable);
		*/
	 }
	
	public void autonomousPeriodic(){
		Scheduler.getInstance().run();
		updateStatus();
	}
	 
	public void teleopInit() {
		if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
		
		Robot.driveTrain.setControlMode(DriveTrainControlMode.JOYSTICK);
		driveTrain.setPeriodMs(10);
		controlLoop.start();
	}
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		//System.out.println(DriveTrain.controlMode.toString());
		Scheduler.getInstance().run();
		updateStatus();
	}

	public void testInit(){
		Robot.operationMode = Robot.OperationMode.TEST;
	}
	
	public void testPeriodic(){
		Scheduler.getInstance().run();
	}
	
	public void setupAutonChooser(){
		autonChooser = new SendableChooser<Command>();
		autonChooser.addDefault("Center Peg", new CenterPegDropOff());
		autonChooser.addObject("Do Nothing", new CommandGroup());
		autonChooser.addObject("Side Peg", new SidePegDropOff());
		autonChooser.addObject("SHOOT Side Peg", new SidePegShoot());
		SmartDashboard.putData("Auton Setting", autonChooser);
	}
	
	public void updateStatus() {
		driveTrain.updateStatus(operationMode);
		intake.updateStatus(operationMode);
		manipulator.updateStatus(operationMode);
		shooter.updateStatus(operationMode);

	}
}