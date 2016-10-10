/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rose;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Joel
 */
public class JIFraAddSchool extends javax.swing.JInternalFrame {

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    String query, text;
     
    String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    public JIFraAddSchool() {
        initComponents();
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
        jtxtSchool = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtaddress = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jtxtphoneno = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Create School");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("School");

        jtxtSchool.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtSchoolKeyPressed(evt);
            }
        });

        jLabel2.setText("Address");

        jtxtaddress.setColumns(20);
        jtxtaddress.setRows(5);
        jtxtaddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtaddressKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jtxtaddress);

        jLabel3.setText("Phone Number");

        jtxtphoneno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtphonenoKeyPressed(evt);
            }
        });

        jButton1.setText("Add");
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtphoneno, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtSchool, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtSchool, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxtphoneno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(23, Short.MAX_VALUE))
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
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jtxtSchool.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a school name", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtxtaddress.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a school address", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtxtphoneno.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a school phone number", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                con = new DBConnection().connect();
                con.setAutoCommit(false);
                query = "INSERT INTO mst_school (SCHOOL_NAME, SCHOOL_YEAR, FLAG,DATE_CREATED,SCHOOL_ADDRESS,SCHOOL_PHONENO) VALUES ( ? , ? , ? , ? , ? , ? )";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, jtxtSchool.getText().trim());
                pstmt.setString(2, Global.year);
                pstmt.setString(3, "I");
                pstmt.setString(4, today);
                pstmt.setString(5, jtxtaddress.getText().trim());
                pstmt.setString(6, jtxtphoneno.getText().trim());
                int i = pstmt.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Added successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    con.commit();
                    jtxtSchool.setText("");
                    jtxtaddress.setText("");
                    jtxtphoneno.setText("");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            } finally {
                try {
                    if (!con.isClosed()) {
                        con.close();
                    }
                    if (!pstmt.isClosed()) {
                        pstmt.isClosed();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtxtSchoolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSchoolKeyPressed
        text = jtxtSchool.getText();
        if (text.length() == 40) {
            jtxtSchool.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtSchool.setEditable(true);
            }
        } else {
            jtxtSchool.setEditable(true);
        }
    }//GEN-LAST:event_jtxtSchoolKeyPressed

    private void jtxtaddressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtaddressKeyPressed
        text = jtxtaddress.getText();
        if (text.length() == 50) {
            jtxtaddress.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtaddress.setEditable(true);
            }
        } else {
            jtxtaddress.setEditable(true);
        }
    }//GEN-LAST:event_jtxtaddressKeyPressed

    private void jtxtphonenoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtphonenoKeyPressed
        text = jtxtphoneno.getText();
        if (text.length() == 12) {
            jtxtphoneno.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtphoneno.setEditable(true);
            }
        } else {
            jtxtphoneno.setEditable(true);
        }
    }//GEN-LAST:event_jtxtphonenoKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtxtSchool;
    private javax.swing.JTextArea jtxtaddress;
    private javax.swing.JTextField jtxtphoneno;
    // End of variables declaration//GEN-END:variables
}