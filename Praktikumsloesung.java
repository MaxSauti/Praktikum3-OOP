public class Praktikumsloesung extends Hilfsmittel {

    /**
     * Konstruktor zum Erstellen einer Praktikumslösung
     * @param beschreibung  Beschreibung der Lösung (muss "Praktikumslösung" sein)
     */
    public Praktikumsloesung(String beschreibung) {
        super(beschreibung);
    }

    /**
     *
     * @return Art des Hilfsmittels (Praktikumslösung)
     */
    public String getArt() {
        return "Praktikumslösung";
    }
}
