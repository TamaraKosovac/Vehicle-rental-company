package org.unibl.etf.epj2.rentals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import org.unibl.etf.epj2.drivers.Driver;
import org.unibl.etf.epj2.vehicles.ReadVehicles;


/** The {@code RentalCalculator} class provides functionality for calculating and managing rental prices.
 * It reads and writes property files related to rental calculations, handles pricing based on vehicle types
 * and writes rental details to text files.
 * 
 * @author Tamara Kosovac
 */
public class RentalCalculator {
	/**
	 * The default file name for properties values.
	 */
	public static final String FILE_WITH_VALUES = "values";
	
	/**
	 * The folder name from where bills will be stored.
	 */
	public static final String FOLDER_NAME = RentalCalculator.returnValueFromPropertieFile("FOLDER_NAME");
	
	/**
	 * The prefix used for identifying car-related values.
	 */
	public static final String CAR_FIRST_LETTER = "A";
	
	/**
	 * The prefix used for identifying bike-related values.
	 */
	public static final String BIKE_FIRST_LETTER = "B";
	
	/**
	 * The key for car unit price in the properties file.
	 */
	public static final String CAR_UNIT_PRICE = "CAR_UNIT_PRICE";
	
	/**
	 * The key for bike unit price in the properties file.
	 */
	public static final String BIKE_UNIT_PRICE = "BIKE_UNIT_PRICE";
	
	/**
	 * The key for scooter unit price in the properties file.
	 */
	public static final String SCOOTER_UNIT_PRICE = "SCOOTER_UNIT_PRICE";
	
	/**
	 * The extension for properties files.
	 */
	public static final String EXTENSION = ".properties";
	
	/**
	 * The extension for the text files.
	 */
	public static final String TXT = ".txt";
	
	/**
	 * The key for narrow distance in the properties file.
	 */
	public static final String DISTANCE_NARROW = "DISTANCE_NARROW";
	
	/**
	 * The key for wide distance in the properties file.
	 */
	public static final String DISTANCE_WIDE = "DISTANCE_WIDE";
	
	/**
	 * The key for discount in the properties file.
	 */
	public static final String DISCOUNT = "DISCOUNT";
	
	/**
	 * The key for discount prom in the properties file.
	 */
	public static final String DISCOUNT_PROM = "DISCOUNT_PROM";
	
	/**
	 * The key for folder path in the properties file.
	 */
	public static final String FOLDER_PATH = "FOLDER_PATH";
	
	/**
	 * The count of drivers required for discount calculations.
	 */
	public static final int DRIVER_COUNT = 10;
	
	/**
	 * The minimum coordinate value for validation.
	 */
	public static final int MIN = 5;
	
	/**
	 *  The maximum coordinate value for validation.
	 */
	public static final int MAX = 14;
	

	/**
	 * Writes rental data into property files based on the provided list of rentals.
	 * 
	 * @param rentals  The list of rentals to be written to property file.
	 */
	public static void writeInPropertieFile(List<Rental> rentals) {
		String nameOfFolder = FOLDER_NAME;
		rentals.stream().forEach(rental -> 
        {
			try {
				writeForRentalInPropertiesFile(rental, nameOfFolder);
			} catch (FileNotFoundException exception) {
				exception.printStackTrace();
			}
		});
	}
	
 
	/**
	 * Write rentals details into a properties file based on the rental information.
	 * 
	 * @param rental  The rental to be written.
	 * @param nameOfFolder  The folder where bills will be stored.
	 * @throws FileNotFoundException  If the file cannot be found.
	 */
	private static void writeForRentalInPropertiesFile(Rental rental, String nameOfFolder) throws FileNotFoundException {
	    String typeOfVehicle = rental.getVehicle().getTypeOfVehicle();
	    String vehicleUnitPrice = String.valueOf(rental.getVehicle().getPurchasePrice());
		int firstStartCoordinate = rental.getFirstStartCoordinate();
		int secondStartCoordinate = rental.getSecondStartCoordinate();
		int firstFinishCoordinate = rental.getFirstFinishCoordinate();
		int secondFinishCoordinate = rental.getSecondFinishCoordinate();
		String fileName = rental.getIdOfRental().replace(":", "_") + EXTENSION;
		String txtFilePath = nameOfFolder + File.separator + fileName.replace(EXTENSION, "")  + TXT;
		if(checkIfIsDistanceNarrow(firstStartCoordinate, secondStartCoordinate, firstFinishCoordinate, secondFinishCoordinate)) {
			 addOrUpdatePropertiesFile(DISTANCE_NARROW, returnValueFromPropertieFile(DISTANCE_NARROW), fileName);
		 } else {
			 addOrUpdatePropertiesFile(DISTANCE_WIDE, returnValueFromPropertieFile(DISTANCE_WIDE), fileName);
		 }
		 addOrUpdatePropertiesFile(DISCOUNT, returnValueFromPropertieFile(DISCOUNT), fileName);
		 addOrUpdatePropertiesFile(DISCOUNT_PROM, returnValueFromPropertieFile(DISCOUNT_PROM), fileName);
	    if(ReadVehicles.CAR.equals(typeOfVehicle)) {
			addOrUpdatePropertiesFile(CAR_UNIT_PRICE, vehicleUnitPrice, fileName);
		} else if(ReadVehicles.BIKE.equals(typeOfVehicle)) {
			addOrUpdatePropertiesFile(BIKE_UNIT_PRICE, vehicleUnitPrice, fileName);
		} else {
			addOrUpdatePropertiesFile(SCOOTER_UNIT_PRICE, vehicleUnitPrice, fileName);
		}
	    addOrUpdatePropertiesFile(FOLDER_PATH, txtFilePath, fileName);
	}
	
	
	/**
	 * Adds or updates a property in a properties file.
	 * 
	 * @param key  The key for the property to be set.
	 * @param value  The value to be set for the property
	 * @param fileName The name of te property file.
	 */
	private static void addOrUpdatePropertiesFile(String key, String value, String fileName) {
		Properties properties = new Properties();
		File file = new File(fileName);
		if(file.exists()) {
			try {
				FileInputStream fileInputStream = new FileInputStream(file); 
	            properties.load(fileInputStream);
	            fileInputStream.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			properties.setProperty(key, value);
			try {
				properties.store(fileOutputStream, null);
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch(FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}
	
	
	
	/**
	 * Checks if the coordinates represent a narrow distance area.
	 * @param firstStartCoordinate   The starting coordinate of the first axis.
	 * @param secondStartCoordinate  The starting coordinate of the second axis.
	 * @param firstFinishCoordinate  The finishing coordinate of the first axis.
	 * @param secondFinishCoordinate The finishing coordinate of the second axis.
	 * @return {@code true} if the coordinates represent a narrow distance, {@code false} otherwise
	 */
	private static boolean checkIfIsDistanceNarrow(int firstStartCoordinate, int secondStartCoordinate, int firstFinishCoordinate, int secondFinishCoordinate) {
		
		return isValidCoordinate(firstStartCoordinate) && isValidCoordinate(secondStartCoordinate) &&
		           isValidCoordinate(firstFinishCoordinate) && isValidCoordinate(secondFinishCoordinate);
	}
	
	
	/**
	 * Checks if a coordinate value is within the valid range.
	 * 
	 * @param coordinate  The coordinate value to be checked.
	 * @return  {@code true} if the coordinate is within range, {@code false} otherwise
	 */
	private static boolean isValidCoordinate(int coordinate) {
         return coordinate >= MIN && coordinate <= MAX;
    }
	
	
	/**
	 * Reads and calculates the rental price based on properties file values.
	 * 
	 * @param rental  The rental for which the price is to be calculated.
	 * @return  The total price of the rental.
	 */
	public static double readAndCalculateFromPropertiesFile(Rental rental) {
		double totalPrice = 0.0;
		Properties properties = new Properties();
		String fileName = rental.getIdOfRental().replace(":", "_") + EXTENSION;
		File file = new File(fileName);
		if (file.exists()) {
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				try {
					int unitPrice;
					double distance;
					int usageDurationInSeconds = rental.getUsageDurationInSeconds();
					double discount;
					double discountProm;
					properties.load(fileInputStream);
					if(fileName.contains(CAR_FIRST_LETTER)) {
						unitPrice = Integer.parseInt(properties.getProperty(CAR_UNIT_PRICE));
					} else if(fileName.contains(BIKE_FIRST_LETTER)) {
						unitPrice = Integer.parseInt(properties.getProperty(BIKE_UNIT_PRICE));
					} else {
						unitPrice = Integer.parseInt(properties.getProperty(SCOOTER_UNIT_PRICE));
					} 
					
					if(checkIfIsDistanceNarrow(rental.getFirstStartCoordinate(), rental.getSecondStartCoordinate(), rental.getFirstFinishCoordinate(), rental.getSecondFinishCoordinate())) {
			            distance = Double.parseDouble(properties.getProperty(DISTANCE_NARROW));
					} else {
					    distance = Double.parseDouble(properties.getProperty(DISTANCE_WIDE));
					}
					if(Driver.getDriverCount(rental.getDriver().getNameOfTheDriver()) == DRIVER_COUNT) {
						discount = Double.parseDouble(properties.getProperty(DISCOUNT));
					} else {
						discount = 0.0;
					}
					if(ReadRentals.YES.equals(rental.getPromotion())) {
					    discountProm = Double.parseDouble(properties.getProperty(DISCOUNT_PROM));
					} else {
						discountProm = 0.0;
					}
					String nameOfFolder = FOLDER_NAME;
					File folder = new File(nameOfFolder);
					folder.mkdirs();
					totalPrice = calculatePrices(unitPrice, usageDurationInSeconds, distance, discount, discountProm);
					if(ReadRentals.YES.equals(rental.getMalfunction().getHasMalfunction())) {
						totalPrice = 0.0;
					} 
					RentalCalculator.writeInTxtFile(rental, folder, fileName, unitPrice, usageDurationInSeconds, discount, discountProm, totalPrice);
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return totalPrice;
	}
	
	
	/**
	 * Writes rental information to a text file based on the provided rental details. 
	 * 
	 * @param rental    The Rental object containing details of the rental.
	 * @param folder    The folder where text files will be stored.
	 * @param fileName  The name of the propertie file from where we read information.
	 * @param unitPrice  The unit price of the vehicle.
	 * @param usageDurationInSeconds  The usage duration of the rental in seconds.
	 * @param discount  The discount of the rental.
	 * @param discountProm  The discount prom of the rental.
	 * @param totalPrice  The total price of the rental.
	 */
	private static void writeInTxtFile(Rental rental, File folder, String fileName, int unitPrice, int usageDurationInSeconds, double discount, double discountProm, double totalPrice) {
		try {
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(folder + File.separator + fileName.replace(EXTENSION, "") + TXT)));
			writer.println("Datum iznajmljivanja: " + rental.getRentalDateTime());
			writer.println("Korisnik koji iznajmljuje: " + rental.getDriver().getNameOfTheDriver());
			writer.println("ID vozila je: " + rental.getVehicle().getIdOfVehicle());
			writer.println("Cijena nabavke vozila je: " + unitPrice);
			writer.println("Pocetna lokacija je: " + rental.getFirstStartCoordinate() + "," + rental.getSecondStartCoordinate());
			writer.println("Odredisna lokacija je: " + rental.getFirstFinishCoordinate() + "," + rental.getSecondFinishCoordinate());
			writer.println("Trajanje je: " + usageDurationInSeconds);
			writer.println("Da li ima kvar? " + rental.getMalfunction().getHasMalfunction());
			writer.println("Da li ima promociju? " + rental.getPromotion());
			writer.println("Popust iznosi: " + discount);
			writer.println("Promocija iznosi: " + discountProm);
			writer.println("Ukupna cijena za placanje je: " + totalPrice);
			writer.println("Da li je voznja u uzem dijelu grada: " + checkIfIsDistanceNarrow(rental.getFirstStartCoordinate(), rental.getSecondStartCoordinate(), rental.getFirstFinishCoordinate(), rental.getSecondFinishCoordinate()));
			writer.close();
		} catch(IOException exception) {
			exception.printStackTrace();
		}

	}
	
	
	/**
	 * Calculates the rental price based on unit price, usage duration, distance,
	 * applicable discounts and promotions.
	 * 
	 * @param unitPrice  The unit price of the vehicle.
	 * @param usageDurationInSeconds  The duration of the rental in seconds.
	 * @param distance  The distance which depends on narrow or wide city valute.
	 * @param discount  The discount applicable to the rental.
	 * @param discountProm  The promotional discount applicable to the rental.
	 * @return The total price for the rental after applying discounts and promotions.
	 */
	private static double calculatePrices(int unitPrice, int usageDurationInSeconds, double distance, double discount, double discountProm) {
		double price = (unitPrice * usageDurationInSeconds) * distance;
		double totalPrice = price - ((discount/100) * price) - ((discountProm/100) * price);
		return totalPrice;
	}
	
	
	/**
	 * Retrieves the value associated with a specific key from a default property file.
	 * 
	 * @param key  The key whos associated value is to be returned.
	 * @return  The value associated with the specified key, or an empty string if the key is not found.
	 */
	public static String returnValueFromPropertieFile(String key) {
		Properties properties = new Properties();
		File file = new File(FILE_WITH_VALUES + EXTENSION);
		String value = "";
		if(file.exists()) {
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				try {
					properties.load(fileInputStream);
					value = properties.getProperty(key);
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	

}