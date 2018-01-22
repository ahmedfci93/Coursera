/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.*;
import java.util.function.Consumer;

import javax.transaction.xa.Xid;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 3
	private HashMap<GeographicPoint,ArrayList<edgeInfo>> graph=null;// graph has its vertices and information of each edge  which is out-going edge from vertices 
	private int numEdges;//counter of edges
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 3
		// creation of graph
		graph= new HashMap< GeographicPoint, ArrayList<edgeInfo> >();
		// initialization of edge number
		numEdges=0;
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 3
		//number of vertices in graph
		return graph.keySet().size();
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 3
		//all vertices in graph
		return graph.keySet();
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		//TODO: Implement this method in WEEK 3
		return numEdges;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 3
		if(!graph.containsKey(location))//check if vertex added before.
		{
			ArrayList<edgeInfo> neighbours =new ArrayList<edgeInfo>();
			graph.put(location,neighbours);
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {

		//TODO: Implement this method in WEEK 3
		//gatesCondition to check all validations are correct
		gateConditions(from, to, roadName, roadType, length);
		/*
		 * edgeInfo object has all information of edge out-going from GeographicPoint
		 * like destination Geographic point, roadName, roadType, Length.
		 * */
		edgeInfo edge=new edgeInfo();
		edge.setTo(to);
		edge.setRoadName(roadName);
		edge.setRoadType(roadType);
		edge.setLength(length);
		// add to graph a vertex and its information of out-going edge from it
		graph.get(from).add(edge);
		numEdges++;//count edge
	}
	
	public void gateConditions(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length)
	{
		if(!graph.containsKey(from)||!graph.containsKey(to))
			throw new IllegalArgumentException("From Or To GeGeographicPoints equal null");
		if(roadName.equals(null)||roadType.equals(null)||length==0)
			throw new IllegalArgumentException("road name Or road type equal null");
		if(length==0)
			throw new IllegalArgumentException("length equals zero");
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		if(start.equals(null)||goal.equals(null)||
				!graph.containsKey(start)||!graph.containsKey(goal))return null;
		Queue<GeographicPoint> queue= new LinkedList<GeographicPoint>();
		List<GeographicPoint> visited=new ArrayList<GeographicPoint>();
		HashMap<GeographicPoint,GeographicPoint> parents=new HashMap<GeographicPoint,GeographicPoint>();
		Boolean found=false;
		queue.add(start);
		visited.add(start);
		while(!queue.isEmpty())
		{
			GeographicPoint currPoint= queue.remove();
			nodeSearched.accept(currPoint);
			
				if(currPoint.equals(goal))
				{
					found=true;
					break;
				}
				for (edgeInfo next : graph.get(currPoint)) {
					if(!visited.contains(next.getTo()))
					{
						queue.add(next.getTo());
						parents.put(next.getTo(), currPoint);
						visited.add(next.getTo());
					}
				}
		}
		if(!found)return null;
		
		return constructPath(start, goal,parents);
	}
	private List<GeographicPoint> constructPath(GeographicPoint start, 
		     GeographicPoint goal,HashMap<GeographicPoint,GeographicPoint> parents)
	{
		LinkedList<GeographicPoint> path=new LinkedList<GeographicPoint>();
		path.addFirst(goal);
		GeographicPoint position=goal;
		while(position!=start)
		{
			path.addFirst(parents.get(position));
			position=parents.get(position);
		}
		return path;
	}
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		if(start.equals(null)||goal.equals(null)||
				!graph.containsKey(start)||!graph.containsKey(goal))return null;
		PriorityQueue<weightedNode> pQueue= new PriorityQueue<>();
		List<GeographicPoint> visited=new ArrayList<GeographicPoint>();
		HashMap<GeographicPoint,GeographicPoint> parents=new HashMap<GeographicPoint,GeographicPoint>();
		HashMap<GeographicPoint,Double> distances=new HashMap<>();
		Boolean found=false;
		weightedNode sNode=new weightedNode(start);
		int cnt=0;
		sNode.setWeight(0.0);
		pQueue.add(sNode);
		distances.put(start, 0.0);
		while(!pQueue.isEmpty())
		{
			// Hook for visualization.  See writeup.
			//nodeSearched.accept(next.getLocation());
			weightedNode curr=pQueue.remove();
			cnt++;
			if(!visited.contains(curr.getNode()))
			{
				visited.add(curr.getNode());
				nodeSearched.accept(curr.getNode());
				if(curr.getNode().equals(goal))
				{
					System.out.println("visited node in Dijkastra algorithm "+cnt);
					found=true;
					break;
				}
				ArrayList<edgeInfo> neighbours = graph.get(curr.getNode());
				for (edgeInfo next : neighbours) {
					if(!visited.contains(next.getTo()))
					{
						if((!distances.containsKey(next.getTo()))||
								(distances.containsKey(next.getTo())&&
								next.getLength()+curr.getWeight()<distances.get(next.getTo()) ))
						{
							weightedNode newNode=new weightedNode(next.getTo());
							newNode.setWeight(next.getLength()+curr.getWeight());
							distances.put(next.getTo(),next.getLength()+curr.getWeight() );
							pQueue.add(newNode);
							parents.put(next.getTo(), curr.getNode());
						}	
					}
				}	
			}
		}
		if(!found)return null;
		return constructPath(start, goal,parents) ;
	}
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		if(start.equals(null)||goal.equals(null)||
				!graph.containsKey(start)||!graph.containsKey(goal))return null;
		int cnt=0;
		PriorityQueue<weightedNode> pQueue= new PriorityQueue<>();
		List<GeographicPoint> visited=new ArrayList<GeographicPoint>();
		HashMap<GeographicPoint,GeographicPoint> parents=new HashMap<GeographicPoint,GeographicPoint>();
		HashMap<GeographicPoint,Double> distances=new HashMap<>();
		Boolean found=false;
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		double fN= 0.0;
		weightedNode sn=new weightedNode(start);
		sn.setWeight(fN);
		pQueue.add(sn);
		distances.put(start, fN);
		while(!pQueue.isEmpty())
		{
			weightedNode curr=pQueue.remove();
			cnt++;
			if(!visited.contains(curr.getNode()))
			{
				visited.add(curr.getNode());
				
				nodeSearched.accept(curr.getNode());
				if(curr.getNode().equals(goal))
				{	
					System.out.println("visited node in A* algorithm "+cnt);
					found=true;
					break;
				}
				ArrayList<edgeInfo> neighbours= graph.get(curr.getNode());
				for (edgeInfo next : neighbours) {
					if(!visited.contains(next.getTo()))
					{
						fN= curr.getNode().distance(next.getTo())+goal.distance(next.getTo());
						if((!distances.containsKey(next.getTo()))||
								(distances.containsKey(next.getTo())&&
								fN<distances.get(next.getTo()) ))
						{
							weightedNode newNode=new weightedNode(next.getTo());
							newNode.setWeight(fN);
							distances.put(next.getTo(),fN);
							pQueue.add(newNode);
							parents.put(next.getTo(), curr.getNode());
						}	
					}
				}
			}
			
		}

		if(!found)return null;
		return constructPath(start, goal,parents) ;
	}

	
	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");
	    MapGraph simpleTestMap = new MapGraph();
			GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
			
			GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
			GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
			
			System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
			List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
			List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
			
			
			MapGraph testMap = new MapGraph();
			GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
			
			// A very simple test using real data
			testStart = new GeographicPoint(32.869423, -117.220917);
			testEnd = new GeographicPoint(32.869255, -117.216927);
			System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
			testroute = testMap.dijkstra(testStart,testEnd);
			testroute2 = testMap.aStarSearch(testStart,testEnd);
			
			
			// A slightly more complex test using real data
			testStart = new GeographicPoint(32.8674388, -117.2190213);
			testEnd = new GeographicPoint(32.8697828, -117.2244506);
			System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
			testroute = testMap.dijkstra(testStart,testEnd);
			testroute2 = testMap.aStarSearch(testStart,testEnd);
		// You can use this method for testing.  
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		/*
		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		
		
		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		*/
		
		
		/* Use this code in Week 3 End of Week Quiz */
		/*MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		*/
		
	}
}
