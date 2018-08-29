import com.sun.xml.internal.ws.wsdl.writer.document.Port;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensor {

	Port port = LocalEV3.get().getPort("S2");	
	SensorModes colorSensor = new EV3ColorSensor(port);
	SampleProvider colorProvider = ((EV3ColorSensor)colorSensor).getRGBMode();
	float[] sampleR = new float[colorProvider.sampleSize()];
	float[] sampleG = new float[colorProvider.sampleSize()];
	private int r1, r2, g1, g2, b1, b2;
	
	public void calibrateColors() {
		while(Button.ENTER.isUp()) {
			LCD.drawString("Kohdista punaiselle.", 0, 0);
			LCD.drawString("Paina ENTER.", 0, 2);
			colorProvider.fetchSample(sampleR, 0);
			this.r1 = Math.round(sampleR[0]*765);
			this.g1 = Math.round(sampleR[1]*765);
			this.b1 = Math.round(sampleR[2]*765);
		}
		Button.ENTER.waitForPressAndRelease();
		while(Button.ENTER.isUp()) {
			LCD.drawString("Kohdista vihreälle.", 0, 0);
			LCD.drawString("Paina ENTER.", 0, 2);
			colorProvider.fetchSample(sampleG, 0);
			this.r2 = Math.round(sampleG[0]*765);
			this.g2 = Math.round(sampleG[1]*765);
			this.b2 = Math.round(sampleG[2]*765);
		}
		LCD.clear();
	}
}
