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
public class Item_db {

    Connection con;
    Statement st, st2;
    ResultSet rs;

    Item_db() {

    }

    void sendData(Connection con, String sno, String item, String price, String qty) {
        //date="2014-10-08";
        String query = "insert into item_list values(" + sno + "," + "'" + item + "'," + qty + "," + price + ")";

        //System.out.print(query);
        try {
            st = con.createStatement();
            st.executeUpdate(query);
            if (Float.parseFloat(price) != 0 && Float.parseFloat(qty) != 0) {
                String query2 = "insert into stock (Item_name,Quantity) values('" + item + "',0)";
                st = con.createStatement();
                st.executeUpdate(query2);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    void updateData(Connection con, String sno, String item, String price, String qty) {
        String query = "update item_list set Name='" + item + "',Quantity=" + qty + ",Price=" + price + " where Sr_No=" + sno;
        String quer = "select Name from item_list where sr_no=" + sno;

        try {
            PreparedStatement pst;
            pst = con.prepareStatement(quer);
            rs = pst.executeQuery();
            rs.next();
            String n = rs.getString(1);
            st = con.createStatement();
            st.executeUpdate(query);

            String query2 = "update stock set Item_name='" + item + "' where Item_name='" + n + "'";
            st = con.createStatement();
            st.executeUpdate(query2);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    void deleteData(Connection con, String sno) {
        //date="2014-10-08";
        String query = "delete from item_list where Sr_No=" + sno;
        String quer = "select Name from item_list where sr_no=" + sno;
        //System.out.println(query);
        try {
            PreparedStatement pst;
            pst = con.prepareStatement(quer);
            rs = pst.executeQuery();
            rs.next();
            String n = rs.getString(1);

            st = con.createStatement();
            st.executeUpdate(query);

            query = "delete from stock where Item_name='" + n + "'";

            st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

}
