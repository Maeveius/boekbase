import BoekOpBouw.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MCDCBoekTest {

        @Test
        public void testTitelNull() {
            Boek boek = new Boek(true, null, new String[]{"Fantasy"}, 1997, new AuteurBoek("J.K. Rowling", 0,"",""), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
            assertFalse(BoekController.isValidBook(boek));
        }

        @Test
        public void testTitelEmpty() {
            Boek boek = new Boek(true, "", new String[]{"Fantasy"}, 1997, new AuteurBoek("J.K. Rowling", 0,"",""), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
            assertFalse(BoekController.isValidBook(boek));
        }

        @Test
        public void testTitelLengthExceeds() {
            String longTitle = "A".repeat(256);
            Boek boek = new Boek(true, longTitle, new String[]{"Fantasy"}, 1997, new AuteurBoek("J.K. Rowling", 0,"",""), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
            assertFalse(BoekController.isValidBook(boek));
        }

        @Test
        public void testYearNegative() {
            Boek boek = new Boek(true, "Harry Potter", new String[]{"Fantasy"}, -1, new AuteurBoek("J.K. Rowling", 0,"",""), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
            assertFalse(BoekController.isValidBook(boek));
        }

        @Test
        public void testYearTooHigh() {
            Boek boek = new Boek(true, "Harry Potter", new String[]{"Fantasy"}, 2026, new AuteurBoek("J.K. Rowling", 0,"",""), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
            assertFalse(BoekController.isValidBook(boek));
        }

        @Test
        public void testAuteurNull() {
            Boek boek = new Boek(true, "Harry Potter", new String[]{"Fantasy"}, 1997, null, "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
            assertFalse(BoekController.isValidBook(boek));
        }

        @Test
        public void testAuteurNaamNull() {
            Boek boek = new Boek(true, "Harry Potter", new String[]{"Fantasy"}, 1997, new AuteurBoek(null, 0,"",""), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
            assertFalse(BoekController.isValidBook(boek));
        }

        @Test
        public void testAuteurNaamEmpty() {
            Boek boek = new Boek(true, "Harry Potter", new String[]{"Fantasy"}, 1997, new AuteurBoek("", 0,"",""), "Normaal", new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen"));
            assertFalse(BoekController.isValidBook(boek));
        }

        @Test
        public void testValidBook() {
            AuteurBoek auteur = new AuteurBoek("J.K. Rowling", 0,"","");
            OpmerkingBoek opmerking = new OpmerkingBoek("Magisch", "Populair bij kinderen en volwassenen");
            Boek boek = new Boek(true, "Harry Potter", new String[]{"Fantasy"}, 1997, auteur, "Normaal", opmerking);
            assertTrue(BoekController.isValidBook(boek));
        }
    }
    
