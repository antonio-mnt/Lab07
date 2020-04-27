package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class Blackout {
	
	private int nercId;
	private LocalDateTime dataInizio;
	private LocalDateTime dataFine;
	private long oreDisservizio;
	private int nPersone;
	
	
	public Blackout(int nercId, LocalDateTime dataInizio, LocalDateTime dataFine, int nPersone) {
		super();
		this.nercId = nercId;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.oreDisservizio = Duration.between(dataInizio, dataFine).getSeconds()/3600;
		this.nPersone = nPersone;
		
	}

	public int getNercId() {
		return nercId;
	}

	public void setNercId(int nercId) {
		this.nercId = nercId;
	}

	public LocalDateTime getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDateTime getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}

	public int getnPersone() {
		return nPersone;
	}

	public void setnPersone(int nPersone) {
		this.nPersone = nPersone;
	}
	

	public long getOreDisservizio() {
		return oreDisservizio;
	}

	public void setOreDisservizio(int oreDisservizio) {
		this.oreDisservizio = oreDisservizio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataFine == null) ? 0 : dataFine.hashCode());
		result = prime * result + ((dataInizio == null) ? 0 : dataInizio.hashCode());
		result = prime * result + nPersone;
		result = prime * result + nercId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blackout other = (Blackout) obj;
		if (dataFine == null) {
			if (other.dataFine != null)
				return false;
		} else if (!dataFine.equals(other.dataFine))
			return false;
		if (dataInizio == null) {
			if (other.dataInizio != null)
				return false;
		} else if (!dataInizio.equals(other.dataInizio))
			return false;
		if (nPersone != other.nPersone)
			return false;
		if (nercId != other.nercId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%-20s %-20s %-10s %-20s", dataInizio, dataFine, oreDisservizio, nPersone);
	}

	

}
