import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class BoekKastTest {

    private BoekKast boekKast;

    @BeforeEach
    void setUp() {
        // Maak een CSVBoekKast met het pad naar het CSV-bestand
        boekKast = new CSVBoekKast("data.csv");
    }

    @Test
    void zoekBoekenOpNaam_HarryPotter() {
        // Zoek boeken met de naam "Harry Potter"
        List<Boek> gevondenBoeken = boekKast.zoekBoekenOpNaam("Harry Potter");

        // Controleer of ten minste één boek met de naam "Harry Potter" is gevonden
        assertFalse(gevondenBoeken.isEmpty(), "Boek met naam 'Harry Potter' niet gevonden");

        // Controleer de eigenschappen van het eerste gevonden boek
        Boek eersteBoek = gevondenBoeken.get(0);
        assertEquals("Harry Potter", eersteBoek.getNaam(), "Verkeerde boeknaam gevonden");
        assertEquals("Fantasy", eersteBoek.getGenres()[0], "Verkeerd genre gevonden");
        assertEquals(2000, eersteBoek.getJaar(), "Verkeerd jaar gevonden");
        assertEquals("J.K. Rowling", eersteBoek.getSchrijver(), "Verkeerde schrijver gevonden");
        assertEquals("Een geweldig boek", eersteBoek.getSpeciaal(), "Onjuiste speciale status van het boek");
        assertEquals("niet speciaal", eersteBoek.getOpmerking(), "Onjuiste opmerking over het boek");
    }

    @Test
    void zoekOpAlles_AlleBoeken() {
        // Haal alle boeken op
        List<Boek> alleBoeken = boekKast.zoekOpAlles();

        // Controleer of ten minste één boek is gevonden
        assertFalse(alleBoeken.isEmpty(), "Geen boeken gevonden in de boekenkast");

        // Controleer enkele eigenschappen van de eerste twee boeken (indien aanwezig)
        if (alleBoeken.size() >= 1) {
            Boek eersteBoek = alleBoeken.get(0);
            assertNotNull(eersteBoek.getNaam(), "Boeknaam mag niet leeg zijn");
        }
        if (alleBoeken.size() >= 2) {
            Boek tweedeBoek = alleBoeken.get(1);
            assertNotNull(tweedeBoek.getNaam(), "Boeknaam mag niet leeg zijn");
        }
    }
}
