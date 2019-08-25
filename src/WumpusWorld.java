import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WumpusWorld {
	
	static List<String> board;
	String[] elements;
	int prevXPosition;
	int prevYPosition;
	int prevIndex;
	int h;
	int w;
	int x;
	int y;
	int userIndexPosition;
	int userXPosition;
	int userYPosition;
	
	/**
	 * 
	 * Initialize Wumpus board settings (width, height, x, y)
	 */
	public WumpusWorld(int widh, int height, int xElements, int yElements) {
		this.h = height;
		this.w = widh;
		this.x = xElements;
		this.y = yElements;
		this.userIndexPosition = 0;
		this.userXPosition = 0;
		this.userYPosition = this.y * this.h;
	}
	
	/**
	 * 
	 * Check neighbors to know which element is near from user position
	 */
	public void getNeighbors(int index, int xPosition, int yPosition) {
		
		Player player = new Player();
		
		int north = index - y;
		int south = index + y;
		int west = index - 1;
		int east = index + 1;

		int[] neighborsIndexPosition = new int[]{north,south,west,east,index}; 
		
		for (int i = 0; i < neighborsIndexPosition.length; i++) {
			
			 if(neighborsIndexPosition[i] > 0) {
				 
				int elementIndex =  placeElements().indexOf(placeElements().get(neighborsIndexPosition[i]));
				
				switch(placeElements().get(neighborsIndexPosition[i])) {
				  case "PIT":
					  System.out.println("The breeze is noticeable, a deep pit is so close from you...");
					  System.out.println("Pit is in cell number " + elementIndex + "\n");
					  break;
				  case "WUMPUS":
					  System.out.println("There's a strange stench near from you ...");
					  System.out.println("Be careful Wumpus is in cell  number " + elementIndex + "\n");
					  break;
				  case "GOLD":
					  System.out.println("I can see the gold shining near ...");
					  System.out.println("Yes! Gold is in cell number " + elementIndex + " keep going and grab it! \n");
					  break;
				}
			 }
		}
		
		 player.getUserDirection(index, xPosition, yPosition);
	}
	
	/**
	 * 
	 * Set user position based on direction choice
	 */
	public void setUserPosition(String direction, int index, int xPosition, int yPosition) {
		switch(direction) {
		  case "NORTH":
			  prevYPosition = yPosition;
			  prevIndex = index;
			  yPosition +=  h;
			  index -= y;
			  System.out.println("Your are in cell number: " + index);
			  System.out.println("x: " + xPosition + " y: " + yPosition + "\n");
			  checkBoardLimits(direction, prevYPosition, index, xPosition, yPosition);
		    break;
		  case "EAST":
			  prevXPosition = xPosition;
			  prevIndex = index;
			  xPosition += w;
			  index += 1;
			  System.out.println("Your are in cell number: " + index);
			  System.out.println("x: " + xPosition + " y: " + yPosition+ "\n");
			  checkBoardLimits(direction, prevXPosition, index, xPosition, yPosition);
			 break;
		  case "SOUTH": 
			  prevYPosition = yPosition;
			  prevIndex = index;
			  yPosition -= h;
			  index += y;
			  System.out.println("Your are in cell number: " + index);
			  System.out.println("x: " + xPosition + " y: " + yPosition+ "\n");
			  checkBoardLimits(direction, prevYPosition, index, xPosition, yPosition);
			 break;
		  case "WEST":
			  prevXPosition = xPosition;
			  prevIndex = index;
			  xPosition -= w;		  
			  index -= 1;
			  System.out.println("Your are in cell number: " + index);
			  System.out.println("x: " + xPosition + " y: " + yPosition+ "\n");
			  checkBoardLimits(direction, prevXPosition, index, xPosition, yPosition);
			 break;
		}
	}
	
	/**
	 * 
	 * Check if user position is out of board based on direction choice
	 */
	public void checkBoardLimits(String direction, int prevPosition, int index, int xPosition, int yPosition) {
		switch(direction) {
		  case "NORTH":
			  if(yPosition > (h*y)) {
				  yPosition = prevPosition;
				 limitWarning(index, xPosition, yPosition); 
			  } else {
				 checkCurrenUserPosition(index, xPosition, yPosition);
			  }
		    break;
		  case "EAST":
			  if(xPosition >= (w*x)) {
				  xPosition = prevPosition;
				  limitWarning(index, xPosition, yPosition);				 
			 } else {
				 checkCurrenUserPosition(index, xPosition, yPosition);
			  }
		    break;
		  case "SOUTH":
			  if(yPosition <= 0) {
				  yPosition = prevPosition;
				  limitWarning(index, xPosition, yPosition);				 
			  } else {
				  checkCurrenUserPosition(index, xPosition, yPosition);
			  }
		    break;
		  case "WEST":
			  if(xPosition < 0) {
				 xPosition = prevPosition;
				 limitWarning(index, xPosition, yPosition);				
			} else {
				checkCurrenUserPosition(index, xPosition, yPosition);
			  }
			break;
		}
	}
	
	/**
	 * 
	 * Warn user & reset position
	 */
	public void limitWarning(int index, int xPosition, int yPosition) {
		
		Player player = new Player();
		
		System.out.println("Ups, you hit the wall!");
		index = prevIndex;
		
		player.getUserDirection(index, xPosition, yPosition);
	}
	
	/**
	 * 
	 * Check if user position is out of board
	 */
	public void checkCurrenUserPosition(int index, int xPosition, int yPosition) {
		
		switch(placeElements().get(index)) {
		  case "PIT":
			  System.out.println("Ups, you felt into a deep pit...bye");
		    break;
		  case "WUMPUS":
			  System.out.println("GAME OVER...WUMPUS catch you...bye");
		    break;
		  case "GOLD":
			  System.out.println("YOU WIN!");
			 break;
		  default:
			  System.out.println("Everything it´s fine, let´s check your neighbors.\n");
			  getNeighbors(index, xPosition, yPosition);
		}	
	}

	/**
	 * 
	 *Create random list to allocate elements (WUMPUS, GOLD, PIT, EMPTY) 
	 */
	public List<String> placeElements() {
		elements = new String[]{ "WUMPUS","GOLD","PIT"}; 
		
		if(board == null) {
			board = new ArrayList<>();
			
			for (int i = 0; i < elements.length; i++) {
				board.add(elements[i]);
			}
			
			for (int i = 1; i <= (x*y)-elements.length; i++) {
				board.add("EMPTY");
			}
			Collections.shuffle(board);
		} 
		return board;
	}
	
}
