/**
 * Copyright 1998-2012 by Mark Watson. All rights reserved. Originally written
 * by Mark Watson and later review by Rambod Rahmani. The following might be
 * slightly different from the original one.
 */
package chap2.search.maze;

/**
 * This class represents a search space as a 2D maze. The class Maze is used to
 * record the data for a two-dimensional maze, including which grid locations
 * contain walls or obstacles. From an abstract point of view a maze can be seen
 * as a matrix with a certain amount of rows and columns.
 * 
 * @author Rambod Rahmani &lt;rambodrahmani@autistici.org&gt;
 */
public class Maze
{
    // number of rows of the maze
    private int	      rows	    = 0;

    // number of columns of the maze
    private int	      columns	    = 0;

    // starting location of the maze
    private Location  startLocation = new Location();

    // goal location of the maze
    private Location  goalLocation  = new Location();

    /**
     * The maze (or search space) data is stored as a short integer rather than as a
     * boolean so that breath-first style searches can use the array to store search
     * depth. A value of -1 indicates a barrier in the maze.
     */
    private short[][] maze;

    /**
     * Creates a maze with the given number of rows and columns.
     * 
     * @param rows
     *            the number of rows for the maze.
     * @param columns
     *            the number of columns for the maze.
     */
    public Maze(int rows, int columns)
    {
	this.rows = rows;
	this.columns = columns;

	// Rows and columns are increment by 2 in order to be able to add borders to the
	// maze.
	maze = new short[getRows() + 2][getColumns() + 2];

	for (int i = 0; i < getRows() + 2; i++)
	{
	    for (int j = 0; j < getColumns() + 2; j++)
	    {
		maze[i][j] = 0;
	    }
	}

	// Adding top and bottom borders.
	for (int i = 0; i < getColumns() + 2; i++)
	{
	    maze[0][i] = maze[getRows() + 1][i] = MazeSearchGlobals.OBSTACLE;
	}

	// Adding left and right borders.
	for (int i = 0; i < getRows() + 2; i++)
	{
	    maze[i][0] = maze[i][getColumns() + 1] = MazeSearchGlobals.OBSTACLE;
	}

	// Randomizes the maze by putting up arbitrary obstacles.
	int max_obsticles = (getRows() * getColumns()) / 3;
	for (int i = 0; i < max_obsticles; i++)
	{
	    int x = (int) (Math.random() * getRows());
	    int y = (int) (Math.random() * getColumns());
	    setValue(x, y, MazeSearchGlobals.OBSTACLE);
	}

	// Setting the start location.
	// TODO: let the user decide the starting location
	startLocation.setRow(0);
	startLocation.setColumn(0);
	setValue(startLocation, MazeSearchGlobals.START_LOCATION_VALUE);

	// Setting the goal location.
	// TODO: let the user decide the goal location
	goalLocation.setRow(getRows() - 1);
	goalLocation.setColumn(getColumns() - 1);
	setValue(goalLocation, MazeSearchGlobals.GOAL_LOCATION_VALUE);
    }

    /**
     * @param row
     *            the location row index.
     * @param column
     *            the location column index.
     * @param value
     *            the new value for the location in the maze identified by the given
     *            indices row and column.
     */
    synchronized public void setValue(int row, int column, short value)
    {
	maze[row + 1][column + 1] = value;
    }

    /**
     * @see Maze#setValue(int, int, short).
     * @param location
     *            the location in the maze.
     * @param value
     *            the new value for the given location.
     */
    synchronized public void setValue(Location location, short value)
    {
	setValue(location.getRow(), location.getColumn(), value);
    }

    /**
     * @param row
     *            the location row index.
     * @param column
     *            the location column index.
     * @return the current value of the location in the maze identified by the given
     *         indices row and column.
     */
    synchronized public short getValue(int row, int column)
    {
	return maze[row + 1][column + 1];
    }

    /**
     * @return the current number of rows in the maze.
     */
    public int getRows()
    {
	return rows;
    }

    /**
     * @return the current number of columns of the maze.
     */
    public int getColumns()
    {
	return columns;
    }

    /**
     * @return the current starting location in the maze.
     */
    public Location getStartLocation()
    {
	return startLocation;
    }

    /**
     * @return the current goal location in the maze.
     */
    public Location getGoalLocation()
    {
	return goalLocation;
    }

    /**
     * Prints the content of the maze.
     */
    public void print()
    {
	for (int i = 0; i < getRows() + 2; i++)
	{
	    for (int j = 0; j < getColumns() + 2; j++)
	    {
		System.out.print(maze[i][j]);
	    }
	    System.out.println();
	}
    }

    /**
     * Developer test harness.
     * 
     * @param args
     */
    public static void main(String[] args)
    {
	Maze testMaze = new Maze(3, 6);
	testMaze.print();
    }
}
