package Gestore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import Agenda.Agenda;
import Appuntamento.Appuntamento;

/**
 * La classe Gestore rappresenta il gestore principale dell'applicazione.
 */

public class Gestore {
	public int scelta;
	public Scanner sc;
	public ArrayList<Agenda> listaAgenda;
	public int i;
	public Agenda ag;
	public int contatore;
	
	   /**
     * Costruttore della classe Gestore.
     * Inizializza le variabili di istanza e crea una nuova istanza di Gestore.
     */
	
	public Gestore(){
		this.scelta =0;
		this.sc=new Scanner(System.in);
		this.listaAgenda=new ArrayList<>();
		
	}
	
	/**
     * Gestisce l'applicazione principale, richiedendo il login e presentando il menu principale.
     *
     * @throws FileNotFoundException Eccezione lanciata in caso di errore di file non trovato.
     * @throws ClassNotFoundException    Eccezione lanciata in caso di errore di classe non trovata.
     * @throws IOException              Eccezione lanciata in caso di errore di input/output.
     */
	
	public void gestione() throws FileNotFoundException, ClassNotFoundException, IOException {
		this.login("login");
		System.out.println("Benvenuto-a nell'applicazione\n");
		this.menu1();
		
	}
	
	 /**
     * Gestisce il menu principale dell'applicazione, consentendo all'utente di eseguire diverse operazioni.
     *
     * @throws FileNotFoundException Eccezione lanciata in caso di errore di file non trovato.
     * @throws ClassNotFoundException    Eccezione lanciata in caso di errore di classe non trovata.
     * @throws IOException              Eccezione lanciata in caso di errore di input/output.
     */
	
	public void menu1() throws FileNotFoundException, ClassNotFoundException, IOException {
		
			String nomeFile;
			String nome; //nome AGENDA
			
			System.out.println("Premere 1 creare una nuova agenda\nPremere 2 Cancellare un'agenda\nPremere 3 creare una nuova agenda leggendo gli appuntamenti da file\n"
					+ "Premere 4 scrivere un'agenda su file\nPremere 5 gestire un agenda presente\nPremere 6 per uscire dall'Applicazione");
			this.scelta = Integer.parseInt(this.sc.nextLine());
			switch(scelta) {
					case 1:
					System.out.println("Inserire il nome dell'Agenda");
					nome=this.sc.nextLine();
					Agenda ag=new Agenda(nome);
					this.listaAgenda.add(ag);
					if(this.listaAgenda.contains(ag)) {
						System.out.println("Agenda inserita correttamente\n");
					}
					else {
						System.out.println("l'agenda non e' stato inserita\n");
					}
					break;
					case 2:
					i=this.getAgenda();
					ag= this.listaAgenda.get(i);
					this.listaAgenda.remove(i);
					if(!this.listaAgenda.contains(ag)) {
						System.out.println("Agenda è stata elimata correttamente\n");
					}
					else {
						System.out.println("l'agenda non e' stata eliminata\n");
					}
					break;
					case 3:
						System.out.println("Inserire il nome del file da leggere");
						nomeFile=this.sc.nextLine();
						this.login(nomeFile);
						this.menu1();
						break;
					case 4:
					System.out.println("Inserire il nome da dare al file");
					nomeFile=this.sc.nextLine();
					i=this.getAgenda();
					ag=this.listaAgenda.get(i);
					this.scriviAgenda(nomeFile,ag);
					break;
					case 5:
						i=this.getAgenda();
						ag=this.listaAgenda.get(i);
						this.menu(ag);
					case 6:
						this.uscitaApplicazione();
		
			}
		
	
	}
	
	/**
     * Esegue il logout dell'applicazione, salvando i dati su file.
     *
     * @param name Nome del file in cui salvare i dati.
     * @throws IOException Eccezione lanciata in caso di errore di input/output.
     */
	
	public void logout(String name) throws IOException {
        if (name == null)
            throw new IllegalArgumentException("Nome o nome file non valido");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\" + name + ".txt"))) {
            for (Agenda a : this.listaAgenda) {
            	for(Appuntamento x:a.getAppuntamenti()) {
                writer.write(String.format("%s,%s,%s,%d,%s,%s%n",
                        a.getNome(), x.getDataAppuntamento(), x.getOraInizio(), x.getDurata(), x.getNomePersonaAppuntamento(), x.getLuogoAppuntamento()));
            }
           }
        } catch (IOException e) {
            throw new IOException("Errore durante la scrittura del file", e);
        }
    }

	 /**
     * Gestisce il menu dell'agenda, consentendo all'utente di eseguire diverse operazioni sugli appuntamenti.
     *
     * @param ag Agenda su cui eseguire le operazioni.
     * @throws FileNotFoundException    Eccezione lanciata in caso di errore di file non trovato.
     * @throws ClassNotFoundException     Eccezione lanciata in caso di errore di classe non trovata.
     * @throws IOException               Eccezione lanciata in caso di errore di input/output.
     */

	public void menu(Agenda ag) throws FileNotFoundException, ClassNotFoundException, IOException
	{	
		System.out.println("Premere 1 Aggiungere un appuntamento\nPremere 2 Modificare un appuntamento\n"
				+ "Premere 3 Eliminare un appuntamento \nPremere 4 Per cercare un appunmento\nPremere 5 Per visualizzare gli appuntamenti dell'agenda"
				+ "Premere 6 per tornare al MENU principale");
		this.scelta = Integer.parseInt(this.sc.nextLine());
		switch(scelta) {
			case 1:
				this.richiestaAppuntamento(ag);
				break;
			case 2:
				this.modificaAppuntamento(ag);
				break;
				
			case 3:
				this.eliminaAppuntamento(ag);
				break;
			case 4:
				ArrayList<Appuntamento> app = new ArrayList<>();
				app=this.cercaAppuntamento(ag);
				if(app.size()==0) {
					
					System.out.println("La ricerca non ha prodotto nessun risultato");
				}
				else {
					System.out.println(app.toString());
				}
				break;
			case 5:
				System.out.println(ag.toString());
				break;
			case 6:
				this.menu1();
				break;
		}
	}
	

	  /**
     * Elimina un appuntamento dall'agenda.
     *
     * @param a Agenda da cui eliminare l'appuntamento.
     */

	public void eliminaAppuntamento(Agenda a) {
		int i=0,scelta;
		ArrayList<Appuntamento> app=new ArrayList<>();
		app=a.getAppuntamenti();
		for(Appuntamento x:app) {
			System.out.println("Indice:" + i + " Nome Agenda:" + x.toString());
			i++;
		}
		System.out.println("Inserisci l'indice dell'appuntamento da modificare");
		scelta=Integer.parseInt(this.sc.nextLine());
		Appuntamento appDelete=app.get(scelta);
		a.deleteAppuntamento(appDelete);
	}
	

    /**
     * Richiede all'utente di inserire i dati per un nuovo appuntamento e lo aggiunge all'agenda.
     *
     * @param ag Agenda in cui aggiungere l'appuntamento.
     */
	
	public void richiestaAppuntamento(Agenda ag) {
		Appuntamento app=ag.registrazioneAppuntamento();
		ag.addAppuntamento(app);
		
		
	}
	
    /**
     * Scrive l'agenda su un file specifico.
     *
     * @param name Nome del file in cui scrivere l'agenda.
     * @param a    Agenda da scrivere sul file.
     * @throws IOException Eccezione lanciata in caso di errore di input/output.
     */
	
	public void scriviAgenda(String name, Agenda a) throws IOException {
	    if (name == null)
	        throw new IllegalArgumentException("Nome o nome file non valido");
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\" + name + ".txt"))) {
	        for (Appuntamento app : a.getAppuntamenti()) {
	            writer.write(String.format("%s,%s,%s,%d,%s,%s%n",
	                    name, app.getDataAppuntamento(), app.getOraInizio(), app.getDurata(), app.getNomePersonaAppuntamento(), app.getLuogoAppuntamento()));
	        }
	    } catch (IOException e) {
	        throw new IOException("Errore durante la scrittura del file", e);
	    }
	}
	
	 /**
     * Cerca appuntamenti nell'agenda in base a una determinata opzione (per data o per nome).
     *
     * @param ag Agenda in cui cercare gli appuntamenti.
     * @return Una lista di appuntamenti che corrispondono alla ricerca.
     */

	public ArrayList<Appuntamento>cercaAppuntamento(Agenda ag) {
		int opzione=0;
		System.out.println("Premere 1 per effettuare la ricerca degli appuntamenti per data\nPremere 2 per effettuare la ricerca degli appuntamenti per nome");
		opzione = Integer.parseInt(this.sc.nextLine());
		ArrayList<Appuntamento> appuntamenti= new ArrayList<Appuntamento>();
		appuntamenti=ag.ricercaApp(opzione);
		return appuntamenti;
	}
	
	  /**
     * Modifica un appuntamento nell'agenda.
     *
     * @param a Agenda in cui modificare l'appuntamento.
     */
	
	public void modificaAppuntamento(Agenda a) {
		int i=0,scelta,res;
		
		ArrayList<Appuntamento> app=new ArrayList<>();
		app=a.getAppuntamenti();
		for(Appuntamento x:app) {
			System.out.println("Indice:" + i + " Nome Agenda:" + x.toString());
			i++;
		}
		//mettere controllo indice
		System.out.println("Inserisci l'indice dell'appuntamento da modificare");
		scelta=Integer.parseInt(this.sc.nextLine());
		Appuntamento app_mod=app.get(scelta);
		Appuntamento newApp=a.registrazioneAppuntamento();
		res=a.controlloModificaAppuntamento(app_mod,newApp);
		if(res==1) {
			a.deleteAppuntamento(app_mod);
			a.addAppuntamento(newApp);
			System.out.println("L'appuntamento è stato modificato correttamente\n");
			
		}
		else {
			System.out.println("non e' possibile modificare l'appuntamento a causa dell'orario");
		}
		
	}
	
	/**
     * Esegue l'uscita dall'applicazione, salvando i dati su file e terminando il programma.
     *
     * @throws IOException Eccezione lanciata in caso di errore di input/output.
     */
	
	public void uscitaApplicazione() throws IOException
	{
		System.out.println("\nUscita dall'applicazione in corso...");
		System.out.print("\n*/------------------------------------------------------------\\*");
		this.logout("login");
		System.exit(0);
	}
	
	 /**
     * Esegue il login dell'applicazione leggendo i dati da un file specifico.
     *
     * @param filename Nome del file da cui leggere i dati.
     * @throws IOException Eccezione lanciata in caso di errore di input/output.
     */

	public void login(String filename) throws IOException {
        if (filename == null || filename.trim().isEmpty())
            throw new IllegalArgumentException("Nome file non valido");
       
        Path path = Paths.get( "src//"+filename + ".txt");       
        try (BufferedReader reader = Files.newBufferedReader(path)) {
        	 //Agenda ag;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 6)
                    throw new IllegalArgumentException("Formato file non corretto");
    
                String agendaName = fields[0].trim();
                if (agendaName.isEmpty()) 
                    throw new IllegalArgumentException("Nome agenda non presente");
                Agenda ag = null;
                for (Agenda a : this.listaAgenda) {
                    if (a.getNome().equals(agendaName)) {
                        ag = a;
                        break;
                    }
                }

                if (ag == null) {
                    ag = new Agenda(agendaName);
                    this.listaAgenda.add(ag);
                }
          
                
                
                if (ag != null) {
                    LocalDate date;
                    LocalTime oraInizio;
                    int durata;
                    
                    try {
                        date = LocalDate.parse(fields[1]);
                        oraInizio = LocalTime.parse(fields[2]);
                        durata = Integer.parseInt(fields[3]);
                    } catch (DateTimeParseException | NumberFormatException e) {
                        throw new IllegalArgumentException("Formato non valido per data/ora/durata nel file");
                    }

                    if (fields[4].trim().isEmpty() || fields[5].trim().isEmpty())
                        throw new IllegalArgumentException("Nome appuntamento o luogo non valido nel file");
                    Appuntamento a=new Appuntamento(date,oraInizio,durata,fields[4], fields[5]);
                    ag.addAppuntamento(a);
                }
                
                
            }
          
        } catch (IOException e) {
            throw new IOException("Errore durante la lettura del file", e);
        }
    }

    /**
     * Ottiene l'indice dell'agenda su cui operare.
     *
     * @return L'indice dell'agenda selezionata dall'utente.
     */

	public int getAgenda() {
		if(this.listaAgenda.size()==0) {
			String nome;
			System.out.println("Inserire il nome di un agenda");
			nome=this.sc.nextLine();
			Agenda x=new Agenda(nome);
			this.listaAgenda.add(x);
		}
		int i=0,scelta;
		for(Agenda a:this.listaAgenda) {
			System.out.println("Indice:" + i + " Nome Agenda:" + a.getNome());
			i++;
		}
		
		System.out.println("Digita l'indice dell'agenda su cui operare");
		scelta=Integer.parseInt(this.sc.nextLine());
		return scelta;
	}

	

}
		