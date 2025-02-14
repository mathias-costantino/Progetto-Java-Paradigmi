package Main;



import java.io.FileNotFoundException;
import java.io.IOException;

import Gestore.Gestore;

/**
 * La classe Main avvio il programma
 * 
 * @author	ANNA VITERITTI 20033851
 * @author  MATHIAS COSTANTINO 20043922
 */
public class Main {
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException
	{
	    Gestore gest = new Gestore();
	    
		gest.gestione();
	}
}