package org.unibl.etf.epj2.vehicles;


/**
 * The {@code ElectricBike} class represents an electric bike, which is a type of vehicle.
 * It extends the {@link Vehicle} class and includes additional attributes specific to electric bikes,
 * such as the range per charge.
 * 
 * <p>This class provides methods to access and modify the range per charge, which represents how far
 * the bike can travel on a single charge.</p>
 * 
 * <p>Instances of this class can be used to represent electric bikes with specific details, and the class
 * includes a method for returning a string representation of the object.</p>
 * 
 * @author Tamara Kosovac
 */
public class ElectricBike extends Vehicle {
	private static final long serialVersionUID = 1L;
	
	/**
	 * The range that the bike can travel per charge.
	 */
	private int rangePerCharge;
	
	/**
	 * Constructs an {@code ElectricBike} with the specified parameters.
	 * 
	 * @param id            The ID of the electric bike.
	 * @param manufacturer  The manufacturer of the bike.
	 * @param model         The model of the bike.
	 * @param purchasePrice The purchase price of the bike.
	 * @param typeOfVehicle The type of vehicle(in this case, a bike).
	 * @param rangePerCharge The range the bike can travel per charge.
	 */
	public ElectricBike(String id, String manufacturer, String model, int purchasePrice, String typeOfVehicle, int rangePerCharge) {
		super(id, manufacturer, model, purchasePrice, typeOfVehicle);
		this.rangePerCharge = rangePerCharge;
	}

	/**
	 * Returns the range per charge of the electric bike.
	 * 
	 * @return rangePerCharge  The range per charge of the electric bike.
	 */
	public int getRangePerCharge() {
		return rangePerCharge;
	}

	/**
	 * Sets the range per charge of the electric bike.
	 * 
	 * @param rangePerCharge  The new range per charge to set.
	 */
	public void setRangePerCharge(int rangePerCharge) {
		this.rangePerCharge = rangePerCharge;
	}
	
	
	/**
	 * Returns a string representation of the {@code ElectricBike} object.
	 * <p>Includes the superclass details followed by the range per charge.</p>
	 * 
	 * @return A string representation of the electric car.
	 */
	@Override
	public String toString() {
		return super.toString() + ", range per charge: " + rangePerCharge + ".";
	}
}