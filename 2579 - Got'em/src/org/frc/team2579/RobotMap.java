package org.frc.team2579;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// USB Port IDs
	public static final int DRIVER_JOYSTICK_1_USB_ID = 1;
	public static final int DRIVER_JOYSTICK_2_USB_ID = 2;
	public static final int OPERATOR_XBOX_USB_ID = 0;

	// MOTORS
	public static final int DRIVETRAIN_LEFT_MOTOR1_CAN_ID = 4;
	public static final int DRIVETRAIN_LEFT_MOTOR2_CAN_ID = 3;
	public static final int DRIVETRAIN_RIGHT_MOTOR1_CAN_ID = 7;
	public static final int DRIVETRAIN_RIGHT_MOTOR2_CAN_ID = 0;

	public static final int INTAKE_OUTER_ROLLER_MOTOR_CAN_ID = 5;
	public static final int INTAKE_INNER_ROLLER_MOTOR_CAN_ID = 6;

	public static final int SHOOTER_MOTOR_CAN_ID = 8;

	public static final int CLIMBER_MOTOR_CAN_ID = 1;

	public static final int MANIPULATOR_MOTOR_CAN_ID = 2;

	// PNEUMATICS
	public static final int SHOOTER_POSITION_PCM_ID = 2;

	public static final int INTAKE_UP_PCM_ID = 1;
	public static final int INTAKE_DOWN_PCM_ID = 0;

}