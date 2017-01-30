 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * StorageContainer class draws and contains information for the stack of blocks
 * labeled [A] [B] [C] [D] [E] [F].
 * 
 * @author Hamzeh Jaafar
 * @studentID 5006936919
 * @version 1.0
 */
public class StorageContainer {
	public static int x, y;
	public static Point pointA, pointB, pointC, pointD, pointE;
	Dimension dimension;

	/**
	 * Constructor for StorageContainer class, initializes the container given
	 * two points.
	 * 
	 * @param x
	 *            X cordinate where mouse was pressed and the location the stack
	 *            will be drawn.
	 * @param y
	 *            Y cordinate where mouse was pressed and the location the stack
	 *            will be drawn.
	 */
	public StorageContainer(int x, int y) {
		this.x = x;
		this.y = y;
		pointA = new Point(x + 30, y - 30);
		pointB = new Point(x + 30, y - 60);
		pointC = new Point(x + 30, y - 90);
		pointD = new Point(x + 30, y - 120);
		pointE = new Point(x + 30, y - 150);
		dimension = new Dimension(25, 25);
		LinkedSim.queue.add(pointA);
		LinkedSim.queue.add(pointB);
		LinkedSim.queue.add(pointC);
		LinkedSim.queue.add(pointD);
		LinkedSim.queue.add(pointE);

	}

	/**
	 * Draw method draws and updates stack container and the boxes.
	 * 
	 * @param g2
	 *            Graphics2D object of frame
	 * @return void return nothing
	 */

	public void draw(Graphics2D g2) {
		// black box
		g2.setColor(Color.BLACK);
		Rectangle container = new Rectangle(x, y, 80, 25);
		g2.draw(container);
		g2.fill(container);
		g2.drawString("A", (int) pointA.getX() + 11, (int) pointA.getY() + 15);
		g2.drawString("B", (int) pointB.getX() + 11, (int) pointB.getY() + 15);
		g2.drawString("C", (int) pointC.getX() + 11, (int) pointC.getY() + 15);
		g2.drawString("D", (int) pointD.getX() + 11, (int) pointD.getY() + 15);
		g2.drawString("E", (int) pointE.getX() + 11, (int) pointE.getY() + 15);
		// Box'S;
		g2.setColor(Color.GREEN);
		Rectangle boxA = new Rectangle(pointA, dimension);
		g2.draw(boxA);
		Rectangle boxB = new Rectangle(pointB, dimension);
		g2.draw(boxB);
		Rectangle boxC = new Rectangle(pointC, dimension);
		g2.draw(boxC);
		Rectangle boxD = new Rectangle(pointD, dimension);
		g2.draw(boxD);
		Rectangle boxE = new Rectangle(pointE, dimension);
		g2.draw(boxE);
	}

}
