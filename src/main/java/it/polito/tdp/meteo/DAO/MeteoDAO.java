package it.polito.tdp.meteo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.model.Citta;
import it.polito.tdp.meteo.model.Rilevamento;
import it.polito.tdp.meteo.model.RilevamentoMedio;
import it.polito.tdp.*;

public class MeteoDAO {
	
	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getTimestamp("Data").toLocalDateTime(), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese, String localita) {

		return null;
	}

	
	public List<RilevamentoMedio> getRilevamentiMediMese(int mese){
		
		final String sql = "SELECT Localita, AVG(umidita) as umiditaMedia "
				+ "FROM situazione "
				+ "WHERE MONTH(Data)=? "
				+ "GROUP BY localita";
		
		List<RilevamentoMedio> rilevamenti = new ArrayList<RilevamentoMedio>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, mese);;
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				RilevamentoMedio r = new RilevamentoMedio(rs.getString("Localita"), mese, rs.getDouble("umiditaMedia"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	public Citta getRilevamentiLocalitaMese(int mese,String localita){
		
		final String sql = "SELECT data, umidita "
				+ "FROM situazione " 
				+ "WHERE MONTH(DATA) = ? AND Localita = ? " 
				+ "ORDER BY data ASC";
		
		Citta risultato = new Citta(localita, mese);
		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, mese);
			st.setString(2, localita);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Rilevamento r = new Rilevamento(localita, rs.getTimestamp("Data").toLocalDateTime(), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}
			risultato.setRilevamenti(rilevamenti);
			conn.close();
			return risultato;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
	}
	
	
	/**
	 * Metodo con cui ottengo tutte le località presenti nel database
	 * @return La lista delle località come lista di stringhe
	 */
	public List<String> getNomiLocalita() {
		final String sql = "SELECT Localita FROM situazione Group BY Localita";
		List<String> risultato = new ArrayList<String>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				risultato.add(rs.getString("Localita"));
			}
			conn.close();
			return risultato;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}


