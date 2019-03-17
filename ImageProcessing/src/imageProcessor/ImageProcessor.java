package imageProcessor;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageProcessor {
	
	private ProcessEvents eventProcessor;

	private Window mainWindow;
	
	private BufferedImage image;
	
	private JDialog optionsDialog;
	
	
	public ImageProcessor() {
		
		eventProcessor = new ProcessEvents(this);
		
		mainWindow = new MainWindow();
		mainWindow.createWindow(eventProcessor);
	}
	
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
	
	public void addOption(Menu newMenu) {
		
		mainWindow.addMenu(newMenu);
	}

	public void exitProgram() { System.exit(0); }
	
	public void openFile() {
		
		JFileChooser imageChooser = new JFileChooser();
		imageChooser.setFileFilter(new FileNameExtensionFilter("jpg, jpeg, png, gif images","png", "jpeg", "jpg", "gif"));
		
		int returnValue = imageChooser.showOpenDialog(null);
		
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				image = ImageIO.read(imageChooser.getSelectedFile());
				
			} catch (IOException e) {
				
				System.out.println("Cannot open image.");
				e.printStackTrace();
				
			} finally {
				mainWindow.showImage(image);
			}
		}
	}
	
	public void setBrightness() {
		
		if(image == null) this.openFile();
		
		else {
			optionsDialog = new JDialog((Frame) mainWindow, "Adjust brightness", false);
			optionsDialog.setSize(200, 100);
			optionsDialog.setLocation((int)java.awt.Window.CENTER_ALIGNMENT,(int) java.awt.Window.CENTER_ALIGNMENT);
			optionsDialog.setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		
		ImageProcessor imageProcessor = new ImageProcessor();
		
		imageProcessor.addOption(new ProcessMenu("Resize", "resize image"));
		imageProcessor.addOption(new ProcessMenu("Set brightness", "set brightness"));
	}
}
