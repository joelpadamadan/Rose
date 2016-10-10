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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Joel
 */
public class JIFraEditMaterialConsumptionPerClass extends javax.swing.JInternalFrame {

    Connection con = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    Statement stmt = null;
    Statement stmt1 = null;
    Statement stmt2 = null;
    String query, text, item_no, class_no, sl_no, from, to;
    String[] school_no, material_no;
    int rowcount = 0;
    DefaultTableModel model1;
    String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    public JIFraEditMaterialConsumptionPerClass() {
        try {
            initComponents();
            model1 = (DefaultTableModel) jtblMaterial.getModel();
            con = new DBConnection().connect();
            stmt = con.createStatement();
            query = "select school_no,school_name from mst_school where flag!='D' and year(date_created)='" + Global.year + "'";
            rs = stmt.executeQuery(query);
            jcmbSchool.removeAllItems();
            jcmbSchool.addItem("");
            while (rs.next()) {
                jcmbSchool.addItem(rs.getString(1) + "   " + rs.getString(2));
            }
            query = "select class from mst_class where flag!='D'";
            rs = stmt.executeQuery(query);
            jcmbClass.removeAllItems();
            jcmbClass.addItem("");
            while (rs.next()) {
                jcmbClass.addItem(rs.getString(1));
            }

            query = "select class from mst_class where flag!='D'";
            rs = stmt.executeQuery(query);
            jCmbFrom.removeAllItems();
            jCmbFrom.addItem("");
            while (rs.next()) {
                jCmbFrom.addItem(rs.getString(1));
            }

            query = "select class from mst_class where flag!='D'";
            rs = stmt.executeQuery(query);
            jCmbTo.removeAllItems();
            jCmbTo.addItem("");
            while (rs.next()) {
                jCmbTo.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
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

    private void populate() {
        try {
            rowcount = jtblMaterial.getRowCount();
            for (int i = 0; i < rowcount; i++) {
                model1.removeRow(0);
            }
            con = new DBConnection().connect();
            stmt = con.createStatement();
            query = "select a.sl_no,b.school_name,c.class,d.item_name,e.material_name,a.actual_mat_length,a.fixed_mat_length from mst_material_consumption a join mst_school b on a.school_no = b.school_no join mst_class c on a.class_no = c.class_no join mst_item d on a.item_no = d.item_no join mst_material e on a.material_no = e.material_no where a.school_no = '" + school_no[0] + "' and a.flag!='D' and b.flag!='D' and c.flag!='D' and d.flag!='D' and e.flag!='D'";
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
            Logger.getLogger(JIFraAddMaterialConsumptionPerClass.class.getName()).log(Level.SEVERE, null, ex);
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
        jcmbSchool = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblMaterial = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jcmbClass = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jcmbItem = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jcmbMaterial = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jtxtTailorLength = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtxtSchoolLength = new javax.swing.JTextField();
        jbtnEdit = new javax.swing.JButton();
        jbtnSave = new javax.swing.JButton();
        jbtnDelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jCmbFrom = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jCmbTo = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Edit Material Consumption Classwise");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("School");

        jcmbSchool.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbSchoolItemStateChanged(evt);
            }
        });

        jtblMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "School", "Class", "Item", "Material", "Tailor Length", "School Length"
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
        jScrollPane1.setViewportView(jtblMaterial);
        jtblMaterial.getColumnModel().getColumn(0).setMinWidth(0);
        jtblMaterial.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtblMaterial.getColumnModel().getColumn(0).setMaxWidth(0);

        jLabel2.setText("Class");

        jcmbClass.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbClassItemStateChanged(evt);
            }
        });

        jLabel3.setText("Item");

        jcmbItem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbItemItemStateChanged(evt);
            }
        });

        jLabel4.setText("Material");

        jLabel5.setText("Tailor Length");

        jtxtTailorLength.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtTailorLengthKeyPressed(evt);
            }
        });

        jLabel6.setText("School Length");

        jtxtSchoolLength.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtSchoolLengthKeyPressed(evt);
            }
        });

        jbtnEdit.setText("Edit");
        jbtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditActionPerformed(evt);
            }
        });

        jbtnSave.setText("Save");
        jbtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveActionPerformed(evt);
            }
        });

        jbtnDelete.setText("Delete");
        jbtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Copy"));

        jLabel7.setText("From");

        jLabel8.setText("To");

        jButton1.setText("Copy");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCmbFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCmbTo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jCmbFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jCmbTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbtnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnDelete))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxtTailorLength, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxtSchoolLength, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jbtnSave))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jcmbSchool, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(12, 12, 12)
                                    .addComponent(jcmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jcmbItem, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jcmbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 55, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcmbSchool, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(477, 477, 477)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnEdit, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtnDelete, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jcmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jcmbItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jcmbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jtxtTailorLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jtxtSchoolLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addComponent(jbtnSave)
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(155, Short.MAX_VALUE)))
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

    private void jcmbSchoolItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbSchoolItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!jcmbSchool.getSelectedItem().equals("")) {
                school_no = jcmbSchool.getSelectedItem().toString().split("   ");
                populate();
            }
        }
    }//GEN-LAST:event_jcmbSchoolItemStateChanged

    private void jcmbClassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbClassItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!jcmbSchool.getSelectedItem().equals("") && !jcmbClass.getSelectedItem().equals("")) {
                try {
                    school_no = jcmbSchool.getSelectedItem().toString().split("   ");
                    con = new DBConnection().connect();
                    stmt = con.createStatement();
                    query = "select b.item_name from mst_school_item_price a join mst_item b on a.item_no = b.item_no where a.school_no = '" + school_no[0] + "' and a.class_from <= '" + jcmbClass.getSelectedItem() + "' and a.class_to >='" + jcmbClass.getSelectedItem() + "' and a.flag!='D' and b.flag!='D'";
                    rs = stmt.executeQuery(query);
                    jcmbItem.removeAllItems();
                    jcmbItem.addItem("");
                    while (rs.next()) {
                        jcmbItem.addItem(rs.getString(1));
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
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
                jcmbItem.removeAllItems();
            }
        }
    }//GEN-LAST:event_jcmbClassItemStateChanged

    private void jcmbItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbItemItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!jcmbItem.getSelectedItem().equals("")) {
                try {
                    school_no = jcmbSchool.getSelectedItem().toString().split("   ");
                    con = new DBConnection().connect();
                    stmt = con.createStatement();
                    query = "select a.material_no,c.material_name from mst_school_mat_price a join mst_item b on a.item_no = b.item_no join mst_material c on a.material_no = c.material_no where b.item_name = '" + jcmbItem.getSelectedItem() + "' and a.school_no = '" + school_no[0] + "' and a.flag!='D' and b.flag!='D' and c.flag!='D'";
                    rs = stmt.executeQuery(query);
                    jcmbMaterial.removeAllItems();
                    while (rs.next()) {
                        jcmbMaterial.addItem(rs.getString(1) + "   " + rs.getString(2));
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
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
                jcmbMaterial.removeAllItems();
            }
        }
    }//GEN-LAST:event_jcmbItemItemStateChanged

    private void jtxtTailorLengthKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtTailorLengthKeyPressed
        text = "";
        text = jtxtTailorLength.getText();
        if (text.length() == 6) {
            jtxtTailorLength.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtTailorLength.setEditable(true);
            }
        } else if (evt.getKeyChar() >= evt.VK_0 && evt.getKeyChar() <= evt.VK_9 || evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT || evt.getKeyChar() == evt.VK_PERIOD) {
            jtxtTailorLength.setEditable(true);
        } else {
            jtxtTailorLength.setEditable(false);
        }
    }//GEN-LAST:event_jtxtTailorLengthKeyPressed

    private void jtxtSchoolLengthKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSchoolLengthKeyPressed
        text = "";
        text = jtxtSchoolLength.getText();
        if (text.length() == 6) {
            jtxtSchoolLength.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtSchoolLength.setEditable(true);
            }
        } else if (evt.getKeyChar() >= evt.VK_0 && evt.getKeyChar() <= evt.VK_9 || evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT || evt.getKeyChar() == evt.VK_PERIOD) {
            jtxtSchoolLength.setEditable(true);
        } else {
            jtxtSchoolLength.setEditable(false);
        }
    }//GEN-LAST:event_jtxtSchoolLengthKeyPressed

    private void jbtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditActionPerformed
        sl_no = jtblMaterial.getValueAt(jtblMaterial.getSelectedRow(), 0).toString();
        jcmbClass.setSelectedItem(jtblMaterial.getValueAt(jtblMaterial.getSelectedRow(), 2).toString());
        jcmbItem.setSelectedItem(jtblMaterial.getValueAt(jtblMaterial.getSelectedRow(), 3).toString());
        jtxtTailorLength.setText(jtblMaterial.getValueAt(jtblMaterial.getSelectedRow(), 5).toString());
        jtxtSchoolLength.setText(jtblMaterial.getValueAt(jtblMaterial.getSelectedRow(), 6).toString());
    }//GEN-LAST:event_jbtnEditActionPerformed

    private void jbtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveActionPerformed
        if (jcmbSchool.getSelectedItem() == null || jcmbSchool.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a school", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jcmbClass.getSelectedItem() == null || jcmbClass.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a class", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jcmbItem.getSelectedItem() == null || jcmbItem.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select an item", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jcmbMaterial.getSelectedItem() == null || jcmbMaterial.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a material", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtxtTailorLength.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter tailor length", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtxtSchoolLength.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter school length", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                school_no = jcmbSchool.getSelectedItem().toString().split("   ");
                material_no = jcmbMaterial.getSelectedItem().toString().split("   ");
                con = new DBConnection().connect();
                stmt = con.createStatement();
                query = "select class_no from mst_class where class = '" + jcmbClass.getSelectedItem() + "' and flag!='D'";
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    class_no = rs.getString(1);
                }
                query = "select item_no from mst_item where item_name = '" + jcmbItem.getSelectedItem() + "' and flag!='D' and year(date_created)='" + Global.year + "'";
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    item_no = rs.getString(1);
                }
                query = "UPDATE MST_MATERIAL_CONSUMPTION SET SCHOOL_NO='" + school_no[0] + "',CLASS_NO='" + class_no + "',ITEM_NO='" + item_no + "',MATERIAL_NO='" + material_no[0] + "',ACTUAL_MAT_LENGTH='" + jtxtTailorLength.getText().trim() + "',FIXED_MAT_LENGTH='" + jtxtSchoolLength.getText().trim() + "',FLAG='U',DATE_UPDATED='" + today + "' WHERE SL_NO = '" + sl_no + "'";
                int i = stmt.executeUpdate(query);
                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Successfully Updated!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    jcmbItem.setSelectedItem("");
                    jcmbMaterial.setSelectedItem("");
                    jtxtTailorLength.setText("");
                    jtxtSchoolLength.setText("");
                    jcmbClass.setSelectedItem("");
                    populate();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JIFraEditMaterialConsumptionPerClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jbtnSaveActionPerformed

    private void jbtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteActionPerformed
        if (jtblMaterial.getSelectedRow() != -1) {
            try {
                con = new DBConnection().connect();
                stmt = con.createStatement();
                query = "UPDATE MST_MATERIAL_CONSUMPTION SET FLAG='D',DATE_UPDATED='" + today + "' WHERE SL_NO = '" + sl_no + "'";
                int i = stmt.executeUpdate(query);
                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Successfully Deleted!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    jcmbItem.setSelectedItem("");
                    jcmbMaterial.setSelectedItem("");
                    jtxtTailorLength.setText("");
                    jtxtSchoolLength.setText("");
                    populate();
                }
            } catch (SQLException ex) {
                Logger.getLogger(JIFraEditMaterialConsumptionPerClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jbtnDeleteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jcmbSchool.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a school", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jCmbFrom.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a class to copy from", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jCmbFrom.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a class to copy to", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                con = new DBConnection().connect();
                con.setAutoCommit(false);
                stmt = con.createStatement();
                stmt1 = con.createStatement();
                stmt2 = con.createStatement();
                query = "select class_no from mst_class where class = '" + jCmbFrom.getSelectedItem() + "' and flag!='D'";
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    from = rs.getString(1);
                }
                query = "select class_no from mst_class where class = '" + jCmbTo.getSelectedItem() + "' and flag!='D'";
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    to = rs.getString(1);
                }
                int i = 0;
                query = "select * from mst_material_consumption where class_no = '" + to + "' and school_no = '" + school_no[0] + "' and flag!='D'";
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Please delete all the records of class " + to, "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    query = "select * from mst_material_consumption where class_no = '" + from + "' and school_no = '" + school_no[0] + "' and flag!='D'";
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        query = "INSERT INTO MST_MATERIAL_CONSUMPTION (SCHOOL_NO,CLASS_NO,ITEM_NO,MATERIAL_NO,FIXED_MAT_LENGTH,ACTUAL_MAT_LENGTH,FLAG,DATE_CREATED) VALUES ('" + rs.getString(2) + "', '" + to + "', '" + rs.getString(4) + "', '" + rs.getString(5) + "', '" + rs.getString(6) + "', '" + rs.getString(7) + "', 'I', '" + today + "')";
                        i = stmt1.executeUpdate(query);
                    }
                    if (i > 0) {
                        JOptionPane.showMessageDialog(this, "Successfully Added!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        jCmbFrom.setSelectedItem("");
                        jCmbTo.setSelectedItem("");
                        con.commit();
                        populate();
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(JIFraEditMaterialConsumptionPerClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jCmbFrom;
    private javax.swing.JComboBox jCmbTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnDelete;
    private javax.swing.JButton jbtnEdit;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JComboBox jcmbClass;
    private javax.swing.JComboBox jcmbItem;
    private javax.swing.JComboBox jcmbMaterial;
    private javax.swing.JComboBox jcmbSchool;
    private javax.swing.JTable jtblMaterial;
    private javax.swing.JTextField jtxtSchoolLength;
    private javax.swing.JTextField jtxtTailorLength;
    // End of variables declaration//GEN-END:variables
}
