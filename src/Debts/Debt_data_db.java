/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Debts;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Himanshu Soni
 */
public class Debt_data_db {

    Connection con;
    Statement st, st2;
    ResultSet rs;

    Debt_data_db() {

    }

    void sendData(Connection con, String id, String date, String cash) {
        String query = "insert into debt_cash values(" + id + ",'" + date + "'," + cash + ")";
        //System.out.print(query);
        try {
            st = con.createStatement();

            st.executeUpdate(query);

        } catch (SQLException e) {
            //System.out.println(e);
        }

    }

    void updateData(Connection con, String id, String date, Float cash) {
        String query = "update debt_cash set Date='" + date + "', Cash=" + cash + " where Id=" + id;
        //System.out.println(query);
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            //System.out.println(e);
        }

    }

    void deleteData(Connection con, String id, String date) {
        //date="2014-10-08";
        String query = "delete from debt_cash where Id=" + id + " and Date='" + date + "'";
        //System.out.println(query);
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            //System.out.println(e);
        }

    }

}
