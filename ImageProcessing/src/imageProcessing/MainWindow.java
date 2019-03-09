package imageProcessing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainWindow {

	private final int WIDTH = 600;
	private final int HEIGHT = 500;
	
	private int iconWidth;
	private int iconHeight;
	
	private JFrame mainWindow;
	private GridBagLayout mainWindowLayout;
	EventProcessing eventProcessing;
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu aboutMenu;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem saveAs;
	private JMenuItem quit;
	private JMenuItem aboutApp;
	private JMenuItem aboutAuthor;
	private JLabel imageViewer;
	
	BufferedImage image = null;
	
	public MainWindow() {
		
		mainWindow = new JFrame("Simple image processing");
		mainWindow.setSize(WIDTH, HEIGHT);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		eventProcessing = new EventProcessing(this);
		mainWindow.setVisible(true);
	}
	
	public void populateWindow() {
		
		createMenu();
		createViews();
	}
	
	private void createViews() {
		
		mainWindowLayout = new GridBagLayout();
		imageViewer = new JLabel();
		GridBagConstraints bagConstraints = new GridBagConstraints();
		
		mainWindow.setLayout(mainWindowLayout);
		
		bagConstraints.weightx = 40;
		bagConstraints.fill  = GridBagConstraints.BOTH;
		
		mainWindowLayout.setConstraints(imageViewer, bagConstraints);
		mainWindow.add(imageViewer);
	}
	
	public void openFile() {
		
		JFileChooser imageChooser = new JFileChooser();
		int returnValue = imageChooser.showOpenDialog(null);
		
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				image = ImageIO.read(imageChooser.getSelectedFile());
			} catch (IOException e) {
				
				System.out.println("Cannot open image.");
				e.printStackTrace();
			}
		}
		
		if(image != null) {
			iconWidth = image.getWidth();
			iconHeight = image.getHeight();
			
			imageViewer.setIcon(new ImageIcon(image));
			imageViewer.addMouseWheelListener(eventProcessing);
			imageViewer.setOpaque(true);
		
			mainWindow.add(imageViewer);
			mainWindow.setVisible(true);
		}
	}
	
	public void zoomImage(int notches) {
		
		iconWidth = iconWidth + (10 * notches);
		iconHeight = iconHeight + (10 * notches);
		
		imageViewer.setIcon(new ImageIcon(image.getScaledInstance(iconWidth, iconHeight, Image.SCALE_REPLICATE)));
	}
	
	public void exitProgram() {
		System.exit(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	private void createMenu() {
		
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		open = new JMenuItem("Open");
		open.addActionListener(eventProcessing);
		open.setActionCommand("open file");
		
		save = new JMenuItem("Save");
		save.addActionListener(eventProcessing);
		save.setActionCommand("save file");
		
		saveAs = new JMenuItem("Save as...");
		saveAs.addActionListener(eventProcessing);
		saveAs.setActionCommand("save file as");
		
		quit = new JMenuItem("Quit");
		quit.addActionListener(eventProcessing);
		quit.setActionCommand("exit program");
		
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(saveAs);
		fileMenu.addSeparator();
		fileMenu.add(quit);
		menuBar.add(fileMenu);
		
		aboutMenu = new JMenu("About");
		
		aboutApp = new JMenuItem("About this software");
		aboutApp.addActionListener(eventProcessing);
		aboutApp.setActionCommand("about app");
		
		aboutAuthor = new JMenuItem("About author");
		aboutAuthor.addActionListener(eventProcessing);
		aboutAuthor.setActionCommand("about author");
		
		aboutMenu.add(aboutApp);
		aboutMenu.add(aboutAuthor);
		menuBar.add(aboutMenu);
		
		mainWindow.setJMenuBar(menuBar);
		mainWindow.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		MainWindow mainWindow = new MainWindow();
		mainWindow.populateWindow();
	}
}
