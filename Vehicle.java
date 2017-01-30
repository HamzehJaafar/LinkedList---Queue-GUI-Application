 

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JComponent;

/**
 * Vehicle class implements the methods that are shared between the RailCar and
 * TrainEngine class.
 * 
 * @author Hamzeh Jaafar
 * @studentID 5006936919
 * @version 1.0
 */
public abstract class Vehicle implements VehicleInterface {
	private int x, y;
	public static int number = 0;
	private int thisNum;
	private Point boxP;
	private Dimension dimension;
	protected Vehicle trailer;
	private Rectangle boundingBox;
	protected boolean isPartOfATrailer;
	private boolean isTrailerHead;
	private boolean carryingBox;

	/**
	 * Constructor for Vehicle class, this initializes either a Car of a
	 * Vehicle.
	 * 
	 * @param x2
	 *            X cordinate where the mouse was clicked
	 * @param y2
	 *            Y cordinate where the mouse was clicked
	 * @param width
	 *            The width of the bonding box for this Vehicle
	 * @param height
	 *            The height of the bonding box for this Vehicle
	 **/
	public Vehicle(int x2, int y2, int width, int height) {
		thisNum = number++;
		x = x2;
		y = y2;
		dimension = new Dimension(width, height);
		boundingBox = new Rectangle(x2, y2, (int) dimension.getWidth(), (int) dimension.getHeight());
		trailer = null;
		carryingBox = false;
		isPartOfATrailer = false;
		isTrailerHead = false;
	}

	/**
	 * getX gets the X cordinate of the Vehicle .
	 * 
	 * @return The X Cordinate
	 **/
	public int getX() {
		return x;
	}

	/**
	 * getY gets the Y cordinate of the Vehicle .
	 * 
	 * @return The Y Cordinate
	 **/
	public int getY() {
		return y;
	}

	/**
	 * getNumber gets the number of the Vehicle.
	 * 
	 * @return The the number of the Vehicle.
	 **/
	public int getNumber() {
		return thisNum;
	}

	/**
	 * collide function determines whether two Vehicle objects intersect one
	 * another.
	 * 
	 * @param object
	 *            The other object to compare for an intersection.
	 * @return the boolean value if a collision has occurred or not.
	 **/
	public boolean collide(Vehicle object) {
		return (this.boundingBox.intersects(object.boundingBox));
	}

	/**
	 * contains function if the vehicle object contains a given point.
	 * 
	 * @param x
	 *            X cordinate for the point to check
	 * @param y
	 *            Y cordinate for the point to check
	 **/

	public boolean contains(int x, int y) {
		return this.boundingBox.contains(x, y);
	}

	abstract void draw(Graphics2D g2);

	/**
	 * move function moves the vehicle object to the point location given.
	 * 
	 * @param point
	 *            The new location for the object to move towards.
	 **/
	public void move(Point point) {
		x = (int) point.getX();
		y = (int) point.getY();
		this.boundingBox.setLocation(point);
		if (this.hasBox()) {
			boxP.setLocation(point.getX() + 15, point.getY() - 15);
		}
		if (this.trailer != null) {
			this.trailer.setContact(this);
		}

	}

	/**
	 * hitchTrailer function connects the tail of a vehicle objectto the head of
	 * another.
	 * 
	 * @param head
	 *            The vehicle object that the tail will connect with.
	 * @param tail
	 *            The vehicle object that will be connected to the head.
	 **/
	public static void hitchTrailer(Vehicle head, Vehicle tail) {
		head.trailer = tail;

		if (head.isPartOfATrailer) {
			tail.isPartOfATrailer = true;
		}

		else if (tail.isTrailerHead) {
			tail.isTrailerHead = false;
			tail.isPartOfATrailer = true;
		}

		else {
			head.isTrailerHead = true;
			head.isPartOfATrailer = false;
			tail.isPartOfATrailer = true;
		}

	}

	/**
	 * Sets the contact of this vehicle to the previous one.
	 * 
	 * @param prev
	 *            The vehicle that is before this one in this LinkedList
	 */
	public void setContact(Vehicle previous) {
		int x = (int) previous.getX();
		int xDimension = (int) previous.dimension.getWidth();
		int y = (int) previous.getY();

		int newX = x + xDimension + 1;
		Point point = new Point(newX, y);
		this.move(point);
	}

	/**
	 * Removes the Trailer link of a vehicle object
	 */
	public void removeTrailerLink() {
		this.trailer = null;

	}

	/**
	 * Removes the vehicle object from a trailer.
	 */
	public void removeFromTrailer() {

		this.trailer = null;
		this.isPartOfATrailer = false;

		Random random = new Random();
		int y = random.nextInt((int) (MainFrame.FRAME_HEIGHT - this.dimension.getHeight() * 3));

		int x = random.nextInt((int) (MainFrame.FRAME_WIDTH - this.dimension.getWidth() * 3));
		Point point = new Point(x, y);

		this.move(point);

	}

	/**
	 * Adds a box from the storage container onto a vehicle object
	 * 
	 * @param p
	 *            The point where the vehicle is located.
	 */
	public void addBox(Point p) {
		boxP = p;
		carryingBox = true;
	}

	/**
	 * Removes the box from a vehicle.
	 */
	public void removeBox() {
		boxP = null;
		carryingBox = false;
	}

	/**
	 * Returns the point where the box is currently located.
	 * 
	 * @return the current location of the box.
	 */
	public Point getBox() {
		return boxP;
	}

	/**
	 * Checks too see if this vehicle contains a box already.
	 * 
	 * @return a boolean variable that determines whether the vehicle is
	 *         carrying a box or not.
	 */
	public boolean hasBox() {
		return carryingBox;
	}
}
