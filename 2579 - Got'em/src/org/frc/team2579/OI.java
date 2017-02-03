package org.frc.team2579;

import org.frc.team2579.buttons.XBoxTriggerButton;

import org.frc.team2579.controller.XboxController;
import org.frc.team2579.subsystems.DriveTrain;
import org.frc.team2579.subsystems.Intake;


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
