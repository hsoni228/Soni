/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerBill;

/**
 *
 * @author Himanshu Soni
 */
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Add_Bill extends javax.swing.JFrame {

    /**
     * Creates new form Add_Bill
     */
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String arr[];
    int counter = 0;

    public Add_Bill() {
        initComponents();
    }

    public Add_Bill(Connection conn) {
        this();
        con = conn;
        this.getRootPane().setDefaultButton(jButton1);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        Date dt = new Date();
        String modifiedDate = new SimpleDateFormat("dd-MMM-yyyy").format(dt);

        jButton1.setFocusable(false);
        jButton2.setFocusable(false);

        jComboBox1.removeAllItems();
        jComboBox2.removeAllItems();
        jTextField5.grabFocus();

        jTable1.setEnabled(false);

        try {
            java.util.Date date = new SimpleDateFormat("dd-MMM-yyyy").parse(modifiedDate);
            jDateChooser1.setDate(date);

            pst = con.prepareStatement("select MAX(Bill_No) from bill_details");
            rs = pst.executeQuery();
            rs.next();

            int a;
            if (rs.getString(1) == null) {
                a = 0;
            } else {
                a = (Integer.parseInt(rs.getString(1)));
            }
            a++;
            jTextField4.setText(a + "");
            //System.out.print(jTextField4.getText());

            pst = con.prepareStatement("select MAX(Sr_No) from item_details where Bill_No=" + jTextField4.getText());
            rs = pst.executeQuery();
            rs.next();

            if (rs.getString(1) == null) {
                a = 0;
            } else {
                a = (Integer.parseInt(rs.getString(1)));
            }
            a++;
            jTextField1.setText(a + "");
            jLabel10.setText("0");
            jTextField6.setText("");
            jTextField3.setText("");

            pst = con.prepareStatement("select count(*) from item_details");
            rs = pst.executeQuery();
            rs.next();
            int size = Integer.parseInt(rs.getString(1));
            arr = new String[size + 1];

            pst = con.prepareStatement("select Name from item_list");
            rs = pst.executeQuery();

            int i;
            for (i = 0; i < size + 1; i++) {
                rs.next();
                arr[i] = rs.getString(1);
                //System.out.println("size is " + i + " " + arr[i]);

            }

        } catch (Exception e) {
            //System.out.println("11" + e);
        }

    }

    void updateTable(String bill) {
        try {
            int a = Integer.parseInt(bill);
            pst = con.prepareStatement("select MAX(Bill_No) from bill_details");
            rs = pst.executeQuery();
            rs.next();

            int b;
            if (rs.getString(1) == null) {
                b = 0;
            } else {
                b = (Integer.parseInt(rs.getString(1)));
            }

            if (a <= b) {
                pst = con.prepareStatement("select Name,Sr_No,Item_Name,Quantity,Price,Date from item_details,bill_details where item_details.Bill_No=" + bill + " and item_details.Bill_No=bill_details.Bill_No order by Sr_No");
                rs = pst.executeQuery();

                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.getDataVector().removeAllElements();
                float total = 0, sum = 0;

                while (rs.next()) {
                    //System.out.println(rs.getFloat(5));
                    sum = rs.getFloat(5) + sum;
                    jLabel10.setText("" + sum);
                    jTextField5.setText(rs.getString(1));
                    jComboBox2.removeAllItems();
                    jComboBox2.addItem(rs.getString(1));
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(6));
                    jDateChooser1.setDate(date);
                    model.addRow(new Object[]{rs.getInt(2), rs.getString(3), rs.getFloat(5) / rs.getFloat(4), rs.getString(4), rs.getFloat(5)});

                }
            } else {
                jTextField5.setText("");
                jComboBox1.removeAllItems();
                jComboBox2.removeAllItems();
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
                jDateChooser1.setDate(null);
                jTextField4.setText(++b + "");
                Date dt = new Date();
                String modifiedDate = new SimpleDateFormat("dd-MMM-yyyy").format(dt);
                java.util.Date date = new SimpleDateFormat("dd-MMM-yyyy").parse(modifiedDate);
                jDateChooser1.setDate(date);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void sendBill(String bill) {
        try {
            String sql = "select date from bill_details where bill_no=" + bill;
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            //System.out.println(rs.getString(1));
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(1));
            jDateChooser1.setDate(date);
            jTextField4.setText(bill);
        } catch (Exception e) {
            System.out.println(e);
        }
        updateTable(bill);
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Bill");

        jLabel1.setText("Sr_No");

        jLabel2.setText("Item");

        jLabel3.setText("Quantity");

        jLabel5.setText("Price");

        jLabel6.setText("Bill No.");

        jLabel7.setText("Name");

        jLabel8.setText("Date");

        jLabel9.setText("Total Amount");

        jLabel10.setText("jLabel10");

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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jDateChooser1.setDateFormatString("dd-MMM-yyyy");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sr. No.", "Name", "Rate", "Quantity", "Price"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField3FocusGained(evt);
            }
        });

        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField4FocusGained(evt);
            }
        });

        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField5FocusGained(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField6FocusGained(evt);
            }
        });

        jButton3.setText("Export");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton4.setText("Save");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jComboBox2, 0, 145, Short.MAX_VALUE)
                                            .addComponent(jTextField5))
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel8))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 368, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(110, 110, 110))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(jButton3)
                .addGap(60, 60, 60)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(35, 35, 35)
                .addComponent(jLabel10)
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Add_Bill_db d = new Add_Bill_db();

        try {
            pst = con.prepareStatement("select MAX(Sr_No) from item_details where Bill_No=" + jTextField4.getText());
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

        Date dateFromDateChooser = jDateChooser1.getDate();
        String dateString = String.format("%1$tY-%1$tm-%1$td", dateFromDateChooser);

        float pri = 0, qty = 0;

        try {
            pst = con.prepareStatement("select Quantity,Price from item_list where Name='" + jComboBox1.getSelectedItem() + "'");
            //System.out.println(pst);
            rs = pst.executeQuery();
            rs.next();

            qty = Float.parseFloat(rs.getString(1));
            //System.out.println(qty);
            pri = Float.parseFloat(rs.getString(2));
            //System.out.println(pri);

            if ("".equals(jTextField6.getText()) && !"".equals(jTextField3.getText())) {
                //System.out.println("Exe");
                float pric = Float.parseFloat(jTextField3.getText());

                float a = qty / pri * pric;
                //System.out.println("here qty is "+a);
                jTextField6.setText("" + a);
            }

            if (!"".equals(jTextField6.getText()) && "".equals(jTextField3.getText())) {
                //System.out.println("Exe2");
                float quaty = Float.parseFloat(jTextField6.getText());
                float a = pri / qty * quaty;
                //System.out.println(a);
                jTextField3.setText("" + a);
            }

            if (jTextField6.getText() != "" || jTextField3.getText() != "") {
                //System.out.println("hello"+jTextField3.getText() + "text is " + jTextField6.getText());
                d.sendData(con, jTextField4.getText(), jTextField1.getText(), jComboBox1.getSelectedItem().toString(), jTextField6.getText(), jTextField3.getText(), jComboBox2.getSelectedItem().toString(), dateString);
                int b = Integer.parseInt(jTextField1.getText());
                b++;

                jTextField1.setText("" + b);
            }
            jTextField2.setText("");
            jTextField6.setText("");
            jTextField3.setText("");
            jTextField2.grabFocus();

            updateTable(jTextField4.getText());
        } catch (Exception e) {
            //System.out.println("\nhere it is    " + e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Add_Bill_db d = new Add_Bill_db();

        Date dateFromDateChooser = jDateChooser1.getDate();

        String dateString = String.format("%1$tY-%1$tm-%1$td", dateFromDateChooser);
        float pri = 0, qty = 0;
        try {
            if ("".equals(jTextField6.getText()) && "".equals(jTextField3.getText())) {
                System.out.println("hello text is ");
                d.updatData(con, jTextField4.getText(), jComboBox2.getSelectedItem().toString(), dateString);
                int b = Integer.parseInt(jTextField1.getText());
                b++;
                jTextField1.setText("" + b);
            } else {

                System.out.println("kkkkk");
                pst = con.prepareStatement("select Quantity,Price from item_list where Name='" + jComboBox1.getSelectedItem() + "'");
                System.out.println(pst);
                rs = pst.executeQuery();
                rs.next();

                qty = Float.parseFloat(rs.getString(1));
                System.out.println(qty);
                pri = Float.parseFloat(rs.getString(2));
                System.out.println(pri);

                if ("".equals(jTextField6.getText()) && !"".equals(jTextField3.getText())) {
                    System.out.println("Exe");
                    float pric = Float.parseFloat(jTextField3.getText());

                    float a = qty / pri * pric;
                    System.out.println("here qty is " + a);
                    jTextField6.setText("" + a);
                }

                if (!"".equals(jTextField6.getText()) && "".equals(jTextField3.getText())) {
                    System.out.println("Exe2");
                    float quaty = Float.parseFloat(jTextField6.getText());
                    float a = pri / qty * quaty;
                    System.out.println(a);
                    jTextField3.setText("" + a);
                }

                if (!"".equals(jTextField6.getText()) && !"".equals(jTextField3.getText())) {
                    System.out.println("hello" + jTextField3.getText() + "text is " + jTextField6.getText());
                    d.updateData(con, jTextField4.getText(), jTextField1.getText(), jComboBox1.getSelectedItem().toString(), jTextField6.getText(), jTextField3.getText(), jComboBox2.getSelectedItem().toString(), dateString);
                    int b = Integer.parseInt(jTextField1.getText());
                    b++;

                    jTextField1.setText("" + b);
                }
            }

            pst = con.prepareStatement("select MAX(Sr_No) from item_details where Bill_No=" + jTextField4.getText());
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
            jTextField6.setText("");
            jTextField3.setText("");
            jTextField2.grabFocus();

        } catch (Exception e) {
            System.out.println(e);
        }

        updateTable(jTextField4.getText());

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        try {
            pst = con.prepareStatement("select Name from item_list where Name like '" + jTextField2.getText() + "%'");
            rs = pst.executeQuery();
            jComboBox1.removeAllItems();
            while (rs.next()) {
                jComboBox1.addItem(rs.getString(1));
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusGained
        updateTable(jTextField4.getText());
    }//GEN-LAST:event_jTextField5FocusGained

    private void jTextField4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusGained
        jTextField4.select(0, jTextField4.getText().length());
    }//GEN-LAST:event_jTextField4FocusGained

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        jTextField1.select(0, jTextField1.getText().length());
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusGained
        jTextField6.select(0, jTextField6.getText().length());
    }//GEN-LAST:event_jTextField6FocusGained

    private void jTextField3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusGained
        jTextField3.select(0, jTextField3.getText().length());
    }//GEN-LAST:event_jTextField3FocusGained

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            Date dateFromDateChooser = jDateChooser1.getDate();
            String dateString = String.format("%1$tY-%1$tm-%1$td", dateFromDateChooser);

            String query2 = "insert into bill_details values(" + jTextField4.getText() + ",'" + jTextField4.getText() + "','" + dateString + "')";
            pst = con.prepareStatement(query2);
            rs = pst.executeQuery();
            
            String sql = "select id from customers where name='" + jComboBox2.getSelectedItem().toString() + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            Debts.Debt_data d = new Debts.Debt_data(con);
            //d.setVisible(true);
            d.fwdData(1, jTextField4.getText(), rs.getString(1), jComboBox2.getSelectedItem().toString(), "");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            excel exp = new excel();
            Date dateFromDateChooser = jDateChooser1.getDate();
            String dateString = String.format("%1$td-%1$tm-%1$tY", dateFromDateChooser);
            String name = "";

            pst = con.prepareStatement("select * from debit");
            rs = pst.executeQuery();

            float debit = 0, credit = 0;
            while (rs.next()) {
                debit = debit + rs.getFloat(5);
                credit = credit + rs.getFloat(6);
            }
            float bal = debit - credit;
            String balance = "" + bal;

            exp.fillData(jTextField4.getText(), jComboBox2.getSelectedItem().toString(), dateString, jTable1, new File("d:\\result.xls"), jLabel10.getText(), balance);

            Add_Bill a = new Add_Bill(con);
            a.setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            String sql = "select id from customers where name='" + jComboBox2.getSelectedItem().toString() + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();

            Date dateFromDateChooser = jDateChooser1.getDate();
            String dateString = String.format("%1$tY-%1$tm-%1$td", dateFromDateChooser);

            String query2 = "insert into bill_details values(" + jTextField4.getText() + ",'" + jTextField4.getText() + "','" + dateString + "')";
            pst = con.prepareStatement(query2);
            rs = pst.executeQuery();

            Debts.Debt_data d = new Debts.Debt_data(con);
            d.setVisible(true);
            d.fwdData(1, jTextField4.getText(), rs.getString(1), jComboBox2.getSelectedItem().toString(), "");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Add_Bill a = new Add_Bill(con);
            a.setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        try {
            pst = con.prepareStatement("select Name from customers where Name like '" + jTextField5.getText() + "%'");
            rs = pst.executeQuery();
            jComboBox2.removeAllItems();
            while (rs.next()) {
                jComboBox2.addItem(rs.getString(1));
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jTextField5KeyReleased

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
            java.util.logging.Logger.getLogger(Add_Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Add_Bill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
