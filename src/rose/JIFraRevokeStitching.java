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
public class JIFraRevokeStitching extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    Connection con = null;
    ResultSet rs = null;
    Statement stmt = null;
    String query;
    String[] orderno;
    int stsl, rowcount;
    String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    public JIFraRevokeStitching() {
        try {
            initComponents();
            model = (DefaultTableModel) jtblStitchingMaster.getModel();
            con = new DBConnection().connect();
            stmt = con.createStatement();
            query = "select distinct(a.stsl_no),b.order_no,b.student_name from trn_stitching a left join mst_student b on a.stsl_no = b.stsl_no where a.status = 'C' and a.flag!='D' and b.flag!='D' and b.school_no = '" + Global.getSchoolno() + "'";
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
        jtblStitchingMaster = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Revoke Stitching");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Order No.");

        jcmbOrderNo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbOrderNoItemStateChanged(evt);
            }
        });

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
        jScrollPane1.setViewportView(jtblStitchingMaster);
        jtblStitchingMaster.getColumnModel().getColumn(0).setMinWidth(0);
        jtblStitchingMaster.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtblStitchingMaster.getColumnModel().getColumn(0).setMaxWidth(0);
        jtblStitchingMaster.getColumnModel().getColumn(1).setMinWidth(70);
        jtblStitchingMaster.getColumnModel().getColumn(1).setPreferredWidth(70);
        jtblStitchingMaster.getColumnModel().getColumn(1).setMaxWidth(70);
        jtblStitchingMaster.getColumnModel().getColumn(5).setMinWidth(55);
        jtblStitchingMaster.getColumnModel().getColumn(5).setPreferredWidth(55);
        jtblStitchingMaster.getColumnModel().getColumn(5).setMaxWidth(55);
        jtblStitchingMaster.getColumnModel().getColumn(8).setMinWidth(50);
        jtblStitchingMaster.getColumnModel().getColumn(8).setPreferredWidth(50);
        jtblStitchingMaster.getColumnModel().getColumn(8).setMaxWidth(50);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
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
                .addContainerGap(32, Short.MAX_VALUE))
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
            } else {
                rowcount = jtblStitchingMaster.getRowCount();
                for (int i = 0; i < rowcount; i++) {
                    model.removeRow(0);
                }
            }
        }
    }//GEN-LAST:event_jcmbOrderNoItemStateChanged
    private void populatetable(int stslno) {
        try {
            rowcount = jtblStitchingMaster.getRowCount();
            for (int i = 0; i < rowcount; i++) {
                model.removeRow(0);
            }
            con = new DBConnection().connect();
            stmt = con.createStatement();
            query = "select a.sl_no,b.order_no,b.student_name,e.tailor_name,d.item_name,a.qty,a.date_given,a.date_recieved,a.status from trn_stitching a left join mst_student b on a.stsl_no = b.stsl_no left join mst_item d on a.item_no = d.item_no left join mst_tailor e on a.tailor_no = e.tailor_no where a.status = 'C' and a.stsl_no = '" + stslno + "' and a.flag!='D'  and b.flag!='D'  and d.flag!='D'  and e.flag!='D'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                rowcount = jtblStitchingMaster.getRowCount();
                model.addRow(new Object[]{});
                jtblStitchingMaster.setValueAt(rs.getString(1), rowcount, 0);
                jtblStitchingMaster.setValueAt(rs.getString(2), rowcount, 1);
                jtblStitchingMaster.setValueAt(rs.getString(3), rowcount, 2);
                jtblStitchingMaster.setValueAt(rs.getString(4), rowcount, 3);
                jtblStitchingMaster.setValueAt(rs.getString(5), rowcount, 4);
                jtblStitchingMaster.setValueAt(rs.getString(6), rowcount, 5);
                jtblStitchingMaster.setValueAt(rs.getString(7), rowcount, 6);
                jtblStitchingMaster.setValueAt(rs.getString(8), rowcount, 7);
                jtblStitchingMaster.setValueAt(rs.getString(9), rowcount, 8);
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
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jtblStitchingMaster.getRowCount() > 0) {
            if (jtblStitchingMaster.getSelectedRow() >= 0) {
                int b = JOptionPane.showConfirmDialog(this, "Do you want to revoke this item?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (b == JOptionPane.YES_OPTION) {
                    try {
                        con = new DBConnection().connect();
                        stmt = con.createStatement();
                        orderno = jcmbOrderNo.getSelectedItem().toString().split("   ");
                        query = "select stsl_no from mst_student where order_no = '" + orderno[0] + "' and school_no='" + Global.getSchoolno() + "' and flag!='D'";
                        rs = stmt.executeQuery(query);
                        if (rs.next()) {
                            stsl = rs.getInt(1);
                        }
                        query = "update trn_stitching set date_recieved=" + null + ", date_updated='" + today + "',flag='I',status ='P' where sl_no = '" + jtblStitchingMaster.getValueAt(jtblStitchingMaster.getSelectedRow(), 0) + "' ";
                        int i = stmt.executeUpdate(query);
                        if (i > 0) {
                            JOptionPane.showMessageDialog(this, "Update Successfull!", "Info", JOptionPane.INFORMATION_MESSAGE);
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
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order number to continue!", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcmbOrderNo;
    private javax.swing.JTable jtblStitchingMaster;
    // End of variables declaration//GEN-END:variables
}
