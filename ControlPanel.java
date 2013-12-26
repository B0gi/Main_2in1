import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.Timer;

//****	��������	****//
class FControlPanelLeft extends Panel implements ActionListener {  
    int tm_unit=200;
    int tm_sum =0;
    int sec=0;
    private JLabel color = new JLabel("    �¤l");
    private JLabel steps = new JLabel("  �U�l�� = ");
    private JLabel time = new JLabel("  �ɶ� = ");
    private Timer timer = new Timer(tm_unit, this);
    MainFrame mainFrame = new MainFrame();
	ChessBoard board = mainFrame.getBoard();
	ChessAction chessAction = board.getChessAction();
	
    public FControlPanelLeft(){   
    	timer.restart();
        setLayout(new GridLayout(14,1,10,20));
        add(new Label("                      ")); 
        add(color);  
        add(new Label());
        add(new Label());
        add(new Label());
        add(new Label());
        add(new Label());
        add(new Label());
        add(steps);
        add(time);
    }
    public void paint(Graphics g){
		super.paint(g);
		if(board.getPlayer() == 1)
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.ORANGE);
		g.fillOval(55, 45, 15, 15);
    }
    private void player_event(){
    	if(board.getPlayer() == 1)
    		color.setText("    �¤l�U");
    	else
    		color.setText("    ���l�U");
    }
    private void steps_event(){
    	if(board.getPlayer() == 1)
    		steps.setText("  �¤l�� = "+ chessAction.getCount()/2);
    	else
    		steps.setText("  ���l�� = "+ chessAction.getCount()/2);
    }
    private void timer_event(){
		if ((tm_sum += tm_unit) >= 1000 && board.getGameStatus() == true){
			tm_sum -= 1000;
			sec+=1;
			time.setText("  �ɶ�= " + sec +"s");
		}
    }
	public void actionPerformed(ActionEvent e){
			player_event();
			timer_event();
			steps_event();
	}
}

class DControlPanelLeft extends Panel implements ActionListener {
	private int tm_unit=200;
	private int tm_sum =0;
	private int sec=0;
    private JLabel color = new JLabel("");
    private JLabel steps = new JLabel("  �B��");
    private JLabel time = new JLabel("  �ɶ� = ");
    private Timer timer = new Timer(tm_unit, this);
    private MainFrame mainFrame = new MainFrame();
    private ChessBoard board = mainFrame.getBoard();
    private ChessAction chessAction = board.getChessAction();
    private Player player = chessAction.getPlayer();
    public DControlPanelLeft(){
    	timer.restart();
        setLayout(new GridLayout(20,1,10,30));
        add(new Label("                            ")); 
        add(color);
        add(new Label());
        add(new Label());
        add(new Label());
        add(steps);
        add(time);
    }
    public void paint(Graphics g){
    	super.paint(g);
    }
    private void player_event(){
    	Graphics g = getGraphics();
    	if(!player.getSetColor()){
    		g.drawImage(new ImageIcon("chess/09.jpg").getImage(),1,22,this);
    	}else if(player.getColor() == 0){
    		g.drawImage(new ImageIcon("chess/00.jpg").getImage(),1,22,this);
    	}else if(player.getColor() == 1){
    		g.drawImage(new ImageIcon("chess/10.jpg").getImage(),1,22,this);
    	}
    }
    private void steps_event(){
    	if(!player.getSetColor()){
    		steps.setText("  �B��");
    	}else if(player.getColor() == 0){
    		steps.setText("  �¤�B�� = "+ (player.getStep()+1)/2);
    	}else if(player.getColor() == 1){
    		steps.setText("  ����B�� = "+ (player.getStep()+1)/2);
    	}
    }
    private void timer_event(){
		if ((tm_sum += tm_unit) >= 1000 && board.getGameStatus() == true){
			tm_sum -= 1000;
			sec+=1;
			time.setText("  �ɶ�= " + sec +"s");
		}
    }
	public void actionPerformed(ActionEvent e){
			if(board.getGameStatus()){ 
				player_event();
				timer_event();
				steps_event();
			}
	}
}
//****	�k������	****//
class ControlPanelRight extends Panel implements ActionListener {
	Button b0 = new Button("�]  �m");
    Button b1 = new Button("��  ��"); 
    Button b2 = new Button("��  �s");   
    Button b3 = new Button("��  �U");   
    Button b4 = new Button("��  �}"); 
    Button b5 = new Button("�D�e��"); 
    String game;
    public ControlPanelRight(String game){
    	this.game = game;
    	setLayout(new GridLayout(0, 1, 0, 5));
        add(b5);
        add(new Label());
    	add(b0);
        add(new Label());   
        add(new Label());   
        add(new Label());  
        add(b1);
        add(new Label());   
        add(new Label());   
        add(new Label());   
        add(b2);   
        add(b3);   
        add(b4);   
        b0.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
    }
    
	public void actionPerformed(ActionEvent e){
		Button button = (Button) e.getSource();
		MainFrame mainFrame = new MainFrame();
		ChessBoard board = mainFrame.getBoard();
		ChessAction chessAction = board.getChessAction();
		if(button == b0){
			new Set(game);
		}else if(button == b1){
			if(board.getGameStatus() == true)
				chessAction.regret();
		}else if(button == b2){
			new Renew();
		}else if(button == b3){
			new Help(game);
		}else if(button == b4){
			new Exit();
		}else if(button == b5){
			new Main();
		}
	}
}

//****	�]�m�e��	****//
class Set implements ActionListener {
	Panel p0 = new Panel();
	Panel p1 = new Panel();
	Panel p2 = new Panel();
	Panel p3 = new Panel();
	Frame frameSet = new Frame("�]  �m");
	Button b0 = new Button("15 x 15");
	Button b1 = new Button("17 x 17");
	Button b2 = new Button("19 x 19");
	JTextArea text = new JTextArea("�ѽL�j�p");
	
	public Set(String game){
		frameSet.setSize(200, 100);
		frameSet.setResizable(false);
		frameSet.setLocation(300, 300);
		if(game == "Five"){
			p0.add(b0);
			p1.add(b1);
			p2.add(b2);
			p3.add(text, BorderLayout.CENTER);
			b0.addActionListener(this);
			b1.addActionListener(this);
			b2.addActionListener(this);
			frameSet.add(p3, BorderLayout.NORTH);
			frameSet.add(p0, BorderLayout.WEST);
			frameSet.add(p1);
			frameSet.add(p2, BorderLayout.EAST);
		}else if(game == "Dark"){}
		frameSet.setVisible(true);
		frameSet.addWindowListener(new CloseWindow());
	}
	
	public void actionPerformed(ActionEvent e){
		Button button = (Button) e.getSource();
		MainFrame mainFrame = new MainFrame();
		ChessBoard board = mainFrame.getBoard();
		Frame frame = mainFrame.getFrame();
		
		if(button == b0){
			mainFrame.setLW(690, 600);
			board.setBoard(14, 14);
		}else if(button == b1){
			mainFrame.setLW(760, 650);
			board.setBoard(16, 16);
		}else if(button == b2){
			mainFrame.setLW(830, 750);
			board.setBoard(18, 18);
		}
		frame.dispose();
		frameSet.dispose();
		mainFrame.startGame();
	}
}

//****	���s�}�l	****//
class Renew implements ActionListener {
	Panel p0 = new Panel();
	Panel p1 = new Panel();
	Panel p3 = new Panel();
	Frame frameRenew = new Frame("��  �s");
	Button b0 = new Button("��  ��");
	Button b1 = new Button("�T  �w");
	JTextArea text = new JTextArea("�T�w�n���s�}�l?");
	
	public Renew(){
		frameRenew.setSize(200, 100);
		frameRenew.setResizable(false);
		frameRenew.setLocation(300, 300);
		p0.add(b0);
		p1.add(b1);
		p3.add(text, BorderLayout.CENTER);
		b0.addActionListener(this);
		b1.addActionListener(this);
		frameRenew.add(p3, BorderLayout.NORTH);
		frameRenew.add(p0, BorderLayout.EAST);
		frameRenew.add(p1, BorderLayout.WEST);
		frameRenew.setVisible(true);
		frameRenew.addWindowListener(new CloseWindow());
	}
	
	public void actionPerformed(ActionEvent e){
		Button button = (Button) e.getSource();
		MainFrame mainFrame = new MainFrame();
		Frame frame = mainFrame.getFrame();
		
		if(button == b0){
			frameRenew.dispose();
		}else if(button == b1){
			mainFrame.getBoard().setGameStatus(false);
			frameRenew.dispose();
			frame.dispose();
			mainFrame.getBoard().getChessAction().getMessage().dispose();
			mainFrame.startGame();
		}
	}
}

//****	���U�e��	****//
class Help implements ActionListener {
	Frame frameHelp = new Frame("��  �U");
	Button b0 = new Button("�T�w");
	JTextArea text = new JTextArea();
	
	public Help(String game){
		if(game == "Five"){
			text.setText(" ��H�﫳�A���U��̫��¤l�A��U��̫����l�C\n" +
					" �H���y�覡�N�Ѥl�m��ѽL�I�W�A���N���ӴѤl�s���@�u���ӡC\n" +
					" ��ѽL�I���U���Ѥl�B�LĹ�a�A�h���M�ѡC");
		}else if(game == "Dark"){
			text.setText(" �Ѥl�ƶq�`�@32��A���B�¤�U16��F�C���ѽL�榡�� 4 x 8 �A\n" +
					" �@�}�l�Ѥl�һ\�ۡC�Ѥl���j�p�̧Ǭ��G\n" +
					" �� > �K > �� > �� > �X > �� > �L�F�N > �h > �H > �� > �� > �] > ��C\n" +
					" ���ѮɡA�Ҧ��Ѥl�ҥu�ઽ��(�ξ)�@�B��Ů�(�Υi�Y���Ѥl��m)�A\n" +
					" �u���b��\\�]�Y�ѮɡA�i�H���V�����@��(�w½�}�λ\�Ѭҥi)�A\n" +
					" ����\\�]�b���Y�Ѥl�����p�U�A��u�ઽ��(�ξ)�@�B�C");
		}
		frameHelp.setSize(400, 160);
		frameHelp.setResizable(false);
		frameHelp.setLocation(300, 300);
		b0.addActionListener(this);
		frameHelp.add(text, BorderLayout.NORTH);
		frameHelp.add(b0, BorderLayout.SOUTH);
		frameHelp.setVisible(true);
		frameHelp.addWindowListener(new CloseWindow());
	}
	
	public void actionPerformed(ActionEvent e){
		Button button = (Button) e.getSource();
		
		if(button == b0){
			frameHelp.dispose();
		}
	}
}

//**** ���}�e��	****//
class Exit implements ActionListener {
	Panel p0 = new Panel();
	Panel p1 = new Panel();
	Panel p3 = new Panel();
	Frame frameExit = new Frame("��  �}");
	Button b0 = new Button("��  ��");
	Button b1 = new Button("�T  �w");
	JTextArea text = new JTextArea("�T�w�n���}?");
	
	public Exit(){
		frameExit.setSize(200, 100);
		frameExit.setResizable(false);
		frameExit.setLocation(300, 300);
		p0.add(b0);
		p1.add(b1);
		p3.add(text, BorderLayout.CENTER);
		b0.addActionListener(this);
		b1.addActionListener(this);
		frameExit.add(p3, BorderLayout.NORTH);
		frameExit.add(p0, BorderLayout.EAST);
		frameExit.add(p1, BorderLayout.WEST);
		frameExit.setVisible(true);
		frameExit.addWindowListener(new CloseWindow());
	}
	
	public void actionPerformed(ActionEvent e){
		Button button = (Button) e.getSource();
		
		if(button == b0){
			frameExit.dispose();
		}else if(button == b1){
			System.exit(0);
		}
	}
}

class Main implements ActionListener {
	Panel p0 = new Panel();
	Panel p1 = new Panel();
	Panel p3 = new Panel();
	Frame frameRenew = new Frame("��  �s");
	Button b0 = new Button("��  ��");
	Button b1 = new Button("�T  �w");
	JTextArea text = new JTextArea("�T�w�n�^�D�e��?");
	
	public Main(){
		frameRenew.setSize(200, 100);
		frameRenew.setResizable(false);
		frameRenew.setLocation(300, 300);
		p0.add(b0);
		p1.add(b1);
		p3.add(text, BorderLayout.CENTER);
		b0.addActionListener(this);
		b1.addActionListener(this);
		frameRenew.add(p3, BorderLayout.NORTH);
		frameRenew.add(p0, BorderLayout.EAST);
		frameRenew.add(p1, BorderLayout.WEST);
		frameRenew.setVisible(true);
		frameRenew.addWindowListener(new CloseWindow());
	}
	
	public void actionPerformed(ActionEvent e){
		Button button = (Button) e.getSource();
		MainFrame mainFrame = new MainFrame();
		Frame frame = mainFrame.getFrame();
		
		if(button == b0){
			frameRenew.dispose();
		}else if(button == b1){
			frameRenew.dispose();
			frame.dispose();
			mainFrame.getBoard().getChessAction().getMessage().dispose();
			new Start();
		}
	}
}

//**** ��Ĺ�ܭ� ****//
class FWin implements ActionListener {
	private Frame win = new Frame("Win");
	private Button enter = new Button("�T�w");
	private JTextArea textWin = new JTextArea("");
	public FWin(int status){
		MainFrame mainFrame = new MainFrame();
		ChessBoard board = mainFrame.getBoard();
		Panel p = new Panel();
		board.setGameStatus(false);
		win.setSize(200, 100);
		win.setResizable(false);
		win.setLocation(300, 300);
		if(status == 0)
			textWin = new JTextArea("!! �M      �� !!");
		else if(status == 1)
			textWin = new JTextArea("�� �l �� !!");
		else
			textWin = new JTextArea("�� �l �� !!");
		
		p.add(textWin, BorderLayout.CENTER);
		enter.addActionListener(this);
		win.add(p, BorderLayout.NORTH);
		win.add(enter, BorderLayout.SOUTH);
		win.setVisible(true);
		win.addWindowListener(new CloseWindow());
	}

	public void actionPerformed(ActionEvent e){
		Button button = (Button) e.getSource();
		
		if(button == enter){
			win.dispose();
		}
	}
}

class DWin implements ActionListener {
	private MainFrame mainFrame = new MainFrame();
	private ChessBoard board = mainFrame.getBoard();
	private Frame win = new Frame("Win");
	private Button enter = new Button("�T�w");
	private JTextArea textWin = new JTextArea("");
	
	public DWin(int color){
		Panel p = new Panel();
		board.setGameStatus(false);
		win.setSize(200, 100);
		win.setResizable(false);
		win.setLocation(300, 300);
		if(color == 1)
			textWin = new JTextArea("�� �� �� !!");
		else if(color == 0)
			textWin = new JTextArea("�� �� �� !!");
		p.add(textWin, BorderLayout.CENTER);
		enter.addActionListener(this);
		win.add(p, BorderLayout.NORTH);
		win.add(enter, BorderLayout.SOUTH);
		win.setVisible(true);
		win.addWindowListener(new CloseWindow());
	}

	public void actionPerformed(ActionEvent e){
		Button button = (Button) e.getSource();
		
		if(button == enter){
			win.dispose();
		}
	}
}

//**** ���X�l�e�� ****//
class HasChess implements ActionListener {
	private Frame hasChess = new Frame("");
	private static JTextArea textHasChess = new JTextArea();
	private Button enter = new Button("�T�w");
	
	public HasChess(String s){
		hasChess.setSize(200, 100);
		hasChess.setResizable(false);
		hasChess.setLocation(300, 300);
		enter.addActionListener(this);
		textHasChess.setText("�iĵ�i�j����o��"+ s +"!!");
		hasChess.add(textHasChess, BorderLayout.NORTH);
		hasChess.add(enter, BorderLayout.SOUTH);
		hasChess.setVisible(true);
		hasChess.addWindowListener(new CloseWindow());
	}
	
	public void actionPerformed(ActionEvent e){
		Button button = (Button) e.getSource();
		
		if(button == enter){
			hasChess.dispose();
		}
	}
}