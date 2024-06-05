package login;

import java.io.*;
import java.util.List;

public class CsvAccountManager {
    private static final String DELIMITER = ";";
    private final String bestandsnaam;

    public CsvAccountManager(String bestandsnaam) {
        this.bestandsnaam = bestandsnaam;
    }

    private void updateCSVAccount(List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bestandsnaam))) {
            for (String line : lines) {
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void accountChecken(GebruikersNaam gebruikersNaam, WachtWoord wachtWoord) {
        if (isValidUser(gebruikersNaam, wachtWoord)) {
            System.out.println("Account bestaat.");
        } else {
            System.out.println("Account bestaat niet.");
        }
    }

    public void accountToevoegen(GebruikersNaam gebruikersNaam, WachtWoord wachtWoord) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(bestandsnaam, true)))) {
            writer.println(String.format("%s,%s", gebruikersNaam.getGebruikersNaam(), wachtWoord.getWachtwoord()));
            System.out.println("Account succesvol toegevoegd aan het systeem.");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
