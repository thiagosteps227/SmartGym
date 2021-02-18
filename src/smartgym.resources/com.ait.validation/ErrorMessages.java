package com.ait.validation;

public enum ErrorMessages {
	EMPTY_FIELDS("One or more empty fields"),
	INVALID_INSTRUCTOR("Invalid Instructor");
	
	private String errorMessage;
	
	ErrorMessages(String errMsg){
		this.errorMessage=errMsg;
	}
	
	public String getMsg(){
		return errorMessage;
	}
}