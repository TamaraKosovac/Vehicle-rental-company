package org.unibl.etf.epj2.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.epj2.rentals.Rental;

/**
 * A JFrame that displays a table of rentals with associated malfunctions.
 * The frame organizes the content into a single panel that includes a title and a table.
 * The frame uses a BorderLayout to arrange the content, with the table displayed in the center.
 * 
 * @author Tamara Kosovac
 */
public class ShowMalfunctionsFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/**
	 * The main content pane for the frame.
	 */
	private JPanel contentPane;


	/**
	 * Constructs a new ShowMalfunctionsFrame that displays a list of rentals with malfunctions in a tabular format.
     * The frame includes a title and a scrollable table that lists all malfunctions.
     * 
     * @param rentalsWithMalfunctions  A list of rentals that have malfunctions.
     */
	public ShowMalfunctionsFrame(List<Rental> rentalsWithMalfunctions) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Prikaz kvarova");
		setBounds(140, 10, 10, 140);
		setSize(900, 700);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	    JTable malfunctionTable = ShowVehiclesFrame.createStyledTable(new MalfunctionTableModel(rentalsWithMalfunctions));
	    JPanel malfunctionPanel = new JPanel(new BorderLayout());
	    JLabel malfunctionLabel = new JLabel("Kvarovi", JLabel.CENTER);
	    malfunctionLabel.setFont(new Font("Serif", Font.BOLD, 16));
	    malfunctionPanel.add(malfunctionLabel, BorderLayout.NORTH);
	    malfunctionPanel.add(new JScrollPane(malfunctionTable), BorderLayout.CENTER);
        contentPane.add(malfunctionPanel, BorderLayout.CENTER);
	    setContentPane(contentPane);
	}

}
