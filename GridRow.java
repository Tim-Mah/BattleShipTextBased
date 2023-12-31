public class GridRow {
	/***************************
	 *@author: Tim Mah
	 *@dueDate: May 28th 2020
	 *@description: Creates an array
	 * which will keep track of the hits
	 * and misses, and position of boat
	 ****************************/
	//Rows are 10 wide
	//Rows only need to take up 2 spaces, first marks boat, second marks hit/miss
	//There are 5 ships of lengths 5, 4, 3, 3, and 2.
	//Aircraft carrier is length 5 and will be represented by the letter A
	//Battleship is length 4, and will be represented with the letter B
	//Submarine is length 3 and will be represented by the letter S
	//Cruiser is also length 3 and will be represented with letter C
	//Destroyer is length 2, and will be represented with the letter D
	private String[] row;
	public GridRow()
	{
		String[] blankRow = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
		row = blankRow;
	}
	//Hits will be marked with H, while misses will be marked with M
	/**
	 * Prints the row of ships, hits and/or misses.
	 * @return row represented as a String.
	 */
	public String toString()
	{
		//Instead of using a for-loop, I use this because I want each grid slot to take up exactly 2 spaces.
		String s1 = row[0].trim();
		String s2 = row[1].trim();
		String s3 = row[2].trim();
		String s4 = row[3].trim();
		String s5 = row[4].trim();
		String s6 = row[5].trim();
		String s7 = row[6].trim();
		String s8 = row[7].trim();
		String s9 = row[8].trim();
		String s10= row[9].trim();
		//2 spaces, first for ship, second for hit/miss
		//also need |  |  |  |  |  |  |  |  |  |  | to divide the cells
		return String.format("%1s%2s%1s%2s%1s%2s%1s%2s%1s%2s%1s%2s%1s%2s%1s%2s%1s%2s%1s%2s%1s","|",s1,"|", s2,"|", s3,"|", s4,"|", s5,"|", s6,"|", s7,"|", s8,"|", s9,"|", s10,"|");
	}
	/**
	 * Checks if the spot has been hit, adds appropriate
	 * char.
	 * @param i The index of the row that is checked.
	 * @return true or false, depending if
	 * it is a hit or a miss.
	 */
	public boolean hasHit(int i)
	{
		//if a spot has H, then it is hit
		if(row[i].contains("H") == true)
		{
			return true;
		}else
		{
			return false;
		}//End if/else
	}
	/**
	 * Checks if the spot has a ship,
	 * if it does, it is hit, otherwise miss.
	 * @param i The index of the row that is checked.
	 * @return true or false, depending if
	 * it is a hit or a miss.
	 */
	public boolean isHit(int i)
	{
		if(row[i].contains("A") == true || row[i].contains("B") == true || row[i].contains("C") == true || row[i].contains("D") == true || row[i].contains("S") == true)
		{
			return true;
		}else
		return false;
	}//end if/else
	/**
	 * Adds H to the specified spot with a ship.
	 * @param i the index of said ship.
	 */
	public void addHit(int i)
	{
		//boat will be generated first, so this is
		//in the right hand slot
		if(row[i].contains("H"))
		{
			System.out.println("You fool... Keep track of where you fire.\n You have just wasted a turn.");
		}else
		{
			row[i] += "H";
			row[i] = row[i].trim();//remove spaces
		}
	}
	/**
	 * Adds M to the specified spot with a ship.
	 * @param i the index of said ship.
	 */
	public void addMiss(int i)
	{
		if(row[i].contains("M"))
		{
			System.out.println("You fool... Keep track of where you fire.\n You have just wasted a turn.");
		}else
		{
		row[i] += "M";
		}
	}
	/**
	 * Gets the row array and removes the spaces.
	 * @return row array.
	 */
	public String[] getRow()
	{
		//want to make sure there are no spaces
		for(byte b = 0; b<row.length; b++)
		{
			String s = row[b].trim();
			row[b] = s;
		}
		return row;
	}
	/**
	 * Returns String at index i
	 * @param i the index of row
	 * @return the String at index i
	 */
	public String getIndex(int i)
	{
		return row[i];
	}
	/**
	 * Adds the string to the specified index
	 * in the array row.
	 * @param i the index of the row
	 * @param ship the string added
	 */
	public void addShip(int i, String ship)
	{
		row[i] += ship;
	}
	/**
	 * Remove the specified ship at the
	 * specified index.
	 * @param i the index that will be ship free.
	 * @param ship the ship that will be wiped from
	 * existence.
	 */
	public void removeIndex(int i, String ship)//so the player can change their ship during the set up if they want to move it
	{
		row[i] = row[i].replaceAll(ship, "");//should get rid of the ship
	}
}//end class
