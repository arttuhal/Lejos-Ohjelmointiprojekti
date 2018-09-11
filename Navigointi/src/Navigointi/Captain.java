package Navigointi;

import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.ShortestPathFinder;
import lejos.robotics.subsumption.Behavior;

public class Captain implements Behavior {
	private volatile boolean suppressed = false;
	//private final Waypoint[] waypoints = new Waypoint[] { new Waypoint(10, 10), new Waypoint(20, 20), new Waypoint(30, 30), new Waypoint(40, 40) };
	private Navigator nav;
	private ShortestPathFinder etsija;
	private Path lyhin;
	private Tracking tracking;

	public Captain(Navigator nav, Kartta kartta, Tracking tracking) throws DestinationUnreachableException {
		this.nav = nav;
		this.tracking = tracking;
		this.etsija = new ShortestPathFinder(kartta.getKartta());
		this.lyhin = this.etsija.findRoute(tracking.getPose(), new Waypoint(100,100));
		nav.setPath(lyhin);
	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		suppressed = false;
		nav.followPath();
		while (!suppressed)
			Thread.yield();
	}

	@Override
	public void suppress() {
		suppressed = true;

	}

}
