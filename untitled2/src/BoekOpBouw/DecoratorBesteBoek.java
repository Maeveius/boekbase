package BoekOpBouw;

public class DecoratorBesteBoek extends DecoratorBoek {
    public DecoratorBesteBoek(ComponenteVanHetBoek boek) {
        super(boek);
    }

    @Override
    public String getAuteur() {
        if (boek instanceof Boek) {
            return super.getAuteur() + ", Beste Boek: " + ((Boek) boek).getAuteurBoek().getBesteBoek();
        }
        return super.getAuteur();
    }
}
