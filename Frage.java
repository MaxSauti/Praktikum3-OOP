public class Frage {

    private String frage;
    private int richtigeAntwort;
    private String[] antworten = new String[4];

    /**
     * Konstruktor zum Erstellen einer Frage
     * @param frage         Frage die gestellt wird
     * @param richtigIDX    Index der richtigen Antwort
     * @param antworten     String Array von Antworten
     */
    public Frage(String frage, int richtigIDX, String[] antworten){
        this.frage = frage;
        richtigeAntwort = richtigIDX;
        setAntworten(antworten);
    }

    /**
     *
     * @return  Antworten Array
     */
    public String[] getAntworten() {
        return antworten;
    }

    /**
     * Setzt Antworten
     * @param antworten  Die übergebenen Antworten
     */
    public void setAntworten(String[] antworten) {
        this.antworten = antworten;
    }

    /**
     *
     * @return  Frage
     */
    public String getFrage() {
        return frage;
    }

    /**
     *
     * @return  richtige Antwort
     */
    public int getRichtigeAntwort() {
        return richtigeAntwort;
    }

    /**
     * Formatiert die Antworten als String
     * @return  die formatierten Antworten
     */
    public String getAntwortenString(){
        String formatiert = "";
        for (int i = 0; i < antworten.length; i++){
            formatiert += "\n" + (i + 1) + ". " + antworten[i];
        }
        return formatiert;
    }
}
