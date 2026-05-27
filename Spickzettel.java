public class Spickzettel extends Hilfsmittel{

    /**
     * Konstruktor zum Erstellen eines Spickzettels
     * @param beschreibung  Beschreibung des Hilfsmittels (immer "Spickzettel")
     */
    public Spickzettel(String beschreibung) {
        super(beschreibung);
    }

    /**
     * Gibt den String der richtigen Antwort zurück
     * @param frage Die Frage zu welcher die richtige Antwort gesucht wird
     * @return  Die richtige Antwort als String
     */
    public String richtigeAntwort(Frage frage){
        return frage.getAntworten()[frage.getRichtigeAntwort()];
    }

}
