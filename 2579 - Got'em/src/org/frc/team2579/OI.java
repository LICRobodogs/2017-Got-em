package org.frc.team2579;

import org.frc.team2579.commands.BallStopIn;
import org.frc.team2579.commands.BallStopOut;
import org.frc.team2579.commands.ClimbOff;
import org.frc.team2579.commands.ClimbUp;
import org.frc.team2579.commands.ClimberKonami;
import org.frc.team2579.commands.ClimberSpeed;
import org.frc.team2579.commands.IntakeInnerSpeed;
import org.frc.team2579.commands.IntakeOff;
import org.frc.team2579.commands.IntakeOuterSpeed;
import org.frc.team2579.commands.ManipulatorFullyDeploy;
import org.frc.team2579.commands.ManipulatorFullyRetract;
import org.frc.team2579.commands.ManipulatorIntakeOff;
import org.frc.team2579.commands.ManipulatorSpeed;
import org.frc.team2579.commands.ShooterHold;
import org.frc.team2579.commands.ShooterMode;
import org.frc.team2579.commands.ShooterPID;
import org.frc.team2579.commands.ShooterShoot;
import org.frc.team2579.commands.ShooterSpeed;
import org.frc.team2579.controller.XboxController;
import org.frc.team2579.subsystems.Climber;
import org.frc.team2579.subsystems.Intake;
import org.frc.team2579.subsystems.Manipulator;
import org.frc.team2579.subsystems.Shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	private static OI instance;

	private XboxController m_driverJoystick;
	private XboxController m_operatorXBox;

	private OI() {
		m_driverJoystick = new XboxController(RobotMap.DRIVER_JOYSTICK_1_USB_ID);
		m_operatorXBox = new XboxController(RobotMap.OPERATOR_XBOX_USB_ID);

		// Driver's sticks
		JoystickButton manipulatorFullyRetract = new JoystickButton(m_driverJoystick.getJoyStick(), XboxController.LEFT_BUMPER_BUTTON);
        manipulatorFullyRetract.whenPressed(new ManipulatorFullyRetract());
		
        JoystickButton manipulatorFullyDeploy = new JoystickButton(m_driverJoystick.getJoyStick(), XboxController.RIGHT_BUMPER_BUTTON);
        manipulatorFullyDeploy.whileHeld(new ManipulatorFullyDeploy());
        
        JoystickButton manipulatorOutSpeed = new JoystickButton(m_driverJoystick.getJoyStick(), XboxController.B_BUTTON);
        manipulatorOutSpeed.whileHeld(new ManipulatorSpeed(Manipulator.INTAKE_EJECT_SPEED));
        manipulatorOutSpeed.whenReleased(new ManipulatorIntakeOff());
    
        JoystickButton manipulatorInSpeed = new JoystickButton(m_driverJoystick.getJoyStick(), XboxController.A_BUTTON);
        manipulatorInSpeed.whileHeld(new ManipulatorSpeed(Manipulator.INTAKE_LOAD_SPEED));
        manipulatorInSpeed.whenReleased(new ManipulatorIntakeOff());
        
        //EMERGENCY CLIMBER BYPASS KONAMI CODE
        JoystickButton climberKon = new JoystickButton(m_driverJoystick.getJoyStick(), XboxController.BACK_BUTTON);
        climberKon.whenPressed(new ClimberKonami("kon"));
        //END EMERGENCY CLIMBER BYPASS KONAMI CODE
        
        // Operator's Sticks
        
        //EMERGENCY CLIMBER BYPASS KONAMI CODE
        JoystickButton climberAmi = new JoystickButton(m_operatorXBox.getJoyStick(), XboxController.RIGHT_JOYSTICK_BUTTON);
        climberAmi.whenPressed(new ClimberKonami("ami"));
        //END EMERGENCY CLIMBER BYPASS KONAMI CODE
        
        JoystickButton climb = new JoystickButton(m_operatorXBox.getJoyStick(), XboxController.RIGHT_BUMPER_BUTTON);
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
        
        JoystickButton shooterShoot = new JoystickButton(m_operatorXBox.getJoyStick(), XboxController.X_BUTTON);
        shooterShoot.whileHeld(new ShooterShoot());
        shooterShoot.whenReleased(new ShooterHold());

	}


	public XboxController getDriverJoystick() {
		return m_driverJoystick;
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
