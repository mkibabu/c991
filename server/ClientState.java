package server;

/**
 * Represents the current state of a client, after a single round of the game
 * is played. Acts as an execution fragment: it holds the results of one round
 * of playing the game.
 *
 */

public class ClientState
{
	private int cid;	// the client id
	private int rid;	// the id of the round
	private int bid;	// the id of the branch taken in this round
	private int inp;	// the client's input at the beginning of the round
	private int out;	// the client's result/output after playing the round
	
	
	public ClientState(int c, int r, int b, int i, int o)
	{
		cid = c;
		rid = r;
		bid = b;
		inp = i;
		out = o;
	}
	
	public ClientState(String clientMessage)
	{
		String[] parts = clientMessage.split(":");
		cid = Integer.parseInt(parts[0]);
		rid = Integer.parseInt(parts[1]);
		bid = Integer.parseInt(parts[2]);
		inp = Integer.parseInt(parts[3]);
		out = Integer.parseInt(parts[4]);

	}
	
	public int getCid()
	{
		return cid;
	}
	
	public int getRid()
	{
		return rid;
	}
	
	public int getBid()
	{
		return bid;
	}
	
	public int getInp()
	{
		return inp;
	}
	
	public int getOut()
	{
		return out;
	}
}
