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
 * @author Betsy
 */
public class JIFraAddMaterialConsumptionPerClass extends javax.swing.JInternalFrame {

    Connection con = null;
    ResultSet rs = null;
    Statement stmt = null;
    String query, text, item_no, class_no;
    String[] school_no, material_no;
    int rowcount = 0;
    DefaultTableModel model1;
    String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
     

    public JIFraAddMaterialConsumptionPerClass() {
        try {
            initComponents();
            model1 = (DefaultTableModel) jtblMaterial.getModel();
            con = new DBConnection().connect();
            stmt = con.createStatement();
            query = "select school_no,school_name from mst_school where flag != 'D' and year(date_created)='"+Global.year+"' ";
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
        jbtnAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblMaterial = new javax.swing.JTable();

        setClosable(true);
        setTitle("Add Material Consumption Classwise");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("School");

        jcmbSchool.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbSchoolItemStateChanged(evt);
            }
        });

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

        jbtnAdd.setText("Add");
        jbtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddActionPerformed(evt);
            }
        });

        jtblMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "School", "Class", "Item", "Material", "Tailor Length", "School Length"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtblMaterial);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcmbSchool, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcmbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtTailorLength, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtSchoolLength, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(jbtnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(12, 12, 12)
                                .addComponent(jcmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcmbItem, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcmbSchool, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jcmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jcmbItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jcmbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jtxtTailorLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jtxtSchoolLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnAdd))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jcmbSchoolItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbSchoolItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!jcmbSchool.getSelectedItem().equals("")) {
                school_no = jcmbSchool.getSelectedItem().toString().split("   ");
                populate();
            }
        }
    }//GEN-LAST:event_jcmbSchoolItemStateChanged

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

    private void jcmbClassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbClassItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!jcmbSchool.getSelectedItem().equals("") && !jcmbClass.getSelectedItem().equals("")) {
                try {
                    school_no = jcmbSchool.getSelectedItem().toString().split("   ");
                    con = new DBConnection().connect();
                    stmt = con.createStatement();
                    query = "select b.item_name from mst_school_item_price a join mst_item b on a.item_no = b.item_no where a.school_no = '" + school_no[0] + "' and a.class_from <= '" + jcmbClass.getSelectedItem() + "' and a.class_to >='" + jcmbClass.getSelectedItem() + "' and a.flag!='D' and b.flag!='D'  and year(a.date_created)='"+Global.year+"'";
                    System.out.println(query);
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
    private void populate() {
        try {
            rowcount = jtblMaterial.getRowCount();
            for (int i = 0; i < rowcount; i++) {
                model1.removeRow(0);
            }
            con = new DBConnection().connect();
            stmt = con.createStatement();
            query = "select b.school_name,c.class,d.item_name,e.material_name,a.actual_mat_length,a.fixed_mat_length from mst_material_consumption a join mst_school b on a.school_no = b.school_no join mst_class c on a.class_no = c.class_no join mst_item d on a.item_no = d.item_no join mst_material e on a.material_no = e.material_no where a.school_no = '" + school_no[0] + "' and a.flag!='D' and b.flag!='D' and c.flag!='D' and d.flag!='D' and e.flag!='D'";
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
    private void jbtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddActionPerformed
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
                query = "select * from MST_MATERIAL_CONSUMPTION where item_no = '" + item_no + "' and material_no = '" + material_no[0] + "' and class_no = '" + class_no + "' and school_no = '" + school_no[0] + "' and flag!='D'";
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Entry already exist", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    query = "INSERT INTO MST_MATERIAL_CONSUMPTION (SCHOOL_NO,CLASS_NO,ITEM_NO,MATERIAL_NO,ACTUAL_MAT_LENGTH,FIXED_MAT_LENGTH,FLAG,DATE_CREATED) VALUES ('" + school_no[0] + "', '" + class_no + "', '" + item_no + "', '" + material_no[0] + "', '" + jtxtTailorLength.getText().trim() + "', '" + jtxtSchoolLength.getText().trim() + "', 'I', '" + today + "')";
                    int i = stmt.executeUpdate(query);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(this, "Successfully Added!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        jcmbItem.setSelectedItem("");
                        jcmbMaterial.setSelectedItem("");
                        jtxtTailorLength.setText("");
                        jtxtSchoolLength.setText("");
                        populate();
                    }
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
    }//GEN-LAST:event_jbtnAddActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnAdd;
    private javax.swing.JComboBox jcmbClass;
    private javax.swing.JComboBox jcmbItem;
    private javax.swing.JComboBox jcmbMaterial;
    private javax.swing.JComboBox jcmbSchool;
    private javax.swing.JTable jtblMaterial;
    private javax.swing.JTextField jtxtSchoolLength;
    private javax.swing.JTextField jtxtTailorLength;
    // End of variables declaration//GEN-END:variables
}