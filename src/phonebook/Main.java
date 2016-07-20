/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.StampedLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author homefulloflove
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        String conn = "jdbc:sqlite:database.sl3";
        
        try(Connection ctn = DriverManager.getConnection(conn)){
           
            Statement stmt = ctn.createStatement();
            
            stmt.execute("CREATE TABLE IF NOT EXISTS contacts (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT)");
            
//            stmt.executeUpdate("INSERT INTO contacts VALUES (null, 'Olga', '0932066751')");
//            stmt.executeUpdate("INSERT INTO contacts VALUES (null, 'Vasia', '0675432878')");
//            stmt.executeUpdate("INSERT INTO contacts VALUES (null, 'Alex', '0673270889')");
            
            stmt.executeUpdate("DELETE FROM contacts WHERE id = 4");
            
            
            if (stmt.execute("SELECT * FROM contacts")) {
                ResultSet rs = stmt.getResultSet();
                while(rs.next()){
                    // +380 (92) 123-45-67
                    System.out.printf("#%d name: %s, phone number: %s\n",rs.getInt(1), rs.getString(2), rs.getString(3));
                }
            }
            
            ResultSet eq = stmt.executeQuery("SELECT * FROM contacts");
            
//            System.out.println(eq);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
