import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

class ChessBoard extends JComponent{
	ChessBoard board;
	public ChessBoard(){}
	
	public ChessBoard(String game){
		if(game == "Five")
			board = new FChessBoard();
		else if(game == "Dark")
			board = new DChessBoard();
		addMouseListener(new MouseClicked());
	}
	public void setBoard(int rows, int cols){
		board.setBoard(rows, cols);
	}
	public void setPlayer(){
		board.setPlayer();
	}
	public void setChessRegret(int x, int y, int count){
		board.setChessRegret(x, y, count);
	}
	public void setGameStatus(boolean theGameStatus){
		board.setGameStatus(theGameStatus);
	}
	public void setHasChess(int i, int j, boolean change){
		board.setHasChess(i, j, change);
	}
	public void setChess(int x, int y, int player) {
		board.setChess(x, y, player);
	}
	public void setChess(int i, int j, Chess theChess){
		board.setChess(i, j, theChess);
	}
	public int getMargin(){
		return board.getMargin();
	}
	public int getGridSpan(){
		return board.getGridSpan();
	}
	public int getRows(){
		return board.getRows();
	}
	public int getCols(){
		return board.getCols();
	}
	public int getChessRegret(int x, int y){
		return board.getChessRegret(x, y);
	}
	public int getFChess(int x, int y){
		return board.getFChess(x, y);
	}
	public Chess getChess(int x, int y){
		return board.getChess(x, y);
	}
	public boolean hasChess(int x, int y){
		return board.hasChess(x, y);
	}
	public Boolean getHasChess(int x, int y){
		return board.getHasChess(x, y);
	}
	
	public ChessAction getChessAction() {
		return board.getChessAction();
	}
	public int getPlayer(){
		return board.getPlayer();
	}
	public AllChess getAllChess(){
		return board.getAllChess();
	}
	public boolean getGameStatus(){
		return board.getGameStatus();
	}
	public void selectChess(int i, int j, Graphics g){
		board.selectChess(i, j, g);
	}
	public void paint(Graphics g){
		board.paint(g);
	}
}

//****	棋盤	****//
class FChessBoard extends ChessBoard {
	private final int MARGIN=30;
	private final int GRID_SPAN=35;
	private final int CIRCLE=20;
	private static int ROWS=14;
	private static int COLS=14;
	private int[][] chess;
	private static int[][] chessRegret;
	private int player = 1;
	private static boolean gameStatus;
	private ChessAction chessAction = new ChessAction("Five");
	
	public FChessBoard(){
		reset();
	}
	//****	set	****//
	public void setPlayer(){
		if(player == 1){				
			player = 2;
		}else if(player == 2){
			player = 1;
		}
	}
	public void setChess(int x, int y, int player) {
		chess[x][y] = player;
	}
	public void setBoard(int rows, int cols){
		ROWS = rows;
		COLS = cols;
	}
	public void setGameStatus(boolean theGameStatus){
		gameStatus = theGameStatus;
	}
	public void setChessRegret(int x, int y, int count){
		chessRegret[x][y] = count;
	}
	
	//****	get	****//
	public int getChessRegret(int x, int y){
		return chessRegret[x][y];
	}
	public int getMargin(){
		return MARGIN;
	}
	public int getGridSpan(){
		return GRID_SPAN;
	}
	public int getRows(){
		return ROWS;
	}
	public int getCols(){
		return COLS;
	}
	public int getFChess(int x, int y){
		return chess[x][y];
	}
	public int getPlayer(){
		return player;
	}

	public boolean getGameStatus(){
		return gameStatus;
	}
	public ChessAction getChessAction() {
		return chessAction;
	}
		
	//****	設置棋子	****//
	private void reset(){
		gameStatus = true;
		chess = new int[ROWS+1][COLS+1];
		chessRegret = new int[ROWS+1][COLS+1];
		for(int i = 0; i<=ROWS; i++){
			for(int j = 0; j<=COLS; j++){
				chess[i][j] = 0;
				chessRegret[i][j] = -1;
			}
		}
	}
	
	//****	畫出棋盤	****//
	public void paint(Graphics g){
		regretChess(g);
		drawTable(g);
		putChess(g);
		showWin(g);
	}
	
	//****	悔棋時白色要覆蓋	****//
	public void regretChess(Graphics g){
		for(int i = 0; i<=ROWS; i++){
			for(int j = 0; j<=COLS; j++){
				if(chess[i][j] == 0){
					g.setColor(Color.WHITE);
					g.fillOval(i*GRID_SPAN+CIRCLE,j*GRID_SPAN+CIRCLE, CIRCLE, CIRCLE);
				}
			}
		}
	}
	
	//****	畫出格子	****//
	public void drawTable(Graphics g){
		g.setColor(Color.BLACK);
		for(int i=0;i<=ROWS;i++){  
	        g.drawLine(MARGIN, MARGIN+i*GRID_SPAN, MARGIN+COLS*GRID_SPAN, MARGIN+i*GRID_SPAN);  
	    }  
	    for(int i=0;i<=COLS;i++){  
	        g.drawLine(MARGIN+i*GRID_SPAN, MARGIN, MARGIN+i*GRID_SPAN, MARGIN+ROWS*GRID_SPAN);  
	    }
	}
	
	//****	棋子顏色與放置	****//
	public void putChess(Graphics g){
	    for(int i = 0; i<=ROWS; i++){
			for(int j = 0; j<=COLS; j++){
				if(chess[i][j] == 1){
					g.setColor(Color.BLACK);
					g.fillOval(i*GRID_SPAN+CIRCLE,j*GRID_SPAN+CIRCLE, CIRCLE, CIRCLE);
				}else if(chess[i][j] == 2){
					g.setColor(Color.ORANGE);
					g.fillOval(i*GRID_SPAN+CIRCLE,j*GRID_SPAN+CIRCLE, CIRCLE, CIRCLE);
				}
			}
		}
	}
	
	//****	顯示贏	****//
	public void showWin(Graphics g){
		int winStatus = chessAction.getWinStatus();
		int XX = chessAction.getXX(), YY = chessAction.getYY();
	    if(gameStatus == false){
			g.setColor(Color.RED);
			if(winStatus == 1){
				g.drawLine(MARGIN+XX*GRID_SPAN, MARGIN+YY*GRID_SPAN, MARGIN+(XX+4)*GRID_SPAN, MARGIN+YY*GRID_SPAN);
			}else if(winStatus == 2){
				g.drawLine(MARGIN+XX*GRID_SPAN, MARGIN+YY*GRID_SPAN, MARGIN+XX*GRID_SPAN, MARGIN+(YY+4)*GRID_SPAN);
			}else if(winStatus == 3){
				g.drawLine(MARGIN+XX*GRID_SPAN, MARGIN+YY*GRID_SPAN, MARGIN+(XX+4)*GRID_SPAN, MARGIN+(YY+4)*GRID_SPAN);
			}else if(winStatus == 4){
				g.drawLine(MARGIN+XX*GRID_SPAN, MARGIN+YY*GRID_SPAN, MARGIN+(XX+4)*GRID_SPAN, MARGIN+(YY-4)*GRID_SPAN);
			}
		}
	}	
}

class DChessBoard extends ChessBoard{
	private final int MARGIN=20;
	private final int GRID_SPAN=90;
	private final int ROWS=4;
	private final int COLS=8;
	private boolean[][] hasChess = new boolean[COLS][ROWS];
	private Chess[][] chess = new Chess[COLS][ROWS];
	private boolean gameStatus;
	private AllChess allChess = new AllChess();
	private ChessAction chessAction = new ChessAction("Dark");
	
	public DChessBoard(){
		reset();
	}
	
	//****	set	****//
	private void reset(){
		setGameStatus(true);
		for(int i = 0; i < COLS; i++)
			for (int j = 0; j < ROWS; j++)
				hasChess[i][j] = true;
		
		for(int i = 0; i < COLS; i++){
			for (int j = 0; j < ROWS;){
				int X = (int) (Math.random()*2);
				int Y = (int) (Math.random()*16);
				Chess tmp = allChess.getChess(X, Y);
				if(tmp.isDie()){
					tmp.setDie(false);
					chess[i][j] = tmp;
					j++;
				}
			}
		}
		
		for (int j = 0; j < ROWS; j++){
			for(int i = 0; i < COLS; i++){
				System.out.printf("%3s",chess[i][j].toString());
			}
			System.out.println();
		}
	}
	public void setChess(int i, int j, Chess theChess){
		this.chess[i][j] = theChess;
	}
	public void setHasChess(int i, int j, boolean change){
		hasChess[i][j] = change;
	}
	//****	get	****//
	public int getMargin(){
		return MARGIN;
	}
	public int getGridSpan(){
		return GRID_SPAN;
	}
	public int getRows(){
		return ROWS;
	}
	public int getCols(){
		return COLS;
	}
	public Chess getChess(int X, int Y){
		return chess[X][Y];
	}
	public Boolean getHasChess(int X, int Y){
		return hasChess[X][Y];
	}
	public ChessAction getChessAction(){
		return chessAction;
	}
	public AllChess getAllChess(){
		return allChess;
	}
	public void setGameStatus(boolean theGameStatus){
		this.gameStatus = theGameStatus;
	}
	public boolean getGameStatus(){
		return gameStatus;
	}
	public boolean hasChess(int X, int Y){
		if(hasChess[X][Y] == true)
			return true;
		else
			return false;
	}
	
	//****	畫出棋盤	****//
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		for(int i=0;i<=ROWS;i++){  
	        g.drawLine(MARGIN, MARGIN+i*GRID_SPAN, MARGIN+COLS*GRID_SPAN, MARGIN+i*GRID_SPAN);  
	    }  
	    for(int i=0;i<=COLS;i++){  
	        g.drawLine(MARGIN+i*GRID_SPAN, MARGIN, MARGIN+i*GRID_SPAN, MARGIN+ROWS*GRID_SPAN);  
	    }
	    putChess(g);
	}
	
	private void putChess(Graphics g){
	    for(int i = 0; i < COLS; i++){
	    	for(int j = 0; j < ROWS; j++){
	    		if(!hasChess[i][j]){
	    			g.drawImage(new ImageIcon("chess/09.jpg").getImage(),23+GRID_SPAN*i,23+GRID_SPAN*j,this);
	    		}else if(!chess[i][j].getState()){
	    			g.drawImage(new ImageIcon("chess/08.jpg").getImage(),30+GRID_SPAN*i,30+GRID_SPAN*j,this);
	    		}else{
	    			int chessColor = chess[i][j].getColor();
	    			int chessID = chess[i][j].getIdentify();
	    			g.drawImage(new ImageIcon("chess/09.jpg").getImage(),23+GRID_SPAN*i,23+GRID_SPAN*j,this);
	    			switch(chessColor){
		    			case 0:
		    				g.drawImage(new ImageIcon("chess/"+ chessColor +""+ chessID +".jpg").getImage(),30+GRID_SPAN*i,30+GRID_SPAN*j,this);
		    				break;
		    			case 1:
		    				g.drawImage(new ImageIcon("chess/"+ chessColor +""+ chessID +".jpg").getImage(),30+GRID_SPAN*i,30+GRID_SPAN*j,this);
		    				break;
		    		}
	    		}
	    	}
		}
	}
	
	public void selectChess(int i, int j, Graphics g){
		int chessColor = chess[i][j].getColor();
		int chessID = chess[i][j].getIdentify();
		g.drawImage(new ImageIcon("chess/09.jpg").getImage(),23+GRID_SPAN*i,23+GRID_SPAN*j,this);
		g.drawImage(new ImageIcon("chess/"+ chessColor +""+ chessID +".jpg").getImage(),30+GRID_SPAN*i,23+GRID_SPAN*j,this);
	}
}