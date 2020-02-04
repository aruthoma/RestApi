package com.legalentity.app.model;

import java.time.LocalDate;
import java.util.Date;

public class LegalEntityBean {
	
	private String entityName;
	private Date dateOfIncorp;
	private String countryOfIncorp;
	private int totalShares;
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public Date getDateOfIncorp() {
		return dateOfIncorp;
	}
	public void setDateOfIncorp(Date dateOfIncorp) {
		
		this.dateOfIncorp = dateOfIncorp;
	}
	public String getCountryOfIncorp() {
		return countryOfIncorp;
	}
	public void setCountryOfIncorp(String countryOfIncorp) {
		this.countryOfIncorp = countryOfIncorp;
	}
	public int getTotalShares() {
		return totalShares;
	}
	public void setTotalShares(int totalShares) {
		this.totalShares = totalShares;
	}
	
	public LegalEntityBean(String entityName, Date dateOfIncorp, String countryOfIncorp, int totalShares) {
		super();
		this.entityName = entityName;
		this.dateOfIncorp = dateOfIncorp;
		this.countryOfIncorp = countryOfIncorp;
		this.totalShares = totalShares;
	}
	
	public LegalEntityBean() {
		
	}
	
	@Override
	public String toString() {
		return "LegalEntityResourceBean [entityName=" + entityName + ", dateOfIncorp=" + dateOfIncorp
				+ ", countryOfIncorp=" + countryOfIncorp + ", totalShares=" + totalShares + "]";
	}
	
	public static Date parseDate(String date) {
		try {
			LocalDate localDate = LocalDate.parse(date);
			return java.sql.Date.valueOf(localDate);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	

}
