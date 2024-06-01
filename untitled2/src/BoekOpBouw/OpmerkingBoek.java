package BoekOpBouw;

public class OpmerkingBoek {
    private String opmerking;
    private String details;

    public OpmerkingBoek(String opmerking, String details) {
        this.opmerking = opmerking;
        this.details = details;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return opmerking;
    }
}
