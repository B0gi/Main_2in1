import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//****	¤e¤eÃö³¬µøµ¡		****//
class CloseWindow extends WindowAdapter {
	public void windowClosing(WindowEvent e){
		e.getWindow().dispose();
	}
}

class MainFrameClose extends WindowAdapter {
	public void windowClosing(WindowEvent e){
		System.exit(0);
	}
}