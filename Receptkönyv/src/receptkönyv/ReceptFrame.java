
package receptkönyv;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

////A Recept frame osztálya\\\\
/*  ADATTAGJAI:
 *      DbConnection db - Létrehozza a kapcsolatot
 *      alapanyagok - Alapanyag tipusu ArrayList, az Alapanyagok mentéséhez
 *      alapanyagokLista - String tipusu ArrayList, csak az Alapanyag nevét tartalmazza, egyszeri feltételhez szükséges(van-e már ilyen alapanyagnév)
 *      etelek - Etel tipusu ArrayList, az Etelek mentéséhez
 *  METÓDUSAI:
 *      konstruktor
 *      frissit() - frissiti az alapanyagok listáját egy adott recept létrehozásánál
 *      errorMessage() - egy előreugró ablak a user tájékoztatására
 *      isNumeric() - numerikus-e a szöveg amit adtunk neki
 *      kilistaz() - a táblát tölti fel ételekkel
 *  ESEMÉNYEK :
 *      receptHozzaadBtnActionPerformed() - recept hozzáadása panelhez visz
 *      receptListaBtnActionPerformed() - recept listájához visz
 *      alapanyagHozzaadActionPerformed() - alapanyag hozzáadása a recept készítésénél
 *      alapanyagSzerkesztActionPerformed() - alapanyag szerkesztése a recept készítésénél
 *      alapanyagBoxActionPerformed() - alapanyag nevének kiválasztása az alapanyag szerkesztéséhez
 *      receptAddActionPerformed() - recept felvétele az adatbázisba
 *      listazBtnActionPerformed() - az ételek listázása 
 *      receptTablaMouseClicked() - egy recept megjelenítése
 *      visszaBtnActionPerformed() - vissza a ételek listájához
 *      torolBtnActionPerformed() - egy recept törlése
 *      jButton1ActionPerformed() - kiszámolja mennyi alapanyag kell x főre
 */

public class ReceptFrame extends javax.swing.JFrame {
    private DbConnection db;
    private ArrayList<Alapanyag> alapanyagok;
    private ArrayList<String> alapanyagokLista;
    private ArrayList<Etel> etelek;
    
    
   /* [A Frame konstruktora, itt hozzuk létre a adatbázissal való csatlakozást]
    *   ha nem sikerul a csatlakozás: a lista panelt láthatóvá tesszük, és mindent lezárunk, és kiírjuk neki a hibát
    *   ha sikerul: példányosítunk, és láthatóva tesszük a lista panelt, és itt már kilistázzuk az ételeket
    */
    public ReceptFrame() {
        initComponents();
        db = new DbConnection();
        
        if(db.csat == false) {
            receptLista.setVisible(true);
            receptHozzaad.setVisible(false);
            recept.setVisible(false);
            receptHozzaadBtn.setEnabled(false);
            receptListaBtn.setEnabled(false);
            receptTipusBox.setEnabled(false);
            listazBtn.setEnabled(false);
            receptTabla.setEnabled(false);
            errorMessage("Nem csatlakozott az adatbázishoz, kapcsold be az adatbázist és indítsd újra a programot!", 1);
            
        } else {
            alapanyagok = new ArrayList<Alapanyag>();
            alapanyagokLista = new ArrayList<String>();
            etelek = db.etellista();    // a DbConnection metódusa: lekérdezi az összes ételt és listában visszaadja
            receptLista.setVisible(true);
            receptHozzaad.setVisible(false);
            recept.setVisible(false);
            kilistaz("Összes");
            alapanyagSzerkeszt.setEnabled(false);
        }
    }
    
    
   /* [A recept hozzáadása panelnél, frissítjük az alapanyagok listáját]
    * paraméter: 
    * return: 
    */
    private void frissit() {
        alapanyagArea.setText("");
        for(int i = 0; i < alapanyagok.size(); i++) {
            Alapanyag a = alapanyagok.get(i);
          //pl: ablakmosó: 2 l
            alapanyagArea.append(a.getNev() + ": " + a.getMennyiseg() + " " + a.getMertek() + "\n");
        }
    }
    
    
   /* [Egy felugró ablak a user tájékoztatásához]
    * paraméter: az adott hiba amit kiír és mennyi idő alatt
    * return: 
    */
    private void errorMessage(String error, int second) {
        Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                  JOptionPane.showMessageDialog(null,error);
                }
              }, second*1000);
    }
    
    
   /* [Megnézi hogy egy kapott String átváltás után numerikus-e]
    * paraméter: egy String
    * return: egy logikai érték amivel lelehet ellenőrizni, hogy numerikus-e a String
    */
    private boolean isNumeric(String str) { 
        try {  
            Integer.parseInt(str);  
            return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }
    
    
   /* [A táblába belerakja az ételek tulajdonságait]
    * paraméter: milyen típus alapján listázzon
    *       ha Összes: mindent kilistáz
    * return: 
    */
    private void kilistaz(String mit) {
        etelek = db.etellista();
        int sorokSzama = etelek.size();
        Object data[][] = new Object[sorokSzama][3];
        
        if(mit == "Összes") {
            for(int i = 0; i < etelek.size(); i++) {
                Etel etel = etelek.get(i);
                data[i][0] = etel.getNev();
                data[i][1] = etel.getIdo();
                data[i][2] = etel.getFo();  
            }
            receptTabla.setModel(new javax.swing.table.DefaultTableModel(data,new String[] { "Név", "Idő(perc)", "fő(adag)" }));
        
        } else {
            int sor = 0;
            for(int i = 0; i < etelek.size(); i++) {
                Etel etel = etelek.get(i);
                if(etel.getTipus().equals(mit)) {
                    data[sor][0] = etel.getNev();
                    data[sor][1] = etel.getIdo();
                    data[sor][2] = etel.getFo();
                    sor++;
                }
            }
            receptTabla.setModel(new javax.swing.table.DefaultTableModel(data,new String[] { "Név", "Idő(perc)", "fő(adag)" }));
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        receptHozzaad = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tipusBox = new javax.swing.JComboBox();
        idoField = new javax.swing.JTextField();
        foField = new javax.swing.JTextField();
        nevField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        elkeszitesField = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        alapanyagArea = new javax.swing.JTextArea();
        alapanyagNevField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        mennyisegField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        mertekBox = new javax.swing.JComboBox();
        alapanyagHozzaad = new javax.swing.JButton();
        alapanyagSzerkeszt = new javax.swing.JButton();
        alapanyagBox = new javax.swing.JComboBox();
        receptAdd = new javax.swing.JButton();
        receptLista = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        receptTabla = new javax.swing.JTable();
        receptTipusBox = new javax.swing.JComboBox();
        listazBtn = new javax.swing.JButton();
        recept = new javax.swing.JPanel();
        receptNev = new javax.swing.JLabel();
        visszaBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        alapanyagokArea = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        receptElkeszitesArea = new javax.swing.JTextArea();
        receptFo = new javax.swing.JLabel();
        receptIdo = new javax.swing.JLabel();
        torolBtn = new javax.swing.JButton();
        pwField = new javax.swing.JPasswordField();
        foreField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        ennyiFore = new javax.swing.JLabel();
        receptHozzaadBtn = new javax.swing.JButton();
        receptListaBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Név: ");

        jLabel2.setText("Fő:");

        jLabel3.setText("Idő: ");

        jLabel4.setText("Típus:");

        jLabel5.setText("Elkészítés:");

        tipusBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Leves", "Főétel", "Sütemény" }));

        elkeszitesField.setColumns(2);
        elkeszitesField.setRows(5);
        elkeszitesField.setAutoscrolls(false);
        jScrollPane1.setViewportView(elkeszitesField);

        alapanyagArea.setEditable(false);
        alapanyagArea.setColumns(2);
        alapanyagArea.setRows(5);
        alapanyagArea.setFocusable(false);
        jScrollPane2.setViewportView(alapanyagArea);

        jLabel6.setText("Alapanyag neve:");

        jLabel7.setText("Mennyiség: ");

        jLabel8.setText("Mérték:");

        mertekBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "g", "dkg", "kg", "dl", "l", "ek", "db", "csipet" }));

        alapanyagHozzaad.setText("Alapanyag hozzáadása");
        alapanyagHozzaad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alapanyagHozzaadActionPerformed(evt);
            }
        });

        alapanyagSzerkeszt.setText("Alapanyag szerkesztése");
        alapanyagSzerkeszt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alapanyagSzerkesztActionPerformed(evt);
            }
        });

        alapanyagBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alapanyagok" }));
        alapanyagBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alapanyagBoxActionPerformed(evt);
            }
        });

        receptAdd.setText("RECEPT HOZZÁADÁSA");
        receptAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receptAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout receptHozzaadLayout = new javax.swing.GroupLayout(receptHozzaad);
        receptHozzaad.setLayout(receptHozzaadLayout);
        receptHozzaadLayout.setHorizontalGroup(
            receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receptHozzaadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(receptAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(receptHozzaadLayout.createSequentialGroup()
                        .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                            .addComponent(jLabel5))
                        .addGap(24, 24, 24)
                        .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tipusBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(idoField)
                                .addComponent(foField))
                            .addGroup(receptHozzaadLayout.createSequentialGroup()
                                .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nevField, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(receptHozzaadLayout.createSequentialGroup()
                                            .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(mennyisegField)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(mertekBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(alapanyagHozzaad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(alapanyagSzerkeszt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel6)
                                    .addComponent(alapanyagNevField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2)
                                    .addComponent(alapanyagBox, 0, 164, Short.MAX_VALUE))
                                .addGap(63, 63, 63)))))
                .addContainerGap())
        );
        receptHozzaadLayout.setVerticalGroup(
            receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receptHozzaadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nevField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(foField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(idoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tipusBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(receptHozzaadLayout.createSequentialGroup()
                        .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(receptHozzaadLayout.createSequentialGroup()
                                .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(receptHozzaadLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(alapanyagNevField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(mennyisegField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(mertekBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel5))
                                .addGap(15, 15, 15)
                                .addComponent(alapanyagHozzaad))
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(receptHozzaadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(alapanyagSzerkeszt)
                            .addComponent(alapanyagBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(receptAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        receptTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Név", "Idő", "Fő"
            }
        ));
        receptTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                receptTablaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(receptTabla);

        receptTipusBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Összes", "Leves", "Főétel", "Sütemény" }));

        listazBtn.setText("KILISTÁZ");
        listazBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listazBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout receptListaLayout = new javax.swing.GroupLayout(receptLista);
        receptLista.setLayout(receptListaLayout);
        receptListaLayout.setHorizontalGroup(
            receptListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receptListaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(receptListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                    .addComponent(receptTipusBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(receptListaLayout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(listazBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        receptListaLayout.setVerticalGroup(
            receptListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, receptListaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(receptTipusBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(listazBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        receptNev.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        receptNev.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        receptNev.setText("RECEPT");
        receptNev.setToolTipText("");

        visszaBtn.setText("VISSZA");
        visszaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visszaBtnActionPerformed(evt);
            }
        });

        jLabel9.setText("Fő: ");

        jLabel10.setText("Idő:");

        jLabel11.setText("Alapanyagok:");

        alapanyagokArea.setEditable(false);
        alapanyagokArea.setTabSize(0);
        jScrollPane3.setViewportView(alapanyagokArea);

        jLabel12.setText("Elkészítés: ");

        receptElkeszitesArea.setEditable(false);
        receptElkeszitesArea.setTabSize(0);
        receptElkeszitesArea.setAutoscrolls(false);
        jScrollPane5.setViewportView(receptElkeszitesArea);

        receptFo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        receptFo.setText("10");

        receptIdo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        receptIdo.setText("10");

        torolBtn.setText("RECEPT TÖRLÉSE");
        torolBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                torolBtnActionPerformed(evt);
            }
        });

        jLabel13.setText("Hány főre akarod elkészíteni?");

        jButton1.setText("Mehet");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel14.setText("alapból");

        ennyiFore.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout receptLayout = new javax.swing.GroupLayout(recept);
        recept.setLayout(receptLayout);
        receptLayout.setHorizontalGroup(
            receptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receptLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(receptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(receptLayout.createSequentialGroup()
                        .addGroup(receptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(receptNev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(receptLayout.createSequentialGroup()
                                .addGroup(receptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(visszaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(receptLayout.createSequentialGroup()
                        .addGroup(receptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(receptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, receptLayout.createSequentialGroup()
                                .addGroup(receptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(receptLayout.createSequentialGroup()
                                        .addGap(0, 401, Short.MAX_VALUE)
                                        .addComponent(pwField, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(torolBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(receptLayout.createSequentialGroup()
                                        .addGroup(receptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(receptLayout.createSequentialGroup()
                                                .addComponent(ennyiFore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(271, 271, 271))
                                            .addGroup(receptLayout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(receptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(receptLayout.createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(68, 68, 68)
                                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                .addGap(20, 20, 20))
                            .addGroup(receptLayout.createSequentialGroup()
                                .addGroup(receptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(receptFo, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                    .addComponent(receptIdo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(foreField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(116, 116, 116))))))
        );
        receptLayout.setVerticalGroup(
            receptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receptLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(receptNev, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(receptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(receptFo)
                    .addComponent(foreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jButton1)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(receptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(receptIdo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(receptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(ennyiFore))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(visszaBtn)
                    .addComponent(torolBtn)
                    .addComponent(pwField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(receptHozzaad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(receptLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(recept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(receptHozzaad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(receptLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(recept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(11, 11, 11)))
        );
        jLayeredPane1.setLayer(receptHozzaad, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(receptLista, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(recept, javax.swing.JLayeredPane.DEFAULT_LAYER);

        receptHozzaadBtn.setText("ÚJ RECEPT HOZZÁADÁSA");
        receptHozzaadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receptHozzaadBtnActionPerformed(evt);
            }
        });

        receptListaBtn.setText("RECEPTEK LISTÁJA");
        receptListaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receptListaBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(receptHozzaadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(receptListaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(receptHozzaadBtn)
                    .addComponent(receptListaBtn))
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    // A receptHozzaad panelhez visz
    private void receptHozzaadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receptHozzaadBtnActionPerformed
        receptLista.setVisible(false);
        receptHozzaad.setVisible(true);
        recept.setVisible(false);
    }//GEN-LAST:event_receptHozzaadBtnActionPerformed

    
    // A recptek listájához visz, és alapbol kilistázza az összes receptet
    private void receptListaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receptListaBtnActionPerformed
        receptLista.setVisible(true);
        receptHozzaad.setVisible(false);
        recept.setVisible(false);
        kilistaz("Összes");
    }//GEN-LAST:event_receptListaBtnActionPerformed

    
    // Alapanyag hozzáadása a TextArea-ba
    private void alapanyagHozzaadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alapanyagHozzaadActionPerformed
        
        // hibák kezelése és azoknak else ágában az ablak feldobása
        if(alapanyagNevField.getText().length() > 0 && mennyisegField.getText().length() > 0) {
            
            if(isNumeric(mennyisegField.getText()) && !isNumeric(alapanyagNevField.getText())) {
                
                //az alapanyag nevét kisbetűvel, és a mennyiseget, merteket is elmentjük
                String alapanyagNev = alapanyagNevField.getText().toLowerCase();
                int mennyiseg = Integer.parseInt(mennyisegField.getText());
                String mertek = (String)mertekBox.getSelectedItem();
                
                // megnézi, hogy nincs-e benne a TextAreaba az alapanyag neve, ha nincs akkor belerakja -> nem lehet egyszerre több ugyanolyan alapnyagnév!
                if(!alapanyagokLista.contains(alapanyagNev)) {
                    alapanyagokLista.add(alapanyagNev);
                    // csinál egy alapanyagot, majd hozzadja a listához, és frissíti a TextAreát
                    Alapanyag a = new Alapanyag(alapanyagNev, mertek ,mennyiseg);
                
                    alapanyagok.add(a);
                    frissit();
                    // a lenyíló listába is belementi, hogy tudjuk majd szerkeszteni
                    alapanyagBox.addItem(alapanyagNev);
                    
                    
                    // Az adatbázisba rakja bele neve alapján
                    if(db.isAlapanyag(alapanyagNev)) {
                        System.out.println(alapanyagNev + " már benne van az adatbázisban");
                    } else {
                        db.addAlapanyag(alapanyagNev);
                        System.out.println(alapanyagNev + " hozzáadva az adatbázisba");
                    }
                    // Az inputokat kinullázuk
                    alapanyagNevField.setText("");
                    mennyisegField.setText("");
                    mertekBox.setSelectedIndex(0);
                    
                } else {
                    errorMessage(alapanyagNev + " már benne van az alapanyagok közt",0);
                } 
            } else {
                    errorMessage("Minden alapanyag mezőbe rendes adatot adj meg!",0);
            }
        } else {
            errorMessage("Add meg az összes mezőt!",0);
        }
    }//GEN-LAST:event_alapanyagHozzaadActionPerformed

    
    // Alapanyag szerkesztése az alapanyagok indexe alapján
    private void alapanyagSzerkesztActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alapanyagSzerkesztActionPerformed
        
        // az alapanyag lenyíló indexét elmentjük
        int alapanyagIndex = alapanyagBox.getSelectedIndex();
        
        // alapanyag törléséhez a név semmi
        if(alapanyagNevField.getText().length() == 0) {
            
            // a legelső index az 'Alapanyagok', ezért kikell vonni 1-et
            Alapanyag a = alapanyagok.get(alapanyagIndex-1);
            alapanyagok.remove(a);
            alapanyagBox.removeItemAt(alapanyagIndex);
            frissit();
        // ha a név valami akkor módosíthatjuk az adatait
        } else {
            
            // a legelső index az 'Alapanyagok', ezért kikell vonni 1-et 
            // az Alapanyag adatai alapján feltöltjük az input mezőket
            Alapanyag a = alapanyagok.get(alapanyagIndex-1);
            a.setNev(alapanyagNevField.getText());
            a.setMennyiseg(Integer.parseInt(mennyisegField.getText()));
            a.setMertek((String)mertekBox.getSelectedItem());
            frissit();
            // csak ekkor szerkeszthetünk a gombbal
            alapanyagSzerkeszt.setEnabled(false);

            alapanyagBox.setSelectedIndex(0);
            alapanyagNevField.setText("");
            mennyisegField.setText("");
            mertekBox.setSelectedIndex(0);
            
        }
    }//GEN-LAST:event_alapanyagSzerkesztActionPerformed

    
    // Az alapanyag nevének kiválasztása a szerkesztéshez
    private void alapanyagBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alapanyagBoxActionPerformed
        
        int index = alapanyagBox.getSelectedIndex();
        // az első elem az 'Alapanyagok' ezért ha azt jelöljük ki -> alapállapot
        if(index != 0) {
           // csinál egy Alapanyagot az index alapján
            Alapanyag a = alapanyagok.get(index-1);
            String mertek = a.getMertek();
            //g, dkg, kg, dl, l, ek, db, csipet
            // ez alapján állítja be az alapanyag mértékegységét
            int mertekIndex;
                switch(mertek) {
                    case "g" : mertekIndex = 0; break; 
                    case "dkg" : mertekIndex = 1; break;
                    case "kg" : mertekIndex = 2; break;
                    case "dl" : mertekIndex = 3; break; 
                    case "l" : mertekIndex = 4; break; 
                    case "ek" : mertekIndex = 5; break; 
                    case "db" : mertekIndex = 6; break; 
                    case "csipet" : mertekIndex = 7; break;
                    default:  mertekIndex = -1;
                }
            alapanyagNevField.setText(a.getNev());
            mennyisegField.setText(Integer.toString(a.getMennyiseg()));
            mertekBox.setSelectedIndex(mertekIndex);
            alapanyagSzerkeszt.setEnabled(true);
            
        } else {
            alapanyagNevField.setText("");
            mennyisegField.setText("");
            mertekBox.setSelectedIndex(0);
            alapanyagSzerkeszt.setEnabled(false);
        }
    }//GEN-LAST:event_alapanyagBoxActionPerformed

    
    // Recept hozzáadása
    private void receptAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receptAddActionPerformed
        
        // hibakezelés, ezekre hibák kiírása a felugró ablakban
        if(nevField.getText().length() > 0 && foField.getText().length() > 0 && idoField.getText().length() > 0 && elkeszitesField.getText().length() > 0 && alapanyagok.size() > 0) {
            if(isNumeric(foField.getText()) && isNumeric(idoField.getText())) {
                // Az Etel adatainak mentése
                String nev = nevField.getText();
                int fo = Integer.parseInt(foField.getText());
                int ido = Integer.parseInt(idoField.getText());
                String tipus = (String)tipusBox.getSelectedItem();
                String elkeszites = elkeszitesField.getText();
                // itt régebben hiba volt, de igy maradt (hosszú magánhangzókat nem csípte)
                String ujtipus;
                System.out.println(elkeszites);
                switch(tipus) {
                    case "Főétel" : ujtipus = "Főétel"; break;
                    case "Leves" : ujtipus = "Leves"; break;
                    case "Sütemény" : ujtipus = "Sütemény"; break;
                    default: ujtipus = "no";
                }
                
                // megnézi hogy van-e már ilyen nevű étel az adatbázisban, ha nem akkor hozzáadja
                if(!db.isEtel(nev)) {
                    db.addEtel(nev, ujtipus, ido, fo, elkeszites);
                    etelek = db.etellista();
                    
                    // a kapcsolótáblába tölti fel az adatbázban
                    for(int i = 0; i < alapanyagok.size(); i++) {
                        Alapanyag a = alapanyagok.get(i);
                        db.addAlapanyagToEtel(db.getData("etel", nev, "id"), db.getData("alapanyag", a.getNev(), "id"), a.getMennyiseg(), a.getMertek());
                    }
                    // kinullázzuk
                    nevField.setText("");
                    foField.setText("");
                    idoField.setText("");
                    elkeszitesField.setText("");
                    
                    // az adott étel alapanyagainak elemeit töröljük a listákból
                    alapanyagok.clear();
                    alapanyagokLista.clear();
                    frissit();
                    errorMessage(nev + " recept sikeresen hozzáadva!",0);
                    
                } else {
                    errorMessage("Már van ilyen étel az adatbázisban!",0);
                }
               
            } else {
                errorMessage("Megfelelő adatokat adj meg!",0);
            }
        } else {
            errorMessage("Mindenhova irj adatokat!",0);
        }
    }//GEN-LAST:event_receptAddActionPerformed

    
    // Listázás a lenyíló lista alapján
    private void listazBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listazBtnActionPerformed
        String tipus = (String)receptTipusBox.getSelectedItem();
        kilistaz(tipus);
    }//GEN-LAST:event_listazBtnActionPerformed

    
    // A kiválasztott recept adatait jeleníti meg a táblából egy új panelban
    private void receptTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_receptTablaMouseClicked
        try {
        // a kiválasztott sor
        int index = receptTabla.getSelectedRow();
        // lekéri a modelt, és a nevet(első oslop) menti el
        TableModel model = receptTabla.getModel();
        String nev = (String)model.getValueAt(index, 0);
        if(nev != null) {
            alapanyagokArea.setText("");
            // végigmegy az ételek listán 
            for(Etel etel : etelek) {
                //ha egyezik a kiválasztott név, kiírja az adatokat
                if(etel.getNev() == nev) {
                    receptNev.setText(nev);
                    receptIdo.setText(Integer.toString(etel.getIdo()) + " perc");
                    receptFo.setText(Integer.toString(etel.getFo()));
                    receptElkeszitesArea.setText(etel.getElkeszites() + "\n");
                    
                    // az étel alapanyagainak megszerzi és ezt is kiirja
                    ArrayList<Alapanyag> a = db.getEtelAlapanyag(nev);
                    for(int i = 0; i < a.size() ; i++) {
                        Alapanyag al = a.get(i);
                        alapanyagokArea.append(al.getNev() + ": " + al.getMennyiseg() + " " + al.getMertek() + "\n");
                    }
                }
            }
            // megjeleníti a recept panelt a kivalasztott receptet az adatokkal
            receptLista.setVisible(false);
            receptHozzaad.setVisible(false);
            recept.setVisible(true);
        } else { 
            errorMessage("Válassz egy receptet",0);
        }
        } catch(Exception e) {
            System.out.println("Ne klikkeljél, csatlakozz az adatbázishoz!");
        }
    }//GEN-LAST:event_receptTablaMouseClicked

    
    // A recept panelból vissza a receptek listájához
    private void visszaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visszaBtnActionPerformed
        receptLista.setVisible(true);
        receptHozzaad.setVisible(false);
        recept.setVisible(false);
        alapanyagokArea.setText("");
        ennyiFore.setText("");
    }//GEN-LAST:event_visszaBtnActionPerformed
    
    
    // A recept törlése
    private void torolBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_torolBtnActionPerformed
        // ha 0000 amit beirtunk akkor tudja torolni az adatbázisbol
        if(pwField.getText().equals("0000")) {
            if(db.torol(receptNev.getText())) {
                errorMessage("Sikeresen törölve: " + receptNev.getText(),0);
                receptLista.setVisible(true);
                receptHozzaad.setVisible(false);
                recept.setVisible(false);
                kilistaz("Összes");
                ennyiFore.setText("");
            }
        }
        pwField.setText("");
    }//GEN-LAST:event_torolBtnActionPerformed

    
    // Kiszámolja mennyi főre mennyi alapanyag kell
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        //hibakezelés
        if (foreField.getText().length() > 0 && isNumeric(foreField.getText())) {
            //mennyi főre
            int fore = Integer.parseInt(foreField.getText());
            if (fore > 0 && fore < 100) {
                //alapból a recept mennyi főre van
                double ennyiFo = Double.parseDouble(receptFo.getText());
                double alapbol = ennyiFo;
                alapanyagokArea.setText("");
                // a lekerítéshez, hogy ne legyen ilyen: 3.333333333333333333333333333333333333333333333333333
                DecimalFormat df = new DecimalFormat("###.#");
                
                //alapanyagok listat az adott etelhez lekerjuk
                ArrayList<Alapanyag> a = db.getEtelAlapanyag(receptNev.getText());
                // az uj mennyiséget így kiszámoljuk, és utánna kiirjuk a TextArea-ba
                for(int i = 0; i < a.size() ; i++) {
                    Alapanyag al = a.get(i);
                    double ujMennyiseg = (fore / alapbol) * al.getMennyiseg();
                    alapanyagokArea.append(al.getNev() + ": " + df.format(ujMennyiseg) + " " + al.getMertek() + "\n");
                }
                // kiirjuk a további adatait
                foreField.setText("");
                ennyiFore.setText(Integer.toString(fore) + " főre:");
                // előreugo ablak
                errorMessage(receptNev.getText()+ ", " + fore +" főre:",0);
            } else {
                errorMessage("Csak 1 és 100 között adj meg értéket!",0);
            }
        } else {
            errorMessage("Megfelően töltsd ki!",0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReceptFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReceptFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReceptFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReceptFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReceptFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alapanyagArea;
    private javax.swing.JComboBox alapanyagBox;
    private javax.swing.JButton alapanyagHozzaad;
    private javax.swing.JTextField alapanyagNevField;
    private javax.swing.JButton alapanyagSzerkeszt;
    private javax.swing.JTextArea alapanyagokArea;
    private javax.swing.JTextArea elkeszitesField;
    private javax.swing.JLabel ennyiFore;
    private javax.swing.JTextField foField;
    private javax.swing.JTextField foreField;
    private javax.swing.JTextField idoField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton listazBtn;
    private javax.swing.JTextField mennyisegField;
    private javax.swing.JComboBox mertekBox;
    private javax.swing.JTextField nevField;
    private javax.swing.JPasswordField pwField;
    private javax.swing.JPanel recept;
    private javax.swing.JButton receptAdd;
    private javax.swing.JTextArea receptElkeszitesArea;
    private javax.swing.JLabel receptFo;
    private javax.swing.JPanel receptHozzaad;
    private javax.swing.JButton receptHozzaadBtn;
    private javax.swing.JLabel receptIdo;
    private javax.swing.JPanel receptLista;
    private javax.swing.JButton receptListaBtn;
    private javax.swing.JLabel receptNev;
    private javax.swing.JTable receptTabla;
    private javax.swing.JComboBox receptTipusBox;
    private javax.swing.JComboBox tipusBox;
    private javax.swing.JButton torolBtn;
    private javax.swing.JButton visszaBtn;
    // End of variables declaration//GEN-END:variables
}
