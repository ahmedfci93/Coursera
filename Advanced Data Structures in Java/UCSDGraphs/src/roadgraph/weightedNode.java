package roadgraph;

import java.util.Comparator;

import geography.GeographicPoint;

public class weightedNode implements Comparable<weightedNode> {
	private GeographicPoint node;
	private double weight;
	
	public weightedNode(GeographicPoint node) {
		super();
		this.node = node;
		this.weight = Double.POSITIVE_INFINITY;
	}
	
	public GeographicPoint getNode() {
		return node;
	}

	public void setNode(GeographicPoint node) {
		this.node = node;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	

	@Override
	public int compareTo(weightedNode x) {
		if(this.weight<x.getWeight())return -1;
		if(this.weight>x.getWeight())return 1;
		return 0;
	}
	
	
	
}
