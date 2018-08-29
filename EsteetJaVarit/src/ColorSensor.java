import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class ColorSensor {

	Port port = LocalEV3.get().getPort("S1");
	SensorModes colorSensor = new EV3ColorSensor(port);
	SampleProvider colorProvider = ((EV3ColorSensor) colorSensor).getRGBMode();
	float[] sampleR = new float[colorProvider.sampleSize()];
	float[] sampleG = new float[colorProvider.sampleSize()];
	float[] secondSample = new float[colorProvider.sampleSize()];
	private int r1, r2, g1, g2, b1, b2;

	public void calibrateColors() {
		while (Button.ENTER.isUp()) {
			LCD.drawString("Kohdista punaiselle.", 0, 0);
			LCD.drawString("Paina ENTER.", 0, 2);
			colorProvider.fetchSample(sampleR, 0);
			this.r1 = Math.round(sampleR[0] * 765);
			this.g1 = Math.round(sampleR[1] * 765);
			this.b1 = Math.round(sampleR[2] * 765);
			Delay.msDelay(100);
		}
		LCD.clear();
		Delay.msDelay(1000);
	}

	public boolean checkForRed() {
		colorProvider.fetchSample(secondSample, 0);
		this.r2 = Math.round(secondSample[0] * 765);
		this.g2 = Math.round(secondSample[1] * 765);
		this.b2 = Math.round(secondSample[2] * 765);
		if (Math.abs(r2 - r1) < 10 && Math.abs(g2 - g1) < 10 && Math.abs(b2 - b1) < 10) {
			return true;
		}
		return false;
	}
}
