 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 * MenuFrame class contains the header of the JFrame and the JFrame itself. It
 * holds the upper menu and refers to its their action listener.
 * 
 * @author Hamzeh Jaafar
 * @studentID 5006936919
 * @version 1.0
 */
public class MainFrame extends JFrame {
	// Size of JFrame
	public static final int FRAME_WIDTH = 700;
	public static final int FRAME_HEIGHT = 500;

	/**
	 * Constructor for MainFrame class, initializes a new JFrame with defined
	 * width and height, and creates the menu for the JFrame.
	 */
	public MainFrame() {

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		menuBar.add(createQueueMenu());
		menuBar.add(createListMenu());

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	/**
	 * Creates the File menu.
	 * 
	 * @return the menu
	 */
	public JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		JMenuItem newItem = new JMenuItem("New");
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new MenuListener());
		newItem.addActionListener(new MenuListener());
		menu.add(newItem);
		menu.add(exitItem);
		return menu;
	}

	/**
	 * Creates the Queue menu.
	 * 
	 * @return the menu
	 */
	public JMenu createQueueMenu() {
		JMenu menu = new JMenu("Queue");
		menu.add(createRemoveMenu());
		menu.add(createAddMenu());
		return menu;
	}

	/**
	 * Creates the List menu.
	 * 
	 * @return the menu
	 */
	public JMenu createListMenu() {
		JMenu menu = new JMenu("List");
		menu.add(createAddFirst());
		menu.add(createAddLast());
		menu.add(createRemoveFirst());
		menu.add(createRemoveLast());
		return menu;
	}

	/**
	 * Creates the Remove menu.
	 * 
	 * @return the menu
	 */
	public JMenuItem createRemoveMenu() {
		JMenuItem item = new JMenuItem("remove");
		item.addActionListener(new MenuListener());

		return item;
	}

	/**
	 * Creates the Add menu.
	 * 
	 * @return the menu
	 */
	public JMenuItem createAddMenu() {
		JMenuItem item = new JMenuItem("add");
		item.addActionListener(new MenuListener());

		return item;
	}

	/**
	 * Creates the AddFirst menu.
	 * 
	 * @return the menu
	 */
	public JMenuItem createAddFirst() {
		JMenuItem item = new JMenuItem("AddFirst");
		item.addActionListener(new MenuListener());
		return item;
	}

	/**
	 * Creates the AddLast menu.
	 * 
	 * @return the menu
	 */
	public JMenuItem createAddLast() {
		JMenuItem item = new JMenuItem("AddLast");
		item.addActionListener(new MenuListener());
		return item;
	}

	/**
	 * Creates the RemoveFirst menu.
	 * 
	 * @return the menu
	 */
	public JMenuItem createRemoveFirst() {
		JMenuItem item = new JMenuItem("RemoveFirst");
		item.addActionListener(new MenuListener());
		return item;
	}

	/**
	 * Creates the RemoveLast menu.
	 * 
	 * @return the menu
	 */
	public JMenuItem createRemoveLast() {
		JMenuItem item = new JMenuItem("RemoveLast");
		item.addActionListener(new MenuListener());
		return item;
	}

}
