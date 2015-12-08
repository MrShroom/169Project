
public class AITests {

	public static void main(String[] args) 
	{
		try{		
			GameRunner aGame = new GameRunner();
			double[] argument = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
			System.out.println(aGame.playSession(argument));
			double[] argument2 = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0};
			System.out.println(aGame.playSession(argument2));
			aGame.close();
		}
		catch(Exception e)
		{
			System.out.println("hi");
			System.out.println(e.getMessage());
		}

	}

}
