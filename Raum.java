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

    public Raum(String beschreibung, Gegner gegner){
        this(beschreibung);
        setGegner(gegner);
        if (gegner instanceof Professor) {
            besiegt = false;
        }
    }

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

    public void setGegner(Gegner gegner) {this.gegner = gegner;}

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
     *     Ausg�nge: nord west
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
        else {return "Sie sind " + beschreibung + ".\n" + getHilfsmittelAlsString() + gibAusgaengeAlsString();}
    }

    /**
     * Liefere eine Zeichenkette, die die Ausg�nge dieses Raums
     * beschreibt, beispielsweise
     * "Ausg�nge: north west".
     */
    private String gibAusgaengeAlsString()
    {
        String ergebnis = "Ausg�nge:";
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

    public void setBesiegt(boolean besiegt){
        this.besiegt = besiegt;
    }

    public boolean isBesiegt() {
        return besiegt;
    }

    public String getHilfsmittelAlsString() {
        if (hilfsmittel == null){
            return "Hilfsmittel in diesem Raum: keine\n";
        }
        return "Hilfsmittel in diesem Raum: " + hilfsmittel.getBeschreibung() + "\n";
    }

    public Hilfsmittel getHilfsmittel() {
        return hilfsmittel;
    }

    public void sammleHilfsmittel() {
        hilfsmittel = null;
    }

    public void setBesucht(boolean bes){
        besucht = bes;
    }

    public boolean isBesucht() {
        return besucht;
    }

    public HashMap getAusgaenge(){
        return ausgaenge;
    }
}

