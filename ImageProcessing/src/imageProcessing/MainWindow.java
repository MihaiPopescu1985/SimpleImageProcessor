package imageProcessing;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainWindow {

	private final int WIDTH = 600;
	private final int HEIGHT = 500;
	
	private JFrame mainWindow;
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu aboutMenu;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem saveAs;
	private JMenuItem quit;
	private JMenuItem aboutApp;
	private JMenuItem aboutAuthor;
	
	public MainWindow() {
		
		mainWindow = new JFrame("Simple image processing");
		mainWindow.setSize(WIDTH, HEIGHT);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}
	
	public void populateWindow() {
		
		createMenu();
	}
	
	private void createMenu() {
		
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		saveAs = new JMenuItem("SaveAs");
		quit = new JMenuItem("Quit");
		
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(saveAs);
		fileMenu.addSeparator();
		fileMenu.add(quit);
		menuBar.add(fileMenu);
		
		aboutMenu = new JMenu("About");
		aboutApp = new JMenuItem("About this software");
		aboutAuthor = new JMenuItem("About author");
		
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
