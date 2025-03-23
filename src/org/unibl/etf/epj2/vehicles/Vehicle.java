package org.unibl.etf.epj2.vehicles;

import java.io.Serializable;

/**
 * Abstract class representing a vehicle.
 * Implements Serializable interface to allow object serialization.
 * Provides basic attributes and methods for all types of vehicles.
 * 
 * <p>
 * This class is a part of the vehicle management system and serves as a base class
 * for different types of vehicles.
 * </p>
 * 
 * <P><string>Note:</strong> This class is abstract and cannot be instantiated directly.</p>
 * 
 * @author Tamara Kosovac
 */
public abstract class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Unique identifier of the vehicle.
	 */
	protected String idOfVehicle;
	
	/**
	 * Manufacturer of the vehicle.
	 */
	protected String manufacturer;
	
	/**
	 * Model of the vehicle.
	 */
	protected String model;
	
	/**
	 * Purchase price of the vehicle.
	 */
	protected int purchasePrice;
	
	/**
	 * Battery level of the vehicle.
	 */
	protected int batteryLevel;
	
	/**
	 * Indicates whether the vehicle has passenger capacity.
	 * Default will be false.
	 */
	protected boolean hasPassengerCapacity;
	
	/**
	 * Type of the vehicle.
	 */
	protected String typeOfVehicle;
	
	/**
	 * Constructor to initialize a Vehicle object.
	 * 
	 * @param idOfVehicle   Unique identifier of the vehicle.
	 * @param manufacturer  Manifacturer of the vehicle.
	 * @param model         Model of the vehicle.
	 * @param purchasePrice Purchase price of the vehicle.
	 * @param typeOfVehicle Type of the vehicle.
	 */
	public Vehicle(String idOfVehicle, String manufacturer, String model, int purchasePrice, String typeOfVehicle) {
		this.idOfVehicle = idOfVehicle;
		this.manufacturer = manufacturer;
		this.model = model;
		this.purchasePrice = purchasePrice;
	    chargeBatteryLevel();
		this.hasPassengerCapacity = false;
		this.typeOfVehicle = typeOfVehicle;
	}
	

	/**
	 * Gets the unique identifier of the vehicle.
	 * 
	 * @return idOfVehicle  The unique identifier of the vehicle.
	 */
	public String getIdOfVehicle() {
		return idOfVehicle;
	}

	/**
	 * Sets the unique identifier of the vehicle.
	 * 
	 * @param idOfVehicle  The unique identifier to set.
	 */
	public void setIdOfVehicle(String idOfVehicle) {
		this.idOfVehicle = idOfVehicle;
	}

	/**
	 * Gets the manufacturer of the vehicle.
	 * 
	 * @return manufacturer  The manufacturer of the vehicle.
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Sets the manufacturer of the vehicle.
	 * 
	 * @param manufacturer  The manufacturer to set.
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Gets the model of the vehicle.
	 * 
	 * @return model  The model of the vehicle.
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Sets the model of the vehicle.
	 * 
	 * @param model  The model to set.
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Gets the purchase price of the vehicle.
	 * 
	 * @return purchasePrice  The purchase price of the vehicle.
	 */
	public int getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * Sets the purchase price of the vehicle.
	 * 
	 * @param purchasePrice  The purchase price to set.
	 */
	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * Gets the battery level of the vehicle.
	 * 
	 * @return batteryLevel  The battery level of the vehicle.
	 */
	public int getBatteryLevel() {
		return batteryLevel;
	}

	/**
	 * Sets the battery level of the vehicle.
	 * 
	 * @param batteryLevel  The battery level to set.
	 */
	public void setBatteryLevel(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	
	/**
	 * Gets whether the vehicle has passenger capacity.
	 * 
	 * @return True if the vehicle has passenger capacity, false otherwise.
	 */
	public boolean getPassengerCapacity() {
		return hasPassengerCapacity;
	}
	
	/**
	 * Gets the type of the vehicle.
	 * 
	 * @return typeOfVehicle  The type of the vehicle.
	 */
	public String getTypeOfVehicle() {
		return typeOfVehicle;
	}
	
	/**
	 * Sets the type of the vehicle.
	 * 
	 * @param typeOfVehicle  The type of vehicle to set.
	 */
	public void setTypeOfVehicle(String typeOfVehicle) {
		this.typeOfVehicle = typeOfVehicle;
	}
	
	/**
	 * Charges the vehicle's battery to full (100).
	 */
	public void chargeBatteryLevel() {
		this.batteryLevel = 100;
	}
	
	/**
	 * Decreases the vehicle's battery level by 1, with a minimum battery level of 5.
	 * 
	 * @return The updated battery level after the decrease.
	 */
   public int decreaseBatteryLevel() {
	 return Math.max(5, this.batteryLevel -= 1);
   }
	
	/**
	 * Returns a string representation of the vehicle.
	 * <p>Includes the vehicle's ID, type, manufacturer, model, purchase price, battery level and passenger capacity status.</p>
	 * @return A string representation of the vehicle.
	 */
	@Override
	public String toString() {
		return "ID of vehicle: " + idOfVehicle + ",type of vehicle: " + typeOfVehicle + ", manufacturer: " + manufacturer + ", model: " + model + ", purchase price: "
				+ purchasePrice + ", battery level: " + batteryLevel + ", has passenger capacity: " + hasPassengerCapacity;
	}
	
	/**
	 * Compares this vehicle to another object for equality based on the vehicle's ID.
	 * 
	 * @param object  The object to compare with.
	 * @return True if the object is a Vehicle with the same ID, false otherwise.
	 */
	@Override
	public boolean equals(Object object) {
		if(object == null) {
			return false;
		}
		if(getClass() != object.getClass()) {
			return false;
		}
		final Vehicle other = (Vehicle) object;
		if(!this.idOfVehicle.equals(other.idOfVehicle)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Generates a hash code for the vehicle based on its ID.
	 * 
	 * @return The hash code of the vehicle.
	 */
	@Override
	public int hashCode() {
		return idOfVehicle.hashCode();
	}
}
