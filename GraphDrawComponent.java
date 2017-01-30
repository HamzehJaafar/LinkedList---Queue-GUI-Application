 

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * GraphDrawComponent class initializes the components for the Vehicles and
 * Storage Container.
 * 
 * @author Hamzeh Jaafar
 * @studentID 5006936919
 * @version 1.0
 */
public class GraphDrawComponent extends JComponent {
	private StorageContainer container;

	/**
	 * Constructor for GraphDrawComponent class, this initializes either a
	 * Train, a Car or the stack of boxes depending on given type
	 * 
	 * @param x
	 *            X cordinate where the mouse was clicked
	 * @param y
	 *            Y cordinate where the mouse was clicked
	 * @param type
	 *            The component type to be created
	 **/
	public GraphDrawComponent(int x, int y, String string) {
		if (string == "train") {
			TrainEngine train = new TrainEngine(x, y);

			LinkedSim.arr.add(train);
			repaint();
		}
		if (string == "car") {
			RailCar car = new RailCar(x, y);

			LinkedSim.arr.add(car);
			repaint();
		}
		if (string == "container") {
			container = new StorageContainer(x, y);

			repaint();
		}
	}

	/**
	 * Paint method for the graphic interface and paints the components into the
	 * JFrame
	 * 
	 * @param g
	 *            The graphics object GUI.
	 * @return void nothing
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (Vehicle graphArray : LinkedSim.arr) {
			graphArray.draw(g2);
		}
		if (container != null)
			container.draw(g2);
	}
}