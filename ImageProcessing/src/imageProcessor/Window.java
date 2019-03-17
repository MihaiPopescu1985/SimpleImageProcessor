package imageProcessor;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JDialog;

public interface Window {

	public void createWindow(ActionListener newActionListener);
	public void setEventProcessor(ActionListener event);
	public void showImage(BufferedImage image);
	public void addMenu(Menu newMenu);
	public void showOptionDialog(JDialog newOptionDialog);
}
