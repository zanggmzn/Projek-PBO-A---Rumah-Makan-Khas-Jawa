/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
/**
 *
 * @author Aluito Aryo Prabowo
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Model.Model;
import View.View;
import View.viewPesan;
import Model.modelPesan;
import Controller.controllerPesan;

public class Controller {
    Model model;
    View view;

    public Controller(Model model, View view) { //deklar nya ya sama kek yg main
        this.model = model;
        this.view = view;

        if (model.getJmlData() != 0) {
            String datas[][] = model.read();//var model ngehubungin ke model yg read
            view.tabel.setModel((new JTable(datas, view.namaKolom)).getModel());//baru ditampilin oleh view.setmodel
        } else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }

        view.tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int pilih = view.tabel.getSelectedRow();//ini murni pake view
                if (pilih == -1) {
                    return;
                }
                //settext di text field
                String nama = (String) view.tabel.getValueAt(pilih, 2);//kan kolom kedua mulainya (0 1 2 3 4)
                view.tfNama.setText(nama);
                String harga = (String) view.tabel.getValueAt(pilih, 3);
                view.tfHarga.setText(harga);
                String stok = (String) view.tabel.getValueAt(pilih, 4);
                view.tfStok.setText(stok);
            }
        });
        
        view.bSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = view.getSearch();//view ambil yg di box
                model.setSearch(search);//model ngasih set isi ke search
                String datas[][] = model.search(model);
                view.tabel.setModel((new JTable(datas, view.namaKolom)).getModel());//setModel dicontroller adl deklar dr java JTable
            }
        });
        
        view.bPesan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                viewPesan viewP = new viewPesan();
                modelPesan modelP = new modelPesan();
                controllerPesan controlP = new controllerPesan (modelP, viewP);//controller yg awal parameter sama yg di controller awal juga
            }
        });
        
        view.bRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datas[][] = model.read();//model baca
                view.tfNama.setText("");//view ngeread
                view.tfHarga.setText("");
                view.tfStok.setText("");
                view.tabel.setModel((new JTable(datas, view.namaKolom)).getModel());
            }
        });

        view.bCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nama = view.getNama();//diambil sama view dulu semuana
                    Integer harga = view.getHarga();
                    String stok = view.getStok();
                    model.setModel(nama, harga, stok );//buat execute insert tambah tabel sama si var model
                    model.insert(model); //diinsert di model
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Harap isi semua field");
                }
            }
        });

        view.bUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int baris = view.tabel.getSelectedRow();//view ambil nilai baris yg diambil
                if (baris >= 0) {//kalo emang ada data
                    String dataTerpilihX = view.tabel.getValueAt(baris, 1).toString();//data terpilihX ini akan berisi baris nua
                    int dataTerpilih = Integer.parseInt(dataTerpilihX);//dataterpiihX di konversi dulu baru disimpan di var data terpilih
                    int input = JOptionPane.showConfirmDialog(null, "Apa anda ingin UPDATE ID : " + dataTerpilih + "?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);
                    if (input == 0) {
                        String nama = view.getNama();
                        int harga = view.getHarga();
                        String stok = view.getStok();
                        model.update(dataTerpilih, nama, harga, stok);//parameter sama kek yg di model
                    } else {
                        JOptionPane.showMessageDialog(null, "Tidak Jadi Diupdate");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih data yang akan diupdate dahulu");
                }
            }
        });

        view.bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int baris = view.tabel.getSelectedRow();
                if (baris >= 0) {
                    String dataTerpilihX = view.tabel.getValueAt(baris, 1).toString();//jadi fungsi data terpilihX buat ambil baris
                    int dataTerpilih = Integer.parseInt(dataTerpilihX);//fungsi datatepilih buat nampung yg udah di parse
                    int input = JOptionPane.showConfirmDialog(null, "Apa anda ingin menghapus ID : " + dataTerpilih + "?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);
                    if (input == 0) {
                        model.delete(dataTerpilih);
                    } else {
                        JOptionPane.showMessageDialog(null, "Tidak Jadi Dihapus");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih data yang akan didelete dahulu");
                }
            }
        });

        view.bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = JOptionPane.showConfirmDialog(null, "Apa anda yakin ingin keluar?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);
                if (input == 0) {
                    System.exit(0);
                }
            }
        });
    }
}