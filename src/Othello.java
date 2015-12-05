import java.awt.Point;

public class Othello implements Reversi{

	private byte [][] boardState;
	private int width = 8;
	private int height = 8;
	private byte currentTurn = 1;
	
	public Othello() {
		boardState = new byte[height][width];
		for (byte i = 0; i < height; i++) {
			for (byte j = 0; j < width; j++) {
				boardState[i][j] = 0;
			}
		}
		setCenter();
	}
	
	@Override
	public int currentTurn() {
		// TODO Auto-generated method stub
		return currentTurn;
	}

	@Override
	public int makeMove(Point move) {
		// TODO Auto-generated method stub
		flipTurn();
		return 0;
	}

	@Override
	public Point[] availableMoves() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[][] getBoardState() {
		byte[][] boardClone = new byte[height][width];
		for(byte i = 0; i < height; i++){
			for(byte j = 0; j < width; j++){
				boardClone[i][j] = boardState[i][j];
			}
		}
		
		return boardClone;
	}

	@Override
	public Reversi clone() {
		// TODO Auto-generated method stub
		return null;
	}

	private void setCenter(){
		boardState[height/2-1][width/2-1] = 1;
		boardState[height/2][width/2-1] = 2;
		boardState[height/2-1][width/2] = 2;
		boardState[height/2][width/2] = 1;
	}

	@Override
	public int count1() {
		// TODO Auto-generated method stub
		int count = 0;
		for(byte i = 0; i < height; i++){
			for(byte j = 0; j < width; j++){
				if(boardState[i][j] == 1){
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public int count2() {
		// TODO Auto-generated method stub
		int count = 0;
		for(byte i = 0; i < height; i++){
			for(byte j = 0; j < width; j++){
				if(boardState[i][j] == 2){
					count++;
				}
			}
		}
		return count;
	}
	
	private void flipTurn(){
		currentTurn = (byte) (currentTurn == 1 ? 2 : 1);
	}
	
}
