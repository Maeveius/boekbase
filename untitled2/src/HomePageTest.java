import BoekOpBouw.Boek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class HomePageTest {

    private BoekKast boekKast;
    @BeforeEach
    void setUp() {

        boekKast = new CSVBoekKast("untitled2\\src\\data.csv");
    }

    @Test
    void wijzigen_NaamAanpassen_Succesvol() {
        Boek testBoek = new Boek(false, "Harry Potter", new String[]{"Fantasy"}, 2000, "J.K. Rowling", "Een geweldig boek", "niet speciaal");
        boekKast.voegBoekToe(testBoek);

        String input = "Y\n" +
                "Harry Potter\n" +
                "1\n" +
                "Nieuwe Naam\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);


        HomePage homePage = new HomePage(boekKast);


        homePage.wijzigen();


        List<Boek> gevondenBoeken = boekKast.zoekBoekenOpNaam("Nieuwe Naam");


        assertFalse(gevondenBoeken.isEmpty(), "BoekOpBouw.Boek met nieuwe naam niet gevonden");
        assertEquals("Nieuwe Naam", gevondenBoeken.get(0).getNaam(), "Naam van het boek niet correct aangepast");
    }
}
