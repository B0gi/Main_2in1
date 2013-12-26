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

//****	���l�ѹC��	****//
class FMainFrame extends MainFrame{
	private static Frame frame;
	private static ChessBoard board;
	private static int length = 690;
	private static int width = 600;
	private static FControlPanelLeft leftControl;
	private static ControlPanelRight rightControl;
	//****	�C���e��	****//
	public void startGame(){
		Panel panelL = new Panel();
		Panel panelM = new Panel();
	    Panel panelR = new Panel();
	    frame = new Frame("Five");	//�إ߷s������frame title��Five
		frame.setSize(length, width);	//�]�m�������j�p
		frame.setResizable(false);	//�T�w�����j�p
		frame.setLocation(10, 10);	//�]�m�����X�{��m
		board = new ChessBoard("Five");	//�s�W�ѽL
		leftControl = new FControlPanelLeft();	//�s�W ���䪺������
    	rightControl = new ControlPanelRight("Five");	//�s�W �k�䪺������
		panelL.add(leftControl, BorderLayout.WEST);
		panelM.add(board, BorderLayout.CENTER);
		panelR.add(rightControl, BorderLayout.EAST);
		frame.add(panelL, BorderLayout.WEST);	//���䪺������ �[�줶������
		frame.add(board, BorderLayout.CENTER);	//�ѽL �[�줶������
		frame.add(panelR, BorderLayout.EAST);	//�k�䪺������ �[�줶���k��
		
		frame.setVisible(true);	//���farme����
		frame.addWindowListener(new MainFrameClose());	//���k�W���e�e������frame
	}
	
	//****	�]�m�D�e�����e	****//
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

//****	�t�ѹC��	****//
class DMainFrame extends MainFrame{
	private static Frame frame;
	private static ChessBoard board;
	private static DControlPanelLeft leftControl;
	private static ControlPanelRight rightControl;
	int length = 950;
	int width = 450;
	//****	�C���e��	****//
	public void startGame(){
		Panel panelL = new Panel();
		Panel panelM = new Panel();
	    Panel panelR = new Panel();
	    frame = new Frame("Dark");	//�إ߷s������frame title��Five
		frame.setSize(length, width);	//�]�m�������j�p
		frame.setResizable(false);	//�T�w�����j�p
		frame.setLocation(10, 10);	//�]�m�����X�{��m
		board = new ChessBoard("Dark");	//�s�W�ѽL
		leftControl = new DControlPanelLeft();	//�s�W ���䪺������
		rightControl = new ControlPanelRight("Dark");	//�s�W �k�䪺������
		panelL.add(leftControl, BorderLayout.WEST);
		panelM.add(board, BorderLayout.CENTER);
		panelR.add(rightControl, BorderLayout.EAST);
		frame.add(panelL, BorderLayout.WEST);	//���䪺������ �[�줶������
		frame.add(board, BorderLayout.CENTER);	//�ѽL �[�줶������
		frame.add(panelR, BorderLayout.EAST);	//�k�䪺������ �[�줶���k��
		
		frame.setVisible(true);	//���farme����
		frame.addWindowListener(new MainFrameClose());	//���k�W���e�e������frame
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