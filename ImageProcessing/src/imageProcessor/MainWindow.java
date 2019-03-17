package imageProcessor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame implements Window {
	
	private static final long serialVersionUID = 1L;
	
	private final int WINDOW_WIDTH = 600;
	private final int WINDOW_HEIGHT = 500;
	
	private final int PREFERRED_WIDTH = 200;
	private final int PREFERRED_HEIGHT = 100;
	
	private JMenuBar menuBar;
	private JMenu filtersMenu;
	private ButtonGroup filtersMenuItems;
	
	private BufferedImage image;
	private Image iconImage;
	private JLabel imageViewer;
	
	private ActionListener actionListener;

	@Override
	public void showImage(BufferedImage newImage) {
		
		image = newImage;
		
		setImageProportions();
		visualizeImage();
	}
	
	private void visualizeImage() {
		
		imageViewer.setIcon(new ImageIcon(iconImage));
		imageViewer.setHorizontalAlignment(SwingConstants.CENTER);
		imageViewer.setVerticalAlignment(SwingConstants.CENTER);
		
		this.add(imageViewer);
		this.setVisible(true);
	}
	
	private void setImageProportions() {
		
		if(image != null) {
			double ratio = 0;
			
			int iconWidth = image.getWidth();
			int iconHeight = image.getHeight();
			
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
			iconImage = image.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
		}
	}

	@Override
	public void createWindow(ActionListener newActionListener) {
		
		this.setTitle("Simple image processing.");
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.actionListener = newActionListener;
		createMenuBar();
		setImageViewer();
		
		this.setVisible(true);
	}
	
	public void setImageViewer() {
		
		imageViewer = new JLabel();
		
		imageViewer.setOpaque(true);
		imageViewer.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
		imageViewer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		imageViewer.setBackground(Color.BLACK);
		
		this.add(imageViewer, BorderLayout.CENTER);
	}
	
	@Override
	public void addMenu(imageProcessor.Menu newMenu) {
		
		JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem(newMenu.getName());
		radioButtonMenuItem.setActionCommand(newMenu.getActionCommandName());
		radioButtonMenuItem.addActionListener(actionListener);
		
		filtersMenuItems.add(radioButtonMenuItem);
		filtersMenu.add(radioButtonMenuItem);
	}

	private void createMenuBar() {
		
		menuBar = new JMenuBar();
		
		createFileMenu();
		createFiltersMenu();
		createAboutMenu();
		
		this.setJMenuBar(menuBar);
	}
	
	private void createFiltersMenu() {
		
		filtersMenu = new JMenu("Filters");
		filtersMenuItems = new ButtonGroup();
		
		menuBar.add(filtersMenu);
	}
	
	private void createAboutMenu() {
		
		JMenu aboutMenu = new JMenu("About");
		JMenuItem aboutApp = new JMenuItem("About this software");
		JMenuItem aboutAuthor = new JMenuItem("About author");
		
		aboutApp.addActionListener(actionListener);
		aboutApp.setActionCommand("about app");
		
		aboutAuthor.addActionListener(actionListener);
		aboutAuthor.setActionCommand("about author");
		
		aboutMenu.add(aboutApp);
		aboutMenu.add(aboutAuthor);
		menuBar.add(aboutMenu);
	}
	
	private void createFileMenu() {
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem open = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem saveAs = new JMenuItem("Save as...");
		JMenuItem quit = new JMenuItem("Quit");
		
		open.addActionListener(actionListener);
		open.setActionCommand("open file");
		
		save.addActionListener(actionListener);
		save.setActionCommand("save file");
		
		saveAs.addActionListener(actionListener);
		saveAs.setActionCommand("save file as");
		
		quit.addActionListener(actionListener);
		quit.setActionCommand("exit program");
		
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(saveAs);
		fileMenu.addSeparator();
		fileMenu.add(quit);
		menuBar.add(fileMenu);
	}

	
	@Override
	public void showOptionDialog(JDialog newOptionDialog) {
		
		
	}

	@Override
	public void setEventProcessor(ActionListener newActionListener) {
		
		this.actionListener = newActionListener;
	}
}
