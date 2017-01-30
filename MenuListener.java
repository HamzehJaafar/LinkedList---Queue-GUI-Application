 

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JMenuItem;

/**
 * MenuListener implements the ActionListener for the header menu.
 * 
 * @author Hamzeh Jaafar
 * @studentID 5006936919
 * @version 1.0
 */
public class MenuListener extends LinkedSim implements ActionListener {

	@Override
	/**
	 * actionPerformed determines which menu was pressed and acts accordingly.
	 * 
	 * @param e
	 *            ActionEvent indicates the menu that was pressed.
	 */
	public void actionPerformed(ActionEvent e) {
		String item = e.getActionCommand();
		if (item == "AddFirst") {
			LinkedSim.addFirst();
		} else if (item == "AddLast") {
			LinkedSim.addLast();
		} else if (item == "RemoveFirst") {
			LinkedSim.removeFirst();
		} else if (item == "RemoveLast") {
			LinkedSim.removeLast();
		} else if (item == "remove") {
			LinkedSim.remove();
		} else if (item == "add") {
			LinkedSim.add();
		} else if (item == "New") {
			arr = new ArrayList<Vehicle>();
			queue = new LinkedList<Point>();
			click = 0;
			selected = null;
			frame.getContentPane().removeAll();
			revalidate();
			frame.repaint();
			repaint();
		} else if (item == "Exit")
			System.exit(0);

	}

}
