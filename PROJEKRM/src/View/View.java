/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author Aluito Aryo Prabowo
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class View extends JFrame {
    public JLabel lNama, lHarga, lStok, lMenu, lKelola;
    public JTextField tfNama, tfHarga, tfStok, tfSearch ;
    public JButton bRefresh, bCreate, bUpdate, bDelete, bExit, bSearch, bPesan;
    public JTable tabel;
    public DefaultTableModel tableModel;
    public JScrollPane scrollPane;
    public Object namaKolom[] = {"Nomor", "ID", "Nama", "Harga", "Stok"};
    public JLabel lmenu = new JLabel("Menu");
    public JLabel lHeading = new JLabel("RUMAH MAKAN KHAS JAWA");

    public View() {
        setTitle("RUMAH MAKAN KHAS JAWA");
        tableModel = new DefaultTableModel(namaKolom, 0);
        tabel = new JTable(tableModel);
        
        lMenu = new JLabel("Menu");
        lNama = new JLabel("Nama Menu:");
        lHarga = new JLabel("Harga:");  
        lStok = new JLabel("Stok:");
        lKelola = new JLabel ("Kelola Data");

        tfNama = new JTextField();
        tfHarga = new JTextField();
        tfStok = new JTextField();
        tfSearch = new JTextField();

        bRefresh = new JButton("Refresh");
        bCreate = new JButton("Tambah");
        bUpdate = new JButton("Update");
        bDelete = new JButton("Hapus");
        bExit = new JButton("Keluar");
        bSearch = new JButton("Cari");
        bPesan = new JButton("Pesan");
        
        setLayout(null);
        
        add(lHeading);
        add(lNama);
        add(tfNama);
        add(lHarga);
        add(lMenu);
        add(tfHarga);
        add(lStok);
        add(tfStok);
        add(tfSearch);
        add(lKelola);

        add(bSearch);
        add(bRefresh);
        add(bCreate);
        add(bUpdate);
        add(bDelete);
        add(bExit);
        
        scrollPane = new JScrollPane(tabel);
        add(scrollPane);
        lMenu.setBounds(40, 60, 90, 20);
        scrollPane.setBounds(40, 100, 500, 210);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        add(bPesan);
        bPesan.setBounds(860, 400, 90, 30);

        lHeading.setBounds(400,10,200,20);
        lKelola.setBounds(735, 110, 100, 30);
        lNama.setBounds(610, 160, 100, 30);
        tfNama.setBounds(810, 160, 150, 25);
        lHarga.setBounds(610, 200, 100, 30);
        tfHarga.setBounds(810, 200, 150, 25);
        lStok.setBounds(610, 240, 100, 30);
        tfStok.setBounds(810, 240, 150, 25);

        tfSearch.setBounds(387, 60, 150, 25);
        bSearch.setBounds(570, 60, 70, 20);

        bRefresh.setBounds(80, 60, 90, 20);
        bCreate.setBounds(620, 325, 80, 20);
        bUpdate.setBounds(740, 325, 80, 20);
        bDelete.setBounds(860, 325, 80, 20);
        bExit.setBounds(40, 400, 90, 30);

        this.dispose();
        setSize(1000, 500);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);//biar muncul di tengah
    }
    
    public String getNama() {
        return tfNama.getText();
    }

    public int getHarga() {
        return (Integer.parseInt(tfHarga.getText()));
    }

    public String getStok() {
        return tfStok.getText();
    }

    public String getSearch() {
        return tfSearch.getText();
    }
    
}