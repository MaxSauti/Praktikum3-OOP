public class Abschlusspruefung extends Gegner{

    private Frage[] fragen;
    private boolean bestanden;

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
}
