package org.unibl.etf.epj2.malfunctions;

/**
 * Represents a malfunction of a vehicle, including its description and occurrence date and time.
 * <p>The class provides functionality to set and retrieve details about the malfunction.</p>
 * 
 * @author Tamara Kosovac
 */
public class Malfunction {
	/**
	 * The status indicating whether a malfunction exists.
	 */
	private String hasMalfunction;
	
	/**
	 * A description of the malfunction.
	 */
	private String descriptionOfMalfunction;
	
	/**
	 * The date and time when the malfunction occurred.
	 */
	private String dateTimeOfMalfunction;
	
	/**
	 * A constant representing the absence of a malfunction.
	 */
	public static final String NOT_EXIST = "nema";
	
	/**
	 * Constructs a new Malfunction in case that vehicle has malfunction.
	 * 
	 * @param hasMalfunction        The status yes, because malfunction occured.
	 * @param dateTimeOfMalfunction The date and time when the malfunction occurred.
	 */
	public Malfunction(String hasMalfunction, String dateTimeOfMalfunction) {
		this.hasMalfunction = hasMalfunction;
		this.dateTimeOfMalfunction = dateTimeOfMalfunction;
		this.descriptionOfMalfunction = "Pokvareno vozilo";
	}
	
	/**
	 * Constructs a new Malfunction in case that vehicle hasn't malfunction.
	 *  
	 * @param hasMalfunction  The status no, because malfunction didn't occurred.
	 */
	public Malfunction(String hasMalfunction) {
		this.hasMalfunction = hasMalfunction;
		this.dateTimeOfMalfunction = NOT_EXIST;
	}
	
	/**
	 * Returns the status of the malfunction. 
	 * 
	 * @return {@code "YES"} if there was a malfunction, otherwise {@code "NO"}
	 */
	public String getHasMalfunction() {
		return hasMalfunction;
	}

	/**
	 * Sets the status of the malfunction.
	 * 
	 * @param hasMalfunction  The new status of malfunction to set.
	 */
	public void setHasMalfunction(String hasMalfunction) {
		this.hasMalfunction = hasMalfunction;
	}

    /**
     * Returns the description of the malfunction.
     * 
     * @return descriptionOfMalfunction  The description of malfunction.
     */
	public String getDescriptionOfMalfunction() {
		return descriptionOfMalfunction;
	}

    
	/**
	 * Sets the description of malfunction.
	 * 
	 * @param descriptionOfMalfunction  The new description of malfunction to set.
	 */
	public void setDescriptionOfMalfunction(String descriptionOfMalfunction) {
		this.descriptionOfMalfunction = descriptionOfMalfunction;
	}

    
	/**
	 * Returns the date and time when the malfunction occurred.
	 * 
	 * @return dateTimeOfMalfunction  The date and time when malfunction happened.
	 */
	public String getDateTimeOfMalfunction() {
		return dateTimeOfMalfunction;
	}

    
	/**
	 * Sets the date and time when the malfunction occurred.
	 * 
	 * @param dateTimeOfMalfunction  The new date and time of malfunction to set.
	 */
	public void setDateTimeOfMalfunction(String dateTimeOfMalfunction) {
		this.dateTimeOfMalfunction = dateTimeOfMalfunction;
	}
	

	/**
	 * Returns a string representation of the {@code Malfunction} object.
	 * 
	 * @return A string representation of the malfunction.
	 */
	@Override
	public String toString() {
		return "Description of malfunction: " + descriptionOfMalfunction + ", date time: " + dateTimeOfMalfunction;
	}
}