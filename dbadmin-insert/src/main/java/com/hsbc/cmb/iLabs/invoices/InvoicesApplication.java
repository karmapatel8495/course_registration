package com.hsbc.cmb.iLabs.invoices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.hsbc.cmb.iLabs.invoices.model.Invoice;

@RestController
@EnableAutoConfiguration
public class InvoicesApplication {

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping("/")
	String getInvoices(Model model) {
		
		String dbURL = "jdbc:derby://localhost:1527/msadb;create=true";
		String tableName = "Users";
		
		Connection connection = null;
		Statement statement = null;
		ArrayList<String> columnLabels = new ArrayList<String>();
		ArrayList<Invoice> invoicesList = new ArrayList<Invoice>();
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM " + tableName);
			ResultSetMetaData rsmd = results.getMetaData();
			for (int i = 1; i < rsmd.getColumnCount(); i++) {
				System.out.println(rsmd.getColumnLabel(i));
				columnLabels.add(rsmd.getColumnLabel(i));
			}
			while(results.next()){
				int invoiceNumber = results.getInt(1);
				int noOfDaysLeft = results.getInt(2);
				int invoiceValue = results.getInt(3);
				int fundingAvailable = results.getInt(4);
				Invoice currentInvoice = new Invoice();
				currentInvoice.setFundingAvailable(Integer.toString(fundingAvailable));
				currentInvoice.setInvoiceNumber(Integer.toString(invoiceNumber));
				currentInvoice.setInvoiceValue("USD " + Integer.toString(invoiceValue));
				currentInvoice.setNumberOfDaysLeft(Integer.toString(noOfDaysLeft) + " days");
				invoicesList.add(currentInvoice);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Map<String, Invoice> invoicesMap = new HashMap<String, Invoice>();
		for (int i = 0; i < invoicesList.size(); i++) {
			Invoice currentInvoice = invoicesList.get(i);
			invoicesMap.put(currentInvoice.getInvoiceNumber(), currentInvoice);
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(invoicesMap);
		model.addAttribute("invoiceDataArrayList", invoicesList);
		model.addAttribute("invoiceData", jsonData);
		return jsonData;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(InvoicesApplication.class, args);
	}

}