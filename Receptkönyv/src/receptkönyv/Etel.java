
package receptkönyv;

////Egy Étel osztály a különböző ételeknek\\\\
/*  ADATTAGJAI:
 *      String nev - az étel neve
 *      String tipus - az alapanyag típusa(Leves, Főétel, Sütemény)
 *      int ido - az étel mennyi perc alatt készül el
 *      int fo - az étel mennyi főre van
 *      String elkeszites - az étel elkészítése
 *
 *  METÓDUSAI:
 *      konstruktor
 *      getterek és setterek
 */

public class Etel {
    private String nev;
    private String tipus;
    private int ido;
    private int fo;
    private String elkeszites;

    public Etel(String nev, String tipus, int ido, int fo, String elkeszites) {
        this.nev = nev;
        this.tipus = tipus;
        this.ido = ido;
        this.fo = fo;
        this.elkeszites = elkeszites;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public int getIdo() {
        return ido;
    }

    public void setIdo(int ido) {
        this.ido = ido;
    }

    public int getFo() {
        return fo;
    }

    public void setFo(int fo) {
        this.fo = fo;
    }

    public String getElkeszites() {
        return elkeszites;
    }

    public void setElkeszites(String elkeszites) {
        this.elkeszites = elkeszites;
    }
    
    
}
