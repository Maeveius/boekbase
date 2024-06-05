package login;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvAccountManager {
    private static final String DELIMITER = ";";
    private final String bestandsnaam;

    public CsvAccountManager(String bestandsnaam) {
        this.bestandsnaam = bestandsnaam;
    }

    public boolean isValidUser(GebruikersNaam gebruikersNaam, WachtWoord wachtWoord) {
        try (BufferedReader reader = new BufferedReader(new FileReader(bestandsnaam))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts[0].equals(gebruikersNaam.getGebruikersNaam()) && parts[1].equals(wachtWoord.getWachtwoord())) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
