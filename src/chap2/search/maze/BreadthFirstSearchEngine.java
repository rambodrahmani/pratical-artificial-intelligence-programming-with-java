/**
 * Copyright 1998-2012 by Mark Watson. All rights reserved. Originally written
 * by Mark Watson and later review by Rambod Rahmani. The following might be
 * slightly different from the original one.
 */
package chap2.search.maze;

/**
 * @author Rambod Rahmani &lt;rambodrahmani@autistici.org&gt;
 */
public class BreadthFirstSearchEngine extends AbstractSearchEngine
{
    /**
     * Default constructor.
     * 
     * @param rows
     * @param columns
     */
    public BreadthFirstSearchEngine(int rows, int columns)
    {
	super(rows, columns);

	doSearchOn2DGrid();
    }

    /**
     * 
     */
    private void doSearchOn2DGrid()
    {
	int rows = getMaze().getRows();
	int columns = getMaze().getColumns();

	boolean alReadyVisitedFlag[][] = new boolean[rows][columns];

	// float distanceToNode[][] = new float[width][height];
	Location predecessor[][] = new Location[rows][columns];
	LocationQueue queue = new LocationQueue();

	for (int i = 0; i < rows; i++)
	{
	    for (int j = 0; j < columns; j++)
	    {
		alReadyVisitedFlag[i][j] = false;
		// distanceToNode[i][j] = 10000000.0f;
		predecessor[i][j] = null;
	    }
	}

	alReadyVisitedFlag[getStartLocation().getColumn()][getStartLocation().getRow()] = true;

	// distanceToNode[startLoc.width][startLoc.height] = 0.0f;
	queue.addToBackOfQueue(getStartLocation());
	boolean success = false;

	outer: while (queue.isEmpty() == false)
	{
	    Location head = queue.peekAtFrontOfQueue();

	    if (head == null)
	    {
		break; // ??
	    }

	    Location[] connected = getPossibleMoves(head);

	    for (int i = 0; i < 4; i++)
	    {
		if (connected[i] == null)
		{
		    break;
		}

		int row = connected[i].getRow();
		int column = connected[i].getColumn();
		if (alReadyVisitedFlag[row][column] == false)
		{
		    // distanceToNode[w][h] = distanceToNode[w][h] + 1.0f;
		    alReadyVisitedFlag[row][column] = true;
		    predecessor[row][column] = head;
		    queue.addToBackOfQueue(connected[i]);
		    if (equals(connected[i], getGoalLocation()))
		    {
			success = true;
			break outer; // we are done
		    }
		}
	    }
	    queue.removeFromFrontOfQueue(); // ignore return value
	}

	// now calculate the shortest path from the predecessor array:
	setMaxDepth(0);
	if (success)
	{
	    setSearchPathAt(getMaxDepth(), getGoalLocation());
	    setMaxDepth(getMaxDepth() + 1);
	    for (int i = 0; i < 100; i++)
	    {
		Location predecessorLocation = predecessor[getSearchPathAt(getMaxDepth() - 1)
			.getRow()][getSearchPathAt(getMaxDepth() - 1).getColumn()];

		setSearchPathAt(getMaxDepth(), predecessorLocation);

		setMaxDepth(getMaxDepth() + 1);

		if (equals(getSearchPathAt(getMaxDepth() - 1), getStartLocation()))
		{
		    break; // back to starting node
		}
	    }
	}
    }

    protected class LocationQueue
    {
	private Location[] queue = null;

	private int	   tail	 = -1;

	private int	   head	 = -1;

	private int	   len	 = -1;

	public LocationQueue(int num)
	{
	    queue = new Location[num];
	    head = tail = 0;
	    len = num;
	}

	public LocationQueue()
	{
	    this(400);
	}

	public void addToBackOfQueue(Location n)
	{
	    queue[tail] = n;
	    if (tail >= (len - 1))
	    {
		tail = 0;
	    }
	    else
	    {
		tail++;
	    }
	}

	public Location removeFromFrontOfQueue()
	{
	    Location ret = queue[head];
	    if (head >= (len - 1))
	    {
		head = 0;
	    }
	    else
	    {
		head++;
	    }
	    return ret;
	}

	public boolean isEmpty()
	{
	    return head == (tail + 1);
	}

	public Location peekAtFrontOfQueue()
	{
	    return queue[head];
	}
    }
}
