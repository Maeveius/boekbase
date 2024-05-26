import BoekOpBouw.Boek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class BoekKastTest {

    private BoekKast boekKast;

    @BeforeEach
    void setUp() {
        boekKast = new CSVBoekKast("untitled2\\src\\data.csv");
    }

    @Test
    void zoekBoekenOpNaam_HarryPotter() {
        List<Boek> gevondenBoeken = boekKast.zoekBoekenOpNaam("Harry Potter");

        // Controleer of ten minste één boek met de naam "Harry Potter" is gevonden
        assertFalse(gevondenBoeken.isEmpty(), "Boek met naam 'Harry Potter' niet gevonden");

        // Controleer de eigenschappen van het eerste gevonden boek
        Boek eersteBoek = gevondenBoeken.get(0);
        assertEquals("Harry Potter", eersteBoek.getTitel(), "Verkeerde boeknaam gevonden");
        assertEquals("Fantasy", eersteBoek.getGenres(), "Verkeerd genre gevonden");
        assertEquals(2000, eersteBoek.getJaar(), "Verkeerd jaar gevonden");
        assertEquals("J.K. Rowling", eersteBoek.getAuteur(), "Verkeerde schrijver gevonden");
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
            assertNotNull(eersteBoek.getTitel(), "Boeknaam mag niet leeg zijn");
        }
        if (alleBoeken.size() >= 2) {
            Boek tweedeBoek = alleBoeken.get(1);
            assertNotNull(tweedeBoek.getTitel(), "Boeknaam mag niet leeg zijn");
        }
    }
}
