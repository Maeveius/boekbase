abstract class SpeciaalBoek extends Boek {
    public SpeciaalBoek(boolean gelezen, String naam, String[] genres, int jaar, String schrijver, String opmerking) {
        super(gelezen, naam, genres, jaar, schrijver, opmerking);
    }

    public abstract boolean isSpeciaalBoek();
}

