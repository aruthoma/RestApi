package com.legalentity.app.model;

public class LegalEntityShareHolderBean {

	private String shareHolderName;
	private String countryOfShareHolder;
	private int sharesOwned;

	public String getShareHolderName() {
		return shareHolderName;
	}

	public void setShareHolderName(String shareHolderName) {
		this.shareHolderName = shareHolderName;
	}

	public int getSharesOwned() {
		return sharesOwned;
	}

	public String getCountryOfShareHolder() {
		return countryOfShareHolder;
	}

	public void setCountryOfShareHolder(String countryOfShareHolder) {
		this.countryOfShareHolder = countryOfShareHolder;
	}

	public void setSharesOwned(int sharesOwned) {
		this.sharesOwned = sharesOwned;
	}

	public LegalEntityShareHolderBean() {

	}

	public LegalEntityShareHolderBean(String shareHolderName, String countryOfShareHolder, int sharesOwned) {
		super();
		this.shareHolderName = shareHolderName;
		this.countryOfShareHolder = countryOfShareHolder;
		this.sharesOwned = sharesOwned;		
	}

	@Override
	public String toString() {
		return "LegalEntityShareHolderBean [shareHolderName=" + shareHolderName + ", countryOfShareHolder="
				+ countryOfShareHolder + ", sharesOwned=" + sharesOwned + "]";
	}

	

}
