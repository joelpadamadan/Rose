/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rose;

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
public class JIFraEditCategory extends javax.swing.JInternalFrame {

    Connection con = null;
    ResultSet rs = null;
    Statement stmt = null;
    String query = null;
    DefaultTableModel model1 = null;
    String text = null;
    int rowcount = 0, excluded = 0, cat_id = 0, i = 0;
    String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    public JIFraEditCategory() {
        initComponents();
        model1 = (DefaultTableModel) jTblCategory.getModel();
        jCmbExcluded.addItem("No");
        jCmbExcluded.addItem("Yes");
        populatetable();

    }

    private void populatetable() {
        try {
            con = new DBConnection().connect();
            stmt = con.createStatement();
            rowcount = jTblCategory.getRowCount();
            for (int i = 0; i < rowcount; i++) {
                model1.removeRow(0);
            }
            query = "select cat_id,cat_name,excluded from mst_category where flag != 'D'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                rowcount = jTblCategory.getRowCount();
                model1.addRow(new Object[]{});
                jTblCategory.setValueAt(rs.getString(1), rowcount, 0);
                jTblCategory.setValueAt(rs.getString(2), rowcount, 1);
                if (rs.getString(3).equals("0")) {
                    jTblCategory.setValueAt("No", rowcount, 2);
                } else {
                    jTblCategory.setValueAt("Yes", rowcount, 2);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblCategory = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTxtCategory = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jCmbExcluded = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Edit Category");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTblCategory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Category", "Excluded"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTblCategory);
        jTblCategory.getColumnModel().getColumn(0).setMinWidth(0);
        jTblCategory.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTblCategory.getColumnModel().getColumn(0).setMaxWidth(0);
        jTblCategory.getColumnModel().getColumn(2).setMinWidth(100);
        jTblCategory.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTblCategory.getColumnModel().getColumn(2).setMaxWidth(100);

        jLabel1.setText("Category");

        jTxtCategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtCategoryKeyPressed(evt);
            }
        });

        jLabel2.setText("Excluded");

        jButton1.setText("Edit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtCategory)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCmbExcluded, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTxtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jCmbExcluded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(15, Short.MAX_VALUE))
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

    private void jTxtCategoryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtCategoryKeyPressed
        text = jTxtCategory.getText();
        if (text.length() == 15) {
            jTxtCategory.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jTxtCategory.setEditable(true);
            }
        } else {
            jTxtCategory.setEditable(true);
        }
    }//GEN-LAST:event_jTxtCategoryKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTblCategory.getRowCount() > 0) {
            if (jTblCategory.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "Please select a row from the table to edit", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                cat_id = Integer.parseInt(jTblCategory.getValueAt(jTblCategory.getSelectedRow(), 0).toString());
                jTxtCategory.setText(jTblCategory.getValueAt(jTblCategory.getSelectedRow(), 1).toString());
                jCmbExcluded.setSelectedItem(jTblCategory.getValueAt(jTblCategory.getSelectedRow(), 2));
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jTxtCategory.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a category name", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                i = 0;
                String CatName = jTxtCategory.getText().trim();
                if (jCmbExcluded.getSelectedItem().equals("Yes")) {
                    excluded = 1;
                } else {
                    excluded = 0;
                }
                con = new DBConnection().connect();
                con.setAutoCommit(false);
                stmt = con.createStatement();
                query = "update mst_category set cat_name = '" + CatName + "', excluded = '" + excluded + "', FLAG = 'U', DATE_UPDATED = '" + today + "' where cat_id = '" + cat_id + "'";
                i = stmt.executeUpdate(query);
                if (i > 0) {
                    con.commit();
                    jTxtCategory.setText("");
                    JOptionPane.showMessageDialog(this, "Added Successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    populatetable();
                    cat_id = 0;
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
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          if (jTblCategory.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row from the table to delete", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int b = JOptionPane.showConfirmDialog(this, "Do you want delete the selected category?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (b == JOptionPane.YES_OPTION) {
                try {
                    i = 0;
                    con = new DBConnection().connect();
                    con.setAutoCommit(false);
                    stmt = con.createStatement();
                    query = "update mst_category set FLAG = 'D',DATE_UPDATED = '" + today + "' where cat_id = '" + jTblCategory.getValueAt(jTblCategory.getSelectedRow(), 0) + "'";
                    i = stmt.executeUpdate(query);
                    if (i > 0) {
                        con.commit();
                        JOptionPane.showMessageDialog(this, "Deleted Successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);                        
                        populatetable();
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
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jCmbExcluded;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblCategory;
    private javax.swing.JTextField jTxtCategory;
    // End of variables declaration//GEN-END:variables
}
