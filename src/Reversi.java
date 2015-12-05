import java.awt.Point;
public interface Reversi {
	
	public int currentTurn();
	
	public int makeMove(Point move);
	
	public Point[] availableMoves();
	
	public byte [][] getBoardState();
	
	public Reversi clone();
}
