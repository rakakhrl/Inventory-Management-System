/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author rakakhrl
 */
public class DatabaseConnection {
    private static Connection connection;
    
    public static Connection initConnection () {
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/misp_warehouse", "root", "jatisarir28");
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Message: " + e.getMessage());
            }
        
        return connection;
    }
    
}
