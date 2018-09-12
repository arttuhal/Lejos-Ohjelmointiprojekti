package Navigointi;

import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.Pose;

public class Tracking extends Thread {
	private PoseProvider poseProvider;
	private Pose pose;
	private Boolean lopeta = false;

	public Tracking(PoseProvider poseProvider) {
		this.poseProvider = poseProvider;
	}

	public void run() {
		while (!lopeta) {
			pose = poseProvider.getPose();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

	public void Lopeta() {
		lopeta = true;
	}

	public Pose getPose() {
		return this.pose;
	}
}
