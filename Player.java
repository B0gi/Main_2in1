import java.util.ArrayList;

class Player {
	private int[] color = new int[2];
	private int player = 1;
	private int step = 0;
	private boolean setColor = false;
	
	public void reset(){
		setColor = false;
	}
	public void changePlayer(){
		if(player == 1)  player = 2;
		else if(player == 2) player = 1;
	}
	public void setColor(int theColor){
		if(setColor) return;
		if(theColor == 0)  {color[0] = 0; color[1] = 1;}
		else if(theColor == 1)  {color[0] = 1; color[1] = 0;}
		setColor = true;
	}
	public void addStep(){
		step++;
	}
	public void decStep(){
		step--;
	}
	public int getPlayer(){
		return player;
	}
	public int getColor(){
		return color[player-1];
	}
	public String getSColor(){
		if(color[player-1] == 0) return "¬õ¤è";
		else return "¶Â¤è";
	}
	public int getStep(){
		return step;
	}
	public boolean getSetColor(){
		return setColor;
	}
	public boolean check(Chess chess){
		if(chess.getColor() == color[player-1])
			return true; 
		return false;
	}
}

class Coordinate {
	private Chess A;
	private Chess B;
	private int X1;
	private int Y1;
	private int X2;
	private int Y2;
	
	public Coordinate(Chess A, Chess B, int X1, int Y1, int X2, int Y2){
		this.A = A;
		this.B = B;
		this.X1 = X1;
		this.Y1 = Y1;
		this.X2 = X2;
		this.Y2 = Y2;
	}
	
	public Chess getA(){
		return A;
	}
	public Chess getB(){
		return B;
	}
	public int getX1(){
		return X1;
	}
	public int getY1(){
		return Y1;
	}
	public int getX2(){
		return X2;
	}
	public int getY2(){
		return Y2;
	}
}

class Regret {
	private ArrayList<Coordinate> regret = new ArrayList<Coordinate>();
	
	public Coordinate pop(){
		MainFrame mainFrame = new MainFrame();
		ChessBoard board = mainFrame.getBoard();
		ChessAction action = board.getChessAction();
		Player player = action.getPlayer();
		if(regret.isEmpty()) return null;
		int index = regret.size()-1;
		if(index == 0) {
			player.reset();
		}
		return regret.remove(index);
	}
	public void add(Coordinate c){
		regret.add(c);
	}
}