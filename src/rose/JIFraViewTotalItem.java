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
public class JIFraViewTotalItem extends javax.swing.JInternalFrame {

    Connection con;
    Statement stmt;
    Statement stmt1;
    ResultSet rs;
    ResultSet rs1;
     
    String query;
    DefaultTableModel model1;
    int rowcount = 0;
    double gt;

    public JIFraViewTotalItem() {
        try {
            initComponents();
            model1 = (DefaultTableModel) jtblProfit.getModel();
            con = new DBConnection().connect();
            stmt = con.createStatement();            
            //query = "select school_no,school_name from mst_school where school_year = '" + year + "' and flag!='D'";
            query = "select b.item_name,sum(qty) from MST_STUDENT_DETAIL a join mst_item b on a.item_no = b.item_no join mst_student c on a.stsl_no = c.stsl_no where c.school_no = '"+Global.getSchoolno()+"' and a.flag!='D' and b.flag!='D' and c.flag!='D' group by b.item_name";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                rowcount = jtblProfit.getRowCount();
                model1.addRow(new Object[]{""});
                jtblProfit.setValueAt(rs.getString(1), rowcount, 0);
                jtblProfit.setValueAt(rs.getString(2), rowcount, 1);              
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jtblProfit = new javax.swing.JTable();

        setClosable(true);
        setTitle("Total Item / School");

        jtblProfit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEM", "QTY"
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
        jScrollPane1.setViewportView(jtblProfit);
        jtblProfit.getColumnModel().getColumn(1).setMinWidth(120);
        jtblProfit.getColumnModel().getColumn(1).setPreferredWidth(120);
        jtblProfit.getColumnModel().getColumn(1).setMaxWidth(120);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtblProfit;
    // End of variables declaration//GEN-END:variables
}
