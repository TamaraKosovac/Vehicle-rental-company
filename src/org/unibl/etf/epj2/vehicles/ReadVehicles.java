package org.unibl.etf.epj2.vehicles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.unibl.etf.epj2.rentals.RentalCalculator;

/**
 * This class is responsible for reading vehicle data from a CSV file and loading them into a set of {@link Vehicle} objects.
 * The class contains constants that represents different types of vehicles and separators used in the CSV file.
 * 
 * <p> This class utilizes the {@link RentalCalculator} class to retrieve the file path of the CSV file containing vehicle data.</p>
 * 
 * @author Tamara Kosovac
 */
public class ReadVehicles {
	
	/**
	 * The file path of the CSV file containing vehicle data.
	 * This value is retrieved from the propertie file.
	 */
	public static final String FILE_WITH_VEHICLES = RentalCalculator.returnValueFromPropertieFile("FILE_WITH_VEHICLES");
	
	/**
	 * A constant representing the identifier key in the CSV file.
	 */
	public static final String ID = "ID";
	
	/**
	 * A constant representing the string used to identify a car in the CSV file.
	 */
	public static final String CAR = "automobil";
	
	/**
	 * A constant representing the string used to identify a bike in the CSV file.
	 */
	public static final String BIKE = "bicikl";
	
	/**
	 * A constant representing the string used to identify a scooter in the CSV file.
	 */
	public static final String SCOOTER = "trotinet";
	
	/**
	 * A constant representing the separator used in the CVS file.
	 */
	public static final String SEPARATOR = ",";
	
	
	/**
	 * Static method for loading vehicles from a CSV file. The method reads the file, parses each line and 
	 * creates appropriate {@link Vehicle} object based on the vehicle type.
	 * 
	 * <p>If the vehicle type is unrecognized, the line is ignored. Duplicate vehicles (identified by the vehicle ID) 
	 * are also ignored.</p>
	 * 
	 * @return A set of {@link Vehicle} objects loaded from the CSV file.
	 */
	public static Set<Vehicle> readVehicles() {
		Path filePath = new File(FILE_WITH_VEHICLES).toPath();
		Set<Vehicle> vehicles = new HashSet<>();
		Stream<String> content;
		try {
			content = Files.lines(filePath);
			content.forEach(line -> {
				if(!line.contains(ID)) {
					String parts[] = line.split(SEPARATOR);
					if (parts.length < 9) {
	                    System.out.println("Invalid data format: " + line);
	                    return; 
	                }
					try {
						String idOfVehicle = parts[0];
						String manufacturer = parts[1];
						String model = parts[2];
						String purchaseDate = parts[3];
						int purchasePrice = Integer.parseInt(parts[4]);
						String rangePerChargeString = parts[5];
						int rangePerCharge = rangePerChargeString.isEmpty() ? 0 : Integer.parseInt(rangePerChargeString);
						String maxSpeedString = parts[6];
						int maxSpeed = maxSpeedString.isEmpty() ? 0 : Integer.parseInt(maxSpeedString);
						String description = parts[7];
						String typeOfVehicle = parts[8];
						Vehicle vehicle = null;
						switch(typeOfVehicle) {
						case CAR:
							vehicle = new ElectricCar(idOfVehicle, manufacturer, model, purchasePrice, typeOfVehicle, purchaseDate, description);
							break;
						case BIKE:
							vehicle = new ElectricBike(idOfVehicle, manufacturer, model, purchasePrice, typeOfVehicle, rangePerCharge);
							break;
						case SCOOTER:
							vehicle = new ElectricScooter(idOfVehicle, manufacturer, model, purchasePrice, typeOfVehicle, maxSpeed);
							break;
						default:
							System.out.println("Unknown vehicle type: " + typeOfVehicle);
	                        return;
						}
						
						if(vehicle != null) {
							if(vehicles.add(vehicle)) {
							} else {
								System.out.println("Vehicle " + idOfVehicle + " will be ignored, cause its duplicate.");
							}
						}
					} catch(NumberFormatException exception) {
						exception.printStackTrace();
					} catch (Exception e) {
	                    System.out.println("Unexpected error for line: " + line);
	                    e.printStackTrace();
	                }
				}
			});
		} catch(IOException exception) {
			exception.printStackTrace();
		}
		return vehicles;
	}
}