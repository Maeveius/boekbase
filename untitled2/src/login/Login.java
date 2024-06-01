package login;

import java.util.Scanner;

public class Login {
    private Scanner sc = new Scanner(System.in);
    private AccountFactory accountFactory;

    public Login() {
        this.accountFactory = accountFactory;
    }

    public boolean logins() {
        System.out.println("Enter your username: ");
        String username = sc.nextLine();
        GebruikersNaam gebruikersNaam = accountFactory.maakGebruikersNaam(username);

        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        WachtWoord wachtWoord = accountFactory.maakWachtWoord(password);

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
