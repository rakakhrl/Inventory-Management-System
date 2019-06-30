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
public class DriverController {
    public HashMap<String, String> addDriver(String kodeDriver, String namaDriver) {
        int result;
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> response = new HashMap<String, String>();
        
        String sqlQuery = "INSERT INTO driver (kode_driver, nama_driver) VALUES (?, ?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, kodeDriver);
            ps.setString(2, namaDriver);
            
            result = ps.executeUpdate();
            
            if (result == 1) {
                response.put("code", "OPERATION_SUCCESS");
                response.put("message", "Driver berhasil di tambahkan!");
            } else {
                response.put("code", "OPERATION_FAILED");
                response.put("message", "Driver gagal di tambahkan!");
            }
            
            return response;
            
        } catch (SQLException err) {
            response.put("code", "SQL_ERROR");
            response.put("message", err.getMessage());
            
            return response;
        }
        
    }
    
    public HashMap<String, String> hapusDriver(String kodeDriver) {
        int result;
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> response = new HashMap<String, String>();
        
        String sqlQuery = "DELETE FROM inventory WHERE kode_barang=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, kodeDriver);
            
            result = ps.executeUpdate();
            
            if (result == 1) {
                response.put("code", "OPERATION_SUCCESS");
                response.put("message", "Driver berhasil di hapuskan!");
            } else {
                response.put("code", "OPERATION_FAILED");
                response.put("message", "Driver gagal di hapuskan!");
            }
            
            return response;
            
        } catch (SQLException err) {
            response.put("code", "SQL_ERROR");
            response.put("message", err.getMessage());
            
            return response;
        } 
    }
    
    public HashMap<String, String> editDriver(String kodeDriver, String namaDriver) {
        int result;
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> response = new HashMap<String, String>();
        
        String sqlQuery = "UPDATE driver SET nama_driver=? WHERE kode_driver=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, namaDriver);
            ps.setString(2, kodeDriver);
            
            result = ps.executeUpdate();
            
            if (result == 1) {
                response.put("code", "OPERATION_SUCCESS");
                response.put("message", "Driver berhasil di update!");
            } else {
                response.put("code", "OPERATION_FAILED");
                response.put("message", "Driver gagal di update!");
            }
            
            return response;
            
        } catch (SQLException err) {
            response.put("code", "SQL_ERROR");
            response.put("message", err.getMessage());
            
            return response;
        }
        
    }
    
    public ResultSet fetchAll() {
        ResultSet result;
        Connection conn = DatabaseConnection.initConnection();
        
        String sqlQuery = "SELECT * FROM driver";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            
            result = ps.executeQuery();
            
            return result;
        } catch (SQLException err) {
            System.out.println("SQLError: " + err.getMessage());
            
            return null;
        }
        
    }
    
    public ResultSet fetchOne(String kodeDriver) {
        ResultSet result;
        Connection conn = DatabaseConnection.initConnection();
        
        String sqlQuery = "SELECT * FROM driver WHERE kode_driver=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            
            result = ps.executeQuery();
            
            return result;
        } catch (SQLException err) {
            System.out.println("SQLError: " + err.getMessage());
            
            return null;
        }
        
    }
    
}
