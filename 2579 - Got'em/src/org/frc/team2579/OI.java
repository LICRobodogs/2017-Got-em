package org.frc.team2579;

import org.frc.team2579.commands.ManipulatorFullyDeploy;
import org.frc.team2579.commands.ManipulatorFullyRetract;
import org.frc.team2579.commands.ManipulatorIntakeOff;
import org.frc.team2579.commands.ManipulatorSpeed;
import org.frc.team2579.controller.XboxController;

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
        manipulatorFullyDeploy.whenPressed(new ManipulatorFullyDeploy());
        manipulatorFullyDeploy.whenReleased(new ManipulatorIntakeOff());
		
        JoystickButton manipulatorSpeed = new JoystickButton(m_operatorXBox.getJoyStick(), XboxController.A_BUTTON);
        manipulatorSpeed.whenPressed(new ManipulatorSpeed(1));
        
        JoystickButton manipulatorOutSpeed = new JoystickButton(m_operatorXBox.getJoyStick(), XboxController.B_BUTTON);
        manipulatorOutSpeed.whenPressed(new ManipulatorSpeed(-1));
        
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
