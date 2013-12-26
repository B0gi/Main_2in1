import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;

public class ChessGame{
	public static void main(String[] args) {
		new Start();
	}
}

class Start implements ActionListener{
	private static Frame frame;
	private static int length = 200;
	private static int width = 200;
	private Button b0 = new Button("Five Chess");
	private Button b1 = new Button("Dark Chess"); 
    
	public Start(){
		Panel p = new Panel();
		JTextPane text = new JTextPane();
		text.setText("棋子遊戲");
		text.setSize(100, 50);
	    frame = new Frame("ChessGame");	//建立新的介面frame title為Five
		frame.setSize(length, width);	//設置介面的大小
		frame.setResizable(false);	//固定介面大小
		frame.setLocation(10, 10);	//設置介面出現位置
		frame.setLayout(new GridLayout(0, 1, 0, 15));
		p.add(text);
		frame.add(p);
		frame.add(b0);
		frame.add(b1);
		b0.addActionListener(this);
		b1.addActionListener(this);
		frame.setVisible(true);	//顯示farme介面
		frame.addWindowListener(new MainFrameClose());	//按右上角叉叉能關閉frame
	}
	
	public void actionPerformed(ActionEvent e){
		Button button = (Button) e.getSource();
		
		if(button == b0){
			new FiveChess();
		}else if(button == b1){
			new DarkChess();
		}
		frame.dispose();
	}
}