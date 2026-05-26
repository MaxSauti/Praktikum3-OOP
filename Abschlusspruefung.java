public class Abschlusspruefung extends Gegner{

    private Frage[] fragen;
    private boolean bestanden;
    private int richtigeAntworten;
    private int frageNr;

    public Abschlusspruefung(String name, Frage[] fragen) {
        super(name);
        this.fragen = fragen;
    }

    public Frage[] getFragen() {
        return fragen;
    }

    public boolean isBestanden() {
        return bestanden;
    }

    public void setBestanden(boolean bestanden) {
        this.bestanden = bestanden;
    }

    public void stelleFragen(int index) {
        System.out.println(fragen[index].getFrage());
        System.out.println(fragen[index].getAntwortenString());
    }

    public void setRichtigeAntworten() {
        this.richtigeAntworten++;
    }

    public int getRichtigeAntworten() {
        return richtigeAntworten;
    }

    public int getFrageNr() {
        return frageNr;
    }

    public void setFrageNr() {
        this.frageNr++;
    }
}
