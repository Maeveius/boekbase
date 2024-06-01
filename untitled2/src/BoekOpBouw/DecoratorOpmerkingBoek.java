package BoekOpBouw;

public class DecoratorOpmerkingBoek extends DecoratorBoek {
    public DecoratorOpmerkingBoek(ComponenteVanHetBoek boek) {
        super(boek);
    }

    @Override
    public String getOpmerking() {
        if (boek instanceof Boek) {
            return super.getOpmerking() + ", Details: " + ((Boek) boek).getOpmerkingBoek().getDetails();
        }
        return super.getOpmerking();
    }
}
