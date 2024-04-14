import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BoekControllerTest {


    @Test
    void voegBoekToe_Succesvol() {

        String input = "1\n"
                + "Harry Potter\n"
                + "Fantasy\n"
                + "2000\n"
                + "J.K. Rowling\n"
                + "Een geweldig boek\n"
                + "3\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        BoekKast boekKast = new CSVBoekKast("untitled2\\src\\data.csv");
        BoekController boekController = new BoekController(boekKast);

        boekController.voegBoekToe();

        String toegevoegdeBoekNaam = "Harry Potter";
        Boek toegevoegdBoek = null;
        for (Boek boek : boekKast.zoekOpAlles()) {
            if (boek.getNaam().equals(toegevoegdeBoekNaam)) {
                toegevoegdBoek = boek;
                break;
            }
        }

        assertNotNull(toegevoegdBoek, "Boek is niet toegevoegd aan de BoekKast");
        assertEquals("Harry Potter", toegevoegdBoek.getNaam(), "Verkeerde boeknaam toegevoegd");
        assertTrue(Arrays.asList(toegevoegdBoek.getGenres()).contains("Fantasy"), "Boek heeft niet het verwachte genre");
        assertEquals(2000, toegevoegdBoek.getJaar(), "Verkeerd jaar toegevoegd");
        assertEquals("J.K. Rowling", toegevoegdBoek.getSchrijver(), "Verkeerde schrijver toegevoegd");
        assertEquals("Een geweldig boek", toegevoegdBoek.getSpeciaal(), "Onjuiste speciale status van het boek");
        assertEquals("niet speciaal", toegevoegdBoek.getOpmerking(), "Onjuiste opmerking over het boek");
    }

    @Test
    void verwijderBoek() {
        CSVBoekKast boekKast = new CSVBoekKast("untitled2\\temp.csv");

        Boek testBoek = new Boek(true, "Testboek", new String[]{"Test"}, 2022, "Testauteur", "Geen speciaal", "Testopmerking");
        boekKast.voegBoekToe(testBoek);

        List<Boek> gevondenBoekenVoorVerwijdering = boekKast.zoekBoekenOpNaam("Testboek");
        assertFalse(gevondenBoekenVoorVerwijdering.isEmpty(), "Testboek moet aanwezig zijn vóór verwijdering");

        String input = "Testboek\n" +
                "Y\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        HomePage homePage = new HomePage(boekKast);

        homePage.verwijderBoek();

        List<Boek> gevondenBoekenNaVerwijdering = boekKast.zoekBoekenOpNaam("Testboek");
        assertTrue(gevondenBoekenNaVerwijdering.isEmpty(), "Testboek moet niet meer aanwezig zijn na verwijdering");
    }
}

