package imageProcessing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventProcessing implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.compareTo("") == 0)
			System.out.println(" ");
	}

}
