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
public class VehicleController {
    public ResultSet fetchAll() {
        Connection conn = DatabaseConnection.initConnection();
        ResultSet result;
        
        String sqlQuery = "SELECT * FROM vehicle";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            
            result = ps.executeQuery();
            
            return result;
            
        } catch (SQLException err) {
            System.out.println("SQLError: " + err.getMessage());
            
            return null;
        }
        
    }
    
    public ResultSet fetchOne(String nopol) {
        Connection conn = DatabaseConnection.initConnection();
        ResultSet result;
        
        String sqlQuery = "SELECT * FROM vehicle WHERE nopol_kendaraan=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, nopol);
            
            result = ps.executeQuery();
            
            return result;
            
        } catch (SQLException err) {
            System.out.println("SQLError: " + err.getMessage());
            
            return null;
        }
        
    }
    
    public HashMap<String, String> addVehicle(String nopol, String namaVehicle, String tipeVehicle) {
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> result = new HashMap<String, String>();
        
        String sqlQuery = "INSERT INTO vehicle (nopol_kendaraan, nama_kendaraan, tipe_kendaraan) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, nopol);
            ps.setString(2, namaVehicle);
            ps.setString(3, tipeVehicle);
            
            int response = ps.executeUpdate();
            
            if (response == 1) {
                result.put("code", "OPERATION_SUCCESS");
                result.put("message", "Kendaraan berhasil ditambahkan!");
            } else {
                result.put("code", "OPERATION_FAILED");
                result.put("message", "Terjadi kesalahan, kendaraan gagal di tambahkan!");
            }
            
            return result;
            
        } catch (SQLException err) {
            result.put("code", "SQL_ERROR");
            result.put("message", err.getMessage());
            
            return result;
        }
    }
    
    public HashMap<String, String> deleteVehicle(String nopol) {
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> result = new HashMap<String, String>();
        
        String sqlQuery = "DELETE FROM vehicle WHERE nopol_kendaraan=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, nopol);
            
            int response = ps.executeUpdate();
            
            if (response == 1) {
                result.put("code", "OPERATION_SUCCESS");
                result.put("message", "Kendaraan berhasil dihapus!");
            } else {
                result.put("code", "OPERATION_FAILED");
                result.put("message", "Terjadi kesalahan, kendaraan gagal di hapus!");
            }
            
            return result;
            
        } catch (SQLException err) {
            result.put("code", "SQL_ERROR");
            result.put("message", err.getMessage());
            
            return result;
        }
    }
    
    public HashMap<String, String> editVehicle(String nopol, String namaVehicle, String tipeVehicle) {
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> result = new HashMap<String, String>();
        
        String sqlQuery = "UPDATE vehicle SET nopol_kendaraan=?, nama_kendaraan=?, tipe_kendaraan=? WHERE nopol_kendaraan=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, nopol);
            ps.setString(2, namaVehicle);
            ps.setString(3, tipeVehicle);
            ps.setString(4, nopol);
            
            int response = ps.executeUpdate();
            
            if (response == 1) {
                result.put("code", "OPERATION_SUCCESS");
                result.put("message", "Kendaraan berhasil diedit!");
            } else {
                result.put("code", "OPERATION_FAILED");
                result.put("message", "Terjadi kesalahan, kendaraan gagal diedit!");
            }
            
            return result;
            
        } catch (SQLException err) {
            result.put("code", "SQL_ERROR");
            result.put("message", err.getMessage());
            
            return result;
        }
    }
    
}
