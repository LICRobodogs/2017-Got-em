package org.frc.team2579.commands;

import org.frc.team2579.RobotMain;
import org.frc.team2579.subsystems.Manipulator;
import edu.wpi.first.wpilibj.command.Command;

public class ManipulatorMoveMP extends Command {
	private Manipulator.PresetPositions position;
	private boolean safeDeploy;
	private boolean noMovement;
	private Manipulator.Attachment manipulator;

	public ManipulatorMoveMP(Manipulator.PresetPositions position) {
		this(position, false);
	}

	public ManipulatorMoveMP(Manipulator.PresetPositions position,
			boolean safeDeploy) {
		requires(RobotMain.manipulator);
		this.position = position;
		this.safeDeploy = safeDeploy;
	}

	public ManipulatorMoveMP(Manipulator.PresetPositions position,
			Manipulator.Attachment manipulator) {
		requires(RobotMain.manipulator);
		this.position = position;
		this.manipulator = manipulator;
	}

	protected void initialize() {
		this.noMovement = false;
		if (manipulator != null) {
			if (RobotMain.manipulator.getAttachment() == manipulator) {
				RobotMain.manipulator.setPresetPosition(position);
			} else {
				this.noMovement = true;
			}
		} else if (safeDeploy) {
			if (RobotMain.manipulator.getLeftArmAngle() > (Manipulator.CHEVAL_DE_FRISE_PARTIALLY_DEPLOYED_ANGLE_DEG - 10)) {
				RobotMain.manipulator.setPresetPosition(position);
			} else {
				this.noMovement = true;
			}
		} else {
			RobotMain.manipulator.setPresetPosition(position);
		}
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return noMovement || RobotMain.manipulator.isAtTarget();
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}