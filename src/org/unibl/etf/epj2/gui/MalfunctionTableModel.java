package org.unibl.etf.epj2.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.epj2.rentals.Rental;

/**
 * Represents a table model for displaying rental objects with malfunction in a JTable.
 * 
 * @author Tamara Kosovac
 */
public class MalfunctionTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	/**
	 * List of Rental objects to be displayed in the table.
	 */
	private List<Rental> rentals;
	
	/**
	 * Array of column names for the table.
	 */
	private String[] columnNames = {"Vrsta prevoznog sredstva", "ID", "Vrijeme", "Opis"};
	
	/**
	 * Returns the list of rentals.
	 * 
	 * @return rentals  The list of rentals.
	 */
	public List<Rental> getRentals() {
		return rentals;
	}

	/**
	 * Sets the list of rentals.
	 * 
	 * @param rentals  The rentals to set.
	 */
	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	/**
	 * Returns the array of column names.
	 * 
	 * @return columnNames  The column names.
	 */
	public String[] getColumnNames() {
		return columnNames;
	}

	/**
	 * Sets the array of column names.
	 * 
	 * @param columnNames  The column names to set.
	 */
	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}
    
	/**
	 * Constructs a MalfunctionTableModel with the specified list of objects with malfunctions.
	 * 
	 * @param rentals  List of Rental objects.
	 */
	public MalfunctionTableModel(List<Rental> rentals) {
		this.rentals = rentals;
	}
	
	/**
	 * Returns the number of rows in the table model.
	 * 
	 * @return The number of rows in the table model.
	 */
	@Override
	public int getRowCount() {
		return rentals.size();
	}
	
	/**
	 * Returns the number of columns in the table model.
	 * 
	 * @return The number of columns in the table model.
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	/**
	 *  Returns the value at the specified row and column in the table model.
	 *  @param rowIndex     The row index of the value to be retrieved.
	 *  @param columnIndex  The column index of the value to be retrieved.
	 *  @return The value at the specified row and column.
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Rental rental = rentals.get(rowIndex);
		switch(columnIndex) {
		case 0: 
			return rental.getVehicle().getTypeOfVehicle();
		case 1:
			return rental.getVehicle().getIdOfVehicle();
		case 2:
			return rental.getRentalDateTime();
		case 3: 
			return rental.getMalfunction().getDescriptionOfMalfunction();
		default:
			return null;
		}
	}
	
	/**
	 * Returns the name of the column at the specified index.
	 * 
	 * @param column  The column index of the name to be retrieved.
	 * @return The name of the column at the specified index.
	 */
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
}
