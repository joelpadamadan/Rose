/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rose;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Betsy
 */
public class JIFraMViewAllStudent extends javax.swing.JInternalFrame {

    Connection con;
    Statement stmt1, stmt2;
    ResultSet rs1, rs2;
    String query, classno;
    String[] schoolno;
    DefaultTableModel model1;
    int stsl = 0, rowcount = 0, slno = 0;
    float gt = 0, material_total = 0, item_total = 0;
    String today = new SimpleDateFormat("dd-MM-yy").format(new Date());
    boolean check = false;
     

    public JIFraMViewAllStudent() {
        try {
            initComponents();
            model1 = (DefaultTableModel) jtblStudent.getModel();
            jcmbGender.addItem("");
            jcmbGender.addItem("Male");
            jcmbGender.addItem("Female");
            con = new DBConnection().connect();
            stmt1 = con.createStatement();
            query = "select school_no,school_name from mst_school where flag !='D' and year(date_created)='" + Global.year + "'";
            rs1 = stmt1.executeQuery(query);
            jcmbSchool.removeAllItems();
            jcmbSchool.addItem("");
            while (rs1.next()) {
                jcmbSchool.addItem(rs1.getString(1) + "   " + rs1.getString(2));
            }
            jcmbFontSize.setSelectedItem("14");
            jtblStudent.setAutoCreateRowSorter(true);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            jtblStudent.getTableHeader().setDefaultRenderer(rightRenderer);
            jtblStudent.getColumn(jtblStudent.getColumnName(0)).setCellRenderer(rightRenderer);
            jtblStudent.getColumn(jtblStudent.getColumnName(1)).setCellRenderer(rightRenderer);
            jtblStudent.getColumn(jtblStudent.getColumnName(2)).setCellRenderer(rightRenderer);
            jtblStudent.getColumn(jtblStudent.getColumnName(3)).setCellRenderer(rightRenderer);
            jtblStudent.getColumn(jtblStudent.getColumnName(4)).setCellRenderer(rightRenderer);
            jtblStudent.getColumn(jtblStudent.getColumnName(5)).setCellRenderer(rightRenderer);
            jtblStudent.getColumn(jtblStudent.getColumnName(6)).setCellRenderer(rightRenderer);
            jtblStudent.getColumn(jtblStudent.getColumnName(7)).setCellRenderer(rightRenderer);
            jtblStudent.getColumn(jtblStudent.getColumnName(8)).setCellRenderer(rightRenderer);
            jtblStudent.setShowGrid(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            try {
                if (!rs1.isClosed()) {
                    rs1.close();
                }
                if (!con.isClosed()) {
                    con.close();
                }
                if (!stmt1.isClosed()) {
                    stmt1.isClosed();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }

    private void generatetable(String mainquery) {
        try {
            con = new DBConnection().connect();
            stmt1 = con.createStatement();
            stmt2 = con.createStatement();
            query = mainquery;
            rs1 = stmt1.executeQuery(query);
            slno = 1;
            while (rs1.next()) {
                material_total = 0;
                item_total = 0;
                gt = 0;
                stsl = rs1.getInt(1);
                check = false;
//                query = "select * from trn_cm where stsl_no = '" + stsl + "' and flag!='D'";
//                rs2 = stmt2.executeQuery(query);
//                if (!rs2.next()) {
//                    check = true;
//                }
//                query = "select * from trn_cm where stsl_no = '" + stsl + "' and status = 'P' and flag!='D'";
//                rs2 = stmt2.executeQuery(query);
//                if (rs2.next()) {
//                    check = true;
//                }
//                query = "select * from trn_stitching where stsl_no = '" + stsl + "' and flag!='D'";
//                rs2 = stmt2.executeQuery(query);
//                if (!rs2.next()) {
//                    check = true;
//                }
//                query = "select * from trn_stitching where stsl_no = '" + stsl + "' and status = 'P' and flag!='D'";
//                rs2 = stmt2.executeQuery(query);
//                if (rs2.next()) {
//                    check = true;
//                }
//                query = "select * from trn_buttonhole where stsl_no = '" + stsl + "' and flag!='D'";
//                rs2 = stmt2.executeQuery(query);
//                if (!rs2.next()) {
//                    check = true;
//                }
//                query = "select * from trn_buttonhole where stsl_no = '" + stsl + "' and status = 'P' and flag!='D'";
//                rs2 = stmt2.executeQuery(query);
//                if (rs2.next()) {
//                    check = true;
//                }
//                query = "select * from trn_ironing where stsl_no = '" + stsl + "' and flag!='D'";
//                rs2 = stmt2.executeQuery(query);
//                if (!rs2.next()) {
//                    check = true;
//                }
//                query = "select * from trn_ironing where stsl_no = '" + stsl + "' and status = 'P' and flag!='D'";
//                rs2 = stmt2.executeQuery(query);
//                if (rs2.next()) {
//                    check = true;
//                }
                if (!check) {
                    query = "select c.material_name,a.fixed_mat_length,b.item_name,e.selling_price,f.qty from mst_student_mat_detail a join mst_item b on a.item_no = b.item_no join mst_material c on a.material_no = c.material_no join mst_school_mat_price e on a.material_no = e.material_no and a.item_no = e.item_no join mst_student_detail f on a.stsl_no = f.stsl_no and a.item_no = f.item_no where a.stsl_no =  '" + stsl + "' and e.school_no = '" + schoolno[0] + "' and a.flag!='D' and b.flag!='D' and c.flag!='D' and e.flag!='D' and f.flag!='D'";
                    rs2 = stmt2.executeQuery(query);
                    while (rs2.next()) {
                        material_total = material_total + (rs2.getFloat(2) * rs2.getFloat(4)) * rs2.getInt(5);
                    }
                    query = "select a.item_no,a.qty,b.item_name,d.selling_price from mst_student_detail a join mst_item b on a.item_no = b.item_no join mst_school_item_price d on a.item_no = d.item_no where a.stsl_no = '" + stsl + "' and d.school_no = '" + schoolno[0] + "' and a.flag!='D' and b.flag!='D' and d.flag!='D'";
                    rs2 = stmt2.executeQuery(query);
                    while (rs2.next()) {
                        item_total = item_total + (rs2.getFloat(2) * rs2.getFloat(4));
                    }
                    material_total = Global.round(material_total);
                    item_total = Global.round(item_total);
                    gt = material_total + item_total;
                    model1.addRow(new Object[]{});
                    jtblStudent.setValueAt(slno, model1.getRowCount() - 1, 0);
                    jtblStudent.setValueAt(rs1.getString(2), model1.getRowCount() - 1, 1);
                    jtblStudent.setValueAt(rs1.getString(3), model1.getRowCount() - 1, 2);
                    jtblStudent.setValueAt(rs1.getString(4), model1.getRowCount() - 1, 3);
                    jtblStudent.setValueAt(rs1.getString(5), model1.getRowCount() - 1, 4);
                    jtblStudent.setValueAt(rs1.getString(6), model1.getRowCount() - 1, 5);
                    jtblStudent.setValueAt(material_total, model1.getRowCount() - 1, 6);
                    jtblStudent.setValueAt(item_total, model1.getRowCount() - 1, 7);
                    jtblStudent.setValueAt(gt, model1.getRowCount() - 1, 8);
                    slno++;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } finally {
            try {
                if (!rs1.isClosed()) {
                    rs1.close();
                }
                if (!con.isClosed()) {
                    con.close();
                }
                if (!stmt1.isClosed()) {
                    stmt1.isClosed();
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
        jbtnView = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblStudent = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jcmbClass = new javax.swing.JComboBox();
        jcmbGender = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jcmbFontSize = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jcmbDivision = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jcmbSchool = new javax.swing.JComboBox();

        setClosable(true);
        setTitle("View All Student");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jbtnView.setText("View");
        jbtnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnViewActionPerformed(evt);
            }
        });

        jtblStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sl.", "Order No.", "Name", "Class", "Division", "Gender", "Material Total", "Item Total", "Grand Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtblStudent);
        jtblStudent.getColumnModel().getColumn(0).setMinWidth(40);
        jtblStudent.getColumnModel().getColumn(0).setPreferredWidth(40);
        jtblStudent.getColumnModel().getColumn(0).setMaxWidth(40);
        jtblStudent.getColumnModel().getColumn(1).setPreferredWidth(20);
        jtblStudent.getColumnModel().getColumn(3).setPreferredWidth(30);
        jtblStudent.getColumnModel().getColumn(4).setPreferredWidth(30);
        jtblStudent.getColumnModel().getColumn(5).setPreferredWidth(70);
        jtblStudent.getColumnModel().getColumn(6).setPreferredWidth(80);
        jtblStudent.getColumnModel().getColumn(7).setPreferredWidth(80);
        jtblStudent.getColumnModel().getColumn(8).setPreferredWidth(80);

        jLabel2.setText("Class");

        jcmbClass.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbClassItemStateChanged(evt);
            }
        });

        jLabel3.setText("Gender");

        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jcmbFontSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "8", "10", "12", "14", "16", "18", "20", "22", "24", "26" }));

        jLabel1.setText("Font Size");

        jLabel4.setText("Division");

        jLabel5.setText("School Name :");

        jcmbSchool.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbSchoolItemStateChanged(evt);
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
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbDivision, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcmbFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jbtnView)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcmbSchool, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jcmbSchool, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jcmbFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jcmbDivision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtnView)
                        .addComponent(jLabel2)
                        .addComponent(jcmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
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
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnViewActionPerformed
        if (jcmbSchool.getSelectedItem() == null || jcmbSchool.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a school", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            schoolno = jcmbSchool.getSelectedItem().toString().split("   ");
            jtblStudent.setFont(new Font("Tahoma", Font.PLAIN, Integer.parseInt(jcmbFontSize.getSelectedItem().toString())));
            jtblStudent.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, Integer.parseInt(jcmbFontSize.getSelectedItem().toString())));
            jtblStudent.setRowHeight(Integer.parseInt(jcmbFontSize.getSelectedItem().toString()) + 5);
            rowcount = jtblStudent.getRowCount();
            for (int i = 0; i < rowcount; i++) {
                model1.removeRow(0);
            }
            classno = "";
            try {
                con = new DBConnection().connect();
                stmt1 = con.createStatement();
                query = "select class_no from mst_class where class='" + jcmbClass.getSelectedItem() + "' and flag!='D'";
                rs1 = stmt1.executeQuery(query);
                if (rs1.next()) {
                    classno = rs1.getString(1);
                }
                if (jcmbDivision.getSelectedItem() != null && !jcmbDivision.getSelectedItem().equals("")) {
                    if ((jcmbClass.getSelectedItem() == null || jcmbClass.getSelectedItem().equals("")) && (jcmbGender.getSelectedItem() == null || jcmbGender.getSelectedItem().equals(""))) {
                        generatetable("select a.stsl_no,a.order_no,a.student_name,b.class,a.division,a.gender from mst_student a join mst_class b on a.class_no = b.class_no where school_no = '" + schoolno[0] + "' and a.division = '" + jcmbDivision.getSelectedItem() + "' and a.flag != 'D' and b.flag != 'D'");
                    } else if ((jcmbClass.getSelectedItem() != null || !jcmbClass.getSelectedItem().equals("")) && (jcmbGender.getSelectedItem() == null || jcmbGender.getSelectedItem().equals(""))) {
                        //class
                        generatetable("select a.stsl_no,a.order_no,a.student_name,b.class,a.division,a.gender from mst_student a join mst_class b on a.class_no = b.class_no where school_no = '" + schoolno[0] + "'  and a.division = '" + jcmbDivision.getSelectedItem() + "' and a.class_no = '" + classno + "' and a.flag != 'D' and b.flag != 'D'");
                    } else if ((jcmbClass.getSelectedItem() == null || jcmbClass.getSelectedItem().equals("")) && (jcmbGender.getSelectedItem() != null || !jcmbGender.getSelectedItem().equals(""))) {
                        //gender
                        generatetable("select a.stsl_no,a.order_no,a.student_name,b.class,a.division,a.gender from mst_student a join mst_class b on a.class_no = b.class_no where school_no = '" + schoolno[0] + "'  and a.division = '" + jcmbDivision.getSelectedItem() + "' and a.gender = '" + jcmbGender.getSelectedItem() + "' and a.flag != 'D' and b.flag != 'D'");
                    } else if ((jcmbClass.getSelectedItem() != null || !jcmbClass.getSelectedItem().equals("")) && (jcmbGender.getSelectedItem() != null || !jcmbGender.getSelectedItem().equals(""))) {
                        //class
                        //gender
                        generatetable("select a.stsl_no,a.order_no,a.student_name,b.class,a.division,a.gender from mst_student a join mst_class b on a.class_no = b.class_no where school_no = '" + schoolno[0] + "'  and a.division = '" + jcmbDivision.getSelectedItem() + "' and a.class_no = '" + classno + "' and a.gender = '" + jcmbGender.getSelectedItem() + "' and a.flag != 'D' and b.flag != 'D'");
                    }
                } else {
                    if ((jcmbClass.getSelectedItem() == null || jcmbClass.getSelectedItem().equals("")) && (jcmbGender.getSelectedItem() == null || jcmbGender.getSelectedItem().equals(""))) {
                        generatetable("select a.stsl_no,a.order_no,a.student_name,b.class,a.division,a.gender from mst_student a join mst_class b on a.class_no = b.class_no where school_no = '" + schoolno[0] + "' and a.flag != 'D' and b.flag != 'D'");
                    } else if ((jcmbClass.getSelectedItem() != null || !jcmbClass.getSelectedItem().equals("")) && (jcmbGender.getSelectedItem() == null || jcmbGender.getSelectedItem().equals(""))) {
                        //class
                        generatetable("select a.stsl_no,a.order_no,a.student_name,b.class,a.division,a.gender from mst_student a join mst_class b on a.class_no = b.class_no where school_no = '" + schoolno[0] + "' and a.class_no = '" + classno + "' and a.flag != 'D' and b.flag != 'D'");
                    } else if ((jcmbClass.getSelectedItem() == null || jcmbClass.getSelectedItem().equals("")) && (jcmbGender.getSelectedItem() != null || !jcmbGender.getSelectedItem().equals(""))) {
                        //gender
                        generatetable("select a.stsl_no,a.order_no,a.student_name,b.class,a.division,a.gender from mst_student a join mst_class b on a.class_no = b.class_no where school_no = '" + schoolno[0] + "' and a.gender = '" + jcmbGender.getSelectedItem() + "' and a.flag != 'D' and b.flag != 'D'");
                    } else if ((jcmbClass.getSelectedItem() != null || !jcmbClass.getSelectedItem().equals("")) && (jcmbGender.getSelectedItem() != null || !jcmbGender.getSelectedItem().equals(""))) {
                        //class
                        //gender
                        generatetable("select a.stsl_no,a.order_no,a.student_name,b.class,a.division,a.gender from mst_student a join mst_class b on a.class_no = b.class_no where school_no = '" + schoolno[0] + "' and a.class_no = '" + classno + "' and a.gender = '" + jcmbGender.getSelectedItem() + "' and a.flag != 'D' and b.flag != 'D'");
                    }
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            } finally {
                try {
                    if (!rs1.isClosed()) {
                        rs1.close();
                    }
                    if (!con.isClosed()) {
                        con.close();
                    }
                    if (!stmt1.isClosed()) {
                        stmt1.isClosed();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }

    }//GEN-LAST:event_jbtnViewActionPerformed

    private void jcmbClassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbClassItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (jcmbClass.getSelectedItem() != null && !jcmbClass.getSelectedItem().equals("")) {
                try {
                    con = new DBConnection().connect();
                    stmt1 = con.createStatement();
                    query = "select distinct(a.division) from mst_student a join mst_class b on a.class_no = b.class_no where b.class = '" + jcmbClass.getSelectedItem() + "' and a.school_no = '" + schoolno[0] + "' and a.flag!='D' and b.flag!='D'";
                    rs1 = stmt1.executeQuery(query);
                    jcmbDivision.addItem("");
                    while (rs1.next()) {
                        jcmbDivision.addItem(rs1.getString(1));
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                } finally {
                    try {
                        if (!rs1.isClosed()) {
                            rs1.close();
                        }
                        if (!con.isClosed()) {
                            con.close();
                        }
                        if (!stmt1.isClosed()) {
                            stmt1.isClosed();
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }
                }
            } else {
                jcmbDivision.removeAllItems();
            }
        }
    }//GEN-LAST:event_jcmbClassItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jtblStudent.getRowCount() > 0) {
            MessageFormat header = new MessageFormat("Student Bill Detail - " + Global.getSchoolname() + "   " + today);
            MessageFormat footer = new MessageFormat("Page {0,number,integer}");
            try {
                jtblStudent.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            } catch (java.awt.print.PrinterException e) {
                System.err.format("Cannot print %s%n", e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jcmbSchoolItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbSchoolItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (jcmbSchool.getSelectedItem() == null || jcmbSchool.getSelectedItem().equals("")) {
                jcmbClass.removeAllItems();
            } else {
                try {
                    schoolno = jcmbSchool.getSelectedItem().toString().split("   ");
                    con = new DBConnection().connect();
                    stmt1 = con.createStatement();
                    query = "select distinct(a.class_no),b.class from mst_student a join mst_class b on a.class_no = b.class_no where a.flag != 'D' and b.flag != 'D' and school_no = '" + schoolno[0] + "' ";
                    rs1 = stmt1.executeQuery(query);
                    jcmbClass.removeAllItems();
                    jcmbClass.addItem("");
                    while (rs1.next()) {
                        jcmbClass.addItem(rs1.getString(2));
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                } finally {
                    try {
                        if (!rs1.isClosed()) {
                            rs1.close();
                        }
                        if (!con.isClosed()) {
                            con.close();
                        }
                        if (!stmt1.isClosed()) {
                            stmt1.isClosed();
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }
                }
            }
        }
    }//GEN-LAST:event_jcmbSchoolItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnView;
    private javax.swing.JComboBox jcmbClass;
    private javax.swing.JComboBox jcmbDivision;
    private javax.swing.JComboBox jcmbFontSize;
    private javax.swing.JComboBox jcmbGender;
    private javax.swing.JComboBox jcmbSchool;
    private javax.swing.JTable jtblStudent;
    // End of variables declaration//GEN-END:variables
}
