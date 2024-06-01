package BoekOpBouw;

public class DecoratorAlgemeneInformatie extends DecoratorBoek {
    public DecoratorAlgemeneInformatie(ComponenteVanHetBoek boek) {
        super(boek);
    }

    @Override
    public String getAuteur() {
        if (boek instanceof Boek) {
            return super.getAuteur() + ", Algemene Informatie: " + ((Boek) boek).getAuteurBoek().getAlgemeneInformatie();
        }
        return super.getAuteur();
    }
}
