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
public class SupplierController {
    public ResultSet fetchAll() {
        Connection conn = DatabaseConnection.initConnection();
        ResultSet result;
        
        String sqlQuery = "SELECT * FROM supplier";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            
            result = ps.executeQuery();
            
            return result;
            
        } catch (SQLException err) {
            System.out.println("SQLError: " + err.getMessage());
            
            return null;
        }
    }
    
    public ResultSet fetchOne(String kodeSupplier) {
        Connection conn = DatabaseConnection.initConnection();
        ResultSet result;
        
        String sqlQuery = "SELECT * FROM supplier WHERE kode_supplier=?";
        
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
    
    public HashMap<String, String> addSupplier(String kodeSupplier, String namaSupplier, String alamat) {
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> result = new HashMap<String, String>();
        
        String sqlQuery = "INSERT INTO supplier (kode_supplier, nama_supplier, alamat) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, kodeSupplier);
            ps.setString(2, namaSupplier);
            ps.setString(3, alamat);
            
            int response = ps.executeUpdate();
            
            if (response == 1) {
                result.put("code", "OPERATION_SUCCESS");
                result.put("message", "Supplier berhasil ditambahkan!");
            } else {
                result.put("code", "OPERATION_FAILED");
                result.put("message", "Terjadi kesalahan, supplier gagal di tambahkan!");
            }
            
            return result;
            
        } catch (SQLException err) {
            result.put("code", "SQL_ERROR");
            result.put("message", err.getMessage());
            
            return result;
        }
    }
    
    public HashMap<String, String> deleteSupplier(String kodeSupplier) {
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> result = new HashMap<String, String>();
        
        String sqlQuery = "DELETE FROM supplier WHERE kode_supplier=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, kodeSupplier);
            
            int response = ps.executeUpdate();
            
            if (response == 1) {
                result.put("code", "OPERATION_SUCCESS");
                result.put("message", "Supplier berhasil dihapus!");
            } else {
                result.put("code", "OPERATION_FAILED");
                result.put("message", "Terjadi kesalahan, supplier gagal dihapus!");
            }
            
            return result;
            
        } catch (SQLException err) {
            result.put("code", "SQL_ERROR");
            result.put("message", err.getMessage());
            
            return result;
        }
    }
    
    public HashMap<String, String> editSupplier(String kodeSupplier, String namaSupplier, String alamat) {
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> result = new HashMap<String, String>();
        
        String sqlQuery = "UPDATE supplier SET nama_supplier=?, alamat=? WHERE kode_supplier=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, namaSupplier);
            ps.setString(2, alamat);
            ps.setString(3, kodeSupplier);
            
            int response = ps.executeUpdate();
            
            if (response == 1) {
                result.put("code", "OPERATION_SUCCESS");
                result.put("message", "Supplier berhasil diedit!");
            } else {
                result.put("code", "OPERATION_FAILED");
                result.put("message", "Terjadi kesalahan, supplier gagal diedit!");
            }
            
            return result;
            
        } catch (SQLException err) {
            result.put("code", "SQL_ERROR");
            result.put("message", err.getMessage());
            
            return result;
        }   
    }
    
}
