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
public class LoginController {
    public HashMap<String, String> login(String username, String password) {
        ResultSet result;
        Connection conn = DatabaseConnection.initConnection();
        HashMap<String, String> response = new HashMap<String, String>();
        HashMap<String, String> error = new HashMap<String, String>();
        String sqlQuery = "SELECT * FROM user WHERE username=? AND password=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, username);
            ps.setString(2, password);
            
            result = ps.executeQuery();
            
            if(result.next()){
                response.put("code", "LOGIN_SUCCESS");
                response.put("message", "Login Berhasil!");
            }else{
                response.put("code", "LOGIN_FAILED");
                response.put("message", "Maaf username atau password salah.");
            }
            
            return response;
            
        } catch(SQLException err) {
            error.put("errorMessage", err.getMessage());
            
            return error;
        }
    
    }
}