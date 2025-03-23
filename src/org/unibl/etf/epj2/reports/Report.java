package org.unibl.etf.epj2.reports;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.unibl.etf.epj2.rentals.ReadRentals;
import org.unibl.etf.epj2.rentals.RentalCalculator;

/**
 * Represents a report generated from rental bills, including methods for calculating various matrics.
 * This class reads rental bills, group them by date, and provides methods to calculate total income,
 * discounts, maintenance costs and other business-related metrics.
 * <p>The class assumes that rental bills are stored in files with specific formats and that these files
 * are located in a directory specified by {@link RentalCalculator#FOLDER_NAME}. </p>
 * 
 * @author Tamara Kosovac
 */
public class Report {
	/**
	 * Constants representing a boolean true value as a string.
	 */
	public static final String TRUE = "true";
	
	/**
	 * Coefficient for calculating repair costs for cars.
	 */
	public static final double CAR_COEFFICIENT = 0.07;
	
	/**
	 * Coefficient for calculating repair costs for bikes.
	 */
	public static final double BIKE_COEFFICIENT = 0.04;
	
	/**
	 * Coefficient for calculating repair costs for scooters.
	 */
	public static final double SCOOTER_COEFFICIENT = 0.02;
	
	/**
	 * List of bills to be included in the report.
	 */
	private List<Bill> bills = new ArrayList<>();
	
	/**
	 * Map of bills grouped by date.
	 */
	private Map<String, List<Bill>> billsGroupedByDate = new HashMap<>();
	
	/**
	 * Constructs a new Report object, initializes bills, and groups bills by date.
	 */
	public Report() {
		super();
		this.bills = createBills();
		this.setBillsGroupedByDate(createBillsGroupedByDate());
	}

	/**
	 * Returns the list of bills.
	 * 
	 * @return bills  The list of bills.
	 */
	public List<Bill> getBills() {
		return bills;
	}

	/**
	 * Sets the list of bills.
	 * 
	 * @param bills  The new list of bills to set.
	 */
	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	
	/**
	 * Returns the map of bills grouped by date.
	 * 
	 * @return billsGroupedByDate  The map of bills grouped by date and time.
	 */
	public Map<String, List<Bill>> getBillsGroupedByDate() {
		return billsGroupedByDate;
	}

	/**
	 * Sets the map of bills groped by date.
	 * 
	 * @param billsGroupedByDate  The new map of bills grouped by date and time to set.
	 */
	public void setBillsGroupedByDate(Map<String, List<Bill>> billsGroupedByDate) {
		this.billsGroupedByDate = billsGroupedByDate;
	}
	
	/**
	 * Creates a list of bills by reading data from files in the specified directory.
	 * 
	 * @return bills  The list of bills created from the files.
	 */
	private List<Bill> createBills() {
		File directory = new File(RentalCalculator.FOLDER_NAME);
		File[] files = directory.listFiles((dir, name) -> name.endsWith(RentalCalculator.TXT));
		if(files != null) {
			for(File file : files) {
				try {
					Scanner reader = new Scanner(file);
					String rentalDateTime = takingSecondString(reader.nextLine(), ":");
					String nameOfTheDriver = takingSecondString(reader.nextLine(), ":");
					String idOfVehicle = takingSecondString(reader.nextLine(), ":");
					int unitPrice = Integer.parseInt(takingSecondString(reader.nextLine(), ":"));
					String startLocation = takingSecondString(reader.nextLine(), ":");;
					String finishLocation = takingSecondString(reader.nextLine(), ":");;
					int usageDurationInSeconds = Integer.parseInt(takingSecondString(reader.nextLine(), ":"));
					String hasMalfunction = takingSecondString(reader.nextLine(), "?");;
					String hasPromotion = takingSecondString(reader.nextLine(), "?");;
					double discount = Double.parseDouble(takingSecondString(reader.nextLine(), ":"));
					double discount_prom = Double.parseDouble(takingSecondString(reader.nextLine(), ":"));
					double totalPrice = Double.parseDouble(takingSecondString(reader.nextLine(), ":"));
					String inNarrowCity = takingSecondString(reader.nextLine(), ":");
					Bill bill = new Bill(rentalDateTime, nameOfTheDriver, idOfVehicle, unitPrice, startLocation, finishLocation,
							usageDurationInSeconds, hasMalfunction, hasPromotion, discount, discount_prom, totalPrice, inNarrowCity);
					bills.add(bill);
					reader.close();
				} catch(IOException exception) {
					exception.printStackTrace();
				}
			}
		}
		return bills;
	}
	
	/**
	 * Creates a map of bills grouped by their date.
	 * 
	 * @return billsGroupedByDate  The map of bills grouped by date.
	 */
	private Map<String, List<Bill>> createBillsGroupedByDate() {
		Map<String, List<Bill>> billsGroupedByDate = bills.stream()
	            .collect(Collectors.groupingBy(bill -> {
	                String rentalDateTime = bill.getRentalDateTime();
	                return rentalDateTime.split(" ")[0]; 
	            }));
		return billsGroupedByDate;
	}
	
	/**
	 * Calculates the total income from a list of bills.
	 * 
	 * @param list   The list of bills to calculate total income from.
	 * @return totalIncome  The amount of total income.
	 */
	public double totalIncome(List<Bill> list) {
		return list.stream().mapToDouble(Bill::getTotalPrice).sum();
	}
	
	/**
	 * Calculates the total discount from a list of bills.
	 * 
	 * @param list     The list of bills to calculate total discount from.
	 * @return totalDiscount  The amount of total discount.
	 */
	public double totalDiscount(List<Bill> list) {
		return (list.stream().mapToDouble(Bill::getDiscount).sum()) / 100;
	}
	
	
	/**
	 * Calculates the total promotional discount from a list of bills.
	 * 
	 * @param list  The list of bills to calculate total promotional discount from.
	 * @return totalDiscountPromotion  The amount of total discount promotion.
	 */
	public double totalDiscountPromotion(List<Bill> list) {
		return (list.stream().mapToDouble(Bill::getDiscountProm).sum()) / 100;
	}
	
	
	/**
	 * Calculates the total income for rentals in narrow city areas.
	 * 
	 * @param list   The list of bills to calculate total income in narrow city from.
	 * @return totalIncomeInNarrowCity  The amount of total income in narrow city.
	 */
	public double totalIncomeInNarrowCity(List<Bill> list) {
		double totalIncomeInNarrowCity = 0.0;
		for(Bill bill : list) {
			if(bill != null && bill.getInNarrowCity() != null) {
				if(TRUE.equals(bill.getInNarrowCity())) {
					totalIncomeInNarrowCity += bill.getTotalPrice();
				} else {
					totalIncomeInNarrowCity += 0.0;
				}
			}
		}
	    return totalIncomeInNarrowCity;
	}
	
	/**
	 * Calculates the total income for rentals in wide city areas.
	 * 
	 * @param list  The list of bills to calculate total income in wide city from.
	 * @return totalIncomeInWideCity  The amount of total income in wide city.
	 */
	public double totalIncomeInWideCity(List<Bill> list) {
		return this.totalIncome(list) - this.totalIncomeInNarrowCity(list);
	}
	
	/**
	 * Calculates the total amount of malfunction repairs from a list of bills.
	 * 
	 * @param list   The list of bills to calculate total malfunction repair costs from.
	 * @return totalAmountOfMalfunctionRepairs  The amount of total malfunction repairs.
	 */
	public double totalAmountOfMalfunctionRepairs(List<Bill> list) {
		double totalAmountOfMalfunctionRepairs = 0.0;
		for(Bill bill : list) {
			if(bill != null && bill.getIdOfVehicle() != null) {
				if(ReadRentals.YES.equals(bill.getHasMalfunction())) {
					if(bill.getIdOfVehicle().contains(RentalCalculator.CAR_FIRST_LETTER)) {
						totalAmountOfMalfunctionRepairs += (CAR_COEFFICIENT * bill.getUnitPrice());
					} else if(bill.getIdOfVehicle().contains(RentalCalculator.BIKE_FIRST_LETTER)) {
						totalAmountOfMalfunctionRepairs += (BIKE_COEFFICIENT * bill.getUnitPrice());
					} else {
						totalAmountOfMalfunctionRepairs += (SCOOTER_COEFFICIENT * bill.getUnitPrice());
					}
				}
			}
		}
		return totalAmountOfMalfunctionRepairs;
	}

	/**
	 * Calculates the total maintenance amount, which is 20% of the total income.
	 * 
	 * @param list  The list of bills to calculate total maintenance amount from.
	 * @return Total maintenance amount.
	 */
	public double totalMaintenanceAmount(List<Bill> list) {
		return totalIncome(list) * 0.2;
	}
	
	/**
	 * Calculates the total costs of the company, which is 20% of the total income.
	 * 
	 * @param list  The list of bills to calculate total company costs from.
	 * @return Total costs of the company.
	 */
	public double totalCostsOfCompany(List<Bill> list) {
		return totalIncome(list) * 0.2;
	}
	
	/**
	 * Calculates the total tax, which is 10% of the income after subtracting maintenance and repair costs.
	 * 
	 * @param list  The list of bills to calculate total tax from.
	 * @return Total tax amount.
	 */
	public double totalTax(List<Bill> list) {
		return (totalIncome(list) - totalMaintenanceAmount(list) - totalAmountOfMalfunctionRepairs(list) - totalCostsOfCompany(list)) * 0.1;
	}
	
	
	/**
	 * Extracts the part of a string that comes after the first occurrence of a specified separator.
	 * 
	 * @param line  The input string from which the part after the separator is to be extracted.
	 * @param separator  The separator used to determine the starting point of the substring.
	 * @return  The substring that follows the first occurrence of the separator. If the separator is not found,
     * an empty string is returned
	 */
	private String takingSecondString(String line, String separator) {
		int indexOfSeparator = line.indexOf(separator);
		if(indexOfSeparator != -1) {
			String secondString = line.substring(indexOfSeparator+1).trim();
			return secondString;
		}
		return "";
	}
}