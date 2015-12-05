import java.awt.Point;
import java.util.List;

public class OthelloAI implements AI {
	
	List<Double> heuristicParameters;
	Double[][] heuristicMap;
	
	public OthelloAI(List<Double> hp)
	{
		// Initialize private variables
		this.heuristicParameters = hp;
		this.heuristicMap = new Double[8][8];
		
		// Initialize upper left quadrant of heuristicMap with values
		this.heuristicMap[0][0] = hp.get(0);
		this.heuristicMap[1][0] = hp.get(1);
		this.heuristicMap[2][0] = hp.get(2);
		this.heuristicMap[3][0] = hp.get(3);
		
		this.heuristicMap[0][1] = hp.get(1);
		this.heuristicMap[1][1] = hp.get(4);
		this.heuristicMap[2][1] = hp.get(5);
		this.heuristicMap[3][1] = hp.get(6);
		
		this.heuristicMap[0][2] = hp.get(2);
		this.heuristicMap[1][2] = hp.get(5);
		this.heuristicMap[2][2] = hp.get(7);
		this.heuristicMap[3][2] = hp.get(8);
		
		this.heuristicMap[0][3] = hp.get(3);
		this.heuristicMap[1][3] = hp.get(6);
		this.heuristicMap[2][3] = hp.get(8);
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
