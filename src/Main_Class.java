
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Himanshu Soni
 */
public class Main_Class {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hsoni", "root", "");

            Login l = new Login(conn);
            l.setVisible(true);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

    }

}
