package org.unibl.etf.epj2.vehicles;


/**
 * The {@code ElectricScooter} class represents an electric scooter, which is a type of vehicle. 
 * It extends the {@link Vehicle} class and includes additional attributes specific to electric scooter.
 * 
 * <p>This class provides methods to get and set the maximum speed of the scooter.</p>
 * 
 * @author Tamara Kosovac
 */
public class ElectricScooter extends Vehicle {
	private static final long serialVersionUID = 1L;
	
	/**
	 * The maximum speed of the electric scooter.
	 */
	private int maxSpeed;
	
	/**
	 * Constructs an {@code ElectricScooter} with the specified parameters.
	 * 
	 * @param id            The ID of the scooter.
	 * @param manufacturer  The manufacturer of the scooter.  
	 * @param model         The model of the scooter.
	 * @param purchasePrice The purchase price of the scooter.
	 * @param typeOfVehicle The type of vehicle(in this case, a scooter).
	 * @param maxSpeed      The maximum speed of the scooter.
	 */
	public ElectricScooter(String id, String manufacturer, String model, int purchasePrice, String typeOfVehicle, int maxSpeed) {
		super(id, manufacturer, model, purchasePrice, typeOfVehicle);
		this.maxSpeed = maxSpeed;
	}

	/**
	 * Returns the maximum speed of the electric scooter.
	 * 
	 * @return maxSpeed  The maximum speed of the electric scooter.
	 */
	public int getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * Sets the maximum speed of the electric scooter.
	 * 
	 * @param maxSpeed  The maximum speed to set.
	 */
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	/**
	 * Returns a string representation of the {@ElectricScooter} object.
	 * <p>The string includes the superclass details followed by the maximum speed.</p>
	 * 
	 * @return A string representation of the electric scooter.
	 */
	@Override
	public String toString() {
		return super.toString() + ", max speed: " + maxSpeed + ".";
	}
}
