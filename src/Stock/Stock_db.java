package Stock;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Himanshu Soni
 */
public class Stock_db {

    Connection con;
    Statement st, st2;
    ResultSet rs;
    PreparedStatement pst;
    Stock_db() {

    }

    void sendData(Connection con, String item, String qty) {
        
        String query = "select quantity from stock where item_name='"+item+"'";
        String query2 = "update stock set quantity=? where item_name='"+item+"'";
        try {
            pst = con.prepareStatement(query);
            rs=pst.executeQuery();
            rs.next();
            float a=Float.parseFloat(rs.getString(1));
            a=Float.parseFloat(qty)+a;
            pst = con.prepareStatement(query2);
            pst.setFloat(1, a);
            pst.executeUpdate();

        } catch (SQLException e) {
            //System.out.println(e);
        }

    }

    void updateData(Connection con,String item, String qty) {
        String query = "update stock set quantity=" + qty + " where item_name='" + item+"'";

        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            //System.out.println(e);
        }

    }

    void deleteData(Connection con, String sno) {
        //date="2014-10-08";
        String query = "delete from item_list where Sr_No=" + sno;
        //System.out.println(query);
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            //System.out.println(e);
        }

    }

}
