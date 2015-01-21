/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColdDrinks;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Himanshu Soni
 */
public class Add_Cold_Drink_db {

    Connection con;
    Statement st;
    ResultSet rs;

    Add_Cold_Drink_db() {

    }

    void sendData(Connection con, String sno, String name, String date, String qty) {
        //date="2014-10-08";
        String query = "insert into cold_drinks values(" + sno + "," + "'" + name + "'" + "," + "'" + date + "'" + "," + qty + ")";
        //System.out.println(query);
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            //System.out.println(e);
        }

    }

    void updateData(Connection con, String sno, String name, String date, String qty) {
        //date="2014-10-08";
        String query = "update cold_drinks set Name='" + name + "',Date='" + date + "',Quantity=" + qty + " where S_No=" + sno;
        //System.out.println(query);
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            //System.out.println(e);
        }

    }

    void deleteData(Connection con, String sno) {
        //date="2014-10-08";
        String query = "delete from cold_drinks where S_No=" + sno;
        //System.out.println(query);
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            //System.out.println(e);
        }

    }

}
