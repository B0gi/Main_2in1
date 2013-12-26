import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;

class MainFrame {
	static MainFrame mainFrame;
	public MainFrame(){}
	
	public MainFrame(String game){
		if(game == "Five")
			mainFrame = new FMainFrame();
		else if(game == "Dark")
			mainFrame = new DMainFrame();
	}
	public void startGame(){
		mainFrame.startGame();
	}
	public void setLW(int l, int w){
		mainFrame.setLW(l, w);
	}
	public Frame getFrame(){
		return mainFrame.getFrame();
	}
	public ChessBoard getBoard(){
		return mainFrame.getBoard();
	}
}

//****	五子棋遊戲	****//
class FMainFrame extends MainFrame{
	private static Frame frame;
	private static ChessBoard board;
	private static int length = 690;
	private static int width = 600;
	private static FControlPanelLeft leftControl;
	private static ControlPanelRight rightControl;
	//****	遊戲畫面	****//
	public void startGame(){
		Panel panelL = new Panel();
		Panel panelM = new Panel();
	    Panel panelR = new Panel();
	    frame = new Frame("Five");	//建立新的介面frame title為Five
		frame.setSize(length, width);	//設置介面的大小
		frame.setResizable(false);	//固定介面大小
		frame.setLocation(10, 10);	//設置介面出現位置
		board = new ChessBoard("Five");	//新增棋盤
		leftControl = new FControlPanelLeft();	//新增 左邊的控制鍵
    	rightControl = new ControlPanelRight("Five");	//新增 右邊的控制鍵
		panelL.add(leftControl, BorderLayout.WEST);
		panelM.add(board, BorderLayout.CENTER);
		panelR.add(rightControl, BorderLayout.EAST);
		frame.add(panelL, BorderLayout.WEST);	//左邊的控制鍵 加到介面左邊
		frame.add(board, BorderLayout.CENTER);	//棋盤 加到介面中央
		frame.add(panelR, BorderLayout.EAST);	//右邊的控制鍵 加到介面右邊
		
		frame.setVisible(true);	//顯示farme介面
		frame.addWindowListener(new MainFrameClose());	//按右上角叉叉能關閉frame
	}
	
	//****	設置主畫面長寬	****//
	public void setLW(int l, int w){
		length = l;
		width = w;
	}
	
	public Frame getFrame(){
		return frame;
	}
	public ChessBoard getBoard(){
		return board;
	}
}

//****	暗棋遊戲	****//
class DMainFrame extends MainFrame{
	private static Frame frame;
	private static ChessBoard board;
	private static DControlPanelLeft leftControl;
	private static ControlPanelRight rightControl;
	int length = 950;
	int width = 450;
	//****	遊戲畫面	****//
	public void startGame(){
		Panel panelL = new Panel();
		Panel panelM = new Panel();
	    Panel panelR = new Panel();
	    frame = new Frame("Dark");	//建立新的介面frame title為Five
		frame.setSize(length, width);	//設置介面的大小
		frame.setResizable(false);	//固定介面大小
		frame.setLocation(10, 10);	//設置介面出現位置
		board = new ChessBoard("Dark");	//新增棋盤
		leftControl = new DControlPanelLeft();	//新增 左邊的控制鍵
		rightControl = new ControlPanelRight("Dark");	//新增 右邊的控制鍵
		panelL.add(leftControl, BorderLayout.WEST);
		panelM.add(board, BorderLayout.CENTER);
		panelR.add(rightControl, BorderLayout.EAST);
		frame.add(panelL, BorderLayout.WEST);	//左邊的控制鍵 加到介面左邊
		frame.add(board, BorderLayout.CENTER);	//棋盤 加到介面中央
		frame.add(panelR, BorderLayout.EAST);	//右邊的控制鍵 加到介面右邊
		
		frame.setVisible(true);	//顯示farme介面
		frame.addWindowListener(new MainFrameClose());	//按右上角叉叉能關閉frame
	}

	public Frame getFrame(){
		return frame;
	}
	public ChessBoard getBoard(){
		return board;
	}
	public DControlPanelLeft getControlPanelLeft(){
		return leftControl;
	}
}