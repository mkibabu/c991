package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Verifies the results of a client's state via its execution prefix.
 *
 */

public class Verifier
{
	// Map of <ClientId, ExecutionPrefix> of clients exhbiting valid behavior
	private HashMap<Integer, ArrayList<ClientState>> validClients;
	// Map of <ClientId, ExecutionPrefix> of clients exhbiting invalid behavior
	private HashMap<Integer, ArrayList<ClientState>> invalidClients;
	// Module that reverses invalid client message execution
	private BackupIterator bi;
	// Server's mofified copy of the client's game
	private ServerGame sg;
	

	public Verifier()
	{
		validClients = new HashMap<Integer, ArrayList<ClientState>>();
		invalidClients = new HashMap<Integer, ArrayList<ClientState>>();
		bi = new BackupIterator();
		sg = new ServerGame();
	}
	
	/**
	 * Verifies whether this iteration of the client's state (which acts
	 * as an execution prefix) is a valid iteration. If it is, the client
	 * state is appended to the validClients map; else, the client is moved
	 * to the invalidClient map and the backup iterator initiated.
	 * @param cs - the client state/execution prefix to test
	 */
	public void verifyClientState(ClientState cs)
	{
		boolean valid = sg.executeBranch(cs.getBid(), cs.getInp(), cs.getOut());
		ArrayList<ClientState> list = null;
		if(valid)
		{
			// add the client and the new state to the valid clients map
			
			
			if(validClients.get(cs.getCid()) == null)
			{
				list = new ArrayList<ClientState>();
			}
			else
			{
				list = validClients.get(cs.getCid());
			}
			list.add(cs);
			validClients.put(cs.getCid(), list);
		}
		else
		{
			// move the client to the invalid client list
			if(invalidClients.get(cs.getCid()) != null)
			{
				list = invalidClients.get(cs.getCid());

			}
			else if (validClients.get(cs.getCid()) != null)
			{
				list = validClients.get(cs.getCid());
			}
			else
			{
				list = new ArrayList<ClientState>();
			}
			list.add(cs);
			invalidClients.put(cs.getCid(), list);
			// initiate backup procedures
			bi.verifyClient(list);
		}
	}
		
	public static void main(String[] args) throws Exception
	{
		Verifier ver = new Verifier();
		
		ServerSocket welcomeSocket = new ServerSocket(6789);

		Socket connectionSocket = welcomeSocket.accept();

		while (true)
		{
			// socket read/write
			BufferedReader inFromClient =
					new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			
			// read a line from the socket
			String clientMessage = inFromClient.readLine();
			
			// create a clientState from it
			ClientState cs = new ClientState(clientMessage);
			
			// verify this client state
			ver.verifyClientState(cs);
		}
	}
}
