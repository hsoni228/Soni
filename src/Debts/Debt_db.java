package Debts;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Himanshu Soni
 */
public class Debt_db {

    Connection con;
    Statement st, st2;
    ResultSet rs;

    Debt_db() {

    }

    void sendData(Connection con, String sno, String name) {
        //date="2014-10-08";
        String query = "insert into customers values(" + sno + "," + "'" + name + "')";
        //System.out.print(query);
        try {
            st = con.createStatement();

            st.executeUpdate(query);

        } catch (SQLException e) {
            //System.out.println(e);
        }

    }

    void updateData(Connection con, String sno, String name) {
        String query = "update customers set Name='" + name + "' where Id=" + sno;

        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            //System.out.println(e);
        }

    }

    void deleteData(Connection con, String sno) {
        //date="2014-10-08";
        String query = "delete from customers where Id=" + sno;
        //System.out.println(query);
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            //System.out.println(e);
        }

    }

}
