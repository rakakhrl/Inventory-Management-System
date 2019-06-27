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
public class InventoryController {
    public HashMap<String, String> tambahJumlah(String kodeBrg, int jumlah) {
        ResultSet result;
        ResultSet updatedResult;
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> response = new HashMap<String, String>();
        
        String getQuery = "SELECT * FROM inventory WHERE kode_barang=?";
        String insertQuery = "UPDATE inventory SET jumlah=? WHERE kode_barang=?";
        
        try {
            PreparedStatement getPS = conn.prepareStatement(getQuery);
            getPS.setString(1, kodeBrg);
            
            result = getPS.executeQuery();
            
            if (result.first()) {
                int jumlahSebelum = result.getInt("jumlah");
                int jumlahSesudah = jumlahSebelum + jumlah;
                
                PreparedStatement insertPS = conn.prepareStatement(insertQuery);
                insertPS.setInt(1, jumlahSesudah);
                insertPS.setString(2, kodeBrg);
                
                insertPS.executeUpdate();
                
                updatedResult = getPS.executeQuery();
                
                if (updatedResult.first() && updatedResult.getInt("jumlah") > jumlahSebelum) {
                    System.out.println(updatedResult.getInt("jumlah"));
                    response.put("code", "OPERATION_SUCCESS");
                    response.put("message", "Barang berhasil di update!");
                } else {
                    System.out.println(updatedResult.getInt("jumlah"));
                    response.put("code", "OPERATION_FAILED");
                    response.put("message", "Terjadi kesalahan, barang gagal di update!");
                }
                
            } else {
                System.out.println(result);
                response.put("code", "OPERATION_FAILED");
                response.put("message", "Terjadi kesalahan, barang tidak ditemukan.");
            }
            
            return response;
            
        } catch (SQLException err) {
            response.put("code", "SQL_ERROR");
            response.put("message", err.getMessage());
            
            return response;
        }
    }
    
    public HashMap<String, String> kurangJumlah(String kodeBrg, int jumlah) {
        ResultSet result;
        ResultSet updatedResult;
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> response = new HashMap<String, String>();
        
        String getQuery = "SELECT * FROM inventory WHERE kode_barang=?";
        String insertQuery = "UPDATE inventory SET jumlah=? WHERE kode_barang=?";
        
        try {
            PreparedStatement getPS = conn.prepareStatement(getQuery);
            getPS.setString(1, kodeBrg);
            
            result = getPS.executeQuery();
            
            if (result.first()) {
                int jumlahSebelum = result.getInt("jumlah");
                int jumlahSesudah = jumlahSebelum - jumlah;
                
                PreparedStatement insertPS = conn.prepareStatement(insertQuery);
                insertPS.setInt(1, jumlahSesudah);
                insertPS.setString(2, kodeBrg);
                
                insertPS.executeUpdate();
                
                updatedResult = getPS.executeQuery();
                
                if (updatedResult.first() && updatedResult.getInt("jumlah") < jumlahSebelum) {
                    System.out.println(updatedResult.getInt("jumlah"));
                    response.put("code", "OPERATION_SUCCESS");
                    response.put("message", "Barang berhasil di update!");
                } else {
                    System.out.println(updatedResult.getInt("jumlah"));
                    response.put("code", "OPERATION_FAILED");
                    response.put("message", "Terjadi kesalahan, barang gagal di update!");
                }
                
            } else {
                System.out.println(result);
                response.put("code", "OPERATION_FAILED");
                response.put("message", "Terjadi kesalahan, barang tidak ditemukan.");
            }
            
            return response;
            
        } catch (SQLException err) {
            response.put("code", "SQL_ERROR");
            response.put("message", err.getMessage());
            
            return response;
        }
    }
    
    public HashMap<String, String> tambahBarang(String kodeBrg, String namaBrg, int jumlah, float hargaKg) {
        ResultSet result;
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> response = new HashMap<String, String>();
        
        String insertQuery = "INSERT INTO inventory (kode_barang, nama_barang, jumlah, harga_perkilo) VALUES (?, ?, ?, ?)";
        String getQuery = "SELECT * FROM inventory WHERE kode_barang=?";
        
        try {
            PreparedStatement insertPS = conn.prepareStatement(insertQuery);
            insertPS.setString(1, kodeBrg);
            insertPS.setString(2, namaBrg);
            insertPS.setInt(3, jumlah);
            insertPS.setFloat(4, hargaKg);
            
            PreparedStatement getPS = conn.prepareStatement(getQuery);
            getPS.setString(1, kodeBrg);
            
            insertPS.executeUpdate();
            result = getPS.executeQuery();
            
            if (result.first()) {
                response.put("code", "OPERATION_SUCCESS");
                response.put("message", "Barang berhasil di tambahkan!");
            } else {
                response.put("code", "OPERATION_FAILED");
                response.put("message", "Terjadi kesalahan, barang gagal di tambahkan!");
            }
            
            return response;
            
        } catch (SQLException err) {
            System.out.println(err);
            response.put("code", "SQL_ERROR");
            response.put("message", err.getMessage());
            
            return response;
        }
    }
    
    public HashMap<String, String> hapusBarang(String kodeBrg) {
        ResultSet result;
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> response = new HashMap<String, String>();
        
        String delQuery = "DELETE FROM inventory WHERE kode_barang=?";
        String getQuery = "SELECT * FROM inventory WHERE kode_barang=?";
        
        try {
            PreparedStatement delPS = conn.prepareStatement(delQuery);
            delPS.setString(1, kodeBrg);
            
            PreparedStatement getPS = conn.prepareStatement(getQuery);
            getPS.setString(1, kodeBrg);
            
            delPS.executeUpdate();
            result = getPS.executeQuery();
            
            if (result.first() == false) {
                response.put("code", "OPERATION_SUCCESS");
                response.put("message", "Barang berhasil di hapuskan!");
            } else {
                response.put("code", "OPERATION_FAILED");
                response.put("message", "Terjadi kesalahan, barang gagal di hapuskan!");
            }
            
            return response;
            
        } catch (SQLException err) {
            System.out.println(err);
            response.put("code", "SQL_ERROR");
            response.put("message", err.getMessage());
            
            return response;
        }
    }
    
    public HashMap<String, String> editBarang(String kodeBrg, String namaBrg, float hargaKg) {
        int result;
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> response = new HashMap<String, String>();
        
        String editQuery = "UPDATE inventory SET nama_barang=?, harga_perkilo=? WHERE kode_barang=?";
        String getQuery = "SELECT * FROM inventory WHERE kode_barang=?";
        
        try {
            PreparedStatement editPS = conn.prepareStatement(editQuery);
            editPS.setString(1, namaBrg);
            editPS.setFloat(2, hargaKg);
            editPS.setString(3, kodeBrg);
            
            PreparedStatement getPS = conn.prepareStatement(getQuery);
            getPS.setString(1, kodeBrg);
            
            result = editPS.executeUpdate();
            // result = getPS.executeQuery();
            
            if (result == 1) {
                response.put("code", "OPERATION_SUCCESS");
                response.put("message", "Barang berhasil di update!");
            } else {
                response.put("code", "OPERATION_FAILED");
                response.put("message", "Terjadi kesalahan, barang gagal di update!");
            }
            
            return response;
            
        } catch (SQLException err) {
            System.out.println(err);
            response.put("code", "SQL_ERROR");
            response.put("message", err.getMessage());
            
            return response;
        }
    }
    
    public ResultSet fetchOne(String kodeBrg) {
        ResultSet result;
        Connection conn = DatabaseConnection.initConnection();
        
        String sqlQuery = "SELECT * FROM inventory WHERE kode_barang=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, kodeBrg);
            
            result = ps.executeQuery();
            
            return result;
            
        } catch (SQLException err) {
            System.out.println("SQLError: " + err.getMessage());
            
            return null;
        }
    }
    
    public ResultSet fetchAll() {
        ResultSet result;
        Connection conn = DatabaseConnection.initConnection();
        
        String sqlQuery = "SELECT * FROM inventory";
        
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
