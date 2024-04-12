import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVBoekKast implements BoekKast {
    private String bestandsnaam;

    public CSVBoekKast(String bestandsnaam) {
        this.bestandsnaam = bestandsnaam;
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
        String speciaalType = "Niet speciaal";

        if (boek instanceof SpeciaalBoek) {
            speciaalType = ((SpeciaalBoek) boek).isSpeciaalBoek() ? "Speciaal" : "Niet speciaal";
        }
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(bestandsnaam, true)))) {
            writer.println(String.format("%b,%s,%s,%d,%s,%s,%s",
                    boek.isGelezen(), boek.getNaam(), String.join(",", boek.getGenres()),
                    boek.getJaar(), boek.getSchrijver(), boek.getOpmerking(), speciaalType));
            System.out.println("Boek succesvol toegevoegd aan het CSV-bestand.");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public List<Boek> zoekOpAlles() {
        List<Boek> gevondenBoeken = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\brian\\IdeaProjects\\boekbase\\untitled2\\src\\data.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] gegevens = line.split(",");
                if (gegevens.length >= 6) {
                    boolean gelezen = Boolean.parseBoolean(gegevens[0]);
                    String naam = gegevens[1];
                    String[] genres = gegevens[2].split(",");
                    int jaar = Integer.parseInt(gegevens[3]);
                    String schrijver = gegevens[4];
                    String opmerking = gegevens[5];
                    Boek boek = new Boek(gelezen, naam, genres, jaar, schrijver, opmerking);
                    gevondenBoeken.add(boek);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gevondenBoeken;
    }

    @Override
    public void updateBoek(String criterium, String oudeWaarde, String nieuweWaarde) {
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(bestandsnaam))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] gegevens = line.split(",");
                if (gegevens.length >= 6) {
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
                            if (gegevens[4].equalsIgnoreCase(oudeWaarde)) {
                                gegevens[4] = nieuweWaarde;
                            }
                            break;
                        case "opmerking":
                            if (gegevens[5].equalsIgnoreCase(oudeWaarde)) {
                                gegevens[5] = nieuweWaarde;
                            }
                            break;
                        default:
                            break;
                    }
                    updatedLines.add(String.join(",", gegevens));
                } else {
                    System.out.println("Ongeldige regel in CSV-bestand: " + line);
                }
            }


            updateCSV(updatedLines);

            System.out.println("Boek succesvol aangepast.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void verwijderBoek(String naam) {
        try {
            File inputFile = new File("C:\\Users\\brian\\IdeaProjects\\untitled2\\src\\data.csv");
            File tempFile = new File("C:\\Users\\brian\\IdeaProjects\\untitled2\\src\\temp.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().isEmpty() || !currentLine.contains(",")) {
                    System.out.println("Ongeldige regel in CSV-bestand: " + currentLine);
                    continue;
                }

                String[] gegevens = currentLine.split(",");
                if (gegevens.length >= 6) {
                    String boekNaam = gegevens[1].trim();

                    if (!boekNaam.equalsIgnoreCase(naam)) {
                        writer.write(currentLine + System.lineSeparator());
                    }
                }
            }

            writer.close();
            reader.close();

            if (!inputFile.delete()) {
                System.out.println("Kon het originele bestand niet verwijderen");
                return;
            }

            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Kon het tijdelijke bestand niet hernoemen");
            } else {
                System.out.println("Boek succesvol verwijderd.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<Boek> zoekEnToonResultaten(String criterium, String waarde) {
        List<Boek> gevondenBoeken = new ArrayList<>();
        try {BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\brian\\IdeaProjects\\boekbase\\untitled2\\src\\data.csv"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] gegevens = line.split(",");
                if (gegevens.length >= 6) {
                    String currentWaarde = "";
                    switch (criterium) {
                        case "Gelezen":
                            currentWaarde = gegevens[0];
                            break;
                        case "Naam":
                            currentWaarde = gegevens[1];
                            break;
                        case "Genre":
                            currentWaarde = gegevens[2];
                            break;
                        case "Jaar":
                            currentWaarde = gegevens[3];
                            break;
                        case "Schrijver":
                            currentWaarde = gegevens[4];
                            break;
                        case "Opmerking":
                            currentWaarde = gegevens[5];
                            break;
                    }
                    if (currentWaarde.equalsIgnoreCase(waarde)) {
                        boolean gelezen = Boolean.parseBoolean(gegevens[0]);
                        String naam = gegevens[1];
                        String[] genres = gegevens[2].split(",");
                        int jaar = Integer.parseInt(gegevens[3]);
                        String schrijver = gegevens[4];
                        String opmerking = gegevens[5];
                        Boek boek = new Boek(gelezen, naam, genres, jaar, schrijver, opmerking);
                        gevondenBoeken.add(boek);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gevondenBoeken;
    }



}