package imageProcessor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProcessEvents implements ActionListener {
	
	private ImageProcessor imageProcessor;
	
	public ProcessEvents(ImageProcessor newImageProcessor) {
		
		this.imageProcessor = newImageProcessor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String message = e.getActionCommand();
		
		if(message.compareTo("exit program") == 0) imageProcessor.exitProgram();
		
		if(message.compareTo("open file") == 0) imageProcessor.openFile();
		
		if(message.compareTo("set brightness") == 0) imageProcessor.setBrightness();
	}

}
