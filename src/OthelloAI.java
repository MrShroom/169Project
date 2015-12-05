import java.awt.Point;

public class OthelloAI implements AI {
	
	double heuristicFlip;
	double[][] heuristicMap;
	
	// A quick test of the constructor
	public static void main(String [] args)
	{	
		// Print a test heuristic map
		double[] argument = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
		OthelloAI x = new OthelloAI(argument);
		
		for (int i = 0; i < 8; i ++){
			for (int j = 0; j < 8; j++){
				System.out.print(x.heuristicMap[i][j]);
			}
			System.out.println();
		}
		
		// Other testing here
	}

	
	public OthelloAI(double[] hp)
	public double evaluateBoard()
	{
		double score = 0.0;
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++){
			}
		return score;
	}
	public OthelloAI(double[] hp)
	{
		// Initialize private variables
		this.heuristicFlip = hp[9];
		this.heuristicMap = new double[8][8];
		
		// Initialize upper left quadrant of heuristicMap with values
		this.heuristicMap[0][0] = hp[0];
		this.heuristicMap[1][0] = hp[1];
		this.heuristicMap[2][0] = hp[2];
		this.heuristicMap[3][0] = hp[3];
		
		this.heuristicMap[0][1] = hp[1];
		this.heuristicMap[1][1] = hp[4];
		this.heuristicMap[2][1] = hp[5];
		this.heuristicMap[3][1] = hp[6];
		
		this.heuristicMap[0][2] = hp[2];
		this.heuristicMap[1][2] = hp[5];
		this.heuristicMap[2][2] = hp[7];
		this.heuristicMap[3][2] = hp[8];
		
		this.heuristicMap[0][3] = hp[3];
		this.heuristicMap[1][3] = hp[6];
		this.heuristicMap[2][3] = hp[8];
		this.heuristicMap[3][3] = -1.0;
		
		// Mirror values from upper left quadrant to every other quadrant
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				this.heuristicMap[7-i][7-j] = this.heuristicMap[i][j];
				this.heuristicMap[7-i][j]   = this.heuristicMap[i][j];
				this.heuristicMap[i][7-j]   = this.heuristicMap[i][j];
			}
		}
	}

	@Override
	public Point makeMove(Reversi game) {
		// TODO Auto-generated method stub
		return null;
	}

}
