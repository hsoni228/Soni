/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Notebook;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Himanshu Soni
 */
public class Rough_db {

    Connection con;
    Statement st;
    ResultSet rs;

    Rough_db() {

    }

    void sendData(Connection con, String sno, String name, String item, String date, String price) {
        //date="2014-10-08";
        String query = "insert into rough values(" + sno + "," + "'" + name + "','" + item + "'" + "," + "'" + date + "'" + "," + price + ",0)";
        //System.out.println(query);
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            //System.out.println(e);
        }

    }

    void updateData(Connection con, String sno, String name, String item, String date, String price) {
        //date="2014-10-08";
        String query = "update rough set Name='" + name + "',Item='" + item + "',Date='" + date + "',Price=" + price + " where Sr_No=" + sno;
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
        String query = "delete from rough where Sr_No=" + sno;
        //System.out.println(query);
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            //System.out.println(e);
        }

    }

}
