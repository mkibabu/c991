package client;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class Game 
{
	private int clientId;
	private int branchId;
	private int roundId;
	private int result;
	private Random dice;

	public Game(int cid)
	{
		clientId 	= cid;	// unique id for each client
		branchId	= -1;	// unique id for each branch
		roundId		= -1;	// id of each iteration/round of the game
		result		= -1;	// result of each round/iteration of the game
		dice = new Random();
	}

	/**
	 * RULES OF THE GAME: All or nothing.
	 * Roll a single die.If the die value is 3 or less,
	 * you lose. Else, you win the maximum (6) points.
	 */
	
	public void playGame()
	{
		// Playing a new round; increase round id
		roundId += 1;

		int diceRoll = 1 + dice.nextInt(6);
		
		if(diceRoll <= 3)
		{
			branchId = 0;
			result = 0;
			System.out.println("Roll = " + diceRoll + ", result = 0");
			sendMessage(diceRoll, roundId, branchId, result);				
		}
		else
		{
			branchId = 1;
			result = 6;
			System.out.println("Roll = " + diceRoll + ", result = 6");
			sendMessage(diceRoll, roundId, branchId, result);				
		}
		
	}
	
	public int getRid()
	{
		return roundId;
	}

	public static void main (String[] args)
	{
		Game client = new Game(55);
		while(client.getRid() < 4)
		{
			client.playGame();
		}

	}

	private void sendMessage(int droll, int rid, int bid, int res) 
	{
		try
		{
			Socket clientSocket = new Socket("localhost", 6789);

			String mes = Integer.toString(clientId) + ":" + Integer.toString(droll) + ":"
					+ Integer.toString(rid) + ":" + Integer.toString(bid) + ":"
					+ Integer.toString(res);

			InetAddress addr = InetAddress.getByName("localhost");

			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

			outToServer.writeBytes(mes + '\n');

			clientSocket.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
