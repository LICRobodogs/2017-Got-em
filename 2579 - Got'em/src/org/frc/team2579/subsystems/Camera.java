package org.frc.team2579.subsystems;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import org.frc.team2579.RobotMain;
import org.frc.team2579.RobotMain.OperationMode;
import org.frc.team2579.vision.ImageProcessor;
import org.frc.team2579.vision.ImageProcessor.TargetInfo;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;

public class Camera extends Subsystem {
	private UsbCamera centerCam;

	// private ImageProcessor imageProcessor;

	public Camera() {
		try {
			new Thread(() -> {
				UsbCamera camera = CameraServer.getInstance()
						.startAutomaticCapture();
				camera.setResolution(640, 480);

				CvSink cvSink = CameraServer.getInstance().getVideo();
				CvSource outputStream = CameraServer.getInstance().putVideo(
						"Blur", 640, 480);

				Mat source = new Mat();
				Mat output = new Mat();

				while (!Thread.interrupted()) {
					cvSink.grabFrame(source);
					Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
					outputStream.putFrame(output);
				}
			}).start();
		} catch (Exception e) {
			System.err.println("An error occurred in the Camera constructor");
		}
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void postCameraImageToDashboard() {

	}

	public UsbCamera getCamera() {
		return centerCam;
	}

}