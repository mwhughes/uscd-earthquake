package module6;


import java.util.List;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PGraphics;

/** Implements a common marker for cities and earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 *
 */
public abstract class CommonMarker extends SimplePointMarker {

	// Records whether this marker has been clicked (most recently)
	protected boolean clicked = false;
	private Marker nearCity = null;
	private Marker nearQuake = null;
	private boolean isNearestClicked = false;
	private double distanceFromNearest = 0.0;
	
	public CommonMarker(Location location) {
		super(location);
		
	}
	
	public CommonMarker(Location location, java.util.HashMap<java.lang.String,java.lang.Object> properties) {
		super(location, properties);
	}
	
	// Getter method for clicked field
	public boolean getClicked() {
		return clicked;
	}
	
	// Setter method for clicked field
	public void setClicked(boolean state) {
		clicked = state;
	}
	
	// Common piece of drawing method for markers; 
	// YOU WILL IMPLEMENT. 
	// Note that you should implement this by making calls 
	// drawMarker and showTitle, which are abstract methods 
	// implemented in subclasses
	public void draw(PGraphics pg, float x, float y) {
		// For starter code just drawMaker(...)
		if (!hidden) {
			drawMarker(pg, x, y);
			if (selected) {
				showTitle(pg, x, y);
			}
		}
	}
	public void setNearCity(List<Marker> cityMarkers)
	{
		Boolean isCity = this.getClass() == cityMarkers.get(1).getClass();
		if (isCity)
		{
			this.nearCity = (Marker) this;
		}
		
		else 
		{ 
			Marker tempNearest = cityMarkers.get(1);
			double minDis = this.getDistanceTo(tempNearest.getLocation());
			double curDis = 0.0;
			for (Marker m: cityMarkers)
			{
				curDis =  this.getDistanceTo(m.getLocation());
				if (curDis<minDis)
				{
					minDis = curDis;
					tempNearest = m;
				}
			}
			
			this.nearCity = tempNearest;
		}
	
	}
	
	public void setNearQuake(List<Marker> quakeMarkers)
	{
	
		Boolean isQuake = this.getClass() == quakeMarkers.get(1).getClass();
		if (isQuake)
		{
			this.nearQuake = (Marker) this;
		}
		
		else 
		{ 
			Marker tempNearest = quakeMarkers.get(1);
			double minDis = this.getDistanceTo(tempNearest.getLocation());
			double curDis = 0.0;
			for (Marker m: quakeMarkers)
			{
				curDis =  this.getDistanceTo(m.getLocation());
				if (curDis<minDis)
				{
					minDis = curDis;
					tempNearest = m;
				}
			}
			
			this.nearQuake = tempNearest;
		}
	}
	
	public Marker getNearQuake()
	{
	return nearQuake;
	}
	
	public Marker getNearCity()
	{
	return nearCity;
	}
	
	public boolean getWhetherisNearest()
	{
		return isNearestClicked;
	}
	
	public void setWhetherisNearest(boolean tValue)
	{
		isNearestClicked = tValue;
	}
	
	public void setDistanceNearest(Marker m)
	{
		this.distanceFromNearest = this.getDistanceTo(m.getLocation());
	}
	
	public double getDistanceNearest()
	{
		return this.distanceFromNearest;
	}
	
	public abstract void drawMarker(PGraphics pg, float x, float y);
	public abstract void showTitle(PGraphics pg, float x, float y);
}