package imageProcessing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainWindow {

	private final int WINDOW_WIDTH = 600;
	private final int WINDOW_HEIGHT = 500;
	private final int PREFERRED_WIDTH = 200;
	private final int PREFERRED_HEIGHT = 100;
	
	private int iconWidth;
	private int iconHeight;
	
	private JFrame mainWindow;
	EventProcessing eventProcessing;
	


	private JLabel imageViewer;
	private JPanel optionsPanel;
	
	BufferedImage image = null;
	
	public MainWindow() {
		
		mainWindow = new JFrame("Simple image processing");
		mainWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		eventProcessing = new EventProcessing(this);
		mainWindow.setVisible(true);
	}
	
	public void populateWindow() {
		
		createMenu();
		createViews();
	}
	
	private void createViews() {
		
		imageViewer = new JLabel();
		optionsPanel = new JPanel(new BorderLayout());
		
		optionsPanel.setOpaque(true);
		optionsPanel.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
		optionsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		imageViewer.setOpaque(true);
		imageViewer.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
		imageViewer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		imageViewer.setBackground(Color.WHITE);
		
		mainWindow.add(optionsPanel, BorderLayout.LINE_START);
		mainWindow.add(imageViewer, BorderLayout.CENTER);
		
		mainWindow.setVisible(true);
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
			double ratio = 0;
			
			iconWidth = image.getWidth();
			iconHeight = image.getHeight();
			
			int viewerWidth = imageViewer.getWidth();
			int viewerHeight = imageViewer.getHeight();
			
			double widthRatio = (double)iconWidth / (double)viewerWidth;
			double heightRatio = (double)iconHeight / (double)viewerHeight;
			
			if(widthRatio > 1 && heightRatio > 1) {
				if(widthRatio > heightRatio) {
					ratio = (double)iconHeight / (double)iconWidth;
					
					iconWidth = viewerWidth;
					iconHeight = (int) (iconWidth * ratio);
				} else {
					ratio = (double)iconWidth / (double)iconHeight;
					
					iconHeight = viewerHeight;
					iconWidth = (int) (iconHeight * ratio);
				}
			}
			
			Image iconImage = image.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
			
			imageViewer.setIcon(new ImageIcon(iconImage));
			imageViewer.setHorizontalAlignment(SwingConstants.CENTER);
			imageViewer.setVerticalAlignment(SwingConstants.CENTER);
			imageViewer.addMouseWheelListener(eventProcessing);
		}
	}
	
	public void zoomImage(int notches) {
		// To be implemented
	}
	
	
	public void exitProgram() {
		System.exit(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	
	private void createMenu() {
		
		JMenuBar menuBar = new JMenuBar();;
		JMenu fileMenu = new JMenu("File");
		JMenu aboutMenu = new JMenu("About");
		
		JMenuItem open = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem saveAs = new JMenuItem("Save as...");
		JMenuItem quit = new JMenuItem("Quit");
		JMenuItem aboutApp = new JMenuItem("About this software");
		JMenuItem aboutAuthor = new JMenuItem("About author");
		
		open.addActionListener(eventProcessing);
		open.setActionCommand("open file");
		
		save.addActionListener(eventProcessing);
		save.setActionCommand("save file");
		
		saveAs.addActionListener(eventProcessing);
		saveAs.setActionCommand("save file as");
		
		quit.addActionListener(eventProcessing);
		quit.setActionCommand("exit program");
		
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(saveAs);
		fileMenu.addSeparator();
		fileMenu.add(quit);
		menuBar.add(fileMenu);
		
		aboutApp.addActionListener(eventProcessing);
		aboutApp.setActionCommand("about app");
		
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
