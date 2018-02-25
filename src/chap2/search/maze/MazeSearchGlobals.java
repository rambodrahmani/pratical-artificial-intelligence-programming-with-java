/**
 * Copyright 1998-2012 by Mark Watson. All rights reserved. Originally written
 * by Mark Watson and later review by Rambod Rahmani. The following might be
 * slightly different from the original one.
 */
package chap2.search.maze;

/**
 * This class contains constants used in the package {@like chap2.search.maze}.
 * 
 * @author Rambod Rahmani &lt;rambodrahmani@autistici.org&gt;
 */
public class MazeSearchGlobals
{
    // A value of -1 indicates a barrier in the maze.
    public static final short OBSTACLE		   = -1;

    // A value of -2 indicates the starting location in the maze.
    public static final short START_LOCATION_VALUE = -2;

    // A value of -2 indicates the goal location in the maze.
    public static final short GOAL_LOCATION_VALUE  = -3;
}

/**
 * Constants should be: public - so that it can be accessed from anywhere<br />
 * static - no need to create an instance<br />
 * final - since its constants shouldn't be allowed to change<br />
 * As per Java naming convention should be capitalized so that easy to read and
 * stands out in Java documentation.
 */
