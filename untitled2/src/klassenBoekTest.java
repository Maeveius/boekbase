import BoekOpBouw.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class klassenBoekTest {

    @Test
    public void testValidTitle() {
        Boek boek = new Boek(true, "Harry Potter", new String[]{"Fantasy"}, 1997, new AuteurBoek("J.K. Rowling"), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertTrue(BoekController.isValidBook(boek));
    }

    @Test
    public void testEmptyTitle() {
        Boek boek = new Boek(true, "", new String[]{"Fantasy"}, 1997, new AuteurBoek("J.K. Rowling"), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertFalse(BoekController.isValidBook(boek));
    }

    @Test
    public void testNullTitle() {
        Boek boek = new Boek(true, null, new String[]{"Fantasy"}, 1997, new AuteurBoek("J.K. Rowling"), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertFalse(BoekController.isValidBook(boek));
    }

    @Test
    public void testFutureYear() {
        Boek boek = new Boek(true, "Harry Potter", new String[]{"Fantasy"}, 3000, new AuteurBoek("J.K. Rowling"), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertFalse(BoekController.isValidBook(boek));
    }

    @Test
    public void testNegativeYear() {
        Boek boek = new Boek(true, "Harry Potter", new String[]{"Fantasy"}, -1, new AuteurBoek("J.K. Rowling"), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertFalse(BoekController.isValidBook(boek));
    }

    @Test
    public void testNullAuteur() {
        Boek boek = new Boek(true, "Harry Potter", new String[]{"Fantasy"}, 1997, null, "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertFalse(BoekController.isValidBook(boek));
    }
}
