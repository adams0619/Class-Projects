package edu.ncsu.csc216.carrental.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.InvalidIDException;

/**
 * GUI dialog box that appears when adding a Car
 * 
 * @author Noah Unger (ngunger@ncsu.edu)
 * 
 */
@SuppressWarnings("serial")
public class AddCarDialog extends JDialog {

	/** Border text */
	private final static String NEW_DEVICE_INFO = "New Car Information";
	/** User input fields, combo labels */
	private final static String[] INFO_LABELS = { "FleetNum", "Make", "Model",
			"Color" };
	/** Title bar text */
	private final static String TITLE_BAR = "Add New Car";
	/** Cancel button text */
	private final static String CANCEL = "Cancel";
	/** Submit button text */
	private final static String SUBMIT = "Submit";

	/** Panel with Submit and Cancel buttons */
	private JPanel pnlBtn = new JPanel();
	/** Main window panel, contains other panels */
	private JPanel pnlMain = new JPanel();
	/** Panel for laying out user input components */
	private JPanel pnlInfo = new JPanel();
	/** Constraints to lay out user input components */
	GridBagConstraints gbC = new GridBagConstraints();

	/** Submit button (when the user wants to complete the Add operation) */
	private JButton btnSubmit = new JButton(SUBMIT);
	/** Cancel button for aborting the operation */
	private JButton btnCancel = new JButton(CANCEL);

	/** User input labels */
	private JLabel[] lblInfo = new JLabel[4];
	/** Components for user input (text fields, combo) */
	private JComponent[] compInfo = new JComponent[4];

	/** Result returned on successful SUBMIT */
	private Car result = null;

	/**
	 * Constructor for dialog box
	 */
	public AddCarDialog() {
		super((java.awt.Frame) null, TITLE_BAR, true);
		setUpGeometry();
		add(pnlMain, BorderLayout.CENTER);
		add(pnlBtn, BorderLayout.SOUTH);
		pack();
	}

	/**
	 * Constructor for dialog box
	 * 
	 * @param frame
	 *            for window
	 */
	public AddCarDialog(Frame frame) {
		super((java.awt.Frame) null, TITLE_BAR, true);
		setUpGeometry();
		add(pnlMain, BorderLayout.CENTER);
		add(pnlBtn, BorderLayout.SOUTH);
		pack();
	}

	/**
	 * Private method -- sets layouts, adds panels and widgets
	 */
	private void setUpGeometry() {
		// The bottom panel will have Submit, Cancel buttons
		pnlBtn.setLayout(new FlowLayout());
		pnlBtn.add(btnSubmit);
		pnlBtn.add(btnCancel);

		// Put together the main panel
		pnlMain.setLayout(new BorderLayout());
		pnlMain.add(pnlBtn, BorderLayout.SOUTH);
		setUpInfo();
		pnlMain.add(pnlInfo, BorderLayout.CENTER);

		addActionListeners();
	}

	/**
	 * Private method. Creates the grid to lay out the labels and widgets.
	 */
	private void setUpInfo() {
		// Initialize the layout grid constraints
		pnlInfo.setLayout(new GridBagLayout());
		gbC.weightx = 0.5;
		gbC.gridx = 0;
		gbC.gridy = 0;

		// Add the other labels and widgets for user entry/selection
		for (int k = 0; k < 4; k++) {
			gbC.gridx = 0;
			gbC.gridy = 1 + k;
			lblInfo[k] = new JLabel(INFO_LABELS[k]);
			lblInfo[k].setHorizontalAlignment(SwingConstants.RIGHT);
			pnlInfo.add(lblInfo[k], gbC);
			gbC.gridx = 1;
			//
			// WHAT IS THE K<2
			//
			// if (k < 2)
			// compInfo[k] = new JTextField(20);
			// else
			// compInfo[k] = cboTier;
			compInfo[k] = new JTextField(20);
			pnlInfo.add(compInfo[k], gbC);
		}
		pnlInfo.setBorder(BorderFactory.createTitledBorder(NEW_DEVICE_INFO));
	}

	/**
	 * Adds internally defined action listeners to Submit and Cancel buttons
	 */
	private void addActionListeners() {
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// Try creating a car out of the user input. With success, close
				// the dialog.
				try {
					result = getInput();
					setVisible(false);
					dispose();
				} catch (InvalidIDException e) {
					// Without success, show a message and let the user correct
					// the errors.
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				result = null;
				setVisible(false);
				dispose();
			}
		});
	}

	/**
	 * Private method -- gets input from text fields
	 * 
	 * @return InvalidIDException for invalid info
	 */
	private Car getInput() {
		String fleetNum = ((JTextField) compInfo[0]).getText().trim();
		String make = ((JTextField) compInfo[1]).getText().trim();
		String model = ((JTextField) compInfo[2]).getText().trim();
		String color = ((JTextField) compInfo[3]).getText().trim();
		return new Car(fleetNum, make, model, color);
	}

	/**
	 * Method that returns the data from the dialog box as a Car
	 * 
	 * @return Car from input info
	 */
	public Car getNewCar() {
		return result;
	}
}
