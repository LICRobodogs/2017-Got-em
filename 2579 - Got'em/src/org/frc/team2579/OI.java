package org.frc.team2579;

import org.frc.team2579.buttons.XBoxTriggerButton;
import org.frc.team2579.commands.DriveTrainAbsoluteTurnMP;
import org.frc.team2579.commands.DriveTrainClimberSet;
import org.frc.team2579.commands.DriveTrainGyroLock;
import org.frc.team2579.commands.DriveTrainRelativeMaxTurnMP;
import org.frc.team2579.commands.DriveTrainRelativeTurnMP;
import org.frc.team2579.commands.DriveTrainSpeed;
import org.frc.team2579.commands.DriveTrainStraightMP;
import org.frc.team2579.commands.IntakeEject;
import org.frc.team2579.commands.IntakeFullyDeploy;
import org.frc.team2579.commands.IntakeFullyRetract;
import org.frc.team2579.commands.IntakeInnerSpeed;
import org.frc.team2579.commands.IntakeLowBarPosition;
import org.frc.team2579.commands.IntakeOff;
import org.frc.team2579.commands.IntakeOuterSpeed;
import org.frc.team2579.commands.ManipulatorArmSpeed;
import org.frc.team2579.commands.ManipulatorMoveMP;
import org.frc.team2579.commands.ShooterShootAndRetract;
import org.frc.team2579.commands.ShooterShootAndRetractCamera;
import org.frc.team2579.commands.ShooterWinchRetractAndSpoolOut;
import org.frc.team2579.commands.ShooterWinchSafeRelease;
import org.frc.team2579.commands.ShooterWinchSpeed;
import org.frc.team2579.controller.XboxController;
import org.frc.team2579.subsystems.DriveTrain;
import org.frc.team2579.subsystems.DriveTrain.ClimberState;
import org.frc.team2579.subsystems.Intake;
import org.frc.team2579.subsystems.Manipulator.ArmSide;
import org.frc.team2579.subsystems.Manipulator.PresetPositions;
import org.frc.team2579.utility.MPSoftwarePIDController.MPSoftwareTurnType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.InternalButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
	private static OI instance;

	private Joystick m_driverJoystickPower;
	private Joystick m_driverJoystickTurn;
	private XboxController m_operatorXBox;

	private OI() {
		m_driverJoystickPower = new Joystick(RobotMap.DRIVER_JOYSTICK_1_USB_ID);
		m_driverJoystickTurn = new Joystick(RobotMap.DRIVER_JOYSTICK_2_USB_ID);
		m_operatorXBox = new XboxController(RobotMap.OPERATOR_XBOX_USB_ID);

		// Driver's sticks

		JoystickButton climberDeploy1 = new JoystickButton(
				m_driverJoystickPower, 3);
		climberDeploy1.whenPressed(new DriveTrainClimberSet(
				ClimberState.DEPLOYED));

		JoystickButton climberRetract1 = new JoystickButton(
				m_driverJoystickPower, 4);
		climberRetract1.whenPressed(new DriveTrainClimberSet(
				ClimberState.RETRACTED));

		JoystickButton intakeFullyDeploy1 = new JoystickButton(
				m_driverJoystickPower, 5);
		intakeFullyDeploy1.whenPressed(new IntakeFullyDeploy());
		intakeFullyDeploy1.whenReleased(new IntakeOff());

		JoystickButton intakeFullyRetract1 = new JoystickButton(
				m_driverJoystickPower, 6);
		intakeFullyRetract1.whenPressed(new IntakeFullyRetract());

		JoystickButton gyroLock = new JoystickButton(m_driverJoystickTurn, 1);
		gyroLock.whenPressed(new DriveTrainGyroLock(true, true));
		gyroLock.whenReleased(new DriveTrainGyroLock(false, false));

		// JoystickButton manipulatorPartiallyDeploy1 = new
		// JoystickButton(m_driverJoystickTurn, 3);
		// manipulatorPartiallyDeploy1.whenPressed(new
		// ManipulatorMoveMP(PresetPositions.PARTIALLY_DEPLOYED));

		JoystickButton turn180 = new JoystickButton(m_driverJoystickTurn, 3);
		turn180.whenPressed(new DriveTrainRelativeMaxTurnMP(180,
				DriveTrain.MAX_TURN_RATE_DEG_PER_SEC, MPSoftwareTurnType.TANK));

		// JoystickButton shooterShortPosition1 = new
		// JoystickButton(m_driverJoystickTurn, 4);
		// shooterShortPosition1.whenPressed(new
		// ShooterShotPosition(ShotPosition.SHORT));

		JoystickButton intakeLowBarPosition1 = new JoystickButton(
				m_driverJoystickTurn, 5);
		intakeLowBarPosition1.whenPressed(new IntakeLowBarPosition());

		JoystickButton intakeEject1 = new JoystickButton(m_driverJoystickTurn,
				6);
		intakeEject1.whenPressed(new IntakeEject());
		intakeEject1.whenReleased(new IntakeOff());

		// JoystickButton shooterShoot1 = new
		// JoystickButton(m_driverJoystickTurn, 2);
		// shooterShoot1.whenPressed(new ShooterShootAndRetract());

		// JoystickButton engageClimberAndWinch1 = new
		// JoystickButton(m_driverJoystickTurn, 11);
		// engageClimberAndWinch1.whenPressed(new ClimberEngageAndWinch());

		JoystickButton safeReleaseWinch1 = new JoystickButton(
				m_driverJoystickTurn, 12);
		safeReleaseWinch1.whenPressed(new ShooterWinchSafeRelease());

		// Operator's controller
		JoystickButton manipulatorDeploy = new JoystickButton(
				m_operatorXBox.getJoyStick(), XboxController.Y_BUTTON);
		manipulatorDeploy.whenPressed(new ManipulatorMoveMP(
				PresetPositions.FULLY_DEPLOYED));

		// JoystickButton retractClimber = new
		// JoystickButton(m_operatorXBox.getJoyStick(),
		// XboxController.B_BUTTON);
		// retractClimber.whenPressed(new
		// DriveTrainClimberSet(ClimberState.RETRACTED));
		//
		// JoystickButton deployClimber = new
		// JoystickButton(m_operatorXBox.getJoyStick(),
		// XboxController.X_BUTTON);
		// deployClimber.whenPressed(new
		// DriveTrainClimberSet(ClimberState.DEPLOYED));

		JoystickButton manipulatorRetract = new JoystickButton(
				m_operatorXBox.getJoyStick(), XboxController.A_BUTTON);
		manipulatorRetract.whenPressed(new ManipulatorMoveMP(
				PresetPositions.ZERO));

		// JoystickButton shooterShortPosition = new
		// JoystickButton(m_operatorXBox.getJoyStick(),
		// XboxController.X_BUTTON);
		// shooterShortPosition.whenPressed(new
		// ShooterShotPosition(ShotPosition.SHORT));

		JoystickButton intakeFullyDeploy = new JoystickButton(
				m_operatorXBox.getJoyStick(),
				XboxController.RIGHT_BUMPER_BUTTON);
		intakeFullyDeploy.whenPressed(new IntakeFullyDeploy());
		intakeFullyDeploy.whenReleased(new IntakeOff());

		JoystickButton intakeEject = new JoystickButton(
				m_operatorXBox.getJoyStick(), XboxController.LEFT_BUMPER_BUTTON);
		intakeEject.whenPressed(new IntakeEject());
		intakeEject.whenReleased(new IntakeOff());

		XBoxTriggerButton shooterShoot = new XBoxTriggerButton(m_operatorXBox,
				XBoxTriggerButton.RIGHT_TRIGGER);
		shooterShoot.whenPressed(new ShooterShootAndRetract(false));

		XBoxTriggerButton shooterShootCamera = new XBoxTriggerButton(
				m_operatorXBox, XBoxTriggerButton.LEFT_TRIGGER);
		shooterShootCamera.whenPressed(new ShooterShootAndRetractCamera());

		JoystickButton retractWinch = new JoystickButton(
				m_operatorXBox.getJoyStick(), XboxController.BACK_BUTTON);
		retractWinch.whenPressed(new ShooterWinchRetractAndSpoolOut());

		JoystickButton safeReleaseWinch = new JoystickButton(
				m_operatorXBox.getJoyStick(), XboxController.START_BUTTON);
		safeReleaseWinch.whenPressed(new ShooterWinchSafeRelease());

		// Motors
		Button manipulatorArmUP = new InternalButton();

		SmartDashboard.putData("Manip Positive", manipulatorArmUP);

		Button outerIntakeOn = new InternalButton();
		outerIntakeOn.whenPressed(new IntakeOuterSpeed(
				Intake.OUTER_INTAKE_LOAD_SPEED));
		SmartDashboard.putData("Outer Roller On", outerIntakeOn);

		Button outerIntakeOff = new InternalButton();
		outerIntakeOff.whenPressed(new IntakeOuterSpeed(0.0));
		SmartDashboard.putData("Outer Roller Off", outerIntakeOff);

		Button innerIntakeOn = new InternalButton();
		innerIntakeOn.whenPressed(new IntakeInnerSpeed(
				Intake.INNER_INTAKE_LOAD_SPEED));
		SmartDashboard.putData("Inner Roller On", innerIntakeOn);

		Button innerIntakeOff = new InternalButton();
		innerIntakeOff.whenPressed(new IntakeInnerSpeed(0.0));
		SmartDashboard.putData("Inner Roller Off", innerIntakeOff);

		Button shooterWinchPositive = new InternalButton();
		shooterWinchPositive.whileHeld(new ShooterWinchSpeed(0.5));
		shooterWinchPositive.whenReleased(new ShooterWinchSpeed(0.0));
		SmartDashboard.putData("Winch Speed Pos", shooterWinchPositive);

		Button shooterWinchNegative = new InternalButton();
		shooterWinchNegative.whileHeld(new ShooterWinchSpeed(-0.5));
		shooterWinchNegative.whenReleased(new ShooterWinchSpeed(0.0));
		SmartDashboard.putData("Winch Speed Neg", shooterWinchNegative);

		Button shooterWinchRetract = new InternalButton();
		shooterWinchRetract.whenPressed(new ShooterWinchRetractAndSpoolOut());
		SmartDashboard.putData("Winch Retract", shooterWinchRetract);

		Button shooterWinchSpoolOut = new InternalButton();
		shooterWinchSpoolOut.whenPressed(new ShooterWinchSpoolOut());
		SmartDashboard.putData("Winch Spool Out", shooterWinchSpoolOut);

		Button shooterShootAndRetract = new InternalButton();
		shooterShootAndRetract.whenPressed(new ShooterShootAndRetract(false));
		SmartDashboard.putData("Shoot and Retract", shooterShootAndRetract);

		Button drivePos05 = new InternalButton();
		drivePos05.whenPressed(new DriveTrainSpeed(0.5));
		SmartDashboard.putData("Drive 0.5", drivePos05);

		Button drivePos08 = new InternalButton();
		drivePos08.whenPressed(new DriveTrainSpeed(0.8));
		SmartDashboard.putData("Drive 0.8", drivePos08);

		Button drivePos10 = new InternalButton();
		drivePos10.whenPressed(new DriveTrainSpeed(1.0));
		SmartDashboard.putData("Drive 1.0", drivePos10);

		Button driveOff = new InternalButton();
		driveOff.whenPressed(new DriveTrainSpeed(0.0));
		SmartDashboard.putData("Drive off", driveOff);

		Button driveMP = new InternalButton();
		driveMP.whenPressed(new DriveTrainStraightMP(96,
				DriveTrain.MP_AUTON_MAX_STRAIGHT_VELOCITY_INCHES_PER_SEC, true,
				false, 0));
		SmartDashboard.putData("Drive Straight", driveMP);

		Button turnRelativeMP = new InternalButton();
		turnRelativeMP.whenPressed(new DriveTrainRelativeTurnMP(60,
				DriveTrain.MP_AUTON_MAX_TURN_RATE_DEG_PER_SEC,
				MPSoftwareTurnType.TANK));
		SmartDashboard.putData("Turn Relative", turnRelativeMP);

		Button turnAbsoluteMP = new InternalButton();
		turnAbsoluteMP.whenPressed(new DriveTrainAbsoluteTurnMP(0,
				DriveTrain.MP_AUTON_MAX_TURN_RATE_DEG_PER_SEC,
				MPSoftwareTurnType.TANK));
		SmartDashboard.putData("Turn Absolute", turnAbsoluteMP);

		Button armMPDeploy = new InternalButton();
		armMPDeploy.whenPressed(new ManipulatorMoveMP(
				PresetPositions.FULLY_DEPLOYED));
		SmartDashboard.putData("Arm Deploy", armMPDeploy);

		Button armMPPartialDeploy = new InternalButton();
		armMPPartialDeploy.whenPressed(new ManipulatorMoveMP(
				PresetPositions.PARTIALLY_DEPLOYED));
		SmartDashboard.putData("Arm Partial Deploy", armMPPartialDeploy);

		Button armMPZero = new InternalButton();
		armMPZero.whenPressed(new ManipulatorMoveMP(PresetPositions.ZERO));
		SmartDashboard.putData("Arm Zero Position", armMPZero);

		Button armMPRetract = new InternalButton();
		armMPRetract.whenPressed(new ManipulatorMoveMP(
				PresetPositions.RETRACTED));
		SmartDashboard.putData("Arm Retract", armMPRetract);

		Button resetArmZero = new InternalButton();
		resetArmZero.whenPressed(new ManipulatorResetZero());
		SmartDashboard.putData("Reset Arm Zero", resetArmZero);

		Button gyroReset = new InternalButton();
		gyroReset.whenPressed(new DriveTrainGyroReset());
		SmartDashboard.putData("Gyro Reset", gyroReset);

		Button gyroCalibrate = new InternalButton();
		gyroCalibrate.whenPressed(new DriveTrainGyroCalibrate());
		SmartDashboard.putData("Gyro Calibrate", gyroCalibrate);

		Button drivetrainHoldOn = new InternalButton();
		drivetrainHoldOn.whenPressed(new DriveTrainHold(true));
		SmartDashboard.putData("Drive Hold On", drivetrainHoldOn);

		Button drivetrainHoldOff = new InternalButton();
		drivetrainHoldOff.whenPressed(new DriveTrainHold(false));
		SmartDashboard.putData("Drive Hold Off", drivetrainHoldOff);

		Button cameraUpdateDashboard = new InternalButton();
		cameraUpdateDashboard.whenPressed(new CameraUpdateDashboard());
		SmartDashboard.putData("Camera Update", cameraUpdateDashboard);

		Button cameraSaveImage = new InternalButton();
		cameraSaveImage.whenPressed(new CameraSaveImage());
		SmartDashboard.putData("Camera Save", cameraSaveImage);

		Button cameraReadImage = new InternalButton();
		cameraReadImage.whenPressed(new CameraReadAndProcessImage());
		SmartDashboard.putData("Camera Read", cameraReadImage);

		Button cameraReadImageTurnToBestTarget = new InternalButton();
		cameraReadImageTurnToBestTarget
				.whenPressed(new CameraReadImageTurnToBestTarget());
		SmartDashboard.putData("Camera Read Turn",
				cameraReadImageTurnToBestTarget);

		Button cameraTurnToBestTarget = new InternalButton();
		cameraTurnToBestTarget.whenPressed(new CameraTurnToBestTarget());
		SmartDashboard.putData("Camera Turn To Best", cameraTurnToBestTarget);

		Button cameraUpdateBestTarget = new InternalButton();
		cameraUpdateBestTarget.whenPressed(new CameraUpdateBestTarget());
		SmartDashboard.putData("Camera Update Best", cameraUpdateBestTarget);

		Button cameraTurnShootLaser = new InternalButton();
		cameraTurnShootLaser
				.whenPressed(new ShooterLaserAlignmentShootAndRetract());
		SmartDashboard.putData("Camera Shoot Laser", cameraTurnShootLaser);

		Button incrementCameraOffsetPos = new InternalButton();
		incrementCameraOffsetPos.whenPressed(new CameraOffset(0.5));
		SmartDashboard.putData("Camera Offset Pos", incrementCameraOffsetPos);

		Button incrementCameraOffsetNeg = new InternalButton();
		incrementCameraOffsetNeg.whenPressed(new CameraOffset(-0.5));
		SmartDashboard.putData("Camera Offset Neg", incrementCameraOffsetNeg);

		DigitalIOSwitch cdfSwitch = new DigitalIOSwitch(
				RobotMap.CDF_SENSOR_DIO_PORT_ID);
		cdfSwitch.whenPressed(new ManipulatorMoveMP(
				PresetPositions.FULLY_DEPLOYED, true));
	}

	public Joystick getDriverJoystickPower() {
		return m_driverJoystickPower;
	}

	public Joystick getDriverJoystickTurn() {
		return m_driverJoystickTurn;
	}

	public XboxController getOperatorXBox() {
		return m_operatorXBox;
	}

	public static OI getInstance() {
		if (instance == null) {
			instance = new OI();
		}
		return instance;
	}
}
