import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

public class KaukoOhjain extends Thread {
	private EV3IRSensor infraredSensor;
	private int channel = 3, command;
	private float[] sample;
	private SampleProvider distance;

	public KaukoOhjain(EV3IRSensor infraredSensor) {
		this.infraredSensor = infraredSensor;
		distance = infraredSensor.getDistanceMode();
	}

	public void run() {
		while (true) {
			command = infraredSensor.getRemoteCommand(channel);
			this.sample = new float[distance.sampleSize()];
			distance.fetchSample(sample, 0);
		}
	}

	public synchronized int getCommand() {
		return command;
	}

	public synchronized float getDistance() {
		return this.sample[0];
	}

}
