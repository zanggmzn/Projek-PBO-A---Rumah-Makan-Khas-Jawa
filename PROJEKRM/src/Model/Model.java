/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
/**
 *
 * @author Aluito Aryo Prabowo
 */
import javax.swing.*;
import java.sql.*;

public class Model {
    Connection koneksi;
    Statement statement;

    public Model() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/prakyo";
            koneksi = DriverManager.getConnection(url, "root", "");
            statement = koneksi.createStatement();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Class Not found : " + ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception : " + ex);
        }
    }

    public void insert(Model model) {
        try {
            String query = "INSERT INTO tb_data VALUES (null, '" + model.getNama() + "', '" + model.getHarga() + "', '" + model.getStok() + "')";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        } catch (Exception sql) {
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
    
    public String[][] read() {
        try {
            int jmlData = 0;//kalo ada akan kepake disini
            int nomor = 1;
            String data[][] = new String[getJmlData()][5];
            String query = "SELECT * FROM tb_data";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data[jmlData][0] = Integer.toString(nomor);
                data[jmlData][1] = resultSet.getString("id");
                data[jmlData][2] = resultSet.getString("nama");
                data[jmlData][3] = resultSet.getString("harga");
                data[jmlData][4] = resultSet.getString("stok");
                nomor++;
                jmlData++;
            }
            return data;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
    public int getJmlData() {
        int jmlData = 0;//fungsinya sama kayak getjml data search
        try {
            String query = "SELECT  * FROM tb_data"; //alias buat ngecek beneran ada pa ga
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                jmlData++;
            }
            return jmlData;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return 0;
        }
    }
     
    public void update(int dataTerpilih, String nama, int harga, String stok) {//parameter bebas tapi sesuaikan
        try {
            String query = "UPDATE tb_data SET nama = '" + nama + "',harga = '" + harga + "', stok = '" + stok + "' WHERE id = " + dataTerpilih + "";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Diupdate");
        } catch (Exception sql) {
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }

    public void delete(int id) {
        try {
            String query = "DELETE FROM tb_data WHERE id = " + id + "";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        } catch (SQLException sql) {
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }

    public String[][] search(Model model) {
        try {
            int jmlData = 0;//ini baru fungsi search sebenarnya
            int nomor = 1;
            String datas[][] = new String[getJmlDataSearch(model)][5];//didapat dari jumlah data yg di method getsearch
            String query = "SELECT * FROM tb_data WHERE nama LIKE '%" + model.getSearch() + "%'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {//ini belum dalam bentuk tabel karena tabel akan ditampilin di controller
                datas[jmlData][0] = Integer.toString(nomor);//baru bentuk data
                datas[jmlData][1] = resultSet.getString("id");
                datas[jmlData][2] = resultSet.getString("nama");
                datas[jmlData][3] = resultSet.getString("harga");
                datas[jmlData][4] = resultSet.getString("stok");
                nomor++;
                jmlData++;
            }
            return datas;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
    public int getJmlDataSearch(Model model) {
        int jmlData = 0;//ini fungsine buat cek bener gak ada datanya terus ada berapa
        try {
            String query = "SELECT * FROM tb_data WHERE nama LIKE '%" + model.getSearch() + "%'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {//kalo ada bener
                jmlData++;//jumlah data akan terus bertambah
            }
            return jmlData;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return 0;
        }
    }
    
    
    public String nama,search,stok;
    public int harga;

    public void setModel(String nama, int harga, String stok) {//ini buat set saat di create atau insert
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }
    
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

}