package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println(model.getNercList());
		
		Nerc n = new Nerc(13,"MAAC");
		
		List<Blackout> soluzione = new ArrayList(model.getAnalysis(n, 2, 100));
		
		for(Blackout b: soluzione) {
			System.out.println(b);
		}
		
		System.out.println(model.getBestSomma());
		System.out.println(model.getSommaOre(soluzione));
		
		
		//System.out.println(model.getBlackout(n));
		
		//System.out.println(model.getNumeroAnni(model.getBlackout(n)));


	}

}
