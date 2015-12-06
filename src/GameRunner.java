import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
public class GameRunner {
	
	private int numOfAI = 10;
	private AI[] opponents;
	private FileWriter writer;
	private int gameNum = 1;
	public GameRunner() throws IOException{
		opponents = new AI[numOfAI];
		opponents = makeRandomAIs();
		writer = new FileWriter("data.csv");
		writer.write("GameSession#,");
		writer.write("AIParameters,");
		writer.write("WinRate,");
		writer.write("AveargeTime");
	}
	public double playSession(double[] AIParameters) throws IOException{
		AI ours = makeAI(AIParameters);
		double winRate = 0;
		double totalWins = 0;
		double averageTime = 0;
		double totalTime = 0;
		long currentTime = System.currentTimeMillis();
		for(int i = 0; i < numOfAI; i++){
			currentTime = System.currentTimeMillis();
			double thisTime;
			if(playGame(ours, opponents[i]) == 1){
				thisTime = System.currentTimeMillis() - currentTime;
				totalTime += thisTime;
				totalWins++;
			}
			
			currentTime = System.currentTimeMillis();
			if(playGame(opponents[i],ours) == 2){
				thisTime = System.currentTimeMillis() - currentTime;
				totalTime += thisTime;
				totalWins++;
			}
		}
		winRate = totalWins/(numOfAI*2);
		averageTime = totalTime/(numOfAI*2);
		writer.write(Integer.toString(gameNum));
		writer.write(" ,");
		for(int j = 0; j < 9; j++){
			writer.write(Double.toString(AIParameters[j]));
			writer.write(" ");
		}
		writer.write(",");
		writer.write(Double.toString(winRate));
		writer.write(",");
		writer.write(Double.toString(averageTime));
		gameNum++;
		return winRate;
	}
	
	private double[] generateParameters(){
		double[] param = new double[9];
		for(int i = 0; i < 9; i++){
			param[0] = -100 + (Math.random()*200);
		}
		return param;
	}
	
	private AI makeAI(double[] AIParameters){
		return new OthelloAI(AIParameters);
	}
	
	private AI[] makeRandomAIs(){
		AI[] opponents = new AI[numOfAI];
		for(int i = 0; i < numOfAI; i++){
			double[] AIParameters = generateParameters();
			makeAI(AIParameters);
		}
		return opponents;
	}
	
	private int playGame(AI player1, AI player2){
		Reversi game = new Othello();
		int winner;
		do{
			AI currentAI;
			if(game.currentTurn() == 1){
				currentAI = player1;
			}else{
				currentAI = player2;
			}
			Point move = currentAI.makeMove(game);
			winner = game.makeMove(move);
		}
		while(winner == -1);
		return winner;
	}
	
}