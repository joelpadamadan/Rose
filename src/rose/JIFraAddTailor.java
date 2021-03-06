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
public class JIFraAddTailor extends javax.swing.JInternalFrame {

    /**
     * Creates new form jIFraCuttingMasterDetails
     */
    String text;
    DefaultTableModel model1;
    Connection con = null;
    ResultSet rs = null;
    Statement stmt = null;
    String query = null;
    int rowcount = 0;
     

    public JIFraAddTailor() {
        initComponents();
        model1 = (DefaultTableModel) jtblTailor.getModel();
        populatetable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblTailor = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jtxtTailor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTxtTailorCode = new javax.swing.JTextField();
        jbtnSubmit = new javax.swing.JButton();

        setClosable(true);
        setTitle("Add Tailor");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jtblTailor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tailor Code", "Tailor Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtblTailor);
        jtblTailor.getColumnModel().getColumn(0).setMaxWidth(75);

        jLabel4.setText("Tailor");

        jtxtTailor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtTailorKeyPressed(evt);
            }
        });

        jLabel2.setText("Tailor Code");

        jTxtTailorCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtTailorCodeKeyPressed(evt);
            }
        });

        jbtnSubmit.setText("Add");
        jbtnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtTailor, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTxtTailorCode, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 43, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtxtTailor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTxtTailorCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnSubmit))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtTailorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtTailorKeyPressed
        text = jtxtTailor.getText();
        if (text.length() == 30) {
            jtxtTailor.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtTailor.setEditable(true);
            }
        } else {
            jtxtTailor.setEditable(true);
        }
    }//GEN-LAST:event_jtxtTailorKeyPressed
    private void populatetable() {
        try {
            rowcount = 0;
            con = new DBConnection().connect();
            stmt = con.createStatement();
            rowcount = jtblTailor.getRowCount();
            for (int i = 0; i < rowcount; i++) {
                model1.removeRow(0);
            }
            query = "select tailor_code,tailor_name from mst_tailor where flag != 'D' and year(date_created)='" + Global.year + "' order by tailor_no desc";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                rowcount = jtblTailor.getRowCount();
                model1.addRow(new Object[]{""});
                jtblTailor.setValueAt(rs.getString(1), rowcount, 0);
                jtblTailor.setValueAt(rs.getString(2), rowcount, 1);
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
    private void jbtnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSubmitActionPerformed
        try {
            if (jtxtTailor.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter tailor name to proceed", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else if (jTxtTailorCode.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter tailor code to proceed", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int i = 0;
                con = new DBConnection().connect();
                con.setAutoCommit(false);
                stmt = con.createStatement();
                query = "select * from mst_tailor where tailor_code = '" + jTxtTailorCode.getText().trim() + "' and tailor_name = '" + jtxtTailor.getText().trim() + "' and flag!='D' and year(date_created)='" + Global.year + "'";
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "This tailor name with tailor code already exist", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    query = "insert into mst_tailor(tailor_code,tailor_name,flag,date_created) values('" + jTxtTailorCode.getText().trim().toUpperCase() + "','" + jtxtTailor.getText().trim() + "','I','" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "')";
                    i = stmt.executeUpdate(query);
                }
                if (i > 0) {
                    con.commit();
                    JOptionPane.showMessageDialog(this, "Added Successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
                    jtxtTailor.setText("");
                    jTxtTailorCode.setText("");
                    jtxtTailor.grabFocus();
                    populatetable();
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
    }//GEN-LAST:event_jbtnSubmitActionPerformed

    private void jTxtTailorCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtTailorCodeKeyPressed
        text = jTxtTailorCode.getText();
        if (text.length() == 2) {
            jTxtTailorCode.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jTxtTailorCode.setEditable(true);
            }
        } else {
            jTxtTailorCode.setEditable(true);
        }
    }//GEN-LAST:event_jTxtTailorCodeKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTxtTailorCode;
    private javax.swing.JButton jbtnSubmit;
    private javax.swing.JTable jtblTailor;
    private javax.swing.JTextField jtxtTailor;
    // End of variables declaration//GEN-END:variables
}
