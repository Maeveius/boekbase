package BoekOpBouw;

public class AuteurBoek {
    private String auteur;
    private int geboortejaar;
    private String besteBoek;
    private String algemeneInformatie;

    public AuteurBoek(String auteur) {
        this.auteur = auteur;
        this.geboortejaar = geboortejaar;
        this.besteBoek = besteBoek;
        this.algemeneInformatie = algemeneInformatie;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getGeboortejaar() {
        return geboortejaar;
    }

    public void setGeboortejaar(int geboortejaar) {
        this.geboortejaar = geboortejaar;
    }

    public String getBesteBoek() {
        return besteBoek;
    }

    public void setBesteBoek(String besteBoek) {
        this.besteBoek = besteBoek;
    }

    public String getAlgemeneInformatie() {
        return algemeneInformatie;
    }

    public void setAlgemeneInformatie(String algemeneInformatie) {
        this.algemeneInformatie = algemeneInformatie;
    }


    public class OpmerkingBoek {
        private String opmerking;
        private String extraInformatie;

        public OpmerkingBoek(String opmerking, String extraInformatie) {
            this.opmerking = opmerking;
            this.extraInformatie = extraInformatie;
        }

        public String getOpmerking() {
            return opmerking;
        }

        public void setOpmerking(String opmerking) {
            this.opmerking = opmerking;
        }

        public String getExtraInformatie() {
            return extraInformatie;
        }

        public void setExtraInformatie(String extraInformatie) {
            this.extraInformatie = extraInformatie;
        }
    }
}