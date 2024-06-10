import BoekOpBouw.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandwaardenBoekTest {

    @Test
    public void testYearAtBoundary() {
        Boek boek = new Boek(true, "Harry Potter", new String[]{"Fantasy"}, 0, new AuteurBoek("J.K. Rowling"), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertFalse(BoekController.isValidBook(boek));

        boek = new Boek(true, "Harry Potter", new String[]{"Fantasy"}, 1, new AuteurBoek("J.K. Rowling"), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertTrue(BoekController.isValidBook(boek));

        boek = new Boek(true, "Harry Potter", new String[]{"Fantasy"}, 2024, new AuteurBoek("J.K. Rowling"), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertTrue(BoekController.isValidBook(boek));
    }

    @Test
    public void testTitleLengthBoundary() {
        String longTitle = "A".repeat(255);
        Boek boek = new Boek(true, longTitle, new String[]{"Fantasy"}, 1997, new AuteurBoek("J.K. Rowling"), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertTrue(BoekController.isValidBook(boek));

        longTitle = "A".repeat(256);
        boek = new Boek(true, longTitle, new String[]{"Fantasy"}, 1997, new AuteurBoek("J.K. Rowling"), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertFalse(BoekController.isValidBook(boek));

        longTitle = "A".repeat(254);
        boek = new Boek(true, longTitle, new String[]{"Fantasy"}, 1997, new AuteurBoek("J.K. Rowling"), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertTrue(BoekController.isValidBook(boek));
    }
}
