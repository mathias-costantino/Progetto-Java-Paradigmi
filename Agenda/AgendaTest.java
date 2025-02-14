package Agenda;

import Appuntamento.Appuntamento;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AgendaTest {
    private Agenda agenda;

    @Before
    public void setUp() {
        agenda = new Agenda("Test Agenda");
    }

    @Test
    public void testAddDeleteAppuntamento() {
        LocalDate date = LocalDate.parse("01-01-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalTime time = LocalTime.parse("10:00", DateTimeFormatter.ofPattern("HH:mm"));
        Appuntamento app = new Appuntamento(date, time, 60, "Alice", "Location");

        assertEquals(0, agenda.addAppuntamento(app));
        assertEquals(1, agenda.appSize());

        agenda.deleteAppuntamento(app);
        assertEquals(0, agenda.appSize());
    }
    
    private Appuntamento creaAppuntamento(String data, String ora, String nomePersona) {
        LocalDate date = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalTime time = LocalTime.parse(ora, DateTimeFormatter.ofPattern("HH:mm"));
        return new Appuntamento(date, time, 60, nomePersona, "Location");
    }
    
    @Test
    public void testRicercaAppPerData() {
        agenda.addAppuntamento(creaAppuntamento("01-01-2023", "10:00", "Alice"));
        agenda.addAppuntamento(creaAppuntamento("02-01-2023", "10:00", "Bob"));

        ArrayList<Appuntamento> result = agenda.ricercaApp(1);
        assertEquals(1, result.size());
        assertEquals("01-01-2023", result.get(0).getDataAppuntamento().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    @Test
    public void testRicercaAppPerNomePersona() {
        agenda.addAppuntamento(new Appuntamento(LocalDate.parse("01-01-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                                                LocalTime.parse("10:00", DateTimeFormatter.ofPattern("HH:mm")),
                                                60, "Alice", "Location"));
        agenda.addAppuntamento(new Appuntamento(LocalDate.parse("01-01-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                                                LocalTime.parse("10:00", DateTimeFormatter.ofPattern("HH:mm")),
                                                60, "Bob", "Location"));

        ArrayList<Appuntamento> result = agenda.ricercaApp(2);
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getNomePersonaAppuntamento());
    }


}
