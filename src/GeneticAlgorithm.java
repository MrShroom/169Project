import java.util.Random;

public class GeneticAlgorithm 
{
	private static double[][] currentGenetration;
	private static double[] currentStatus;
	private static int currentGenerationNumber = 1;
	private final static int numPopulation = 10;//must be even
	private final static int numParameters = 10;
	private final static int maxGenerations = 4;
	private final static double stopIfGreater = 0.95;
	private final static double mutationProb = 0.01;
	private static double[] solution;
	private static double solutionRatio;
	private static GameRunner runner;
	private static Random rnd;
	
	public static void createFirstGeneration()
	{
		currentGenetration = new double[numPopulation][numParameters]; 
		currentStatus = new double[numPopulation];
		for(int i = 0; i< numPopulation;i++)
		{
			currentGenetration[i] = runner.generateParameters();
			currentStatus[i] =0;
		}		
	}
	
	public static void runSimulation()
	{
		Thread [] t = new Thread [numPopulation];
		for(int i =0; i<numPopulation;i++)
		{
			t[i] = new Thread(new SimulationRunner(i));
			t[i].start();
		}
		for(int i =0; i<numPopulation;i++)
		{
			try 
			{
				t[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
	
	private static class SimulationRunner implements Runnable 
	{
		int index;
		public SimulationRunner(int whichindex)
		{
			index = whichindex;
		}
	    
		public void run() 
	    {
	        
	        try 
	        {
	        	currentStatus[index] = runner.playSession(currentGenetration[index]);
	        } 
	        catch (Exception e) 
	        {
	        	System.out.println("Something went wrong in SimulationRunner");
	        	e.printStackTrace();
	        }
	    }
	}
	
	private static boolean isSolution()
	{
		if(currentGenerationNumber > maxGenerations)
		{
			double highest = 0;
			int index =0;
			for( int j =0 ; j < numPopulation ; j++)
			{
				if(currentStatus[j] >= highest)
				{
					highest = currentStatus[j];
					index = j;
				}				
			}
			solution = new double [numParameters];
			solutionRatio = highest;
			System.arraycopy(currentGenetration[index],0, solution, 0, numParameters);
			return true;
		}		
		for( int j =0 ; j < numPopulation ; j++)
		{
			if(currentStatus[j] >=stopIfGreater)
			{
				solution = new double [numParameters];
				solutionRatio = currentStatus[j];
				System.arraycopy(currentGenetration[j],0, solution, 0, numParameters);
				return true;					
			}
		}
		return false;
	}
	
	private static double[][] chooseParents()
	{
		int numberParents = numPopulation/2;
		double[][] parents = new double[numberParents][numParameters];
		for(int i = 0; i < numberParents; i++)
		{
			double highest = 0;
			int index =0;
			for( int j =0 ; j < numPopulation ; j++)
			{
				if(currentStatus[j] >= highest)
				{
					highest = currentStatus[j];
					index = j;
				}				
			}
			if( i == 0)
			{
				System.out.println("Generation " + currentGenerationNumber 
						+ " best :" + highest );
			}
			System.arraycopy( currentGenetration[index], 0, parents[i], 0,numParameters);
			currentStatus[index] = -1;
		}
		return parents;
	}	
	
	private static void breedFitest()
	{
		double[][] parents = chooseParents();
		currentGenerationNumber ++;
		for( int i = 0; i< numPopulation/4 ; i += 2)
		{
			for(int j = 0 ; j < numParameters/4 ; j ++)
			{
				if(rnd.nextDouble() < mutationProb)
					currentGenetration[i][j] = rnd.nextInt(200)-100;
				else
					currentGenetration[i][j] = parents[i][j];
				
				if(rnd.nextDouble() < mutationProb)
					currentGenetration[i+1][j] = rnd.nextInt(200)-100;
				else
					currentGenetration[i+1][j] = parents[i+1][j];
			}
			for(int j = numParameters/2 ; j < numParameters ; j ++)
			{
				if(rnd.nextDouble() < mutationProb)
					currentGenetration[i][j] = rnd.nextInt(200)-100;
				else
					currentGenetration[i][j] = parents[i+1][j];
				
				if(rnd.nextDouble() < mutationProb)
					currentGenetration[i+1][j] = rnd.nextInt(200)-100;
				else
					currentGenetration[i+1][j] = parents[i][j];
			}
		}
		for( int i = 0, index = numPopulation/2 ; i< numPopulation/4 ;index+=2, i++)
		{
			int parent2 = numPopulation/2 - i -1;
			for(int j = 0 ; j < numParameters/4 ; j ++)
			{
				if(rnd.nextDouble() < mutationProb)
					currentGenetration[index][j] = rnd.nextInt(200)-100;
				else
					currentGenetration[index][j] = parents[i][j];
				
				if(rnd.nextDouble() < mutationProb)
					currentGenetration[index+1][j] = rnd.nextInt(200)-100;
				else
					currentGenetration[index+1][j] = parents[parent2][j];
			}
			for(int j = numParameters/2 ; j < numParameters ; j ++)
			{
				if(rnd.nextDouble() < mutationProb)
					currentGenetration[index][j] = rnd.nextInt(200)-100;
				else
					currentGenetration[index][j] = parents[parent2][j];
				
				if(rnd.nextDouble() < mutationProb)
					currentGenetration[index+1][j] = rnd.nextInt(200)-100;
				else
					currentGenetration[index+1][j] = parents[i][j];
			}
			
		}
	}
	
	public static void main(String[] args) 
	{
		try{
			runner = new GameRunner();
			rnd = new Random();
			createFirstGeneration();
			runSimulation(); 
			while(!isSolution() )
			{
				breedFitest();
				runSimulation(); 
			}
			
			System.out.println("Best Solution found this run: ");
			System.out.println("Generation: " + currentGenerationNumber);
			System.out.println("win ratio: " + solutionRatio);
			for(int i =0 ; i <numParameters; i++ )
			{
				System.out.println("p[" + i +"]: " + solution[i]);
			}			
			runner.close();
			
		}catch(Exception ex)
		{
			System.out.println("something went wrong:");
			System.out.println(ex.getMessage());
			ex.printStackTrace();		
		}
	}
}
