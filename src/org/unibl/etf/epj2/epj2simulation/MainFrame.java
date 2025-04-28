package org.unibl.etf.epj2.epj2simulation;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the main frame of the simulation application.
 * The frame displays a 20x20 grid where vehicles move and their positions are updated.
 * 
 * @author Tamara Kosovac
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/**
	 * The panel that renders the matrix/grid.
	 */
	private final MatrixPanel matrixPanel;
	
	/**
	 * A thread-safe map that holds the current state of the grid.
     * Keys are positions in the grid, and values are lists of vehicle information at those positions.
	 */
	private final ConcurrentHashMap<String, List<String>> city;
	
	/**
	 * A map that keeps track of the previous positions of vehicles.
     * The key is the vehicle information, and the value is the previous position.
	 */
    private final Map<String, String> previousPosition = new HashMap<>();
    
    /**
     * The dimension of the grid (20x20).
     */
    public static final int DIMENSION = 20;
    
    /**
     * Constructs a new MainFrame instance.
     * Initializes the matrix panel, sets up the frame properties, and adds the panel to the frame.
     */
    public MainFrame() {
        city = new ConcurrentHashMap<>();
        matrixPanel = new MatrixPanel(city, DIMENSION, DIMENSION); 
        setTitle("Simulacija vozila");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(140, 10, 10, 140);
        setLayout(new BorderLayout());
        add(matrixPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }
    
    /**
     * Returns the MatrixPanel associated with this frame.
     * 
	 * @return matrixPanel  The matrix panel associated with this frame.
	 */
	public MatrixPanel getMatrixPanel() {
		return matrixPanel;
	}

	/**
	 * Returns the ConcurrentHashMap representing the city grid.
	 * 
	 * @return city  The city grid.
	 */
	public ConcurrentHashMap<String, List<String>> getCity() {
		return city;
	}

	/**
	 * Returns the map of previous vehicle positions.
	 * 
	 * @return previousPosition  The previous position on map.
	 */
	public Map<String, String> getPreviousPosition() {
		return previousPosition;
	}

    /**
     * Updates the grid with the new position and information of a vehicle.
     * If the vehicle was previously at a different position, it removes the old position's data.
     * 
     * @param x      The x-coordinate of the new position.
     * @param y      The y-coordinate of the new position.
     * @param info   Information about the vehicle, including its ID and battery level.
     */
    public void updateMatrix(int x, int y, String info) {
        SwingUtilities.invokeLater(() -> {
        String newPosition = x + "," + y;
        try {
        	String[] parts = info.split("-");
        	String vehicleId = parts[0];
        	int batteryLevel = Integer.parseInt(parts[1]) + 1;
        	String oldInfo = vehicleId+"-"+batteryLevel;
        if (previousPosition.containsKey(oldInfo)) {
            String oldPosition = previousPosition.get(oldInfo);
            city.get(oldPosition).remove(oldInfo);
            if (city.get(oldPosition).isEmpty()) {
                city.remove(oldPosition);
            }
        }
        } catch(NullPointerException e) {
        	
        }
         city.computeIfAbsent(newPosition, k -> new ArrayList<>()).add(info);
         previousPosition.put(info, newPosition);
         matrixPanel.repaint();
       });
    }

   /**
    * 
    */
   public void clearMatrix() {
      SwingUtilities.invokeLater(() -> {
        city.clear();
        matrixPanel.repaint(); 
      });
  }
   
   /**
    * 
    * @param x
    * @param y
    * @param content
    */
   public void showContentAt(int x, int y, String content) {
       SwingUtilities.invokeLater(() -> {
           String position = x + "," + y;
           city.computeIfAbsent(position, k -> new ArrayList<>()).add(content);
           matrixPanel.repaint();
           Timer timer = new Timer(2000, new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   city.computeIfPresent(position, (k, v) -> {
                       v.remove(content);
                       if (v.isEmpty()) {
                           return null;
                       } else {
                           return v;
                       }
                   });
                   matrixPanel.repaint();
               }
           });
           timer.setRepeats(false);
           timer.start();
       });
       
   }

   /**
    * 
    * @param args
    */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}