/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopkeeperBill;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Himanshu Soni
 */
public class View_db {

    Connection con;
    Statement st, st2;
    ResultSet rs;

    View_db() {

    }

    void sendData(Connection con, String id, String bill, String date, String credit, String debit) {
        //date="2014-10-08";
        String query = "insert into shopkeepers_bill values(" + id + "," + bill + ",'" + date + "'," + debit + "," + credit + ")";
        //System.out.print(query);
        try {
            st = con.createStatement();

            st.executeUpdate(query);

        } catch (SQLException e) {
            //System.out.println(e);
        }

    }

    void updateData(Connection con, String id, String bill, String date, String credit, String debit) {
        String query = "update shopkeepers_bill set Date='" + date + "', Debit=" + debit + ", Credit=" + credit + " where Id=" + id + " and Bill_No=" + bill;
        //System.out.println(query);
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            //System.out.println(e);
        }

    }

    void deleteData(Connection con, String id, String bill) {
        //date="2014-10-08";
        String query = "delete from shopkeepers_bill where id=" + id + " and Bill_No=" + bill;
        //System.out.println(query);
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            //System.out.println(e);
        }

    }

}
