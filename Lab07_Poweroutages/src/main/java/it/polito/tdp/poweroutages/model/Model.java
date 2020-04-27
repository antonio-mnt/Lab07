package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	private List<Blackout> blackoutList;
	private List<Blackout> soluzione;
	private int maxY;
	private int maxH;
	private int bestSomma;
	private int sommaOre;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	
	
	public int getBestSomma() {
		return bestSomma;
	}



	public int getSommaOre() {
		return sommaOre;
	}



	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<Blackout> getBlackout(Nerc nerc){
		blackoutList = new ArrayList<>(podao.getBlackout(nerc));
		return blackoutList;
	}
	
	public List<Blackout> getAnalysis (Nerc nerc, int maxY, int maxH){
		
		blackoutList = new ArrayList<>(podao.getBlackout(nerc));
		soluzione = new ArrayList<>();
		this.maxY = maxY;
		this.maxH = maxH;
		bestSomma = 0;
		sommaOre = 0;
		
		List<Blackout> parziale = new ArrayList<>();
		
		cerca(parziale,0);
		
		return soluzione;
	}

	private void cerca(List<Blackout> parziale, int livello) {
		
		
		if(getSommaOre(parziale)>maxH) {
			return;
		}
		
		if(getNumeroAnni(parziale)>maxY) {
			return;
		}
		
		//if(livello > 0) {
		/*if(bestSomma == 0) {
			bestSomma = getSomma(parziale);
			soluzione = new ArrayList(parziale);
		}else {*/
			if(getSomma(parziale)>bestSomma) {
				soluzione = new ArrayList(parziale);
				bestSomma = getSomma(parziale);
			}
		//}
		//}
		
		if(livello == blackoutList.size()) {
			return;
		}
		
		
		parziale.add(blackoutList.get(livello));
		cerca(parziale, livello + 1);
		parziale.remove(blackoutList.get(livello));
		
		
		cerca(parziale, livello + 1);
		
	}
	
	
	private int getSomma(List<Blackout> parziale) {
		
		int somma = 0;
		
		for(Blackout b: parziale) {
			somma+= b.getnPersone();
		}
		
		return somma;
		
	}
	
	public int getSommaOre(List<Blackout> parziale) {
		sommaOre = 0;
		
		for(Blackout b: parziale) {
			sommaOre+= b.getOreDisservizio();
		}
		
		return sommaOre;
	}
	
	public int getNumeroAnni(List<Blackout> parziale) {
		
		LocalDateTime tempLmin = null;
		LocalDateTime tempLmax = null;
		int dataInizio = 0;
		int dataFine = 0;
		int n = 0;
		
		for(Blackout b: parziale) {
			if(tempLmin==null) {
				tempLmin= b.getDataInizio();
			}else {
				if(b.getDataInizio().isBefore(tempLmin)) {
					tempLmin= b.getDataInizio();
				}
			}
			
		}
		
		for(Blackout b: parziale) {
			if(tempLmax==null) {
				tempLmax= b.getDataFine();
			}else {
				if(b.getDataFine().isAfter(tempLmax)) {
					tempLmax= b.getDataFine();
				}
			}
			
		}
		
	/*	dataInizio = tempLmin.toLocalDate();
	    dataFine = tempLmax.toLocalDate();
	    System.out.println(dataInizio+"   "+dataFine);
		
		int n = Period.between(dataInizio, dataFine).getYears();*/
		
		if(tempLmin!=null && tempLmax!=null) {
			dataInizio = tempLmin.getYear();
		    dataFine  = tempLmax.getYear();
		
		    n = dataFine-dataInizio;
		}
		
		return n;
		
	}

}
