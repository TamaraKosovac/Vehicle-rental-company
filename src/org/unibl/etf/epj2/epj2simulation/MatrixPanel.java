package org.unibl.etf.epj2.epj2simulation;
import javax.swing.*;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;

/**
 * A JPanel subclass that displays a matrix grid where each cell can hold multiple IDs.
 * The grid is drawn based on the data provided in the `city` parameter.
 * The cells are colored based on whether they contain any IDs, and the IDs are displayed within the cells.
 * 
 * @author Tamara Kosovac
 */
public class MatrixPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	/**
	 * A concurrent hash map representing the city grid.
     * The keys are coordinates in the format "row,column" and the values are lists of IDs
     * associated with those coordinates.
	 */
	private final ConcurrentHashMap<String, List<String>> city;
	
	/**
	 * The number of rows in the matrix.
	 */
	private final int rows;
	
	/**
	 * The number of columns in the matrix.
	 */
    private final int cols;

    /**
     * Constructs a MatrixPanel with the specified city grid, number of rows, and number of columns.
     *
     * @param city A concurrent hash map representing the city grid where the keys are coordinate strings and the values are lists of IDs.
     * @param rows The number of rows in the grid.
     * @param cols The number of columns in the grid.
     */
    public MatrixPanel(ConcurrentHashMap<String, List<String>> city, int rows, int cols) {
        this.city = city;
        this.rows = rows;
        this.cols = cols;
        setPreferredSize(new Dimension(800, 800)); 
    }
    
    /**
     * Returns the city grid.
     * 
	 * @return city  A concurrent hash map representing the city grid.
	 */
	public ConcurrentHashMap<String, List<String>> getCity() {
		return city;
	}

	/**
	 * Returns the number of rows in the matrix.
	 * @return rows  The number of rows.
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Returns the number of columns in the matrix.
	 * 
	 * @return cols  The number of columns.
	 */
	public int getCols() {
		return cols;
	}
    
	/**
	 * Paints the component by drawing the matrix grid.
     * The cells are filled with different colors depending on whether they contain IDs,
     * and the IDs are drawn within the cells.
     * 
     * @param graphic  The graphics context to use for painting.
	 */
    @Override
    protected void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        drawMatrix(graphic);
    }

    /**
     * Draws the matrix grid.
     * Cells are filled with a color based on whether they contain any IDs,
     * and the IDs are drawn inside the cells.
     *
     * @param graphic The graphics context to use for drawing.
     */
    private void drawMatrix(Graphics graphic) {
        int cellSize = getWidth() / cols; 
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                List<String> ids = city.getOrDefault(x + "," + y, new ArrayList<>());
                graphic.setColor(ids.isEmpty() ? Color.WHITE : Color.DARK_GRAY);
                graphic.fillRect(y * cellSize, x * cellSize, cellSize, cellSize);
                graphic.setColor(Color.BLACK);
                graphic.drawRect(y * cellSize, x * cellSize, cellSize, cellSize);

                if (!ids.isEmpty()) {
                    int offsetX = 5;
                    int offsetY = cellSize / 2 + graphic.getFontMetrics().getAscent() / 2 - 2; 

                    graphic.setColor(Color.WHITE);
                    Font originalFont = graphic.getFont(); 
                    Font smallerFont = originalFont.deriveFont(originalFont.getSize2D() * 0.8f); 
                    graphic.setFont(smallerFont);
                    for (String id : ids) {
                        graphic.drawString(id, y * cellSize + offsetX, x * cellSize + offsetY);
                        offsetY += graphic.getFontMetrics().getHeight(); 
                        
                    }
                    graphic.setFont(originalFont);
                }
            }
        }
    }
}