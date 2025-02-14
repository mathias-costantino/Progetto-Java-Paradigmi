package Agenda;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Appuntamento.Appuntamento;


/**
 * Classe Agenda rappresenta un'agenda per la gestione di appuntamenti.
 */

public class Agenda implements Iterable<Appuntamento> {
	
	public String nome;
	public ArrayList<Appuntamento> appuntamenti;
	public Scanner sc;
	
	  /**
     * Costruttore per la classe Agenda.
     *
     * @param nome Il nome dell'agenda.
     */
	
		public Agenda(String nome) {
		this.nome = nome;
		this.appuntamenti=new ArrayList<>();
		this.sc=new Scanner(System.in);
	}
	
		   /**
	     * Rimuove un appuntamento dall'agenda.
	     *
	     * @param app L'appuntamento da rimuovere.
	     */
		
	public void deleteAppuntamento(Appuntamento app) {
		this.appuntamenti.remove(app);
	}
	
	 /**
     * Aggiunge un appuntamento all'agenda dopo aver verificato la disponibilità dell'orario.
     *
     * @param app L'appuntamento da aggiungere.
     * @return 0 se l'appuntamento non può essere aggiunto, 1 se aggiunto con successo.
     */
	
	public int addAppuntamento(Appuntamento app) {
		int res=0;
		if(this.appuntamenti.size()==0) {
		this.appuntamenti.add(app);
		}
		else {
			for(Appuntamento a:this.appuntamenti) {
				res=a.controlloOrario(app);
				if(res==0) {
					System.out.println("Impossibile aggiungere l'appuntamento");
					break;
				}
				else {
					res=1;
				}
			}
			if(res==1) {
				this.appuntamenti.add(app);
			}
		}
		return res;
		
	}
	
	  /**
     * Restituisce una lista di appuntamenti presenti nell'agenda.
     *
     * @param a L'agenda da cui ottenere gli appuntamenti.
     * @return Una lista di appuntamenti.
     */
	
	public ArrayList<Appuntamento> getAppuntamenti(Agenda a){
		
		return this.appuntamenti;
	}


	/**
 * @return the nome
 */
public String getNome() {
	return this.nome;
}
	
/**
 * Restituisce il numero di appuntamenti nell'agenda.
 *
 * @return Il numero di appuntamenti.
 */
	
	public int appSize()
	{
		return this.appuntamenti.size();
	}
	
	  /**
     * Restituisce una lista di appuntamenti nell'agenda.
     *
     * @return Una lista di appuntamenti.
     */
	
	public ArrayList<Appuntamento> getAppuntamenti(){
		return this.appuntamenti;
	}
	

    /**
     * Restituisce una rappresentazione in formato stringa dell'agenda.
     *
     * @return Una stringa che rappresenta l'agenda.
     */
	
	@Override
	public String toString() {
		return "Agenda [nome=" + nome + ", appuntamenti=" + appuntamenti + "]";
	}
	
	  /**
     * Restituisce un iteratore per gli appuntamenti nell'agenda.
     *
     * @return Un iteratore per gli appuntamenti.
     */
	
	@Override
	public Iterator<Appuntamento> iterator() {
		return new Iteratore();
	}
	
	
	public class Iteratore implements Iterator<Appuntamento>
	{
		public int indice;

		public Iteratore()
		{
			indice = 0;
		}

		public boolean hasNext()
		{
			return (indice<appuntamenti.size());
		}
		
		 /**
         * Restituisce il prossimo appuntamento nell'iterazione.
         *
         * @return Il prossimo appuntamento se presente, altrimenti null.
         */
		
		public Appuntamento next()
		{
			if (!this.hasNext())
			{
				return null;
			}
			else
			{
				return appuntamenti.get(indice++);
			}
		}
	}
	
    /**
     * Verifica se è possibile modificare un appuntamento nella lista degli appuntamenti.
     *
     * @param app_mod L'appuntamento da modificare.
     * @param newApp  Il nuovo appuntamento proposto.
     * @return 0 se non è possibile modificare l'appuntamento, 1 se la modifica è possibile.
     */

	public int controlloModificaAppuntamento(Appuntamento app_mod, Appuntamento newApp) {
		int res=0;
		for(Appuntamento a:this.appuntamenti) {
			res=a.controlloOrario(newApp);
			if(res==0) {
				break;
			}
			else {
				res=1;
			}
		}
		return res;
		
	}
	
	  /**
     * Registra un nuovo appuntamento inserendo dati come data, orario, durata, nome della persona e luogo.
     *
     * @return Il nuovo appuntamento registrato.
     */
	
	public Appuntamento registrazioneAppuntamento() {
		LocalDate date;
		String data_app;
		System.out.println("Inserire data Appuntamento nel formato ('dd-MM-yyyy')");
		data_app=this.sc.nextLine();
		date=LocalDate.parse(data_app, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		//System.out.println(date);
		String oraI;
		LocalTime oraInizio;
		int durata;
		String nomePersonaAppuntamento;
		String luogoAppuntamento;
		System.out.println("Inserire ora Inizio Appuntamento nel formato ('HH-mm')");
		oraI=this.sc.nextLine();
		oraInizio = LocalTime.parse(oraI, DateTimeFormatter.ofPattern("HH-mm"));
		System.out.println("Inserire la durata dell'appuntamento");
		durata=Integer.parseInt(this.sc.nextLine());
		System.out.println("Inserire Nome Persona Appuntamento");
		nomePersonaAppuntamento=this.sc.nextLine();
		System.out.println("Inserire Luogo Appuntamento");
		luogoAppuntamento=this.sc.nextLine();
		Appuntamento app=new Appuntamento(date, oraInizio, durata, nomePersonaAppuntamento, luogoAppuntamento);
		return app;
	}
	
	 /**
     * Ricerca appuntamenti in base al tipo di ricerca (per data o per nome della persona).
     *
     * @param i Tipo di ricerca (1 per data, 2 per nome della persona).
     * @return Una lista di appuntamenti corrispondenti alla ricerca.
     */

	public ArrayList<Appuntamento> ricercaApp(int i) {
		LocalDate date;
		String data_app;
		String nomePersonaAppuntamento;
		ArrayList<Appuntamento> app= new ArrayList<>();
		if(i==1) {
			//data
			System.out.println("Inserire data Appuntamento nel formato ('dd-MM-yyyy')");
			data_app=this.sc.nextLine();
			date=LocalDate.parse(data_app, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			if(i==1) {
			for(Appuntamento a:this.appuntamenti) {
				if(a.getDataAppuntamento().equals(date)) {
					app.add(a);
				}
			}
		}
		else {
			System.out.println("Inserire Nome Persona Appuntamento");
			nomePersonaAppuntamento=this.sc.nextLine();
			for(Appuntamento a:this.appuntamenti) {
				if(a.getNomePersonaAppuntamento().equals(nomePersonaAppuntamento)) {
					app.add(a);
				}
			}
		}
		}
		return app;
	}
}

