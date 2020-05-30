 
package receptkönyv;

////Egy Alapanyag osztály a különböző alapanyagoknak\\\\
/*  ADATTAGJAI:
 *      String nev - az alapanyag neve
 *      String mertek - az alapanyag mertekegysége(pl. kg, dkg..)
 *      int mennyiseg - az alapanyag mennyisége
 *
 *  METÓDUSAI:
 *      konstruktor
 *      getterek és setterek
 */

public class Alapanyag {
    private String nev;
    private String mertek;
    private int mennyiseg;

    public Alapanyag(String nev, String mertek, int mennyiseg) {
        this.nev = nev;
        this.mertek = mertek;
        this.mennyiseg = mennyiseg;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getMertek() {
        return mertek;
    }

    public void setMertek(String mertek) {
        this.mertek = mertek;
    }

    public int getMennyiseg() {
        return mennyiseg;
    }

    public void setMennyiseg(int mennyiseg) {
        this.mennyiseg = mennyiseg;
    }
    
    
}
