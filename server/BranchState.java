package server;

/**
 * Alternate class to be used to verify the server's output.
 * The related .jpf file if BranchState.jpf
 * @author Michael
 *
 */
public class BranchState 
{
	
	public int playRound (int in, int out)
	{
		int branchId;
		if(in <= 3)
		{
			branchId = 0;
			out = 0;
			System.out.println("Roll = " + in + ", result = " + out + ", branchId = " + branchId);
		}
		else
		{
			branchId = 1;
			out = 6;
			System.out.println("Roll = " + in + ", result = " + out + ", branchId = " + branchId);
		}
		
		return branchId;

	}
	
	public static void main(String [] args)
	{
		BranchState br = new BranchState();
		br.playRound(2, 0);
		br.playRound(2, 0);

	}
}
