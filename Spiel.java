import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 *  Dies ist die Hauptklasse der Anwendung "Die Welt von Zuul".
 *  "Die Welt von Zuul" ist ein sehr einfaches, textbasiertes
 *  Adventure-Game. Ein Spieler kann sich in einer Umgebung bewegen,
 *  mehr nicht. Das Spiel sollte auf jeden Fall ausgebaut werden,
 *  damit es interessanter wird!
 * 
 *  Zum Spielen muss eine Instanz dieser Klasse erzeugt werden und
 *  an ihr die Methode "spielen" aufgerufen werden.
 * 
 *  Diese Instanz dieser Klasse erzeugt und initialisiert alle
 *  anderen Objekte der Anwendung: Sie legt alle R�ume und einen
 *  Parser an und startet das Spiel. Sie wertet auch die Befehle
 *  aus, die der Parser liefert und sorgt f�r ihre Ausf�hrung.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (March 2003)
 */

class Spiel 
{
    private Parser parser;
    private Raum aktuellerRaum;
    private ArrayList<Gegner> gegnerListe = new ArrayList<>();
    private ArrayList<Frage> fragenListe = new ArrayList<>();
    private ArrayList<Hilfsmittel> inventar = new ArrayList<>();
        
    
    public static void main (String[] args)
    {
    	Spiel spiel = new Spiel();
    	spiel.spielen();
    }

    /**
     * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
     */
    public Spiel() 
    {
        fragenAnlegen();
        gegnerAnlegen();
        raeumeAnlegen();
        parser = new Parser();
    }

    private void fragenAnlegen() {
        Frage frage1, frage2, frage3, frage4;
        String[] antworten1, anworten2, antworten3, antworten4;

        antworten1 = new String[]{"Wenn ihr Skalarprodukt 0 ist", "Wenn sie Vielfache voneinander sind", "Wenn ihr Skalarprodukt 1 ist", "Wenn sie orthogonal zueinander sind"};
        frage1 = new Frage("Wann sind Vektoren parallel?", 1, antworten1);
        fragenListe.add(frage1);

        anworten2 = new String[]{"Vererbung", "Objektreferenzen", "Polymorphie", "Redundanz"};
        frage2 = new Frage("Was beschreibt das Überladen von Methoden?", 2, anworten2);
        fragenListe.add(frage2);
    }

    private void gegnerAnlegen(){
        Gegner gegner1, gegner2, gegner3, gegner4;

        gegner1 = new Gegner("Matheprofessor", fragenListe.get(0));
        gegnerListe.add(gegner1);

        gegner2 = new Gegner("Informatikprofessor", fragenListe.get(1));
        gegnerListe.add(gegner2);
    }

    /**
     * Erzeuge alle R�ume und verbinde ihre Ausg�nge miteinander.
     */
    private void raeumeAnlegen()
    {
        Raum draussen, hoersaal, cafeteria, labor, buero;
        Hilfsmittel s1 = new Spickzettel("Spickzettel");
        Hilfsmittel s2 = new Spickzettel("Spickzettel");
        // die R�ume erzeugen
        draussen = new Raum("vor dem Haupteingang der Universit�t");
        hoersaal = new Raum("in einem Vorlesungssaal", gegnerListe.get(0));
        cafeteria = new Raum("in der Cafeteria der Uni", s1);
        labor = new Raum("in einem Rechnerraum", s2);
        buero = new Raum("im Verwaltungsb�ro der Informatik", gegnerListe.get(1));
        
        // die Ausg�nge initialisieren
        draussen.setzeAusgang("east", hoersaal);
        draussen.setzeAusgang("south", labor);
        draussen.setzeAusgang("west", cafeteria);

        hoersaal.setzeAusgang("west", draussen);

        cafeteria.setzeAusgang("east", draussen);

        labor.setzeAusgang("north", draussen);
        labor.setzeAusgang("east", buero);

        buero.setzeAusgang("west", labor);

        aktuellerRaum = draussen;  // das Spiel startet draussen
    }

    /**
     * Die Hauptmethode zum Spielen. L�uft bis zum Ende des Spiels
     * in einer Schleife.
     */
    public void spielen() 
    {            
        willkommenstextAusgeben();

        // Die Hauptschleife. Hier lesen wir wiederholt Befehle ein
        // und f�hren sie aus, bis das Spiel beendet wird.
                
        boolean beendet = false;
        while (! beendet) {
            Befehl befehl = parser.liefereBefehl();
            beendet = verarbeiteBefehl(befehl);
        }
        System.out.println("Danke f�r dieses Spiel. Auf Wiedersehen.");
    }
    
    /**
     * Einen Begr��ungstext f�r den Spieler ausgeben.
     */
    private void willkommenstextAusgeben()
    {
        System.out.println();
        System.out.println("Willkommen zu Zuul!");
        System.out.println("Zuul ist ein neues, unglaublich langweiliges Spiel.");
        System.out.println("Tippen sie 'help', wenn Sie Hilfe brauchen.");
        System.out.println();
        System.out.println(aktuellerRaum.gibLangeBeschreibung());
    }

    /**
     * Verarbeite einen gegebenen Befehl (f�hre ihn aus).
     * Wenn der Befehl das Spiel beendet, wird 'true' zur�ckgeliefert,
     * andernfalls 'false'.
     */
    private boolean verarbeiteBefehl(Befehl befehl) 
    {
        boolean moechteBeenden = false;

        if(befehl.istUnbekannt()) {
            System.out.println("Ich wei� nicht, was Sie meinen...");
            return false;
        }

        String befehlswort = befehl.gibBefehlswort();
        if (befehlswort.equals("help"))
            hilfstextAusgeben();
        else if (befehlswort.equals("go"))
            wechsleRaum(befehl);
        else if (befehlswort.equals("answer")) {
            beantworteFrage(befehl);
        } else if (befehlswort.equals("collect")) {
            sammleHilfsmittel(befehl);
        } else if (befehlswort.equals("inv")) {
            zeigeInventar();
        }
        else if (befehlswort.equals("use")) {
            nutzeHilfsmittel(befehl);
        }
        else if (befehlswort.equals("quit")) {
            moechteBeenden = beenden(befehl);
        }
        return moechteBeenden;
    }

    // Implementierung der Benutzerbefehle:

    /**
     * Gib Hilfsinformationen aus.
     * Hier geben wir eine etwas alberne und unklare Beschreibung
     * aus, sowie eine Liste der Befehlsw�rter.
     */
    private void hilfstextAusgeben() 
    {
        System.out.println("Sie haben sich verlaufen. Sie sind allein.");
        System.out.println("Sie irren auf dem Unigel�nde herum.");
        System.out.println();
        System.out.println("Ihnen stehen folgende Befehle zur Verf�gung:");
        parser.zeigeBefehle();
    }

    /**
     * Versuche, den Raum zu wechseln. Wenn es einen Ausgang gibt,
     * wechsele in den neuen Raum, ansonsten gib eine Fehlermeldung
     * aus.
     */
    private void wechsleRaum(Befehl befehl) 
    {
        if(!befehl.hatZweitesWort()) {
        	// Gibt es kein zweites Wort, wissen wir nicht, wohin...
            System.out.println("Wohin m�chten Sie gehen?");
            return;
        }

        if (!aktuellerRaum.isBesiegt()) {
            System.out.println("Du kannst den Raum erst verlassen wenn du den Gegner besiegt hast");
            return;
        }

        String richtung = befehl.gibZweitesWort();

        // Wir versuchen den Raum zu verlassen.
        Raum naechsterRaum = aktuellerRaum.gibAusgang(richtung);

        if (naechsterRaum == null)
            System.out.println("Dort ist keine T�r!");
        else {
            aktuellerRaum = naechsterRaum;
            System.out.println(aktuellerRaum.gibLangeBeschreibung());
        }
    }

    private void beantworteFrage(Befehl befehl) {
        if (aktuellerRaum.getGegner() == null) {
            System.out.println("In diesem Raum gibt es keinen Gegner der dir eine Frage stellt");
            return;
        }

        if (!befehl.hatZweitesWort()) {
            System.out.println("Wie lautet deine Antwort?");
            return;
        }

        Frage frage = aktuellerRaum.getGegner().getFrage();
        int richtigIDX = frage.getRichtigeAntwort();

        int antwort;

        try {
            antwort = Integer.parseInt(befehl.gibZweitesWort());
        } catch (NumberFormatException ignored) {
            System.out.println("Gib bitte eine verfügbare Zahl an");
            return;
        }


        if (antwort - 1 == richtigIDX) {
            System.out.println("Richtige Antwort, der Gegner ist besiegt");
            aktuellerRaum.setBesiegt(true);
            System.out.println(aktuellerRaum.gibLangeBeschreibung());
        }
    }

    public void sammleHilfsmittel(Befehl befehl){
        if(befehl.hatZweitesWort()) {
            System.out.println("Zum Einsammeln bitte nur \"collect\" eingeben");
            return;
        }
        if (aktuellerRaum.getHilfsmittel() == null) {
            System.out.println("Hier gibt es nichts einzusammeln");
            return;
        }

        inventar.add(aktuellerRaum.getHilfsmittel());
        System.out.println(aktuellerRaum.getHilfsmittel().getBeschreibung() + " eingesammelt");
        aktuellerRaum.sammleHilfsmittel();
        System.out.println(aktuellerRaum.gibLangeBeschreibung());
    }

    public void zeigeInventar(){
        int i = 1;
        for (Hilfsmittel item : inventar) {
            System.out.println(i + ". " + item.getBeschreibung());
            i++;
        }
    }

    public void nutzeHilfsmittel(Befehl befehl){
        if (!befehl.hatZweitesWort()) {
            zeigeInventar();
            System.out.println("Welches Item willst du nutzen?");
            return;
        }

        Hilfsmittel help;
        int index;

        try {
            index = Integer.parseInt(befehl.gibZweitesWort()) - 1;
            help = inventar.get(index);
        } catch (Exception ignored) {
            System.out.println("Gib einen passenden Index an");
            return;
        }


        if (help.getBeschreibung().equals("Spickzettel")){
            Spickzettel spicker = (Spickzettel) help;
            if (aktuellerRaum.isBesiegt()){
                System.out.println("In diesem Raum kannst du keinen Spickzettel verwenden");
                return;
            }
            System.out.println("Die richtige Antwort ist: " + spicker.richtigeAntwort(aktuellerRaum.getGegner().getFrage()));
            inventar.remove(index);
        }



    }

    /**
     * "quit" wurde eingegeben. �berpr�fe den Rest des Befehls,
     * ob das Spiel wirklich beendet werden soll. Liefere 'true',
     * wenn der Befehl das Spiel beendet, 'false' sonst.
     */
    private boolean beenden(Befehl befehl) 
    {
        if(befehl.hatZweitesWort()) {
            System.out.println("Was soll beendet werden?");
            return false;
        }
        else
            return true;  // Das Spiel soll beendet werden.
    }
}
