/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerBill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Himanshu Soni
 */
public class Add_Bill_db {

    Connection con;
    Statement st, st2;
    ResultSet rs;

    Add_Bill_db() {

    }

    void sendData(Connection con, String bill, String sno, String item, String qty, String price, String name, String date) {
        
        String query = "insert into item_details values(" + bill + "," + sno + "," + "'" + item + "'" + "," + qty + "," + price + ")";
        
        String query3 = "select quantity from stock where item_name='"+item+"'";
        System.out.println(query);
        try {
            st = con.createStatement();
            st.executeUpdate(query);
            
            PreparedStatement pst=con.prepareStatement(query3);
            rs=pst.executeQuery();
            rs.next();
            Float a=Float.parseFloat(rs.getString(1));
            a=a-Float.parseFloat(qty);
            
            query="update stock set quantity="+a+" where item_name='"+item+"'";
            pst=con.prepareStatement(query);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    void updateData(Connection con, String bill, String sno, String item, String qty, String price, String name, String date) {
        String q="select item_name,Quantity from item_details where Sr_No=" + sno +" and Bill_No="+bill;
        String query = "update item_details set Item_Name='" + item + "',Quantity=" + qty + ",Price=" + price + " where Sr_No=" + sno +" and Bill_No="+bill;
        String query2 = "update bill_details set Name='" + name + "',Date='" + date + "' where Bill_No=" + bill;
        System.out.println("here "+query+"     "+query2);
        try {
            PreparedStatement pst=con.prepareStatement(q);
            rs=pst.executeQuery();
            rs.next();
            String b=rs.getString(1);
            Float a=Float.parseFloat(rs.getString(2));
            System.out.println(a);
            q="select quantity from stock where item_name='"+b+"'";
            pst=con.prepareStatement(q);
            rs=pst.executeQuery();
            rs.next();
            Float c=Float.parseFloat(rs.getString(1));
            System.out.println(c+"");
            c=c+a;
            System.out.println(c+" "+a);
            q="update stock set quantity="+c+" where item_name='"+b+"'";
            pst=con.prepareStatement(q);
            pst.executeUpdate();
            
            q="select quantity from stock where item_name='"+item+"'";
            PreparedStatement pst1=con.prepareStatement(q);
            ResultSet rs1=pst1.executeQuery();
            rs1.next();
            Float d=Float.parseFloat(rs1.getString(1));
            System.out.println(d+" "+item);
            d=d-Float.parseFloat(qty);
            System.out.println(d+"");
            q="update stock set quantity="+d+" where item_name='"+item+"'";
            pst=con.prepareStatement(q);
            pst.executeUpdate();
            
            st = con.createStatement();
            st2 = con.createStatement();
            st.executeUpdate(query);
            st2.executeUpdate(query2);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    void updatData(Connection con, String bill, String name, String date) {
        try {
            String sql = "select id from customers where name='" + name+"'";
            PreparedStatement pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            String id = rs.getString(1);
            String query = "update debt_data set Id=" + id + " where bill_no=" + bill;
            String query2 = "update bill_details set Name='" + name + "',Date='" + date + "' where Bill_No=" + bill;
            System.out.println(query+"     "+query2);
            st = con.createStatement();
            st2 = con.createStatement();
            st.executeUpdate(query);
            st2.executeUpdate(query2);
        } catch (SQLException e) {
            System.out.println(e);
        }

    

    }

}
