/**
 * Copyright 1998-2012 by Mark Watson. All rights reserved. Originally written
 * by Mark Watson and later review by Rambod Rahmani. The following might be
 * slightly different from the original one.
 */
package chap2.search.maze.depthfirst;

import chap2.search.maze.AbstractSearchEngine;
import chap2.search.maze.Location;

/**
 * @author Rambod Rahmani &lt;rambodrahmani@autistici.org&gt;
 */
public class DepthFirstSearchEngine extends AbstractSearchEngine
{
    private boolean debug = false;

    /**
     * Default constructor. Calls the base class constructor and then solves the
     * search problem by calling the method
     * {@link DepthFirstSearchEngine#iterateSearch(Location, int)}.
     * 
     * @param rows
     * @param columns
     */
    public DepthFirstSearchEngine(int rows, int columns)
    {
	super(rows, columns);

	iterateSearch(getStartLocation(), 1);
    }

    /**
     * @param location
     *            the current location.
     * @param depth
     *            the current search depth.
     */
    private void iterateSearch(Location location, int depth)
    {
	// Checking if at least one solution was found, avoiding more solutions once one
	// path to the goal is found.
	if (isSearching() == false)
	{
	    return;
	}

	/*
	 * If debugging is enabled (debug = true), this will print the current location
	 * as the recursive search proceeds.
	 */
	if (debug)
	{
	    System.out.println("Current location: (" + location.getRow() + ", " + location.getColumn() + ").");
	}

	// We set the maze value to the depth for display purposes only.
	getMaze().setValue(location, (short) depth);

	/*
	 * Here, we use the super class getPossibleMoves method to get an array of
	 * possible neighboring squares that we could move to;
	 */
	Location[] moves = getPossibleMoves(location);

	/*
	 * If debugging is enabled (debug = true), this will print the possible moves
	 * from the current location as the recursive search proceeds.
	 */
	if (debug)
	{
	    System.out.println("Possible moves:");
	    for (int i = 0; i < moves.length; i++)
	    {
		if (moves[i] != null)
		{
		    System.out.print(moves[i].toString() + ", ");
		}
		System.out.println();
	    }
	}

	/*
	 * we then loop over the four possible moves (a null value in the array
	 * indicates an illegal move):
	 */
	for (int i = 0; i < 4; i++)
	{
	    if (moves[i] == null)
	    {
		// a null value in the array indicates an illegal move
		break;
	    }

	    // Record the next move in the search path array
	    setSearchPathAt(depth, moves[i]);

	    // and check to see if we are done:
	    if (equals(moves[i], getGoalLocation()))
	    {
		System.out.println("Found the goal at " + moves[i].getRow() + ", " + moves[i].getColumn());
		stopSearching();
		setMaxDepth(depth);
		return;
	    }
	    else
	    {
		/*
		 * If the next possible move is not the goal move, we recursively call the
		 * iterateSearch method again, but starting from this new location and
		 * increasing the depth counter by one:
		 */
		iterateSearch(moves[i], depth + 1);
		if (isSearching() == false)
		{
		    return;
		}
	    }
	}
	return;
    }

    /**
     * @param debug
     *            true if debugging messages are needed, false otherwise.
     */
    public void setDebug(boolean debug)
    {
	this.debug = debug;
    }
}
