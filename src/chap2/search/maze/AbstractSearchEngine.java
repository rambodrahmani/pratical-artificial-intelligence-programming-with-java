/**
 * Copyright 1998-2012 by Mark Watson. All rights reserved. Originally written
 * by Mark Watson and later review by Rambod Rahmani. The following might be
 * slightly different from the original one.
 */
package chap2.search.maze;

/**
 * 2D Maze Search. This abstract class contains common code and data that is
 * required by both the depth first (uses a stack to to store moves) search
 * class {@link DepthFirstSearchEngine} and the breadth first (uses a queue to
 * store moves) search class {@link BreadthFirstSearchEngine}.
 * 
 * @author Rambod Rahmani &lt;rambodrahmani@autistici.org&gt;
 */
public class AbstractSearchEngine
{
    /**
     * Internal maze instance. This maze has a startLocation and a goalLocation and
     * can be solved using either a DepthFirst or BreadThird search engine.
     */
    private Maze       maze	       = null;

    // The current search path from the starting location to the goal location
    private Location[] searchPath      = null;

    private int	       pathCount       = 0;

    private int	       maxDepth	       = 0;

    private Location   currentLocation = null;

    // isSearching is used to halt searching, avoiding more solutions, once one path
    // to the goal is found.
    private boolean    isSearching     = true;

    /**
     * @param rows
     * @param columns
     */
    public AbstractSearchEngine(int rows, int columns)
    {
	maze = new Maze(rows, columns);

	initSearch(rows > columns ? rows : columns);
    }

    /**
     * Utility function: allocates the array searchPath of Location objects, which
     * will be used to record the path traversed through the maze.
     */
    private void initSearch(int dim)
    {
	if (searchPath == null)
	{
	    searchPath = new Location[dim * 100];
	    for (int i = 0; i < searchPath.length; i++)
	    {
		searchPath[i] = new Location();
	    }
	}

	pathCount = 0;

	currentLocation = maze.getStartLocation();

	searchPath[pathCount++] = currentLocation;
    }

    /**
     * Two locations are considered to be equal if they have the same row and column
     * indices.
     * 
     * @param l1
     *            the first location to be compared.
     * @param l2
     *            the second location to be compared.
     * @return true if location l1 and location l2 are equal.
     */
    protected boolean equals(Location l1, Location l2)
    {
	return l1.getRow() == l2.getRow() && l1.getColumn() == l2.getColumn();
    }

    /**
     * Utility function used to calculate possible moves from the given location.
     * This search engine only moves forward, backward, to the right and to the
     * left.
     * 
     * @param location
     *            the current location.
     * @return an array of Location objects that can be moved to from the specified
     *         current location.
     */
    protected Location[] getPossibleMoves(Location location)
    {
	// forward, backward, to the right, to the left
	Location tempMoves[] = new Location[4];

	tempMoves[0] = tempMoves[1] = tempMoves[2] = tempMoves[3] = null;

	int row = location.getRow();
	int column = location.getColumn();

	// The location object index in tempMoves[].
	int index = 0;

	// move forward
	if (maze.getValue(row + 1, column) == 0
		|| maze.getValue(row + 1, column) == MazeSearchGlobals.GOAL_LOCATION_VALUE)
	{
	    tempMoves[index++] = new Location(row + 1, column);
	}

	// move backward
	if (maze.getValue(row - 1, column) == 0
		|| maze.getValue(row - 1, column) == MazeSearchGlobals.GOAL_LOCATION_VALUE)
	{
	    tempMoves[index++] = new Location(row - 1, column);
	}

	// move to the right
	if (maze.getValue(row, column - 1) == 0
		|| maze.getValue(row, column - 1) == MazeSearchGlobals.GOAL_LOCATION_VALUE)
	{
	    tempMoves[index++] = new Location(row, column - 1);
	}

	// move to the left
	if (maze.getValue(row, column + 1) == 0
		|| maze.getValue(row, column + 1) == MazeSearchGlobals.GOAL_LOCATION_VALUE)
	{
	    tempMoves[index++] = new Location(row, column + 1);
	}

	return tempMoves;
    }

    /**
     * @return the current maze.
     */
    public Maze getMaze()
    {
	return maze;
    }

    /**
     * @return the search path for the current search max depth.
     */
    public Location[] getSearchPath()
    {
	Location[] ret = new Location[maxDepth];
	for (int i = 0; i < maxDepth; i++)
	{
	    ret[i] = searchPath[i];
	}
	return ret;
    }

    /**
     * @param position
     *            the position of the required location in the search path.
     * @return the location in the search path at the given position.
     */
    public Location getSearchPathAt(int position)
    {
	return searchPath[position];
    }

    /**
     * @param position
     *            the position in the search path for the location to set.
     * @param location
     *            the location to set in the given position.
     */
    public void setSearchPathAt(int position, Location location)
    {
	searchPath[position] = location;
    }

    /**
     * @return the current path count of the search in the maze.
     */
    public int getPathCount()
    {
	return pathCount;
    }

    /**
     * @return the current max depth of the search in the maze.
     */
    public int getMaxDepth()
    {
	return maxDepth;
    }

    /**
     * @param maxDepth
     *            the max depth of the search in the maze to set.
     */
    public void setMaxDepth(int maxDepth)
    {
	this.maxDepth = maxDepth;
    }

    /**
     * @return
     */
    public Location getStartLocation()
    {
	return maze.getStartLocation();
    }

    /**
     * @return
     */
    public Location getGoalLocation()
    {
	return maze.getGoalLocation();
    }

    /**
     * @return
     */
    public Location getCurrentLocation()
    {
	return currentLocation;
    }

    /**
     * @return the current isSearching.
     */
    public boolean isSearching()
    {
	return isSearching;
    }

    /**
     * Sets isSearching to false, isSearching is used to halt searching, avoiding
     * more solutions, once one path to the goal is found.
     */
    public void stopSearching()
    {
	isSearching = false;
    }
}
