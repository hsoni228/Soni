/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColdDrinks;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Himanshu Soni
 */
public class Add_Cold_Drink extends javax.swing.JFrame {

    /**
     * Creates new form Add_Cold_Drink
     */
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    int counter = 0;

    public Add_Cold_Drink() {
        initComponents();
        jButton1.setFocusable(false);
        jButton2.setFocusable(false);
        jButton3.setFocusable(false);
        jDateChooser1.setFocusable(false);
    }

    public Add_Cold_Drink(Connection conn) {
        this();
        con = conn;
        this.getRootPane().setDefaultButton(jButton1);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        Date dt = new Date();
        String modifiedDate = new SimpleDateFormat("dd-MMM-yyyy").format(dt);
        jTable1.setEnabled(false);
        jTextField2.grabFocus();
        jTable1.setEnabled(false);

        try {
            java.util.Date date = new SimpleDateFormat("dd-MMM-yyyy").parse(modifiedDate);
            jDateChooser1.setDate(date);

            pst = con.prepareStatement("select MAX(S_No) from cold_drinks");
            rs = pst.executeQuery();
            rs.next();

            int a;
            if (rs.getString(1) == null) {
                a = 0;
            } else {
                a = (Integer.parseInt(rs.getString(1)));
            }
            a++;
            jTextField1.setText(a + "");

            updateTable();

        } catch (Exception e) {
            //System.out.println(e);
        }

    }

    public void updateTable() {
        try {
            pst = con.prepareStatement("select * from cold_drinks order by S_No");
            rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.getDataVector().removeAllElements();
            while (rs.next()) {
                String modifiedDate = new SimpleDateFormat("dd-MMM-yyyy").format(rs.getDate(3));
                model.addRow(new Object[]{rs.getInt(1), rs.getString(2), modifiedDate, rs.getInt(4)});
            }
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Cold Drink");
        setBackground(new java.awt.Color(255, 204, 0));

        jLabel1.setText("S. No.");

        jLabel2.setText("Name");

        jLabel3.setText("Date");

        jLabel5.setText("Quantity");

        jDateChooser1.setDateFormatString("dd-MMM-yyyy"); // NOI18N
        jDateChooser1.setMaximumSize(new java.awt.Dimension(100, 100));

        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField2FocusGained(evt);
            }
        });

        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField3FocusGained(evt);
            }
        });

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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sr No.", "Name", "Date", "Quantity"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addGap(21, 21, 21))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(26, 26, 26)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(jButton1)
                                .addGap(26, 26, 26)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3))
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Add_Cold_Drink_db d = new Add_Cold_Drink_db();
        Date dateFromDateChooser = jDateChooser1.getDate();

        String dateString = String.format("%1$tY-%1$tm-%1$td", dateFromDateChooser);

        try {
            pst = con.prepareStatement("select MAX(S_No) from cold_drinks");
            rs = pst.executeQuery();
            rs.next();

            int a;
            if (rs.getString(1) == null) {
                a = 0;
            } else {
                a = (Integer.parseInt(rs.getString(1)));
            }
            a++;
            jTextField1.setText(a + "");
        } catch (Exception e) {

        }

        d.sendData(con, jTextField1.getText(), jTextField2.getText(), dateString, jTextField3.getText());
        int b = Integer.parseInt(jTextField1.getText());
        b++;
        jTextField1.setText("" + b);
        jTextField2.setText("");
        jTextField3.setText("");

        jTextField2.grabFocus();
        jButton1.enable(false);
        jButton2.enable(false);
        jButton3.enable(false);

        updateTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Add_Cold_Drink_db d = new Add_Cold_Drink_db();

        Date dateFromDateChooser = jDateChooser1.getDate();

        String dateString = String.format("%1$tY-%1$tm-%1$td", dateFromDateChooser);

        d.updateData(con, jTextField1.getText(), jTextField2.getText(), dateString, jTextField3.getText());

        try {
            pst = con.prepareStatement("select MAX(S_No) from cold_drinks");
            rs = pst.executeQuery();
            rs.next();

            int a;
            if (rs.getString(1) == null) {
                a = 0;
            } else {
                a = (Integer.parseInt(rs.getString(1)));
            }
            a++;
            jTextField1.setText(a + "");
        } catch (Exception e) {

        }
        
        jTextField2.grabFocus();

        try {
            jTextField2.setText("");
            jTextField3.setText("");

            Date dt = new Date();
            String modifiedDate = new SimpleDateFormat("dd-MMM-yyyy").format(dt);

            java.util.Date date = new SimpleDateFormat("dd-MMM-yyyy").parse(modifiedDate);
            jDateChooser1.setDate(date);
        } catch (Exception e) {
            //System.out.println(e);
        }

        updateTable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Add_Cold_Drink_db d = new Add_Cold_Drink_db();
        d.deleteData(con, jTextField1.getText());

        try {
            pst = con.prepareStatement("select MAX(S_No) from cold_drinks");
            rs = pst.executeQuery();
            rs.next();

            int a;
            if (rs.getString(1) == null) {
                a = 0;
            } else {
                a = (Integer.parseInt(rs.getString(1)));
            }
            a++;
            jTextField1.setText(a + "");
            jTextField2.setText("");
            jTextField3.setText("");
            
        } catch (Exception e) {

        }
        
        jTextField2.grabFocus();
        
        updateTable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusGained

        jTextField2.select(0, jTextField2.getText().length());
        try {

            pst = con.prepareStatement("select * from cold_drinks where S_No=" + jTextField1.getText());
            rs = pst.executeQuery();
            rs.next();

            jTextField2.setText(rs.getString(2));

            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(3));
            jDateChooser1.setDate(date);

            jTextField3.setText(rs.getString(4));

        } catch (Exception e) {
            //System.out.println(e);
        }
    }//GEN-LAST:event_jTextField2FocusGained

    private void jTextField3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusGained
        jTextField3.select(0, jTextField3.getText().length());
    }//GEN-LAST:event_jTextField3FocusGained

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
            java.util.logging.Logger.getLogger(Add_Cold_Drink.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_Cold_Drink.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_Cold_Drink.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Cold_Drink.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Add_Cold_Drink().setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
