 

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * LinkedSim class implements the methods for the header menu of the JFrame
 * which also includes selection and deselection methods.
 * 
 * @author Hamzeh Jaafar
 * @studentID 5006936919
 * @version 1.0
 */
public class LinkedSim extends JPanel {
	static ArrayList<Vehicle> arr = new ArrayList<Vehicle>();
	static Queue<Point> queue = new LinkedList<Point>();
	public static Vehicle selected;
	static int click = 0;
	public static JFrame frame = new MainFrame();

	
	/**
	 * Main class contains the MouseListeners and initializes the JFrame of this
	 * program, as well it contains functions for the upper menu in the JFrame.
	 * 
	 * @param args
	 *            Array of Arguements that are handed on execution of the file.
	 */
	public static void main(String[] args) {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("FontViewer");
		frame.setVisible(true);

		/**
		 * Listener class checks for mouse clicks on the JFrame and initializes
		 * the JFrame Components (1 Train , 5 Cars and Storage Container) on the
		 * first 7 clicks. This class also listens for selection,deselection and
		 * motion.
		 * 
		 * @author Hamzeh Jaafar
		 * @studentID 5006936919
		 * @version 1.0
		 */

		class Listener implements MouseListener, MouseMotionListener {

			@Override
			/**
			 * mouseClicked for clicks on the JFrame and for the first 7 this
			 * function creates the inital Cars, Train and Storage Contair
			 * components.
			 * 
			 * @param e
			 *            MouseEvent the indicates the action of the mouse
			 */
			public void mouseClicked(MouseEvent e) {
				if (click == 0) {
					GraphDrawComponent car = new GraphDrawComponent(e.getX() - 8, e.getY() - 62, "train");
					frame.add(car);
					car.revalidate();
					frame.repaint();
					click++;
					return;

				} else if (click < 6 && click > 0) {
					GraphDrawComponent car = new GraphDrawComponent(e.getX() - 7, e.getY() - 62, "car");
					frame.add(car);
					click++;
					car.revalidate();
					frame.repaint();

				} else if (click == 6) {
					GraphDrawComponent container = new GraphDrawComponent(e.getX() - 7, e.getY() - 62, "container");
					frame.add(container);
					click++;
					container.revalidate();
					frame.repaint();
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			/**
			 * mousePressed checks for mouse clicks in the JFrame for selecting
			 * and deselecting Vehicle objects
			 * 
			 * @param e
			 *            MouseEvent the indicates the action of the mouse
			 */
			public void mousePressed(MouseEvent event) {
				if (selected != null) {
					selected.isUnSelected();
					frame.repaint();
				}
				selected = null;
				int x = event.getX();
				int y = event.getY();

				for (Vehicle vehicle : arr) {
					if (vehicle.contains(x - 8, y - 62) && !vehicle.isPartOfATrailer) {
						selected = vehicle;
						selected.isSelected();
						break;
					}
				}

				frame.repaint();

			}

			/**
			 * mouseReleased checks when the mouse gets released and determines
			 * if a vehicle is close enough to another vehicle to attach to it,
			 * and attaches if it is close enough.
			 * 
			 * @param e
			 *            MouseEvent the indicates the action of the mouse
			 */
			@Override
			public void mouseReleased(MouseEvent e) {
				if (selected == null) {
					return;
				} else {
					for (Vehicle vehicle : arr) {

						if (selected instanceof TrainEngine) {
							return;

						}
						if (!vehicle.equals(selected) && vehicle.collide(selected)) {

							if (vehicle.trailer != null) {
								Vehicle lastTrailer = vehicle.trailer;
								Vehicle prev = vehicle;

								while (lastTrailer.trailer != null) {
									prev = lastTrailer;
									lastTrailer = lastTrailer.trailer;
								}

								Vehicle.hitchTrailer(lastTrailer, selected);
								selected.setContact(lastTrailer);
								selected.isUnSelected();
								selected = vehicle;
								selected.isSelected();

								frame.repaint();
							}

							else {
								Vehicle.hitchTrailer(vehicle, selected);
								selected.setContact(vehicle);
								selected.isUnSelected();
								selected = vehicle;
								selected.isSelected();

								frame.repaint();
							}
						}

					}
				}

			}

			/**
			 * mouseDragged method checks for a selected vehicle and moves it to
			 * the new location of the mouse.
			 * 
			 * @param e
			 *            MouseEvent the indicates the action of the mouse
			 */
			public void mouseDragged(MouseEvent event) {

				if (selected != null) {
					int x = event.getX();
					int y = event.getY();
					Point point = new Point(x - 8, y - 62);
					selected.move(point);

					frame.repaint();
				}
			}

			// Unused Method.

			@Override
			public void mouseMoved(MouseEvent arg0) {
			}
		}
		// Create JFrame motion Listener.
		MouseMotionListener motion = new Listener();
		// Creates JFrame mouse Listener.
		MouseListener listen = new Listener();

		// Adds Listeners to Jframe.
		frame.addMouseListener(listen);
		frame.addMouseMotionListener(motion);
	}

	/**
	 * removeLast method removes the last vehicle in the LinkedList of the Train
	 * vehicle.
	 * 
	 * @return void Return nothing.
	 */
	public static void removeLast() {
		Vehicle truck = arr.get(0);

		if (truck.trailer == null) {
			return;
		}

		Vehicle lastTrailer = truck.trailer;
		Vehicle prev = truck;

		while (lastTrailer.trailer != null) {
			prev = lastTrailer;
			lastTrailer = lastTrailer.trailer;
		}

		lastTrailer.removeFromTrailer();
		prev.removeTrailerLink();

		frame.repaint();
	}

	/**
	 * removeFirst method removes the first vehicle in the LinkedList of the
	 * Train vehicle.
	 * 
	 * @return void Return nothing.
	 */
	public static void removeFirst() {
		Vehicle truck = arr.get(0);

		if (truck.trailer == null) {
			return;
		}

		Vehicle lastTrailer = truck.trailer;
		Vehicle lastTrailerNext = lastTrailer.trailer;

		if (lastTrailerNext == null) {
			lastTrailer.removeFromTrailer();
			truck.removeTrailerLink();
		}

		else {
			lastTrailer.removeFromTrailer();
			truck.removeTrailerLink();
			Vehicle.hitchTrailer(truck, lastTrailerNext);
			lastTrailerNext.setContact(truck);
		}
		frame.repaint();
	}

	/**
	 * addLast method adds a selected vehicle to the end of the Train vehicle.
	 * 
	 * @return void Return nothing.
	 */
	public static void addLast() {
		if (selected == null || selected.isPartOfATrailer) {
			return;
		}

		Vehicle truck = arr.get(0);

		if (truck.trailer != null) {
			Vehicle lastTrailer = truck.trailer;

			while (lastTrailer.trailer != null) {
				lastTrailer = lastTrailer.trailer;
			}

			Vehicle.hitchTrailer(lastTrailer, selected);
			selected.setContact(lastTrailer);
		}

		else {
			Vehicle.hitchTrailer(truck, selected);
			selected.setContact(truck);
		}

		selected.isUnSelected();

		frame.repaint();
	}

	/**
	 * addFirst method adds a selected vehicle to the front of the Train
	 * vehicle.
	 * 
	 * @return void Return nothing.
	 */
	public static void addFirst() {
		Vehicle truck = arr.get(0);

		if (selected == null || selected.isPartOfATrailer) {
			return;
		}

		if (truck.trailer == null) {
			Vehicle.hitchTrailer(truck, selected);
			selected.setContact(truck);
		}

		else {
			Vehicle lastTrailer = truck.trailer;

			truck.removeTrailerLink();

			Vehicle.hitchTrailer(truck, selected);
			selected.setContact(truck);

			Vehicle endOfSelected = truck.trailer;
			while (endOfSelected.trailer != null) {
				endOfSelected = endOfSelected.trailer;
			}

			Vehicle.hitchTrailer(endOfSelected, lastTrailer);
			lastTrailer.setContact(endOfSelected);
		}
		frame.repaint();
	}

	/**
	 * remove method removes a box from the queue and adds it to a Car vehicle.
	 * If Vehicle already contains a box it adds it to the next available
	 * trailer.
	 * 
	 * @return void Return nothing.
	 */
	public static void remove() {
		if (selected == null) {
			return;
		}

		if (selected.hasBox() || selected == arr.get(0)) {
			Vehicle temp = selected.trailer;
			while (temp != null) {

				if (temp.hasBox()) {
					temp = temp.trailer;
					continue;
				}
				Point point = queue.remove();
				temp.addBox(point);
				point.setLocation(temp.getX() + 15, temp.getY() - 15);
				for (Point e : queue) {

					e.setLocation(e.getX(), e.getY() + 30);

				}
				break;
			}
		} else {
			Point point = queue.remove();

			selected.addBox(point);
			point.setLocation(selected.getX() + 15, selected.getY() - 15);

			for (Point e : queue) {

				e.setLocation(e.getX(), e.getY() + 30);
			}

		}
		frame.repaint();
	}

	/**
	 * add method takes the box out of a selected vehicle and adds it back to
	 * the queue and the stack of boxes.
	 * 
	 * @return void Return nothing.
	 */
	public static void add() {
		if (selected == null) {
			return;
		}
		if (!selected.hasBox() || selected == arr.get(0)) {
			Vehicle temp = selected.trailer;
			while (temp != null) {

				if (!temp.hasBox()) {
					temp = temp.trailer;
					continue;
				}
				Point p = temp.getBox();
				temp.removeBox();
				int y = 30 * queue.size() + 30;
				p.setLocation(StorageContainer.x + 30, StorageContainer.y - y);
				queue.add(p);
				break;
			}

		} else {
			Point p = selected.getBox();
			selected.removeBox();
			int y = 30 * queue.size() + 30;
			p.setLocation(StorageContainer.x + 30, StorageContainer.y - y);
			queue.add(p);
		}
		frame.repaint();
	}
}