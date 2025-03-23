package org.unibl.etf.epj2.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.epj2.vehicles.ElectricBike;

/**
 * Represents a table model for displaying a list of ElectricBike objects in a JTable.
 * 
 * @author Tamara Kosovac
 */
public class BikeTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	/**
	 * List of ElectricBike objects to be displayed in the table.
	 */
	private List<ElectricBike> bikes;
	
	/**
	 * Array of column names for the table.
	 */
	private String[] columnNames = {"ID", "Vrsta", "Proizvodjac", "Model", "Cijena", "Domet"};
	
	
	/**
	 * Returns the list of electric bikes.
	 * 
	 * @return bikes  The list of bikes.
	 */
	public List<ElectricBike> getBikes() {
		return bikes;
	}

	/**
	 * Sets the list of bikes.
	 * 
	 * @param bikes  The bikes to set.
	 */
	public void setBikes(List<ElectricBike> bikes) {
		this.bikes = bikes;
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
	 * Constructs a BikeTableModel with the specified list of ElectricBike objects.
	 * 
	 * @param bikes  List of ElectricBike objects to be displayed in the table.
	 */
	public BikeTableModel(List<ElectricBike> bikes) {
		this.bikes = bikes;
	}
	
	/**
	 * Returns the number of rows in the table model.
	 * 
	 * @return The number of rows in the table model.
	 */
	@Override
	public int getRowCount() {
		return bikes.size();
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
		ElectricBike bike = bikes.get(rowIndex);
		switch(columnIndex) {
		case 0: 
			return bike.getIdOfVehicle();
		case 1:
			return bike.getTypeOfVehicle();
		case 2:
			return bike.getManufacturer();
		case 3: 
			return bike.getModel();
		case 4:
			return bike.getPurchasePrice();
		case 5: 
			return bike.getRangePerCharge();
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
