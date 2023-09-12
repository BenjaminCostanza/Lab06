package it.polito.tdp.meteo.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Rilevamento {
	
	private String localita;
	private LocalDateTime data;
	private int umidita;

	public Rilevamento(String localita, LocalDateTime data, int umidita) {
		this.localita = localita;
		this.data = data;
		this.umidita = umidita;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public int getUmidita() {
		return umidita;
	}

	public void setUmidita(int umidita) {
		this.umidita = umidita;
	}

	// @Override
	// public String toString() {
	// return localita + " " + data + " " + umidita;
	// }

	@Override
	public String toString() {
		return String.valueOf(umidita);
	}

	

}
