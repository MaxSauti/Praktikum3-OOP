import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/*
 * Diese Klasse modelliert R�ume in der Welt von Zuul.
 * 
 * Ein "Raum" repr�sentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen R�umen �ber Ausg�nge verbunden.
 * F�r jeden existierenden Ausgang h�lt ein Raum eine Referenz auf 
 * den benachbarten Raum.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (M�rz 2003)
 */

class Raum 
{
    private boolean secret = false;
    private boolean besucht = false;
    private String beschreibung;
    private HashMap ausgaenge;        // die Ausg�nge dieses Raums
    private Gegner gegner;
    private boolean besiegt = true;
    private Hilfsmittel hilfsmittel;

    /**
     * Erzeuge einen Raum mit einer Beschreibung. Ein Raum
     * hat anfangs keine Ausg�nge.
     * @param beschreibung enth�lt eine Beschreibung in der Form
     *        "in einer K�che" oder "auf einem Sportplatz".
     */
    public Raum(String beschreibung) 
    {
        this.beschreibung = beschreibung;
        ausgaenge = new HashMap();
    }

    /**
     * Konstruktor zum Erstellen eines Raumes mit Gegner
     * @param beschreibung  Beschreibung des Raums
     * @param gegner        Gegner in diesem Raum
     */
    public Raum(String beschreibung, Gegner gegner){
        this(beschreibung);
        setGegner(gegner);
        if (gegner instanceof Professor || gegner instanceof Abschlusspruefung) {
            besiegt = false;
        }
    }

    /**
     * Konstruktor zum Erstellen eines Raumes mit Hilfsmittel
     * @param beschreibung  Beschreibung des Raumes
     * @param hilfsmittel   Hilfsmittel in diesem Raum
     */
    public Raum(String beschreibung, Hilfsmittel hilfsmittel) {
        this(beschreibung);
        this.hilfsmittel = hilfsmittel;
    }

    /**
     * Definiere einen Ausgang f�r diesen Raum.
     * @param richtung die Richtung, in der der Ausgang liegen soll
     * @param nachbar der Raum, der �ber diesen Ausgang erreicht wird
     */
    public void setzeAusgang(String richtung, Raum nachbar) 
    {
        ausgaenge.put(richtung, nachbar);
    }

    /**
     * Definiere Gegner für diesen Raum
     * @param gegner Gegner des Raumes
     */
    private void setGegner(Gegner gegner) {this.gegner = gegner;}

    /**
     *
     * @return Gegner des Raumes
     */
    public Gegner getGegner() {
        return gegner;
    }

    /**
     * Liefere die Beschreibung dieses Raums (die dem Konstruktor
     * �bergeben wurde).
     */
    public String gibKurzbeschreibung()
    {
        return beschreibung;
    }

    /**
     * Liefere eine lange Beschreibung dieses Raums, in der Form:
     *     Sie sind in der K�che.
     *     Ausgänge: nord west
     */
    public String gibLangeBeschreibung()
    {
        if (gegner == null) {
            return "Sie sind " + beschreibung + ".\n" + getHilfsmittelAlsString() + gibAusgaengeAlsString();
        }
        else if (gegner instanceof Professor prof && !besiegt) {
                return "Sie sind " + beschreibung +
                        ".\nDein Gegner in diesem Raum ist: " + prof.getName() +
                        "\nUm ihn zu besiegen musst du seine Frage korrekt beantworten, sonst verlierst du ein Leben" +
                        "\nFrage: " + prof.getFrage().getFrage() +
                        prof.getFrage().getAntwortenString();
        }
        if (gegner instanceof Praktikum prak && !prak.getAufgabe().isErfuellt()) {
            return "Sie sind " + beschreibung +
                    ".\nDein Gegner in diesem Raum ist: " + prak.getName() +
                    "\nUm es zu besiegen musst du eine Praktikumslösung finden und sie vorlegen, sonst wirst du niemals dein Studium schaffen" +
                    "\nDeine Aufgabe lautet: " + prak.getAufgabe().getBeschreibung()
                    + "\n" + gibAusgaengeAlsString();
        }
        if (gegner instanceof Abschlusspruefung fin) {
            return "Jetzt beginnt deine Abschlussprüfung, ich hoffe du hast gut gelernt oder ein paar Hilfsmittel dabei";
        }
        else {return "Sie sind " + beschreibung + ".\n" + getHilfsmittelAlsString() + gibAusgaengeAlsString();}
    }

    /**
     * Liefere eine Zeichenkette, die die Ausg�nge dieses Raums
     * beschreibt, beispielsweise
     * "Ausg�nge: north west".
     */
    private String gibAusgaengeAlsString()
    {
        String ergebnis = "Ausgänge:";
        Set keys = ausgaenge.keySet();
        for(Iterator iter = keys.iterator(); iter.hasNext(); )
            ergebnis += " " + iter.next();
        return ergebnis;
    }

    /**
     * Liefere den Raum, den wir erreichen, wenn wir aus diesem Raum
     * in die angegebene Richtung gehen. Liefere 'null', wenn in
     * dieser Richtung kein Ausgang ist.
     * @param richtung die Richtung, in die gegangen werden soll.
     */
    public Raum gibAusgang(String richtung) 
    {
        return (Raum)ausgaenge.get(richtung);
    }

    /**
     *
     * @param besiegt Ob der Raum besiegt wurde
     */
    public void setBesiegt(boolean besiegt){
        this.besiegt = besiegt;
    }

    /**
     *
     * @return Ob der Raum besiegt wurde
     */
    public boolean isBesiegt() {
        return besiegt;
    }

    /**
     * Formatiert die Hilfsmittel als String
     * @return  Hilfsmittel formatiert
     */
    public String getHilfsmittelAlsString() {
        if (hilfsmittel == null){
            return "Hilfsmittel in diesem Raum: keine\n";
        }
        return "Hilfsmittel in diesem Raum: " + hilfsmittel.getBeschreibung() + "\n";
    }

    /**
     *
     * @return Hilfsmittel des Raumes
     */
    public Hilfsmittel getHilfsmittel() {
        return hilfsmittel;
    }

    /**
     * Entfernt das Hilfsmittel aus dem Raum
     */
    public void sammleHilfsmittel() {
        hilfsmittel = null;
    }

    /**
     *
     * @return Ausgänge des Raumes
     */
    public HashMap getAusgaenge(){
        return ausgaenge;
    }

    /**
     *
     * @return  Ob der Raum geheim sein soll
     */
    public boolean isSecret() {
        return secret;
    }

    /**
     * Kann einen Raum auf geheim setzen, sodass er vorerst nicht auf der Map angezeigt wird
     * @param secret   Setzt einen Raum auf geheim wenn
     */
    public void setSecret(boolean secret) {
        this.secret = secret;
    }
}

