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
 * @author Joel
 */
public class JIFraAddClass extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFraAddClass
     */
    String text;
    DefaultTableModel model1;
    Connection con = null;
    ResultSet rs = null;
    Statement stmt = null;
    String query = "";
    int rowcount = 0;
    String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    public JIFraAddClass() {
        initComponents();
        model1 = (DefaultTableModel) jTblClass.getModel();
        populatetable();
    }

    private void populatetable() {
        try {
            con = new DBConnection().connect();
            stmt = con.createStatement();
            rowcount = jTblClass.getRowCount();
            for (int i = 0; i < rowcount; i++) {
                model1.removeRow(0);
            }
            query = "select class from mst_class where flag != 'D' order by class*1";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                rowcount = jTblClass.getRowCount();
                model1.addRow(new Object[]{});
                jTblClass.setValueAt(rs.getString(1), rowcount, 0);
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

        jLabel1 = new javax.swing.JLabel();
        jTxtClass = new javax.swing.JTextField();
        jBtnClass = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblClass = new javax.swing.JTable();

        setClosable(true);
        setTitle("Add Class");

        jLabel1.setText("Class");

        jTxtClass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtClassKeyPressed(evt);
            }
        });

        jBtnClass.setText("Add");
        jBtnClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnClassActionPerformed(evt);
            }
        });

        jTblClass.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Class"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTblClass);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtClass, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnClass)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTxtClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnClass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnClassActionPerformed
        if (jTxtClass.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a class to add", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                String ClassName = jTxtClass.getText().trim();
                con = new DBConnection().connect();
                con.setAutoCommit(false);
                stmt = con.createStatement();
                query = "select class_no from mst_class where class = '" + ClassName + "' and flag!='D' ";
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Class already exists", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    query = "insert into mst_class(class,flag,date_created) values ('" + ClassName + "','I','" + today + "')";
                    int r = stmt.executeUpdate(query);
                    if (r > 0) {
                        JOptionPane.showMessageDialog(this, "Added Successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        con.commit();
                        populatetable();
                        jTxtClass.setText("");
                        jTxtClass.grabFocus();
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
    }//GEN-LAST:event_jBtnClassActionPerformed

    private void jTxtClassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtClassKeyPressed
        text = jTxtClass.getText();
        if (text.length() == 15) {
            jTxtClass.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jTxtClass.setEditable(true);
            }
        } else {
            jTxtClass.setEditable(true);
        }
    }//GEN-LAST:event_jTxtClassKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnClass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblClass;
    private javax.swing.JTextField jTxtClass;
    // End of variables declaration//GEN-END:variables
}
