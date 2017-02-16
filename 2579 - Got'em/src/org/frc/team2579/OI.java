package org.frc.team2579;

import org.frc.team2579.commands.BallStopIn;
import org.frc.team2579.commands.BallStopOut;
import org.frc.team2579.commands.ClimbOff;
import org.frc.team2579.commands.ClimbUp;
import org.frc.team2579.commands.ClimberSpeed;
import org.frc.team2579.commands.IntakeInnerSpeed;
import org.frc.team2579.commands.IntakeOff;
import org.frc.team2579.commands.IntakeOuterSpeed;
import org.frc.team2579.commands.ManipulatorFullyDeploy;
import org.frc.team2579.commands.ManipulatorFullyRetract;
import org.frc.team2579.commands.ManipulatorIntakeOff;
import org.frc.team2579.commands.ManipulatorSpeed;
import org.frc.team2579.commands.ShooterShoot;
import org.frc.team2579.commands.ShooterSpeed;
import org.frc.team2579.controller.XboxController;
import org.frc.team2579.subsystems.Climber;
import org.frc.team2579.subsystems.Intake;
import org.frc.team2579.subsystems.Manipulator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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
		JoystickButton manipulatorFullyRetract = new JoystickButton(m_operatorXBox.getJoyStick(), XboxController.LEFT_BUMPER_BUTTON);
        manipulatorFullyRetract.whenPressed(new ManipulatorFullyRetract());
		
        JoystickButton manipulatorFullyDeploy = new JoystickButton(m_operatorXBox.getJoyStick(), XboxController.RIGHT_BUMPER_BUTTON);
        manipulatorFullyDeploy.whileHeld(new ManipulatorFullyDeploy());
        manipulatorFullyDeploy.whenReleased(new ManipulatorIntakeOff());
        
        JoystickButton manipulatorOutSpeed = new JoystickButton(m_operatorXBox.getJoyStick(), XboxController.B_BUTTON);
        manipulatorOutSpeed.whenPressed(new ManipulatorSpeed(Manipulator.INTAKE_EJECT_SPEED));
        
        JoystickButton climb = new JoystickButton(m_operatorXBox.getJoyStick(), XboxController.X_BUTTON);
        climb.whileHeld(new ClimbUp());
        climb.whenReleased(new ClimbOff());
        
        JoystickButton ballStopIn = new JoystickButton(m_operatorXBox.getJoyStick(), XboxController.BACK_BUTTON);
        ballStopIn.whenPressed(new BallStopIn());
        
        JoystickButton ballStopOut = new JoystickButton(m_operatorXBox.getJoyStick(), XboxController.START_BUTTON);
        ballStopOut.whenPressed(new BallStopOut());
        
        JoystickButton innerIntakeIn = new JoystickButton(m_operatorXBox.getJoyStick(), XboxController.A_BUTTON);
        innerIntakeIn.whileHeld(new IntakeInnerSpeed(Intake.INNER_INTAKE_LOAD_SPEED));
        innerIntakeIn.whenReleased(new IntakeOff());
        
        JoystickButton outerIntakeIn = new JoystickButton(m_operatorXBox.getJoyStick(), XboxController.Y_BUTTON);
        outerIntakeIn.whileHeld(new IntakeOuterSpeed(Intake.OUTER_INTAKE_LOAD_SPEED));
        outerIntakeIn.whenReleased(new IntakeOff());
        
        JoystickButton shooterShoot = new JoystickButton(m_operatorXBox.getJoyStick(), XboxController.RIGHT_TRIGGER_AXIS);
        shooterShoot.whileHeld(new ShooterShoot());
        
		// Operator's Sticks

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
