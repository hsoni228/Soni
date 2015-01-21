/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Debts;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Himanshu Soni
 */
public class Debt_data extends javax.swing.JFrame {

    /**
     * Creates new form Debt_data
     */
    Connection con;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;
    CallableStatement cstmt;

    Date dt = new Date();
    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(dt);

    public Debt_data() {
        initComponents();
        jButton1.setFocusable(false);
        jButton2.setFocusable(false);
        jButton3.setFocusable(false);

    }

    public Debt_data(Connection conn) {
        this();
        con = conn;
        this.getRootPane().setDefaultButton(jButton1);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(modifiedDate);
            jDateChooser1.setDate(date);

            jTable1.setEnabled(false);
            jTextField1.grabFocus();

        } catch (Exception e) {
            //System.out.println("here " + e);
        }
    }

    public void fwdData(int swich,String bill, String id, String name, String rno) {
        String query = "", query2 = "",quer="";
        if ("".equals(bill)) {
            query = "insert into debt_data values(" + id + ",'0'," + rno + ")";
            query2 = "update rough set Deleted='1' where Sr_No=" + rno;
        } else if ("".equals(rno)) {
            quer="delete from debt_data where Bill_No="+bill;
            query = "insert into debt_data values(" + id + "," + bill + ",'0')";
        }
        //System.out.println(query);
        try {
            st = con.createStatement();
            if (!"".equals(bill)) {
                st = con.createStatement();
                st.executeUpdate(quer);
            }
            
            st.executeUpdate(query);
            
            if (!"".equals(rno)) {
                st = con.createStatement();
                st.executeUpdate(query2);
            }

        } catch (Exception e) {

        }
        jLabel1.setText(id);
        jLabel4.setText(name);
        updateTable();
        if (rno != "") {
            Notebook.Rough r = new Notebook.Rough(con);
            r.setVisible(true);
            dispose();
        }
        
        if(swich==1)
        this.dispose();
    }

    void updateTable() {
        String query = "delete from debit";
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {

        }

        try {
            pst = con.prepareStatement("select Date,Id,debt_data.Bill_No,SUM(Price) from bill_details,debt_data,item_details where id=" + jLabel1.getText() + " and debt_data.Bill_No=item_details.Bill_No and debt_data.Bill_No=bill_details.Bill_No group by Bill_No");

            rs = pst.executeQuery();

            while (rs.next()) {
                query = "insert into debit values('B','" + rs.getString(1) + "'," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4) + ",0)";
                st = con.createStatement();
                st.executeUpdate(query);
            }
        } catch (Exception e) {

        }

        try {
            pst = con.prepareStatement("select Date,Id,Cash from debt_cash where Id=" + jLabel1.getText());
            //System.out.println(pst);
            rs = pst.executeQuery();

            while (rs.next()) {
                query = "insert into debit values('C','" + rs.getString(1) + "'," + rs.getString(2) + ",0,0," + rs.getString(3) + ")";
                //System.out.println(query);
                st = con.createStatement();
                st.executeUpdate(query);
                //System.out.println(query);
            }
        } catch (Exception e) {

        }

        try {
            pst = con.prepareStatement("select Date,id,Sr_No,Price from rough,debt_data where Id=" + jLabel1.getText() + " and R_No=Sr_No");
            //System.out.println(pst);
            rs = pst.executeQuery();

            while (rs.next()) {
                query = "insert into debit values('R','" + rs.getString(1) + "'," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4) + ",0)";
                //System.out.println(query);
                st = con.createStatement();
                st.executeUpdate(query);
                //System.out.println(query);
            }
        } catch (Exception e) {
            //System.out.println(e);
        }

        try {
            pst = con.prepareStatement("select * from debit order by Date");

            rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.getDataVector().removeAllElements();
            float debit = 0, credit = 0;
            while (rs.next()) {
                String modifiedDate = new SimpleDateFormat("dd-MMM-yyyy").format(rs.getDate(2));
                model.addRow(new Object[]{rs.getString(1), modifiedDate, rs.getInt(4), rs.getFloat(5), rs.getFloat(6)});
                debit = debit + rs.getFloat(5);
                credit = credit + rs.getFloat(6);
            }
            float bal = debit - credit;
            jLabel8.setText("" + bal);

            ////System.out.println("stmt is "+pst);
        } catch (Exception e) {
            //System.out.println(e);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Customer Details");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "Date", "Bill No.", "Debit", "Credit"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Customer Id");

        jLabel3.setText("Customer Name");

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Modify");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        jLabel4.setText("jLabel4");

        jDateChooser1.setDateFormatString("dd-MMM-yyyy");

        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });

        jLabel5.setText("Cash");

        jLabel6.setText("Date");

        jLabel7.setText("Balance");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jButton2)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Debt_data_db d = new Debt_data_db();
        Date dateFromDateChooser = jDateChooser1.getDate();
        String dateString = String.format("%1$tY-%1$tm-%1$td", dateFromDateChooser);
        
        d.sendData(con, jLabel1.getText(), dateString, jTextField1.getText());
        jTextField1.setText("0");
        updateTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Debt_data_db d = new Debt_data_db();
        Date dateFromDateChooser = jDateChooser1.getDate();

        String dateString = String.format("%1$tY-%1$tm-%1$td", dateFromDateChooser);

        d.updateData(con, jLabel1.getText(), dateString, Float.parseFloat(jTextField1.getText()));
        jTextField1.setText("0");
        updateTable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Debt_data_db d = new Debt_data_db();
        Date dateFromDateChooser = jDateChooser1.getDate();
        String dateString = String.format("%1$tY-%1$tm-%1$td", dateFromDateChooser);
        d.deleteData(con, jLabel1.getText(), dateString);
        jTextField1.setText("0");
        updateTable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        Point point = evt.getPoint();
        int row = jTable1.rowAtPoint(point);

        if ("B".equals(jTable1.getModel().getValueAt(row, 0).toString())) {
            CustomerBill.Add_Bill a = new CustomerBill.Add_Bill(con);
            a.setVisible(true);
            a.sendBill(jTable1.getModel().getValueAt(row, 2).toString());
        } else if ("R".equals(jTable1.getModel().getValueAt(row, 0).toString())) {
            Notebook.Rough_Name n = new Notebook.Rough_Name(con);
            n.setVisible(true);
            n.sendRough(jTable1.getModel().getValueAt(row, 2).toString());
        } else if ("C".equals(jTable1.getModel().getValueAt(row, 0).toString())) {

        }
        dispose();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        jTextField1.select(0, jTextField1.getText().length());
    }//GEN-LAST:event_jTextField1FocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Debt_data.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Debt_data.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Debt_data.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Debt_data.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Debt_data().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
