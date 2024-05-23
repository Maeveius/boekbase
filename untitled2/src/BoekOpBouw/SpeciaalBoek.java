package BoekOpBouw;

abstract class SpeciaalBoek extends Boek {
    public SpeciaalBoek(boolean gelezen, String naam, String[] genres, int jaar, String schrijver, String speciaal, String opmerking) {
        super(gelezen, naam, genres, jaar, schrijver, speciaal, opmerking);
    }

    public abstract boolean isSpeciaalBoek();
}

