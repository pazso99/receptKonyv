
package receptkönyv;

import java.sql.*;
import java.util.ArrayList;

////A lokális adatbázist összekapcsaló osztály\\\\
/*  ADATTAGJAI:
 *      Connection con - Létrehozza a kapcsolatot
 *      Statement st - Az sql lekérdezésekhez
 *      ResultSet rs - Sql lekérdezés után visszaadja az adatokat
 *      boolean csat - A konstruktorban kap egy true értéket ha a csatlakozott a db-hez, ha nem akkor false -> hiba kiírása
 *  METÓDUSAI:
 *      konstruktor
 *      addEtel() - étel hozzáadása az adatbázisba
 *      addAlapanyag() - alapanyag hozzáadása
 *      addAlapanyagToEtel() - az alapanyagok étellel való összekapcsolása
 *      isAlapanyag() - megnézi hogy van-e ilyen alapanyag az adatbázisban
 *      isEtel() - megnézi hogy van-e ilyen étel az adatbázisban
 *      etellista() - visszaadja a ételek listáját az adatbázisból
 *      getData() - valamilyen étel vagy alapanyag adatának lekérése
 *      getEtelAlapanyag() - az adott étel alapanyagait küldi vissza
 *      torol() - étel törlése az adatbázisból
 *      updateAlapanyag() - alapanyag módosítása a recept hozzáadásánál
 */

public class DbConnection {

    private Connection con; 
    private Statement st;  
    private ResultSet rs;
    public boolean csat;
   
   
   /* [A db csatlakozásának konstruktora, itt csatlakozik a lokális adatbázishoz, aminek a neve: recept]
    */
    public DbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recept?useUnicode=true&characterEncoding=utf-8", "root", "");
            st = con.createStatement();
            csat = true;

        } catch (Exception e) {
            System.out.println("nem csat");
            csat = false;
        }
    }

    
   /* [Étel hozzáadása az adatbázisba, az etel táblába]
    * paraméter: az adott ételhez szükséges adatok
    * return: egy logikai érték amivel lelehet ellenőrizni, hogy sikeres volt-e az insert
    */
    public boolean addEtel(String nev, String tipus, int ido, int adag, String elkeszites) {
    //INSERT INTO `etel` (`id`, `nev`, `tipus`, `ido`, `fo`, `elkeszites`) VALUES (NULL, 'Palacsinta', 'Főétel', '30', '4', 'A palacsintatészta..');
        
        try {
            String str = "INSERT INTO `etel` (`id`, `nev`, `tipus`, `ido`, `fo`, `elkeszites`) VALUES (NULL, '" + nev + "', '" + tipus + "', '" + ido + "', '" + adag + "', '" + elkeszites + "');";
            st.executeUpdate(str);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
   /* [Alapanyag hozzáadása az adatbázisba, az alapanyag táblába]
    * paraméter: az adott alapanyag neve
    * return: egy logikai érték amivel lelehet ellenőrizni, hogy sikeres volt-e a query
    */
    public boolean addAlapanyag(String nev) {
    //INSERT INTO `alapanyag` (`id`, `nev`) VALUES (NULL, 'liszt');
        
        try {
            String str = "INSERT INTO `alapanyag` (`id`, `nev`) VALUES (NULL, '"+ nev +"');";
            st.executeUpdate(str);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
   /* [Ételhez alapanyag hozzáadása az adatbázisban a hozzadad kapcsolótáblába]
    * paraméter: az adott etel es alapanyag idje, plusz a további adatai(mennyiseg, mertekegyseg)
    * return: egy logikai érték amivel lelehet ellenőrizni, hogy sikeres volt-e a query
    */
    public boolean addAlapanyagToEtel(int etelId, int alapanyagId, int mennyiseg, String mertekegyseg ) {
    //INSERT INTO `hozzadad` (`etel.id`, `alapanyag.id`, `mennyiseg`, `mertekegyseg`) VALUES ('3', '18', '20', 'dkg');
        
        try {
            String str = "INSERT INTO `hozzadad` (`etel.id`, `alapanyag.id`, `mennyiseg`, `mertekegyseg`) VALUES ('"+ etelId +"', '"+ alapanyagId +"', '"+ mennyiseg +"', '"+ mertekegyseg +"');";
            st.executeUpdate(str);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }    
    

   /* [Megnézi, hogy az adott alapanyag benne van már az adatbázisban]
    * paraméter: az adott alapanyag neve
    * return: egy logikai érték amivel lelehet ellenőrizni, hogy sikeres volt-e a query
    */
    public boolean isAlapanyag(String nev) {
        try {    
            String query = "SELECT * FROM `alapanyag` WHERE nev='" + nev + "';";
            //System.out.println(query + "\n");
            rs = st.executeQuery(query);
            while (rs.next()) {
                return true;
            } return false;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
 
    
   /* [Megnézi, hogy van-e ilyen étel az adatbázisban]
    * paraméter: az adott alapanyag neve
    * return: egy logikai érték amivel lelehet ellenőrizni, hogy sikeres volt-e a query
    */
    public boolean isEtel(String nev) {
        try {    
            String query = "SELECT * FROM `etel` WHERE nev='" + nev + "';";
            //System.out.println(query + "\n");
            rs = st.executeQuery(query);
            while (rs.next()) {
                return true;
            } return false;
             
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
  
    
   /* [Lekérdezi az összes ételt az adatbázisból és egy Arraylistben adja vissza]
    * paraméter:
    * return: ArrayList ami Etel tipusu
    */
    public ArrayList<Etel> etellista() {
        
        try {    
            ArrayList<Etel> etelek = new ArrayList<Etel>();
            String query = "SELECT * FROM `etel`;";
            rs = st.executeQuery(query);
            while (rs.next()) {
                String nev = rs.getString("nev");
                String tipus = rs.getString("tipus");
                int ido = rs.getInt("ido");
                int fo = rs.getInt("fo");
                String elkeszites = rs.getString("elkeszites");
                Etel etel = new Etel(nev, tipus, ido, fo, elkeszites);
                etelek.add(etel);
            }
            return etelek;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
   /* [Bármilyen étel vagy alapanyag adat lekérdezése]
    * paraméter: a tábla, étel/alapanyag neve és hogy mit akarunk lekérdezni a táblából
    * return: a lekérdezett adat, ha hiba volt a lekérdezéssel akkor -1
    */
    public int getData(String tabla, String nev, String mit) {

        try {
            String query = "select " + mit + " from "+ tabla +" where nev='" + nev + "'";
            query = query.toLowerCase();
            rs = st.executeQuery(query);
            while (rs.next()) {
                int value = rs.getInt(mit);
                return value;
            }
            return -1;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    
   /* [Egy adott étel alapanyagainak adatainak lekérdezése az alapanyag és a kapcsólótáblából, és ezt egy ArrayListben adja vissza]
    * paraméter: az adott étel neve
    * return: egy Alapanyag tipusu ArrayList
    */
    public ArrayList<Alapanyag> getEtelAlapanyag(String nev) {
        //SELECT alapanyag.nev, hozzadad.mennyiseg, hozzadad.mertekegyseg FROM `alapanyag` INNER JOIN `hozzadad` ON alapanyag.id = `alapanyag.id` INNER JOIN `etel` ON `etel.id` = etel.id WHERE etel.nev='Fagyi'    
        try {
            ArrayList<Alapanyag> alapanyagok = new ArrayList<Alapanyag>();
            String query = "SELECT alapanyag.nev, hozzadad.mennyiseg, hozzadad.mertekegyseg FROM `alapanyag` INNER JOIN `hozzadad` ON alapanyag.id = `alapanyag.id` INNER JOIN `etel` ON `etel.id` = etel.id WHERE etel.nev='"+ nev +"'";
            query = query.toLowerCase();
            rs = st.executeQuery(query);
            while (rs.next()) {
                String alapanyag = rs.getString("nev");
                int mennyiseg = rs.getInt("mennyiseg");
                String mertek = rs.getString("mertekegyseg");
                Alapanyag a = new Alapanyag(alapanyag,mertek,mennyiseg);
                alapanyagok.add(a);
            }
            return alapanyagok;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
   /* [Étel törlése az adatbázisból]
    * paraméter: az adott étel neve
    * return: egy logikai érték amivel lelehet ellenőrizni, hogy sikeres volt-e a query
    */
    public boolean torol(String nev) {
        //DELETE FROM `etel` WHERE `etel`.`nev` = nev
        try {
            PreparedStatement pst = con.prepareStatement("DELETE FROM `etel` WHERE `etel`.`nev` = '"+ nev +"'");
            pst.executeUpdate(); 
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
