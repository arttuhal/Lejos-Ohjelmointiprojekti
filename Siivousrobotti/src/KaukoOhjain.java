import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3IRSensor;

public class KaukoOhjain extends Thread{
	private EV3IRSensor infraredSensor;
	private int channel = 3, command;
	
	public KaukoOhjain(EV3IRSensor infraredSensor) {
		this.infraredSensor = infraredSensor;
	}
	
	public void run() {
		try {
			while (true) {
				Thread.sleep(200);
				command = infraredSensor.getRemoteCommand(channel);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int getCommand() {
		return command;
	}

}
