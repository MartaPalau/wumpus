import java.util.Scanner;
/**
 * 
 * Class Player for player configuration & choices
 */
public class Player {
	Scanner myScanner;
	String userChoice;
	String userName;
	WumpusWorld world;
	
	public Player() {
		/**
		 * 
		 * Initialize Wumpus board settings (width, height, x, y)
		 */
		world = new WumpusWorld(4,4,3,3);
	}
	
	/**
	 * 
	 * Explain game & get user name
	 */
	public void playerSetUp() {
		myScanner = new Scanner(System.in);
		userName = "";
		
		System.out.println("Welcome to a Hunt the Wumpus let큦 start playing!");
		System.out.println("Please write your name");
		userName = myScanner.nextLine();
		getUserName(userName);
		
	}
	
	public void getUserName(String name) {
		if(name.equals("")) {
			System.out.println("Ups..it seems that ypu forgot to type your name. Let큦 try again.\n");
			playerSetUp();
		} else {
			System.out.println("Welcome " + userName);
			getUserChoice();
		}
	}
	
	/**
	 * 
	 * Get user choice
	 */
	public void getUserChoice() {
		userChoice = "";
		
		System.out.println("Do you want to start? (y/n)");
		userChoice = myScanner.nextLine();
		
		if(userChoice.equals("")) {
			System.out.println("Ups..it seems that ypu forgot to type your choice. Let큦 try again.\n");
			getUserChoice();
		} else {
			if (userChoice.equals("y")) {
				System.out.println("Let큦 start playing!. \n");
				System.out.println("You큥e about to get in " + world.x + " x " + world.y + " cell board. \n");
				System.out.println("Your start line is in the top-left fist cell. \n");
				
				world.getNeighbors(world.userIndexPosition, world.userXPosition, world.userYPosition);
			} else {
				System.out.println("Oh! It큦 a pitty. See you next time. :)");
			}
		}
	}

	/**
	 * 
	 * Get user choice direction starting from initial values (index, x position, y position)
	 * Get direction (N, W, E, S)
	 * Control empty inputs & set user position in board in WumpusWorld class
	 */
	public void getUserDirection(int index, int xPosition, int yPosition) {
		
		myScanner = new Scanner(System.in);
		
		System.out.println("Which direction do you choose?");
		System.out.println("1. NORTH");
		System.out.println("2. EAST");
		System.out.println("3. SOUTH");
		System.out.println("4. WEST");
		
		userChoice = myScanner.nextLine();
		
		if(userChoice.equals("")) {
			System.out.println("Ups..it seems that ypu forgot to type your choice. Let큦 try again.\n");
			getUserDirection(index, xPosition, yPosition);
		} else {
			world.setUserPosition(userChoice, index, xPosition, yPosition);
		}
		
	}
	
	/**
	 * 
	 *End of game
	 */
	public void endOfGame() {
		System.out.println("Do you want to play again?");
		System.out.println("Bye..");
		WumpusWorld.board = null;
		playerSetUp();
		
	}
	
}
