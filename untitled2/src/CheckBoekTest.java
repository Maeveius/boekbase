import BoekOpBouw.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckBoekTest {


    @Test
    public void testValidBook_AllTrue() {
        AuteurBoek auteur = new AuteurBoek("J.K. Rowling", 0,"","");
        OpmerkingBoek opmerking = new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen");
        Boek boek = new Boek(true, "Harry Potter", new String[]{"Fantasy"}, 1997, auteur, "Normaal", opmerking);
        assertTrue(BoekController.isValidBook(boek));

    }

    @Test
    public void testValidBook_TitleFalse() {
        Boek boek = new Boek(true, null, new String[]{"Fantasy"}, 1997, new AuteurBoek("J.K. Rowling", 0,"",""), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertFalse(BoekController.isValidBook(boek));
    }

    @Test
    public void testValidBook_YearFalse() {
        Boek boek = new Boek(true, "Harry Potter", new String[]{"Fantasy"}, -1, new AuteurBoek("J.K. Rowling", 0,"",""), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertFalse(BoekController.isValidBook(boek));
    }

    @Test
    public void testValidBook_AuteurFalse() {
        Boek boek = new Boek(true, "Harry Potter", new String[]{"Fantasy"}, 1997, null, "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertFalse(BoekController.isValidBook(boek));
    }
}
