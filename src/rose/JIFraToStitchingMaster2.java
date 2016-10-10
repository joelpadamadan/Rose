/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rose;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Joel
 */
public class JIFraToStitchingMaster2 extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    Connection con = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;
    Statement stmt = null;
    Statement stmt1 = null;
    Statement stmt2 = null;
    String query = null, text;
    String[] orderno, groupno, tailorno, itemno;
    Boolean check = false;
    int rowcount = 0, qty = 0, temp = 0, count = 0, stsl = 0, slno = 0, takenqty = 0;
    String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
     

    public JIFraToStitchingMaster2() {
        try {
            initComponents();
            MyTraversalPolicy policy = new MyTraversalPolicy();
            setFocusTraversalPolicy(policy);
            model = (DefaultTableModel) jtblStitchingMaster.getModel();
            jdteDate.setDate(new Date());
            jdteTakeDate.setDate(new Date());
            con = new DBConnection().connect();
            stmt = con.createStatement();
            query = "select tailor_no,tailor_name from mst_tailor where flag!='D' and year(date_created)='" + Global.year + "'";
            rs = stmt.executeQuery(query);
            jcmbStitchingMaster.addItem("");
            while (rs.next()) {
                jcmbStitchingMaster.addItem(rs.getString(1) + "   " + rs.getString(2));
            }
            query = "select distinct(a.stsl_no),b.order_no,b.student_name from trn_cm a left join mst_student b on a.stsl_no = b.stsl_no where a.status = 'C' and a.flag!='D' and b.flag!='D' and b.school_no = '" + Global.getSchoolno() + "'";
            rs = stmt.executeQuery(query);
            jcmbOrderNo.addItem("");
            while (rs.next()) {
                jcmbOrderNo.addItem(rs.getString(2) + "   " + rs.getString(3));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
                if (!con.isClosed()) {
                    con.close();
                }
                if (!stmt.isClosed()) {
                    stmt.isClosed();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }

    private void populatetable(int stslno) {
        try {
            rowcount = jtblStitchingMaster.getRowCount();
            for (int i = 0; i < rowcount; i++) {
                model.removeRow(0);
            }
            con = new DBConnection().connect();
            stmt = con.createStatement();
            query = "select a.sl_no,b.order_no,b.student_name,e.tailor_name,d.item_name,a.qty,a.date_given,a.date_recieved,a.status from trn_stitching a left join mst_student b on a.stsl_no = b.stsl_no left join mst_item d on a.item_no = d.item_no left join mst_tailor e on a.tailor_no = e.tailor_no where a.stsl_no = '" + stslno + "' and a.flag!='D'  and b.flag!='D'  and d.flag!='D'  and e.flag!='D'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                model.addRow(new Object[]{});
                temp = jtblStitchingMaster.getRowCount() - 1;
                jtblStitchingMaster.setValueAt(rs.getString(1), temp, 0);
                jtblStitchingMaster.setValueAt(rs.getString(2), temp, 1);
                jtblStitchingMaster.setValueAt(rs.getString(3), temp, 2);
                jtblStitchingMaster.setValueAt(rs.getString(4), temp, 3);
                jtblStitchingMaster.setValueAt(rs.getString(5), temp, 4);
                jtblStitchingMaster.setValueAt(rs.getString(6), temp, 5);
                jtblStitchingMaster.setValueAt(rs.getString(7), temp, 6);
                jtblStitchingMaster.setValueAt(rs.getString(8), temp, 7);
                jtblStitchingMaster.setValueAt(rs.getString(9), temp, 8);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
                if (!con.isClosed()) {
                    con.close();
                }
                if (!stmt.isClosed()) {
                    stmt.isClosed();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }

    }

    private class MyTraversalPolicy extends FocusTraversalPolicy {

        @Override
        public Component getComponentAfter(Container aContainer, Component aComponent) {
            if (aComponent.equals(jcmbStitchingMaster)) {
                return jcmbOrderNo;
            } else if (aComponent.equals(jcmbOrderNo)) {
                return jcmbItem;
            } else if (aComponent.equals(jcmbItem)) {
                return jtxtQty;
            } else if (aComponent.equals(jtxtQty)) {
                return jdteDate;
            } else if (aComponent.equals(jdteDate)) {
                return jbtnGive;
            } else if (aComponent.equals(jbtnGive)) {
                return jdteTakeDate;
            } else if (aComponent.equals(jdteTakeDate)) {
                return jtxtQty1;
            } else if (aComponent.equals(jtxtQty1)) {
                return jbtnTake;
            } else if (aComponent.equals(jbtnTake)) {
                return jBtnDelete;
            } else if (aComponent.equals(jBtnDelete)) {
                return jcmbStitchingMaster;
            }
            return jcmbStitchingMaster;

        }

        @Override
        public Component getComponentBefore(Container aContainer, Component aComponent) {
            if (aComponent.equals(jcmbStitchingMaster)) {
                return jBtnDelete;
            } else if (aComponent.equals(jcmbOrderNo)) {
                return jcmbStitchingMaster;
            } else if (aComponent.equals(jcmbItem)) {
                return jcmbOrderNo;
            } else if (aComponent.equals(jtxtQty)) {
                return jcmbItem;
            } else if (aComponent.equals(jdteDate)) {
                return jtxtQty;
            } else if (aComponent.equals(jbtnGive)) {
                return jdteDate;
            } else if (aComponent.equals(jdteTakeDate)) {
                return jbtnGive;
            } else if (aComponent.equals(jtxtQty1)) {
                return jdteTakeDate;
            } else if (aComponent.equals(jbtnTake)) {
                return jtxtQty1;
            } else if (aComponent.equals(jBtnDelete)) {
                return jbtnTake;
            }
            return jcmbStitchingMaster;
        }

        @Override
        public Component getFirstComponent(Container aContainer) {
            return jcmbStitchingMaster;
        }

        @Override
        public Component getLastComponent(Container aContainer) {
            return jbtnTake;

        }

        @Override
        public Component getDefaultComponent(Container aContainer) {
            return jcmbStitchingMaster;
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jcmbOrderNo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jdteDate = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblStitchingMaster = new javax.swing.JTable();
        jbtnGive = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jcmbStitchingMaster = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jcmbItem = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jtxtQty = new javax.swing.JTextField();
        jdteTakeDate = new com.toedter.calendar.JDateChooser();
        jbtnTake = new javax.swing.JButton();
        jBtnDelete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jtxtQty1 = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Stitching Master");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("Order No.");

        jcmbOrderNo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbOrderNoItemStateChanged(evt);
            }
        });

        jLabel3.setText("Date");

        jdteDate.setDateFormatString("dd-MM-yyyy");

        jtblStitchingMaster.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Order No.", "Student Name", "Stitching Master", "Item", "Quantity", "Date Given", "Date Recieved", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblStitchingMaster.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblStitchingMasterMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblStitchingMaster);
        jtblStitchingMaster.getColumnModel().getColumn(0).setMinWidth(0);
        jtblStitchingMaster.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtblStitchingMaster.getColumnModel().getColumn(0).setMaxWidth(0);
        jtblStitchingMaster.getColumnModel().getColumn(1).setMinWidth(70);
        jtblStitchingMaster.getColumnModel().getColumn(1).setPreferredWidth(70);
        jtblStitchingMaster.getColumnModel().getColumn(1).setMaxWidth(70);
        jtblStitchingMaster.getColumnModel().getColumn(5).setMinWidth(70);
        jtblStitchingMaster.getColumnModel().getColumn(5).setPreferredWidth(70);
        jtblStitchingMaster.getColumnModel().getColumn(5).setMaxWidth(70);
        jtblStitchingMaster.getColumnModel().getColumn(6).setMinWidth(80);
        jtblStitchingMaster.getColumnModel().getColumn(6).setPreferredWidth(80);
        jtblStitchingMaster.getColumnModel().getColumn(6).setMaxWidth(80);
        jtblStitchingMaster.getColumnModel().getColumn(7).setMinWidth(110);
        jtblStitchingMaster.getColumnModel().getColumn(7).setPreferredWidth(110);
        jtblStitchingMaster.getColumnModel().getColumn(7).setMaxWidth(110);
        jtblStitchingMaster.getColumnModel().getColumn(8).setMinWidth(60);
        jtblStitchingMaster.getColumnModel().getColumn(8).setPreferredWidth(60);
        jtblStitchingMaster.getColumnModel().getColumn(8).setMaxWidth(60);

        jbtnGive.setText("GIVE");
        jbtnGive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGiveActionPerformed(evt);
            }
        });

        jLabel4.setText("Stitching Master");

        jcmbStitchingMaster.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbStitchingMasterItemStateChanged(evt);
            }
        });

        jLabel5.setText("Item");

        jcmbItem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbItemItemStateChanged(evt);
            }
        });

        jLabel6.setText("Quantity");

        jtxtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtQtyKeyPressed(evt);
            }
        });

        jbtnTake.setText("TAKE");
        jbtnTake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTakeActionPerformed(evt);
            }
        });

        jBtnDelete.setText("DELETE");
        jBtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDeleteActionPerformed(evt);
            }
        });

        jLabel7.setText("Quantity");

        jtxtQty1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtQty1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbItem, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 189, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jdteTakeDate, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnTake)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnDelete)
                        .addGap(42, 42, 42))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdteDate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnGive)
                        .addGap(49, 49, 49))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbStitchingMaster, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jcmbStitchingMaster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jcmbOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jcmbItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jdteDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jtxtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(jbtnGive))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jtxtQty1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnTake))
                        .addComponent(jBtnDelete))
                    .addComponent(jdteTakeDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcmbOrderNoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbOrderNoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!jcmbOrderNo.getSelectedItem().equals("")) {
                try {
                    con = new DBConnection().connect();
                    stmt = con.createStatement();
                    orderno = jcmbOrderNo.getSelectedItem().toString().split("   ");
                    query = "select stsl_no from mst_student where order_no = '" + orderno[0] + "' and school_no='" + Global.getSchoolno() + "' and flag!='D'";
                    rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        stsl = rs.getInt(1);
                    }
                    query = "select distinct(a.item_no),b.item_name from trn_cm a join mst_item b on a.item_no = b.item_no where a.stsl_no = '" + stsl + "' and a.status = 'C' and a.flag!='D' and b.flag!='D'";
                    rs = stmt.executeQuery(query);
                    jcmbItem.removeAllItems();
                    jcmbItem.addItem("");
                    jcmbItem.addItem("All");
                    while (rs.next()) {
                        jcmbItem.addItem(rs.getString(1) + "   " + rs.getString(2));
                    }
                    populatetable(stsl);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                } finally {
                    try {
                        if (!rs.isClosed()) {
                            rs.close();
                        }
                        if (!con.isClosed()) {
                            con.close();
                        }
                        if (!stmt.isClosed()) {
                            stmt.isClosed();
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }
                }
            }
        } else {
            jcmbItem.removeAllItems();
        }
    }//GEN-LAST:event_jcmbOrderNoItemStateChanged

    private void jbtnGiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGiveActionPerformed
        if (jcmbStitchingMaster.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a stitching master", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jcmbOrderNo.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select an order no.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jdteDate.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please select a date", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jcmbItem.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select an item", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                orderno = jcmbOrderNo.getSelectedItem().toString().split("   ");
                tailorno = jcmbStitchingMaster.getSelectedItem().toString().split("   ");
                con = new DBConnection().connect();
                stmt1 = con.createStatement();
                stmt = con.createStatement();
                stmt2 = con.createStatement();
                con.setAutoCommit(false);
                int i = 0;
                temp = 0;
                if (jcmbItem.getSelectedItem().equals("All")) {
                    query = "select item_no,sum(qty) from trn_cm where stsl_no = '" + stsl + "' and status = 'C' and flag!='D' group by item_no";
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        query = "select item_no,sum(qty) from trn_stitching where stsl_no = '" + stsl + "' and item_no ='" + rs.getString(1) + "' and flag!='D' group by item_no";
                        rs1 = stmt1.executeQuery(query);
                        if (rs1.next()) {
                            temp = rs.getInt(2) - rs1.getInt(2);
                            if (temp > 0) {
                                query = "select sl_no,qty from trn_stitching where stsl_no ='" + stsl + "' and item_no = '" + rs.getString(1) + "' and date_given = '" + today + "' and tailor_no = '" + tailorno[0] + "' and status='P' and flag!='D'";
                                rs2 = stmt2.executeQuery(query);
                                if (rs2.next()) {
                                    temp = temp + rs2.getInt(2);
                                    query = "update trn_stitching set qty = '" + temp + "' where sl_no='" + rs2.getString(1) + "'";
                                    i = i + stmt2.executeUpdate(query);
                                } else {
                                    query = "insert into trn_stitching(tailor_no,stsl_no,item_no,qty,status,date_given,flag,date_created) values('" + tailorno[0] + "','" + stsl + "','" + rs.getString(1) + "','" + temp + "','P','" + new SimpleDateFormat("yyyy-MM-dd").format(jdteDate.getDate()) + "','I','" + today + "')";
                                    i = i + stmt2.executeUpdate(query);
                                }
                            }
                        } else {
                            query = "insert into trn_stitching(tailor_no,stsl_no,item_no,qty,status,date_given,flag,date_created) values('" + tailorno[0] + "','" + stsl + "','" + rs.getString(1) + "','" + rs.getString(2) + "','P','" + new SimpleDateFormat("yyyy-MM-dd").format(jdteDate.getDate()) + "','I','" + today + "')";
                            i = i + stmt1.executeUpdate(query);
                        }
                    }
                } else {
                    if (jtxtQty.getText().equals("")) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid quantity", "Info", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        itemno = jcmbItem.getSelectedItem().toString().split("   ");
                        query = "select item_no,sum(qty) from trn_cm where stsl_no = '" + stsl + "' and item_no = '" + itemno[0] + "' and status = 'C' and flag!='D' group by item_no";
                        rs = stmt.executeQuery(query);
                        if (rs.next()) {
                            if (rs.getInt(2) >= Integer.parseInt(jtxtQty.getText())) {
                                query = "select item_no,sum(qty) from trn_stitching where stsl_no = '" + stsl + "' and item_no ='" + itemno[0] + "' and flag!='D' group by item_no";
                                rs1 = stmt1.executeQuery(query);
                                if (rs1.next()) {
                                    temp = rs.getInt(2) - rs1.getInt(2);
                                    if (temp > 0) {
                                        if (temp >= Integer.parseInt(jtxtQty.getText())) {
                                            query = "select sl_no,qty from trn_stitching where stsl_no ='" + stsl + "' and item_no = '" + rs.getString(1) + "' and date_given = '" + today + "' and tailor_no = '" + tailorno[0] + "' and status='P' and flag!='D'";
                                            rs2 = stmt2.executeQuery(query);
                                            if (rs2.next()) {
                                                temp = Integer.parseInt(jtxtQty.getText()) + rs2.getInt(2);
                                                query = "update trn_stitching set qty = '" + temp + "' where sl_no='" + rs2.getString(1) + "'";
                                                i = i + stmt2.executeUpdate(query);
                                            } else {
                                                query = "insert into trn_stitching(tailor_no,stsl_no,item_no,qty,status,date_given,flag,date_created) values('" + tailorno[0] + "','" + stsl + "','" + itemno[0] + "','" + jtxtQty.getText() + "','P','" + new SimpleDateFormat("yyyy-MM-dd").format(jdteDate.getDate()) + "','I','" + today + "')";
                                                i = i + stmt2.executeUpdate(query);
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(this, "The quantity entered has exceeded please check!", "Info", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                    }
                                } else {
                                    query = "insert into trn_stitching(tailor_no,stsl_no,item_no,qty,status,date_given,flag,date_created) values('" + tailorno[0] + "','" + stsl + "','" + itemno[0] + "','" + jtxtQty.getText() + "','P','" + new SimpleDateFormat("yyyy-MM-dd").format(jdteDate.getDate()) + "','I','" + today + "')";
                                    i = i + stmt1.executeUpdate(query);
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Quantity entered is not available at the moment!", "Info", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                }
                if (i > 0) {
                    con.commit();
                    JOptionPane.showMessageDialog(this, "Inserted Successfull!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    populatetable(stsl);
                    jcmbItem.setSelectedItem("");
                    jtxtQty.setText("");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            } finally {
                jcmbOrderNo.grabFocus();
                try {
                    if (!rs.isClosed()) {
                        rs.close();
                    }
                    if (!con.isClosed()) {
                        con.close();
                    }
                    if (!stmt.isClosed()) {
                        stmt.isClosed();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jbtnGiveActionPerformed

    private void jcmbStitchingMasterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbStitchingMasterItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (jcmbStitchingMaster.getSelectedItem().equals("")) {
                jcmbOrderNo.setSelectedItem("");
                jcmbItem.setSelectedItem("");
            }
        }
    }//GEN-LAST:event_jcmbStitchingMasterItemStateChanged

    private void jcmbItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbItemItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (jcmbItem.getSelectedItem().equals("All")) {
                jtxtQty.setEnabled(false);
                jtxtQty.setText("");
            } else {
                jtxtQty.setEnabled(true);
                if (!jcmbItem.getSelectedItem().equals("")) {
                    try {
                        itemno = jcmbItem.getSelectedItem().toString().split("   ");
                        jtxtQty.setEnabled(true);
                        con = new DBConnection().connect();
                        stmt = con.createStatement();
                        query = "select qty from mst_student_detail where item_no = '" + itemno[0] + "' and stsl_no = '" + stsl + "' and flag!='D' ";
                        rs = stmt.executeQuery(query);
                        if (rs.next()) {
                            jtxtQty.setText(rs.getString(1));
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    } finally {
                        try {
                            if (!rs.isClosed()) {
                                rs.close();
                            }
                            if (!con.isClosed()) {
                                con.close();
                            }
                            if (!stmt.isClosed()) {
                                stmt.isClosed();
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(this, ex.getMessage());
                        }
                    }
                } else {
                    jtxtQty.setText("");
                }
            }
        }
    }//GEN-LAST:event_jcmbItemItemStateChanged

    private void jtxtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtQtyKeyPressed
        text = "";
        text = jtxtQty.getText();
        if (text.length() == 2) {
            jtxtQty.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtQty.setEditable(true);
            }
        } else if (evt.getKeyChar() >= evt.VK_0 && evt.getKeyChar() <= evt.VK_9 || evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT || evt.getKeyChar() == evt.VK_PERIOD) {
            jtxtQty.setEditable(true);
        } else {
            jtxtQty.setEditable(false);
        }
    }//GEN-LAST:event_jtxtQtyKeyPressed

    private void jbtnTakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTakeActionPerformed
        try {
            if (jtblStitchingMaster.getRowCount() > 0) {
                if (jtblStitchingMaster.getSelectedRow() < 0) {
                    int b = JOptionPane.showConfirmDialog(this, "Do you want a take 'ALL' items from the stitching master?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (b == JOptionPane.YES_OPTION) {
                        temp = 0;
                        for (int i = 0; i < jtblStitchingMaster.getRowCount(); i++) {
                            if (jtblStitchingMaster.getValueAt(i, 8).toString().equals("C")) {
                                temp++;
                            }
                        }
                        if (temp != jtblStitchingMaster.getRowCount()) {
                            temp = 0;
                            con = new DBConnection().connect();
                            con.setAutoCommit(false);
                            stmt = con.createStatement();
                            orderno = jcmbOrderNo.getSelectedItem().toString().split("   ");
                            query = "select stsl_no from mst_student where order_no = '" + orderno[0] + "' and school_no='" + Global.getSchoolno() + "' and flag!='D'";
                            rs = stmt.executeQuery(query);
                            if (rs.next()) {
                                stsl = rs.getInt(1);
                            }
                            for (int i = 0; i < jtblStitchingMaster.getRowCount(); i++) {
                                if (jtblStitchingMaster.getValueAt(i, 8).toString().equals("P")) {
                                    query = "update trn_stitching set date_recieved='" + new SimpleDateFormat("yyyy-MM-dd").format(jdteTakeDate.getDate()) + "', date_updated='" + today + "',flag='I',status ='C' where sl_no = '" + jtblStitchingMaster.getValueAt(i, 0) + "' ";
                                    temp = temp + stmt.executeUpdate(query);
                                }
                            }
                            if (temp > 0) {
                                JOptionPane.showMessageDialog(this, "Update Successfull!", "Info", JOptionPane.INFORMATION_MESSAGE);
                                con.commit();
                                populatetable(stsl);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "All item have already been taken", "Info", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else if (jdteTakeDate.getDate() == null) {
                    JOptionPane.showMessageDialog(this, "Please select a date", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else if (jtblStitchingMaster.getValueAt(jtblStitchingMaster.getSelectedRow(), 8).toString().equals("C")) {
                    JOptionPane.showMessageDialog(this, "This item has already been taken", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else if (jtxtQty1.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please enter a quantity", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int b = JOptionPane.showConfirmDialog(this, "Do you want a take this item from the stitching master?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (b == JOptionPane.YES_OPTION) {
                        if (Integer.parseInt(jtxtQty1.getText()) > 0) {
                            int i = 0;
                            con = new DBConnection().connect();
                            con.setAutoCommit(false);
                            stmt = con.createStatement();
                            stmt2 = con.createStatement();
                            orderno = jcmbOrderNo.getSelectedItem().toString().split("   ");
                            if (Integer.parseInt(jtxtQty1.getText()) == takenqty) {
                                query = "update trn_stitching set date_recieved='" + new SimpleDateFormat("yyyy-MM-dd").format(jdteTakeDate.getDate()) + "', date_updated='" + today + "',flag='I',status ='C' where sl_no = '" + slno + "' ";
                                i = stmt.executeUpdate(query);
                            } else if (Integer.parseInt(jtxtQty1.getText()) < takenqty) {
                                temp = takenqty - Integer.parseInt(jtxtQty1.getText());
                                query = "update trn_stitching set date_updated='" + today + "',flag='I',qty = '" + temp + "'  where sl_no = '" + slno + "' ";
                                i = stmt.executeUpdate(query);
                                query = "select tailor_no,stsl_no,item_no,qty,status,date_given,flag,date_created from trn_stitching where sl_no = '" + slno + "'";
                                rs = stmt.executeQuery(query);
                                if (rs.next()) {
                                    query = "insert into trn_stitching(tailor_no,stsl_no,item_no,qty,status,date_given,date_recieved,flag,date_created) values('" + rs.getString(1) + "','" + rs.getString(2) + "','" + rs.getString(3) + "','" + jtxtQty1.getText() + "','C','" + new SimpleDateFormat("yyyy-MM-dd").format(jdteTakeDate.getDate()) + "','" + today + "','I','" + rs.getString(8) + "')";
                                    i = i + stmt2.executeUpdate(query);
                                }
                            }
                            if (i > 0) {
                                con.commit();
                                JOptionPane.showMessageDialog(this, "Update Successfull!", "Info", JOptionPane.INFORMATION_MESSAGE);
                                populatetable(stsl);
                                jtxtQty1.setText("");
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select an order number to continue!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            jcmbOrderNo.grabFocus();
            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
                if (!con.isClosed()) {
                    con.close();
                }
                if (!stmt.isClosed()) {
                    stmt.isClosed();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_jbtnTakeActionPerformed

    private void jBtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDeleteActionPerformed
        if (jtblStitchingMaster.getRowCount() > 0) {
            if (jtblStitchingMaster.getSelectedRow() > -1) {
                int b = JOptionPane.showConfirmDialog(this, "Do you want delete the record?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (b == JOptionPane.YES_OPTION) {
                    try {
                        temp = 0;
                        orderno = jcmbOrderNo.getSelectedItem().toString().split("   ");
                        con = new DBConnection().connect();
                        con.setAutoCommit(false);
                        stmt = con.createStatement();
                        query = "select stsl_no from mst_student where order_no = '" + orderno[0] + "' and school_no='" + Global.getSchoolno() + "' and flag!='D'";
                        rs = stmt.executeQuery(query);
                        if (rs.next()) {
                            stsl = rs.getInt(1);
                        }
                        query = "update trn_stitching set date_updated='" + today + "',flag='D' where sl_no = '" + jtblStitchingMaster.getValueAt(jtblStitchingMaster.getSelectedRow(), 0) + "' ";
                        temp = stmt.executeUpdate(query);
                        if (temp > 0) {
                            JOptionPane.showMessageDialog(this, "Delete Successfull!", "Info", JOptionPane.INFORMATION_MESSAGE);
                            con.commit();
                            populatetable(stsl);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    } finally {
                        try {
                            if (!rs.isClosed()) {
                                rs.close();
                            }
                            if (!con.isClosed()) {
                                con.close();
                            }
                            if (!stmt.isClosed()) {
                                stmt.isClosed();
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(this, ex.getMessage());
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a record to delete", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jBtnDeleteActionPerformed

    private void jtxtQty1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtQty1KeyPressed
        text = "";
        text = jtxtQty.getText();
        if (text.length() == 2) {
            jtxtQty.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtQty.setEditable(true);
            }
        } else if (evt.getKeyChar() >= evt.VK_0 && evt.getKeyChar() <= evt.VK_9 || evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT || evt.getKeyChar() == evt.VK_PERIOD) {
            jtxtQty.setEditable(true);
        } else {
            jtxtQty.setEditable(false);
        }
    }//GEN-LAST:event_jtxtQty1KeyPressed

    private void jtblStitchingMasterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblStitchingMasterMouseClicked
        if (jtblStitchingMaster.getSelectedRow() > -1) {
            jtxtQty1.setText(jtblStitchingMaster.getValueAt(jtblStitchingMaster.getSelectedRow(), 5).toString());
            takenqty = Integer.parseInt(jtblStitchingMaster.getValueAt(jtblStitchingMaster.getSelectedRow(), 5).toString());
            slno = Integer.parseInt(jtblStitchingMaster.getValueAt(jtblStitchingMaster.getSelectedRow(), 0).toString());
        }
    }//GEN-LAST:event_jtblStitchingMasterMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnDelete;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnGive;
    private javax.swing.JButton jbtnTake;
    private javax.swing.JComboBox jcmbItem;
    private javax.swing.JComboBox jcmbOrderNo;
    private javax.swing.JComboBox jcmbStitchingMaster;
    private com.toedter.calendar.JDateChooser jdteDate;
    private com.toedter.calendar.JDateChooser jdteTakeDate;
    private javax.swing.JTable jtblStitchingMaster;
    private javax.swing.JTextField jtxtQty;
    private javax.swing.JTextField jtxtQty1;
    // End of variables declaration//GEN-END:variables
}
