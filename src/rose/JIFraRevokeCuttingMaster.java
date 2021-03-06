/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rose;

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
public class JIFraRevokeCuttingMaster extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    Connection con = null;
    ResultSet rs = null;
    Statement stmt = null;
    String query;
    String[] orderno;
    int stsl, rowcount;
    String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    public JIFraRevokeCuttingMaster() {
        try {
            initComponents();
            model = (DefaultTableModel) jtblCuttingMaster.getModel();
            con = new DBConnection().connect();
            stmt = con.createStatement();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jcmbOrderNo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblCuttingMaster = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Revoke CuttingMaster");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Order No.");

        jcmbOrderNo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbOrderNoItemStateChanged(evt);
            }
        });

        jtblCuttingMaster.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Order No.", "Student Name", "Cutting Master", "Item", "Quantity", "Date Given", "Date Recieved", "Status"
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
        jScrollPane1.setViewportView(jtblCuttingMaster);
        jtblCuttingMaster.getColumnModel().getColumn(0).setMinWidth(0);
        jtblCuttingMaster.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtblCuttingMaster.getColumnModel().getColumn(0).setMaxWidth(0);
        jtblCuttingMaster.getColumnModel().getColumn(1).setMinWidth(70);
        jtblCuttingMaster.getColumnModel().getColumn(1).setPreferredWidth(70);
        jtblCuttingMaster.getColumnModel().getColumn(1).setMaxWidth(70);
        jtblCuttingMaster.getColumnModel().getColumn(5).setMinWidth(55);
        jtblCuttingMaster.getColumnModel().getColumn(5).setPreferredWidth(55);
        jtblCuttingMaster.getColumnModel().getColumn(5).setMaxWidth(55);
        jtblCuttingMaster.getColumnModel().getColumn(8).setMinWidth(50);
        jtblCuttingMaster.getColumnModel().getColumn(8).setPreferredWidth(50);
        jtblCuttingMaster.getColumnModel().getColumn(8).setMaxWidth(50);

        jButton1.setText("Revoke");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 320, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcmbOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                    populatemastertable(orderno[0]);
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
                rowcount = jtblCuttingMaster.getRowCount();
                for (int i = 0; i < rowcount; i++) {
                    model.removeRow(0);
                }
            }
        }
    }//GEN-LAST:event_jcmbOrderNoItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jtblCuttingMaster.getRowCount() > 0) {
            if (jtblCuttingMaster.getSelectedRow() >= 0) {
                int b = JOptionPane.showConfirmDialog(this, "Do you want a revoke this item to the cutting master?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (b == JOptionPane.YES_OPTION) {
                    try {
                        con = new DBConnection().connect();
                        stmt = con.createStatement();
                        orderno = jcmbOrderNo.getSelectedItem().toString().split("   ");
                        query = "update trn_cm set date_recieved=" + null + ", date_updated='" + today + "',flag='I',status ='P' where sl_no = '" + jtblCuttingMaster.getValueAt(jtblCuttingMaster.getSelectedRow(), 0) + "' ";
                        int i = stmt.executeUpdate(query);
                        if (i > 0) {
                            JOptionPane.showMessageDialog(this, "Update Successfull!", "Info", JOptionPane.INFORMATION_MESSAGE);
                            populatemastertable(orderno[0]);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    } finally {
                        try {
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
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order number to continue!", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void populatemastertable(String orderno) {
        try {
            con = new DBConnection().connect();
            stmt = con.createStatement();
            query = "select a.sl_no,b.order_no,b.student_name,c.cutting_master,d.item_name,a.qty,a.date_given,a.date_recieved,a.status from trn_cm a,mst_student b,mst_group c,mst_item d where a.status = 'C' and a.group_no = c.group_no and a.stsl_no = b.stsl_no and b.school_no = '" + Global.getSchoolno() + "' and d.item_no = a.item_no and a.flag != 'D' and b.flag != 'D' and c.flag != 'D' and d.flag != 'D' and b.order_no='" + orderno + "'";
            rs = stmt.executeQuery(query);
            rowcount = jtblCuttingMaster.getRowCount();
            for (int i = 0; i < rowcount; i++) {
                model.removeRow(0);
            }
            while (rs.next()) {
                model.addRow(new Object[]{});
                jtblCuttingMaster.setValueAt(rs.getString(1), jtblCuttingMaster.getRowCount() - 1, 0);
                jtblCuttingMaster.setValueAt(rs.getString(2), jtblCuttingMaster.getRowCount() - 1, 1);
                jtblCuttingMaster.setValueAt(rs.getString(3), jtblCuttingMaster.getRowCount() - 1, 2);
                jtblCuttingMaster.setValueAt(rs.getString(4), jtblCuttingMaster.getRowCount() - 1, 3);
                jtblCuttingMaster.setValueAt(rs.getString(5), jtblCuttingMaster.getRowCount() - 1, 4);
                jtblCuttingMaster.setValueAt(rs.getString(6), jtblCuttingMaster.getRowCount() - 1, 5);
                jtblCuttingMaster.setValueAt(rs.getString(7), jtblCuttingMaster.getRowCount() - 1, 6);
                jtblCuttingMaster.setValueAt(rs.getString(8), jtblCuttingMaster.getRowCount() - 1, 7);
                jtblCuttingMaster.setValueAt(rs.getString(9), jtblCuttingMaster.getRowCount() - 1, 8);
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcmbOrderNo;
    private javax.swing.JTable jtblCuttingMaster;
    // End of variables declaration//GEN-END:variables
}
