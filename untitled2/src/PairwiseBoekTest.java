import BoekOpBouw.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PairwiseBoekTest {

    @Test
    public void testPairwise1() {
        Boek boek = new Boek(true, "Harry Potter", new String[]{"Fantasy"}, 1997, new AuteurBoek("J.K. Rowling", 0,"",""), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertTrue(BoekController.isValidBook(boek));
    }

    @Test
    public void testPairwise2() {
        Boek boek = new Boek(false, "Harry Potter", new String[]{"Science Fiction"}, 1997, new AuteurBoek("J.K. Rowling", 0,"",""), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertTrue(BoekController.isValidBook(boek));
    }

    @Test
    public void testPairwise3() {
        Boek boek = new Boek(true, "Harry Potter", new String[]{"Mystery"}, 2023, new AuteurBoek("J.K. Rowling", 0,"",""), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertTrue(BoekController.isValidBook(boek));
    }

    @Test
    public void testPairwise4() {
        Boek boek = new Boek(false, "Harry Potter", new String[]{"Fantasy"}, 2023, null, "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
        assertFalse(BoekController.isValidBook(boek));
    }
}
