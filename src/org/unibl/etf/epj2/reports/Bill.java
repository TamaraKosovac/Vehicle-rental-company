package org.unibl.etf.epj2.reports;

/**
 * Represents a bill for a vehicle rental.
 * <p>The {@code Bill} class encapsulates all relevant details about a rental transaction, including
 * the rental date and time, driver information, vehicle details, location, usage duration and pricing
 * information. It also includes data about malfunctions, promotions and discounts applied during the rental.</p>
 * 
 * @author Tamara Kosovac
 */
public class Bill {
	/**
	 * Represents the rental date and time of the vehicle.
	 */
	private String rentalDateTime;
	
	/**
	 * Represents the name of the driver who rented the vehicle.
	 */
	private String nameOfTheDriver;
	
	/**
	 * Represents the unique identifier of the vehicle.
	 */
	private String idOfVehicle;
	
	/**
	 * Represents the unit price.
	 */
	private int unitPrice;
	
	/**
	 * Represents the starting location of the rental.
	 */
	private String startLocation;
	
	/**
	 * Represents the finishing location of the rental.
	 */
	private String finishLocation;
	
	/**
	 * Represents the duration of the rental in seconds.
	 */
	private int usageDurationInSeconds;
	
	/**
	 * Indicates whether there was a malfunction during the rental.
	 */
	private String hasMalfunction;
	
	/**
	 * Indicates whether a promotion was applied during the rental.
	 */
	private String hasPromotion;
	
	/**
	 * Represents the amount of dicount applied to the rental.
	 */
	private double discount;
	
	/**
	 * Represents the amount of promotional discount applied to the rental.
	 */
	private double discountProm;
	
	/**
	 * Represents the total price of the rental after applying dicounts and promotions.
	 */
	private double totalPrice;
	
	/**
	 * Indicates whether the rental was in a narrow city area.
	 */
	private String inNarrowCity;

	/**
	 * Constructs a new {@code Bill} with the specified details.
	 * 
	 * @param rentalDateTime         The date and time of the rental.
	 * @param nameOfTheDriver        The name of the driver.
	 * @param idOfVehicle            The ID of the vehicle.
	 * @param unitPrice              The price per unit.
	 * @param startLocation          The starting location of the rental.
	 * @param finishLocation         The finishing location of the rental.
	 * @param usageDurationInSeconds The duration of the rental in seconds.
	 * @param hasMalfunction         Indicates if there was a malfunction during the rental.
	 * @param hasPromotion           Indicates if a promotion was applied.
	 * @param discount               The amount of discount applied.
	 * @param discountProm           The amount of promotional discount applied.
	 * @param totalPrice             The total price of the rental.
	 * @param inNarrowCity           Indicates if the rental was in a narrow city area.
	 */
	public Bill(String rentalDateTime, String nameOfTheDriver, String idOfVehicle, int unitPrice, String startLocation,
			String finishLocation, int usageDurationInSeconds, String hasMalfunction, String hasPromotion,
			double discount, double discountProm, double totalPrice, String inNarrowCity) {
		super();
		this.rentalDateTime = rentalDateTime;
		this.nameOfTheDriver = nameOfTheDriver;
		this.idOfVehicle = idOfVehicle;
		this.unitPrice = unitPrice;
		this.startLocation = startLocation;
		this.finishLocation = finishLocation;
		this.usageDurationInSeconds = usageDurationInSeconds;
		this.hasMalfunction = hasMalfunction;
		this.hasPromotion = hasPromotion;
		this.discount = discount;
		this.discountProm = discountProm;
		this.totalPrice = totalPrice;
		this.inNarrowCity = inNarrowCity;
	}
	
	/**
	 * Returns the rental date and time.
	 * 
	 * @return rentalDateTime  The date and time of the rental.
	 */
	public String getRentalDateTime() {
		return rentalDateTime;
	}
	
	/**
	 * Sets the rental date and time.
	 * 
	 * @param rentalDateTime  The new rental date and time to set.
	 */
	public void setRentalDateTime(String rentalDateTime) {
		this.rentalDateTime = rentalDateTime;
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
	 * Returns the ID of the vehicle.
	 * 
	 * @return idOfVehicle  The id of the vehicle.
	 */
	public String getIdOfVehicle() {
		return idOfVehicle;
	}
	
	/**
	 * Sets the ID of the vehicle.
	 * 
	 * @param idOfVehicle  The new id to set.
	 */
	public void setIdOfVehicle(String idOfVehicle) {
		this.idOfVehicle = idOfVehicle;
	}
	
	/**
	 * Returns the starting location of the rental.
	 * 
	 * @return startLocation  The start location of the rental.
	 */
	public String getStartLocation() {
		return startLocation;
	}
	
	/**
	 * Sets the starting location of the rental.
	 * 
	 * @param startLocation  The new start location to set.
	 */
	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}
	
	/**
	 * Returns the finishing location of the rental.
	 * 
	 * @return finishLocation  The finish location of the rental.
	 */
	public String getFinishLocation() {
		return finishLocation;
	}
	
	/**
	 * Sets the finishing location of the rental.
	 * 
	 * @param finishLocation  The new finish location to set.
	 */
	public void setFinishLocation(String finishLocation) {
		this.finishLocation = finishLocation;
	}
	
	/**
	 * Returns the duration of the rental in seconds.
	 * 
	 * @return usageDurationInSeconds  The usage duration in seconds of the rental.
	 */
	public int getUsageDurationInSeconds() {
		return usageDurationInSeconds;
	}
	
	/**
	 * Sets the duration of the rental in seconds.
	 * 
	 * @param usageDurationInSeconds  The new usage duration in seconds to set.
	 */
	public void setUsageDurationInSeconds(int usageDurationInSeconds) {
		this.usageDurationInSeconds = usageDurationInSeconds;
	}
	
	/**
	 * Returns if there was a malfunction during the rental, yes if there was a
	 * malfunction, otherwise no.
	 * 
	 * @return {@code "YES"} if there was a malfunction, otherwise {@code "NO"}
	 */
	public String getHasMalfunction() {
		return hasMalfunction;
	}
	
	/**
	 * Sets ih there was a malfunction during the rental.
	 * 
	 * @param hasMalfunction  The new malfunction status to set.
	 */
	public void setHasMalfunction(String hasMalfunction) {
		this.hasMalfunction = hasMalfunction;
	}
	
	/**
	 * Returns if a promotion was applied during the rental, yes if there was a
	 * promotion, otherwise no.
	 * 
	 * @return {@code "YES"} if a promotion was applied, otherwise {@code "NO"}
	 */
	public String getHasPromotion() {
		return hasPromotion;
	}
	
	/**
	 * Sets if a promotion was applied during the rental.
	 * 
	 * @param hasPromotion  The new promotion status to set.
	 */
	public void setHasPromotion(String hasPromotion) {
		this.hasPromotion = hasPromotion;
	}
	
	/**
	 * Returns the amount of discount applied.
	 * 
	 * @return discount  The amount of the discount.
	 */
	public double getDiscount() {
		return discount;
	}
	
	/**
	 * Sets the amount of discount applied.
	 * 
	 * @param discount  The new amount of the discount to set.
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	/**
	 * Returns the amount of promotional discount applied.
	 * 
	 * @return discountProm  The amount of the discount prom.
	 */
	public double getDiscountProm() {
		return discountProm;
	}
	
	/**
	 * Sets the amount of promotional discount applied.
	 * 
	 * @param discountProm  The new amount of the dicount prom to set.
	 */
	public void setDiscountProm(double discountProm) {
		this.discountProm = discountProm;
	}
	
	/**
	 * Returns the total price of the rental.
	 * 
	 * @return totalPrice  The total price of rental.
	 */
	public double getTotalPrice() {
		return totalPrice;
	}
	
	/**
	 * Sets the total price of the rental.
	 * 
	 * @param totalPrice  The new total price to set.
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * Returns if the rental was in a narrow city area, yes if there was in a narrow
	 * city, otherwise no.
	 * 
	 * @return {@code "YES"} if the rental was in a narrow city area, otherwise {@code "NO"}
     */
	public String getInNarrowCity() {
		return inNarrowCity;
	}

	/**
	 * Sets if the rental was in a narrow city area.
	 * 
	 * @param inNarrowCity The new narrow city status to set.
	 */
	public void setInNarrowCity(String inNarrowCity) {
		this.inNarrowCity = inNarrowCity;
	}

	/**
	 * Returns the unit price.
	 * 
	 * @return unitPrice  The unit price of the vehicle.
	 */
	public int getUnitPrice() {
		return unitPrice;
	}

	/**
	 * Sets the unit price.
	 * 
	 * @param unitPrice   The new unit price to set.
	 */
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
}