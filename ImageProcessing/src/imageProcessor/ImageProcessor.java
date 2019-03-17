package imageProcessor;

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
			optionsDialog = new JDialog();
		}
	}
	
	public static void main(String[] args) {
		
		ImageProcessor imageProcessor = new ImageProcessor();
		
		imageProcessor.addOption(new ProcessMenu("Resize", "resize image"));
		imageProcessor.addOption(new ProcessMenu("Set brightness", "set brightness"));
	}
	
}
