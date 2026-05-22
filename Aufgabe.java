public class Aufgabe {

    private String beschreibung;
    private boolean erfuellt = false;
    private String loesung;

    public Aufgabe (String beschreibung, String loesung) {
        this.beschreibung = beschreibung;
        this.loesung = loesung;
    }

    public boolean isErfuellt() {
        return erfuellt;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setErfuellt(boolean erfuellt) {
        this.erfuellt = erfuellt;
    }

    public String getLoesung() {
        return loesung;
    }
}
