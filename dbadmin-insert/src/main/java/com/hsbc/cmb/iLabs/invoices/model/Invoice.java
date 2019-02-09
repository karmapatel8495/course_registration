package com.hsbc.cmb.iLabs.invoices.model;

public class Invoice {

	/**
	 * invoiceNumber
	 */
	private String invoiceNumber;

	/**
	 * numberOfDaysLeft
	 */
	private String numberOfDaysLeft;

	/**
	 * invoiceValue
	 */
	private String invoiceValue;

	/**
	 * fundingAvailable
	 */
	private String fundingAvailable;

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getNumberOfDaysLeft() {
		return numberOfDaysLeft;
	}

	public void setNumberOfDaysLeft(String numberOfDaysLeft) {
		this.numberOfDaysLeft = numberOfDaysLeft;
	}

	public String getInvoiceValue() {
		return invoiceValue;
	}

	public void setInvoiceValue(String invoiceValue) {
		this.invoiceValue = invoiceValue;
	}

	public String getFundingAvailable() {
		return fundingAvailable;
	}

	public void setFundingAvailable(String fundingAvailable) {
		this.fundingAvailable = fundingAvailable;
	}
}
