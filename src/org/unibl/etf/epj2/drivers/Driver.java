package org.unibl.etf.epj2.drivers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Represents a driver with personal identification details and license information.
 * 
 * @author Tamara Kosovac
 */
public class Driver {
	/**
	 * The name of the driver.
	 */
	private String nameOfTheDriver;
	
	/**
	 * The unique identification number assigned to the driver.
	 */
	private String driverIdentificationNumber;
	
	/**
	 * The driver's license number.
	 */
	private String driverLicenseNumber;
	
	/**
	 * A map that tracks the count of drivers with the same name.
	 * <p>The key id the driver's name and the value is the number of drivers with that name.</p>
	 */
	private static final Map<String, Integer> driverCountMap = new HashMap<>();
	
	/**
	 * Creates a new driver with the specified name.
	 * <p>This constructor initializes the driver's name, generates unique identification and license
	 * numbers and updates the driver count map.</p>
	 * 
	 * @param nameOfTheDriver  The name of the driver to set.
	 */
	public Driver(String nameOfTheDriver) {
		this.nameOfTheDriver = nameOfTheDriver;
		this.driverIdentificationNumber = setDriverNumbers(8);
		this.driverLicenseNumber = setDriverNumbers(10);
		driverCountMap.put(nameOfTheDriver, driverCountMap.getOrDefault(nameOfTheDriver, 0) + 1);
	}
		
	/**
	 * Generates a random numbers for driver's identification and licens documents.
	 * 
	 * @return The new identification or license numbers of driver.
	 */
	public String setDriverNumbers(int lengthOfDriverNumber) {
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder();
		for(int i=0; i<lengthOfDriverNumber; i++) {
			int randomNumber = random.nextInt(10);
			stringBuilder.append(randomNumber);
		}
		return stringBuilder.toString();
	}

	/**
	 * Returns the name of the driver.
	 * 
	 * @return nameOfTheDriver  The name of the driver.
	 */
	public String getNameOfTheDriver() {
		return nameOfTheDriver;
	}

	/**
	 * Sets the name of the driver.
	 * 
	 * @param nameOfTheDriver  The new name of the driver to set.
	 */
	public void setNameOfTheDriver(String nameOfTheDriver) {
		this.nameOfTheDriver = nameOfTheDriver;
	}

	/**
	 * Returns the driver's identification number.
	 * 
	 * @return driverIdentificationNumber  The driver's identification number.
	 */
	public String getDriverIdentificationNumber() {
		return driverIdentificationNumber;
	}

	/**
	 * Returns the driver's license number.
	 * 
	 * @return driverLicense  The driver's license number.
	 */
	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}
	
	/**
	 * Returns a string representation of the {@code Driver} object.
	 * <p>Includes the driver's name, identification number and license number.</p>
	 * @return A string representation of the driver.
	 */
	@Override
	public String toString() {
		return "Driver's name: " + nameOfTheDriver + ", driver's identification number: " + driverIdentificationNumber + ", driver's license number: " + driverLicenseNumber + ".";
	}
	
	/**
	 * Check if this driver is equal to another object.
	 * 
	 * @param object the object to be compared
	 * @return {@code true} if this driver is equal to the specified object; {@code false} otherwise
	 */
	@Override 
	public boolean equals(Object object) {
		if(object == null) {
			return false;
		}
		if(getClass() != object.getClass()) {
			return false;
		}
		final Driver other = (Driver) object;
		if(!this.nameOfTheDriver.equals(other.nameOfTheDriver)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the hash code of this driver.
	 * <p>The hash code is based on the driver's name.</p>
	 * 
	 * @return The hash code of this driver.
	 */
	@Override
	public int hashCode() {
		return nameOfTheDriver.hashCode();
	}
	
	/**
	 * Retrieves the count of drivers with the specified name.
	 * 
	 * @param nameOfTheDriver  The name of the driver.
	 * @return The count of drivers with the specified name.
	 */
	public static int getDriverCount(String nameOfTheDriver) {
		return driverCountMap.getOrDefault(nameOfTheDriver, 0);
	}
}