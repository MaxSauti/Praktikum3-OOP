public class Abschlusspruefung extends Gegner{

    private Frage[] fragen;
    private boolean bestanden;
    private int richtigeAntworten;
    private int frageNr;

    /**
     * Konstruktor zum erstellen der Abschlusspüfrung
     * @param name      Name der Prüfung
     * @param fragen    Fragen der Prüfung
     */
    public Abschlusspruefung(String name, Frage[] fragen) {
        super(name);
        this.fragen = fragen;
    }

    /**
     *
     * @return  Fragen der Prüfung
     */
    public Frage[] getFragen() {
        return fragen;
    }

    /**
     *
     * @return ob die Prüfung bestanden wurde oder nicht
     */
    public boolean isBestanden() {
        return bestanden;
    }

    /**
     *
     * @param bestanden Legt fest ob die Prüdung bestanden wurde
     */
    public void setBestanden(boolean bestanden) {
        this.bestanden = bestanden;
    }

    /**
     * Gibt die gewünschte Frage aus
     * @param index Index der Frage die gestellt wird
     */
    public void stelleFragen(int index) {
        System.out.println(fragen[index].getFrage());
        System.out.println(fragen[index].getAntwortenString());
    }

    /**
     * Inkrementiert richtige Antworten
     */
    public void setRichtigeAntworten() {
        this.richtigeAntworten++;
    }

    /**
     *
     * @return richtige Antworten
     */
    public int getRichtigeAntworten() {
        return richtigeAntworten;
    }

    /**
     *
     * @return Nummer der aktuellen Frage
     */
    public int getFrageNr() {
        return frageNr;
    }

    /**
     * Inkrementiert Nummer der aktuellen Frage
     */
    public void setFrageNr() {
        this.frageNr++;
    }
}
