import java.io.*;
import java.util.*;
public class BattleShipGame {
	/***************************
	 *@author: Tim Mah
	 *@dueDate: May 28th 2020
	 *@description: Runs various 
	 * methods to create and run
	 * a game of battleship.
	 ****************************/
	static ArrayList<BattleshipPlayer> battleshipPlayer = new ArrayList<BattleshipPlayer>();
	static int player = 0;
	static boolean stopGame = false;
	/**
	 * Runs the program forever, will prompt
	 * player to enter the game, then choose
	 * between playing a new game or playing
	 * a previously saved game.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String userChoice = null;
		do {
			System.out.println("Press enter to start the programme");
			sc.nextLine();
			do
			{
				clearPrevGame();
				System.out.println("Would you like to play a new game, or load the previously saved unfinished game?");
				System.out.println("Press 1 to play a new game, press 2 to load the previous save.");
				String ans = sc.nextLine();
				while(isInt(ans) == false)
				{
				System.out.println("Please enter a number between 1 and 2, any number not 1 or 2 will automatically set up a new game");
				ans = sc.nextLine();
				}
					if(Integer.parseInt(ans) == 2)//if they choose to set up from save
					{
						loadGame();
					}else
					{
						setup();
					}
				game();
				if(stopGame = true)
				{
					userChoice = "no";//stops this from asking to play another game
				}else
				{
					System.out.println("Would you like to play again?");
					userChoice = sc.next();
				}
			}while(userChoice.equalsIgnoreCase("Yes") || userChoice.equalsIgnoreCase("Y"));
		}while(0!=1);//loops infinitely, once they don't want to play, it will ask them to enter the program
		//and will just wait until they do.
	}//end main
	/**
	 * Resets all static values so that
	 * a new game may be played, as well
	 * as remove both battleship players.
	 */
	public static void clearPrevGame()
	{
		//clear board (everything in each board, reset player sink counters, names
		//Easiest way to reset things is to make a new battleshipPlayer, that way, new grids will be made, and everything will be default.
		for(byte b1 =0; b1< battleshipPlayer.size(); b1++)//clear the payers, as the setUp will make the battleshipPlayers
		{
			battleshipPlayer.remove(b1);
		}
		stopGame = false;
		player = 0;
	}
	/**
	 * Asks users for their names, creates
	 * 2 battleshipPlayers.
	 */
	public static void setupPlayers()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter player 1's name.");
		BattleshipPlayer one = new BattleshipPlayer(sc.nextLine());
		System.out.println("Enter player 2's name.");
		BattleshipPlayer two = new BattleshipPlayer(sc.nextLine());
		String yn;
		String playerChange;
		do {
			System.out.println("Do you want to change any names?");
			yn = sc.next();
			if(yn.equalsIgnoreCase("yes") ==true || yn.equalsIgnoreCase("y") ==true || yn.equalsIgnoreCase("ya")==true)
			{
				System.out.println("Would you like to change player 1's name, or player 2's name?");
				playerChange = sc.nextLine();
				playerChange = sc.nextLine();
				switch(playerChange)
				{
				case "1":
				case "player 1":
				case "Player 1":
				case "p1":
				case "P1":
					System.out.println("What would you like to change player 1's name to?");
					one.setName(sc.nextLine());
					break;
				case "2":
				case "player 2":
				case "Player 2":
				case "p2":
				case "P2":
					System.out.println("What would you like to change player 2's name to?");
					two.setName(sc.nextLine());
					break;
				default:
					System.out.println("Please enter either \"p1\" or \"p2\"");
				}//otherwise it is no, and the loop will end
			}else if(yn.equalsIgnoreCase("no") ==true|| yn.equalsIgnoreCase("n") ==true|| yn.equalsIgnoreCase("na")==true)
			{
			}else// they don't enter a valid response
			{
				System.out.println("Please say \"yes\" or \"no\"");
				yn = "yes";
			}
		}while(yn.equalsIgnoreCase("yes")==true || yn.equalsIgnoreCase("y") ==true|| yn.equalsIgnoreCase("ya")==true);
		battleshipPlayer.add(0, one);
		battleshipPlayer.add(1, two);
	}
	/**
	 * Sets up a game from scratch, including
	 * printing the rules, running setupPlayers()
	 * and setupBoard() for both players.
	 */
	public static void setup()
	{
		Scanner sc = new Scanner(System.in);
		rules();
		System.out.println();
		setupPlayers();
		clearView();
		System.out.println("It is " + battleshipPlayer.get(0).getName() + "'s turn to set up their board.");
		System.out.println(battleshipPlayer.get(0).getName() + ", please press enter to set up your board.");//so that the opponent won't see their board
		sc.nextLine();
		System.out.println(battleshipPlayer.get(0).gridDToString());
		setupBoard(0);
		System.out.println("You have placed all of your ships, press enter to let your opponent set up their board.");
		sc.nextLine();
		clearView();
		System.out.println(battleshipPlayer.get(1).getName() + ", please press enter to set up your board.");
		sc.nextLine();
		System.out.println(battleshipPlayer.get(1).gridDToString());
		setupBoard(1);
		System.out.println("It is " + battleshipPlayer.get(0).getName() + "'s turn to fire at their opponent's ship");
		//setup from txt file
	}
	/**
	 * Checks to see if an aircraft carrier can be placed
	 * in the desired slot. Places it if it does.
	 * @param row the index in the grid array, which corresponds to the row number.
	 * @param column the index in the row.
	 * @param direction the direction the ship will be placed.
	 * @param playerOrder whose turn it is.
	 * @return whether the ship has been placed or not
	 */
	public static boolean placeA(String row, int column, String direction, int playerOrder)
	{
		//String is either "r" for right, or "u" for up
		//only allow it to be placed up/right
		//can't be 4 or less away from the right, if horizontal.
		//can't be 4 or less from the top, if vertical
		String[] rows = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		int rowInt = -1;
		int count = 0;
		while(row.equalsIgnoreCase(rows[count]) == false)//finds what index the column would be.
		{
			rowInt +=1;//ship doesn't get placed, check column and row
			count++;

		}//end while loop
		if(direction.equalsIgnoreCase("u") ==true && (row.equalsIgnoreCase("A") == true || row.equalsIgnoreCase("B") == true || row.equalsIgnoreCase("C") == true || row.equalsIgnoreCase("D") == true))
		{
			System.out.println("Can't be placed here, too close to the top");
			return false;
		}else if(direction.equalsIgnoreCase("r") ==true && (column == 10 || column == 9 || column == 8 || column == 7))
		{
			System.out.println("Can't be placed here, too close to the right hand edge");
			return false;
		}else if(isIntersecting("A", rowInt, column, direction, playerOrder) == true)//it intersects
		{
			System.out.println("The ship will intersect with another ship, choose another location");
			return false;
		}else
		{
			//place the aircraft carrier
			//put A in the required spots.
			if(direction.equalsIgnoreCase("r") ==true)//right, starting with the given co-ords
			{
				for(byte b = (byte) (column); b<(column+5); b++)//aircraft carriers are 5 spaces, starts off at the specified space
				{
					battleshipPlayer.get(playerOrder).getGridD().addIndexAtRow((rowInt+1), (b), "A");//row stays the same, column changes
				}//end for loop
				return true;
			}else//it is up
			{
				//I keep the index the same, but change rows every time.
				for(byte b = (byte) (rowInt-4); b<=rowInt; b++)//aircraft carriers are 5 spaces, starts off at the specified space
				{
					battleshipPlayer.get(playerOrder).getGridD().addIndexAtRow((b+1), (column), "A");
				}//end for loop
				return true;
			}//end small if else
		}//end big if-else
	}
	/**
	 * Checks to see if a battleship can be placed
	 * in the desired slot. Places it if it does.
	 * @param row the index in the grid array, which corresponds to the row number.
	 * @param column the index in the row.
	 * @param direction the direction the ship will be placed.
	 * @param playerOrder whose turn it is.
	 * @return whether the ship has been placed or not
	 */
	public static boolean placeB(String row, int column, String direction, int playerOrder)
	{
		String[] rows = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		int rowInt = -1;
		int count = 0;
		while(row.equalsIgnoreCase(rows[count]) == false)//finds what index the column would be.
		{
			rowInt +=1;//ship doesn't get placed, check column and row
			count++;

		}//end while loop
		if(direction.equalsIgnoreCase("u") ==true && (row.equalsIgnoreCase("A") == true || row.equalsIgnoreCase("B") == true || row.equalsIgnoreCase("C") == true))
		{
			System.out.println("Can't be placed here, too close to the top");
			return false;
		}else if(direction.equalsIgnoreCase("r") ==true && (column == 10 || column == 9 || column == 8))
		{
			System.out.println("Can't be placed here, too close to the right hand edge");
			return false;
		}else if(isIntersecting("B", rowInt, (column), direction, playerOrder) == true)//it intersects
		{
			System.out.println("The ship will intersect with another ship, choose another location");
			return false;
		}else
		{
			if(direction.equalsIgnoreCase("r") ==true)//right, starting with the given co-ords
			{
				for(byte b = (byte) (column); b<(column+4); b++)//starts off at the specified space
				{
					battleshipPlayer.get(playerOrder).getGridD().addIndexAtRow((rowInt+1), b, "B");
				}//end for
				return true;
			}else//it is up
			{
				for(byte b = (byte) (rowInt-3); b<=rowInt; b++)//starts off at the specified space
				{
					battleshipPlayer.get(playerOrder).getGridD().addIndexAtRow((b+1), (column), "B");
				}//end for
				return true;
			}//end if-else
		}//end bigger if-else
	}//end placeB()
	/**
	 * Checks to see if a cruiser or submarine can be placed
	 * in the desired slot. Places it if it does.
	 * @param row the index in the grid array, which corresponds to the row number.
	 * @param column the index in the row.
	 * @param direction the direction the ship will be placed.
	 * @param playerOrder whose turn it is.
	 * @param type if the ship to be placed is a cruiser or submarine
	 * @return whether the ship has been placed or not
	 */
	public static boolean placeCS(String row, int column, String direction, int playerOrder, String type)//C stands for Cruiser, S stands for Sub, they share the same length
	{
		String[] rows = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		int rowInt = -1;
		int count = 0;
		while(row.equalsIgnoreCase(rows[count]) == false)//finds what index the column would be.
		{
			rowInt +=1;//ship doesn't get placed, check column and row
			count++;

		}//end while loop
		if(direction.equalsIgnoreCase("u")==true && (row.equalsIgnoreCase("A") == true || row.equalsIgnoreCase("B") == true))
		{
			System.out.println("Can't be placed here, too close to the top");
			return false;
		}else if(direction.equalsIgnoreCase("r") ==true && (column == 10 || column == 9))
		{
			System.out.println("Can't be placed here, too close to the right hand edge");
			return false;
		}else if(isIntersecting("C", rowInt, (column), direction, playerOrder) == true)//it intersects
		{
			System.out.println("The ship will intersect with another ship, choose another location");
			return false;
		}else
		{
			if(direction.equalsIgnoreCase("r")==true)//right, starting with the given co-ords
			{
				for(byte b = (byte) (column); b<(column+3); b++)//starts off at the specified space
				{
					battleshipPlayer.get(playerOrder).getGridD().addIndexAtRow((rowInt+1), b, type);
				}
				return true;
			}else//it is up
			{
				//I keep the index the same, but change rows every time.
				for(byte b = (byte) (rowInt-2); b<=rowInt; b++)//starts off at the specified space
				{
					battleshipPlayer.get(playerOrder).getGridD().addIndexAtRow((b+1), (column), type);
				}
				return true;
			}
		}
	}
	/**
	 * Checks to see if a destroyer can be placed
	 * in the desired slot. Places it if it does.
	 * @param row the index in the grid array, which corresponds to the row number.
	 * @param column the index in the row.
	 * @param direction the direction the ship will be placed.
	 * @param playerOrder whose turn it is.
	 * @return whether the ship has been placed or not
	 */
	public static boolean placeD(String row, int column, String direction, int playerOrder)
	{
		String[] rows = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		int rowInt = -1;//so they will match up with the indexes
		int count = 0;
		while(row.equalsIgnoreCase(rows[count]) == false)//finds what index the column would be.
		{
			rowInt +=1;//ship doesn't get placed, check column and row
			count++;

		}//end while loop
		if(direction.equalsIgnoreCase("u") ==true && row.equalsIgnoreCase("A") == true)
		{
			System.out.println("Can't be placed here, too close to the top");
			return false;
		}else if(direction.equalsIgnoreCase("r") ==true && column == 10)
		{
			System.out.println("Can't be placed here, too close to the right hand edge");
			return false;
		}else if(isIntersecting("D", rowInt, column, direction, playerOrder) == true)//it intersects
		{
			System.out.println("The ship will intersect with another ship, choose another location");
			return false;
		}else
		{
			if(direction.equalsIgnoreCase("r") ==true)//right, starting with the given co-ords
			{
				for(byte b = (byte) (column); b<(column+2); b++)//starts off at the specified space
				{
					battleshipPlayer.get(playerOrder).getGridD().addIndexAtRow((rowInt+1), b, "D");
				}
				return true;
			}else//it is up
			{
				//I keep the index the same, but change rows every time.
				for(byte b = (byte) (rowInt-1); b<=rowInt; b++)//starts off at the specified space
				{
					battleshipPlayer.get(playerOrder).getGridD().addIndexAtRow((b+1), (column), "D");
				}
				return true;
			}
		}
	}
	/**
	 * Lets the players pick were their ships go
	 * by running the setup ships methods. In addition,
	 * this method also allows the user to change where
	 * their ship is located.
	 * @param: playerOrder whose turn it is.
	 */
	public static void setupBoard(int playerOrder)
	{	
		Scanner sc = new Scanner(System.in);
		setupA(playerOrder);
		setupB(playerOrder);
		setupCS(playerOrder, "C");
		setupCS(playerOrder, "S");
		setupD(playerOrder);
		String yn; //stands for yes/no
		do {
			System.out.println("Would you like to move any of your ships?");
			yn = sc.nextLine();
			if(yn.equalsIgnoreCase("yes") ==true || yn.equalsIgnoreCase("y")==true ||yn.equalsIgnoreCase("ya")==true)//if they say yes.
			{
				//ask which ship.
				System.out.println("Which ship would you like to replace?");
				System.out.println("Aircraft Carrier (5 long), Battleship (4 long), Crusier (3 long), Submarine (3 long), Destroyer (2 long)");
				String removeShip = sc.nextLine();
				switch (removeShip)
				{
				case "Aircraft Carrier":
				case "aircraft carrier":
				case "Aircraft carrier":
				case "aircraft Carrier":
				case "aircraftCarrier":
				case "Aircraft Carrier (5 long)"://if they take it too literally
				case "Carrier":
				case "AC":
				case "A"://honestly, if they don't get it, they need to read better
				case "a":
					battleshipPlayer.get(playerOrder).getGridD().removeShip("A");
					setupA(playerOrder);
					break;
				case "Battleship":
				case "battleship":
				case "battle ship":
				case "Battle ship":
				case "Battle Ship":
				case "Battleship (4 long)":
				case "B":
				case "b":
					battleshipPlayer.get(playerOrder).getGridD().removeShip("B");
					setupB(playerOrder);
					break;
				case "Crusier":
				case "crusier":
				case "Crusier (3 long)":
				case "C":
				case "c":
					battleshipPlayer.get(playerOrder).getGridD().removeShip("C");
					setupCS(playerOrder, "C");
					break;
				case "Submarine":
				case "submarine":
				case "Submarine (3 long)":
				case "Sub":
				case "sub":
				case "S":
				case "s":
					battleshipPlayer.get(playerOrder).getGridD().removeShip("S");
					setupCS(playerOrder, "S");
					break;
				case "Destroyer":
				case "destroyer":
				case "Destroyer (2 long)":
				case "D":
				case "d":
					battleshipPlayer.get(playerOrder).getGridD().removeShip("D");
					setupD(playerOrder);
					break;
				case "Clean Slate Protocol"://Iron Man 3 reference, for fun
				case "Order 66"://Star Wars Reference, also for fun
					//I don't expect anyone to type the above two cases, or get the password right
					System.out.println("Can you confirm?");
					String password = sc.nextLine();
					if(password.equalsIgnoreCase("Why not, it's Christmas") == true || password.equalsIgnoreCase("Dew it") == true)
					{
						battleshipPlayer.get(0).getGridD().removeShip("A");
						battleshipPlayer.get(0).getGridD().removeShip("B");
						battleshipPlayer.get(0).getGridD().removeShip("C");
						battleshipPlayer.get(0).getGridD().removeShip("D");
						battleshipPlayer.get(0).getGridD().removeShip("S");
						battleshipPlayer.get(1).getGridD().removeShip("A");
						battleshipPlayer.get(1).getGridD().removeShip("B");
						battleshipPlayer.get(1).getGridD().removeShip("C");
						battleshipPlayer.get(1).getGridD().removeShip("D");
						battleshipPlayer.get(1).getGridD().removeShip("S");
						System.out.println("Here are the boards:");
						System.out.println(battleshipPlayer.get(0).gridDToString());
						System.out.println(battleshipPlayer.get(1).gridDToString());
						System.out.println("Congrats, you have broken the game, as there are now no ships, and the game won't ever end.");
						System.out.println("You must perform a full reboot of the software (recompile it)");
					}else
					{
						System.out.println("Sorry, you are not authourized to perform said action.");
					}
				default:
					yn = "yes";//so the user gets asked again.
				}
			}else if(yn.equalsIgnoreCase("no") == false && yn.equalsIgnoreCase("n") == false && yn.equalsIgnoreCase("na") == false)
			{
				System.out.println("Please enter yes, or no");
				yn = "yes";//to loop it again
			}
		}while(yn.equalsIgnoreCase("yes") == true || yn.equalsIgnoreCase("y") ==true || yn.equalsIgnoreCase("ya") ==true);//loops for some reason
	}
	/**
	 * Asks user where to place their ship
	 * until it is placed.
	 * @param: playerOrder whose turn it is.
	 */
	public static void setupA(int playerOrder)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("   5  6  7  8  9");
		System.out.println("  +--+--+--+--+--+");
		System.out.println("E |A |A |A |A |A |");
		System.out.println("  +--+--+--+--+--+");
		System.out.println("");
		System.out.println("Above is an example of the coorinates E5");
		System.out.println("And the direction r");
		System.out.println("");
		String coords;
		String direction;
		byte errorPhrase = 0;
		do
		{
			if(errorPhrase != 0)//so that this doesn't run the first time
			{
				System.out.println("Please choose a coordinate/direction that would fit on the board, and make sure that this ship won't intersect another.");
				errorPhrase = 1;//so make sure this can go on forever(in case the user makes more than 127 errors)
			}
			System.out.println("Enter coordinates using a letter(from A-J) with no spaces followed by a number(from 1-10): EX/ E5");
			coords = sc.next();
			while(coords.length() < 2 || coords.length() >3|| isValidInt(coords.substring(1)) == false || isValidString(coords.substring(0,1)) == false)//makes sure it is 2 long and meets other requirements
			{
				System.out.println("You may have included a space, entered more than 2 characters, not entered a valid number in the second part,\n or not enter a valid letter");
				System.out.println("Please enter a letter ( From A-J) followed by a number(From 1-10), and no spaces");
				coords = sc.next();
			}
			System.out.println("Next, enter the direction. Choose from \"u\" (up) or \"r\" (right).");
			direction = sc.next();
			while(direction.length() > 1 || direction.length() <1 || isValidDirection(direction) == false)
			{
				System.out.println("Please choose either \"U\" for up, or \"R\" for right");//doesn't actually matter if they enter a capital direction or not
				direction = sc.next();
				errorPhrase ++;
			}
			//Now, there should be 2 valid variables, the coordinates and the direction
		}while (placeA(coords.substring(0,1), (Integer.parseInt(coords.substring(1)) -1), direction ,playerOrder) == false);// to place aircraft carrier
		System.out.println("Aircraft Carrier has been placed.");
		System.out.println("Here is your board:");
		System.out.println(battleshipPlayer.get(playerOrder).gridDToString());
	}
	/**
	 * Asks user where to place their ship
	 * until it is placed.
	 * @param: playerOrder whose turn it is.
	 */
	public static void setupB(int playerOrder)
	{
		Scanner s = new Scanner(System.in);
		System.out.println("   5  6  7  8  9");
		System.out.println("  +--+--+--+--+--+");
		System.out.println("E |B |B |B |B |  |");
		System.out.println("  +--+--+--+--+--+");
		System.out.println("");
		System.out.println("Above is an example of the coorinates E5");
		System.out.println("And the direction r");
		System.out.println("");
		String coords;
		String direction;
		byte errorPhrase = 0;
		do
		{
			if(errorPhrase != 0)//so that this doesn't run the first time
			{
				System.out.println("Please choose a coordinate/direction that would fit on the board.");
				errorPhrase = 1;//so make sure this can go on forever(in case the user makes more than 127 errors)
			}
			System.out.println("Enter coordinates using a letter(from A-J) with no spaces followed by a number(from 1-10): EX/ E5");
			coords = s.next();
			while(coords.length() < 2 || coords.length() >3|| isValidInt(coords.substring(1)) == false || isValidString(coords.substring(0,1)) == false)//makes sure it is 2 long and meets other requirements
			{
				System.out.println("You may have included a space, entered more than 2 characters, not entered a valid number in the second part,\n or not enter a valid letter");
				System.out.println("Please enter a letter ( From A-J) followed by a number(From 1-10), and no spaces");
				coords = s.next();
			}
			System.out.println("Next, enter the direction. Choose from \"u\" (up) or \"r\" (right).");
			direction = s.next();
			while(direction.length() > 1 || direction.length() <1 || isValidDirection(direction) == false)
			{
				System.out.println("Please choose either \"U\" for up, or \"R\" for right");
				direction = s.next();
				errorPhrase ++;
			}
			//Now, there should be 2 valid variables, the coordinates and the direction
		}while (placeB(coords.substring(0,1), (Integer.parseInt(coords.substring(1)) -1), direction ,playerOrder) == false);// to place battleship
		System.out.println("Battleship has been placed.");
		System.out.println("Here is your board:");
		System.out.println(battleshipPlayer.get(playerOrder).gridDToString());
	}
	/**
	 * Asks user where to place their ship
	 * until it is placed.
	 * @param: playerOrder whose turn it is.
	 * @param: type the ship being placed (submarine or cruiser)
	 */
	public static void setupCS(int playerOrder, String type)// 2 in one method
	{
		Scanner sc = new Scanner(System.in);
		if(type.equalsIgnoreCase("C"))
		{
			System.out.println("   5  6  7  8  9");
			System.out.println("  +--+--+--+--+--+");
			System.out.println("E |C |C |C |  |  |");
			System.out.println("  +--+--+--+--+--+");
			System.out.println("");
			System.out.println("Above is an example of the coorinates E5");
			System.out.println("And the direction r");
			System.out.println("");
		}else
		{
			System.out.println("   5  6  7  8  9");
			System.out.println("  +--+--+--+--+--+");
			System.out.println("E |S |S |S |  |  |");
			System.out.println("  +--+--+--+--+--+");
			System.out.println("");
			System.out.println("Above is an example of the coorinates E5");
			System.out.println("And the direction r");
			System.out.println("");
		}
		String coords;
		String direction;
		byte errorPhrase = 0;
		do
		{
			if(errorPhrase != 0)//so that this doesn't run the first time
			{
				System.out.println("Please choose a coordinate/direction that would fit on the board.");
				errorPhrase = 1;//so make sure this can go on forever(in case the user makes more than 127 errors)
			}
			System.out.println("Enter coordinates using a letter(from A-J) with no spaces followed by a number(from 1-10): EX/ E5");
			coords = sc.next();
			while(coords.length() < 2 || coords.length() >3|| isValidInt(coords.substring(1)) == false || isValidString(coords.substring(0,1)) == false)//makes sure it is 2 long and meets other requirements
			{
				System.out.println("You may have included a space, entered more than 2 characters, not entered a valid number in the second part,\n or not enter a valid letter");
				System.out.println("Please enter a letter ( From A-J) followed by a number(From 1-10), and no spaces");
				coords = sc.next();
			}
			System.out.println("Next, enter the direction. Choose from \"u\" (up) or \"r\" (right).");
			direction = sc.next();
			while(direction.length() > 1 || direction.length() <1 || isValidDirection(direction) == false)
			{
				System.out.println("Please choose either \"U\" for up, or \"R\" for right");
				direction = sc.next();
				errorPhrase ++;
			}
			//Now, there should be 2 valid variables, the coordinates and the direction
		}while (placeCS(coords.substring(0,1), (Integer.parseInt(coords.substring(1)) -1), direction ,playerOrder, type) == false);// to place appropriate ship
		if(type.equalsIgnoreCase("C"))
		{
			System.out.println("Cruiser has been placed.");
			System.out.println("Here is your board:");
			System.out.println(battleshipPlayer.get(playerOrder).gridDToString());
		}else
		{
			System.out.println("Submarine has been placed.");
			System.out.println("Here is your board:");
			System.out.println(battleshipPlayer.get(playerOrder).gridDToString());
		}
	}
	/**
	 * Asks user where to place their ship
	 * until it is placed.
	 * @param: playerOrder whose turn it is.
	 */
	public static void setupD(int playerOrder)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("   5  6  7  8  9");
		System.out.println("  +--+--+--+--+--+");
		System.out.println("E |D |D |  |  |  |");
		System.out.println("  +--+--+--+--+--+");
		System.out.println("");
		System.out.println("Above is an example of the coorinates E5");
		System.out.println("And the direction r");
		System.out.println("");
		String coords;
		String direction;
		byte errorPhrase = 0;
		do
		{
			if(errorPhrase != 0)//so that this doesn't run the first time
			{
				System.out.println("Please choose a coordinate/direction that would fit on the board.");
				errorPhrase = 1;//so make sure this can go on forever(in case the user makes more than 127 errors)
			}
			System.out.println("Enter coordinates using a letter(from A-J) with no spaces followed by a number(from 1-10): EX/ E5");
			coords = sc.next();
			while(coords.length() < 2 || coords.length() >3|| isValidInt(coords.substring(1)) == false || isValidString(coords.substring(0,1)) == false)//makes sure it is 2 long and meets other requirements
			{
				System.out.println("You may have included a space, entered more than 2 characters, not entered a valid number in the second part,\n or not enter a valid letter");
				System.out.println("Please enter a letter ( From A-J) followed by a number(From 1-10), and no spaces");
				coords = sc.next();
			}
			System.out.println("Next, enter the direction. Choose from \"u\" (up) or \"r\" (right).");
			direction = sc.next();
			while(direction.length() > 1 || direction.length() <1 || isValidDirection(direction) == false)
			{
				System.out.println("Please choose either \"U\" for up, or \"R\" for right");
				direction = sc.next();
				errorPhrase ++;
			}
			//Now, there should be 2 valid variables, the coordinates and the direction
		}while (placeD(coords.substring(0,1), (Integer.parseInt(coords.substring(1))-1), direction ,playerOrder) == false);// to place destroyer
		System.out.println("Destroyer has been placed.");
		System.out.println("Here is your board:");
		System.out.println(battleshipPlayer.get(playerOrder).gridDToString());
	}
	/**
	 * Runs the appropriate methods to
	 * run a game.
	 */
	public static void game()
	{
		do
		{
			clearView();
			turn(player);
			if(player == 0)//so the turns alternate between the 2 players
			{
				player = 1;
			}else
			{
				player = 0;
			}
			if(stopGame == true)
			{
				break;
			}
		}while(winner() != true);
		if(stopGame == false)
		{
			System.out.println("Press enter to see the boards");
			Scanner s = new Scanner(System.in);
			s.nextLine();
			System.out.println(battleshipPlayer.get(0).getName() + "'s board");
			System.out.println(battleshipPlayer.get(0).getGridD());
			System.out.println(battleshipPlayer.get(1).getName() + "'s board");
			System.out.println(battleshipPlayer.get(1).getGridD());//print both grids with ships.
		}
	}
	/**
	 * Prints the rules of battleship
	 */
	public static void rules()
	{
		System.out.println("OBJECT OF BATTLESHIP:");
		System.out.println("To become the winner of Battleship you must be able to find (sink) all five ships in your opponent’s\n fleet before they sink yours.");
		System.out.println();
		System.out.println("GAME PLAY");
		System.out.println("Each player calls out one shot (or coordinate) each turn in attempt to hit one of their opponent’s ships. To “hit” one of your opponent’s");
		System.out.println("ships, you must call out a letter and a number of where you think one of their ships is located. The instructions state that  once a shot");
		System.out.println("is called,  the opponent must immediately call out “hit” or “miss.” If one of your ships gets hit, place a red peg over the hole location");
		System.out.println("on your ships that was called out. If calling a shot (or trying to hit your opponent’s ships), mark a red peg (if a hit was made) or a white peg");
		System.out.println("(a miss) on your target grid located on the lid or the vertical divider between you and your opponent.This will help you keep track of your hits");
		System.out.println("and misses in your hunt to find their ships.");
		System.out.println();
		System.out.println("Once all holes on a ship have been filled with red pegs, your ship has sunk and must be removed from");
		System.out.println("the ocean. You then announce which ship has sunk. The Battleship rules on successfully sinking a ship");
		System.out.println("are as follows: Carrier – 5 hits, Battleship – 4 hits, Cruiser – 3 hits, Submarine – 3 hits, Destroyer – 2");
		System.out.println("hits. It is considered cheating and against the Battleship rules to be dishonest on the location of your");
		System.out.println("ships.");
	}
	/**
	 * Checks if the string is an integer
	 * between 1 and 10 (inclusive).
	 * @param s the string that is being checked
	 * @return true if it is a valid integer, false otherwise.
	 */
	public static boolean isValidInt(String s) 
	{
		int i;
		try 
		{ 
			i = Integer.parseInt(s); 
		} catch(NumberFormatException e) 
		{ 
			return false; 
		}//end catch
		if(i>=1 && i <= 10)
		{
			return true;
		}else
		{
			return false;
		}
	}//end isValidInt
	/**
	 * Checks if the string is a letter
	 * between A and J (inclusive).
	 * @param s the string being checked
	 * @return true if it is a valid String, false otherwise.
	 */
	public static boolean isValidString(String s)
	{
		switch (s.substring(0,1))
		{
		case "A":
		case "B":
		case "C":
		case "D":
		case "E":
		case "F":
		case "G":
		case "H":
		case "I":
		case "J":
		case "a":
		case "b":
		case "c":
		case "d":
		case "e":
		case "f":
		case "g":
		case "h":
		case "i":
		case "j":
			return true;
		default:
			return false;
		}
	}//end isValidString
	/**
	 * Checks if the string is a valid direction,
	 * right or up.
	 * @param s the string being checked
	 * @return true if the direction is right or up,
	 * false otherwise.
	 */
	public static boolean isValidDirection(String s)
	{
		switch (s.substring(0,1))
		{
		case "U":
		case "u":
		case "r":
		case "R":
			return true;
		default: return false;
		}
	}
	/**
	 * Checks if a ship is intersecting.
	 * @param type the type of ship.
	 * @param rowInt the row the ship will be placed.
	 * @param column the column the ship will be placed.
	 * @param direction the direction the ship will face.
	 * @param playerOrder whose turn it is.
	 * @return true if the ship will intersect with another,
	 *  false otherwise.
	 */
	public static boolean isIntersecting(String type, int rowInt, int column, String direction, int playerOrder)
	{
		int shipLength = 0;
		switch(type)
		{
		case "A":
			shipLength = 5;
			break;
		case "B":
			shipLength = 4;
			break;
		case "C":
		case "S":
			shipLength = 3;
			break;
		case "D":
			shipLength = 2;
			break;
		default://nothin' here, as there will be no error, the method that this will be nested in will already make sure all the variables work.
		}
		if(direction.equalsIgnoreCase("U") ==true)
		{
			for(byte b = (byte) (rowInt-(shipLength-2)); b<=rowInt; b++)
			{
				if(battleshipPlayer.get(playerOrder).getGridD().getIndexAtRow(b, (column)).contains("A") == true
						|| battleshipPlayer.get(playerOrder).getGridD().getIndexAtRow((b), (column)).contains("B") == true
						|| battleshipPlayer.get(playerOrder).getGridD().getIndexAtRow((b), (column)).contains("C") == true
						|| battleshipPlayer.get(playerOrder).getGridD().getIndexAtRow((b), (column)).contains("D") == true
						|| battleshipPlayer.get(playerOrder).getGridD().getIndexAtRow((b), (column)).contains("S")== true)
					//if said square contains a ship marker
				{
					return true;
				}
			}//end for loop
		}else{//it is facing right
			for(byte b = (byte) (column); b<(column+(shipLength)); b++)
			{
				if(battleshipPlayer.get(playerOrder).getGridD().getIndexAtRow((rowInt+1), b).contains("A") == true
						|| battleshipPlayer.get(playerOrder).getGridD().getIndexAtRow((rowInt+1), b).contains("B") == true
						|| battleshipPlayer.get(playerOrder).getGridD().getIndexAtRow((rowInt+1), b).contains("C") == true
						|| battleshipPlayer.get(playerOrder).getGridD().getIndexAtRow((rowInt+1), b).contains("D") == true
						|| battleshipPlayer.get(playerOrder).getGridD().getIndexAtRow((rowInt+1), b).contains("S")== true)
					//if said square contains a ship marker
				{
					return true;
				}
			}//end for
		}
		//use same method to place ships, accept just check.
		return false;//if none of the for-loops trigger, then it won't be intersecting another ship.
	}
	/**
	 * Prints out a lot of lines, so that it
	 * appears that the screen has been cleared.
	 */
	public static void clearView()
	{
		for(byte b = 0; b<127; b++)
		{
			System.out.println();//just puts a bunch of enters, so the opponent's turn will be hidden.
			//they could just scroll up...
		}
	}
	/**
	 * Determines if someone has won,
	 * prints out who wins.
	 * @return true if there is a winner, false otherwise.
	 */
	public static boolean winner()
	{
		//checks each player's sink counter
		if(battleshipPlayer.get(0).isLooser() == true)
		{
			System.out.println("The winner is " +  battleshipPlayer.get(1).getName() + " they have sunk all their opponent's ships");
			return true;
		}else if(battleshipPlayer.get(1).isLooser() == true) {
			System.out.println("The winner is " +  battleshipPlayer.get(0).getName() + " they have sunk all their opponent's ships");
			return true;
		}else
		{
			return false;
		}
	}
	/**
	 * Runs appropriate methods for a turn,
	 * prompts user to fire at the opponent.
	 * @param: playerOrder which player's grid is
	 * being written.
	 */
	public static void turn(int playerOrder)
	{
		Scanner sc = new Scanner(System.in);
		//has the player confirm that they will start their turn
		System.out.println(battleshipPlayer.get(playerOrder).getName() + ", press enter to start your turn.");
		sc.nextLine();
		System.out.println("Here is your board:");
		System.out.println(battleshipPlayer.get(playerOrder).getGridU());
		System.out.println(battleshipPlayer.get(playerOrder).getGridD());
		//call out coordinate
		String coords;
		System.out.println("Call out coordinates to fire");
		System.out.println("Enter coordinates with no spaces and capitals: EX/ E2");
		coords = sc.next();
		//check if it is a valid coordinate.
		//valid coords have a capitol letter in the first part, and a number between 1 and 10
		while(coords.length() < 2 || coords.length() >3|| isValidInt(coords.substring(1)) == false || isValidString(coords.substring(0,1)) == false)//makes sure it is 2 long and meets other requirements
		{
			System.out.println("You may have included a space, entered more than 2 characters, not enter a valid number in the second part,\n or not enter a valid letter");
			System.out.println("Please enter a capitol letter(A-J) followed by a number(1-10), and no spaces");
			coords = sc.next();
		}
		//code that checks it if hit opponent's ship or miss
		int opponent = 0;
		String[] rows = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		int rowInt = -1;
		do{
			rowInt +=1;

		}while(coords.substring(0,1).equalsIgnoreCase(rows[rowInt]) == false);//finds what index the column would be, end do-while loop
		if(playerOrder == 0)//so I can get the opponent's board
		{
			opponent = 1;
			if(battleshipPlayer.get(opponent).getGridD().getGridRow(rowInt).hasHit((Integer.parseInt(coords.substring(1))-1)) == false)//checks to see it has a hit
			{
				if(battleshipPlayer.get(opponent).getGridD().getGridRow(rowInt).isHit((Integer.parseInt(coords.substring(1))-1)))
				{
					battleshipPlayer.get(playerOrder).getGridU().getGridRow(rowInt).addHit((Integer.parseInt(coords.substring(1))-1));//marks it on opponent's ship
					battleshipPlayer.get(opponent).getGridD().getGridRow(rowInt).addHit((Integer.parseInt(coords.substring(1))-1));
					battleshipPlayer.get(opponent).isSunk(rowInt, (Integer.parseInt(coords.substring(1))-1));//checks if the ship is sunk, but no return statement is needed, as it does everything in the method.
					if(battleshipPlayer.get(opponent).getSinkCount() != 5)//so the screen doesn't get cluttered when someone wins
					{
						System.out.println("Here is your board:");
						System.out.println(battleshipPlayer.get(playerOrder).getGridU());
						System.out.println(battleshipPlayer.get(playerOrder).getGridD());
						System.out.println("Hit!!!!");
					}
				}else
				{
					battleshipPlayer.get(playerOrder).getGridU().getGridRow(rowInt).addMiss((Integer.parseInt(coords.substring(1))-1));//marks miss down on the player's board
					System.out.println("Here is your board:");
					System.out.println(battleshipPlayer.get(playerOrder).getGridU());
					System.out.println(battleshipPlayer.get(playerOrder).getGridD());
					System.out.println("Miss...");
				}
			}
		}else
		{
			opponent = 0;
			if(battleshipPlayer.get(opponent).getGridD().getGridRow(rowInt).hasHit((Integer.parseInt(coords.substring(1))-1)) == false)//checks to see it has a hit
			{
				if(battleshipPlayer.get(opponent).getGridD().getGridRow(rowInt).isHit((Integer.parseInt(coords.substring(1))-1)))
				{
					battleshipPlayer.get(playerOrder).getGridU().getGridRow(rowInt).addHit((Integer.parseInt(coords.substring(1))-1));//marks it on opponent's ship
					battleshipPlayer.get(opponent).getGridD().getGridRow(rowInt).addHit((Integer.parseInt(coords.substring(1))-1));
					battleshipPlayer.get(opponent).isSunk(rowInt, (Integer.parseInt(coords.substring(1))-1));//checks if the ship is sunk, but no return statement is needed, as it does everything in the method.
					if(battleshipPlayer.get(opponent).getSinkCount() != 5)//so the screen doesn't get cluttered when someone wins
					{
						System.out.println("Press enter to see your board.");// so that they can see the text about which ship they destroyed
						sc.nextLine();
						System.out.println(battleshipPlayer.get(playerOrder).getGridU());
						System.out.println(battleshipPlayer.get(playerOrder).getGridD());
						System.out.println("Hit!!!!");
					}
				}else
				{
					battleshipPlayer.get(playerOrder).getGridU().getGridRow(rowInt).addMiss((Integer.parseInt(coords.substring(1))-1));//marks miss down on the player's board
					System.out.println("Here is your board:");
					System.out.println(battleshipPlayer.get(playerOrder).getGridU());
					System.out.println(battleshipPlayer.get(playerOrder).getGridD());
					System.out.println("Miss...");
				}
			}else
			{
				System.out.println("You fool, you have already fired here.");
			}
		}
		if(battleshipPlayer.get(opponent).getSinkCount() != 5)
		{
			System.out.println("Press enter to end your turn and pass this device to your opponent,");
			System.out.println("Or, press any number to save and exit the game.");
			Scanner in = new Scanner(System.in);
			String ans = in.nextLine();
			if(isInt(ans.trim()) == true)
			{
				saveGame();
				clearView();
				stopGame = true;
			}
		}
	}//end turn()
	/**
	 * Runs all the methods to read and set up a game 
	 * from the various plain text files.
	 */
	public static void loadGame()
	{
		rsNames();
		rsGridD(0);
		rsGridU(0);
		rsGridD(1);
		rsGridU(1);
		rsTurnSink();
	}
	/**
	 * Reads the and sets the player's lower grid
	 * from the text file.
	 * @param: playerOrder whose turn it is.
	 */
	public static void rsGridD(int playerOrder)//stands for Read and Setup GridD
	{
		try 
		{
			Scanner fs;
			if(playerOrder == 0)
			{
				FileReader fr = new FileReader("P1GD.txt");
				fs = new Scanner(fr);
			}else
			{
				FileReader fr = new FileReader("P2GD.txt");
				fs = new Scanner(fr);
			}//end if-else
			while(fs.hasNextLine())//goes through all the code
			{
				int rowNum;
				int i;
				String ship;
				if(fs.hasNext())//this condition has to be met first, so that there is no error
				{
					System.out.println("This ran");
					rowNum = Integer.parseInt(fs.next());
					if(fs.hasNext())//this also has to be met
					{
						System.out.println("This ran");
						i = Integer.parseInt(fs.next());
						if(fs.hasNext())//last condition to be met
						{
							System.out.println("This ran");
							ship = fs.next();
							battleshipPlayer.get(playerOrder).getGridD().addIndexAtRow(rowNum, i, ship);//this will run because all of them were triggered
						}//end if with ship
					}//end if with index
				}//end if with row
			}//end while
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}//end try catch
	}
	/**
	 * Reads and sets the player's upper grid
	 * from a text file.
	 * @param: playerOrder which player's grid is
	 * being written.
	 */
	public static void rsGridU(int playerOrder)//Read and Setup GridU
	{
		try 
		{
			Scanner fs;
			if(playerOrder == 0)
			{
				FileReader fr = new FileReader("P1GU.txt");
				fs = new Scanner(fr);
			}else
			{
				FileReader fr = new FileReader("P2GU.txt");
				fs = new Scanner(fr);
			}//end if-else
			while(fs.hasNextLine())//goes through all the code
			{
				int rowNum;
				int i;
				String HM;
				if(fs.hasNext())//this condition has to be met first, so that there is no error
				{
					rowNum = Integer.parseInt(fs.next());
					if(fs.hasNext())//this also has to be met
					{
						i = Integer.parseInt(fs.next());
						if(fs.hasNext())//last condition to be met
						{
							HM = fs.next();
							if(HM.contains("H"))//this is a hit
							{
								battleshipPlayer.get(playerOrder).getGridU().getGridRow(rowNum).addHit(i);//this will run because all of them were triggered
							}else//end hit/miss if/else statement	
							{//this is a miss
								battleshipPlayer.get(playerOrder).getGridU().getGridRow(rowNum).addMiss(i);
							}//end the else part
						}//end if with ship
					}//end if with index
				}//end if with row
			}//end while
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}//end try catch
	}
	/**
	 * Creates 2 battleshipPlayers with the names
	 * that were read in from the text file.
	 */
	public static void rsNames()
	{
		FileReader fr;
		try {
			fr = new FileReader("Names.txt");
			Scanner fs = new Scanner(fr);
			BattleshipPlayer one = new BattleshipPlayer(fs.nextLine());
			BattleshipPlayer two = new BattleshipPlayer(fs.nextLine());
			battleshipPlayer.add(0, one);
			battleshipPlayer.add(1, two);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}//end try/catch
	}
	/**
	 * Reads in whose turn it is from
	 * the text file, reads and sets each player's
	 * sinkCounter.
	 */
	public static void rsTurnSink()//reads and sets the player turn, and everyplayer's sink counter
	{
		FileReader fr;
		try {
			fr = new FileReader("Turn_Sink.txt");
			Scanner fs = new Scanner(fr);
			//to tell the program whose turn it is
			player = Integer.parseInt(fs.next());
			//set their sink counters (if any of their ships were sunk)
			battleshipPlayer.get(0).setSinkCount(Integer.parseInt(fs.next()));
			battleshipPlayer.get(1).setSinkCount(Integer.parseInt(fs.next()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}//end try/catch
	}
	/**
	 * Calls on a variety of methods that
	 * writes all the information used
	 * in the game onto text files.
	 */
	public static void saveGame()
	{
		wTurnSink();
		wGridD(0);
		wGridU(0);
		wGridD(1);
		wGridU(1);
		wNames();
	}
	/**
	 * Writes the player's lower grid on the
	 * appropriate text file.
	 * @param: playerOrder which player's grid is
	 * being written.
	 */
	public static void wGridD(int playerOrder)//w stands for "write"
	{
		try 
		{
			PrintWriter pw;
			if(playerOrder == 0)
			{
				FileWriter fw = new FileWriter("P1GD.txt",false);//should overwrite it
				pw = new PrintWriter(fw);
			}else
			{
				FileWriter fw = new FileWriter("P2GD.txt", false);
				pw = new PrintWriter(fw);
			}//end if-else
			for(byte row = 0; row < 10; row++)
			{
				for(byte index = 0; index<10; index++)
				{
					if(battleshipPlayer.get(playerOrder).getGridD().getIndexAtRow(row, index).trim().isEmpty() == false)
					{
						//there is something in that index (A ship, or a ship with a hit)
						pw.println();
						pw.print(row + " " + index + " " + battleshipPlayer.get(playerOrder).getGridD().getIndexAtRow(row, index).trim());//this is the correct format
					}
				}
			}
			pw.close();
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}//end try/catch
	}
	/**
	 * Writes the player's upper grid on the
	 * appropriate text file.
	 * @param: playerOrder which player's grid is
	 * being written.
	 */
	public static void wGridU(int playerOrder)
	{
		try 
		{
			PrintWriter pw;
			if(playerOrder == 0)
			{
				FileWriter fw = new FileWriter("P1GU.txt",false);//should overwrite it
				pw = new PrintWriter(fw);
			}else
			{
				FileWriter fw = new FileWriter("P2GU.txt", false);
				pw = new PrintWriter(fw);
			}//end if-else
			for(byte row = 0; row < 10; row++)
			{
				for(byte index = 0; index<10; index++)
				{
					if(battleshipPlayer.get(playerOrder).getGridU().getIndexAtRow(row, index).trim().isEmpty() == false)
					{
						//there is something in that index (Hit or miss)
						pw.println();
						pw.print(row + " " + index + " " + battleshipPlayer.get(playerOrder).getGridU().getIndexAtRow(row, index).trim());//this is the correct format
					}
				}
			}
			pw.close();
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}//end try/catch
	}
	/**
	 * Writes both player's names on the
	 * appropriate text file.
	 */
	public static void wNames()
	{
		try 
		{
			FileWriter fw = new FileWriter("Names.txt",false);//should overwrite it
			PrintWriter pw = new PrintWriter(fw);
			pw.println(battleshipPlayer.get(0).getName());
			pw.println(battleshipPlayer.get(1).getName());
			pw.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Writes whose turn it would
	 * be if play resumed, and also writes
	 * down each player's sinkCounters
	 * on the appropriate file.
	 */
	public static void wTurnSink()
	{
		try 
		{
			FileWriter fw = new FileWriter("Turn_Sink.txt",false);//should overwrite it
			PrintWriter pw = new PrintWriter(fw);
			if(player == 0)//because this will be called at the end of a player's turn, so the other person would resume play
			{
				pw.println(1);
			}else
			{
				pw.println(0);
			}
			pw.println(battleshipPlayer.get(0).getSinkCount());
			pw.println(battleshipPlayer.get(1).getSinkCount());
			pw.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Checks to see if a string can be parsed into an int.
	 * @param s The sting that is being checked if it can be parsed.
	 * @return true if s can be parsed, false if it can't.
	 */
	public static boolean isInt(String s) 
	{
		try 
		{ 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) 
		{ 
			return false; 
		}//end catch
		return true;
	}//end isInt
}//end class
