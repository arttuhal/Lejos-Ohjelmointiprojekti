import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class InfraredSensor {
	private float[] sample;

	EV3IRSensor IR = new EV3IRSensor(SensorPort.S4);

	public void testDistance() {
		SampleProvider distance = ((EV3IRSensor) IR).getDistanceMode();
		this.sample = new float[distance.sampleSize()];
		while (this.sample[0] <= 0.0 || this.sample[0] > 1000.0) {
			LCD.drawString(toString(), 0, 0);
			distance.fetchSample(sample, 0);
			Delay.msDelay(10);
		}
		LCD.clear();
		while (this.sample[0] >= 15.0) {
			LCD.drawString(toString(), 0, 0);
			distance.fetchSample(sample, 0);
			Delay.msDelay(10);
		}
		LCD.clear();
	}

	@Override
	public String toString() {
		return "" + this.sample[0];
	}

}
