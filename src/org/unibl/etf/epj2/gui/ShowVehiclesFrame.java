package org.unibl.etf.epj2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.unibl.etf.epj2.vehicles.ElectricBike;
import org.unibl.etf.epj2.vehicles.ElectricCar;
import org.unibl.etf.epj2.vehicles.ElectricScooter;
import org.unibl.etf.epj2.vehicles.Vehicle;

/**
 * A JFrame that displays a set of vehicles categorized into electric cars, bikes, and scooters.
 * It organizes the vehicles into separate tables within the frame.
 * 
 * @author Tamara Kosovac
 */
public class ShowVehiclesFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/**
	 *  The main content pane for the frame.
	 */
	private JPanel contentPane;

	/**
	 * Constructs a new ShowVehiclesFrame that displays the given set of vehicles.
	 * The vehicles are categorized into electric cars, bikes, and scooters, each displayed in its own table.
	 * 
	 * @param vehicles  The set of Vehicle objects to be displayed in the frame.
	 */
	public ShowVehiclesFrame(Set<Vehicle> vehicles) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Prikaz vozila");
		setBounds(140, 10, 10, 140);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setSize(900, 700);
		setContentPane(contentPane);
	    List<ElectricCar> cars = vehicles.stream()
		            .filter(obj -> obj instanceof ElectricCar)
		            .map(obj -> (ElectricCar) obj)
		            .collect(Collectors.toList());

		List<ElectricBike> bikes = vehicles.stream()
		            .filter(obj -> obj instanceof ElectricBike)
		            .map(obj -> (ElectricBike) obj)
		            .collect(Collectors.toList());

		List<ElectricScooter> scooters = vehicles.stream()
		            .filter(obj -> obj instanceof ElectricScooter)
		            .map(obj -> (ElectricScooter) obj)
		            .collect(Collectors.toList());

		JTable carTable = createStyledTable(new CarTableModel(cars));
		JTable bikeTable = createStyledTable(new BikeTableModel(bikes));
		JTable scooterTable = createStyledTable(new ScooterTableModel(scooters));
		    
		JPanel carPanel = new JPanel(new BorderLayout());
		JLabel carLabel = new JLabel("Elektricni automobili", JLabel.CENTER);
		carLabel.setFont(new Font("Serif", Font.BOLD, 16));
		carPanel.add(carLabel, BorderLayout.NORTH);
		carPanel.add(new JScrollPane(carTable), BorderLayout.CENTER);

	    JPanel bikePanel = new JPanel(new BorderLayout());
		JLabel bikeLabel = new JLabel("Elektricni bicikli", JLabel.CENTER);
		bikeLabel.setFont(new Font("Serif", Font.BOLD, 16));
		bikePanel.add(bikeLabel, BorderLayout.NORTH);
		bikePanel.add(new JScrollPane(bikeTable), BorderLayout.CENTER);

		JPanel scooterPanel = new JPanel(new BorderLayout());
		JLabel scooterLabel = new JLabel("Elektricni skuteri", JLabel.CENTER);
		scooterLabel.setFont(new Font("Serif", Font.BOLD, 16));
		scooterPanel.add(scooterLabel, BorderLayout.NORTH);
	    scooterPanel.add(new JScrollPane(scooterTable), BorderLayout.CENTER);
		setLayout(new GridLayout(3, 1));
		add(carPanel);
		add(bikePanel);
		add(scooterPanel);
	}
	
	
	/**
	 * Creates a styled JTable with the given table model. The table is customized
	 * with specific fonts, colors, and cell alignment to enhance its appearance.
	 * 
	 * @param model  The table model to be used by the JTable.
	 * @return A styled JTable with the specified table model.
	 */
	public static JTable createStyledTable(TableModel model) {
	    JTable table = new JTable(model);
	    table.setFont(new Font("Serif", Font.PLAIN, 14));
	    table.setRowHeight(20);
	    table.setForeground(Color.BLACK);
	    table.setBackground(Color.LIGHT_GRAY);
	    table.setGridColor(Color.GRAY);
	    table.setSelectionBackground(Color.DARK_GRAY);
	    table.setSelectionForeground(Color.WHITE);
	    JTableHeader header = table.getTableHeader();
	    header.setFont(new Font("Serif", Font.BOLD, 16));
	    header.setBackground(Color.DARK_GRAY);
	    header.setForeground(Color.WHITE);
	    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	    renderer.setHorizontalAlignment(JLabel.CENTER);
	    table.setDefaultRenderer(Object.class, renderer);
	    return table;
	}
}