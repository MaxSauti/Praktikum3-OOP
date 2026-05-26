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
    private Raum[][] raumArr = new Raum[10][10];
    private int hoffnung;
    private boolean alleBesiegt = false;
    private Karte karte;
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
        karte = new Karte();
        hoffnung = 3;
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

        gegner1 = new Professor("Matheprofessor", fragenListe.get(0));
        gegnerListe.add(gegner1);

        gegner2 = new Professor("Informatikprofessor", fragenListe.get(1));
        gegnerListe.add(gegner2);

        Aufgabe ti = new Aufgabe("Durch welches Tupel wird ein DEA definiert?", "M = (Z, Sigma, Delta, z0, E)");
        gegner3 = new Praktikum("Theoretische Informatik", ti);
        gegnerListe.add(gegner3);
    }

    /**
     * Erzeuge alle R�ume und verbinde ihre Ausg�nge miteinander.
     */
    private void raeumeAnlegen()
    {
        Raum draussen, hoersaal, cafeteria, labor, buero, flur, toilette, archiv, bibliothek;
        Hilfsmittel s1 = new Spickzettel("Spickzettel");
        Hilfsmittel s2 = new Spickzettel("Spickzettel");
        Hilfsmittel p1 = new Praktikumsloesung("Praktikumslösung");
        // die R�ume erzeugen
        draussen = new Raum("vor dem Haupteingang der Universit�t", gegnerListe.get(2));
        hoersaal = new Raum("in einem Vorlesungssaal", gegnerListe.get(0));
        cafeteria = new Raum("in der Cafeteria der Uni", s1);
        labor = new Raum("in einem Rechnerraum", s2);
        buero = new Raum("im Verwaltungsb�ro der Informatik", gegnerListe.get(1));
        flur = new Raum("im Flur", p1);
        toilette = new Raum("im Badezimmer");
        toilette.setSecret(true);
        archiv = new Raum("im Archiv");
        bibliothek = new Raum("in der Bibliothek");

        raumArr[0] = new Raum[]{null, null, null, null, flur, toilette, archiv, bibliothek, null, null};
        raumArr[1] = new Raum[]{null, null, null, cafeteria, draussen, hoersaal, null, null, null, null};
        raumArr[2] = new Raum[]{null, null, null, null, labor, buero, null, null, null, null};

        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                if (raumArr[i][j] != null) {
                    if (raumArr[i - 1][j] != null) {
                        raumArr[i][j].setzeAusgang("north", raumArr[i - 1][j]);
                        raumArr[i - 1][j].setzeAusgang("south", raumArr[i][j]);
                    }
                    if (raumArr[i][j + 1] != null) {
                        raumArr[i][j].setzeAusgang("east", raumArr[i][j + 1]);
                        raumArr[i][j + 1].setzeAusgang("west", raumArr[i][j]);
                    }
                    if (i == 1) {
                        if (raumArr[i-1][j] != null && raumArr[i - 1][j + 1] != null) {
                            raumArr[i-1][j].setzeAusgang("east", raumArr[i-1][j + 1]);
                            raumArr[i-1][j + 1].setzeAusgang("west", raumArr[i-1][j]);
                        }
                    }
                }
            }
        }

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
            if (hoffnung <= 0) {
                System.out.println("\n------------------------------");
                System.out.println("Du hast jegliche Hoffnung verloren und gibst auf!\n");
                break;
            }
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
            System.out.println("Ich weiß nicht, was Sie meinen...");
            return false;
        }

        String befehlswort = befehl.gibBefehlswort();
        if (befehlswort.equals("help"))
            hilfstextAusgeben();
        else if (befehlswort.equals("go"))
            wechsleRaum(befehl);
        else if (befehlswort.equals("answer")) {
            beantworteFrage(befehl);
        } else if (befehlswort.equals("lives")) {
            gibLebenAus();
        } else if (befehlswort.equals("collect")) {
            sammleHilfsmittel(befehl);
        } else if (befehlswort.equals("inv")) {
            zeigeInventar();
        }
        else if (befehlswort.equals("use")) {
            nutzeHilfsmittel(befehl);
        }
        else if (befehlswort.equals("map")) {
            karte.printKarte(raumArr, aktuellerRaum, alleBesiegt);
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
     * aus, sowie eine Liste der Befehlswörter.
     */
    private void hilfstextAusgeben() 
    {
        System.out.println("Sie haben sich verlaufen. Sie sind allein.");
        System.out.println("Sie irren auf dem Unigelände herum.");
        System.out.println();
        System.out.println("Ihnen stehen folgende Befehle zur Verfügung:");
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
            System.out.println("Wohin möchten Sie gehen?");
            return;
        }

        if (!aktuellerRaum.isBesiegt()) {
            System.out.println("Du kannst den Raum erst verlassen wenn du den Gegner besiegt hast\n");
            Gegner gegner = aktuellerRaum.getGegner();

            if (gegner instanceof Professor prof) {
                System.out.println(prof.getFrage().getFrage());
                System.out.println(prof.getFrage().getAntwortenString());
            }
            return;
        }

        String richtung = befehl.gibZweitesWort();

        // Wir versuchen den Raum zu verlassen.
        Raum naechsterRaum = aktuellerRaum.gibAusgang(richtung);

        if (naechsterRaum == null)
            System.out.println("Dort ist keine T�r!");
        else if (alleBesiegt == false && naechsterRaum.isSecret()) {
            System.out.println("Diesen Raum kannst du noch nicht betreten");
        } else {
            aktuellerRaum = naechsterRaum;
            System.out.println(aktuellerRaum.gibLangeBeschreibung());
        }
    }

    private void beantworteFrage(Befehl befehl) {
        if (aktuellerRaum.getGegner() == null || !(aktuellerRaum.getGegner() instanceof Professor prof)) {
            System.out.println("In diesem Raum gibt es keinen Gegner der dir eine Frage stellt");
            return;
        }

        if (!befehl.hatZweitesWort()) {
            System.out.println("Wie lautet deine Antwort?");
            return;
        }
        Frage frage = prof.getFrage();
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
        } else {
            System.out.println("Das war die falsche Antwort, du verlierst Hoffnung");
            hoffnung = hoffnung - 1;
            gibLebenAus();
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
            Professor prof = (Professor) aktuellerRaum.getGegner();
            System.out.println("Die richtige Antwort ist: " + spicker.richtigeAntwort(prof.getFrage()));
            inventar.remove(index);
        }

        if (help.getBeschreibung().equals("Praktikumslösung")) {
            Praktikumsloesung pl = (Praktikumsloesung) help;
            if (aktuellerRaum.getGegner() instanceof Praktikum prak) {
                if (prak.getAufgabe().isErfuellt()) {
                    System.out.println("Du hast die Aufgabe in diesem Raum bereits erfolgreich gelöst");
                    return;
                }
            } else {
                System.out.println("In diesem Raum kannst du kein Praktikum bearbeiten");
                return;
            }
            System.out.println("\nDu reichst die Lösung ein: \n" + prak.getAufgabe().getLoesung() + "\nDas war die richtige Lösung\n");
            prak.getAufgabe().setErfuellt(true);
            System.out.println("Du hast die Aufgabe in diesem Raum erfolgreich abgeschlossen");
            inventar.remove(index);
            alleBesiegt = true;
            for (Gegner gegner : gegnerListe) {
                if (gegner instanceof Praktikum prak2) {
                    if (!prak2.getAufgabe().isErfuellt()) {
                        alleBesiegt = false;
                        break;
                    }
                }
            }
            if (alleBesiegt) {
                System.out.println("Du hast alle Praktika bestanden und bist somit bereit für die Abschlussprüfung");
                System.out.println("Vielleicht gibt es ja Änderungen auf der Map :)");
            }
        }



    }

    public void gibLebenAus() {
        System.out.println("Du hast noch " + hoffnung + " Hoffnung");
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
