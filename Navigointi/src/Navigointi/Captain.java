package Navigointi;

import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.ShortestPathFinder;
import lejos.robotics.subsumption.Behavior;

public class Captain implements Behavior {
	private volatile boolean suppressed = false;
	private final Waypoint[] waypoints = new Waypoint[] { new Waypoint(70, -6), new Waypoint(40, -36), new Waypoint(99, -70), new Waypoint(0, 0) };
	private Navigator nav;
	private ShortestPathFinder etsija;
	private Path lyhin;
	private Tracking tracking;
	private int viimeisin = 0;

	public Captain(Navigator nav, Kartta kartta, Tracking tracking) throws DestinationUnreachableException {
		this.nav = nav;
		this.tracking = tracking;
		this.etsija = new ShortestPathFinder(kartta.getKartta());
		this.etsija.lengthenLines(20);
		this.lyhin = etsiLyhinReitti(etsija, tracking, waypoints[0]);	
		
		nav.setPath(lyhin);
	}
	
	public Path etsiLyhinReitti(ShortestPathFinder etsija, Tracking tracking, Waypoint waypoint) {
		Path lyhin = new Path();
		try {
			lyhin = this.etsija.findRoute(tracking.getPose(), waypoint);
		} catch (DestinationUnreachableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lyhin;
		
	}
	

	@Override
	public boolean takeControl() {
		this.lyhin = etsiLyhinReitti(etsija, tracking, waypoints[viimeisin]);
		this.nav.setPath(lyhin);
		return true;
	}

	@Override
	public void action() {
		suppressed = false;
		nav.followPath();
		while (nav.isMoving()){}
			//Thread.yield();
		viimeisin++;
		if(viimeisin >= 4) {
			System.exit(0);
		}
		Laadunvalvoja.aloitaTyo();
	}

	@Override
	public void suppress() {
		suppressed = true;

	}

}
