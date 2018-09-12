package Navigointi;

import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class Tyoskentele implements Behavior{
	private volatile boolean suppressed = false;
	

	@Override
	public boolean takeControl() {
		if(!Laadunvalvoja.tyoValmis) {
			return true;
		}
		return false;
	}

	@Override
	public void action() {
		suppressed = false;
		LCD.drawString("TYOSKENTELEE", 0, 5);
		while (!suppressed)
			try {
				Thread.sleep(1000);
				suppress();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Laadunvalvoja.jatkaMatkaa();
		LCD.clear();
		
	}

	@Override
	public void suppress() {
		suppressed = true;
		
	}

}
