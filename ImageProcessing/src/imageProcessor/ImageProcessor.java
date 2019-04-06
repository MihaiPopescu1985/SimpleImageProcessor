package imageProcessor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ImageProcessor {
	
	private ProcessEvents eventProcessor;

	private Window mainWindow;
	
	private BufferedImage image;
	
	private JDialog optionsDialog;
	
	private File imageFile;
	
	
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
			
			if(message.compareTo("open file") == 0) imageProcessor.openFile();
			
			else if(message.compareTo("save file") == 0) {
				
				if(isOkSelected("This will overwrite original image") == JOptionPane.OK_OPTION) {
					
					try {
						ImageIO.write(image, "jpg", imageFile);
						
					} catch (IOException e1) {
						imageProcessor.showMessage("Error saving image.");
						
					} catch (IllegalArgumentException ie) {
						imageProcessor.showMessage("No image.");
					}
				}
			}
			
			else if(message.compareTo("save file as") == 0) imageProcessor.showMessage("Not yet implemented");
			
			else if(message.compareTo("exit program") == 0) imageProcessor.exitProgram();
			
			else if(message.compareTo("about app") == 0) imageProcessor.showMessage("Simple image processing");
			
			else if(message.compareTo("about author") == 0) imageProcessor.showMessage("Mihai Popescu, justmaryo@gmail.com");
			
			else if(message.compareTo("resize image") == 0) imageProcessor.showMessage("Not yet implemented");
			
			else if(message.compareTo("set brightness") == 0) imageProcessor.setBrightness();
			
			else if(message.compareTo("increment brightness") == 0) imageProcessor.incrementBrightness();
			
			else if(message.compareTo("decrement brightness") == 0) imageProcessor.decrementBrightness();
			
			else if(message.compareTo("mirror image") == 0) imageProcessor.mirrorImage();
		}
	}
	
	public void showMessage(String message) {
		
		JOptionPane.showMessageDialog((Component) mainWindow, message);
	}
	
	public int isOkSelected(String message) {
		
		return JOptionPane.showConfirmDialog(
				(Component) mainWindow, 
				message, 
				"Attention !",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE);
	}

	public void mirrorImage() {
		
		if(image == null)
			openFile();
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		BufferedImage imageMirrored = new BufferedImage(width+1, height, image.getType());
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				
				int mirrorWidth = width - i;
				imageMirrored.setRGB(mirrorWidth, j, image.getRGB(i, j));
			}
		}
		image = imageMirrored;
		mainWindow.showImage(image);
	}

	public void decrementBrightness() {
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				image.setRGB(i, j, new Color(image.getRGB(i, j)).darker().getRGB());
			}
		}
		mainWindow.showImage(image);
	}

	public void incrementBrightness() {
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				image.setRGB(i, j, new Color(image.getRGB(i, j)).brighter().getRGB());
			}
		}
		mainWindow.showImage(image);
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
				imageFile = imageChooser.getSelectedFile();
				image = ImageIO.read(imageFile);
				
			} catch (IOException e) {
				
				this.showMessage("Cannot open image.");
				e.printStackTrace();
				
			} finally {
				mainWindow.showImage(image);
			}
		}
	}
	
	public void setBrightness() {
		
		if(image == null) {
			this.openFile();
			setBrightness();
		}
		
		else {
			createDialog((Frame) mainWindow, "Adjust brightness", true, 400, 100, new GridLayout());
			
			optionsDialog.add(createButton("Increment brightness", "increment brightness"));
			optionsDialog.add(createButton("Decrement brightness", "decrement brightness"));
			
			optionsDialog.setVisible(true);
		}
	}
	
	private void createDialog(Frame frame, String title, boolean modal, int width, int height, LayoutManager layout) {
		
		optionsDialog = new JDialog(frame, title, modal);
		optionsDialog.setSize(width, height);
		optionsDialog.setLocationRelativeTo(null);
		optionsDialog.setLayout(layout);
	}
	
	private JButton createButton(String title, String command) {
		JButton button = new JButton(title);
		button.setActionCommand(command);
		button.addActionListener(eventProcessor);
		
		return button;
	}
	
	public static void main(String[] args) {
		
		ImageProcessor imageProcessor = new ImageProcessor();
		
		imageProcessor.addOption(new ProcessMenu("Resize", "resize image"));
		imageProcessor.addOption(new ProcessMenu("Set brightness", "set brightness"));
		imageProcessor.addOption(new ProcessMenu("Mirror", "mirror image"));
	}
}
