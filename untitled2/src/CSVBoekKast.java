import BoekOpBouw.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVBoekKast implements ZoekBoek, BoekErAf, BoekUpdate, BoekErBij {
    private final String bestandsnaam;
    private final List<BoekKastObserver> observers;

    public CSVBoekKast(String bestandsnaam) {
        this.bestandsnaam = bestandsnaam;
        this.observers = new ArrayList<>();
    }

    private void updateCSV(List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bestandsnaam))) {
            for (String line : lines) {
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void voegBoekToe(Boek boek) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(bestandsnaam, true)))) {
            writer.println(String.format("%b;%s;%s;%d;%s;%s;%s",
                    boek.getGelezen(), boek.getTitel(), String.join(",", boek.getGenres()),
                    boek.getJaar(), boek.getAuteur(), boek.getSpeciaal(), boek.getOpmerking()));
            System.out.println("Boek succesvol toegevoegd aan het CSV-bestand.");
            meldObservers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void verwijderBoek(String naam) {
        try {
            File inputFile = new File(bestandsnaam);
            File tempFile = new File("untitled2\\temp.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    String[] gegevens = currentLine.split(";");
                    if (!gegevens[1].equalsIgnoreCase(naam)) {
                        writer.write(currentLine + System.lineSeparator());
                    }
                }
            }

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.out.println("Kon het bestand niet bijwerken.");
            } else {
                System.out.println("Boek succesvol verwijderd.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBoek(String criterium, String oudeWaarde, String nieuweWaarde) {
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(bestandsnaam))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] gegevens = line.split(";");
                if (gegevens.length >= 7) {
                    switch (criterium.toLowerCase()) {
                        case "naam":
                            if (gegevens[1].equalsIgnoreCase(oudeWaarde)) {
                                gegevens[1] = nieuweWaarde;
                            }
                            break;
                        case "genre":
                            if (gegevens[2].equalsIgnoreCase(oudeWaarde)) {
                                gegevens[2] = nieuweWaarde;
                            }
                            break;
                        case "jaar":
                            if (gegevens[3].equalsIgnoreCase(oudeWaarde)) {
                                gegevens[3] = nieuweWaarde;
                            }
                            break;
                        case "schrijver":
                            String[] auteurParts = gegevens[4].split(",");
                            if (auteurParts[4].equalsIgnoreCase(oudeWaarde)) {
                                auteurParts[4] = nieuweWaarde;
                                gegevens[4] = String.join(",", auteurParts);
                            }
                            break;
                        case "Speciaal":
                            if (gegevens[5].equalsIgnoreCase(oudeWaarde)) {
                                gegevens[5] = nieuweWaarde;
                            }
                            break;
                        case "opmerking":
                            if (gegevens[6].equalsIgnoreCase(oudeWaarde)) {
                                gegevens[6] = nieuweWaarde;
                            }
                            break;
                        default:
                            break;
                    }
                    updatedLines.add(String.join(";", gegevens));
                } else {
                    System.out.println("Ongeldige regel in CSV-bestand: " + line);
                }
            }

            updateCSV(updatedLines);
            System.out.println("Boek succesvol aangepast.");
            meldObservers();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Boek> zoekOpAlles() {
        List<Boek> gevondenBoeken = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(bestandsnaam))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] gegevens = line.split(";");
                if (gegevens.length >= 7) {
                    Boek boek = BoekOpBouw.getBoek(gegevens);
                    gevondenBoeken.add(boek);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gevondenBoeken;
    }

    @Override
    public List<Boek> zoekBoekenOpGelezen(boolean gelezen) {
        return zoekEnToonResultaten("Gelezen", Boolean.toString(gelezen));
    }

    @Override
    public List<Boek> zoekBoekenOpNaam(String naam) {
        return zoekEnToonResultaten("Naam", naam);
    }

    @Override
    public List<Boek> zoekBoekenOpGenre(String genre) {
        return zoekEnToonResultaten("Genre", genre);
    }

    @Override
    public List<Boek> zoekBoekenOpJaar(int jaar) {
        return zoekEnToonResultaten("Jaar", Integer.toString(jaar));
    }

    @Override
    public List<Boek> zoekBoekenOpSchrijver(String schrijver) {
        return zoekEnToonResultaten("Schrijver", schrijver);
    }

    @Override
    public List<Boek> zoekBoekenOpSpeciaal(String speciaal) {
        return zoekEnToonResultaten("Speciaal", speciaal);
    }

    @Override
    public void registreerObserver(BoekKastObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void verwijderObserver(BoekKastObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void meldObservers() {
        for (BoekKastObserver observer : observers) {
            observer.update(zoekOpAlles());
        }
    }

    private List<Boek> zoekEnToonResultaten(String criterium, String waarde) {
        List<Boek> gevondenBoeken = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(bestandsnaam))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] gegevens = line.split(";");
                if (gegevens.length >= 7) {
                    String currentWaarde = "";
                    switch (criterium.toLowerCase()) {
                        case "gelezen":
                            currentWaarde = gegevens[0];
                            break;
                        case "naam":
                            currentWaarde = gegevens[1];
                            break;
                        case "genre":
                            currentWaarde = gegevens[2];
                            break;
                        case "jaar":
                            currentWaarde = gegevens[3];
                            break;
                        case "schrijver":
                            currentWaarde = gegevens[4];
                            break;
                        case "speciaal":
                            currentWaarde = gegevens[5];
                            break;
                        case "opmerking":
                            currentWaarde = gegevens[6];
                            break;
                    }
                    if (currentWaarde.equalsIgnoreCase(waarde)) {
                        Boek boek = BoekOpBouw.getBoek(gegevens);
                        gevondenBoeken.add(boek);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gevondenBoeken;
    }

    public class BoekOpBouw {

        public static Boek getBoek(String[] gegevens) {
            boolean gelezen = Boolean.parseBoolean(gegevens[0]);
            String titel = gegevens[1];
            String[] genres = gegevens[2].split(",");
            int jaar = Integer.parseInt(gegevens[3]);
            String schrijver = gegevens[4];
            String speciaal = gegevens[5];
            String opmerking = gegevens[6];

            AuteurBoek auteurBoek = new AuteurBoek(schrijver);
            OpmerkingBoek opmerkingBoek = new OpmerkingBoek(opmerking, "");

            switch (speciaal.toLowerCase()) {
                case "cd":
                    return new CD(gelezen, titel, genres, jaar, auteurBoek, speciaal, opmerkingBoek);
                case "kookboeken":
                    return new KookBoeken(gelezen, titel, genres, jaar, auteurBoek, speciaal, opmerkingBoek);
                default:
                    return new Boek(gelezen, titel, genres, jaar, auteurBoek, speciaal, opmerkingBoek);
            }
        }
    }
}
