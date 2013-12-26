import java.awt.Graphics;
import java.util.Observable;

class ChessAction extends Observable{
	ChessAction chessAction;
	public ChessAction(){}
	
	public ChessAction(String game){
		if(game == "Five")
			chessAction = new FChessAction();
		else if(game == "Dark")
			chessAction = new DChessAction();
	}
	public void regret(){
		chessAction.regret();
	}
	public void check(int x, int y){
		chessAction.check(x, y);
	}
	public int getCount(){
		return chessAction.getCount();
	}
	public int getXX() {
		return chessAction.getXX();
	}
	public int getYY() {
		return chessAction.getYY();
	}
	public int getWinStatus(){
		return chessAction.getWinStatus();
	}
	public ChessAction getChessAction(){
		return chessAction;
	}
	public Player getPlayer() {
		return chessAction.getPlayer();
	}
	public Message getMessage(){
		return chessAction.getMessage();
	}
}

class FChessAction extends ChessAction{
	private int count = 0;
	private int XX, YY, winStatus;
	private Message message = new Message();
	public FChessAction(){
		addObserver(message);
	}
	public int getCount(){
		return count;
	}
	public int getXX() {
		return XX;
	}
	public int getYY() {
		return YY;
	}
	public int getWinStatus(){
		return winStatus;
	}
	
	public void check(int x, int y){
		MainFrame mainFrame = new MainFrame();
		ChessBoard board = mainFrame.getBoard();
		String s = "";
		
		if(!board.getGameStatus()) return;
		if(board.getFChess(x, y) > 0){
			s = "("+ x +","+ y + ")已放過棋子\n";
			new HasChess(s);
		}else if(board.getFChess(x, y) == 0){
			s = "Player"+ board.getPlayer() +": 放置("+ x +","+ y +")\n";
			setChess(x, y);
		}
		setChanged();
		notifyObservers(s);
	}
	
	//****	放置棋子	****//
	private void setChess(int x, int y){
		MainFrame mainFrame = new MainFrame();
		ChessBoard board = mainFrame.getBoard();
		int ROWS = board.getRows(), COLS = board.getCols();
		System.out.println(x +" "+ y);
		if(x > board.getRows() || y > board.getCols()) return;
		board.setChess(x, y, board.getPlayer());
		board.setChessRegret(x, y, count);
		count++;
		Graphics g = board.getGraphics();
		if(checkWin()){
			board.update(g);
			new FWin(board.getPlayer());
		}else{
			board.setPlayer();
		}
		if(count == (ROWS+1)*(COLS+1) && winStatus == 0){
			new FWin(0);
		}
		board.update(g);
	}
	
	//****	悔棋	****//
	public void regret(){
		MainFrame mainFrame = new MainFrame();
		ChessBoard board = mainFrame.getBoard();
		int ROWS = board.getRows(), COLS = board.getCols();
		String s = "";
		if(count == 0) return;
		for(int i = 0; i <= ROWS; i++){
			for(int j = 0; j <= COLS; j++){
				if(board.getChessRegret(i, j) == (count-1)){
					board.setChess(i, j, 0);
					board.setChessRegret(i, j, -1);
					count--;
					Graphics g = board.getGraphics();
					board.update(g);
					board.setPlayer();
					s = "【悔棋】Player"+ board.getPlayer() +":放置("+ i +","+ j +")\n";
					setChanged();
					notifyObservers(s);
					return;
				}
			}
		}
	}
	
	//**** 判斷贏	****//
	private boolean checkWin(){
		MainFrame mainFrame = new MainFrame();
		ChessBoard board = mainFrame.getBoard();
		int ROWS = board.getRows(), COLS = board.getCols();
		for(int i = 0; i <= ROWS; i++){
			XX = i;
			for(int j = 0; j <= COLS; j++){
				YY = j;
				if(board.getFChess(i, j) > 0){
					switch(board.getFChess(i, j)){
						case 1:
							if(chechWinStatus(i,j, 1)){
								count++;
								return true;
							}
							break;
						case 2:
							if(chechWinStatus(i,j, 2))
								return true;
							break;
					}
				}
			}
		}
		return false;
	}
	
	//**** 判斷贏的型態	****//
	private boolean chechWinStatus(int i, int j, int player){
		MainFrame mainFrame = new MainFrame();
		ChessBoard board = mainFrame.getBoard();
		int ROWS = board.getRows(), COLS = board.getCols();
		int winCount;
		winCount = 0;
		winStatus = 1;
		for(int k = i; k<=ROWS;k++){
			if(board.getFChess(k, j)==player){
				winCount++;
				if(winCount == 5)
					return true;
			}else break;
		}
		winCount = 0;
		winStatus = 2;
		for(int l = j; l<=COLS;l++){
			if(board.getFChess(i, l)==player){
				winCount++;
				if(winCount == 5)
					return true;
			}else break;
		}
		winCount = 0;
		winStatus = 3;
		for(int k=i,l=j;k<=ROWS&&l<=COLS;k++,l++){
			if(board.getFChess(k, l)==player){
				winCount++;
				if(winCount == 5) 
					return true;
			}else break;
		}
		winCount = 0;
		winStatus = 4;
		for(int k=i,l=j;k<=ROWS&&l>=0;k++,l--){
			if(board.getFChess(k, l)==player){
				winCount++;
				if(winCount == 5) 
					return true;
			}else break;
		}
		winStatus = 0;
		return false;
	}
	public Message getMessage(){
		return message;
	}
}

class DChessAction extends ChessAction{
	private int step = 0;
	private int tmpX, tmpY;
	private static Player player = new Player();
	private Regret regret = new Regret();
	private Coordinate coordinate;
	private Chess A;
	private Chess B;
	private Message message = new Message();
	public DChessAction(){
		addObserver(message);
	}
	public void check(int X, int Y){
		MainFrame mainFrame = new MainFrame();
		ChessBoard board = mainFrame.getBoard();
		String s = "";
		int ROWS = board.getRows();
		int COLS = board.getCols(); 
		if(X < COLS && Y < ROWS){
			if(!board.getChess(X, Y).getState()){
				board.getChess(X, Y).setState(true);
				step = 0;
				player.setColor(board.getChess(X, Y).getColor());
				player.changePlayer();
				player.addStep();
				s = "【翻棋】"+ player.getSColor() +" step"+ (player.getStep()+1)/2 +"："+
						" 翻開("+ X +","+ Y +")  "+ board.getChess(X, Y).toString() +"\n";
				coordinate = new Coordinate(null, null, X, Y, -1, -1);
				regret.add(coordinate);
			}else{
				if(board.hasChess(X ,Y)){
					if(step == 0 || A.getColor() == board.getChess(X ,Y).getColor()){
						A = board.getChess(X ,Y);
						if(!player.check(A)) return;
						tmpX = X;
						tmpY = Y;
						step = 1;
					}else if(step == 1){
						if(!move(tmpX, tmpY, X, Y)){
							new HasChess("移動");
							s = "【警告】"+ A.toString() +"("+ tmpX +","+ tmpY +") 不能移動到 ("+ X +","+ Y +")\n";
							step = 0;
						}else{
							B = board.getChess(X ,Y);
							if(eat(tmpX, tmpY, X, Y)){
								board.setChess(X, Y, A);
								board.setHasChess(tmpX, tmpY, false);
								B.setDie(true);
								step = 0;
								player.changePlayer();
								player.addStep();
								s = "【吃棋】"+ player.getSColor() +" step"+ (player.getStep()+1)/2 +"："+ A.toString()
										+"("+ tmpX +","+ tmpY +") 吃 "+ B.toString() +"("+ X +","+ Y +")\n";
								coordinate = new Coordinate(A, B, tmpX, tmpY, X, Y);
								regret.add(coordinate);
							}else{
								new HasChess("吃");
								s = "【警告】"+ A.toString() +"("+ tmpX +","+ tmpY +") 不能吃 "+
										B.toString() + "("+ X +","+ Y +")" +"\n";
								step = 0;
							}
						}
					}
				}else if(step == 1){
					if(!move(tmpX, tmpY, X, Y)){
						new HasChess("移動");
						s ="【警告】"+ A.toString() +"("+ tmpX +","+ tmpY +") 不能移動到 ("+ X +","+ Y +")\n";
						step = 0;
					}else{
						board.setHasChess(X, Y, true);
						board.setChess(X, Y, A);
						board.setHasChess(tmpX, tmpY, false);
						step = 0;
						player.changePlayer();
						player.addStep();
						s = "【移動】"+ player.getSColor() +" step"+ (player.getStep()+1)/2 +"："+
								A.toString() +"("+ tmpX +","+ tmpY +")   移動到 ("+ X +","+ Y +")\n";
						coordinate = new Coordinate(A, null, tmpX, tmpY, X, Y);
						regret.add(coordinate);
					}
				}
			}
			setChanged();
			notifyObservers(s);
			board.paint(board.getGraphics());
			if(step == 1){
				board.selectChess(X, Y, board.getGraphics());
			}
			int winer = win();
			if(winer != -1){
				new DWin(winer);
				board.setGameStatus(false);
			}
		}
	}

	private boolean eat(int X1, int Y1, int X2, int Y2){
		MainFrame frame = new MainFrame();
		ChessBoard board = frame.getBoard();
		int priorityA = A.getPriority();
		int priorityB = B.getPriority();
		int colorA = A.getColor();
		int colorB = B.getColor();
		if(colorA == colorB){
			return false;
		}
		if(priorityA == 1) {
			int count = 0;
			if(X1 < X2){
				for(int i=X1;i<X2;i++) 
					if(board.getHasChess(i, Y2)) count++;
			}else if(X1 > X2){
				for(int i=X1;i>X2;i--) 
					if(board.getHasChess(i, Y2)) count++;
			}else if(Y1 < Y2){
				for(int i=Y1;i<Y2;i++) 
					if(board.getHasChess(X2, i)) count++;
			}else if(Y1 > Y2){
				for(int i=Y1;i>Y2;i--) 
					if(board.getHasChess(X2, i)) count++;
			}
			System.out.println(count);
			if(count == 2) return true;
		}else if(priorityA == 0 && priorityB == 6){
			return true;
		}else if(priorityA == 6 && priorityB == 0){
			return false;
		}else if(priorityA >= priorityB){
			return true;
		}
		return false;
	}
	
	private boolean move(int X1, int Y1, int X2, int Y2){
		MainFrame frame = new MainFrame();
		ChessBoard board = frame.getBoard();
		if(A.getPriority() == 1 && board.getHasChess(X2, Y2)){
			if((X1 > X2 || X1 < X2) && Y1 == Y2) return true;
			if((Y1 > Y2 || Y1 < Y2) && X1 == X2) return true;
		}else{
			if((X1+1 == X2 || X1-1 == X2) && Y1 == Y2) return true;
			if((Y1+1 == Y2 || Y1-1 == Y2) && X1 == X2) return true;
		}
		return false;
	}
	
	public void regret(){
		MainFrame mainFrame = new MainFrame();
		ChessBoard board = mainFrame.getBoard();
		String s = "";
		if(!board.getGameStatus()) return;
		Coordinate c = regret.pop();
		if(c == null)	return;
		Chess A = c.getA();
		Chess B = c.getB();
		int X1 = c.getX1();
		int Y1 = c.getY1();
		int X2 = c.getX2();
		int Y2 = c.getY2();
		if(A == null){
			s = "【悔棋】 翻開("+ X1 +","+ Y1 +")："+ board.getChess(X1, Y1).toString() +"\n";
			board.getChess(X1, Y1).setState(false);
			player.decStep();
			player.changePlayer();
		}else if(B == null){
			s = "【悔棋】 "+ A.toString() +"("+ X1 +","+ Y1 +") 移動到 ("+ X2 +","+ Y2 +")\n";
			board.setHasChess(X2, Y2, false);
			board.setChess(X1, Y1, A);
			board.setHasChess(X1, Y1, true);
			player.changePlayer();
			player.decStep();
		}else{
			s = "【悔棋】 "+ A.toString() +"("+ X1 +","+ Y1 +") 吃 ("+ X2 +","+ Y2 +"):"+B.toString() +"\n";
			board.setChess(X1, Y1, A);
			board.setHasChess(X1, Y1, true);
			board.setChess(X2, Y2, B);
			board.setHasChess(X2, Y2, true);
			B.setDie(false);
			player.changePlayer();
			player.decStep();
		}
		setChanged();
		notifyObservers(s);
		board.paint(board.getGraphics());
	}
	
	private int win(){
		MainFrame mainFrame = new MainFrame();
		ChessBoard board = mainFrame.getBoard();
		AllChess allChess = board.getAllChess();

		for(int j = 0; j < 2; j++){
			int count = 16;
			for(int i=0; i<16; i++){
				Chess chess = allChess.getChess(j, i);
				if(chess.isDie()==true){
					count--;
				}
			}
			if(count==0){
				return j;
			}
		}
		return -1;
	}
	
	public Player getPlayer(){
		return player;
	}
	public Message getMessage(){
		return message;
	}
}