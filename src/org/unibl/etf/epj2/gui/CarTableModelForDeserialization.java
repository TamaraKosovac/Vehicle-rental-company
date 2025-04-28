package org.unibl.etf.epj2.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.epj2.reports.Report;
import org.unibl.etf.epj2.vehicles.ElectricCar;

/**
 * Represents a table model for displaying deserialized list of ElectricCar objects in a JTable.
 * 
 * @author Tamara Kosovac
 */
public class CarTableModelForDeserialization extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	/**
	 * List of deserialized ElectricCar objects to be displayed in the table.
	 */
	private List<ElectricCar> cars;
	
	/**
	 * Array of column names for the table.
	 */
	private String[] columnNames = {"ID", "Vrsta", "Proizvodjac", "Model", "Datum nabavke" , "Cijena", "Opis", "Cijena popravke"};
	
	
	/**
	 * Returns the list of deserialized electric cars.
	 * 
	 * @return cars  The list of deserialized cars.
	 */
	public List<ElectricCar> getCars() {
		return cars;
	}

	/**
	 * Sets the list of deserialiazed cars.
	 * 
	 * @param cars  The deserialized cars to set.
	 */
	public void setCars(List<ElectricCar> cars) {
		this.cars = cars;
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
	 * Constructs a CarTableModelForDeserialization with the specified list of deserialized ElectricCar objects.
	 * 
	 * @param cars  List of deserialized ElectricCar objects to be displayed in the table.
	 */
	public CarTableModelForDeserialization(List<ElectricCar> cars) {
		this.cars = cars;
	}
	
	/**
	 * Returns the number of rows in the table model.
	 * 
	 * @return The number of rows in the table model.
	 */
	@Override
	public int getRowCount() {
		return cars.size();
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
		ElectricCar car = cars.get(rowIndex);
		switch(columnIndex) {
		case 0: 
			return car.getIdOfVehicle();
		case 1:
			return car.getTypeOfVehicle();
		case 2:
			return car.getManufacturer();
		case 3: 
			return car.getModel();
		case 4:
			return car.getPurchaseDate();
		case 5: 
			return car.getPurchasePrice();
		case 6:
			return car.getDescription();
		case 7:
			return String.format("%.1f", car.getPurchasePrice() * Report.CAR_COEFFICIENT);
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
