package Appuntamento;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appuntamento implements Comparable<Appuntamento>{
	
	public LocalDate dataAppuntamento;
	public LocalTime oraInizio;
	public int durata;
	public String nomePersonaAppuntamento;
	public String luogoAppuntamento;
	
	 /**
     * Costruttore della classe `Appuntamento`.
     *
     * @param dataAppuntamento        La data dell'appuntamento.
     * @param oraInizio               L'ora di inizio dell'appuntamento.
     * @param durata                  La durata in minuti dell'appuntamento.
     * @param nomePersonaAppuntamento Il nome della persona coinvolta nell'appuntamento.
     * @param luogoAppuntamento       Il luogo dell'appuntamento.
     */
	
	public Appuntamento(LocalDate dataAppuntamento, LocalTime oraInizio, int durata,  String nomePersonaAppuntamento,String luogoAppuntamento) {
		
		this.dataAppuntamento = dataAppuntamento;
		this.oraInizio = oraInizio;
		this.durata=durata;
		this.nomePersonaAppuntamento = nomePersonaAppuntamento;
		this.luogoAppuntamento = luogoAppuntamento;
	}
	
	 /**
     * Costruttore di copia che crea un nuovo appuntamento a partire da un altro appuntamento esistente.
     *
     * @param a L'appuntamento da cui creare una copia.
     */
	
	public Appuntamento(Appuntamento a)
	{
		this.dataAppuntamento = a.getDataAppuntamento();
		this.oraInizio = a.getOraInizio();
		this.durata = a.getDurata();
		this.nomePersonaAppuntamento = a.getNomePersonaAppuntamento();
		this.luogoAppuntamento = a.getLuogoAppuntamento();
	}
	
	
	  /**
     * Restituisce la data dell'appuntamento.
     *
     * @return La data dell'appuntamento.
     */
	
	public LocalDate getDataAppuntamento() {
		return dataAppuntamento;
	}

	
	  /**
     * Restituisce l'ora di inizio dell'appuntamento.
     *
     * @return L'ora di inizio dell'appuntamento.
     */
	
	public LocalTime getOraInizio() {
		return oraInizio;
	}

	/**
     * Imposta l'ora di inizio dell'appuntamento.
     *
     * @param oraInizio La nuova ora di inizio dell'appuntamento.
     */
	
	public void setOraInizio(LocalTime oraInizio) {
		this.oraInizio = oraInizio;
	}

	/**
     * Restituisce la durata in minuti dell'appuntamento.
     *
     * @return La durata in minuti dell'appuntamento.
     */
	
	public int getDurata() {
		return this.durata;
	}

	/**
     * Imposta la durata in minuti dell'appuntamento.
     *
     * @param durata La nuova durata in minuti dell'appuntamento.
     */
	
	public void setOraFine(int durata) {
		this.durata = durata;
	}

	 /**
     * Restituisce il nome della persona coinvolta nell'appuntamento.
     *
     * @return Il nome della persona coinvolta nell'appuntamento.
     */
	
	public String getNomePersonaAppuntamento() {
		return this.nomePersonaAppuntamento;
	}

	 /**
     * Restituisce il luogo dell'appuntamento.
     *
     * @return Il luogo dell'appuntamento.
     */
	
	public String getLuogoAppuntamento() {
		return luogoAppuntamento;
	}

	@Override
	public String toString() {
		return "Appuntamento [Data Appuntamento :" + dataAppuntamento + ", Ore Inizio Appuntamento" + oraInizio + ", Durata" + durata
				+ ", Nome Persona:" + nomePersonaAppuntamento + ", Luogo Appuntamento=" + luogoAppuntamento
				+ "]";
	}

	   /**
     * Imposta la data dell'appuntamento.
     *
     * @param dataAppuntamento La nuova data dell'appuntamento.
     */
	
	public void setDataAppuntamento(LocalDate dataAppuntamento) {
		this.dataAppuntamento = dataAppuntamento;
	}



    /**
     * Imposta il nome della persona coinvolta nell'appuntamento.
     *
     * @param nomePersonaAppuntamento Il nuovo nome della persona coinvolta nell'appuntamento.
     */
	
	public void setNomePersonaAppuntamento(String nomePersonaAppuntamento) {
		this.nomePersonaAppuntamento = nomePersonaAppuntamento;
	}

	 /**
     * Imposta il luogo dell'appuntamento.
     *
     * @param luogoAppuntamento Il nuovo luogo dell'appuntamento.
     */
	
	public void setLuogoAppuntamento(String luogoAppuntamento) {
		this.luogoAppuntamento = luogoAppuntamento;
	}
	
	
	/**
     * Verifica se l'orario dell'appuntamento è in conflitto con un altro appuntamento.
     *
     * @param app L'appuntamento con cui verificare il conflitto.
     * @return 0 se non c'è conflitto, 1 se c'è conflitto.
     */
	
	public int controlloOrario(Appuntamento app)
	{
		if (this.dataAppuntamento.isEqual(app.getDataAppuntamento()) && (app.oraInizio.equals(this.getOraInizio()) || (this.oraInizio.isBefore(app.getOraInizio()) && app.getOraInizio().isBefore(this.oraInizio.plusMinutes(this.durata))) || (app.getOraInizio().isBefore(this.oraInizio) && this.oraInizio.isBefore(app.getOraInizio().plusMinutes(app.getDurata())))))
			{
				return 0;
			}
		
		return 1;
	}

	
	
	@Override
	public int compareTo(Appuntamento app)
	{
		
		if (this.dataAppuntamento.isEqual(app.getDataAppuntamento()))
		{
			
			if (app.oraInizio.equals(this.getOraInizio()))
			{
				return 0;
			}
			else if(this.oraInizio.isAfter(app.getOraInizio()))
			{
				return 1;
			}
			else
			{
				return -1;
			}
		}
		else if(this.dataAppuntamento.isBefore(app.getDataAppuntamento()))
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}

	
}


