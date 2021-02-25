package com.ait.validation;

import java.util.Arrays;
import java.util.List;

import smartgym.model.Timetable;


public class TimetableValidator {
	
	Timetable timetable = new Timetable();
	List<String> instructors = Arrays.asList("DAVID BLACK", "AUDREY CLARK","JOHN OHANA", "FRANCINE KURTZ","PAUL BYRNE");
	
	public void validateTimetable(Timetable timetable) throws TimetableValidationException {
		this.timetable = timetable;
		checkEmptyFields(timetable);
		checkValidInstructor(timetable, instructors);
	
	}
	
	private void checkEmptyFields(Timetable timetable) throws TimetableValidationException {
		if ((timetable.getClassName().length() == 0) || (timetable.getInstructor().length() == 0) || (timetable.getWeekDay().length() == 0)
				|| (timetable.getClassTime().length() == 0)) {
			throw new TimetableValidationException(ErrorMessages.EMPTY_FIELDS.getMsg());
		}
	}

	private void checkValidInstructor(Timetable timetable, List<String> instructors) throws TimetableValidationException {
		boolean instructorOK = false;
		String instructorEntered = timetable.getInstructor().toUpperCase();
		for (String instructor : instructors) {
			if (instructor.equals(instructorEntered)) {
				instructorOK = true;
				break;
			}
		}
		if (instructorOK == false) {
			throw new TimetableValidationException(ErrorMessages.INVALID_INSTRUCTOR.getMsg());
		}
	}
}
