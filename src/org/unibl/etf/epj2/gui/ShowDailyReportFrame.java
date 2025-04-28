package org.unibl.etf.epj2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import org.unibl.etf.epj2.reports.Bill;
import org.unibl.etf.epj2.reports.Report;

/**
 * A JFrame that displays a daily report of various financial metrics.
 * The report includes daily total income, discounts, promotions, maintenance costs and other relevant financial data.
 * The data is grouped by date and presented in a JTable with multiple columns representing different financial categories.
 * 
 * The frame uses a BorderLayout to organize the components, with a title at the top and a scrollable table in the center.
 * 
 * @author Tamara Kosovac
 */
public class ShowDailyReportFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/**
	 * The main content pane for the frame.
	 */
	private JPanel contentPane;


	/**
	 * Constructs a new ShowDailyReportFrame that calculates and displays the daily report.
     * The report data is fetched using methods from the Report class, grouped by date, and displayed in a JTable.
	 */
	public ShowDailyReportFrame() {
		setTitle("Prikaz dnevnog izvještaja");
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setBounds(140, 10, 10, 140);
	    setSize(900, 700);
	    contentPane = new JPanel(new BorderLayout());
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    Report report = new Report();
	    Map<String, List<Bill>> billsGroupedByDate = report.getBillsGroupedByDate();
	    String[] columnNames = {
	        "Datum", 
	        "Ukupan dnevni prihod", 
	        "Ukupan dnevni popust", 
	        "Ukupno dnevno promocije", 
	        "Ukupan dnevni iznos voznji u uzem dijelu grada", 
	        "Ukupan dnevni iznos voznji u sirem dijelu grada", 
	        "Ukupan dnevni iznos za odrzavanje", 
	        "Ukupan dnevni iznos za popravke kvarova"
	    };
	    List<Object[]> dataList = new ArrayList<>();
	    for (Map.Entry<String, List<Bill>> entry : billsGroupedByDate.entrySet()) {
	        String rentalDateTime = entry.getKey();
	        List<Bill> dailyBills = entry.getValue();
	        dataList.add(new Object[] {
	            rentalDateTime, 
	            report.totalIncome(dailyBills), 
	            report.totalDiscount(dailyBills), 
	            report.totalDiscountPromotion(dailyBills), 
	            report.totalIncomeInNarrowCity(dailyBills), 
	            report.totalIncomeInWideCity(dailyBills), 
	            report.totalMaintenanceAmount(dailyBills), 
	            String.format("%.1f", report.totalAmountOfMalfunctionRepairs(dailyBills))
	        });
	    }
	    Object[][] data = dataList.toArray(new Object[0][]);
	    JTable reportTable = new JTable(data, columnNames);
	    reportTable.setFont(new Font("Serif", Font.PLAIN, 14));
	    reportTable.setRowHeight(20);
	    reportTable.setForeground(Color.BLACK);
	    reportTable.setBackground(Color.LIGHT_GRAY);
	    reportTable.setGridColor(Color.GRAY);
	    reportTable.setSelectionBackground(Color.DARK_GRAY);
	    reportTable.setSelectionForeground(Color.WHITE);
	    
	    JTableHeader header = reportTable.getTableHeader();
	    header.setFont(new Font("Serif", Font.BOLD, 16));
	    header.setBackground(Color.DARK_GRAY);
	    header.setForeground(Color.WHITE);
	    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	    renderer.setHorizontalAlignment(JLabel.CENTER);
	    reportTable.setDefaultRenderer(Object.class, renderer);
	    JScrollPane scrollPane = new JScrollPane(reportTable);
	    JLabel titleLabel = new JLabel("Dnevni izvjestaj", JLabel.CENTER);
	    titleLabel.setFont(new Font("Serif", Font.BOLD, 16));
	    titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
	    contentPane.add(titleLabel, BorderLayout.NORTH);
	    contentPane.add(scrollPane, BorderLayout.CENTER);
	}
}
