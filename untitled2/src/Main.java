public class Main {
    public static void main(String[] args) {
        BoekKast boekKast1 = new CSVBoekKast("untitled2\\src\\data.csv");
        BoekController controller = new BoekController(boekKast1);
        HomePage homePage = new HomePage(boekKast1);

        try {
            int keuze;
            do {
                keuze = homePage.toonMenu();
                switch (keuze) {
                    case 1:
                        controller.voegBoekToe();
                        break;
                    case 2:
                        homePage.genreUpdate();
                        break;
                    case 3:
                        homePage.wijzigen();
                        break;
                    case 4:
                        controller.verwijderBoek();
                        break;
                    case 5:
                        System.out.println("Bedankt voor het gebruik van het boekenbeheersysteem.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Ongeldige keuze. Probeer opnieuw.");
                }
            } while (keuze != 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
