package org.unibl.etf.epj2.rentals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.unibl.etf.epj2.drivers.Driver;
import org.unibl.etf.epj2.epj2simulation.MainFrame;
import org.unibl.etf.epj2.malfunctions.Malfunction;
import org.unibl.etf.epj2.vehicles.ReadVehicles;
import org.unibl.etf.epj2.vehicles.Vehicle;


/**
 * Handles the reading and processing of rental data from a file.
 * <p>This class reads rental records from a specific file, processes them and manages driver and vehicle
 * information.</p>
 * 
 * @author Tamara Kosovac
 */
public class ReadRentals {
	/**
	 * Path to the file containing rental data.
	 * <p>The path is retrieved from the property file using the {@code RentalCalculator.returnValueFromPropertieFile} method.</p>
	 */
	public static final String FILE_WITH_RENTALS = RentalCalculator.returnValueFromPropertieFile("FILE_WITH_RENTALS");
	
	/**
	 * Header for the date column in the rental data file.
	 */
	public static final String DATE = "Datum";
	
	/**
	 * Indicator for whether a malfunction exists.
	 */
	public static final String YES = "da";
	
	/**
	 * Starting coordinate value for validation.
	 */
	public static final int START = 0;
	
    /**
     * Finishing coordinate value for validation.
     */
	public static final int FINISH = 19;
	
	/**
	 * Indicates if the method has been called for the first time
	 */
	private static boolean firstCall = true;


	/**
	 * Check if the rental reading is being called for the first time.
	 * 
	 * @return {@code true} if it is the first call; {@code false} otherwise
	 */
	public static boolean isFirstCall() {
		return firstCall;
	}

	/**
	 * Sets the flag indicating whether the method is being called for the first time.
	 * 
	 * @param firstCall {@code true} if it is the first call; {@code false} otherwise
	 */
	public static void setFirstCall(boolean firstCall) {
		ReadRentals.firstCall = firstCall;
	}

	/**
	 * Reads rental data from the file, processes it, and returns a list of sorted rentals.
     * <p>This method reads each line from the rental data file, parses the information, creates rental objects, and ensures that no duplicate rentals are added. </p>
     * 
	 * @param vehicles        The set of available vehicles.
	 * @param mainFrame       The main frame of the application.
	 * @return sortedRentals  A list of sorted rental objects.
	 */
	public static List<Rental> readRentals(Set<Vehicle> vehicles, MainFrame mainFrame) {
		Path filePath = new File(FILE_WITH_RENTALS).toPath();
		Stream<String> content;
		List<Rental> rentals = new ArrayList<>();
		List<Rental> sortedRentals = new ArrayList<>();
		List<Driver> drivers = new ArrayList<>();
		try {
			content = Files.lines(filePath);
			content.forEach(line -> {
				if(!line.contains(DATE)) {
					String[] parts = line.split(ReadVehicles.SEPARATOR);
					try {
						String rentalDateTime = parts[0];
			    		String nameOfTheDriver = parts[1];
			    		String idOfVehicle = parts[2];
			    		String firstStartCoordinateString = parts[3].replace("\"", "");
			            String secondStartCoordinateString = parts[4].replace("\"", "");
			            String firstFinishCoordinateString = parts[5].replace("\"", "");
			            String secondFinishCoordinateString = parts[6].replace("\"", "");
			            int firstStartCoordinate = Integer.parseInt(firstStartCoordinateString);
			            int secondStartCoordinate = Integer.parseInt(secondStartCoordinateString);
			            int firstFinishCoordinate = Integer.parseInt(firstFinishCoordinateString);
			            int secondFinishCoordinate = Integer.parseInt(secondFinishCoordinateString);
			            int usageDurationInSeconds = Integer.parseInt(parts[7]);
			            String hasMalfunction = parts[8];
			            Malfunction malfunction = null;
			            if(YES.equals(hasMalfunction)) {
			            	malfunction = new Malfunction(hasMalfunction, rentalDateTime);
			            } else {
			            	malfunction = new Malfunction(hasMalfunction);
			            }
			            String promotion = parts[9];
			            Driver driver = ReadRentals.findOrCreateDriver(drivers, nameOfTheDriver);
			            Optional<Vehicle> existingVehicle = vehicles.stream()
			            	    .filter(vehicle -> vehicle.getIdOfVehicle().equals(idOfVehicle))
			            	    .findFirst();
			            boolean rentalExists = rentals.stream()
			            		.anyMatch(rent -> rent.getVehicle().getIdOfVehicle().equals(idOfVehicle) && 
			            		rent.getRentalDateTime().equals(rentalDateTime) && checkIfCoordinatesAreCorrect(firstStartCoordinate, secondStartCoordinate, firstFinishCoordinate, secondFinishCoordinate));
			            if(existingVehicle.isPresent() && !rentalExists) {
			            	Vehicle vehicle = existingVehicle.get();
			            	Rental rental = new Rental(rentalDateTime, driver, vehicle, firstStartCoordinate, secondStartCoordinate, firstFinishCoordinate, secondFinishCoordinate, usageDurationInSeconds, malfunction, promotion, mainFrame);
			                rentals.add(rental);
			                } else {
			                	if(firstCall) {
			                	  System.out.println("Rental for vehicle with id " + idOfVehicle + " in " + rentalDateTime + " already exist.");
			                	}
			                }
					}  catch(NumberFormatException exception) {
						exception.printStackTrace();
					} catch (Exception e) {
	                    System.out.println("Unexpected error for line: " + line);
	                    e.printStackTrace();
	                }
				}
			});
			sortedRentals = rentals.stream()
			              .sorted((firstRental, secondRental) -> firstRental.getRentalDateTime().compareTo(secondRental.getRentalDateTime()))
			              .collect(Collectors.toList());
		} catch(IOException exception) {
			exception.printStackTrace();
		}
		firstCall = false;
		return sortedRentals;
	}
	
	/**
	 * 
	 * @param drivers          The list of existing drivers.
	 * @param nameOfTheDriver  The name of the driver to find or create.
	 * @return driver          The found or newly created driver.
	 */
	public static Driver findOrCreateDriver(List<Driver> drivers, String nameOfTheDriver) {
		Optional<Driver> existingDriver = drivers.stream()
				.filter(driver -> driver.getNameOfTheDriver().equals(nameOfTheDriver)).findFirst();
		if(existingDriver.isPresent()) {
			return existingDriver.get();
		}
		Driver driver = new Driver(nameOfTheDriver);
		drivers.add(driver);
		return driver;
	}
	
	/**
	 * Checks if the given coordinates are within the valid range.
	 * 
	 * @param firstStartCoordinate    The first start coordinate.
	 * @param secondStartCoordinate   The second start coordinate.
	 * @param firstFinishCoordinate   The first finish coordinate.
	 * @param secondFinishCoordinate  The second finish coordinate.
	 * @return {@code true} if all coordinates are within range; {@code false} otherwise
	 */
	public static boolean checkIfCoordinatesAreCorrect(int firstStartCoordinate, int secondStartCoordinate, int firstFinishCoordinate, int secondFinishCoordinate) {
		return isValidCoordinateWithinRange(firstStartCoordinate) && isValidCoordinateWithinRange(secondStartCoordinate) &&
		           isValidCoordinateWithinRange(firstFinishCoordinate) && isValidCoordinateWithinRange(secondFinishCoordinate);
	}
	
	/**
	 * Validates if the coordinate is within the acceptable range.
	 * 
	 * @param coordinate  The coordinate to validate.
	 * @return {@code true} if the coordinate is within the valid range; {@code false} otherwise
	 */
	static boolean isValidCoordinateWithinRange(int coordinate) {
		if(coordinate >= START && coordinate <= FINISH) {
		   return true;
		} else {
			System.out.println("Coordinates aren't within range.");
			return false;
		}
    }
}