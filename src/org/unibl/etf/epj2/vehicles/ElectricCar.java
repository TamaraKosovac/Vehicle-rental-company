package org.unibl.etf.epj2.vehicles;


/**
 * The {@code ElectricCar} class represents an electric car, which is a type of vehicle.
 * It extends the {@link Vehicle} class and includes additional attributes specific to electric cars,
 * such as the purchase date and a description.
 * 
 * <p>This class also sets the {@code hasPassengerCapacity} attribute to {@code true},
 * indicating that electric cars have passenger capacity.</p>
 * 
 * <p>Instances of this class can be used to represent electric cars with specific details,
 * and the class provides methods to access an modify these details.</p>
 * 
 * @author Tamara Kosovac
 */
public class ElectricCar extends Vehicle {
	private static final long serialVersionUID = 1L;
	
	/**
	 * The purchase date of the electric car.
	 */
	private String purchaseDate;
	
	/**
	 * A description of the electric car.
	 */
	private String description;
	
	/**
	 * Construct an {@ElectricCar} with the specified parameters.
	 * 
	 * @param idOfVehicle   The ID of the car.
	 * @param manufacturer  The manufacturer of the car.
	 * @param model         The model of the car.
	 * @param purchasePrice The purchase price of the car.
	 * @param typeOfVehicle The type of vehicle(in this case, a car).
	 * @param purchaseDate  The purchase date of the car.
	 * @param description   A description of the car.
	 */
	public ElectricCar(String idOfVehicle, String manufacturer, String model, int purchasePrice, String typeOfVehicle, String purchaseDate, String description) {
		super(idOfVehicle, manufacturer, model, purchasePrice, typeOfVehicle);
		this.purchaseDate = purchaseDate;
		this.description = description;
		this.hasPassengerCapacity = true;
	}

	/**
	 * Returns the purchase date of the electric car.
	 * 
	 * @return purchaseDate  The purchase date of electric car.
	 */
	public String getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * Sets the purchase date of the electric car.
	 * 
	 * @param purchaseDate  The new purchase date to set.
	 */
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * Returns the description of the electric car.
	 * 
	 * @return description  The description of the electric car.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the electric car.
	 * 
	 * @param description  The new description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns a string representation of the {@code ElectricCar} object.
	 * <p>Includes the superclass details followed by the purchase date and description.</p>
	 * 
	 * @return A string representation of the electric car.
	 */
	@Override
	public String toString() {
		return super.toString() + ", purchase date: " + purchaseDate + ", description: " + description + ".";
	}
}