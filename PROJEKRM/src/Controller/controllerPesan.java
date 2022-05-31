 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import View.viewPesan;
import Model.modelPesan;
import View.View;
/**
 *
 * @author asus
 */
public class controllerPesan {
    modelPesan modelP;
    viewPesan viewP;
    
    public controllerPesan(modelPesan modelP, viewPesan viewP) {
        this.modelP = modelP;
        this.viewP = viewP;
    
        if (modelP.getJmlData() != 0) {
            String datas[][] = modelP.read();//var model ngehubungin ke model yg read
            viewP.tabel.setModel((new JTable(datas, viewP.namaKolom)).getModel());//baru ditampilin oleh view.setmodel
        } else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }

        viewP.tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int pilih = viewP.tabel.getSelectedRow();//ini murni pake view
                if (pilih == -1) {
                    return;
                }
                //settext di text field
                String customer = (String) viewP.tabel.getValueAt(pilih, 1);//kan kolom kedua mulainya (0 1 2 3 4)
                viewP.tfCustomer.setText(customer);
                String orderan = (String) viewP.tabel.getValueAt(pilih, 2);
                viewP.tfOrderan.setText(orderan);
                String jumlah = (String) viewP.tabel.getValueAt(pilih, 3);
                viewP.tfJumlah.setText(jumlah);
            }
        });
        
        viewP.bSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = viewP.getSearch();//view ambil yg di box
                modelP.setSearch(search);//model ngasih set isi ke search
                String datas[][] = modelP.search(modelP);
                viewP.tabel.setModel((new JTable(datas, viewP.namaKolom)).getModel());//setModel dicontroller adl deklar dr java JTable
            }
        });
        
        viewP.bRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datas[][] = modelP.read();//model baca
                viewP.tfCustomer.setText("");//view ngeread
                viewP.tfOrderan.setText("");
                viewP.tfJumlah.setText("");
                viewP.tabel.setModel((new JTable(datas, viewP.namaKolom)).getModel());
            }
        });

        viewP.bCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String customer = viewP.getCustomer();//diambil sama view dulu semuana
                    String orderan = viewP.getOrderan();
                    Integer jumlah = viewP.getJumlah();
                    modelP.setModelP(customer, orderan, jumlah);//buat execute insert tambah tabel sama si var model
                    modelP.insert(modelP); //diinsert di model
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Harap isi semua field");
                }
            }
        });

        viewP.bUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int baris = viewP.tabel.getSelectedRow();//view ambil nilai baris yg diambil
                if (baris >= 0) {//kalo emang ada data
                    String dataTerpilihX = viewP.tabel.getValueAt(baris, 0).toString();//data terpilihX ini akan berisi baris nua
                    int dataTerpilih = Integer.parseInt(dataTerpilihX);//dataterpiihX di konversi dulu baru disimpan di var data terpilih
                    int input = JOptionPane.showConfirmDialog(null, "Apa anda ingin UPDATE ID : " + dataTerpilih + "?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);
                    if (input == 0) {
                        String customer = viewP.getCustomer();
                        String orderan = viewP.getOrderan();
                        int jumlah = viewP.getJumlah();
                        modelP.update(dataTerpilih, customer, orderan, jumlah);//parameter sama kek yg di model
                    } else {
                        JOptionPane.showMessageDialog(null, "Tidak Jadi Diupdate");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih data yang akan diupdate dahulu");
                }
            }
        });

        viewP.bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int baris = viewP.tabel.getSelectedRow();
                if (baris >= 0) {
                    String dataTerpilihX = viewP.tabel.getValueAt(baris, 0).toString();//jadi fungsi data terpilihX buat ambil baris
                    int dataTerpilih = Integer.parseInt(dataTerpilihX);//fungsi datatepilih buat nampung yg udah di parse
                    int input = JOptionPane.showConfirmDialog(null, "Apa anda ingin menghapus ID : " + dataTerpilih + "?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);
                    if (input == 0) {
                        modelP.delete(dataTerpilih);
                    } else {
                        JOptionPane.showMessageDialog(null, "Tidak Jadi Dihapus");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih data yang akan didelete dahulu");
                }
            }
        });
        viewP.bExit.addActionListener(new ActionListener() {
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
