package org.frc.team2579.commands;

import edu.rhhs.frc.subsystems.DriveTrain;
import edu.rhhs.frc.subsystems.Intake;
import edu.rhhs.frc.subsystems.Intake.LiftState;
import edu.rhhs.frc.subsystems.Shooter.ShotPosition;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ClimberEngageAndWinch extends CommandGroup {

	public ClimberEngageAndWinch() {
		addSequential(new ShooterShotPosition(ShotPosition.LONG));
		addSequential(new DriveTrainStraightMP(-233.8,
				DriveTrain.MP_AUTON_MOAT_VELOCITY_INCHES_PER_SEC, false, false,
				0));
	}
}
