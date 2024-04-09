import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Boek {
    private boolean gelezen;
    private String naam;
    private String[] genres;
    private int jaar;
    private String schrijver;
    private String opmerking;

    public Boek(boolean gelezen, String naam, String[] genres, int jaar, String schrijver, String opmerking) {
        this.gelezen = gelezen;
        this.naam = naam;
        this.genres = genres;
        this.jaar = jaar;
        this.schrijver = schrijver;
        this.opmerking = opmerking;
    }
    public boolean isGelezen() {
        return gelezen;
    }public String getNaam() {
        return naam;
    }public String[] getGenres() {
        return genres;
    }public int getJaar() {
        return jaar;
    }public String getSchrijver() {
        return schrijver;
    }public String getOpmerking() {
        return opmerking;
    }public void setGelezen(boolean gelezen) {
        this.gelezen = gelezen;
    }public void setNaam(String naam) {
        this.naam = naam;
    }public void setGenres(String[] genres) {
        this.genres = genres;
    }public void setJaar(int jaar) {
        this.jaar = jaar;
    }public void setSchrijver(String schrijver) {
        this.schrijver = schrijver;
    }public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    @Override
    public String toString() {
        return "Boek{" +
                "gelezen=" + gelezen +
                ", naam='" + naam + '\'' +
                ", genres=" + Arrays.toString(genres) +
                ", jaar=" + jaar +
                ", schrijver='" + schrijver + '\'' +
                ", opmerking='" + opmerking + '\'' +
                '}';
    }
}
abstract class BoekRepository {
    abstract void voegBoekToe(Boek boek);

    abstract List<Boek> zoekBoekenOpGelezen(boolean gelezen);
    abstract List<Boek> zoekBoekenOpNaam(String naam);
    abstract List<Boek> zoekBoekenOpGenre(String genre);
    abstract List<Boek> zoekBoekenOpJaar(int jaar);
    abstract List<Boek> zoekBoekenOpSchrijver(String schrijver);
    abstract List<Boek> zoekOpAlles();

    abstract void updateBoek(String criterium, String oudeWaarde, String nieuweWaarde);
    abstract void verwijderBoek(String naam);
}

class CSVBoekRepository extends BoekRepository {
    private String bestandsnaam;
    public CSVBoekRepository(String bestandsnaam) {
        this.bestandsnaam = bestandsnaam;
    }
    private void updateCSV(List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\brian\\IdeaProjects\\untitled2\\src\\data.csv"))) {
            for (String line : lines) {
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void voegBoekToe(Boek boek) {
        try (FileWriter writer = new FileWriter("C:\\Users\\brian\\IdeaProjects\\untitled2\\src\\data.csv", true)) {
            writer.append(String.format("%b,%s,%s,%d,%s,%s%n\n",
                    boek.isGelezen(), boek.getNaam(), String.join(",", boek.getGenres()),
                    boek.getJaar(), boek.getSchrijver(), boek.getOpmerking()));
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

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\brian\\IdeaProjects\\untitled2\\src\\data.csv"))) {
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
        try {BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\brian\\IdeaProjects\\untitled2\\src\\data.csv"));
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


    private List<String[]> leesAlleBoeken() {
        List<String[]> boeken = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\brian\\IdeaProjects\\untitled2\\src\\data.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] gegevens = line.split(",");
                if (gegevens.length >= 5) {
                    boeken.add(gegevens);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return boeken;
    }
}
class BoekController extends CSVBoekRepository{
    private BoekRepository boekRepository;
    private Scanner scanner;

    public BoekController(BoekRepository boekRepository) {
        super("data.csv");
        this.boekRepository = boekRepository;
        this.scanner = new Scanner(System.in);
    }

    public void voegBoekToe() {
        System.out.println("Heb je een nieuw boek gelezen of wil je er nog 1 lezen?");
        System.out.println("1. Heb er 1 gelezen");
        System.out.println("2. Wil er 1 lezen");
        int gelezen = scanner.nextInt();
        scanner.nextLine();
        boolean gelezen1 = (gelezen == 1);
        System.out.print("Welk boek heb je gelezen of wil je lezen?: ");
        String vraag12 = scanner.nextLine();
        System.out.println("Maar welke genre/genres heeft het?");
        String[] vraag13 = scanner.nextLine().split(", ");
        System.out.print("Uit welk jaar komt het boek eigenlijk?: ");
        int vraag14 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("En wie is de schrijver?: ");
        String vraag15 = scanner.nextLine();
        System.out.print("Wat dacht je eigenlijk over het boek?: ");
        String vraag16 = scanner.nextLine();
        System.out.println("Ik heb hem voor je toegevoegd aan je boekenkast :)");
        Boek nieuwBoek = new Boek(gelezen1, vraag12, vraag13, vraag14, vraag15, vraag16);
        boekRepository.voegBoekToe(nieuwBoek);



    }

    public void verwijderBoek() {
        System.out.print("Welk boek wil je verwijderen? Geef de naam: ");
        String naam = scanner.nextLine();

        System.out.println("Weet je zeker dat je dit boek wilt verwijderen: " + naam);
        System.out.println("Y/N");
        String vraag92 = scanner.nextLine();
        if (vraag92.equalsIgnoreCase("Y")) {
            System.out.println("Oké, het is verwijderd");
            boekRepository.verwijderBoek(naam);
        } else if (vraag92.equalsIgnoreCase("N")) {
            System.out.println("Dat dacht ik al");
        } else {
            System.out.println("Ongeldige invoer, stop.");
        }
    }

    public void wijzigen() {
        System.out.println("Weet je zeker dat je een boek wilt aanpassen?");
        System.out.println("Y/N");
        String vraag31 = scanner.nextLine();
        if (vraag31.equalsIgnoreCase("N")) {
            System.out.println("Grappig");
            return;
        }else if (vraag31.equalsIgnoreCase("Y")) {
                System.out.println("Voer de naam van het boek in dat je wilt aanpassen:");
                String boekNaam = scanner.nextLine();


                List<Boek> gevondenBoeken = boekRepository.zoekBoekenOpNaam(boekNaam);

                if (gevondenBoeken.isEmpty()) {
                    System.out.println("Geen boeken gevonden met de opgegeven naam.");
                    return;
                }


                Boek teWijzigenBoek = gevondenBoeken.get(0);

            System.out.println("Wat zou je willen aanpassen?");
            System.out.println("----------------------------");
            System.out.println("1. Naam");
            System.out.println("2. Genre");
            System.out.println("3. Jaar");
            System.out.println("4. Schrijver");
            System.out.println("5. Opmerking");
            System.out.println("----------------------------");
            int keuze = scanner.nextInt();
            scanner.nextLine();

            switch (keuze) {
                case 1:
                    System.out.println("Voer de nieuwe naam in:");
                    String nieuweNaam = scanner.nextLine();
                    boekRepository.updateBoek("Naam", teWijzigenBoek.getNaam(), nieuweNaam);
                    System.out.println("Naam succesvol gewijzigd.");
                    break;
                case 2:
                    System.out.println("Voer het nieuwe genre in:");
                    String nieuwGenre = scanner.nextLine();
                    boekRepository.updateBoek("Genre", String.join(",", teWijzigenBoek.getGenres()), nieuwGenre);
                    System.out.println("Genre succesvol gewijzigd.");
                    break;
                case 3:
                    System.out.println("Voer het nieuwe jaar in:");
                    int nieuwJaar = scanner.nextInt();
                    scanner.nextLine();
                    boekRepository.updateBoek("Jaar", Integer.toString(teWijzigenBoek.getJaar()), Integer.toString(nieuwJaar));
                    System.out.println("Jaar succesvol gewijzigd.");
                    break;
                case 4:
                    System.out.println("Voer de nieuwe schrijver in:");
                    String nieuweSchrijver = scanner.nextLine();
                    boekRepository.updateBoek("Schrijver", teWijzigenBoek.getSchrijver(), nieuweSchrijver);
                    System.out.println("Schrijver succesvol gewijzigd.");
                    break;
                case 5:
                    System.out.println("Voer de nieuwe opmerking in:");
                    String nieuweOpmerking = scanner.nextLine();
                    boekRepository.updateBoek("Opmerking", teWijzigenBoek.getOpmerking(), nieuweOpmerking);
                    System.out.println("Opmerking succesvol gewijzigd.");
                    break;
                default:
                    System.out.println("Ongeldige keuze.");
            }
        }
    }


    public void genreUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Waar wil je op gaan zoeken?");
        System.out.println("----------------------------");
        System.out.println("1. Op naam");
        System.out.println("2. Op genre");
        System.out.println("3. Op jaar");
        System.out.println("4. Op schrijver");
        System.out.println("5. Alle gelezen");
        System.out.println("6. Alle niet gelezen");
        System.out.println("----------------------------");
        int vraag21 = scanner.nextInt();
        scanner.nextLine();

        List<Boek> resultaten = new ArrayList<>();

        switch (vraag21) {
            case 1:
                System.out.println("Type de naam:");
                String vraag211 = scanner.nextLine();
                resultaten = boekRepository.zoekBoekenOpNaam(vraag211);
                break;
            case 2:
                System.out.println("Welke genre valt het onder (je kan meerdere nemen):");
                String vraag212 = scanner.nextLine();
                resultaten = boekRepository.zoekBoekenOpGenre(vraag212);
                break;
            case 3:
                System.out.println("Uit welk jaar komt het boek:");
                int vraag213 = scanner.nextInt();
                scanner.nextLine();
                resultaten = boekRepository.zoekBoekenOpJaar(vraag213);
                break;
            case 4:
                System.out.println("Weet je de schrijver:");
                String vraag214 = scanner.nextLine();
                resultaten = boekRepository.zoekBoekenOpSchrijver(vraag214);
                break;
            case 5:
                System.out.println("Hier zijn alle gelezen boeken:");
                resultaten = boekRepository.zoekBoekenOpGelezen(true);
                break;
            case 6:
                System.out.println("Hier zijn alle boeken die je nog niet gelezen hebt:");
                resultaten = boekRepository.zoekBoekenOpGelezen(false);
                break;

            default:
                System.out.println("Ongeldige keuze.");
                return;
        }


        for (Boek boek : resultaten) {
            System.out.println("Naam: " + boek.getNaam());
            System.out.println("Genres: " + String.join(", ", boek.getGenres()));
            System.out.println("Jaar: " + boek.getJaar());
            System.out.println("Schrijver: " + boek.getSchrijver());
            System.out.println("Opmerking: " + boek.getOpmerking());
            System.out.println("------------------------");
        }
    }

}

class HomePage {
    private int keuze;
    private Scanner scanner;

    public HomePage() {
        this.scanner = new Scanner(System.in);
    }

    public void toonMenu() {
        System.out.println("Wat wil je doen?");
        System.out.println("1. Voeg een nieuw boek toe");
        System.out.println("2. Zoek een boek");
        System.out.println("3. Pas een boek aan");
        System.out.println("4. Verwijder een boek");
        System.out.println("5. Exit");
        System.out.print("Keuze: ");
        keuze = scanner.nextInt();
        scanner.nextLine(); 
    }

    public int getKeuze() {
        return keuze;
    }

    public void closeScanner() {
        scanner.close();
    }
}

    public class Main {
        public static void main(String[] args) {
            BoekRepository repository = new CSVBoekRepository("C:\\Users\\brian\\IdeaProjects\\untitled2\\src\\data.csv");
            BoekController controller = new BoekController(repository);
            HomePage homePage = new HomePage();

            try {
                while (true) {
                    homePage.toonMenu();
                    int keuze = homePage.getKeuze();

                    switch (keuze) {
                        case 1:
                            controller.voegBoekToe();
                            break;
                        case 2:
                            controller.genreUpdate();
                            break;
                        case 3:
                            controller.wijzigen();
                            break;
                        case 4:
                            controller.verwijderBoek();
                            break;
                        case 5:
                            System.out.println("Bedankt voor het gebruik van het boekenbeheersysteem.");
                            homePage.closeScanner();
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Ongeldige keuze. Probeer opnieuw.");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                homePage.closeScanner();
            }
        }
    }
