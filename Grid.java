public class Grid {
	/***************************
	 *@author: Tim Mah
	 *@dueDate: May 28th 2020
	 *@description: Creates a grid
	 * with 10 rows and 10 columns 
	 * and calls add ships to
	 * add 5 ships of length 5, 4,
	 * 3, 3, and 2 to the grid.
	 ****************************/
	
/*	This is how the gird will be formatted, but a 10x10
 * 	 1  2  3  4  5  
 *  +--+--+--+--+--+
 * A|  |  |  |  |  |
 *  +--+--+--+--+--+
 * B|  |  |  |  |  |
 *  +--+--+--+--+--+
 * C|  |  |  |  |  |
 *  +--+--+--+--+--+
 * D|  |  |  |  |  |
 *  +--+--+--+--+--+
 *  , in other words pain
 */
	private GridRow[] grid = new GridRow[10];
	private String player;
	private String[] rowLetter = {"A","B","C","D","E","F","G","H","I","J"};
	public Grid(String p)
	{
		player = p;
		for(byte b = 0; b<10; b++)
		{
			GridRow blank = new GridRow();
			grid[b] = blank;
		}//end for
	}
	/**
	 * Gets the specified row.
	 * @param rowNum the grid row in
	 * the battleship grid.
	 * @return the grid row at rowNum.
	 */
	public GridRow getGridRow(int rowNum)
	{
		return grid[rowNum];
	}
	/**
	 * Returns a specified element at specified row.
	 * @param rowNum the row that contains the element.
	 * @param i the index of said element.
	 * @return the element.
	 */
	public String getIndexAtRow(int rowNum, int i)
	{
		return grid[rowNum].getIndex(i);
	}
	/**
	 * Add the string at the specified coordinates.
	 * @param rowNum the row in the grid
	 * @param i the column in the grid
	 * @param ship the string added (generally a ship)
	 */
	public void addIndexAtRow(int rowNum, int i, String ship)
	{
		 GridRow a = grid[rowNum];
		 a.addShip(i, ship);
	}
	/**
	 * Removes all instances of the specified ship
	 * from the board. (It can be any string, but it
	 * is mostly used for ships)
	 * @param ship the ship being removed.
	 */
	public void removeShip(String ship)
	{
		//goes through the whole board and erases the ship from existence.
		for(byte b = 0; b< grid.length; b++)//runs through each row
		{
			for(byte i = 0; i < 10; i++)//runs through each index in said row
			{
				grid[b].removeIndex(i, ship);
			}
		}
	}//end removeShip()
	/**
	 * Gets all 10 rows in the grid
	 * @return the grid in string 
	 * form, outlined at the tip of
	 * the class.
	 */
	public String toString()
	{
		String printGrid = "  1  2  3  4  5  6  7  8  9  10\n";
		for(byte b= 0; b<grid.length; b++)
		{
			printGrid += " +--+--+--+--+--+--+--+--+--+--+\n";
			printGrid += rowLetter[b] + grid[b].toString() + "\n";
		}
		printGrid += " +--+--+--+--+--+--+--+--+--+--+\n";
		return String.format(printGrid);
	}
}//end class
