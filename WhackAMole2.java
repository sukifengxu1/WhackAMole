package demo;
import java.util.Scanner;
import java.util.Random;

public class WhackAMole {
	
	// fields
	private int score;
	private int molesLeft;
	private int attemptsLeft;
	private int gridDimension; // I added this.
	private char[][] moleGrid;
	
	/**
	 * Constructor for the game, specifies total number of whacks allowed, 
	 * and the grid dimension. Initial number of moles is set to 0.
	 * @param numAttempts the initial total number of whacks allowed
	 * @param gridDimension specifies the size of the 2-dimensional mole grid
	 */
	WhackAMole (int numAttempts, int gridDimension) {
		this.score = 0;
		this.molesLeft = 0;
		this.attemptsLeft = numAttempts;
		this.gridDimension = gridDimension;
		this.moleGrid = new char[gridDimension][gridDimension];
		// Initialize the grid with '*'s filled. (i.e. no mole)
		for(int i = 0; i < gridDimension; i++){
			for(int j = 0; j < gridDimension; j++){
				moleGrid[i][j] = '*';
			}
		}
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getMolesLeft() {
		return this.molesLeft;
	}
	
	public int getAttemptsLeft() {
		return this.attemptsLeft;
	}
	
	/**
	 * Given a location, place a mole at that location. 
	 * Also (possibly) update number of moles left.          
	 * @param x x-coordinate of the location, counting from 0
	 * @param y y-coordinate of the location, counting from 0
	 * @return true if you can place a mole at that location
	 */
	public boolean place(int x, int y) {
		// return false if the given location is out of bound
		if(x >= gridDimension || y >= gridDimension || x < 0 || y < 0){
			return false;
		}
		// return false if a mole already exists at the location
		if(moleGrid[x][y] == 'M'){
			return false;
		}
		moleGrid[x][y] = 'M';
		molesLeft++;
		return true;
	}
	
	/**
	 * Given a location, take a whack at that location. 
	 * If that location contains a mole, the score (earn 1 per time), 
	 * number of attemptsLeft, and molesLeft is updated. 
	 * If that location does not contain a mole, 
	 * only attemptsLeft is updated.
	 * @param x x-coordinate of the location given
	 * @param y y-coordinate of the location given
	 */
	public void whack(int x, int y) {
		if(x >= gridDimension || y >= gridDimension || x < 0 || y < 0){
			System.out.println("Coordinates out of bound. No updates made!");
			return;
		}
		attemptsLeft--;
		if(moleGrid[x][y] == 'M'){
			moleGrid[x][y] = 'W';
			molesLeft--;
			score++;
		}
	}
	
	/**
	 * Print the grid without showing where the moles are. 
	 * For every spot that has recorded a “whacked mole,” print out a W, 
	 * or * otherwise.
	 */
	public void printGridToUser() {
		for(int i = 0; i < gridDimension; i++){
			for(int j = 0; j < gridDimension; j++){
				if(moleGrid[i][j] == 'W'){
					System.out.print("W ");
				}
				else{
					System.out.print("* ");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * Print the grid completely. 
	 * The places where the moles are should be printed as an ‘M’. 
	 * The places where the moles have been whacked should be printed as a ‘W’. 
	 * The places that don’t have a mole should be printed as *.
	 */
	public void printGrid() {
		for(int i = 0; i < gridDimension; i++){
			for(int j = 0; j < gridDimension; j++){
				System.out.print(moleGrid[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// a maximum of 50 attempts to get all the moles
		// a 10 by 10 grid where you a total of 10 moles are randomly placed
		WhackAMole game = new WhackAMole(50, 10);
		Random generator = new Random();
		while (game.getMolesLeft() < 10) {			
			int x = generator.nextInt(10);
			int y = generator.nextInt(10);
			game.place(x, y);
		}
		// Because I separate the input process of x and y by design, 
		// users can input -1 ONLY ONCE to give up. 
		System.out.println("You have " + game.getAttemptsLeft() + " attempts left to get all the moles.");
		System.out.println("You take a whack by entering the location's x and y coordinates successively.");
		System.out.println("At any point in the game, you can input a -1 as the x coordinate to give up.");
		Scanner scan = new Scanner(System.in);
		while (game.getMolesLeft() > 0 && game.getAttemptsLeft() >0) {			
			// decide the x coordinate
			System.out.println("Now input an x coordiante ranging from 0 to 9:");
			int x = scan.nextInt();
			while (x != (int)x || x < -1 || x > 10) {
				System.out.println("x should be an integer ranging from 0 to 9 (or -1 to give up):");
				x = scan.nextInt();
			}
			// check if the user gives up
			if (x == -1) {
				System.out.println("You gave up!");
				game.printGrid();
				scan.close();
				return;
			}
			// decide the y coordinate
			System.out.println("Now input a y coordiante ranging from 0 to 9:");
			int y = scan.nextInt();
			while (y != (int)y || y < 0 || y > 10) {
				System.out.println("y should be an integer ranging from 0 to 9:");
				y = scan.nextInt();
			}
			game.whack(x, y);
			game.printGridToUser();
			System.out.println("You have " + game.getAttemptsLeft() + " attempts left.");
		}
		scan.close();
		if (game.getMolesLeft() == 0) {
			System.out.println("Congratulations! You whacked all the moles.");
		}
		else{
			System.out.println("Sorry! You ran out of attempts.");
		}
		game.printGrid();
	}

}