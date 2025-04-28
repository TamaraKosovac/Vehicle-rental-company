package org.unibl.etf.epj2.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.epj2.vehicles.ElectricScooter;

/**
 * Represents a table model for displaying a list of ElectricScooters objects in a JTable.
 * 
 * @author Tamara Kosovac
 */
public class ScooterTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	/**
	 * List of ElectricScooters objects to be displayed in the table.
	 */
	private List<ElectricScooter> scooters;
	
	/**
	 * Array of column names for the table.
	 */
	private String[] columnNames = {"ID", "Vrsta", "Proizvodjac", "Model", "Cijena", "Max Brzina"};
	
	
	/**
	 * Returns the list of electric scooters.
	 * 
	 * @return scooters  The list of scooters.
	 */
	public List<ElectricScooter> getScooters() {
		return scooters;
	}

	/**
	 * Sets the list of scooters.
	 * 
	 * @param scooters  The scooters to set.
	 */
	public void setScooters(List<ElectricScooter> scooters) {
		this.scooters = scooters;
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
	 * Constructs a BikeTableModel with the specified list of ElectricScooters objects.
	 * 
	 * @param scooters  List of ElectricScooters objects to be displayed in the table.
	 */
	public ScooterTableModel(List<ElectricScooter> scooters) {
		this.scooters = scooters;
	}
	
	/**
	 * Returns the number of rows in the table model.
	 * 
	 * @return The number of rows in the table model.
	 */
	@Override
	public int getRowCount() {
		return scooters.size();
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
		ElectricScooter scooter = scooters.get(rowIndex);
		switch(columnIndex) {
		case 0: 
			return scooter.getIdOfVehicle();
		case 1:
			return scooter.getTypeOfVehicle();
		case 2:
			return scooter.getManufacturer();
		case 3: 
			return scooter.getModel();
		case 4:
			return scooter.getPurchasePrice();
		case 5: 
			return scooter.getMaxSpeed();
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
