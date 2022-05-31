/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import javax.swing.*;
import java.sql.*;
/**
 *
 * @author asus
 */
public class modelPesan {
    Connection koneksi;
    Statement statement;

    public modelPesan() {
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
    
    
    public void insert(modelPesan modelP) {
        try {
            String query = "INSERT INTO tb_order VALUES (null, '" + modelP.getCustomer() + "', '" + modelP.getOrderan() + "', '" + modelP.getJumlah() + "')";
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
            String data[][] = new String[getJmlData()][5];
            String query = "SELECT * FROM tb_order";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data[jmlData][0] = resultSet.getString("id");
                data[jmlData][1] = resultSet.getString("customer");
                data[jmlData][2] = resultSet.getString("orderan");
                data[jmlData][3] = resultSet.getString("jumlah");
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
            String query = "SELECT  * FROM tb_order"; //alias buat ngecek beneran ada pa ga
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
    
    public void update(int dataTerpilih, String customer, String orderan , int jumlah) {//parameter bebas tapi sesuaikan
        try {
            String query = "UPDATE tb_order SET customer = '" + customer + "',orderan = '" + orderan + "', jumlah = '" + jumlah + "' WHERE id = " + dataTerpilih + "";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Diupdate");
        } catch (Exception sql) {
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
    
    public void delete(int id) {
        try {
            String query = "DELETE FROM tb_order WHERE id = " + id + "";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        } catch (SQLException sql) {
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
    
    public String[][] search(modelPesan modelP) {
        try {
            int jmlData = 0;//ini baru fungsi search sebenarnya
            String datas[][] = new String[getJmlDataSearch(modelP)][5];//didapat dari jumlah data yg di method getsearch
            String query = "SELECT * FROM tb_order WHERE customer LIKE '%" + modelP.getSearch() + "%'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {//ini belum dalam bentuk tabel karena tabel akan ditampilin di controller
                datas[jmlData][0] = resultSet.getString("id");
                datas[jmlData][1] = resultSet.getString("customer");
                datas[jmlData][2] = resultSet.getString("orderan");
                datas[jmlData][3] = resultSet.getString("jumlah");
                jmlData++;
            }
            return datas;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
    public int getJmlDataSearch(modelPesan modelP) {
        int jmlData = 0;//ini fungsine buat cek bener gak ada datanya terus ada berapa
        try {
            String query = "SELECT * FROM tb_order WHERE customer LIKE '%" + modelP.getSearch() + "%'";
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
    
    public String customer,search,orderan;
    public int jumlah;

    public void setModelP(String customer, String orderan, int jumlah) {
        this.customer = customer;
        this.orderan = orderan;
        this.jumlah = jumlah;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrderan() {
        return orderan;
    }

    public void setOrderan(String orderan) {
        this.orderan = orderan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    
    
}
