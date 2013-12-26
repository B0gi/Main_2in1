import java.awt.Frame;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextPane;

class Message implements Observer{
	private Frame frame;
	private JTextPane text;
	private String s = "";
	
	public Message(){
		frame = new Frame();
		ScrollPane JSP = new ScrollPane();
		Panel panel = new Panel();
		text = new JTextPane();
	    frame = new Frame("Message");
		frame.setSize(280, 450);
		frame.setResizable(false);
		frame.setLocation(1000, 10);
		panel.add(text);
		JSP.add(panel);
		frame.add(JSP);
		frame.setVisible(true);
		frame.addWindowListener(new CloseWindow());
	}

	public void dispose(){
		frame.dispose();
	}
	public void setVisible(){
		frame.setVisible(true);
	}

	public void update(Observable arg0, Object arg1) {
		this.s = this.s + arg1;
		text.setText(this.s);
	}
}