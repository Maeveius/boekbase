package login;
import java.util.Scanner;

public class Login {
    private Scanner sc = new Scanner(System.in);

    public boolean logins() {
        System.out.println("Enter your username: ");
        String username = sc.nextLine();
        GebruikersNaam gebruikersNaam = new GebruikersNaam(username);

        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        WachtWoord wachtWoord = new WachtWoord(password);

        CsvAccountMannager accountManager = new CsvAccountMannager("untitled2\\src\\login\\accounts.csv");

        if (accountManager.isValidUser(gebruikersNaam, wachtWoord)) {
            System.out.println("Welkom, " + gebruikersNaam.getGebruikersNaame() + "!");
            return true;
        } else {
            System.out.println("Sorry, uw wachtwoord/gebruikersnaam is verkeerd.");
            return false;
        }
    }
}