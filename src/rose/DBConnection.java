/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rose;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Smitticus
 */
public class DBConnection {

    Connection con = null;
    ResultSet rs;
    PreparedStatement pstmt;
    Statement stmt;
    Properties prop = new Properties();
    InputStream input = null;
    String projectpath;
    public Connection connect() {
        try {
            try {  
                projectpath = new File(".").getCanonicalPath();
                input = new FileInputStream(projectpath+"/Properties/DBConnect.properties");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            prop.load(input);
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(prop.getProperty("path"),prop.getProperty("username"),prop.getProperty("password"));
            System.out.println("Connection Successfull");
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public ResultSet select(String query) {
        try {
            con = connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int execute(String query) {
        int i = 0;
        try {
            con = connect();
            stmt = con.createStatement();
            i = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;

    }

    public void close() {
        try {
            if (!rs.isClosed()) {
                rs.close();
            }
            if (!stmt.isClosed()) {
                stmt.close();
            }
            if (!con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
