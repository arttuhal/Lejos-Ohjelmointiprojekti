package Navigointi;

import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class Tyoskentele implements Behavior{
	private volatile boolean suppressed = false;
	private Boolean pisteella = false;
	
	public void onPisteella() {
		pisteella = true;
	}

	@Override
	public boolean takeControl() {
		if(pisteella) {
			return true;
		}
		return false;
	}

	@Override
	public void action() {
		suppressed = false;
		LCD.drawString("TYOSKENTELEE", 0, 3);
		while (!suppressed)
			try {
				Thread.sleep(3000);
				suppress();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		pisteella = false;
		LCD.clear();
		
	}

	@Override
	public void suppress() {
		suppressed = true;
		
	}

}
