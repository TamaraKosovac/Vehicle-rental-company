package org.unibl.etf.epj2.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.epj2.vehicles.ElectricBike;
import org.unibl.etf.epj2.vehicles.ElectricCar;
import org.unibl.etf.epj2.vehicles.ElectricScooter;
import org.unibl.etf.epj2.vehicles.Vehicle;

/**
 * A JFrame that displays deserialized damaged vehicles, electric cars, bikes, and scooters.
 * The frame organizes the vehicles into three separate tables, each displaying a specific type of electric vehicle.
 * The frame uses a BorderLayout to arrange the content, with a GridLayout managing the panels for each vehicle type.
 * 
 * @author Tamara Kosovac
 */
public class ShowDeserializedVehiclesFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/**
	 * The main content pane for the frame.
	 */
	private JPanel contentPane;


	/**
	 * Constructs a new ShowDeserializedVehiclesFrame that displays damaged electric vehicles in a tabular format.
     * The vehicles are grouped by type (ElectricCar, ElectricBike, ElectricScooter) and shown in separate tables.
     * 
     * @param damagedVehicles  A list of deserialized vehicles that are damaged.
	 */
	public ShowDeserializedVehiclesFrame(List<Vehicle> damagedVehicles) {
		setTitle("Prikaz deserijalizovanih vozila");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(140, 10, 10, 140);
		setSize(900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		List<ElectricCar> cars = damagedVehicles.stream()
		            .filter(obj -> obj instanceof ElectricCar)
		            .map(obj -> (ElectricCar) obj)
		            .collect(Collectors.toList());

		List<ElectricBike> bikes = damagedVehicles.stream()
		            .filter(obj -> obj instanceof ElectricBike)
		            .map(obj -> (ElectricBike) obj)
		            .collect(Collectors.toList());

		 List<ElectricScooter> scooters = damagedVehicles.stream()
		            .filter(obj -> obj instanceof ElectricScooter)
		            .map(obj -> (ElectricScooter) obj)
		            .collect(Collectors.toList());
		    JTable carTable = ShowVehiclesFrame.createStyledTable(new CarTableModelForDeserialization(cars));
		    JTable bikeTable = ShowVehiclesFrame.createStyledTable(new BikeTableModelForDeserialization(bikes));
		    JTable scooterTable = ShowVehiclesFrame.createStyledTable(new ScooterTableModelForDeserialization(scooters));
		    JPanel carPanel = new JPanel(new BorderLayout());
		    JLabel carLabel = new JLabel("Pokvareni elektricni automobili", JLabel.CENTER);
		    carLabel.setFont(new Font("Serif", Font.BOLD, 16));
		    carPanel.add(carLabel, BorderLayout.NORTH);
		    carPanel.add(new JScrollPane(carTable), BorderLayout.CENTER);
		    JPanel bikePanel = new JPanel(new BorderLayout());
		    JLabel bikeLabel = new JLabel("Pokvareni elektricni bicikli", JLabel.CENTER);
		    bikeLabel.setFont(new Font("Serif", Font.BOLD, 16));
		    bikePanel.add(bikeLabel, BorderLayout.NORTH);
		    bikePanel.add(new JScrollPane(bikeTable), BorderLayout.CENTER);
		    JPanel scooterPanel = new JPanel(new BorderLayout());
		    JLabel scooterLabel = new JLabel("Pokvareni elektricni skuteri", JLabel.CENTER);
		    scooterLabel.setFont(new Font("Serif", Font.BOLD, 16));
		    scooterPanel.add(scooterLabel, BorderLayout.NORTH);
		    scooterPanel.add(new JScrollPane(scooterTable), BorderLayout.CENTER);
		    setLayout(new BorderLayout());
		    JPanel vehiclePanels = new JPanel(new GridLayout(3, 1));
		    vehiclePanels.add(carPanel);
		    vehiclePanels.add(bikePanel);
		    vehiclePanels.add(scooterPanel);
		    add(vehiclePanels, BorderLayout.CENTER); 
	}
}
