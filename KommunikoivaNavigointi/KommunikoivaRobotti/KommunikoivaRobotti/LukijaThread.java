package KommunikoivaRobotti;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.Waypoint;

public class LukijaThread extends Thread {

	private DataInputStream in;
	private DataOutputStream out;
	private LineMap kartta;
	private boolean lopeta = false;
	private Random rand = new Random();
	private int counter = 0;
	private final Waypoint[] waypoints = new Waypoint[] { new Waypoint(70, -6), new Waypoint(40, -36),
			new Waypoint(99, -70), new Waypoint(0, 0) };


	public LukijaThread(DataInputStream in,DataOutputStream out, Kartta map) {
		this.in = in;
		this.out = out;
		try {
			kartta.loadObject(in);
			map.setKartta(kartta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//setDaemon(true);
	}

	public void run() {
		while (!lopeta) {
			try {
				if(!Laadunvalvoja.tyoValmis) {
					out.writeUTF("havainto");
					out.writeFloat(waypoints[counter].x);
					out.writeFloat(waypoints[counter].y);
					out.writeLong(System.currentTimeMillis());
					out.writeInt(rand.nextInt(100));
					out.flush();
					counter++;
				}
				while(Laadunvalvoja.tyoValmis) {}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void lopeta() {
		lopeta = true;
	}
}
