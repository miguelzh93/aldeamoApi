package com.aldeamo.pojo;

public class Respuesta {
	private String error;
	private Object cuerpo;

	
	
	public Respuesta() {
		this.error = "0";
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(Object cuerpo) {
		this.cuerpo = cuerpo;
	}

}
