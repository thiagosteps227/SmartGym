package com.ait.smartgym;

public class Classtable {

	private String className;
	private int personLimit;
	private int pricePerClass;
	private int priceTwelveWeeks;
	private int classID;

	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public int getPersonLimit() {
		return personLimit;
	}
	
	public void setPersonLimit(int personLimit) {
		this.personLimit = personLimit;
	}
	
	public int getPricePerClass() {
		return pricePerClass;
	}
	
	public void setPricePerClass(int pricePerClass) {
		this.pricePerClass = pricePerClass;
	}
	
	public int getPriceTwelveWeeks() {
		return priceTwelveWeeks;
	}
	
	public void setPriceTwelveWeeks(int priceTwelveWeeks) {
		this.priceTwelveWeeks = priceTwelveWeeks;
	}
	
	public int getClassID() {
		return classID;
	}
	
	public void setClassID(int classID) {
		this.classID = classID;
	}
}
