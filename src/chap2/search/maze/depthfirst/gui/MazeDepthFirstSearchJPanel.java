/**
 *
 */
package chap2.search.maze.depthfirst.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import chap2.search.maze.Location;
import chap2.search.maze.Maze;
import chap2.search.maze.MazeSearchGlobals;
import chap2.search.maze.depthfirst.DepthFirstSearchEngine;

/**
 * @author Rambod Rahmani &lt;rambodrahmani@autistici.org&gt;
 */
public class MazeDepthFirstSearchJPanel extends JPanel
{
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -6081141342884676493L;

    // Current search engine
    private final DepthFirstSearchEngine depthFirstSearchEngine;

    /**
     * Default constructor.
     * 
     * @param depthFirstSearchEngine
     *            the {@link DepthFirstSearchEngine} to use (rows and columns) to
     *            draw the JPanel content.
     */
    public MazeDepthFirstSearchJPanel(DepthFirstSearchEngine depthFirstSearchEngine)
    {
	this.depthFirstSearchEngine = depthFirstSearchEngine;
    }

    /*
     * (non-Javadoc)
     * @see java.awt.Window#paint(java.awt.Graphics)
     */
    public void paint(Graphics g)
    {
	super.paint(g);

	if (depthFirstSearchEngine == null)
	{
	    return;
	}

	// Getting the dimensions of the maze to draw.
	final Maze maze = depthFirstSearchEngine.getMaze();
	final int mazeRows = maze.getRows();
	final int mazeColumns = maze.getColumns();

	Graphics jPnaleGraphics = g;

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
}
