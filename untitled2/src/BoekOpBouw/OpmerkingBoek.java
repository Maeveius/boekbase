package BoekOpBouw;

public class OpmerkingBoek {
    private String opmerking;

    public OpmerkingBoek(String opmerking) {
        this.opmerking = opmerking;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    @Override
    public String toString() {
        return opmerking;
    }
}
