/**
 * Copyright 1998-2012 by Mark Watson. All rights reserved. Originally written
 * by Mark Watson and later review by Rambod Rahmani. The following might be
 * slightly different from the original one.
 */
package chap2.search.maze.breadthfirst;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.DebugGraphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import chap2.search.maze.Location;
import chap2.search.maze.Maze;
import chap2.search.maze.MazeSearchGlobals;

/**
 * @author Rambod Rahmani &lt;rambodrahmani@autistici.org&gt;
 */
public class MazeBreadthFirstSearch extends JFrame
{
    /**
     * 
     */
    private static final long serialVersionUID	  = 2L;

    final JPanel	      jPanel		  = new JPanel();

    BreadthFirstSearchEngine  currentSearchEngine = null;

    /**
     * 
     */
    public MazeBreadthFirstSearch(int rows, int columns)
    {
	try
	{
	    jbInit();
	}
	catch (Exception e)
	{
	    System.out.println("GUI initilization error: " + e);
	}

	currentSearchEngine = new BreadthFirstSearchEngine(rows, columns);

	repaint();
    }

    public void paint(Graphics g_unused)
    {
	if (currentSearchEngine == null)
	{
	    return;
	}

	Maze maze = currentSearchEngine.getMaze();

	int rows = maze.getRows();
	int columns = maze.getColumns();

	System.out.println("Size of current maze: " + rows + " by " + columns);

	Graphics g = jPanel.getGraphics();
	BufferedImage image = new BufferedImage(320, 320, BufferedImage.TYPE_INT_RGB);
	Graphics g2 = image.createGraphics();
	g2.setColor(Color.white);
	g2.fillRect(0, 0, 320, 320);
	g2.setColor(Color.black);

	maze.setValue(0, 0, MazeSearchGlobals.START_LOCATION_VALUE);

	for (int i = 0; i < rows; i++)
	{
	    for (int j = 0; j < columns; j++)
	    {
		short val = maze.getValue(i, j);

		if (val == MazeSearchGlobals.OBSTACLE)
		{
		    g2.setColor(Color.lightGray);
		    g2.fillRect(6 + i * 29, 3 + j * 29, 29, 29);
		    g2.setColor(Color.black);
		    g2.drawRect(6 + i * 29, 3 + j * 29, 29, 29);
		}
		else if (val == MazeSearchGlobals.START_LOCATION_VALUE)
		{
		    g2.setColor(Color.blue);
		    g2.drawString("S", 16 + i * 29, 19 + j * 29);
		    g2.setColor(Color.black);
		    g2.drawRect(6 + i * 29, 3 + j * 29, 29, 29);
		}
		else if (val == MazeSearchGlobals.GOAL_LOCATION_VALUE)
		{
		    g2.setColor(Color.red);
		    g2.drawString("G", 16 + i * 29, 19 + j * 29);
		    g2.setColor(Color.black);
		    g2.drawRect(6 + i * 29, 3 + j * 29, 29, 29);
		}
		else
		{
		    g2.setColor(Color.black);
		    g2.drawRect(6 + i * 29, 3 + j * 29, 29, 29);
		}
	    }
	}

	// redraw the path in black:
	g2.setColor(Color.black);
	Location[] path = currentSearchEngine.getSearchPath();
	for (int i = 1; i < (path.length - 1); i++)
	{
	    int row = path[i].getRow();
	    int column = path[i].getColumn();
	    short val = maze.getValue(row, column);
	    g2.drawString("" + (path.length - i), 16 + row * 29, 19 + column * 29);
	}
	g.drawImage(image, 30, 40, 320, 320, null);
    }

    private void jbInit() throws Exception
    {
	this.setContentPane(jPanel);

	this.setCursor(null);

	this.setDefaultCloseOperation(3);

	this.setTitle("MazeBreadthFirstSearch");

	this.getContentPane().setLayout(null);

	jPanel.setBackground(Color.white);

	jPanel.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);

	jPanel.setDoubleBuffered(false);

	jPanel.setRequestFocusEnabled(false);

	jPanel.setLayout(null);

	this.setSize(370, 420);

	this.setVisible(true);
    }

    /**
     * Developer test harness.
     * 
     * @param args
     */
    public static void main(String[] args)
    {
	new MazeBreadthFirstSearch(10, 10);
    }
}
