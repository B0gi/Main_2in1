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
		text.setText("�Ѥl�C��");
		text.setSize(100, 50);
	    frame = new Frame("ChessGame");	//�إ߷s������frame title��Five
		frame.setSize(length, width);	//�]�m�������j�p
		frame.setResizable(false);	//�T�w�����j�p
		frame.setLocation(10, 10);	//�]�m�����X�{��m
		frame.setLayout(new GridLayout(0, 1, 0, 15));
		p.add(text);
		frame.add(p);
		frame.add(b0);
		frame.add(b1);
		b0.addActionListener(this);
		b1.addActionListener(this);
		frame.setVisible(true);	//���farme����
		frame.addWindowListener(new MainFrameClose());	//���k�W���e�e������frame
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