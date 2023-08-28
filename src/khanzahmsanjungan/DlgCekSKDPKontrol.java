/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgAdmin.java
 *
 * Created on 04 Des 13, 12:59:34
 */
package khanzahmsanjungan;

import bridging.BPJSCekRujukanKartuPCare;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Kode
 */
public class DlgCekSKDPKontrol extends javax.swing.JDialog {

    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    private String umur = "0", sttsumur = "Th";
    private String status = "Baru", BASENOREG = "", URUTNOREG = "", aktifjadwal = "";
    private Properties prop = new Properties();
    private int lebar = 0, tinggi = 0;

    /**
     * Creates new form DlgAdmin
     *
     * @param parent
     * @param id
     */
    public DlgCekSKDPKontrol(java.awt.Frame parent, boolean id) {
        super(parent, id);
        initComponents();

        try {
            ps = koneksi.prepareStatement(
                    "select nm_pasien,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) asal,"
                    + "namakeluarga,keluarga,pasien.kd_pj,penjab.png_jawab,if(tgl_daftar=?,'Baru','Lama') as daftar, "
                    + "TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun, "
                    + "(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                    + "TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten inner join penjab "
                    + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_pj=penjab.kd_pj "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "
                    + "where pasien.no_rkm_medis=?");
        } catch (Exception ex) {
            System.out.println(ex);
        }

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            aktifjadwal = prop.getProperty("JADWALDOKTERDIREGISTRASI");
            URUTNOREG = prop.getProperty("URUTNOREG");
            BASENOREG = prop.getProperty("BASENOREG");
        } catch (Exception ex) {
            aktifjadwal = "";
            URUTNOREG = "";
            BASENOREG = "";
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LblKdPoli = new component.Label();
        LblKdDokter = new component.Label();
        NoReg = new component.TextBox();
        NoRawat = new component.TextBox();
        Biaya = new component.TextBox();
        jPanel2 = new javax.swing.JPanel();
        PanelWall = new usu.widget.glass.PanelGlass();
        jPanel1 = new component.Panel();
        NoRMPasien = new component.TextBox();
        BtnClose = new widget.ButtonBig();
        BtnClose2 = new widget.ButtonBig();
        jLabel28 = new component.Label();

        LblKdPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LblKdPoli.setText("Norm");
        LblKdPoli.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LblKdPoli.setPreferredSize(new java.awt.Dimension(20, 14));

        LblKdDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LblKdDokter.setText("Norm");
        LblKdDokter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LblKdDokter.setPreferredSize(new java.awt.Dimension(20, 14));

        NoReg.setPreferredSize(new java.awt.Dimension(320, 30));
        NoReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoRegActionPerformed(evt);
            }
        });
        NoReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRegKeyPressed(evt);
            }
        });

        NoRawat.setPreferredSize(new java.awt.Dimension(320, 30));
        NoRawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoRawatActionPerformed(evt);
            }
        });
        NoRawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRawatKeyPressed(evt);
            }
        });

        Biaya.setPreferredSize(new java.awt.Dimension(320, 30));
        Biaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BiayaActionPerformed(evt);
            }
        });
        Biaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BiayaKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.BorderLayout(1, 1));

        jPanel2.setBackground(new java.awt.Color(238, 238, 255));
        jPanel2.setForeground(new java.awt.Color(238, 238, 255));

        PanelWall.setBackground(new java.awt.Color(238, 238, 255));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/bpjs-amiz.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setForeground(new java.awt.Color(238, 238, 255));
        PanelWall.setPreferredSize(new java.awt.Dimension(500, 150));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(238, 238, 255));

        javax.swing.GroupLayout PanelWallLayout = new javax.swing.GroupLayout(PanelWall);
        PanelWall.setLayout(PanelWallLayout);
        PanelWallLayout.setHorizontalGroup(
            PanelWallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        PanelWallLayout.setVerticalGroup(
            PanelWallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        jPanel2.add(PanelWall);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(238, 238, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 215, 255)), "::[ Cek Data Kontrol BPJS!!! ]::", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Poppins", 0, 24), new java.awt.Color(0, 131, 62))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 131, 62));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 70));
        jPanel1.setLayout(null);

        NoRMPasien.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 131, 62), 2, true));
        NoRMPasien.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NoRMPasien.setFont(new java.awt.Font("Poppins", 0, 24)); // NOI18N
        NoRMPasien.setPreferredSize(new java.awt.Dimension(350, 75));
        NoRMPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoRMPasienActionPerformed(evt);
            }
        });
        NoRMPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRMPasienKeyPressed(evt);
            }
        });
        jPanel1.add(NoRMPasien);
        NoRMPasien.setBounds(260, 150, 350, 75);

        BtnClose.setBackground(new java.awt.Color(255, 255, 255));
        BtnClose.setForeground(new java.awt.Color(51, 51, 51));
        BtnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/exit.png"))); // NOI18N
        BtnClose.setMnemonic('U');
        BtnClose.setToolTipText("Alt+U");
        BtnClose.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BtnClose.setHorizontalTextPosition(javax.swing.SwingConstants.TRAILING);
        BtnClose.setIconTextGap(2);
        BtnClose.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnClose.setPreferredSize(new java.awt.Dimension(100, 75));
        BtnClose.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseActionPerformed(evt);
            }
        });
        jPanel1.add(BtnClose);
        BtnClose.setBounds(710, 150, 100, 75);

        BtnClose2.setBackground(new java.awt.Color(255, 255, 255));
        BtnClose2.setForeground(new java.awt.Color(51, 51, 51));
        BtnClose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/konfirmasi.png"))); // NOI18N
        BtnClose2.setMnemonic('U');
        BtnClose2.setToolTipText("Alt+U");
        BtnClose2.setFont(new java.awt.Font("Poppins", 1, 11)); // NOI18N
        BtnClose2.setIconTextGap(0);
        BtnClose2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnClose2.setPreferredSize(new java.awt.Dimension(100, 75));
        BtnClose2.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnClose2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnClose2ActionPerformed(evt);
            }
        });
        jPanel1.add(BtnClose2);
        BtnClose2.setBounds(610, 150, 100, 75);

        jLabel28.setForeground(new java.awt.Color(0, 131, 62));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("NO SURAT KONTROL");
        jLabel28.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(600, 75));
        jPanel1.add(jLabel28);
        jLabel28.setBounds(220, 70, 600, 75);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void NoRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoRegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRegActionPerformed

    private void NoRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRegKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRegKeyPressed

    private void NoRawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoRawatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRawatActionPerformed

    private void NoRawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRawatKeyPressed

    private void BiayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BiayaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BiayaActionPerformed

    private void BiayaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BiayaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BiayaKeyPressed

    private void NoRMPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoRMPasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRMPasienActionPerformed

    private void NoRMPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRMPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Sequel.cariInteger("select count(bridging_surat_kontrol_bpjs.no_surat) from bridging_surat_kontrol_bpjs where bridging_surat_kontrol_bpjs.no_surat='" + NoRMPasien.getText() + "'") > 0) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgRegistrasiSEPPertama form = new DlgRegistrasiSEPPertama(null, true);
                form.tampilKontrol(NoRMPasien.getText());
                form.setSize(this.getWidth(), this.getHeight());
                form.setLocationRelativeTo(jPanel1);
                this.dispose();
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Data surat kontrol tidak ditemukan!");
            }
        }

    }//GEN-LAST:event_NoRMPasienKeyPressed

    private void BtnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseActionPerformed

        dispose();
    }//GEN-LAST:event_BtnCloseActionPerformed

    private void BtnClose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnClose2ActionPerformed

        if (Sequel.cariInteger("select count(bridging_surat_kontrol_bpjs.no_surat) from bridging_surat_kontrol_bpjs where bridging_surat_kontrol_bpjs.no_surat='" + NoRMPasien.getText() + "'") > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgRegistrasiSEPPertama form = new DlgRegistrasiSEPPertama(null, true);
            form.tampilKontrol(NoRMPasien.getText());
            form.setSize(this.getWidth(), this.getHeight());
            form.setLocationRelativeTo(jPanel1);
            this.dispose();
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Data surat kontrol tidak ditemukan!");
        }

    }//GEN-LAST:event_BtnClose2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCekSKDPKontrol dialog = new DlgCekSKDPKontrol(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.TextBox Biaya;
    private widget.ButtonBig BtnClose;
    private widget.ButtonBig BtnClose2;
    private component.Label LblKdDokter;
    private component.Label LblKdPoli;
    private component.TextBox NoRMPasien;
    private component.TextBox NoRawat;
    private component.TextBox NoReg;
    private usu.widget.glass.PanelGlass PanelWall;
    private component.Label jLabel28;
    private component.Panel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    public void setPasien(String norm, String kodepoli, String kddokter) {
    }

    private void UpdateUmur() {

    }

    private void isNumber() {
    }
}
