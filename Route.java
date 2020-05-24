package distance;

import java.util.ArrayList;

public class Route {
	private ArrayList<String> stops;
	private double cost;
	private double time;
	
	public Route(ArrayList<String> s, double c, double t)
	{
		stops = s;
		cost = c;
		time = t;
	}
	
	public double getCost()
	{
		return cost;
	}
	public double getTime()
	{
		return time;
	}
	public double getStops()
	{
		return stops.size();
	}
	public ArrayList<String> getStopList()
	{
		return stops;
	}
	public void setStops(ArrayList<String> ar)
	{
		stops = ar;
	}
	public void addStop(String st)
	{
		stops.add(st);
		
	}
	public void addCost(double cos)
	{
		cost += cos;
	}
	public void addTime(double tim)
	{
		time += tim;
	}
}
