package hello;
import java.util.Scanner;

public class WhackAMole {
	
	//fields
	int score = 0;
	int molesLeft = 0;//?
	int attemptsLeft;
	int gridDimension;//?
	int[][] moleGrid;
	
	//constructor
	WhackAMole(int numAttempts, int gridDimension){
		this.attemptsLeft = numAttempts;
		this.gridDimension = gridDimension;
		this.moleGrid = new int[gridDimension][gridDimension];//what are the default values?; how to break a long line like this?
		for(int i = 0; i < gridDimension; i++){
			for(int j = 0; j < gridDimension; j++){
				moleGrid[i][j] = 0;
			}
		}
	}
	
	public boolean place(int x, int y){
		if(x >= gridDimension || y >= gridDimension){
			return false;
		}
		if(moleGrid[x][y] == 1){
			return true;
		}
		moleGrid[x][y] = 1;
		molesLeft++;
		return true;
	}
	
	public void whack(int x, int y){
		if(x >= gridDimension || y >= gridDimension){
			return;
		}
		attemptsLeft--;
		if(moleGrid[x][y] == 1){
			score++;
			molesLeft--;
			moleGrid[x][y] = -1;
			return;
		}
		return;
	}
	
	public void prinGrid(){
		for(int i = 0; i < gridDimension; i++){
			for(int j = 0; j < gridDimension; j++){
				if(moleGrid[i][j] == 1){
					System.out.print("M ");
				}
				else if(moleGrid[i][j] == -1){
					System.out.print("W ");
				}
				else{
					System.out.print("* ");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WhackAMole game = new WhackAMole(50, 10);
		game.molesLeft = 10;
		for(int i = 0; i < 10; i++){
			game.moleGrid[1][i] = 1;		
		}
		System.out.println("You have a maximum of " + game.attemptsLeft + " attempts to get all the moles.");
		System.out.println("You can input -1 -1 to give up at any point in the game.");
		while(game.molesLeft > 0 && game.attemptsLeft >0){			
			System.out.println("Now input x and y coordinates ranging from 1 to 10 where you wold like to take a whack:");
			Scanner scan = new Scanner(System.in);
			int x = scan.nextInt();
			if(x > 10){
				continue;
			}
			int y = scan.nextInt();
			if(y > 10){
				continue;
			}
			if(x == -1 && y == -1){
				System.out.println("You gave up!");
				game.prinGrid();
				scan.close();
				return;//?
			}
			game.whack(x - 1, y - 1);
			scan.close();
			System.out.println("You have " + game.attemptsLeft + " attempts left to get all the moles.");
		}
		if(game.molesLeft == 0){
			System.out.println("You won!");
		}
		else{
			System.out.println("You ran out of attempts.");
		}
		game.prinGrid();
	}

}
