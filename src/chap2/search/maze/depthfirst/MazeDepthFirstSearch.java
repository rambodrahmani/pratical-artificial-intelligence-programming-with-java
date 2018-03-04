/**
 * Copyright 1998-2012 by Mark Watson. All rights reserved. Originally written
 * by Mark Watson and later review by Rambod Rahmani. The following might be
 * slightly different from the original one.
 */
package chap2.search.maze.depthfirst;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import javax.swing.DebugGraphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import chap2.search.maze.Location;
import chap2.search.maze.Maze;
import chap2.search.maze.MazeSearchGlobals;

/**
 * @author Rambod Rahmani &lt;rambodrahmani@autistici.org&gt;
 */
public class MazeDepthFirstSearch extends JFrame
{
    /**
     * The serialization runtime associates with each serializable class a version
     * number, called a serialVersionUID, which is used during deserialization to
     * verify that the sender and receiver of a serialized object have loaded
     * classes for that object that are compatible with respect to serialization. If
     * the receiver has loaded a class for the object that has a different
     * serialVersionUID than that of the corresponding sender's class, then
     * deserialization will result in an InvalidClassException. A serializable class
     * can declare its own serialVersionUID explicitly by declaring a field named
     * "serialVersionUID" that must be static, final, and of type long:
     */
    // ANY-ACCESS-MODIFIER static final long serialVersionUID = 42L;
    /**
     * If a serializable class does not explicitly declare a serialVersionUID, then
     * the serialization runtime will calculate a default serialVersionUID value for
     * that class based on various aspects of the class, as described in the
     * Java(TM) Object Serialization Specification. However, it is strongly
     * recommended that all serializable classes explicitly declare serialVersionUID
     * values, since the default serialVersionUID computation is highly sensitive to
     * class details that may vary depending on compiler implementations, and can
     * thus result in unexpected InvalidClassExceptions during deserialization.
     * Therefore, to guarantee a consistent serialVersionUID value across different
     * java compiler implementations, a serializable class must declare an explicit
     * serialVersionUID value. It is also strongly advised that explicit
     * serialVersionUID declarations use the private modifier where possible, since
     * such declarations apply only to the immediately declaring
     * class--serialVersionUID fields are not useful as inherited members.
     */
    private static final long serialVersionUID	     = 6440782002968347265L;

    //
    final JPanel	      jPanel		     = new JPanel();

    //
    DepthFirstSearchEngine    depthFirstSearchEngine = null;

    /**
     * Default constructor. Creates a {@link DepthFirstSearchEngine} and starts the
     * depth first search on a maze with the specified number of rows and columns.
     * 
     * @param rows
     *            the number of rows of the maze.
     * @param columns
     *            the number of columns of the maze.
     */
    public MazeDepthFirstSearch(int rows, int columns)
    {
	depthFirstSearchEngine = new DepthFirstSearchEngine(rows, columns);

	try
	{
	    initialize();
	}
	catch (Exception e)
	{
	    System.out.println("GUI initilization error: " + e);
	}

	/**
	 * Repaints this component.<br>
	 * If this component is a lightweight component, this method causes a call to
	 * this component's <code>paint</code> method as soon as possible. Otherwise,
	 * this method causes a call to this component's <code>update</code> method as
	 * soon as possible.
	 */
	repaint();
    }

    /*
     * (non-Javadoc)
     * @see java.awt.Window#paint(java.awt.Graphics)
     */
    public void paint(Graphics g)
    {
	if (depthFirstSearchEngine == null)
	{
	    return;
	}

	// Getting the dimensions of the maze to draw.
	final Maze maze = depthFirstSearchEngine.getMaze();
	final int mazeRows = maze.getRows();
	final int mazeColumns = maze.getColumns();

	Graphics jPnaleGraphics = jPanel.getGraphics();

	// BufferedImage used to represent the maze in the JPanel.
	BufferedImage mazeImage = new BufferedImage(mazeColumns * 31, mazeRows * 31, BufferedImage.TYPE_INT_RGB);
	Graphics imageGraphics = mazeImage.createGraphics();
	imageGraphics.setColor(Color.white);
	imageGraphics.fillRect(0, 0, mazeColumns * 31, mazeRows * 31);
	imageGraphics.setColor(Color.black);

	// Drawing the maze.
	for (int i = 0; i < mazeRows; i++)
	{
	    for (int j = 0; j < mazeColumns; j++)
	    {
		short val = maze.getValue(i, j);
		if (val == MazeSearchGlobals.OBSTACLE)
		{
		    imageGraphics.setColor(Color.lightGray);
		    imageGraphics.fillRect(6 + j * 29, 3 + i * 29, 29, 29);
		}
		else if (val == MazeSearchGlobals.START_LOCATION_VALUE || val == 1)
		{
		    imageGraphics.setColor(Color.blue);
		    imageGraphics.drawString("S", 17 + j * 29, 22 + i * 29);
		}
		else if (val == MazeSearchGlobals.GOAL_LOCATION_VALUE)
		{
		    imageGraphics.setColor(Color.red);
		    imageGraphics.drawString("G", 17 + j * 29, 22 + i * 29);
		}

		// Drawing the outline for each square in the maze.
		imageGraphics.setColor(Color.black);
		imageGraphics.drawRect(6 + j * 29, 3 + i * 29, 29, 29);
	    }
	}

	// Drawing the path in black.
	imageGraphics.setColor(Color.black);
	final Location[] path = depthFirstSearchEngine.getSearchPath();
	for (int i = 1; i < path.length; i++)
	{
	    final int row = path[i].getRow();
	    final int column = path[i].getColumn();
	    final short val = maze.getValue(row, column);

	    /*
	     * This offset is used to center the value of the depth in the search path.
	     * Generally, this offset works fine with values from 0 to 150 in mazes with 30
	     * rows and 30 columns.
	     */
	    int offset = (int) ((column * 0.6) - (Integer.toString(val).length() * 0.01));
	    imageGraphics.drawString("" + val, 17 + column * 28 + offset, 22 + row * 29);
	}

	// Drawing the BufferedImage representing the maze on the JPanel
	jPnaleGraphics.drawImage(mazeImage, 10, 10, mazeColumns * 31, mazeRows * 31, null);
    }

    /**
     * @throws Exception
     */
    private void initialize() throws Exception
    {
	// TODO: Add scrollbars to the frame.

	this.setContentPane(jPanel);

	this.setCursor(null);

	// Closing the application when this JFram is closed.
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	// JFrame title.
	this.setTitle("Maze Depth First Search");

	this.getContentPane().setLayout(null);

	jPanel.setBackground(Color.white);

	jPanel.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);

	jPanel.setDoubleBuffered(false);

	jPanel.setRequestFocusEnabled(false);

	jPanel.setLayout(null);

	// Getting the dimensions of the maze to set the correct size for the JFrame.
	final Maze maze = depthFirstSearchEngine.getMaze();
	final int mazeRows = maze.getRows();
	final int mazeColumns = maze.getColumns();

	this.setSize(20 + mazeRows * 31, 40 + mazeColumns * 31);

	this.setVisible(true);
    }

    /**
     * Developer test harness.
     * 
     * @param args
     */
    public static void main(String[] args)
    {
	int inputRows = -1;
	int inputColumns = -1;
	Scanner scanner = new Scanner(System.in);

	System.out.println("Maze Depth First Search");
	System.out.println("-----------------------");
	System.out.println("Enter the number of rows:");
	inputRows = scanner.nextInt();

	System.out.println("Enter the number of columns:");
	inputColumns = scanner.nextInt();

	System.out.println(
		"Starting Maze Depth First Search with a maze of size " + inputRows + " by " + inputColumns + ".");

	// Starts a Depth First Search on a maze of the given dimensions.
	new MazeDepthFirstSearch(inputRows, inputColumns);

	scanner.close();

	/*
	 * The JFrame that opens will show how poor is the path a depth first search can
	 * find between the start and goal locations in the maze. The maze is a 10-by-10
	 * grid. The letter S marks the starting location in the upper left corner and
	 * the goal position is marked with a G in the lower right corner of the grid.
	 * Blocked grid cells are painted light gray. The basic problem with the depth
	 * first search is that the search engine will often start searching in a bad
	 * direction, but still find a path eventually, even given a poor start. The
	 * advantage of a depth first search over a breadth first search is that the
	 * depth first search requires much less memory. We will see that possible moves
	 * for depth first search are stored on a stack (last in, first out data
	 * structure) and possible moves for a breadth first search are stored in a
	 * queue (first in, first out data structure).
	 */
    }
}
