/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rose;

import com.mysql.jdbc.PreparedStatement;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Joel
 */
public class JIFraPackingReport extends javax.swing.JInternalFrame {

    Connection con = null;
    ResultSet rs = null;
    Statement stmt = null;
    String query = null, dd;
    PreparedStatement pstmt = null;
    String projectpath;
     

    public JIFraPackingReport() {

        initComponents();
        try {
            projectpath = new File(".").getCanonicalPath();
            con = new DBConnection().connect();
            query = "select distinct(delivery_date) from trn_packing_list where flag!='D' and year(date_created)='" + Global.year + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            jcmbDeliveryDate.addItem("");
            while (rs.next()) {
                jcmbDeliveryDate.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());

        } catch (IOException ex) {
            Logger.getLogger(JIFraPackingReport.class.getName()).log(Level.SEVERE, null, ex);
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
        jcmbDeliveryDate = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();

        setClosable(true);
        setTitle("Packing List Report");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Delivery Dates");

        jButton1.setText("Retrieve");
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
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcmbDeliveryDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addContainerGap(347, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcmbDeliveryDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDesktopPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jcmbDeliveryDate.getSelectedItem() == null || jcmbDeliveryDate.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a delivery date", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                dd = jcmbDeliveryDate.getSelectedItem().toString();
                HashMap Param = new HashMap();
                Param.put("dd", dd);
                JIFraNewReportViewer viewer = new JIFraNewReportViewer(projectpath + "/Reports/PackingList.jrxml", Param);
                jDesktopPane1.removeAll();
                jDesktopPane1.add(viewer);
                viewer.setVisible(true);
                viewer.setMaximum(true);
            } catch (PropertyVetoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox jcmbDeliveryDate;
    // End of variables declaration//GEN-END:variables
}