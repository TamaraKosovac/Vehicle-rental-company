package org.unibl.etf.epj2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import org.unibl.etf.epj2.reports.Bill;
import org.unibl.etf.epj2.reports.Report;

/**
 * A JFrame that displays a summary report of various financial metrics.
 * The report includes total income, discounts, promotions, maintenance costs and other relevant financial data.
 * The data is presented in a JTable with two columns: "Kategorija" and "Iznos".
 * 
 * @author Tamara Kosovac
 */
public class ShowSummaryReportFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new ShowSummaryReportFrame that calculates and displays the summary report.
     * The report data is fetched using methods from the Report class and displayed in a JTable.
	 */
	public ShowSummaryReportFrame() {
		setTitle("Prikaz sumarnog izvjestaja");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(140, 10, 10, 140);
		setSize(900, 700);
		Report report = new Report();
	    List<Bill> bills = report.getBills();
	    Object[][] data = {
	            {"Ukupan prihod", report.totalIncome(bills)},
	            {"Ukupan popust", report.totalDiscount(bills)},
	            {"Ukupno promocije", report.totalDiscountPromotion(bills)},
	            {"Ukupan iznos svih voznji u uzem dijelu grada", report.totalIncomeInNarrowCity(bills)},
	            {"Ukupan iznos svih voznji u sirom dijelu grada", report.totalIncomeInWideCity(bills)},
	            {"Ukupan iznos za odrzavanje", report.totalMaintenanceAmount(bills)},
	            {"Ukupan iznos za popravke kvarova", String.format("%.1f", report.totalAmountOfMalfunctionRepairs(bills))},
	            {"Ukupni troskovi kompanije", report.totalCostsOfCompany(bills)},
	            {"Ukupan porez", String.format("%.1f", report.totalTax(bills))}
	        };
	    String[] columnNames = {"Kategorija", "Iznos"};
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
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JLabel titleLabel = new JLabel("Sumarni izvjestaj", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
	}
}