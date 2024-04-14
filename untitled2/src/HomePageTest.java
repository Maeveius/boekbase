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
        // Creëer een nieuwe BoekKast implementatie voor elke test
        boekKast = new CSVBoekKast("data.csv");
    }

    @Test
    void wijzigen_NaamAanpassen_Succesvol() {
        Boek testBoek = new Boek(false, "Harry Potter", new String[]{"Fantasy"}, 2000, "J.K. Rowling", "Een geweldig boek", "niet speciaal");
        boekKast.voegBoekToe(testBoek);
        // Simuleer gebruikersinvoer voor het aanpassen van de naam van een boek
        String input = "Y\n" +              // Bevestiging om een boek aan te passen
                "Harry Potter\n" +    // Naam van het boek dat moet worden aangepast
                "1\n" +               // Keuze voor het aanpassen van de naam
                "Nieuwe Naam\n";      // Nieuwe naam voor het boek

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Maak een nieuwe HomePage met de boekenkast
        HomePage homePage = new HomePage(boekKast);

        // Roep de methode aan om een boek aan te passen
        homePage.wijzigen();

        // Zoek het gewijzigde boek op basis van de nieuwe naam
        List<Boek> gevondenBoeken = boekKast.zoekBoekenOpNaam("Nieuwe Naam");

        // Controleer of het boek met de nieuwe naam is gevonden
        assertFalse(gevondenBoeken.isEmpty(), "Boek met nieuwe naam niet gevonden");
        assertEquals("Nieuwe Naam", gevondenBoeken.get(0).getNaam(), "Naam van het boek niet correct aangepast");
    }
}
