package Navigointi;

import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.Waypoint;

import java.util.ArrayList;
import java.util.List;

import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;

public class Kartta {
	private final Rectangle rect = new Rectangle(-50, -50, 200, 300); // x, y, leveys, korkeus
	private final Line[] janat = new Line[] { new Line(0, 0, 100, 0), new Line(100, 0, 100, 100),
			new Line(100, 100, 0, 100), new Line(0, 100, 0, 0) };
	private LineMap kartta = new LineMap(janat, rect);
	// Luodaan esteiden kulmapisteet
	private final Waypoint[] estePisteet1 = new Waypoint[] { new Waypoint(50, 0), new Waypoint(100, 0) };
	private final Waypoint[] estePisteet2 = new Waypoint[] { new Waypoint(50, 0), new Waypoint(100, 0) };

	private List<Waypoint[]> esteet;

	public Kartta() {
		esteet.add(estePisteet1);
		esteet.add(estePisteet2);

	}

	public Line[] luoEste() {
		List<Line> reititLista = new ArrayList<>();
		for(int j = 0; j < esteet.size(); j++) {
			for (int i = 0; i < esteet.get(j).length; i++) {
				for (int k = 0; k < esteet.get(j).length; k++) {
					if (i != k) {
						reititLista.add(new Line(esteet.get(j)[i].x, esteet.get(j)[i].y, esteet.get(j)[k].x, esteet.get(j)[k].y));
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

	public Line[] getJanat() {
		return janat;
	}

	public LineMap getKartta() {
		kartta = new LineMap(luoEste(), rect);
		return kartta;
	}

}
