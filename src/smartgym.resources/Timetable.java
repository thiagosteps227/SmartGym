package com.ait.smartgym;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Timetable {
	
	private int classCode;
	private String className;
	private String instructor;
	private String weekDay;
	private String classTime;
	
	public int getClassCode() {
		return classCode;
	}
	
	public void setClassCode(int classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public String getClassTime() {
		return classTime;
	}

	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}
	
	
	
	
}
