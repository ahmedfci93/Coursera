package roadgraph;

import geography.GeographicPoint;
/*
 * edgeInfo class has all information of edge out-going from GeographicPoint
 * like destination Geographic point, roadName, roadType, Length.
 * */
public class edgeInfo {
	private GeographicPoint to;//Destination
	private String roadName;//road name of edge.
	private String roadType;//road type of edge.
	private double length;//length of edge.
	
	public GeographicPoint getTo() {
		return to;
	}
	public void setTo(GeographicPoint to) {
		this.to = to;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getRoadType() {
		return roadType;
	}
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	
}
