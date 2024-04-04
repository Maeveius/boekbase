import java.util.ArrayList;
import java.util.Scanner;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class HomePage {
    private int vraag1;

    public void andwoord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wat wil je gaan doen?");
        System.out.println("———————————————————————————————");
        System.out.println("1. voeg een boek toe");
        System.out.println("2. zoek een boek");
        System.out.println("3. ik wil een boek aan passen");
        System.out.println("9. een boek verwijderen");
        System.out.println("———————————————————————————————");
        vraag1 = scanner.nextInt();
    }

    public int getVraag1() {
        return vraag1;
    }
}
class Genre {
    private int vraag21;
    private String vraag211;
    private String[] vraag212;
    private int vraag213;
    private String vraag214;

    public void GenreUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Waar wil je op gaan zoeken?");
        System.out.println("———————————————————————————————");
        System.out.println("1. Op naam");
        System.out.println("2. Op genre");
        System.out.println("3. Op jaar");
        System.out.println("4. Op schrijver");
        System.out.println("———————————————————————————————");
        vraag21 = scanner.nextInt();
        scanner.nextLine();
        switch (vraag21) {
            case 1:
                System.out.println("Type de naam:");
                vraag211 = scanner.nextLine();
                zoekOpNaam(vraag211);
                break;
            case 2:
                System.out.println("Welke genre valt het onder (je kan meerdere nemen):");
                vraag212 = scanner.nextLine().split(", ");
                zoekOpGenre(vraag212);
                break;
            case 3:
                System.out.println("Uit welk jaar komt het boek:");
                vraag213 = scanner.nextInt();
                zoekOpJaar(vraag213);
                break;
            case 4:
                System.out.println("Weet je de schrijver:");
                scanner.nextLine();
                vraag214 = scanner.nextLine();
                zoekOpSchrijver(vraag214);
                break;
            default:
                System.out.println("Ongeldige keuze.");
        }
    }

    private void zoekOpNaam(String naam) {
        zoekEnToonResultaten("Naam", naam);
    }

    private void zoekOpGenre(String[] genres) {
        for (String genre : genres) {
            zoekEnToonResultaten("Genre", genre);
        }
    }

    private void zoekOpJaar(int jaar) {
        zoekEnToonResultaten("Jaar", Integer.toString(jaar));
    }

    private void zoekOpSchrijver(String schrijver) {
        zoekEnToonResultaten("Schrijver", schrijver);
    }

    private void zoekEnToonResultaten(String criterium, String waarde) {
        List<String[]> gevondenBoeken = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\brian\\IdeaProjects\\boeken base\\data boeken\\databoeken.csv"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] gegevens = line.split(",");
                if (gegevens.length >= 5) { // Controleren of de regel correct is opgebouwd
                    String currentWaarde = "";
                    switch (criterium) {
                        case "Naam":
                            currentWaarde = gegevens[0];
                            break;
                        case "Genre":
                            currentWaarde = gegevens[1];
                            break;
                        case "Jaar":
                            currentWaarde = gegevens[2];
                            break;
                        case "Schrijver":
                            currentWaarde = gegevens[3];
                            break;
                    }
                    if (currentWaarde.equalsIgnoreCase(waarde)) {
                        gevondenBoeken.add(gegevens);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (gevondenBoeken.isEmpty()) {
            System.out.println("Geen boeken gevonden met " + criterium.toLowerCase() + " '" + waarde + "'.");
        } else {
            System.out.println("Boeken gevonden met " + criterium.toLowerCase() + " '" + waarde + "':");
            for (String[] boek : gevondenBoeken) {
                System.out.println("Naam: " + boek[0]);
                System.out.println("Genre(s): " + boek[1]);
                System.out.println("Jaar: " + boek[2]);
                System.out.println("Schrijver: " + boek[3]);
                System.out.println("Opmerking: " + boek[4]);
                System.out.println("------------------------");
            }
        }
    }
}

class Veranderering {
    Scanner scanner = new Scanner(System.in);
    String vraag31;
    int vraag32;
    String vraag321;
    String vraag3211;
    String vraag322;
    String vraag3221;
    int vraag323;
    int vraag3231;
    String vraag324;
    String vraag3241;

    public void wijzigen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Weet je zeker dat je een boek wilt aanpassen?");
        System.out.println("Y/N");
        vraag31 = scanner.nextLine();
        if (vraag31.equals("N")) {
            System.out.println("Grappig");
        } else {
            System.out.println("Wat zou je willen aanpassen?");
            System.out.println("———————————————————————————————");
            System.out.println("1. Op naam");
            System.out.println("2. Op genre");
            System.out.println("3. Op jaar");
            System.out.println("4. Op schrijver");
            System.out.println("———————————————————————————————");
            vraag32 = scanner.nextInt();
            scanner.nextLine();
            switch (vraag32) {
                case 1:
                    wijzigNaam();
                    break;
                case 2:
                    wijzigGenre();
                    break;
                case 3:
                    wijzigJaar();
                    break;
                case 4:
                    wijzigSchrijver();
                    break;
                default:
                    System.out.println("Ongeldige keuze.");
            }
        }
    }

    private void wijzigNaam() {

        System.out.println("Wie schrijft de naam nou weer verkeerd XD");
        System.out.println("Hoe heb je het verkeerd geschreven dan?");
        vraag321 = scanner.nextLine();
        System.out.println("Dat je die fout hebt kunnen maken XD");
        System.out.println("Wat wil je het maken dan?");
        vraag3211 = scanner.nextLine();
        updateCSV("Naam", vraag321, vraag3211);
        System.out.println("Ik fix het wel omdat jij het niet kan");
    }

    private void wijzigGenre() {
        System.out.println("Wie schrijft het genre nou weer verkeerd XD");
        System.out.println("Hoe heb je het verkeerd geschreven dan?");
        vraag322 = scanner.nextLine();
        System.out.println("Dat je die fout hebt kunnen maken XD");
        System.out.println("Wat wil je het maken dan?");
        vraag3221 = scanner.nextLine();
        updateCSV("Genre", vraag322, vraag3221);
        System.out.println("Ik fix het wel omdat jij het niet kan");
    }

    private void wijzigJaar() {
        System.out.println("Wie schrijft het jaar nou weer verkeerd XD");
        System.out.println("Hoe heb je het verkeerd geschreven dan?");
        vraag323 = scanner.nextInt();
        System.out.println("Dat je die fout hebt kunnen maken XD");
        System.out.println("Wat wil je het maken dan?");
        vraag3231 = scanner.nextInt();
        updateCSV("Jaar", Integer.toString(vraag323), Integer.toString(vraag3231));
        System.out.println("Ik fix het wel omdat jij het niet kan");
    }

    private void wijzigSchrijver() {
        System.out.println("Wie schrijft de schrijver nou weer verkeerd XD");
        System.out.println("Hoe heb je het verkeerd geschreven dan?");
        scanner.nextLine(); // Consume newline
        vraag324 = scanner.nextLine();
        System.out.println("Dat je die fout hebt kunnen maken XD");
        System.out.println("Wat wil je het maken dan?");
        vraag3241 = scanner.nextLine();
        updateCSV("Schrijver", vraag324, vraag3241);
        System.out.println("Ik fix het wel omdat jij het niet kan");
    }

    private void updateCSV(String criterium, String oudeWaarde, String nieuweWaarde) {
        try {
            File inputFile = new File("C:\\Users\\brian\\IdeaProjects\\boeken base\\data boeken\\databoeken.csv");
            File tempFile = new File("C:\\Users\\brian\\IdeaProjects\\boeken base\\data boeken\\temp.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] gegevens = line.split(",");
                if (gegevens.length >= 5) { // Controleren of de regel correct is opgebouwd
                    String currentWaarde = "";
                    switch (criterium) {
                        case "Naam":
                            currentWaarde = gegevens[0];
                            break;
                        case "Genre":
                            currentWaarde = gegevens[1];
                            break;
                        case "Jaar":
                            currentWaarde = gegevens[2];
                            break;
                        case "Schrijver":
                            currentWaarde = gegevens[3];
                            break;
                    }
                    if (currentWaarde.equalsIgnoreCase(oudeWaarde)) {
                        line = line.replace(oudeWaarde, nieuweWaarde);
                    }
                }
                writer.write(line + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();

            if (!inputFile.delete()) {
                System.out.println("Kon het originele bestand niet verwijderen");
                return;
            }

            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Kon het tijdelijke bestand niet hernoemen");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Toevoegen{
    private String vraag12;
    private String[] vraag13;
    private int vraag14;
    private String vraag15;
    private String vraag16;

    public void Toevoegbaar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("oooooh wat leuk dat je een nieuw boek hebt gelezen.");
        System.out.print("welk boek heb je gelezen?: ");
        vraag12 = scanner.nextLine();
        System.out.println("cooool klinkt als een leuk boek");
        System.out.println("maar welke genre/ genres heeft het");
        vraag13 = scanner.nextLine().split(", ");
        System.out.println("klinkt sick");
        System.out.println("uit welk jaar komt het boek eigenlijk?: ");
        vraag14 = scanner.nextInt();
        System.out.println("dat klinkt op zich wel logisch ja");
        System.out.println("en wie is de schrijver dan?: ");
        scanner.nextLine();
        vraag15 = scanner.nextLine();
        System.out.println("zo een voor gevoel had ik al ja");
        System.out.println("maar wat dacht je eigenlijk over het boek?: ");
        vraag16 = scanner.nextLine();
        System.out.println("interessant");
        System.out.println("ik heb hem voor je toegevoegd aan je boekenkast :)");

        try {
            FileWriter writer = new FileWriter("C:\\Users\\brian\\IdeaProjects\\boeken base\\data boeken\\databoeken.csv", true);
            writer.append(vraag12 + ",");
            writer.append(String.join(",", vraag13) + ",");
            writer.append(Integer.toString(vraag14) + ",");
            writer.append(vraag15 + ",");
            writer.append(vraag16 + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Verwijderen{
    private String vraag91;
    private String vraag92;

    public void Verwijderbaar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("welk boek wil je verwijderen");
        vraag91 = scanner.nextLine();

        System.out.println("weet je zeker dat je dit boek wilt verwijderen: " + vraag91);
        System.out.println("Y/N");
        vraag92 = scanner.nextLine();
        if (vraag92.equals("Y")) {
            System.out.println("oke het is weg");

            try {
                File inputFile = new File("C:\\Users\\brian\\IdeaProjects\\boeken base\\data boeken\\databoeken.csv");
                File tempFile = new File("C:\\Users\\brian\\IdeaProjects\\boeken base\\data boeken\\temp.csv");

                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String currentLine;

                while ((currentLine = reader.readLine()) != null) {
                    // Als de huidige regel niet overeenkomt met het te verwijderen boek, schrijf deze regel naar het tijdelijke bestand
                    if (!currentLine.contains(vraag91)) {
                        writer.write(currentLine + System.getProperty("line.separator"));
                    }
                }
                writer.close();
                reader.close();

                // Verwijder het originele bestand
                if (!inputFile.delete()) {
                    System.out.println("Kon het originele bestand niet verwijderen");
                    return;
                }

                // Hernoem het tijdelijke bestand naar het originele bestand
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Kon het tijdelijke bestand niet hernoemen");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (vraag92.equalsIgnoreCase("N")) {
            System.out.println("dat dacht ik al");
        } else {
            System.out.println("just stop");
        }
    }
}

public class BoekBase{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HomePage homePage = new HomePage();
        homePage.andwoord();
        int vraag1 = homePage.getVraag1();

        if (vraag1 == 1) {
            Toevoegen toevoegen = new Toevoegen();
            toevoegen.Toevoegbaar();
        } else if (vraag1 == 2) {
            Genre genre = new Genre();
            genre.GenreUpdate();
        } else if (vraag1 == 3){
            Veranderering veranderering = new Veranderering();
            veranderering.wijzigen();
        } else if (vraag1 == 9) {
            Verwijderen verwijderen = new Verwijderen();
            verwijderen.Verwijderbaar();
        } else {
            System.out.println("waarom probeer je uberhoud iets anders");
        }
    }
}
