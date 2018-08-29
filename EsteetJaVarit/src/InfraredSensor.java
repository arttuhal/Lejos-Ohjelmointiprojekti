import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class InfraredSensor {
	private float[] sample;

	EV3IRSensor IR = new EV3IRSensor(SensorPort.S4);
	public void testDistance(){
		SampleProvider distance = ((EV3IRSensor)IR).getDistanceMode();
		float[] sample = new float[distance.sampleSize()];
		while(this.sample[0] >= 10.0) {
			distance.fetchSample(sample, 0);
			this.sample = sample;
			Delay.msDelay(100);
			
		}
	}
	@Override
	public String toString() {
		return "" + this.sample[0];
	}
	
}
