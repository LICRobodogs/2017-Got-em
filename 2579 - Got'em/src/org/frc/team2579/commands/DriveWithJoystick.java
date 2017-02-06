package org.frc.team2579.commands;

import org.frc.team2579.Robot;
import org.frc.team2579.subsystems.DriveTrain.DriveTrainControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command 
{
	public DriveWithJoystick() {
		requires(Robot.driveTrain);
	}
	
	@Override
	protected void initialize() {
		Robot.driveTrain.setControlMode(DriveTrainControlMode.JOYSTICK);
	}

	@Override
	protected void execute() {
		Robot.driveTrain.driveWithJoystick();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}