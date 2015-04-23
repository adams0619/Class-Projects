package edu.ncsu.csc216.carrental.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.Customer;
import edu.ncsu.csc216.carrental.model.management.NuxCarRental;

/**
 * GUI class for displaying the CarRental simulation
 * 
 * @author Noah Unger (ngunger@ncsu.edu)
 * @author Adams Ombonga (amombong)
 *
 */
@SuppressWarnings("serial")
public class CarRentalGUI extends JFrame implements ActionListener {
	// Window and button strings
	/** Name for program */
	private final static String TITLE = "Nux Car Rental Management";
	/** Name for add car button */
	private final static String ADD_CAR = "Add New Car";
	/** Name for add customer button */
	private final static String ADD_CUSTOMER = "New Customer";
	/** Name for rent button */
	private final static String RENT = "Rent Car";
	/** Name for return button */
	private final static String RETURN = "Customer Return";
	/** Name for detail button */
	private final static String DETAIL = "Detailing Complete";
	/** Name for returning with a problem button */
	private final static String REPORT = "Problem Reported";
	/** Name for repair button */
	private final static String REPAIR = "Repairs Complete";
	/** Name for customer list */
	private final static String CUSTOMERS = "Customers";
	/** Name for available cars list */
	private final static String AVAILABLE = "Available Cars";
	/** Name for rented cars list */
	private final static String RENTED = "Rented Cars";
	/** Name for detailed cars list */
	private final static String DETAILED = "Cars in Detail Shop";
	/** Name for repaired cars list */
	private final static String REPAIRING = "Cars Out for Repair";

	// Size constants for the window
	/** Number for program width */
	private final static int FRAME_WIDTH = 730;
	/** Number for program heigtht */
	private final static int FRAME_HEIGHT = 500;

	// Buttons
	/** Button for adding customer */
	private JButton btnAddCustomer = new JButton(ADD_CUSTOMER);
	/** Button for adding car */
	private JButton btnAddCar = new JButton(ADD_CAR);
	/** Button for renting */
	private JButton btnRent = new JButton(RENT);
	/** Button for returning */
	private JButton btnReturn = new JButton(RETURN);
	/** Button for detailing */
	private JButton btnDetail = new JButton(DETAIL);
	/** Button for returning with a problem*/
	private JButton btnReport = new JButton(REPORT);
	/** Button for repairing */
	private JButton btnRepair = new JButton(REPAIR);

	/** Main left panel */
	private JPanel pnlCustomers = new JPanel(new BorderLayout());
	/** Top of left panel */
	private JPanel pnlAvailableCars = new JPanel(new BorderLayout());
	/** Main right panel */
	private JPanel pnlRentedCars = new JPanel(new BorderLayout());
	/** Top of right panel */
	private JPanel pnlDetailShop = new JPanel(new BorderLayout());
	/** Panel for the display list */
	private JPanel pnlRepairShop = new JPanel(new BorderLayout());

	// List model stuff
	// Default list models for the scrollable lists
	/** Waiting list default list model */
	private DefaultListModel<String> dlmCustomers = new DefaultListModel<String>();
	/** Actual waiting list */
	private JList<String> lstCustomers = new JList<String>(dlmCustomers);
	/** Scroll pane for waiting list */
	private JScrollPane scpCustomers = new JScrollPane(lstCustomers);
	/** Waiting list default for list model */
	private DefaultListModel<String> dlmAvailable = new DefaultListModel<String>();
	/** Actual waiting list */
	private JList<String> lstAvailable = new JList<String>(dlmAvailable);
	/** Scroll pane for waiting list */
	private JScrollPane scpAvailable = new JScrollPane(lstAvailable);
	/** Waiting list default list model */
	private DefaultListModel<String> dlmRented = new DefaultListModel<String>();
	/** Actual waiting list */
	private JList<String> lstRented = new JList<String>(dlmRented);
	/** Scroll pane for waiting list */
	private JScrollPane scpRented = new JScrollPane(lstRented);
	/** Waiting list default list model */
	private DefaultListModel<String> dlmDetailing = new DefaultListModel<String>();
	/** Actual waiting list */
	private JList<String> lstDetailing = new JList<String>(dlmDetailing);
	/** Scroll pane for waiting list */
	private JScrollPane scpDetailing = new JScrollPane(lstDetailing);
	/** Waiting list default list model */
	private DefaultListModel<String> dlmRepairing = new DefaultListModel<String>();
	/** Actual waiting list */
	private JList<String> lstRepairing = new JList<String>(dlmRepairing);
	/** Scroll pane for waiting list */
	private JScrollPane scpRepairing = new JScrollPane(lstRepairing);
	/** Private reference to our NuxCarRental manager */
	private NuxCarRental rentalLocation;

	/**
	 * Constructor for GUI class when no file is passed to the program
	 */
	public CarRentalGUI() {
		setUpGUI();
	}

	/**
	 * Constructor of GUI class given file to initiate
	 * 
	 * @param fileName
	 *            file containing information to be traversed and added to the
	 *            programs list when starting the program
	 */
	public CarRentalGUI(String fileName) {
		rentalLocation = new NuxCarRental();
		if (fileName == null) { // The user specifies the file from a file
								// chooser
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int returnVal = fc.showOpenDialog(this);
			try {
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					rentalLocation = new NuxCarRental(new Scanner(
							fc.getSelectedFile()));
				} else {
					rentalLocation = new NuxCarRental();
				}
			} catch (Exception e) {
				rentalLocation = new NuxCarRental();
			}
		} else { // file name is command-line parameter
			try {
				rentalLocation = new NuxCarRental(new Scanner(
						new File(fileName)));
			} catch (Exception e) {
				rentalLocation = new NuxCarRental();
			}
		}
		setUpGUI();
	}

	/**
	 * Private set up method used to help set up the GUI for our program
	 */
	private void setUpGUI() {
		// Construct the main window.
		setTitle(TITLE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.LINE_AXIS));
		setUpPanels();
		loadModel(lstCustomers, dlmCustomers, rentalLocation.customersWaiting());
		loadModel(lstAvailable, dlmAvailable, rentalLocation.availableCars());
		loadModel(lstRented, dlmRented, rentalLocation.rentedCars());
		loadModel(lstDetailing, dlmDetailing, rentalLocation.detailingCars());
		loadModel(lstRepairing, dlmRepairing, rentalLocation.repairingCars());

		lstCustomers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstAvailable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstRented.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstDetailing.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstRepairing.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Add the waiting room and ward panels to the main window
		c.add(pnlCustomers);
		c.add(pnlAvailableCars);
		c.add(pnlRentedCars);
		c.add(pnlDetailShop);
		c.add(pnlRepairShop);

		// Determine which buttons should be enabled or disabled when setting up
		// GUI
		if (rentalLocation.hasWaitingCustomers()
				&& rentalLocation.hasAvailableCars())
			btnRent.setEnabled(true);
		else
			btnRent.setEnabled(false);

		if (rentalLocation.hasDetailingCars())
			btnDetail.setEnabled(true);
		else
			btnDetail.setEnabled(false);

		if (rentalLocation.hasRentedCars()) {
			btnReturn.setEnabled(true);
			btnReport.setEnabled(true);
		} else {
			btnReturn.setEnabled(false);
			btnReport.setEnabled(false);
		}

		if (rentalLocation.hasRepairingCars())
			btnRepair.setEnabled(true);
		else
			btnRepair.setEnabled(false);

		addListeners();
		// Inner c
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowClosing(WindowEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				FileWriter writer;
				int returnValue = chooser.showSaveDialog(null);
				try {
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						writer = new FileWriter(chooser.getSelectedFile());
						rentalLocation.writeData(writer);
						writer.close();
					}
				} catch (Exception ex) {
					writer = null;
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Method used to sets up the panels that comprise the GUI.
	 */
	private void setUpPanels() {
		pnlCustomers
				.setLayout(new BoxLayout(pnlCustomers, BoxLayout.PAGE_AXIS));
		pnlAvailableCars.setLayout(new BoxLayout(pnlAvailableCars,
				BoxLayout.PAGE_AXIS));
		pnlRentedCars.setLayout(new BoxLayout(pnlRentedCars,
				BoxLayout.PAGE_AXIS));
		pnlDetailShop.setLayout(new BoxLayout(pnlDetailShop,
				BoxLayout.PAGE_AXIS));
		pnlRepairShop.setLayout(new BoxLayout(pnlRepairShop,
				BoxLayout.PAGE_AXIS));

		btnAddCustomer.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAddCar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRent.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnReturn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnDetail.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnReport.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRepair.setAlignmentX(Component.CENTER_ALIGNMENT);

		pnlCustomers.add(btnAddCustomer);
		pnlAvailableCars.add(btnAddCar);
		pnlAvailableCars.add(btnRent);
		pnlRentedCars.add(btnReturn);
		pnlDetailShop.add(btnDetail);
		pnlRentedCars.add(btnReport);
		pnlRepairShop.add(btnRepair);

		// Put the cars/customers in scrolling text areas
		// Put the devices/droids in scrolling text areas
		scpCustomers.setBorder(BorderFactory.createLineBorder(Color.black));
		lstCustomers.setFont(new Font("monospaced", Font.PLAIN, 12));
		scpAvailable.setBorder(BorderFactory.createLineBorder(Color.black));
		lstAvailable.setFont(new Font("monospaced", Font.PLAIN, 12));
		scpRented.setBorder(BorderFactory.createLineBorder(Color.black));
		lstRented.setFont(new Font("monospaced", Font.PLAIN, 12));
		scpDetailing.setBorder(BorderFactory.createLineBorder(Color.black));
		lstDetailing.setFont(new Font("monospaced", Font.PLAIN, 12));
		scpRepairing.setBorder(BorderFactory.createLineBorder(Color.black));
		lstRepairing.setFont(new Font("monospaced", Font.PLAIN, 12));

		// Set up customer panel
		pnlCustomers.add(scpCustomers, BorderLayout.CENTER);
		pnlAvailableCars.add(scpAvailable, BorderLayout.CENTER);
		pnlRentedCars.add(scpRented, BorderLayout.CENTER);
		pnlDetailShop.add(scpDetailing, BorderLayout.CENTER);
		pnlRepairShop.add(scpRepairing, BorderLayout.CENTER);

		// Set up buttons
		btnAddCustomer.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAddCar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRent.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnReturn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnDetail.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnReport.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRepair.setAlignmentX(Component.CENTER_ALIGNMENT);

		pnlCustomers.setBorder(BorderFactory.createTitledBorder(CUSTOMERS));
		pnlAvailableCars.setBorder(BorderFactory.createTitledBorder(AVAILABLE));
		pnlRentedCars.setBorder(BorderFactory.createTitledBorder(RENTED));
		pnlDetailShop.setBorder(BorderFactory.createTitledBorder(DETAILED));
		pnlRepairShop.setBorder(BorderFactory.createTitledBorder(REPAIRING));

	}

	/**
	 * Adds action listeners for buttons
	 */
	private void addListeners() {
		btnAddCustomer.addActionListener(this);
		btnAddCar.addActionListener(this);
		btnRent.addActionListener(this);
		btnReturn.addActionListener(this);
		btnRepair.addActionListener(this);
		btnDetail.addActionListener(this);
		btnReport.addActionListener(this);
	}

	/**
	 * Private Method - loads a list model from a string using newline
	 * tokenizers.
	 * 
	 * @param j
	 *            the JList to refresh
	 * @param m
	 *            the default list model associated with j
	 * @param info
	 *            the String whose tokens initialize the default list model
	 */
	private void loadModel(JList<String> j, DefaultListModel<String> m,
			String info) {
		m.clear();
		if (info == null)
			return;
		StringTokenizer st = new StringTokenizer(info, "\n");
		while (st.hasMoreTokens()) {
			m.addElement(st.nextToken());
		}
		j.ensureIndexIsVisible(0);
	}

	/**
	 * Listens for actions performed then calls internal methods to fit button
	 * 
	 * @param e
	 *            the action event that was performed (button that was selected)
	 */
	public void actionPerformed(ActionEvent e) {
		if (rentalLocation.hasWaitingCustomers()
				&& rentalLocation.hasAvailableCars())
			btnRent.setEnabled(true);
		else
			btnRent.setEnabled(false);

		if (rentalLocation.hasDetailingCars())
			btnDetail.setEnabled(true);
		else
			btnDetail.setEnabled(false);

		if (rentalLocation.hasRentedCars()) {
			btnReturn.setEnabled(true);
			btnReport.setEnabled(true);
		} else {
			btnReturn.setEnabled(false);
			btnReport.setEnabled(false);
		}

		if (rentalLocation.hasRepairingCars())
			btnRepair.setEnabled(true);
		else
			btnRepair.setEnabled(false);

		if (e.getSource().equals(btnAddCustomer)) {
			AddCustomerDialog pane = new AddCustomerDialog();
			pane.setVisible(true);
			Customer v = pane.getNewCustomer();
			boolean customerWasAdded = false;
			if (v != null)
				customerWasAdded = rentalLocation.addCustomer(v);
			else
				customerWasAdded = true;
			// Ensure the customer was added, otherwise re-prompt user for
			// proper info
			while (!customerWasAdded) {
				JOptionPane.showMessageDialog(this, "Customer already exists");
				pane.setVisible(true);
				// Attempt to get a new customer
				v = pane.getNewCustomer();
				if (v != null)
					customerWasAdded = rentalLocation.addCustomer(v);
				else
					customerWasAdded = true;
			}

			// Determine which buttons should be enabled or disabled when
			// setting up
			// GUI
			if (rentalLocation.hasWaitingCustomers()
					&& rentalLocation.hasAvailableCars())
				btnRent.setEnabled(true);
			else
				btnRent.setEnabled(false);

		} else if (e.getSource().equals(btnAddCar)) {
			AddCarDialog pane = new AddCarDialog();
			pane.setVisible(true);
			Car v = pane.getNewCar();
			boolean carWasAdded = false;
			if (v != null)
				carWasAdded = rentalLocation.addCar(v);
			else
				carWasAdded = true;
			// Ensure the car was added, otherwise re-prompt user for proper
			// info
			while (!carWasAdded) {
				JOptionPane.showMessageDialog(this, "Car already exists");
				pane.setVisible(true);
				// Attempt to get a new car
				v = pane.getNewCar();
				if (v != null)
					carWasAdded = rentalLocation.addCar(v);
				else
					carWasAdded = true;
			}

			// Determine which buttons should be enabled or disabled when
			// setting up
			// GUI
			if (rentalLocation.hasWaitingCustomers()
					&& rentalLocation.hasAvailableCars())
				btnRent.setEnabled(true);
			else
				btnRent.setEnabled(false);

		} else if (e.getSource().equals(btnRent)) {
			doRent();
			if (rentalLocation.hasWaitingCustomers()
					&& rentalLocation.hasAvailableCars())
				btnRent.setEnabled(true);
			else
				btnRent.setEnabled(false);
			if (rentalLocation.hasRentedCars()) {
				btnReturn.setEnabled(true);
				btnReport.setEnabled(true);
			} else {
				btnReturn.setEnabled(false);
				btnReport.setEnabled(false);
			}

		} else if (e.getSource().equals(btnReturn)) {
			doReturn();
			if (rentalLocation.hasRentedCars()) {
				btnReturn.setEnabled(true);
				btnReport.setEnabled(true);
			} else {
				btnReturn.setEnabled(false);
				btnReport.setEnabled(false);
			}

			if (rentalLocation.hasDetailingCars())
				btnDetail.setEnabled(true);
			else
				btnDetail.setEnabled(false);

			if (rentalLocation.hasRepairingCars())
				btnRepair.setEnabled(true);
			else
				btnRepair.setEnabled(false);
		} else if (e.getSource().equals(btnDetail)) {
			doDetail();
			if (rentalLocation.hasDetailingCars())
				btnDetail.setEnabled(true);
			else
				btnDetail.setEnabled(false);
		} else if (e.getSource().equals(btnReport)) {
			doReport();
			if (rentalLocation.hasRentedCars()) {
				btnReturn.setEnabled(true);
				btnReport.setEnabled(true);
			} else {
				btnReturn.setEnabled(false);
				btnReport.setEnabled(false);
			}

			if (rentalLocation.hasDetailingCars())
				btnDetail.setEnabled(true);
			else
				btnDetail.setEnabled(false);

			if (rentalLocation.hasRentedCars()) {
				btnReturn.setEnabled(true);
				btnReport.setEnabled(true);
			} else {
				btnReturn.setEnabled(false);
				btnReport.setEnabled(false);
			}

			if (rentalLocation.hasRepairingCars())
				btnRepair.setEnabled(true);
			else
				btnRepair.setEnabled(false);
		} else if (e.getSource().equals(btnRepair)) {
			doRepair();
			if (rentalLocation.hasRepairingCars())
				btnRepair.setEnabled(true);
			else
				btnRepair.setEnabled(false);
			if (rentalLocation.hasDetailingCars())
				btnDetail.setEnabled(true);
			else
				btnDetail.setEnabled(false);
		}

		loadModel(lstCustomers, dlmCustomers, rentalLocation.customersWaiting());
		loadModel(lstAvailable, dlmAvailable, rentalLocation.availableCars());
		loadModel(lstRented, dlmRented, rentalLocation.rentedCars());
		loadModel(lstDetailing, dlmDetailing, rentalLocation.detailingCars());
		loadModel(lstRepairing, dlmRepairing, rentalLocation.repairingCars());
	}

	/**
	 * Rent button method
	 */
	public void doRent() {
		rentalLocation.rentCar();
	}

	/**
	 * Return button method
	 */
	public void doReturn() {
		rentalLocation.returnCar();
	}

	/**
	 * Detail button method
	 */
	public void doDetail() {
		rentalLocation.processDetailed();
	}

	/**
	 * Report button method
	 */
	public void doReport() {
		rentalLocation.reportProblem();
	}

	/**
	 * Repair button method
	 */
	public void doRepair() {
		rentalLocation.completeRepairs();
	}

	/**
	 * Startup method for the application. Creates the GUI.
	 * 
	 * @param args
	 *            args[0] is the name of the input text file.
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
			new CarRentalGUI(args[0]);
		} else
			new CarRentalGUI(null);
	}
}