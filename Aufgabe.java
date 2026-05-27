public class Aufgabe {

    private String beschreibung;
    private boolean erfuellt = false;
    private String loesung;

    /**
     * Konstruktor zum Erstellen einer Aufgabe
     * @param beschreibung  Beschreibung der Aufgabe
     * @param loesung       Loesung der Aufgabe
     */
    public Aufgabe (String beschreibung, String loesung) {
        this.beschreibung = beschreibung;
        this.loesung = loesung;
    }

    /**
     *
     * @return ob die Aufgabe erfüllt wurde oder nicht
     */
    public boolean isErfuellt() {
        return erfuellt;
    }

    /**
     *
     * @return Beschreibung der Aufgabe
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * Wird benutzt um die Aufgabe als erfüllt zu kennzeichnen
     * @param erfuellt ob die Aufgabe erfüllt wurde
     */
    public void setErfuellt(boolean erfuellt) {
        this.erfuellt = erfuellt;
    }

    /**
     *
     * @return die Lösung der Aufgabe
     */
    public String getLoesung() {
        return loesung;
    }
}
