/*
 * Diese Klasse h�lt eine Aufz�hlung aller Befehlsw�rter, die dem
 * Spiel bekannt sind. Mit ihrer Hilfe werden eingetippte Befehle
 * erkannt.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (M�rz 2003)
 */

class Befehlswoerter
{
    // ein konstantes Array mit den g�ltigen Befehlsw�rtern
    private static final String gueltigeBefehle[] = {
        "go", "quit", "help", "jump", "dodge", "attack", "collect", "answer"
    };

    /**
     * Konstruktor - initialisiere die Befehlsw�rter.
     */
    public Befehlswoerter()
    {
        // nichts zu tun momentan...
    }

    /**
     * Pr�fe, ob eine gegebene Zeichenkette ein g�ltiger
     * Befehl ist.
     * Liefere 'true', wenn das der Fall ist, 'false' sonst.
     */
    public boolean istBefehl(String eingabe)
    {
        for(int i = 0; i < gueltigeBefehle.length; i++) {
            if(gueltigeBefehle[i].equals(eingabe))
                return true;
        }
        // Wenn wir hierher gelangen, wurde die Eingabe nicht
        // in den Befehlsw�rter gefunden.
        return false;
    }

    /*
     * Gib alle g�ltigen Befehlsw�rter auf die Konsole aus.
     */
    public void alleAusgeben() 
    {
        for(int i = 0; i < gueltigeBefehle.length; i++) {
            System.out.print(gueltigeBefehle[i] + "  ");
        }
        System.out.println();
    }
}
