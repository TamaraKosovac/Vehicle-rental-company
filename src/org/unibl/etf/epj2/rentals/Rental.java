package org.unibl.etf.epj2.rentals;

import org.unibl.etf.epj2.malfunctions.Malfunction;

import org.unibl.etf.epj2.drivers.Driver;
import org.unibl.etf.epj2.epj2simulation.MainFrame;
//import org.unibl.etf.epj2.epj2simulation.Epj2Simulation;
import org.unibl.etf.epj2.vehicles.Vehicle;


/**
 * Represents a rental transaction, extending 'Thread' to allow simulation of vehicle movement.
 * Manages rental details, including driver, vehicle, coordinates, malfunction, promotion and duration.
 * 
 * @author Tamara Kosovac
 */
public class Rental extends Thread {
	/**
	 * Represents the date and time when the rental was created.
	 */
	private String rentalDateTime;
	
	/**
	 * Represents the driver who is associated with this rental.
	 */
	private Driver driver;
	
	/**
	 * Represents the vehicle that is being rented.
	 */
	private Vehicle vehicle;
	
	/**
	 * Represents the starting X coordinate of the vehicle's movement.
	 */
	private int firstStartCoordinate;
	
	/**
	 * Represents the starting Y coordinate of the vehicle's movement.
	 */
	private int secondStartCoordinate;
	
	/**
	 * Represents the ending X coordinate of the vehicle's movement.
	 */
	private int firstFinishCoordinate;
	
	/**
	 * Represents the ending Y coordinate of the vehicle's movement.
	 */
	private int secondFinishCoordinate;
	
	/**
	 * Represents the duration of the rental in seconds.
	 */
	private int usageDurationInSeconds;
	
	/**
	 * Represents the malfunction of the vehicle during this rental.
	 */
	private Malfunction malfunction;
	
	/**
	 * Represents promotion applied to the rental.
	 */
	private String promotion;
	
	/**
	 * Represents the unique identifier for this rental, a combination of date and vehicle ID.
	 */
	private String idOfRental;
	
	/**
	 * Represents the main application frame used to update the UI with the vehicle's movement.
	 */
	private final MainFrame mainFrame;
	
   /**
    * Constructs a new Rental object with the specified details.
    * 
    * @param rentalDateTime         The date and time of the rental.
    * @param driver                 The driver associated with the rental.
    * @param vehicle                The vehicle being rented.
    * @param firstStartCoordinate   The starting X coordinate.
    * @param secondStartCoordinate  The starting Y coordinate.
    * @param firstFinishCoordinate  The ending X coordinate.
    * @param secondFinishCoordinate The ending Y coordinate.
    * @param usageDurationInSeconds The duration of the rental in seconds.
    * @param malfunction            The malfunction of the vehicle.
    * @param promotion              The promotion applied to the rental.
    * @param mainFrame              The main frame of the application to update the UI.
    */
	public Rental(String rentalDateTime, Driver driver, Vehicle vehicle, int firstStartCoordinate, int secondStartCoordinate, int firstFinishCoordinate,
			int secondFinishCoordinate, int usageDurationInSeconds,Malfunction malfunction, String promotion, MainFrame mainFrame) {
		this.rentalDateTime = rentalDateTime;
		this.driver = driver;
		this.vehicle = vehicle;
		this.setFirstStartCoordinate(firstStartCoordinate);
		this.setSecondStartCoordinate(secondStartCoordinate);
		this.setFirstFinishCoordinate(firstFinishCoordinate);
		this.setSecondFinishCoordinate(secondFinishCoordinate);
		this.usageDurationInSeconds = usageDurationInSeconds;
		this.malfunction = malfunction;
		this.promotion = promotion;
		this.idOfRental = rentalDateTime + " " + this.vehicle.getIdOfVehicle();
		this.mainFrame = mainFrame;
	}
	

	/**
	 * Returns the rental date and time.
	 * 
	 * @return rentalDateTime   The rental date and time.
	 */
	public String getRentalDateTime() {
		return rentalDateTime;
	}
	
	/**
	 * Sets the rental date and time.
	 * 
	 * @param rentalDateTime   The new rental date and time to set.
	 */
	public void setRentalDateTime(String rentalDateTime) {
		this.rentalDateTime = rentalDateTime;
	}
	
	/**
	 * Returns the driver associated with the rental.
	 * 
	 * @return driver   The driver.
	 */
	public Driver getDriver() {
		return driver;
	}
	
	/**
	 * Sets the driver associated with the rental.
	 * 
	 * @param driver  The new driver to set.
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	/**
	 * Returns the vehicle being rented.
	 * 
	 * @return vehicle  The vehicle.
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	/**
	 * Sets the vehicle being rented.
	 * 
	 * @param vehicle  The new vehicle to set.
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	/**
	 * Returns the starting X coordinate.
	 * 
	 * @return firstStartCoordinate  The starting X coordinate.
	 */
	public int getFirstStartCoordinate() {
		return firstStartCoordinate;
	}


	/**
	 * Sets the starting X coordinate.
	 * 
	 * @param firstStartCoordinate  The new starting X coordinate to set.
	 */
	public void setFirstStartCoordinate(int firstStartCoordinate) {
		this.firstStartCoordinate = firstStartCoordinate;
	}


	/**
	 * Returns the starting Y coordinate.
	 * 
	 * @return secondStartCoordinate  The starting Y coordinate.
	 */
	public int getSecondStartCoordinate() {
		return secondStartCoordinate;
	}


	/**
	 * Sets the starting Y coordinate.
	 * 
	 * @param secondStartCoordinate  The new starting Y coordinate to set.
	 */
	public void setSecondStartCoordinate(int secondStartCoordinate) {
		this.secondStartCoordinate = secondStartCoordinate;
	}


	/**
	 * Returns the ending X coordinate.
	 * 
	 * @return firstFinishCoordinate  The ending X coordinate.
	 */
	public int getFirstFinishCoordinate() {
		return firstFinishCoordinate;
	}


	/**
	 * Sets the ending X coordinate.
	 * 
	 * @param firstFinishCoordinate  The new ending X coordinate to set.
	 */
	public void setFirstFinishCoordinate(int firstFinishCoordinate) {
		this.firstFinishCoordinate = firstFinishCoordinate;
	}

	/**
	 * Returns the ending Y coordinate.
	 * 
	 * @return secondFinishCoordinate  The ending Y coordinate.
	 */
	public int getSecondFinishCoordinate() {
		return secondFinishCoordinate;
	}

	/**
	 * Sets the ending Y coordinate.
	 * 
	 * @param secondFinishCoordinate  The new ending Y coordinate to set.
	 */
	public void setSecondFinishCoordinate(int secondFinishCoordinate) {
		this.secondFinishCoordinate = secondFinishCoordinate;
	}
	
	/**
	 * Returns the usage duration of the rental in seconds.
	 * 
	 * @return usageDurationIn Seconds  The usage duration of rental in seconds.
	 */
	public int getUsageDurationInSeconds() {
		return usageDurationInSeconds;
	}
	
	/**
	 * Sets the usage duration of the rental in seconds.
	 * 
	 * @param usageDurationInSeconds  The new usage duration of rental in seconds to set.
	 */
	public void setUsageDurationInSeconds(int usageDurationInSeconds) {
		this.usageDurationInSeconds = usageDurationInSeconds;
	}
	
	
	/**
	 * Returns the malfunction of the vehicle.
	 * 
	 * @return malfunction  The malfunction of the vehicle.
	 */
	public Malfunction getMalfunction() {
		return malfunction;
	}

	/**
	 * Sets the malfunction of the vehicle.
	 * 
	 * @param malfunction  The new malfunction to set.
	 */
	public void setMalfunction(Malfunction  malfunction) {
		this.malfunction = malfunction;
	}

	/**
	 * Returns the promotion applied to the rental.
	 * 
	 * @return promotion  The promotion applied to the rental.
	 */
	public String getPromotion() {
		return promotion;
	}

	/**
	 * Sets the promotion applied to the rental.
	 * 
	 * @param promotion  The new promotion to set.
	 */
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	
	/**
	 * Returns the unique ID of the rental.
	 * 
	 * @return idOfRental  The rental ID.
	 */
	public String getIdOfRental() {
		return idOfRental;
	}


	/**
	 * Sets the unique ID of the rental.
	 * 
	 * @param idOfRental  The new id of rental to set.
	 */
	public void setIdOfRental(String idOfRental) {
		this.idOfRental = idOfRental;
	}
	

	/**
	 * Returns the main frame of the application used to update the UI.
	 * 
	 * @return mainFrame  The main frame.
	 */
	public MainFrame getMainFrame() {
		return mainFrame;
	}

    /**
     *  Returns a string representation of the rental.
     *  <p>Includes details such as date, driver, vehicle,start and finish locations, duration, malfunction status and promotion.</p>
     * 
     * @return A string representation of the rental.
     */
	@Override 
	public String toString() {
		return "Date and time: " + rentalDateTime + ", name of the driver: " + driver + ", vehicle: " + vehicle + ", start location: " + firstStartCoordinate + "," + secondStartCoordinate  + ", finish location: " + firstFinishCoordinate + 
				"," + secondFinishCoordinate + ", usage duration in seconds: " + usageDurationInSeconds + ", has malfunction: " + malfunction.getHasMalfunction() + ", has promotion: " + promotion + ".";
	}
	
	/**
	 * Simulates the movement of the vehicle from the start coordinates to the finish coordinates.
	 * Updates the main frame to reflect the vehicle's position and battery level at regular intervals.
	 */
	@Override
    public void run() {
    	int x1 = this.firstStartCoordinate;
        int x2 = this.firstFinishCoordinate;
        int y1 = this.secondStartCoordinate;
        int y2 = this.secondFinishCoordinate;
        int numberOfFields = Math.abs(x2 - x1) + Math.abs(y2 - y1);
        int delay = (this.usageDurationInSeconds * 1000) / numberOfFields;



        while (x1 != x2) {
            if (x2 > x1) {
                x1++;
            } else {
                x1--;
            }
            mainFrame.updateMatrix(y1, x1, vehicle.getIdOfVehicle()+"-"+vehicle.decreaseBatteryLevel());
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (y1 != y2) {
            if (y2 > y1) {
                y1++;
            } else {
                y1--;
            }
            mainFrame.updateMatrix(y1, x1, vehicle.getIdOfVehicle()+"-"+vehicle.decreaseBatteryLevel());
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }  
    }
}