//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        BoekKast boekKast1 = new CSVBoekKast("C:\\Users\\brian\\IdeaProjects\\boekbasehelp\\untitled2\\src\\data.csv");
        BoekController controller = new BoekController(boekKast1);
        HomePage homePage = new HomePage(boekKast1);

        int keuze;
        try {
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
            } while(keuze != 5);
        } catch (Exception var5) {
            Exception e = var5;
            e.printStackTrace();
        }

    }
}
