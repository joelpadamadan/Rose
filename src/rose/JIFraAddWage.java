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
 * @author Betsy
 */
public class JIFraAddWage extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    Connection con = null;
    ResultSet rs = null;
    Statement stmt = null;
    String query;
    String[] LabourerNo;
    Double total;
    int rowcount, temp;
    String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    String text;
     

    public JIFraAddWage() {
        initComponents();
        jcmbType.addItem("Cutting");
        jcmbType.addItem("Stitching");
        jdteDate.setDate(new Date());
        model = (DefaultTableModel) jtblWageAdvance.getModel();
    }

    private void populatestitching() {
        try {
            LabourerNo = jcmbName.getSelectedItem().toString().split("   ");
            con = new DBConnection().connect();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            rowcount = jtblWageAdvance.getRowCount();
            for (int i = 0; i < rowcount; i++) {
                model.removeRow(0);
            }
            int j = 0;
            query = "select b.tailor_name,a.labour_type,a.description,a.date_given,a.amount from mst_wage a join mst_tailor b on a.labour_no = b.tailor_no  where a.labour_type= 'Stitching' and b.tailor_no = '" + LabourerNo[0] + "' and a.flag!='D' and b.flag!='D' order by a.sl_no desc";
            rs = stmt.executeQuery(query);
            for (j = 0; rs.next(); j++) {
                model.addRow(new Object[]{""});
                jtblWageAdvance.setValueAt(rs.getString(2), j, 0);
                jtblWageAdvance.setValueAt(rs.getString(1), j, 1);
                jtblWageAdvance.setValueAt(rs.getString(3), j, 2);
                jtblWageAdvance.setValueAt(rs.getString(4), j, 3);
                jtblWageAdvance.setValueAt(rs.getString(5), j, 4);
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

    private void populatecutting() {
        try {
            LabourerNo = jcmbName.getSelectedItem().toString().split("   ");
            con = new DBConnection().connect();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            rowcount = jtblWageAdvance.getRowCount();
            for (int i = 0; i < rowcount; i++) {
                model.removeRow(0);
            }
            int j = 0;
            query = "select b.cutting_master,a.labour_type,a.description,a.date_given,a.amount from mst_wage a join mst_group b on a.labour_no = b.group_no  where a.labour_type= 'Cutting' and b.group_no = '" + LabourerNo[0] + "' and a.flag!='D' and b.flag!='D' order by a.sl_no desc";
            rs = stmt.executeQuery(query);
            for (j = 0; rs.next(); j++) {
                model.addRow(new Object[]{""});
                jtblWageAdvance.setValueAt(rs.getString(2), j, 0);
                jtblWageAdvance.setValueAt(rs.getString(1), j, 1);
                jtblWageAdvance.setValueAt(rs.getString(3), j, 2);
                jtblWageAdvance.setValueAt(rs.getString(4), j, 3);
                jtblWageAdvance.setValueAt(rs.getString(5), j, 4);
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

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jcmbType = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jcmbName = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jdteDate = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jtxtAmount = new javax.swing.JTextField();
        jbtnAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblWageAdvance = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jtxtDescription = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Add Wage");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Type");

        jcmbType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbTypeItemStateChanged(evt);
            }
        });

        jLabel2.setText("Name");

        jcmbName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbNameItemStateChanged(evt);
            }
        });

        jLabel3.setText("Date");

        jLabel4.setText("Amount");

        jtxtAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtAmountKeyPressed(evt);
            }
        });

        jbtnAdd.setText("Add");
        jbtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddActionPerformed(evt);
            }
        });

        jtblWageAdvance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "Name", "Description", "Date", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtblWageAdvance);
        jtblWageAdvance.getColumnModel().getColumn(0).setPreferredWidth(120);
        jtblWageAdvance.getColumnModel().getColumn(3).setPreferredWidth(100);
        jtblWageAdvance.getColumnModel().getColumn(4).setPreferredWidth(70);

        jLabel5.setText("Description");

        jtxtDescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtDescriptionKeyPressed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcmbType, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcmbName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdteDate, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addComponent(jbtnAdd)
                        .addGap(0, 111, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jcmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jcmbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(jdteDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAdd)
                    .addComponent(jLabel4)
                    .addComponent(jtxtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jtxtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jcmbTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbTypeItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            try {
                con = new DBConnection().connect();
                stmt = con.createStatement();
                if (jcmbType.getSelectedItem().equals("Cutting")) {
                    query = "select group_no,cutting_master from mst_group where flag!='D' and year(date_created)='" + Global.year + "'";
                    rs = stmt.executeQuery(query);
                    jcmbName.removeAllItems();
                    jcmbName.addItem("");
                    while (rs.next()) {
                        jcmbName.addItem(rs.getString(1) + "   " + rs.getString(2));
                    }
                } else if (jcmbType.getSelectedItem().equals("Stitching")) {
                    query = "select tailor_no,tailor_name from mst_tailor where flag!='D' and year(date_created)='" + Global.year + "'";
                    rs = stmt.executeQuery(query);
                    jcmbName.removeAllItems();
                    jcmbName.addItem("");
                    while (rs.next()) {
                        jcmbName.addItem(rs.getString(1) + "   " + rs.getString(2));
                    }
                } else {
                    jcmbName.removeAllItems();
                    jtxtAmount.setText("");
                    jdteDate.setDate(new Date());
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

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbTypeItemStateChanged

    private void jcmbNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbNameItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (jcmbName.getSelectedItem().equals("")) {
            } else {
                if (jcmbType.getSelectedItem().equals("Cutting")) {
                    populatecutting();
                } else if (jcmbType.getSelectedItem().equals("Stitching")) {
                    populatestitching();
                }
            }
        }
    }//GEN-LAST:event_jcmbNameItemStateChanged

    private void jtxtAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtAmountKeyPressed
        text = "";
        text = jtxtAmount.getText();
        if (text.length() == 5) {
            jtxtAmount.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtAmount.setEditable(true);
            }
        } else if (evt.getKeyChar() >= evt.VK_0 && evt.getKeyChar() <= evt.VK_9 || evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT || evt.getKeyChar() == evt.VK_PERIOD) {
            jtxtAmount.setEditable(true);
        } else {
            jtxtAmount.setEditable(false);
        }
    }//GEN-LAST:event_jtxtAmountKeyPressed

    private void jbtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddActionPerformed
        if (jdteDate.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please select a date", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtxtAmount.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter an amount", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jcmbName.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a labourer", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtxtDescription.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a description", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                temp = 0;
                LabourerNo = jcmbName.getSelectedItem().toString().split("   ");
                con = new DBConnection().connect();
                con.setAutoCommit(false);
                stmt = con.createStatement();
                //                query = "select amount from mst_wage_advance where labour_no = '" + LabourerNo[0] + "' and labour_type='" + jcmbType.getSelectedItem() + "' and date_given = '" + new SimpleDateFormat("yyyy-MM-dd").format(jdteDate.getDate()) + "' and flag!='D'";
                //                rs = stmt.executeQuery(query);
                //                if (rs.next()) {
                //                    temp = rs.getInt(1) + Integer.parseInt(jtxtAmount.getText());
                //                    query = "update mst_wage_advance set amount = '" + temp + "' where labour_no = '" + LabourerNo[0] + "' and labour_type='" + jcmbType.getSelectedItem() + "' and date_given = '" + new SimpleDateFormat("yyyy-MM-dd").format(jdteDate.getDate()) + "'";
                //                } else {
                query = "INSERT INTO mst_wage(LABOUR_NO, LABOUR_TYPE,DESCRIPTION,AMOUNT, DATE_GIVEN, FLAG, DATE_CREATED) VALUES ('" + LabourerNo[0] + "', '" + jcmbType.getSelectedItem() + "','" + jtxtDescription.getText().trim() + "' ,'" + jtxtAmount.getText().trim() + "', '" + new SimpleDateFormat("yyyy-MM-dd").format(jdteDate.getDate()) + "', 'I', '" + today + "')";
                //                }
                int i = stmt.executeUpdate(query);
                if (i > 0) {
                    con.commit();
                    JOptionPane.showMessageDialog(this, "Inserted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    jtxtAmount.setText("");
                    jtxtDescription.setText("");
                    jdteDate.setDate(new Date());
                    if (jcmbType.getSelectedItem().equals("Cutting") && !jcmbName.getSelectedItem().equals("")) {
                        populatecutting();
                    } else if (jcmbType.getSelectedItem().equals("Stitching") && !jcmbName.getSelectedItem().equals("")) {
                        populatestitching();
                    }
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
    }//GEN-LAST:event_jbtnAddActionPerformed

    private void jtxtDescriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtDescriptionKeyPressed
        text = jtxtDescription.getText();
        if (text.length() == 30) {
            jtxtDescription.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtDescription.setEditable(true);
            }
        } else {
            jtxtDescription.setEditable(true);
        }
    }//GEN-LAST:event_jtxtDescriptionKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnAdd;
    private javax.swing.JComboBox jcmbName;
    private javax.swing.JComboBox jcmbType;
    private com.toedter.calendar.JDateChooser jdteDate;
    private javax.swing.JTable jtblWageAdvance;
    private javax.swing.JTextField jtxtAmount;
    private javax.swing.JTextField jtxtDescription;
    // End of variables declaration//GEN-END:variables
}
