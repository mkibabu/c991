package server;

import java.util.Random;

public class ServerGame 
{
	public ServerGame()
	{
	}

	/**
	 * RULES OF THE GAME: All or nothing.
	 * Roll a single die.If the die value is 3 or less,
	 * you lose. Else, you win the maximum (6) points.
	 */
	
	
	public boolean executeBranch(int bid, int input, int result)
	{
		if(bid == 0)
		{
			if(input > 3)		// checking diceRoll value here might be superfluous
			{
				int correctResult = 6;
				if (result == correctResult)
				{
					String message = "Correct result matches";
					System.out.println(message);
					
					return true;
				}
				return false;
			}
			return false;	
		}
		else if(bid == 1)
		{
			if(input > 3)		// checking diceRoll value here might be superfluous
			{
				int correctResult = 6;
				if (result == correctResult)
				{
					String message = "Correct result matches";
					System.out.println(message);
					
					return true;
				}
				return false;
			}
			return false;	
		}
		return false;
	}
	
		
	public static void main(String[] args)
	{
		ServerGame sg = new ServerGame();
		
		sg.executeBranch(0, 5, 6);
		//sg.executeBranch(1, 1, 0);
		//sg.executeBranch(2, 4, 0);

		
	}
	
}
	
