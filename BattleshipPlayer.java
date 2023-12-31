public class BattleshipPlayer {
	/***************************
	 *@author: Tim Mah
	 *@dueDate: May 28th 2020
	 *@description: Creates a 
	 * player and has three 
	 * attributes, a name
	 * and  2 grids.
	 ****************************/
	private String name;
	private Grid gridU;//U stands for Up, D for Down.
	private Grid gridD;
	private byte sinkCount = 0;
	public static final String[] SHIPTYPES = {"Aircraft Carrier", "Batteship", "Cruiser", "Destroyer", "Submarine"};

	/**
	 * This is the constructor and
	 * it creates two new Grids.
	 * @param n is the name of the
	 * BattleshipPlayer.
	 */
	public BattleshipPlayer(String n)
	{
		name = n;
		gridU = new Grid(name);
		gridD = new Grid(name);
	}
	public BattleshipPlayer() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Checks the player's card to see
	 * if they have no battleships.
	 * @return if this BattleshipPlayer
	 * has lost the game.
	 */
	public boolean isLooser()
	{
		if(sinkCount == 5)
		{
			return true;
		}else
		{
			return false;
		}
	}
	/**
	 * Checks if the hit boat has been sunk, removes the ship
	 * so that it can't be sunk more than once.
	 * @param: rowNum which row has been hit.
	 * @param: index the index of rowNum that has been hit.
	 */
	public void isSunk(int rowNum, int index)
	{
		String row[] = gridD.getGridRow(rowNum).getRow();//gets the row
		if (row[index].contains("A") == true)
		{
			//I could just check the whole board for The letter A, and if 
			//There are 5 H and As, then the carrier is sunk

			byte shipHealth = 0;
			//Run a for-loop for every row, inside is another for-loop for the indexes.
			//When ever the loop finds an A with an H, then add one to a counter.
			for(byte b = 0; b<10; b++)
			{
				for(byte i = 0; i<10; i++)
				{
					if(gridD.getIndexAtRow(b, i).contains("A") && gridD.getIndexAtRow(b, i).contains("H"))
					{
						shipHealth +=1;
					}
				}//end for-loop for indexes
			}//end for-loop for rows
			if(shipHealth == 5)//if the ship is sunk
			{
				sinkCount += 1;
				gridD.removeShip("A");
				System.out.println(name + "'s " + SHIPTYPES[0] + " has been sunk.");
				System.out.println(name + " has " + (5- sinkCount) + " ships left.");
			}
		}else if(row[index].contains("B") == true)
		{
			byte shipHealth = 0;
			//Run a for-loop for every row, inside is another for-loop for the indexes.
			//When ever the loop finds an B with an H, then add one to a counter.
			for(byte b = 0; b<10; b++)
			{
				for(byte i = 0; i<10; i++)
				{
					if(gridD.getIndexAtRow(b, i).contains("B") && gridD.getIndexAtRow(b, i).contains("H"))
					{
						shipHealth +=1;
					}
				}//end for-loop for indexes
			}//end for-loop for rows
			if(shipHealth == 4)//if the ship is sunk
			{
				sinkCount += 1;
				gridD.removeShip("B");
				System.out.println(name + "'s " + SHIPTYPES[1] + " has been sunk.");
				System.out.println(name + " has " + (5- sinkCount) + " ships left.");
			}
		}else if(row[index].contains("C") == true)
		{
			byte shipHealth = 0;
			//Run a for-loop for every row, inside is another for-loop for the indexes.
			//When ever the loop finds an C with an H, then add one to a counter.
			for(byte b = 0; b<10; b++)
			{
				for(byte i = 0; i<10; i++)
				{
					if(gridD.getIndexAtRow(b, i).contains("C") && gridD.getIndexAtRow(b, i).contains("H"))
					{
						shipHealth +=1;
					}
				}//end for-loop for indexes
			}//end for-loop for rows
			if(shipHealth == 3)//if the ship is sunk
			{
				sinkCount += 1;
				gridD.removeShip("C");
				System.out.println(name + "'s " + SHIPTYPES[2] + " has been sunk.");
				System.out.println(name + " has " + (5- sinkCount) + " ships left.");
			}
		}else if(row[index].contains("D") == true)
		{
			byte shipHealth = 0;
			//Run a for-loop for every row, inside is another for-loop for the indexes.
			//When ever the loop finds an D with an H, then add one to a counter.
			for(byte b = 0; b<10; b++)
			{
				for(byte i = 0; i<10; i++)
				{
					if(gridD.getIndexAtRow(b, i).contains("D") && gridD.getIndexAtRow(b, i).contains("H"))
					{
						shipHealth +=1;
					}
				}//end for-loop for indexes
			}//end for-loop for rows
			if(shipHealth == 2)//if the ship is sunk
			{
				sinkCount += 1;
				gridD.removeShip("D");
				System.out.println(name + "'s " + SHIPTYPES[3] + " has been sunk.");
				System.out.println(name + " has " + (5- sinkCount) + " ships left.");
			}
		}else//it contains "S"
		{
			byte shipHealth = 0;
			//Run a for-loop for every row, inside is another for-loop for the indexes.
			//When ever the loop finds an S with an H, then add one to a counter.
			for(byte b = 0; b<10; b++)
			{
				for(byte i = 0; i<10; i++)
				{
					if(gridD.getIndexAtRow(b, i).contains("S") && gridD.getIndexAtRow(b, i).contains("H"))
					{
						shipHealth +=1;
					}
				}//end for-loop for indexes
			}//end for-loop for rows
			if(shipHealth == 3)//if the ship is sunk
			{
				sinkCount += 1;
				gridD.removeShip("S");
				System.out.println(name + "'s " + SHIPTYPES[4] + " has been sunk.");
				System.out.println(name + " has " + (5- sinkCount) + " ships left.");
			}
		}
	}
	/**
	 * Gets the player's upper grid.
	 * @return the player's upper
	 * grid in String form.
	 */
	public String gridUToString()
	{
		return gridU.toString();
	}
	/**
	 * Gets the player's lower grid.
	 * @return the player's lower
	 * grid in String form.
	 */
	public String gridDToString()
	{
		return gridD.toString();
	}
	/**
	 * Gets the player's upper grid.
	 * @return the player's upper
	 * grid as an object.
	 */
	public Grid getGridU()
	{
		return gridU;
	}
	/**
	 * Gets the player's lower grid.
	 * @return the player's lower
	 * grid as an object.
	 */
	public Grid getGridD()
	{
		return gridD;
	}
	/**
	 * Gets the player's name.
	 * @return the player's name.
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * Sets the players name.
	 * @param newName the new name of the player.
	 */
	public void setName(String newName)
	{
		name = newName;
	}
	/**
	 * Gets the number of ships sunk by the opponent.
	 * @return the number of sunken ships.
	 */
	public int getSinkCount()
	{
		return sinkCount;
	}
	/**
	 * Change and set the number of sunken ships.
	 * @param newCount the amount of sunken ships.
	 */
	public void setSinkCount(int newCount)//never going to put a number above 5 anyways
	//it's also annoying to add a type cast every time I call that method.
	{
		//this method is so that a saved game can run.
		sinkCount = (byte) newCount;
	}
}
