/**
 * Copyright 1998-2012 by Mark Watson. All rights reserved. Originally written
 * by Mark Watson and later review by Rambod Rahmani. The following might be
 * slightly different from the original one.
 */
package chap2.search.maze;

/**
 * This class represents a location in the maze. Each location is identified by
 * a row index and a column index.
 * 
 * @author Rambod Rahmani &lt;rambodrahmani@autistici.org&gt;
 */
public class Location
{
    // row index
    private int	row    = -1;

    // column index
    private int	column = -1;

    /**
     * Default constructor. Returns a location at row 0 and column 0.
     */
    public Location()
    {
	this.row = 0;
	this.column = 0;
    }

    /**
     * Creates a location with the given row and column indices.
     * 
     * @param row
     *            the row index for the location.
     * @param column
     *            the column index for the location.
     */
    public Location(int row, int column)
    {
	this.row = row;

	this.column = column;
    }

    /**
     * @param row
     *            the row index to set.
     */
    public void setRow(int row)
    {
	this.row = row;
    }

    /**
     * @return the current row index.
     */
    public int getRow()
    {
	return row;
    }

    /**
     * @param column
     *            the column index to set.
     */
    public void setColumn(int column)
    {
	this.column = column;
    }

    /**
     * @return the current column index.
     */
    public int getColumn()
    {
	return column;
    }

    @Override
    public String toString()
    {
	String ret = "";

	ret = "(" + row + ", " + column + ")";

	return ret;
    }
}
