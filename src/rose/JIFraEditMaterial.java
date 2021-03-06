/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rose;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Joel
 */
public class JIFraEditMaterial extends javax.swing.JInternalFrame {

    Connection con = null;
    ResultSet rs = null;
    Statement stmt = null;
    String query = null;
    DefaultTableModel model1 = null;
    String purchasedon = null, text = null;
    int rowcount = 0, materialno = 0, i = 0;
     
    String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    public JIFraEditMaterial() {
        initComponents();
        jcmbType.addItem("");
        jcmbType.addItem("Shirting");
        jcmbType.addItem("Suiting");
        jcmbType.addItem("Lining");
        jDtePurchasedOn.setDate(new Date());
        model1 = (DefaultTableModel) jtblMaterial.getModel();
        popoulateMaterialMaster();
    }

    private void clear() {
        jTxtLength.setText("");
        jTxtMatWidth.setText("");
        jtxtCostPrice.setText("");
        jtxtMaterialName.setText("");
        jcmbType.setSelectedItem("");
        jDtePurchasedOn.setDate(new Date());
    }

    private void popoulateMaterialMaster() {
        try {
            rowcount = jtblMaterial.getRowCount();
            for (int i = 0; i < rowcount; i++) {
                model1.removeRow(0);
            }
            materialno = 0;
            con = new DBConnection().connect();
            stmt = con.createStatement();
            query = "select MATERIAL_NO,MATERIAL_NAME, WIDTH, LENGTH, COST_PRICE, PURCHASED_ON, MATERIAL_TYPE from mst_material where flag!='D' and year(date_created)='" + Global.year + "'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                rowcount = jtblMaterial.getRowCount();
                model1.addRow(new Object[]{""});
                jtblMaterial.setValueAt(rs.getString(1), rowcount, 0);
                jtblMaterial.setValueAt(rs.getString(2), rowcount, 1);
                jtblMaterial.setValueAt(rs.getString(3), rowcount, 2);
                jtblMaterial.setValueAt(rs.getString(4), rowcount, 3);
                jtblMaterial.setValueAt(rs.getString(5), rowcount, 4);
                jtblMaterial.setValueAt(rs.getString(6), rowcount, 5);
                jtblMaterial.setValueAt(rs.getString(7), rowcount, 6);
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
        jLabel6 = new javax.swing.JLabel();
        jtxtMaterialName = new javax.swing.JTextField();
        jLblMatWidth = new javax.swing.JLabel();
        jTxtMatWidth = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtxtCostPrice = new javax.swing.JTextField();
        jLblPurchasedOn = new javax.swing.JLabel();
        jDtePurchasedOn = new com.toedter.calendar.JDateChooser();
        jLblQuantity = new javax.swing.JLabel();
        jTxtLength = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jcmbType = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblMaterial = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jbtnDelete = new javax.swing.JButton();

        setClosable(true);
        setTitle("Edit Material");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel6.setText("Material Name:");

        jtxtMaterialName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtMaterialNameKeyPressed(evt);
            }
        });

        jLblMatWidth.setText("Material width:");

        jTxtMatWidth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtMatWidthKeyPressed(evt);
            }
        });

        jLabel7.setText("Cost Price:");

        jtxtCostPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtCostPriceKeyPressed(evt);
            }
        });

        jLblPurchasedOn.setText("Purchased on:");

        jDtePurchasedOn.setDateFormatString("dd-MMM-yyyy");

        jLblQuantity.setText("Length:");

        jTxtLength.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtLengthKeyPressed(evt);
            }
        });

        jLabel10.setText("Type");

        jtblMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Material Name", "Material Width", "Length", "Cost Price", "Purchased On", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtblMaterial);
        jtblMaterial.getColumnModel().getColumn(0).setMinWidth(0);
        jtblMaterial.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtblMaterial.getColumnModel().getColumn(0).setMaxWidth(0);

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jbtnDelete.setText("Delete");
        jbtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtMaterialName, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLblMatWidth)
                                .addGap(18, 18, 18)
                                .addComponent(jTxtMatWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLblPurchasedOn)
                                .addGap(21, 21, 21)
                                .addComponent(jDtePurchasedOn, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtCostPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLblQuantity)
                                .addGap(34, 34, 34)
                                .addComponent(jTxtLength, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(47, 47, 47)
                                .addComponent(jcmbType, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addComponent(jButton1))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnDelete)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jbtnDelete))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jtxtMaterialName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblMatWidth)
                            .addComponent(jTxtMatWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLblPurchasedOn)
                            .addComponent(jDtePurchasedOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jtxtCostPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblQuantity)
                            .addComponent(jTxtLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jButton1))))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
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

    private void jtxtMaterialNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtMaterialNameKeyPressed
        text = jtxtMaterialName.getText();
        if (text.length() == 30) {
            jtxtMaterialName.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtMaterialName.setEditable(true);
            }
        } else {
            jtxtMaterialName.setEditable(true);
        }
    }//GEN-LAST:event_jtxtMaterialNameKeyPressed

    private void jTxtMatWidthKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtMatWidthKeyPressed
        text = "";
        text = jTxtMatWidth.getText();
        if (text.length() == 3) {
            jTxtMatWidth.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jTxtMatWidth.setEditable(true);
            }
        } else if (evt.getKeyChar() >= evt.VK_0 && evt.getKeyChar() <= evt.VK_9 || evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT || evt.getKeyChar() == evt.VK_PERIOD) {
            jTxtMatWidth.setEditable(true);
        } else {
            jTxtMatWidth.setEditable(false);
        }
    }//GEN-LAST:event_jTxtMatWidthKeyPressed

    private void jtxtCostPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtCostPriceKeyPressed
        text = "";
        text = jtxtCostPrice.getText();
        if (text.length() == 5) {
            jtxtCostPrice.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtCostPrice.setEditable(true);
            }
        } else if (evt.getKeyChar() >= evt.VK_0 && evt.getKeyChar() <= evt.VK_9 || evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT || evt.getKeyChar() == evt.VK_PERIOD) {
            jtxtCostPrice.setEditable(true);
        } else {
            jtxtCostPrice.setEditable(false);
        }
    }//GEN-LAST:event_jtxtCostPriceKeyPressed

    private void jTxtLengthKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtLengthKeyPressed
        text = "";
        text = jTxtLength.getText();
        if (text.length() == 6) {
            jTxtLength.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jTxtLength.setEditable(true);
            }
        } else if (evt.getKeyChar() >= evt.VK_0 && evt.getKeyChar() <= evt.VK_9 || evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT || evt.getKeyChar() == evt.VK_PERIOD) {
            jTxtLength.setEditable(true);
        } else {
            jTxtLength.setEditable(false);
        }
    }//GEN-LAST:event_jTxtLengthKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        purchasedon = new SimpleDateFormat("yyyy-MM-dd").format(jDtePurchasedOn.getDate());
        if (materialno == 0) {
            JOptionPane.showMessageDialog(this, "Please select a record to update", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtxtMaterialName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a material name", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jTxtMatWidth.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid material width", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtxtCostPrice.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid cost price", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jTxtLength.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid length", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (purchasedon == null || purchasedon.equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a valid date", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if ((jcmbType.getSelectedItem() == null) || (jcmbType.getSelectedItem().equals(""))) {
            JOptionPane.showMessageDialog(this, "Please select the material type", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                con = new DBConnection().connect();
                con.setAutoCommit(false);
                stmt = con.createStatement();
                query = "update mst_material set MATERIAL_NAME = '" + jtxtMaterialName.getText().trim() + "', MATERIAL_TYPE = '" + jcmbType.getSelectedItem() + "', WIDTH = '" + jTxtMatWidth.getText().trim() + "', COST_PRICE = '" + jtxtCostPrice.getText().trim() + "', PURCHASED_ON = '" + purchasedon + "', LENGTH = '" + jTxtLength.getText().trim() + "', FLAG = 'U', DATE_UPDATED = '" + today + "' where MATERIAL_NO = '" + materialno + "'";
                i = stmt.executeUpdate(query);
                if (i > 0) {
                    con.commit();
                    JOptionPane.showMessageDialog(this, "Added Successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    clear();
                    jtxtMaterialName.grabFocus();
                    popoulateMaterialMaster();
                    materialno = 0;
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
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (jtblMaterial.getRowCount() > 0) {
            if (jtblMaterial.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "Please select a row from the table to edit", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    purchasedon = jtblMaterial.getValueAt(jtblMaterial.getSelectedRow(), 5).toString();
                    materialno = Integer.parseInt(jtblMaterial.getValueAt(jtblMaterial.getSelectedRow(), 0).toString());
                    jtxtMaterialName.setText((String) jtblMaterial.getValueAt(jtblMaterial.getSelectedRow(), 1));
                    jTxtMatWidth.setText((String) jtblMaterial.getValueAt(jtblMaterial.getSelectedRow(), 2));
                    jTxtLength.setText((String) jtblMaterial.getValueAt(jtblMaterial.getSelectedRow(), 3));
                    jtxtCostPrice.setText((String) jtblMaterial.getValueAt(jtblMaterial.getSelectedRow(), 4));
                    jDtePurchasedOn.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(purchasedon));
                    jcmbType.setSelectedItem(jtblMaterial.getValueAt(jtblMaterial.getSelectedRow(), 6));
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jbtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteActionPerformed
        if (materialno != 0) {
            JOptionPane.showMessageDialog(this, "Please complete the update before deleting the record", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtblMaterial.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row from the table to delete", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int b = JOptionPane.showConfirmDialog(this, "Do you want delete the selected material?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (b == JOptionPane.YES_OPTION) {
                try {
                    i = 0;
                    con = new DBConnection().connect();
                    con.setAutoCommit(false);
                    stmt = con.createStatement();
                    query = "update mst_material set FLAG = 'D',DATE_UPDATED = '" + today + "' where MATERIAL_NO = '" + jtblMaterial.getValueAt(jtblMaterial.getSelectedRow(), 0) + "'";
                    i = stmt.executeUpdate(query);
                    if (i > 0) {
                        con.commit();
                        JOptionPane.showMessageDialog(this, "Deleted Successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        jtxtMaterialName.grabFocus();
                        popoulateMaterialMaster();
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
    }//GEN-LAST:event_jbtnDeleteActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDtePurchasedOn;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLblMatWidth;
    private javax.swing.JLabel jLblPurchasedOn;
    private javax.swing.JLabel jLblQuantity;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTxtLength;
    private javax.swing.JTextField jTxtMatWidth;
    private javax.swing.JButton jbtnDelete;
    private javax.swing.JComboBox jcmbType;
    private javax.swing.JTable jtblMaterial;
    private javax.swing.JTextField jtxtCostPrice;
    private javax.swing.JTextField jtxtMaterialName;
    // End of variables declaration//GEN-END:variables
}
