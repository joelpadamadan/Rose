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
public class JIFraAddExpense extends javax.swing.JInternalFrame {

    Connection con;
    Statement stmt;
    ResultSet rs;
     
    String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    String query, text;
    DefaultTableModel model1;
    int rowcount = 0;

    public JIFraAddExpense() {
        initComponents();
        model1 = (DefaultTableModel) jtblExpense.getModel();
        jdteDate.setDate(new Date());
        populatetable();
    }

    private void populatetable() {
        try {
            rowcount = jtblExpense.getRowCount();
            for (int i = 0; i < rowcount; i++) {
                model1.removeRow(0);
            }
            con = new DBConnection().connect();
            stmt = con.createStatement();
            query = "select sl_no,expense,amount,expense_date from mst_expense where flag!= 'D' and year(date_created)='" + Global.year + "' order by sl_no desc";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                rowcount = jtblExpense.getRowCount();
                model1.addRow(new Object[]{""});
                jtblExpense.setValueAt(rs.getString(1), rowcount, 0);
                jtblExpense.setValueAt(rs.getString(2), rowcount, 1);
                jtblExpense.setValueAt(rs.getString(3), rowcount, 2);
                jtblExpense.setValueAt(rs.getString(4), rowcount, 3);
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
        jLabel1 = new javax.swing.JLabel();
        jtxtExpense = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtxtAmount = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jdteDate = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblExpense = new javax.swing.JTable();
        jbtnAdd = new javax.swing.JButton();

        setClosable(true);
        setTitle("Add Expense");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Expense");

        jtxtExpense.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtExpenseKeyPressed(evt);
            }
        });

        jLabel2.setText("Amount");

        jtxtAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtAmountKeyPressed(evt);
            }
        });

        jLabel3.setText("Date");

        jtblExpense.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Expense", "Amount", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtblExpense);
        jtblExpense.getColumnModel().getColumn(0).setMinWidth(0);
        jtblExpense.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtblExpense.getColumnModel().getColumn(0).setMaxWidth(0);
        jtblExpense.getColumnModel().getColumn(2).setMinWidth(120);
        jtblExpense.getColumnModel().getColumn(2).setPreferredWidth(120);
        jtblExpense.getColumnModel().getColumn(2).setMaxWidth(120);
        jtblExpense.getColumnModel().getColumn(3).setMinWidth(90);
        jtblExpense.getColumnModel().getColumn(3).setPreferredWidth(90);
        jtblExpense.getColumnModel().getColumn(3).setMaxWidth(90);

        jbtnAdd.setText("ADD");
        jbtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtExpense, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdteDate, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnAdd)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdteDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jtxtExpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(jtxtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbtnAdd)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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

    private void jbtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddActionPerformed
        if (jtxtExpense.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter an expense", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtxtAmount.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter an amount", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jdteDate.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please enter a date", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                con = new DBConnection().connect();
                stmt = con.createStatement();
                query = "INSERT INTO mst_expense (EXPENSE, AMOUNT, EXPENSE_DATE, FLAG, DATE_CREATED) VALUES ('" + jtxtExpense.getText() + "', '" + jtxtAmount.getText() + "', '" + new SimpleDateFormat("yyyy-MM-dd").format(jdteDate.getDate()) + "', 'I', '" + today + "')";
                int i = stmt.executeUpdate(query);
                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Added successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    populatetable();
                    jtxtAmount.setText("");
                    jtxtExpense.setText("");
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

    }//GEN-LAST:event_jbtnAddActionPerformed

    private void jtxtAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtAmountKeyPressed
        text = "";
        text = jtxtAmount.getText();
        if (text.length() == 9) {
            jtxtAmount.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtAmount.setEditable(true);
            }
        } else if (evt.getKeyChar() >= evt.VK_0 && evt.getKeyChar() <= evt.VK_9 || evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT || evt.getKeyChar() == evt.VK_PERIOD) {
            jtxtAmount.setEditable(true);
        } else {
            jtxtAmount.setEditable(false);
        }
    }//GEN-LAST:event_jtxtAmountKeyPressed

    private void jtxtExpenseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtExpenseKeyPressed
        text = jtxtExpense.getText();
        if (text.length() == 30) {
            jtxtExpense.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtExpense.setEditable(true);
            }
        } else {
            jtxtExpense.setEditable(true);
        }
    }//GEN-LAST:event_jtxtExpenseKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnAdd;
    private com.toedter.calendar.JDateChooser jdteDate;
    private javax.swing.JTable jtblExpense;
    private javax.swing.JTextField jtxtAmount;
    private javax.swing.JTextField jtxtExpense;
    // End of variables declaration//GEN-END:variables
}
