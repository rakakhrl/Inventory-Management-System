/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author rakakhrl
 */
public class TransactionController {
    public ResultSet fetchAll() {
        ResultSet result;
        Connection conn = DatabaseConnection.initConnection();
        
        String sqlQuery = "SELECT * FROM transaction";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            
            result = ps.executeQuery();
            
            return result;
            
        } catch (SQLException err) {
            System.out.println("SQLError: " + err.getMessage());
            
            return null;
        }
    }
    
    public ResultSet fetchOne(String kodeTransaksi) {
        ResultSet result;
        Connection conn = DatabaseConnection.initConnection();
        
        String sqlQuery = "SELECT * FROM transaction WHERE kode_transaksi=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, kodeTransaksi);
            
            result = ps.executeQuery();
            
            return result;
            
        } catch (SQLException err) {
            System.out.println("SQLError: " + err.getMessage());
            
            return null;
        }
    }
    
    public ResultSet fetchByStatus(String statusTransaksi) {
        ResultSet result;
        Connection conn = DatabaseConnection.initConnection();
        
        String sqlQuery = "SELECT * FROM transaction WHERE status_transaksi=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, statusTransaksi);
            
            result = ps.executeQuery();
            
            return result;
            
        } catch (SQLException err) {
            System.out.println("SQLError: " + err.getMessage());
            
            return null;
        }
    }
    
    public ResultSet fetchBySupplierCode(String kodeSupplier) {
        ResultSet result;
        Connection conn = DatabaseConnection.initConnection();
        
        String sqlQuery = "SELECT * FROM transaction WHERE kode_supplier=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, kodeSupplier);
            
            result = ps.executeQuery();
            
            return result;
            
        } catch (SQLException err) {
            System.out.println("SQLError: " + err.getMessage());
            
            return null;
        }
    }
    
    public ResultSet fetchByCustomerCode(String kodeCustomer) {
        ResultSet result;
        Connection conn = DatabaseConnection.initConnection();
        
        String sqlQuery = "SELECT * FROM transaction WHERE kode_customer=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, kodeCustomer);
            
            result = ps.executeQuery();
            
            return result;
            
        } catch (SQLException err) {
            System.out.println("SQLError: " + err.getMessage());
            
            return null;
        }
    }
    
    public HashMap<String, String> inputTransaksi(
        String kodeBrg,
        int jumlahBrg,
        float hargaTotal,
        String kodeSupplier,
        String kodeCustomer,
        String tanggalTransaksi,
        String kodeDriver,
        String nopol,
        String statusTransaksi
    ) {
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> result = new HashMap<String, String> ();
        
        String sqlQuery = "INSERT INTO transaction (kode_transaksi, kode_barang, jumlah_barang, harga_total, kode_supplier, kode_customer, tanggal_transaksi, kode_driver, nopol_kendaraan, status_transaksi) VALUES (?, ?, ?, ?, ? ,? ,? ,? ,? ,?)";
    
        String kodeTransaksi = "TRC" + tanggalTransaksi.replace("-", "") + "-" + kodeSupplier != null ? kodeSupplier : kodeCustomer + kodeBrg;
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, kodeTransaksi);
            ps.setString(2, kodeBrg);
            ps.setInt(3, jumlahBrg);
            ps.setFloat(4, hargaTotal);
            ps.setString(5, kodeSupplier);
            ps.setString(6, kodeCustomer);
            ps.setString(7, tanggalTransaksi);
            ps.setString(8, kodeDriver);
            ps.setString(9, nopol);
            ps.setString(10, statusTransaksi);
            
            int response = ps.executeUpdate();
            
            if (response == 1) {
                result.put("code", "OPERATION_SUCCESS");
                result.put("message", "Transaksi berhasil di masukan!");
            } else {
                result.put("code", "OPERATION_FAILED");
                result.put("message", "Terjadi kesalahan, transaksi gagal di masukan!");
            }
            
            return result;
            
            
        } catch (SQLException err) {
            result.put("code", "SQL_ERROR");
            result.put("message", err.getMessage());
            
            return result;
        }
    
    }
    
    public HashMap<String, String> changeStatus(String kodeTransaksi, String statusTransaksi) {
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> result = new HashMap<String, String> ();
        
        String sqlQuery = "UPDATE transaction SET status_transaksi=? WHERE kode_transaksi=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, statusTransaksi);
            ps.setString(2, kodeTransaksi);
            
            int response = ps.executeUpdate();
            
            if (response == 1) {
                result.put("code", "OPERATION_SUCCESS");
                result.put("message", "Status transaksi berhasil di update!");
            } else {
                result.put("code", "OPERATION_FAILED");
                result.put("message", "Terjadi kesalahan, status transaksi gagal di update!");
            }
            
            return result;
            
        } catch (SQLException err) {
            result.put("code", "SQL_ERROR");
            result.put("message", err.getMessage());
            
            return result;
        }
        
    }
    
}
