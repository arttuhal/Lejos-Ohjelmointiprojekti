
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.Waypoint;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;

public class Kartta {
	private final Rectangle rect = new Rectangle(-10, 15, 150, -100); // x, y, leveys, korkeus
	private LineMap kartta;
	private final Waypoint[] waypoints = new Waypoint[] { new Waypoint(70, -6), new Waypoint(40, -36),
			new Waypoint(99, -70), new Waypoint(0, 0) };

	// Luodaan esteiden kulmapisteet
	private final Waypoint[] estePisteet1 = new Waypoint[] { new Waypoint(-10, -15), new Waypoint(15, -15) };
	private final Waypoint[] estePisteet2 = new Waypoint[] { new Waypoint(80, -54), new Waypoint(80, -43),
			new Waypoint(110, -54), new Waypoint(110, -43) };
	// private final Waypoint[] estePisteet3 = new Waypoint[] { new Waypoint(30,
	// 15), new Waypoint(30, -15) };

	private List<Waypoint[]> esteet = new ArrayList<Waypoint[]>();

	public Kartta() {
		esteet.add(estePisteet1);
		esteet.add(estePisteet2);
		// esteet.add(estePisteet3);

	}

	public Line[] luoEste() {
		List<Line> reititLista = new ArrayList<>();
		for (int j = 0; j < esteet.size(); j++) {
			for (int i = 0; i < esteet.get(j).length; i++) {
				for (int k = 0; k < esteet.get(j).length; k++) {
					if (i != k) {
						reititLista.add(new Line(esteet.get(j)[i].x, esteet.get(j)[i].y, esteet.get(j)[k].x,
								esteet.get(j)[k].y));
					}
				}
			}
		}
		Line[] reitit = new Line[reititLista.size()];
		reititLista.toArray(reitit);
		return reitit;
	}

	public Rectangle getRect() {
		return rect;
	}

	public LineMap getKartta() {
		kartta = new LineMap(luoEste(), rect);
		return kartta;
	}
	
	public void sendKartta(DataOutputStream out) throws IOException {
		getKartta();
		kartta.dumpObject(out);
		out.flush();
	}

}
