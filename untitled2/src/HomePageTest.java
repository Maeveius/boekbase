import BoekOpBouw.*;
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
        GelezenBoek gelezenBoek = new GelezenBoek(false);
        TitelBoek titelBoek = new TitelBoek("Harry Potter");
        GenreBoek genreBoek = new GenreBoek(new String[]{"Fantasy"});
        JaarBoek jaarBoek = new JaarBoek(2000);
        AuteurBoek auteurBoek = new AuteurBoek("J.K. Rowling");
        OpmerkingBoek opmerkingBoek = new OpmerkingBoek("Een geweldig boek");

        Boek testBoek = new Boek(gelezenBoek, titelBoek, genreBoek, jaarBoek, auteurBoek, "niet speciaal", opmerkingBoek);
        boekKast.voegBoekToe(testBoek);

        String input = "Y\n" +
                "Harry Potter\n" +
                "1\n" +
                "Nieuwe Naam\n";

        // Update the title of the book based on user input
        titelBoek.setTitel("Nieuwe Naam");

        // Verify the title has been updated
        assertEquals("Nieuwe Naam", testBoek.getTitel().getTitel());
    }
}
