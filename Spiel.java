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
    Frage[] abschluss;
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

        antworten3 = new String[]{
                "Die Planung und Steuerung der betrieblichen Abläufe",
                "Die Analyse der Zahlungsströme eines Unternehmens",
                "Die Ermittlung des Unternehmenserfolgs durch Gegenüberstellung von Kosten und Leistungen",
                "Die Untersuchung von Marktanteilen im Wettbewerb"
        };
        frage3 = new Frage("Was ist die zentrale Aufgabe der Kosten- und Leistungsrechnung?", 2, antworten3);
        fragenListe.add(frage3);

        antworten4 = new String[]{
                "Ein Zustand, in dem ein Prozess auf die CPU-Zuteilung wartet",
                "Ein Zustand, in dem ein Prozess im Ready-Queue verharrt",
                "Ein Zustand, in dem ein Prozess auf Ein-/Ausgabeoperationen wartet",
                "Ein Zustand, in dem mehrere Prozesse gegenseitig auf Ressourcen warten und keiner fortfahren kann"
        };
        frage4 = new Frage("Was beschreibt einen Deadlock in Betriebssystemen am besten?", 3, antworten4);
        fragenListe.add(frage4);

        Frage frageFinal1, frageFinal2, frageFinal3, frageFinal4, frageFinal5;
        String[] antwortenFinal1, antwortenFinal2, antwortenFinal3, antwortenFinal4, antwortenFinal5;

        antwortenFinal1 = new String[]{
                "Die Fähigkeit eines Unternehmens, langfristige Kredite aufzunehmen",
                "Die Fähigkeit eines Unternehmens, seine kurzfristigen Zahlungsverpflichtungen zu erfüllen",
                "Die Fähigkeit eines Unternehmens, Gewinne zu maximieren",
                "Die Fähigkeit eines Unternehmens, seine Fixkosten zu senken"
        };
        frageFinal1 = new Frage("Was beschreibt die Liquidität eines Unternehmens?", 1, antwortenFinal1);

        antwortenFinal2 = new String[]{
                "Die Anzahl der benötigten CPU-Kerne",
                "Die maximale Größe des verwendeten Arrays",
                "Die Laufzeit- oder Speicherentwicklung eines Algorithmus in Abhängigkeit der Eingabegröße",
                "Die Anzahl der verwendeten Programmiersprachen"
        };
        frageFinal2 = new Frage("Was beschreibt die algorithmische Komplexität?", 2, antwortenFinal2);

        antwortenFinal3 = new String[]{
                "f'(x) = 2x",
                "f'(x) = 3x^2",
                "f'(x) = x^3",
                "f'(x) = 2"
        };
        frageFinal3 = new Frage("Wie lautet die Ableitung der Funktion f(x) = x^2?", 0, antwortenFinal3);

        antwortenFinal4 = new String[]{
                "Eine Tabelle ohne Primärschlüssel",
                "Eine Tabelle, die nur aus Fremdschlüsseln besteht",
                "Eine Tabelle, die Redundanzen und Anomalien minimiert",
                "Eine Tabelle, die ausschließlich numerische Werte enthält"
        };
        frageFinal4 = new Frage("Was ist das Ziel der Normalisierung in relationalen Datenbanken?", 2, antwortenFinal4);

        antwortenFinal5 = new String[]{
                "Die Berechnung des optimalen Lagerbestands",
                "Die Bewertung einer Investition anhand zukünftiger Ein- und Auszahlungen",
                "Die Analyse der Mitarbeiterzufriedenheit",
                "Die Ermittlung der optimalen Produktionsmenge"
        };
        frageFinal5 = new Frage("Was ist das Ziel der Kapitalwertmethode?", 1, antwortenFinal5);

        abschluss = new Frage[]{frageFinal1, frageFinal2, frageFinal3, frageFinal4, frageFinal5};
    }

    private void gegnerAnlegen(){
        Gegner gegner1, gegner2, gegner3, gegner4, gegner5, gegner6, finals;

        gegner1 = new Professor("Matheprofessor", fragenListe.get(0));
        gegnerListe.add(0, gegner1);

        gegner2 = new Professor("Informatikprofessor", fragenListe.get(1));
        gegnerListe.add(1, gegner2);

        gegner4 = new Professor("BWL Professor", fragenListe.get(2));
        gegnerListe.add(2, gegner4);

        gegner5 = new Professor("Informatikprofessor spez. Betriebssysteme", fragenListe.get(3));
        gegnerListe.add(3, gegner5);

        Aufgabe ti = new Aufgabe("Durch welches Tupel wird ein DEA definiert?", "M = (Z, Sigma, Delta, z0, E)");
        gegner3 = new Praktikum("Theoretische Informatik", ti);
        gegnerListe.add(4, gegner3);

        Aufgabe pse = new Aufgabe("Was steht in der ersten Zeile eines HTTP-Request und durch welchen Header erreicht man VirtualHosting?",
                "In der ersten Zeile stehen, die Methode (meist GET), die Ressource und die HTTP-Version." +
                        "\nAlle mit Leereichen getrennt." +
                        "\nVirtualHosting kann durch den Host Header erreicht werden");
        gegner6 = new Praktikum("Projekt-Systementwicklung", pse);
        gegnerListe.add(5, gegner6);

        finals = new Abschlusspruefung("Abschlussprüfung", abschluss);
        gegnerListe.add(6, finals);
    }

    /**
     * Erzeuge alle R�ume und verbinde ihre Ausg�nge miteinander.
     */
    private void raeumeAnlegen()
    {
        Raum draussen, kammer, hoersaal1, hoersaal2, cafeteria, labor1, labor2, buero1, buero2, flur1, flur2, flur3, flur4, flur5, toilette, archiv, archiv2, bibliothek, abschluss;
        Hilfsmittel s1 = new Spickzettel("Spickzettel");
        Hilfsmittel s2 = new Spickzettel("Spickzettel");
        Hilfsmittel p1 = new Praktikumsloesung("Praktikumslösung");
        Hilfsmittel p2 = new Praktikumsloesung("Praktikumslösung");
        // die R�ume erzeugen
        draussen = new Raum("vor dem Haupteingang der Universit�t");
        cafeteria = new Raum("in der Cafeteria der Uni");
        toilette = new Raum("im Badezimmer", s1);
        flur1 = new Raum("im Flur" , p1);
        kammer = new Raum("in einer Abstellkammer", s2);
        archiv = new Raum("im Archiv");
        archiv2 = new Raum("im Archiv");
        bibliothek = new Raum("in der Bibliothek", p2);
        flur2 = new Raum("im Flur");
        flur3 = new Raum("im Flur");
        flur4 = new Raum("im Flur");
        flur4.setSecret(true);
        flur5 = new Raum("im Flur");
        flur5.setSecret(true);

        labor1 = new Raum("in einem Rechnerraum", gegnerListe.get(4));
        labor2 = new Raum("in einem Rechnerraum", gegnerListe.get(5));

        hoersaal1 = new Raum("in einem Vorlesungssaal", gegnerListe.get(0));
        buero1 = new Raum("im Verwaltungsb�ro der Informatik", gegnerListe.get(1));
        hoersaal2 = new Raum("in einem Hörsaal der BWL", gegnerListe.get(2));
        buero2 = new Raum("in einem Büro eines Betriebssystemspezialisten", gegnerListe.get(3));

        abschluss = new Raum("im finalen Level", gegnerListe.get(6));
        abschluss.setSecret(true);

        raumArr[0] = new Raum[]{null, null, null, null, null, null, null, null, null, null};
        raumArr[1] = new Raum[]{bibliothek, null, buero2, null, labor1, flur4, flur5, abschluss, null, null};
        raumArr[2] = new Raum[]{flur2, labor2, archiv, buero1, flur1, null, null, null, null, null};
        raumArr[3] = new Raum[]{null, null, null, null, draussen, null, null, null, null, null};
        raumArr[4] = new Raum[]{null, null, kammer, archiv2, toilette, null, null, null, null, null};
        raumArr[5] = new Raum[]{null, hoersaal2, flur3, null, cafeteria, hoersaal1, null, null, null, null};

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 0) {
                    if (raumArr[i][j] != null && raumArr[i][j + 1] != null) {
                        raumArr[i][j].setzeAusgang("east", raumArr[i][j + 1]);
                        raumArr[i][j + 1].setzeAusgang("west", raumArr[i][j]);
                    }
                } else {
                    if (raumArr[i][j] != null) {
                        if (raumArr[i - 1][j] != null) {
                            raumArr[i][j].setzeAusgang("north", raumArr[i - 1][j]);
                            raumArr[i - 1][j].setzeAusgang("south", raumArr[i][j]);
                        }
                        if (raumArr[i][j + 1] != null) {
                            raumArr[i][j].setzeAusgang("east", raumArr[i][j + 1]);
                            raumArr[i][j + 1].setzeAusgang("west", raumArr[i][j]);
                        }
                    }
                }
            }
        }

        aktuellerRaum = draussen;  // das Spiel startet draussen
    }

    /**
     * Die Hauptmethode zum Spielen. Läuft bis zum Ende des Spiels
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
            if (aktuellerRaum.getGegner() instanceof Abschlusspruefung fin) {
                while (fin.getFrageNr() < 5) {
                    fin.stelleFragen(fin.getFrageNr());
                    befehl = parser.liefereBefehl();
                    beendet = verarbeiteBefehl(befehl);
                }
                System.out.println("Du hast " + fin.getRichtigeAntworten() + "/5 Fragen richtig beantwortet");
                if (fin.getRichtigeAntworten() >= 3) {
                    fin.setBestanden(true);
                    System.out.println("Herzlichen Glückwunsch, du hast bestanden!!!");
                    break;
                } else {
                    System.out.println("Das war leider nichts, wenn du möchtest kannst du es nochmal von neu versuchen");
                    break;
                }
            }
        }
        System.out.println("Danke fÜr dieses Spiel. Auf Wiedersehen.");
    }
    
    /**
     * Einen Begrüßungstext für den Spieler ausgeben.
     */
    private void willkommenstextAusgeben()
    {
        System.out.println();
        System.out.println("Willkommen zum Unisimulator!");
        System.out.println("Du spielst hier das Leben eines Studenten nach.");
        System.out.println("Dein Ziel ist es, die Abschlussprüfung zu meistern. Einmal begonnen gibt es kein Zurück mehr");
        System.out.println("Um für diese zugelassen zu werden musst du allerdings erstmal alle Praktika bestehen");
        System.out.println("Wenn du es dir einfach machen möchtest kannst du auf dem Gelände nach Spickzetteln suchen,");
        System.out.println("aber Vorsicht, überall lauern Professoren, die dir mit ihren Fragen deine Hoffnung auf einen Abschluss klauen.");
        System.out.println("Tippe 'help', wenn du Hilfe brauchst.");
        System.out.println();
        System.out.println(aktuellerRaum.gibLangeBeschreibung());
    }

    /**
     * Verarbeite einen gegebenen Befehl (führe ihn aus).
     * Wenn der Befehl das Spiel beendet, wird 'true' zurückgeliefert,
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
            System.out.println("Dort ist keine Tür!");
        else if (alleBesiegt == false && naechsterRaum.isSecret()) {
            System.out.println("Diesen Raum kannst du noch nicht betreten");
        } else {
            aktuellerRaum = naechsterRaum;
            System.out.println(aktuellerRaum.gibLangeBeschreibung());
        }
    }

    /**
     * Verarbeitung des "answer" Befehls
     * @param befehl
     */
    private void beantworteFrage(Befehl befehl) {
        if (aktuellerRaum.getGegner() == null || (aktuellerRaum.getGegner() instanceof Praktikum prak)) {
            System.out.println("In diesem Raum gibt es keinen Gegner der dir eine Frage stellt");
            return;
        }

        if (!befehl.hatZweitesWort()) {
            System.out.println("Wie lautet deine Antwort?");
            return;
        }
        if ((aktuellerRaum.getGegner() instanceof Professor prof)) {
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
                if (hoffnung < 3 && prof.tryCount() == 0) {
                    System.out.println("Du hast beim ersten Mal richtig geantwortet und erhältst einen Hoffnungsboost.");
                    hoffnung++;
                    gibLebenAus();
                }
                System.out.println(aktuellerRaum.gibLangeBeschreibung());
            } else {
                prof.setTryCount();
                System.out.println("Das war die falsche Antwort, du verlierst Hoffnung");
                hoffnung = hoffnung - 1;
                gibLebenAus();
            }
        }

        if ((aktuellerRaum.getGegner() instanceof Abschlusspruefung fin)) {
            int antwort;
            try {
                antwort = Integer.parseInt(befehl.gibZweitesWort());
            } catch (NumberFormatException ignored) {
                System.out.println("Gib bitte eine verfügbare Zahl an");
                return;
            }
            if (fin.getFragen()[fin.getFrageNr()].getRichtigeAntwort() == antwort - 1) {
                fin.setRichtigeAntworten();
                fin.setFrageNr();
            }
            else { fin.setFrageNr(); };
        }
    }

    /**
     * Verarbeitung des "collect" Befehls, erwartet kein zweites Wort
     */
    private void sammleHilfsmittel(Befehl befehl){
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

    /**
     * Gebe Inventar aus
     */
    public void zeigeInventar(){
        int i = 1;
        for (Hilfsmittel item : inventar) {
            System.out.println(i + ". " + item.getBeschreibung());
            i++;
        }
    }

    /**
     * Verarbeitung des "use" Befehls
     * Erwartet eine Zahl als zweites Wort und nutzt das Item an dieser Stelle des Inventars falls möglich
     * @param befehl
     */
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
            if (aktuellerRaum.getGegner() instanceof Professor prof) {
                //prof = (Professor) aktuellerRaum.getGegner();
                System.out.println("Die richtige Antwort ist: " + spicker.richtigeAntwort(prof.getFrage()));
            }
            if (aktuellerRaum.getGegner() instanceof Abschlusspruefung fin) {
                System.out.println("Die richtige Antwort ist: " + spicker.richtigeAntwort(fin.getFragen()[fin.getFrageNr()]));
            }
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

    /**
     * Gib Anzahl der verbleibenen Hoffnung aus
     */
    public void gibLebenAus() {
        System.out.println("Du hast noch " + hoffnung + " Hoffnung");
    }

    /**
     * "quit" wurde eingegeben. überprüfe den Rest des Befehls,
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
