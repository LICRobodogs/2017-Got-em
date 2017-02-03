package org.frc.team2579.subsystems;

import java.util.ArrayList;

import org.frc.team2579.subsystems.DriveTrain.ClimberState;
import org.frc.team2579.utility.CANTalonEncoder;
import org.frc.team2579.utility.ControlLoopable;
import org.frc.team2579.utility.MPTalonPIDController;
import org.frc.team2579.utility.PIDParams;
import org.frc.team2579.RobotMain;
import org.frc.team2579.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Manipulator extends Subsystem {

	private DoubleSolenoid manipulator;
	
	public Manipulator() {
		try {
			manipulator = new DoubleSolenoid(
					RobotMap.INTAKE_OUTER_LIFT_PCM_ID,
					RobotMap.INTAKE_INNER_LIFT_PCM_ID);
			setClimberState(ClimberState.RETRACTED);
		} catch (Exception e) {
			System.err
					.println("An error occurred in the Manipulator constructor");
		}
	}

	public void setZeroPosition() {

	}

	protected void initDefaultCommand() {
	}

	public boolean isAtTarget() {
		return false;
		// return isAtTarget;
	}

	public void updateStatus(RobotMain.OperationMode operationMode) {
		// SmartDashboard.putString("Left Arm deg", leftArm.getPositionWorld());
		if (operationMode == RobotMain.OperationMode.TEST) {
			// SmartDashboard.putBoolean("CDF Sensor", cdfSensor.get());
			// MotionProfilePoint mpPoint = mpController.getCurrentPoint();
			// double delta = mpPoint != null ? rightArm.getPositionWorld() -
			// mpController.getCurrentPoint().position : 0;
			// SmartDashboard.putNumber("Right Arm Delta", delta);
		}
	}

}