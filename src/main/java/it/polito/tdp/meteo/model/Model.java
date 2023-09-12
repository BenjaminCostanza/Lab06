package it.polito.tdp.meteo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.meteo.DAO.*;

public class Model {
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	
	MeteoDAO dao;
	Ricorsione ricorsione;

	public Model() {
		this.dao = new MeteoDAO();
		this.ricorsione = new Ricorsione();
	}

	// of course you can change the String output with what you think works best
	public List<RilevamentoMedio> getUmiditaMedia(int mese) {
		return this.dao.getRilevamentiMediMese(mese);
	}
	
	// of course you can change the String output with what you think works best
	public List<String> trovaSequenza(int mese) {
		List<String> nomiLocalita = dao.getNomiLocalita();
		Map<String,Citta> rilevamentiLocalitaMese = new HashMap<String, Citta>();
		for (String s : nomiLocalita) {
			rilevamentiLocalitaMese.put(s, dao.getRilevamentiLocalitaMese(mese, s));
		}
		
		//ricorsione
		return ricorsione.trovaSequenza(rilevamentiLocalitaMese);
	}
	
	public int getCosto() {
		return ricorsione.getBestCosto();			
	}

}
