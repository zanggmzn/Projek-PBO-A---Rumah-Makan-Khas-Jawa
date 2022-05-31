/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asus
 */

public class viewPesan extends JFrame {
    public JLabel lCustomer, lOrderan, lJumlah,lKelola;
    public JTextField tfCustomer, tfOrderan, tfJumlah, tfSearch ;
    public JButton bRefresh, bCreate, bUpdate, bDelete, bExit, bSearch;
    public JTable tabel;
    public DefaultTableModel tableModel;
    public JScrollPane scrollPane;
    public Object namaKolom[] = {"ID", "Customer", "Orderan", "Jumlah"};
    public JLabel lHeading = new JLabel("Daftar Pesan Pelanggan");
    
    public viewPesan() {
        setTitle("Daftar Pesanan Pelanggan");
        tableModel = new DefaultTableModel(namaKolom, 0);
        tabel = new JTable(tableModel);

        lCustomer = new JLabel("Nama Customer:");
        lOrderan = new JLabel("Orderan:");  
        lJumlah = new JLabel("Jumlah:");
        lKelola = new JLabel ("Kelola Data");

        tfCustomer = new JTextField();
        tfOrderan = new JTextField();
        tfJumlah = new JTextField();
        tfSearch = new JTextField();

        bRefresh = new JButton("Refresh");
        bCreate = new JButton("Tambah");
        bUpdate = new JButton("Update");
        bDelete = new JButton("Hapus");
        bExit = new JButton("Keluar");
        bSearch = new JButton("Cari");
        
        setLayout(null);
        
        add(lHeading);
        add(lCustomer);
        add(tfCustomer);
        add(lOrderan);
        add(tfOrderan);
        add(lJumlah);
        add(tfJumlah);
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
        scrollPane.setBounds(40, 100, 500, 210);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        lHeading.setBounds(400,10,200,20);
        lKelola.setBounds(735, 110, 100, 30);
        lCustomer.setBounds(610, 160, 100, 30);
        tfCustomer.setBounds(810, 160, 150, 25);
        lOrderan.setBounds(610, 200, 100, 30);
        tfOrderan.setBounds(810, 200, 150, 25);
        lJumlah.setBounds(610, 240, 100, 30);
        tfJumlah.setBounds(810, 240, 150, 25);

        tfSearch.setBounds(387, 60, 150, 25);
        bSearch.setBounds(570, 60, 70, 20);

        bRefresh.setBounds(40, 60, 90, 20);
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
    
    public String getCustomer() {
        return tfCustomer.getText();
    }

    public String getOrderan() {
        return tfOrderan.getText();
    }

    public int getJumlah() {
        return (Integer.parseInt(tfJumlah.getText()));
    }

    public String getSearch() {
        return tfSearch.getText();
    }
    
}
