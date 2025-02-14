package Appuntamento;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppuntamentoTest {

    private Appuntamento app1;
    private Appuntamento app2;

    @BeforeEach
    public void setUp() {
        LocalDate dataAppuntamento1 = LocalDate.of(2023, 11, 10);
        LocalTime oraInizio1 = LocalTime.of(9, 0);
        int durata1 = 60;
        String nomePersonaAppuntamento1 = "Persona1";
        String luogoAppuntamento1 = "Luogo1";
        app1 = new Appuntamento(dataAppuntamento1, oraInizio1, durata1, nomePersonaAppuntamento1, luogoAppuntamento1);

        LocalDate dataAppuntamento2 = LocalDate.of(2023, 11, 10);
        LocalTime oraInizio2 = LocalTime.of(10, 0);
        int durata2 = 30;
        String nomePersonaAppuntamento2 = "Persona2";
        String luogoAppuntamento2 = "Luogo2";
        app2 = new Appuntamento(dataAppuntamento2, oraInizio2, durata2, nomePersonaAppuntamento2, luogoAppuntamento2);
    }

    @Test
    public void testControlloOrarioOverlap() {
        int result = app1.controlloOrario(app2);
        assertEquals(1, result);
    }

    @Test
    public void testControlloOrarioNoOverlap() {
        app2.setOraInizio(LocalTime.of(11, 0));
        int result = app1.controlloOrario(app2);
        assertEquals(1, result);
    }

    @Test
    public void testCompareToEqual() {
        int result = app1.compareTo(app2);
        assertEquals(-1, result);
    }

    @Test
    public void testCompareToLessThan() {
        app2.setDataAppuntamento(LocalDate.of(2023, 11, 11));
        int result = app1.compareTo(app2);
        assertEquals(-1, result);
    }

    @Test
    public void testCompareToGreaterThan() {
        app2.setDataAppuntamento(LocalDate.of(2023, 11, 9));
        int result = app1.compareTo(app2);
        assertEquals(1, result);
    }
}
