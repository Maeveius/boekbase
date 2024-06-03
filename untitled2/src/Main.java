import login.AccountFactory;
import login.ConcreteAccountFactory;
import login.Login;

public class Main {
    public static void main(String[] args) {
        AccountFactory accountFactory = new ConcreteAccountFactory();
        Login login = new Login(accountFactory);
        boolean loggedIn = false;

        while (!loggedIn) {
            loggedIn = login.logins();
        }

        CSVBoekKast boekKast = new CSVBoekKast("untitled2\\src\\data.csv");
        BoekController controller = new BoekController(boekKast, boekKast, boekKast, boekKast);
        HomePage homePage = new HomePage(boekKast, boekKast, boekKast, boekKast);

        controller.registreerObserver(homePage);

        try {
            int keuze;
            do {
                keuze = homePage.toonMenu();
                switch (keuze) {
                    case 1 -> homePage.voegNieuwBoekToe();
                    case 2 -> homePage.zoekBoekMenu();
                    case 3 -> homePage.wijzigen();
                    case 4 -> homePage.verwijderBoek();
                    case 5 -> {
                        System.out.println("Bedankt voor het gebruik van het boekenbeheersysteem.");
                        System.exit(0);
                    }
                    default -> System.out.println("Ongeldige keuze. Probeer opnieuw.");
                }
            } while (keuze != 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
