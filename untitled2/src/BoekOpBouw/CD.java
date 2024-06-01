package BoekOpBouw;

public class CD extends SpeciaalBoek {
    public CD(boolean gelezen, String titel, String[] genres, int jaar, AuteurBoek auteur, String speciaal, OpmerkingBoek opmerking) {
        super(gelezen, titel, genres, jaar, auteur, speciaal, opmerking);
    }

    @Override
    public boolean isSpeciaalBoek() {
        return true;
    }

}
