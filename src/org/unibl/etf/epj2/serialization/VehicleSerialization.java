package org.unibl.etf.epj2.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.epj2.rentals.ReadRentals;
import org.unibl.etf.epj2.rentals.Rental;
import org.unibl.etf.epj2.vehicles.Vehicle;

/**
 * The {@code VehicleSerialization} class provides methods for serializing and deserializing
 * {@link Vehicle} objects. It is used to save and load vehicle data to and from binary files.
 * 
 * <p>
 * The class includes functionality to:
 * <ul>
 *   <li>Serialize {@link Vehicle} objects that are associated with rentals that have malfunctions.</li>
 *   <li>Deserialize {@link Vehicle} objects from files in a specific directory.</li>
 * </ul>
 * </p>
 * 
 * <p>Serialized files are stored in a folder named {@values #SERIALIZATION_FOLDER} with a ".ser" extension. </p>
 * 
 * @author Tamara Kosovac
 */
public class VehicleSerialization {
	/**
	 * The file extension used for serialized vehicle files.
	 */
	public static final String SER = ".ser";
	
	/**
	 * The name of the folder where serialized vehicle files are stored.
	 */
	public static final String SERIALIZATION_FOLDER = "FolderWithSerializedVehicles";

	/**
	 * Serializes a list of {@link Rental} objects. Vehicles associated with rentals that have a malfunction
	 * are saved to separate files in the {@value #SERIALIZATION_FOLDER} directory.
	 * 
	 * <p>Each vehicle is serialized into a file named with the vehicle's ID and the ".ser" extension.</p>
	 * 
	 * @param rentals  The list of rentals to be processed for serialization.
	 */
	public static void serializeVehicle(List<Rental> rentals) {
		String nameOfFolder = SERIALIZATION_FOLDER;
		File folder = new File(nameOfFolder);
		folder.mkdir();
		for(Rental rental : rentals) {
			if(ReadRentals.YES.equals(rental.getMalfunction().getHasMalfunction())) {
				try {
					FileOutputStream fileOutputStream = new FileOutputStream(nameOfFolder + File.separator + rental.getVehicle().getIdOfVehicle() + SER);
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
					objectOutputStream.writeObject(rental.getVehicle());
					objectOutputStream.close();
					fileOutputStream.close();
				}catch(IOException exception) {
					exception.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Deserialize {@link Vehicle} objects from files in the {@value #SERIALIZATION_FOLDER} directory.
	 * 
	 * <p>This method reads each file with a ".ser" extension and reconstructs the {@link Vehicle} objects.
	 * 
	 * @return a list of deserialized damaged {@link Vehicle} objects
	 */
	public static List<Vehicle> deserializeVehicle() {
		List<Vehicle> damagedVehicles = new ArrayList<>();
		File folder = new File(SERIALIZATION_FOLDER + File.separator);
		for(File file: folder.listFiles()) {
			if(file.isFile() && file.getName().endsWith(SER)) {
				try {
					FileInputStream fileInputStream = new FileInputStream(file);
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
					Vehicle vehicle = (Vehicle)objectInputStream.readObject();
					damagedVehicles.add(vehicle);
					objectInputStream.close();
					fileInputStream.close();
				} catch(IOException | ClassNotFoundException exception) {
					exception.printStackTrace();
				}
			}
		}
			return damagedVehicles;

	}
}