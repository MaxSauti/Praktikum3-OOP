public class Frage {

    private String frage;
    private int richtigeAntwort;
    private String[] antworten = new String[4];

    public Frage(String frage, int richtigIDX, String[] antworten){
        this.frage = frage;
        richtigeAntwort = richtigIDX;
        setAntworten(antworten);
    }


    public String[] getAntworten() {
        return antworten;
    }

    public void setAntworten(String[] Antworten) {
        this.antworten = Antworten;
    }

    public String getFrage() {
        return frage;
    }

    public int getRichtigeAntwort() {
        return richtigeAntwort;
    }

    public String getAntwortenString(){
        String formatiert = "";
        for (int i = 0; i < antworten.length; i++){
            formatiert += "\n" + (i + 1) + ". " + antworten[i];
        }
        return formatiert;
    }
}
