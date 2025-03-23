package org.unibl.etf.epj2.epj2simulation;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.unibl.etf.epj2.gui.ShowDailyReportFrame;
import org.unibl.etf.epj2.gui.ShowDeserializedVehiclesFrame;
import org.unibl.etf.epj2.gui.ShowMalfunctionsFrame;
import org.unibl.etf.epj2.gui.ShowSummaryReportFrame;
import org.unibl.etf.epj2.gui.ShowVehiclesFrame;
import org.unibl.etf.epj2.rentals.ReadRentals;
import org.unibl.etf.epj2.rentals.Rental;
import org.unibl.etf.epj2.rentals.RentalCalculator;
import org.unibl.etf.epj2.serialization.VehicleSerialization;
import org.unibl.etf.epj2.vehicles.ReadVehicles;
import org.unibl.etf.epj2.vehicles.Vehicle;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * The main GUI class for the EPJ2 simulation application.
 * This class creates and displays the main frame of the application with menu options for displaying vehicle data,
 * malfunction data, business reports and deserialized vehicles. It also includes a button to start the simulation.
 * 
 * @author Tamara Kosovac
 */
public class Epj2SimulationGui extends JFrame {
	private static final long serialVersionUID = 1L;

	/**
	 * The main frame of the application.
	 */
	private JFrame frame;

	/**
	 * Entry point of the application. It initializes and displays the main frame.
	 *  
	 * @param args  Command line arguments.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Epj2SimulationGui frame = new Epj2SimulationGui();
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Constructs the main GUI for the EPJ2 simulation application.
     * Initializes the frame and sets up the menu and buttons.
	 */
	public Epj2SimulationGui() {
		initialize();
	}
	

	/**
	 * Initializes the components of the frame, including setting up the menu bar and buttons.
     * Reads vehicle and rental data, and sets up action listeners for menu items and buttons.
	 */
	private void initialize() {
        Set<Vehicle> vehicles = ReadVehicles.readVehicles();
        List<Rental> rentals = ReadRentals.readRentals(vehicles, null);
   		RentalCalculator.writeInPropertieFile(rentals);
		frame = new JFrame("ePJ2");
		frame.setBounds(150, 20, 20, 150);
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 976, 22);
		frame.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Vozila");
		mnNewMenu.setForeground(Color.WHITE);
		mnNewMenu.setOpaque(true);
		mnNewMenu.setBackground(Color.DARK_GRAY);
		mnNewMenu.setFont(new Font("Serif", Font.BOLD, 16));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Prikazi vozila");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowVehiclesFrame showVehiclesFrame = new ShowVehiclesFrame(vehicles);
				showVehiclesFrame.setVisible(true);
			}
		});
		mntmNewMenuItem.setForeground(Color.WHITE);
		mntmNewMenuItem.setBackground(Color.DARK_GRAY);
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem.setFont(new Font("Serif", Font.BOLD, 14));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu1 = new JMenu("Kvarovi");
		mnNewMenu1.setForeground(Color.WHITE);
		mnNewMenu1.setOpaque(true);
		mnNewMenu1.setBackground(Color.DARK_GRAY);
		mnNewMenu1.setFont(new Font("Serif", Font.BOLD, 16));
		menuBar.add(mnNewMenu1);
		
		JMenuItem mntmNewMenuItem1 = new JMenuItem("Prikazi kvarove");
		mntmNewMenuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Rental> rentalsWithMalfunctions = rentals.stream()
			            .filter(rental -> rental.getMalfunction() != null && 
			                    ReadRentals.YES.equals(rental.getMalfunction().getHasMalfunction()))
			            .collect(Collectors.toList());
				ShowMalfunctionsFrame showMalfunctionFrame = new ShowMalfunctionsFrame(rentalsWithMalfunctions);
				showMalfunctionFrame.setVisible(true);
			}
		});
		mntmNewMenuItem1.setForeground(Color.WHITE);
		mntmNewMenuItem1.setBackground(Color.DARK_GRAY);
		mntmNewMenuItem1.setFont(new Font("Serif", Font.BOLD, 14));
		mnNewMenu1.add(mntmNewMenuItem1);
		
		JMenu mnNewMenu2 = new JMenu("Rezultati poslovanja");
		mnNewMenu2.setForeground(Color.WHITE);
		mnNewMenu2.setOpaque(true);
		mnNewMenu2.setBackground(Color.DARK_GRAY);
		mnNewMenu2.setFont(new Font("Serif", Font.BOLD, 16));
		menuBar.add(mnNewMenu2);
		
		JMenuItem mntmNewMenuItem2 = new JMenuItem("Prikazi sumarni izvjestaj");
		mntmNewMenuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowSummaryReportFrame showSummaryReportFrame = new ShowSummaryReportFrame();
				showSummaryReportFrame.setVisible(true);
			}
		});
		mntmNewMenuItem2.setFont(new Font("Serif", Font.BOLD, 14));
		mntmNewMenuItem2.setForeground(Color.WHITE);
		mntmNewMenuItem2.setBackground(Color.DARK_GRAY);
		mnNewMenu2.add(mntmNewMenuItem2);
		
		JMenuItem mntmNewMenuItem3 = new JMenuItem("Prikazi dnevni izvjestaj");
		mntmNewMenuItem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowDailyReportFrame showDailyReportFrame = new ShowDailyReportFrame();
				showDailyReportFrame.setVisible(true);
			}
		});
		mntmNewMenuItem3.setForeground(Color.WHITE);
		mntmNewMenuItem3.setBackground(Color.DARK_GRAY);
		mntmNewMenuItem3.setFont(new Font("Serif", Font.BOLD, 14));
		mnNewMenu2.add(mntmNewMenuItem3);
		
		JMenu mnNewMenu3 = new JMenu("Deserijalizovana vozila");
		mnNewMenu3.setForeground(Color.WHITE);
		mnNewMenu3.setOpaque(true);
		mnNewMenu3.setBackground(Color.DARK_GRAY);
		mnNewMenu3.setFont(new Font("Serif", Font.BOLD, 16));
		menuBar.add(mnNewMenu3);
		
		JMenuItem mntmNewMenuItem4 = new JMenuItem("Prikazi deserijalizovana vozila");
		mntmNewMenuItem4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Vehicle> damagedVehicles = VehicleSerialization.deserializeVehicle();
				ShowDeserializedVehiclesFrame showDeserializedVehiclesFrame = new ShowDeserializedVehiclesFrame(damagedVehicles);
				showDeserializedVehiclesFrame.setVisible(true);
			}
		});
		mntmNewMenuItem4.setForeground(Color.WHITE);
		mntmNewMenuItem4.setBackground(Color.DARK_GRAY);
		mntmNewMenuItem4.setFont(new Font("Serif", Font.BOLD, 14));
		mnNewMenu3.add(mntmNewMenuItem4);
		
		JButton btnNewButton = new JButton("Pokreni simulaciju");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simulationOfRentals(vehicles);
			}
		});
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Serif", Font.BOLD, 14));
		btnNewButton.setBounds(414, 373, 155, 34);
		frame.getContentPane().add(btnNewButton);
		
		frame.setVisible(true);
	}
	
	/**
	 * Starts the simulation of rentals based on the provided vehicles.
     * The simulation processes rentals and updates the main frame with the simulation status.
     * 
	 * @param vehicles  A set of vehicles to be used in the simulation.
	 */
	public void simulationOfRentals(Set<Vehicle> vehicles) {
		SwingUtilities.invokeLater(() -> {
	        MainFrame mainFrame = new MainFrame();
	        List<Rental> rentals = ReadRentals.readRentals(vehicles, mainFrame);
	   		RentalCalculator.writeInPropertieFile(rentals);
	   		Map<String, List<Rental>> rentalsByDateTime = new LinkedHashMap<>();
	        for (Rental rental : rentals) {
	        String dateTime = rental.getRentalDateTime();
	        rentalsByDateTime
	           .computeIfAbsent(dateTime, k -> new ArrayList<>())
	           .add(rental);   
	        }
	        new Thread(() -> {
	         for (Map.Entry<String, List<Rental>> entry : rentalsByDateTime.entrySet()) {
	             List<Rental> rentalsWithSameDateAndTime = entry.getValue();
	             CountDownLatch latch = new CountDownLatch(rentalsWithSameDateAndTime.size());
	             for (Rental rental : rentalsWithSameDateAndTime) {
	            	 rental.getVehicle().chargeBatteryLevel();
	                 new Thread(() -> {
	                     try {
	                    	 if(ReadRentals.YES.equals(rental.getMalfunction().getHasMalfunction())) {
	                    		 mainFrame.showContentAt(rental.getFirstStartCoordinate(), rental.getSecondStartCoordinate(), rental.getVehicle().getIdOfVehicle()+"-"+rental.getVehicle().getBatteryLevel());
	                    	 } else {
	                           rental.start();
	                           rental.join();
	                    	 }
	                     } catch (InterruptedException e) {
	                         e.printStackTrace();
	                     } finally {
	                    	 RentalCalculator.readAndCalculateFromPropertiesFile(rental);
	                         latch.countDown();
	                     }
	                 }).start();
	            	
	             }
	             try {
	                 latch.await();
	                 Thread.sleep(5000); 
	                 mainFrame.clearMatrix();
	             } catch (InterruptedException e) {
	                 e.printStackTrace();
	             }
	         }
	     }).start();
	         VehicleSerialization.serializeVehicle(rentals);
	        });
	}
}