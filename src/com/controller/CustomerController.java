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
public class CustomerController {
    public ResultSet fetchAll() {
        Connection conn = DatabaseConnection.initConnection();
        ResultSet result;
        
        String sqlQuery = "SELECT * FROM customer";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            
            result = ps.executeQuery();
            
            return result;
            
        } catch (SQLException err) {
            System.out.println("SQLError: " + err.getMessage());
            
            return null;
        }
    }
    
    public ResultSet fetchOne(String kodeCustomer) {
        Connection conn = DatabaseConnection.initConnection();
        ResultSet result;
        
        String sqlQuery = "SELECT * FROM customer WHERE kode_customer=?";
        
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
    
    public HashMap<String, String> addCustomer(String kodeCustomer, String namaCustomer, String alamat) {
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> result = new HashMap<String, String>();
        
        String sqlQuery = "INSERT INTO supplier (kode_customer, nama_customer, alamat) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, kodeCustomer);
            ps.setString(2, namaCustomer);
            ps.setString(3, alamat);
            
            int response = ps.executeUpdate();
            
            if (response == 1) {
                result.put("code", "OPERATION_SUCCESS");
                result.put("message", "Customer berhasil ditambahkan!");
            } else {
                result.put("code", "OPERATION_FAILED");
                result.put("message", "Terjadi kesalahan, customer gagal di tambahkan!");
            }
            
            return result;
            
        } catch (SQLException err) {
            result.put("code", "SQL_ERROR");
            result.put("message", err.getMessage());
            
            return result;
        }
    }
    
    public HashMap<String, String> deleteCustomer(String kodeCustomer) {
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> result = new HashMap<String, String>();
        
        String sqlQuery = "DELETE FROM customer WHERE kode_customer=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, kodeCustomer);
            
            int response = ps.executeUpdate();
            
            if (response == 1) {
                result.put("code", "OPERATION_SUCCESS");
                result.put("message", "Customer berhasil dihapus!");
            } else {
                result.put("code", "OPERATION_FAILED");
                result.put("message", "Terjadi kesalahan, customer gagal dihapus!");
            }
            
            return result;
            
        } catch (SQLException err) {
            result.put("code", "SQL_ERROR");
            result.put("message", err.getMessage());
            
            return result;
        }
    }
    
    public HashMap<String, String> editCustomer(String kodeCustomer, String namaCustomer, String alamat) {
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> result = new HashMap<String, String>();
        
        String sqlQuery = "UPDATE customer SET nama_customer=?, alamat=? WHERE kode_customer=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, namaCustomer);
            ps.setString(2, alamat);
            ps.setString(3, kodeCustomer);
            
            int response = ps.executeUpdate();
            
            if (response == 1) {
                result.put("code", "OPERATION_SUCCESS");
                result.put("message", "Customer berhasil diedit!");
            } else {
                result.put("code", "OPERATION_FAILED");
                result.put("message", "Terjadi kesalahan, customer gagal diedit!");
            }
            
            return result;
            
        } catch (SQLException err) {
            result.put("code", "SQL_ERROR");
            result.put("message", err.getMessage());
            
            return result;
        }   
    }
}
