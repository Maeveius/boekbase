package BoekOpBouw;

public class KookBoeken extends SpeciaalBoek {
    public KookBoeken(boolean gelezen, String titel, String[] genres, int jaar, AuteurBoek auteur, String speciaal, OpmerkingBoek opmerking) {
        super(gelezen, titel, genres, jaar, auteur, speciaal,opmerking);
    }

    @Override
    public boolean isSpeciaalBoek() {
        return true;
    }

}
