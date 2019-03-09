package imageProcessing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class EventProcessing implements ActionListener, MouseWheelListener {
	
	private MainWindow window;
	
	public EventProcessing(MainWindow newWindow) {
		
		window = newWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.compareTo("open file") == 0)
			window.openFile();
		
		if(actionCommand.compareTo("save file") == 0)
			System.out.println("save file");
			
		if(actionCommand.compareTo("save file as") == 0)
			System.out.println("save file as");
				
		if(actionCommand.compareTo("exit program") == 0)
			window.exitProgram();
					
		if(actionCommand.compareTo("about app") == 0)
			System.out.println("about app");
						
		if(actionCommand.compareTo("about author") == 0)
			System.out.println("about author");
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		window.zoomImage(e.getWheelRotation());
	}

}











