package com.nelioalves.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	private List<FieldMessage> errors = new ArrayList<>(); 
	
	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldname, String message) {
		errors.add(new FieldMessage(fieldname,message));
	}

	public ValidationError(Integer status, String msg, long timeStamp) {
		super(status, msg, timeStamp);
	
	}

	

}
