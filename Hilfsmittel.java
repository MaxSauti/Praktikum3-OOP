public class Hilfsmittel {
    private String beschreibung;

    /**
     * Konstruktor zum Erstellen eines Hilfsmittels
     * @param beschreibung   Beschreibung des Hilfsmittels
     */
    public Hilfsmittel (String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     * @return  Beschreibung des Hilfsmittels
     */
    public String getBeschreibung() {
        return beschreibung;
    }
}
