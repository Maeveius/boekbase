package BoekOpBouw;

public abstract class SpeciaalBoek extends Boek {
    public SpeciaalBoek(boolean gelezen, String titel, String[] genres, int jaar, AuteurBoek auteur,String speciaal, OpmerkingBoek opmerking) {
        super(gelezen, titel, genres, jaar, auteur, speciaal, opmerking);
    }

    public abstract boolean isSpeciaalBoek();
}
