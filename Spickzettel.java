public class Spickzettel extends Hilfsmittel{

    public Spickzettel(String beschreibung) {
        super(beschreibung);
    }

    public String richtigeAntwort(Frage frage){
        return frage.getAntworten()[frage.getRichtigeAntwort()];
    }

    public String getArt() {
        return "Spickzettel";
    }

}
