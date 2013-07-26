package server;

import java.util.ArrayList;

public class BackupIterator
{
	private int cid;		// the client ID to work with
	private ArrayList<ClientState> prefix;	// execution prefix to go back over
	private int validStateIndex = 0;
	
	private BackupIterator(int cid, ArrayList<ClientState> prefix)
	{
		this.cid = cid;
		this.prefix = prefix;
	}
	
	public BackupIterator()
	{
		
	}
	
	public void verifyClient(ArrayList<ClientState> cst)
	{
		for (int i = 0; i < cst.size(); i++)
		{
			// get the .jpf script to run
			if (verifyState(cst.get(i)) == false)
			{
				// we've found the first incorrect client state
				validStateIndex = i;
				
			}
		}
	}
	
	/**
	 * Verifies a single execution fragment of an execution prefix.
	 * Fashioned in the roughly the same manner as executeBranch()
	 * in server.ServerGame; however, should invoke the jpf script
	 * after finding the correct result, rather than printing the 
	 * result. 
	 * @return
	 */
	private boolean verifyState(ClientState cst)
	{
		// this is where the server failed.
		// could not execute .jpf script from here
		return true;
	}
}
