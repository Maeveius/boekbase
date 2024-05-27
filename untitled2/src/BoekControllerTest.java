import BoekOpBouw.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BoekControllerTest {

    private BoekKast boekKast;

    @BeforeEach
    void setUp() {
        boekKast = new CSVBoekKast("untitled2\\src\\data.csv");
    }

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

        BoekController boekController = new BoekController(boekKast);
        boekController.voegBoekToe();

        String toegevoegdeBoekNaam = "Harry Potter";
        Boek toegevoegdBoek = null;
        for (Boek boek : boekKast.zoekOpAlles()) {
            if (boek.getTitel().getTitel().equals(toegevoegdeBoekNaam)) {
                toegevoegdBoek = boek;
                break;
            }
        }

        assertNotNull(toegevoegdBoek, "Boek is niet toegevoegd aan de BoekKast");
        assertEquals("Harry Potter", toegevoegdBoek.getTitel(), "Verkeerde boeknaam toegevoegd");
        assertTrue(Arrays.asList(toegevoegdBoek.getGenres()).contains("Fantasy"), "Boek heeft niet het verwachte genre");
        assertEquals(2000, toegevoegdBoek.getJaar(), "Verkeerd jaar toegevoegd");
        assertEquals("J.K. Rowling", toegevoegdBoek.getAuteur(), "Verkeerde schrijver toegevoegd");
        assertEquals("niet speciaal", toegevoegdBoek.getSpeciaal(),"Verkeerde schrijver toegevoegd");
        assertEquals("Een geweldig boek", toegevoegdBoek.getOpmerking(), "Onjuiste opmerking over het boek");
    }

    @Test
    void verwijderBoek() {
        GelezenBoek gelezenBoek = new GelezenBoek(true);
        TitelBoek titelBoek = new TitelBoek("Testboek");
        GenreBoek genreBoek = new GenreBoek(new String[]{"test"});
        JaarBoek jaarBoek = new JaarBoek(2022);
        AuteurBoek auteurBoek = new AuteurBoek("Testauteur");
        OpmerkingBoek opmerkingBoek = new OpmerkingBoek("Testopmerking");

        Boek testBoek = new Boek(gelezenBoek, titelBoek, genreBoek, jaarBoek, auteurBoek, "Geen speciaal", opmerkingBoek);
        boekKast.voegBoekToe(testBoek);

        List<Boek> gevondenBoekenVoorVerwijdering = boekKast.zoekBoekenOpNaam("Testboek");
        assertFalse(gevondenBoekenVoorVerwijdering.isEmpty(), "Testboek moet aanwezig zijn vóór verwijdering");

        String input = "Testboek\n" +
                "Y\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BoekController boekController = new BoekController(boekKast);
        boekController.verwijderBoek();

        List<Boek> gevondenBoekenNaVerwijdering = boekKast.zoekBoekenOpNaam("Testboek");
        assertTrue(gevondenBoekenNaVerwijdering.isEmpty(), "Testboek moet niet meer aanwezig zijn na verwijdering");
    }
}
