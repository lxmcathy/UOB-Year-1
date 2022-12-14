package dsa_assignment2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * A Drone class to simulate the decisions and information collected by a drone
 * on exploring an underground maze.
 * 
 */
public class Drone implements DroneInterface
{
	private static final Logger logger     = Logger.getLogger(Drone.class);
	
	public String getStudentID()
	{
		//change this return value to return your student id number
		return "2018963";
	}

	public String getStudentName()
	{
		//change this return value to return your name
		return "XUEMENG LI";
	}

	/**
	 * The Maze that the Drone is in
	 */
	private Maze                maze;

	/**
	 * The stack containing the portals to backtrack through when all other
	 * doorways of the current chamber have been explored (see assignment
	 * handout). Note that in Java, the standard collection class for both
	 * Stacks and Queues are Deques
	 */
	private Deque<Portal>       visitStack = new ArrayDeque<>();

	/**
	 * The set of portals that have been explored so far.
	 */
	private Set<Portal>         visited    = new HashSet<>();

	/**
	 * The Queue that contains the sequence of portals that the Drone has
	 * followed from the start
	 */
	private Deque<Portal>       visitQueue = new ArrayDeque<>();

	/**
	 * This constructor should never be used. It is private to make it
	 * uncallable by any other class and has the assert(false) to ensure that if
	 * it is ever called it will throw an exception.
	 */
	@SuppressWarnings("unused")
	private Drone()
	{
		assert (false);
	}

	/**
	 * Create a new Drone object and place it in chamber 0 of the given Maze
	 * 
	 * @param maze
	 *            the maze to put the Drone in.
	 */
	public Drone(Maze maze)
	{
		this.maze = maze;
	}

	/* 
	 * @see dsa_assignment2.DroneInterface#searchStep()
	 */
	@Override
	public Portal searchStep()
	{
		int i = 0;
		for(i = 0; i <maze.getNumDoors(); i++) {
			Portal portalOut = new Portal(maze.getCurrentChamber(), i);
			if(!visited.contains(new Portal(maze.getCurrentChamber(), i))) {
				Portal portalIn = maze.traverse(i);
				visited.add(portalOut);
				visited.add(portalIn);
				visitQueue.add(portalOut);
				visitQueue.add(portalIn);
				visitStack.add(portalIn);
				return portalIn;
			}
		}
		if(visitStack.isEmpty()) {
			return null;
		}else {
			Portal portalOut = visitStack.removeLast();
			Portal portalIn = maze.traverse(portalOut.getDoor());
			visited.add(portalOut);
			visited.add(portalIn);
			visitQueue.add(portalOut);
			visitQueue.add(portalIn);
			return portalIn;
		}
	}

	/* 
	 * @see dsa_assignment2.DroneInterface#getVisitOrder()
	 */
	@Override
	public Portal[] getVisitOrder()
	{
 
		return visitQueue.toArray(new Portal[0]);
	}


	/*
	 * @see dsa_assignment2.DroneInterface#findPathBack()
	 */
	@Override
	public Portal[] findPathBack()
	{
		Deque<Portal> d1 = new ArrayDeque<>();
		Deque<Portal> d2 = new ArrayDeque<>();
		List<Portal> pass = new ArrayList<Portal>();
		Set<Integer> chamber = new HashSet<>();
		pass.addAll(visitStack);
		
		if (maze.getCurrentChamber() == 0) {
			return (Portal[])d1.toArray(new Portal[0]);
		}
		
		for(int i = pass.size()-1; i >= 0; i--) {

			if(!chamber.add(pass.get(i).getChamber())) {
				d2.add(pass.get(i));
				return  (Portal[])d2.toArray(new Portal[0]);
			}
			d1.add(pass.get(i));
		}
		
		return  (Portal[])d1.toArray(new Portal[0]);
	}
}