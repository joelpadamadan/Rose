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

/**
 *
 * @author Smitticus
 */
public class JIFraSetSchoolName extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFraSetSchoolName
     */
    Connection con = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    Statement stmt = null;
    String query = null;
    String[] school;
     

    public JIFraSetSchoolName() {
        try {
            initComponents();
            con = new DBConnection().connect();
            query = "select school_no,school_name from mst_school where flag != 'D' and year(date_created)='"+Global.year+"'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            jcmbSchoolName.addItem("");
            while (rs.next()) {
                jcmbSchoolName.addItem(rs.getString(1) + "   " + rs.getString(2));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
                if (!stmt.isClosed()) {
                    stmt.close();
                }
                if (!con.isClosed()) {
                    con.close();
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
        jcmbSchoolName = new javax.swing.JComboBox();
        jbtnSelect = new javax.swing.JButton();

        setClosable(true);
        setTitle("Select School");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("School Name:");

        jbtnSelect.setText("Select");
        jbtnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSelectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jcmbSchoolName, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jbtnSelect)
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcmbSchoolName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnSelect))
                .addContainerGap(12, Short.MAX_VALUE))
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
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSelectActionPerformed
       
            if (jcmbSchoolName.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(this, "Please select a school name", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                school = jcmbSchoolName.getSelectedItem().toString().split("   ");
                Global.setSchoolno(school[0]);
                Global.setSchoolname(school[1]);
                TransactionForm main = (TransactionForm) this.getParent().getParent().getParent().getParent().getParent();
                main.setTitle(Global.schoolname);
                this.dispose();
            }       
            
  
    }//GEN-LAST:event_jbtnSelectActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnSelect;
    private javax.swing.JComboBox jcmbSchoolName;
    // End of variables declaration//GEN-END:variables
}