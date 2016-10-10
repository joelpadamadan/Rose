/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rose;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Joel
 */
public class JIFraAddStudentDetails extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFraAddStudentDetails
     */
    Connection con = null;
    Statement stmt = null;
    Statement stmt1 = null;
    ResultSet rs = null;
    String query;
    String text;
    String Gender;
    DefaultTableModel model1;
    int selectedrow = 0, stsl = 0, classno, rowcount = 0;
    boolean check = true;
    String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
     
    double shirtlength = 0, shirthshoulder = 0, shirtchest = 0, shirtsleevelength = 0, shirtsleeveloose = 0, shirtcolar = 0, pantbottomloose = 0, pantlength = 0, pantinside = 0, pantkneelength = 0, pantkneeloose = 0, pantwaist = 0, pantseat = 0, skirtyok = 0, skirtlength = 0, skirtwaist = 0, skirtshoulder = 0, coatlength = 0, coatshoulder = 0, coatchest = 0, coatwaist = 0, coatseat = 0, pinlength = 0;

    public JIFraAddStudentDetails() {
        initComponents();
        try {
            MyTraversalPolicy policy = new MyTraversalPolicy();
            setFocusTraversalPolicy(policy);
            ButtonGroup bg1 = new ButtonGroup();
            bg1.add(jrbtnMale);
            bg1.add(jrbtnFemale);
            model1 = (DefaultTableModel) jtblItem.getModel();
            jtxtSchoolName.setText(Global.getSchoolname());
            con = new DBConnection().connect();
            stmt = con.createStatement();
            query = "select class from mst_class where flag!='D'";
            rs = stmt.executeQuery(query);
            jCmbClass.addItem("");
            while (rs.next()) {
                jCmbClass.addItem(rs.getString(1));
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

    public void populateItem(String gender, String c) {
        try {
            rowcount = jtblItem.getRowCount();
            for (int i = 0; i < rowcount; i++) {
                model1.removeRow(0);
            }
            con = new DBConnection().connect();
            stmt = con.createStatement();
            jcmbItemName.removeAllItems();
            jcmbItemName.addItem("");
            jcmbItemName.addItem("All");
            query = "select distinct(b.set_name) from mst_school_item_price a join mst_set b on a.set_no = b.set_no join mst_item c on a.item_no = c.item_no and  a.school_no = '" + Global.getSchoolno() + "' and c.gender in ('" + gender + "','Common') and a.class_from <= '" + c + "' and a.class_to >= '" + c + "'  and a.flag!='D' and b.flag!='D' and c.flag!='D'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                jcmbItemName.addItem(rs.getString(1));
            }
            query = "select a.item_no,b.item_name from mst_school_item_price a join mst_item b on a.item_no = b.item_no and  a.school_no = '" + Global.getSchoolno() + "' and b.gender in ('" + gender + "','Common') and a.class_from <= '" + c + "' and a.class_to >= '" + c + "'  and a.flag!='D' and b.flag!='D'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                jcmbItemName.addItem(rs.getString(2));
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

    public void clear() {
        jtxtStudentName.setText("");
        jTxtOrderNo.setText("");
        jCmbClass.setSelectedItem("");
        jtxtDivision.setText("");
        jtxtPhone.setText("");
        jcmbItemName.setSelectedItem("");
        jtxtQty.setText("");
        for (int i = 0; i < 6; i++) {
            jtblShirt.setValueAt(null, 0, i);
        }
        for (int i = 0; i < 7; i++) {
            jtblPant.setValueAt(null, 0, i);
        }
        for (int i = 0; i < 5; i++) {
            jtblSkirt.setValueAt(null, 0, i);
        }
        for (int i = 0; i < 5; i++) {
            jtblCoat.setValueAt(null, 0, i);
        }
        rowcount = jtblItem.getRowCount();
        for (int i = 0; i < rowcount; i++) {
            model1.removeRow(0);
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
        jtxtSchoolName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtxtStudentName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jrbtnMale = new javax.swing.JRadioButton();
        jrbtnFemale = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtxtDivision = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtxtPhone = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTxtOrderNo = new javax.swing.JTextField();
        jCmbClass = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblShirt = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblPant = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtblSkirt = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtblCoat = new javax.swing.JTable();
        jbtnSubmit = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jcmbItemName = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jtxtQty = new javax.swing.JTextField();
        jbtnAdd = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtblItem = new javax.swing.JTable();
        jbtnDelete = new javax.swing.JButton();

        setClosable(true);
        setTitle("Add Student Details");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("School Name:");

        jtxtSchoolName.setEditable(false);

        jLabel2.setText("Student Name:");

        jtxtStudentName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtStudentNameKeyPressed(evt);
            }
        });

        jLabel3.setText("Gender:");

        jrbtnMale.setText("Male");
        jrbtnMale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbtnMaleItemStateChanged(evt);
            }
        });

        jrbtnFemale.setText("Female");
        jrbtnFemale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jrbtnFemaleItemStateChanged(evt);
            }
        });

        jLabel4.setText("Class:");

        jLabel5.setText("Division:");

        jtxtDivision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtDivisionActionPerformed(evt);
            }
        });
        jtxtDivision.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtDivisionKeyPressed(evt);
            }
        });

        jLabel6.setText("Phone No.:");

        jtxtPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtPhoneKeyPressed(evt);
            }
        });

        jLabel7.setText("Order No. :");

        jTxtOrderNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtOrderNoKeyPressed(evt);
            }
        });

        jCmbClass.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCmbClassItemStateChanged(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtStudentName)
                            .addComponent(jtxtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jrbtnMale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jrbtnFemale)
                                .addGap(82, 82, 82)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtSchoolName, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtxtDivision, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtSchoolName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTxtOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jtxtStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jrbtnMale)
                    .addComponent(jrbtnFemale)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jtxtDivision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtxtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Measurement"));

        jtblShirt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Shirt Length", "Shirt Shoulder", "Shirt Chest", "Shirt Sleeve Length", "Shirt Sleeve Loose", "Shirt Collar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtblShirt);

        jLabel9.setText("Shirt");

        jLabel10.setText("Pant/Shorts");

        jtblPant.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Pant Length", "Pant Inside", "Pant Bottom Loose", "Pant Knee Length", "Pant Knee Loose", "Pant Waist", "Pant Seat"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtblPant);
        jtblPant.getColumnModel().getColumn(0).setMinWidth(80);
        jtblPant.getColumnModel().getColumn(0).setPreferredWidth(80);
        jtblPant.getColumnModel().getColumn(0).setMaxWidth(80);
        jtblPant.getColumnModel().getColumn(1).setMinWidth(80);
        jtblPant.getColumnModel().getColumn(1).setPreferredWidth(80);
        jtblPant.getColumnModel().getColumn(1).setMaxWidth(80);
        jtblPant.getColumnModel().getColumn(6).setMinWidth(70);
        jtblPant.getColumnModel().getColumn(6).setPreferredWidth(70);
        jtblPant.getColumnModel().getColumn(6).setMaxWidth(70);

        jtblSkirt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Yok", "Pinnafore Length", "Waist", "Shoulder", "Skirt Length"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jtblSkirt);

        jLabel11.setText("Skirt / Pinnafore");

        jLabel12.setText("Coat");

        jtblCoat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Coat Length", "Coat Shoulder", "Coat Chest", "Coat Waist", "Coat Seat"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jtblCoat);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 174, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jbtnSubmit.setText("SAVE");
        jbtnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSubmitActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel18.setText("Item:");

        jLabel19.setText("Quantity:");

        jtxtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtQtyKeyPressed(evt);
            }
        });

        jbtnAdd.setText("ADD");
        jbtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddActionPerformed(evt);
            }
        });

        jtblItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item no.", "Item Name", "Qty"
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
        jScrollPane5.setViewportView(jtblItem);
        jtblItem.getColumnModel().getColumn(0).setMinWidth(0);
        jtblItem.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtblItem.getColumnModel().getColumn(0).setMaxWidth(0);
        jtblItem.getColumnModel().getColumn(2).setMinWidth(40);
        jtblItem.getColumnModel().getColumn(2).setPreferredWidth(40);
        jtblItem.getColumnModel().getColumn(2).setMaxWidth(40);

        jbtnDelete.setText("DELETE");
        jbtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jbtnAdd)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnDelete))
                            .addComponent(jtxtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcmbItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jcmbItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jtxtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAdd)
                    .addComponent(jbtnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnSubmit)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnSubmit)
                        .addGap(8, 8, 8))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSubmitActionPerformed

        shirtlength = 0;
        shirthshoulder = 0;
        shirtchest = 0;
        shirtsleevelength = 0;
        shirtsleeveloose = 0;
        shirtcolar = 0;
        pantlength = 0;
        pantinside = 0;
        pantkneelength = 0;
        pantkneeloose = 0;
        pantwaist = 0;
        pantseat = 0;
        pantbottomloose = 0;
        skirtyok = 0;
        skirtlength = 0;
        skirtwaist = 0;
        skirtshoulder = 0;
        pinlength = 0;
        coatlength = 0;
        coatshoulder = 0;
        coatchest = 0;
        coatwaist = 0;
        coatseat = 0;
        int c = JOptionPane.YES_OPTION;
        if (jtblShirt.getValueAt(0, 0) == null && jtblShirt.getValueAt(0, 1) == null && jtblShirt.getValueAt(0, 2) == null && jtblShirt.getValueAt(0, 3) == null && jtblShirt.getValueAt(0, 4) == null && jtblShirt.getValueAt(0, 5) == null && jtblPant.getValueAt(0, 0) == null && jtblPant.getValueAt(0, 1) == null && jtblPant.getValueAt(0, 2) == null && jtblPant.getValueAt(0, 3) == null && jtblPant.getValueAt(0, 4) == null && jtblPant.getValueAt(0, 5) == null && jtblSkirt.getValueAt(0, 0) == null && jtblSkirt.getValueAt(0, 1) == null && jtblSkirt.getValueAt(0, 2) == null && jtblCoat.getValueAt(0, 0) == null && jtblCoat.getValueAt(0, 1) == null && jtblCoat.getValueAt(0, 2) == null && jtblCoat.getValueAt(0, 3) == null && jtblCoat.getValueAt(0, 4) == null) {
            c = JOptionPane.showConfirmDialog(this, "Measurement have not been filled do you want to continue?", "Info", JOptionPane.YES_NO_OPTION);
        }
        if (c == JOptionPane.YES_OPTION) {
            if (((jtblShirt.getValueAt(0, 0) != null) || (jtblShirt.getValueAt(0, 1) != null) || (jtblShirt.getValueAt(0, 2) != null) || (jtblShirt.getValueAt(0, 3) != null) || (jtblShirt.getValueAt(0, 4) != null) || (jtblShirt.getValueAt(0, 5) != null)) && ((jtblShirt.getValueAt(0, 0) == null) || (jtblShirt.getValueAt(0, 1) == null) || (jtblShirt.getValueAt(0, 2) == null) || (jtblShirt.getValueAt(0, 3) == null) || (jtblShirt.getValueAt(0, 4) == null) || (jtblShirt.getValueAt(0, 5) == null))) {
                JOptionPane.showMessageDialog(this, "Please fill all or none of the measurements for the shirt", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else if (((jtblPant.getValueAt(0, 0) != null) || (jtblPant.getValueAt(0, 1) != null) || (jtblPant.getValueAt(0, 2) != null) || (jtblPant.getValueAt(0, 3) != null) || (jtblPant.getValueAt(0, 4) != null) || (jtblPant.getValueAt(0, 5) != null) || (jtblPant.getValueAt(0, 6) != null)) && ((jtblPant.getValueAt(0, 0) == null) || (jtblPant.getValueAt(0, 1) == null) || (jtblPant.getValueAt(0, 2) == null) || (jtblPant.getValueAt(0, 3) == null) || (jtblPant.getValueAt(0, 4) == null) || (jtblPant.getValueAt(0, 5) == null) || (jtblPant.getValueAt(0, 6) == null))) {
                JOptionPane.showMessageDialog(this, "Please fill all or none of the measurements for the shorts/pants", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else if (((jtblSkirt.getValueAt(0, 0) != null) || (jtblSkirt.getValueAt(0, 1) != null) || (jtblSkirt.getValueAt(0, 2) != null) || (jtblSkirt.getValueAt(0, 3) != null) || (jtblSkirt.getValueAt(0, 4) != null)) && ((jtblSkirt.getValueAt(0, 0) == null) || (jtblSkirt.getValueAt(0, 1) == null) || (jtblSkirt.getValueAt(0, 2) == null) || (jtblSkirt.getValueAt(0, 3) == null) || (jtblSkirt.getValueAt(0, 4) == null))) {
                JOptionPane.showMessageDialog(this, "Please fill all or none of the measurements for the skirt/pinnafore", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else if (((jtblCoat.getValueAt(0, 0) != null) || (jtblCoat.getValueAt(0, 1) != null) || (jtblCoat.getValueAt(0, 2) != null) || (jtblCoat.getValueAt(0, 3) != null) || (jtblCoat.getValueAt(0, 4) != null)) && ((jtblCoat.getValueAt(0, 0) == null) || (jtblCoat.getValueAt(0, 1) == null) || (jtblCoat.getValueAt(0, 2) == null) || (jtblCoat.getValueAt(0, 3) == null) || (jtblCoat.getValueAt(0, 4) == null))) {
                JOptionPane.showMessageDialog(this, "Please fill all or none of the measurements for the ", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (jtblShirt.getValueAt(0, 0) != null && jtblShirt.getValueAt(0, 0).toString().trim().equals("")) {
                    shirtlength = Double.parseDouble(jtblShirt.getValueAt(0, 0).toString());
                }
                if (jtblShirt.getValueAt(0, 1) != null && jtblShirt.getValueAt(0, 1).toString().trim().equals("")) {
                    shirthshoulder = Double.parseDouble(jtblShirt.getValueAt(0, 1).toString());
                }
                if (jtblShirt.getValueAt(0, 2) != null && jtblShirt.getValueAt(0, 2).toString().trim().equals("")) {
                    shirtchest = Double.parseDouble(jtblShirt.getValueAt(0, 2).toString());
                }
                if (jtblShirt.getValueAt(0, 3) != null && jtblShirt.getValueAt(0, 3).toString().trim().equals("")) {
                    shirtsleevelength = Double.parseDouble(jtblShirt.getValueAt(0, 3).toString());
                }
                if (jtblShirt.getValueAt(0, 4) != null && jtblShirt.getValueAt(0, 4).toString().trim().equals("")) {
                    shirtsleeveloose = Double.parseDouble(jtblShirt.getValueAt(0, 4).toString());
                }
                if (jtblShirt.getValueAt(0, 5) != null && jtblShirt.getValueAt(0, 5).toString().trim().equals("")) {
                    shirtcolar = Double.parseDouble(jtblShirt.getValueAt(0, 5).toString());
                }
                if (jtblPant.getValueAt(0, 0) != null && jtblPant.getValueAt(0, 0).toString().trim().equals("")) {
                    pantlength = Double.parseDouble(jtblPant.getValueAt(0, 0).toString());
                }
                if (jtblPant.getValueAt(0, 1) != null && jtblPant.getValueAt(0, 1).toString().trim().equals("")) {
                    pantinside = Double.parseDouble(jtblPant.getValueAt(0, 1).toString());
                }
                if (jtblPant.getValueAt(0, 3) != null && jtblPant.getValueAt(0, 2).toString().trim().equals("")) {
                    pantkneelength = Double.parseDouble(jtblPant.getValueAt(0, 2).toString());
                }
                if (jtblPant.getValueAt(0, 4) != null && jtblPant.getValueAt(0, 3).toString().trim().equals("")) {
                    pantkneeloose = Double.parseDouble(jtblPant.getValueAt(0, 3).toString());
                }
                if (jtblPant.getValueAt(0, 5) != null && jtblPant.getValueAt(0, 4).toString().trim().equals("")) {
                    pantwaist = Double.parseDouble(jtblPant.getValueAt(0, 4).toString());
                }
                if (jtblPant.getValueAt(0, 6) != null && jtblPant.getValueAt(0, 5).toString().trim().equals("")) {
                    pantseat = Double.parseDouble(jtblPant.getValueAt(0, 5).toString());
                }
                if (jtblPant.getValueAt(0, 2) != null && jtblPant.getValueAt(0, 5).toString().trim().equals("")) {
                    pantbottomloose = Double.parseDouble(jtblPant.getValueAt(0, 5).toString());
                }
                if (jtblSkirt.getValueAt(0, 0) != null && jtblSkirt.getValueAt(0, 0).toString().trim().equals("")) {
                    skirtyok = Double.parseDouble(jtblSkirt.getValueAt(0, 0).toString());
                }
                if (jtblSkirt.getValueAt(0, 1) != null && jtblSkirt.getValueAt(0, 1).toString().trim().equals("")) {
                    pinlength = Double.parseDouble(jtblSkirt.getValueAt(0, 1).toString());
                }
                if (jtblSkirt.getValueAt(0, 2) != null && jtblSkirt.getValueAt(0, 2).toString().trim().equals("")) {
                    skirtwaist = Double.parseDouble(jtblSkirt.getValueAt(0, 2).toString());
                }
                if (jtblSkirt.getValueAt(0, 3) != null && jtblSkirt.getValueAt(0, 3).toString().trim().equals("")) {
                    skirtshoulder = Double.parseDouble(jtblSkirt.getValueAt(0, 3).toString());
                }
                if (jtblSkirt.getValueAt(0, 4) != null && jtblSkirt.getValueAt(0, 4).toString().trim().equals("")) {
                    skirtlength = Double.parseDouble(jtblSkirt.getValueAt(0, 4).toString());
                }
                if (jtblCoat.getValueAt(0, 0) != null && jtblCoat.getValueAt(0, 0).toString().trim().equals("")) {
                    coatlength = Double.parseDouble(jtblCoat.getValueAt(0, 0).toString());
                }
                if (jtblCoat.getValueAt(0, 1) != null && jtblCoat.getValueAt(0, 1).toString().trim().equals("")) {
                    coatshoulder = Double.parseDouble(jtblCoat.getValueAt(0, 1).toString());
                }
                if (jtblCoat.getValueAt(0, 2) != null && jtblCoat.getValueAt(0, 2).toString().trim().equals("")) {
                    coatchest = Double.parseDouble(jtblCoat.getValueAt(0, 2).toString());
                }
                if (jtblCoat.getValueAt(0, 3) != null && jtblCoat.getValueAt(0, 3).toString().trim().equals("")) {
                    coatwaist = Double.parseDouble(jtblCoat.getValueAt(0, 3).toString());
                }
                if (jtblCoat.getValueAt(0, 4) != null && jtblCoat.getValueAt(0, 4).toString().trim().equals("")) {
                    coatseat = Double.parseDouble(jtblCoat.getValueAt(0, 4).toString());
                }
                if (jTxtOrderNo.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please enter the order number", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (jtxtStudentName.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please enter the student name", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (jCmbClass.getSelectedItem().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please select a class", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (jtxtDivision.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please enter the division", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (jtxtPhone.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please enter the phone", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (!jrbtnFemale.isSelected() && !jrbtnMale.isSelected()) {
                    JOptionPane.showMessageDialog(this, "Please select a gender", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (jtblItem.getRowCount() < 1) {
                    JOptionPane.showMessageDialog(this, "Please enter items to proceed", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (shirtlength > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid shirt length", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (shirthshoulder > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid shirt shoulder", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (shirtchest > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid shirt chest", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (shirtsleevelength > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid shirt sleeve length", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (shirtsleeveloose > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid shirt sleeve loose", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (shirtcolar > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid shirt collar", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (pantinside > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid pant inside", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (pantkneelength > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid pant knee length", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (pantkneeloose > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid pant knee loose", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (pantlength > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid pant length", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (pantseat > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid pant seat", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (pantwaist > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid pant waist", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (pantbottomloose > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid pant bottom loose", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (skirtlength > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid skirt length", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (skirtwaist > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid skirt waist", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (skirtyok > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid skirt yok", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (skirtshoulder > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid skirt shoulder", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (pinlength > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid pinnafore length", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (coatchest > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid coat chest", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (coatlength > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid coat length", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (coatseat > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid coat seat", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (coatshoulder > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid coat shoulder", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else if (coatwaist > 1000) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid coat waist", "Incomplete", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        int orderno = 0;
                        if (jrbtnMale.isSelected()) {
                            Gender = "Male";
                        } else {
                            Gender = "Female";
                        }
                        con = new DBConnection().connect();
                        con.setAutoCommit(false);
                        stmt = con.createStatement();
                        stmt1 = con.createStatement();
                        check = true;
                        query = "select class_no from mst_class where class = '" + jCmbClass.getSelectedItem() + "' and flag!='D'";
                        rs = stmt.executeQuery(query);
                        if (rs.next()) {
                            classno = rs.getInt(1);
                        }
                        query = "select * from mst_student where order_no = '" + jTxtOrderNo.getText().trim() + "' and flag!='D' and school_no = '" + Global.getSchoolno() + "'";
                        rs = stmt.executeQuery(query);
                        if (!rs.next()) {
                            if (Global.isValidNumber(Integer.class, jTxtOrderNo.getText().trim())) {
                                orderno = Integer.parseInt(jTxtOrderNo.getText().trim());
                                query = "INSERT INTO MST_STUDENT(`SCHOOL_NO`, `ORDER_NO`, `STUDENT_NAME`, `CLASS_NO`, `DIVISION`, `GENDER`, `PHONE_N0`, `SHIRTH_LENGTH`, `SHIRTH_SHOULDER`, `SHIRTH_CHEST`,"
                                        + " `SHIRTH_SLEEVE_LENGTH`, `SHIRTH_SLEEVE_LOOSE`, `SHIRTH_COLLAR`, `PANT_LENGTH`, `PANT_INSIDE`, `PANT_KNEE_LENGHT`, `PANT_KNEE_LOOSE`, `PANT_WAIST`, `PANT_SEAT`,`PANT_BOTTOM_LOOSE`,`PIN_YOK`,"
                                        + " `PIN_LENGTH`, `PIN_WAIST`,`PIN_SHOULDER`,`SKIRT_LENGTH`,`COAT_LENGTH`, `COAT_SHOULDER`, `COAT_CHEST`, `COAT_WAIST`, `COAT_SEAT`,`FLAG`, `DATE_CREATED`)"
                                        + " VALUES ('" + Global.schoolno + "', '" + orderno + "', '" + jtxtStudentName.getText().trim() + "', '" + classno + "', '" + jtxtDivision.getText().trim().toUpperCase() + "', '" + Gender + "', '" + jtxtPhone.getText().trim() + "',"
                                        + " " + jtblShirt.getValueAt(0, 0) + "," + jtblShirt.getValueAt(0, 1) + "," + jtblShirt.getValueAt(0, 2) + "," + jtblShirt.getValueAt(0, 3) + "," + jtblShirt.getValueAt(0, 4) + "," + jtblShirt.getValueAt(0, 5) + ","
                                        + "  " + jtblPant.getValueAt(0, 0) + ", " + jtblPant.getValueAt(0, 1) + ", " + jtblPant.getValueAt(0, 3) + ", " + jtblPant.getValueAt(0, 4) + ", " + jtblPant.getValueAt(0, 5) + ", " + jtblPant.getValueAt(0, 6) + ", " + jtblPant.getValueAt(0, 2) + ","
                                        + " " + jtblSkirt.getValueAt(0, 0) + ", " + jtblSkirt.getValueAt(0, 1) + ", " + jtblSkirt.getValueAt(0, 2) + ", " + jtblSkirt.getValueAt(0, 3) + ", " + jtblSkirt.getValueAt(0, 4) + ", " + jtblCoat.getValueAt(0, 0) + ""
                                        + "," + jtblCoat.getValueAt(0, 1) + ", " + jtblCoat.getValueAt(0, 2) + ", " + jtblCoat.getValueAt(0, 3) + "," + jtblCoat.getValueAt(0, 4) + ","
                                        + " 'I', '" + today + "')";
                                int i = stmt.executeUpdate(query);
                                query = "select max(stsl_no) from mst_student";
                                rs = stmt.executeQuery(query);
                                if (rs.next()) {
                                    stsl = rs.getInt(1);
                                }
                                for (int k = 0; k < jtblItem.getRowCount(); k++) {
                                    query = "insert into mst_student_detail(stsl_no,item_no,qty,flag,date_created) values('" + stsl + "','" + jtblItem.getValueAt(k, 0) + "','" + jtblItem.getValueAt(k, 2) + "','I','" + today + "')";
                                    i = i + stmt.executeUpdate(query);
                                }
                                for (int j = 0; j < jtblItem.getRowCount(); j++) {
                                    query = "select school_no,class_no,item_no,material_no,fixed_mat_length,actual_mat_length from mst_material_consumption where item_no = '" + jtblItem.getValueAt(j, 0) + "' and school_no = '" + Global.getSchoolno() + "' and class_no = '" + classno + "'";
                                    rs = stmt.executeQuery(query);
                                    while (rs.next()) {
                                        query = "INSERT INTO MST_STUDENT_MAT_DETAIL(STSL_NO,ITEM_NO,MATERIAL_NO,FIXED_MAT_LENGTH,ACTUAL_MAT_LENGTH,FLAG,DATE_CREATED) VALUES ('" + stsl + "', '" + rs.getString(3) + "', '" + rs.getString(4) + "', '" + rs.getString(5) + "', '" + rs.getString(6) + "', 'I', '" + today + "')";
                                        i = i + stmt1.executeUpdate(query);
                                    }
                                }
                                if (i >= jtblItem.getRowCount() + 1) {
                                    JOptionPane.showMessageDialog(this, "Added Successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
                                    clear();
                                    con.commit();
                                    stsl = 0;
                                    jTxtOrderNo.grabFocus();
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Please enter a valid order number", "Info", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "This order number already exist", "Info", JOptionPane.INFORMATION_MESSAGE);
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

            }
        }

    }//GEN-LAST:event_jbtnSubmitActionPerformed
    private class MyTraversalPolicy extends FocusTraversalPolicy {

        @Override
        public Component getComponentAfter(Container aContainer, Component aComponent) {
            if (aComponent.equals(jTxtOrderNo)) {
                return jtxtStudentName;
            } else if (aComponent.equals(jtxtStudentName)) {
                return jrbtnMale;
            }
            if (aComponent.equals(jrbtnMale)) {
                return jrbtnFemale;
            } else if (aComponent.equals(jrbtnFemale)) {
                return jCmbClass;
            } else if (aComponent.equals(jCmbClass)) {
                return jtxtDivision;
            } else if (aComponent.equals(jtxtDivision)) {
                return jtxtPhone;
            } else if (aComponent.equals(jtxtPhone)) {
                return jtblShirt;
            } else if (aComponent.equals(jtblShirt)) {
                return jtblPant;
            } else if (aComponent.equals(jtblPant)) {
                return jtblSkirt;
            } else if (aComponent.equals(jtblSkirt)) {
                return jtblCoat;
            } else if (aComponent.equals(jtblCoat)) {
                return jcmbItemName;
            } else if (aComponent.equals(jcmbItemName)) {
                return jtxtQty;
            } else if (aComponent.equals(jtxtQty)) {
                return jbtnAdd;
            } else if (aComponent.equals(jbtnAdd)) {
                return jbtnDelete;
            } else if (aComponent.equals(jbtnDelete)) {
                return jcmbItemName;
            } else {
                return jTxtOrderNo;
            }
        }

        @Override
        public Component getComponentBefore(Container aContainer, Component aComponent) {
            if (aComponent.equals(jTxtOrderNo)) {
                return jbtnSubmit;
            } else if (aComponent.equals(jtxtStudentName)) {
                return jTxtOrderNo;
            }
            if (aComponent.equals(jrbtnMale)) {
                return jtxtStudentName;
            } else if (aComponent.equals(jrbtnFemale)) {
                return jrbtnMale;
            } else if (aComponent.equals(jCmbClass)) {
                return jrbtnFemale;
            } else if (aComponent.equals(jtxtDivision)) {
                return jCmbClass;
            } else if (aComponent.equals(jtxtPhone)) {
                return jtxtDivision;
            } else if (aComponent.equals(jtblShirt)) {
                return jtxtPhone;
            } else if (aComponent.equals(jtblPant)) {
                return jtblShirt;
            } else if (aComponent.equals(jtblSkirt)) {
                return jtblPant;
            } else if (aComponent.equals(jtblCoat)) {
                return jtblSkirt;
            } else if (aComponent.equals(jcmbItemName)) {
                return jtblCoat;
            } else if (aComponent.equals(jtxtQty)) {
                return jcmbItemName;
            } else if (aComponent.equals(jbtnAdd)) {
                return jtxtQty;
            } else if (aComponent.equals(jbtnDelete)) {
                return jbtnAdd;
            } else {
                return jTxtOrderNo;
            }
        }

        @Override
        public Component getFirstComponent(Container aContainer) {
            return jTxtOrderNo;
        }

        @Override
        public Component getLastComponent(Container aContainer) {
            return jbtnSubmit;
        }

        @Override
        public Component getDefaultComponent(Container aContainer) {
            return jTxtOrderNo;
        }
    }
    private void jtxtDivisionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtDivisionKeyPressed
        text = "";
        text = jtxtDivision.getText().toUpperCase();
        if (text.length() == 3) {
            jtxtDivision.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtDivision.setEditable(true);
            }
        } else if (evt.getKeyChar() >= evt.VK_A && evt.getKeyChar() <= evt.VK_Z || evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT || evt.getKeyChar() == evt.VK_PERIOD) {
            jtxtDivision.setEditable(true);
        } else {
            jtxtDivision.setEditable(false);
        }
    }//GEN-LAST:event_jtxtDivisionKeyPressed

    private void jtxtStudentNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtStudentNameKeyPressed
        text = jtxtStudentName.getText();
        if (text.length() == 30) {
            jtxtStudentName.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtStudentName.setEditable(true);
            }
        } else {
            jtxtStudentName.setEditable(true);
        }
    }//GEN-LAST:event_jtxtStudentNameKeyPressed

    private void jtxtPhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtPhoneKeyPressed
        text = "";
        text = jtxtPhone.getText();
        if (text.length() == 11) {
            jtxtPhone.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtPhone.setEditable(true);
            }
        } else if (evt.getKeyChar() >= evt.VK_0 && evt.getKeyChar() <= evt.VK_9 || evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
            jtxtPhone.setEditable(true);
        } else {
            jtxtPhone.setEditable(false);
        }
    }//GEN-LAST:event_jtxtPhoneKeyPressed

    private void jtxtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtQtyKeyPressed
        text = "";
        text = jtxtQty.getText();
        if (text.length() == 2) {
            jtxtQty.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtQty.setEditable(true);
            }
        } else if (evt.getKeyChar() >= evt.VK_0 && evt.getKeyChar() <= evt.VK_9 || evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
            jtxtQty.setEditable(true);
        } else {
            jtxtQty.setEditable(false);
        }
    }//GEN-LAST:event_jtxtQtyKeyPressed

    private void jbtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddActionPerformed
        boolean added = false;
        if (jcmbItemName.getSelectedItem() == null || jcmbItemName.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select an item to proceed", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jCmbClass.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a class", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (!jrbtnFemale.isSelected() && !jrbtnMale.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please select a gender", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                if (jrbtnFemale.isSelected()) {
                    Gender = "Female";
                } else if (jrbtnMale.isSelected()) {
                    Gender = "Male";
                }
                added = false;
                con = new DBConnection().connect();
                stmt = con.createStatement();
                query = "select set_no from mst_set where set_name = '" + jcmbItemName.getSelectedItem().toString() + "' and flag!='D'";
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    if (jtxtQty.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(this, "Please enter quantity to proceed", "Info", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        query = "select a.item_no,c.item_name from mst_school_item_price a join mst_set b on a.set_no = b.set_no join mst_item c on a.item_no = c.item_no where  a.school_no = '" + Global.getSchoolno() + "' and c.gender in ('" + Gender + "','Common') and a.class_from <= '" + jCmbClass.getSelectedItem() + "' and a.class_to >= '" + jCmbClass.getSelectedItem() + "' and b.set_name = '" + jcmbItemName.getSelectedItem() + "'  and a.flag!='D' and b.flag!='D' and c.flag!='D'";
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {

                            added = false;
                            for (int i = 0; i < jtblItem.getRowCount(); i++) {
                                if (jtblItem.getValueAt(i, 1).equals(rs.getString(2))) {
                                    added = true;
                                }
                            }
                            if (!added) {
                                model1.addRow(new Object[]{});
                                jtblItem.setValueAt(rs.getString(1), jtblItem.getRowCount() - 1, 0);
                                jtblItem.setValueAt(rs.getString(2), jtblItem.getRowCount() - 1, 1);
                                jtblItem.setValueAt(jtxtQty.getText().trim(), jtblItem.getRowCount() - 1, 2);
                            }
                        }
                    }
                } else {
                    if (jcmbItemName.getSelectedItem().toString().equals("All")) {
                        query = "select a.item_no,c.item_name,d.cat_name,e.qty from mst_school_item_price a join mst_set b on a.set_no = b.set_no join mst_item c on a.item_no = c.item_no join mst_category d on c.cat_id = d.cat_id join mst_standard_order e on e.set_no = a.set_no where e.school_no = '" + Global.getSchoolno() + "' and a.school_no = '" + Global.getSchoolno() + "' and c.gender in ('" + Gender + "','Common') and a.class_from <= '" + jCmbClass.getSelectedItem() + "' and a.class_to >= '" + jCmbClass.getSelectedItem() + "' and a.flag!='D' and b.flag!='D' and c.flag!='D' and a.set_no = any(select distinct(a.set_no) from mst_school_item_price a join mst_item c on a.item_no = c.item_no where  a.school_no = '" + Global.getSchoolno() + "' and c.gender in ('" + Gender + "','Common') and a.class_from <= '" + jCmbClass.getSelectedItem() + "' and a.class_to >= '" + jCmbClass.getSelectedItem() + "' and a.flag!='D' and c.flag!='D' )";
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            added = false;
                            for (int i = 0; i < jtblItem.getRowCount(); i++) {
                                if (jtblItem.getValueAt(i, 1).equals(rs.getString(2))) {
                                    added = true;
                                }
                            }
                            if (!added) {
                                model1.addRow(new Object[]{});
                                jtblItem.setValueAt(rs.getString(1), jtblItem.getRowCount() - 1, 0);
                                jtblItem.setValueAt(rs.getString(2), jtblItem.getRowCount() - 1, 1);
                                if (rs.getString(3).toString().equals("TIE")) {
                                    jtblItem.setValueAt("1", jtblItem.getRowCount() - 1, 2);
                                } else {
                                    jtblItem.setValueAt(rs.getString(4), jtblItem.getRowCount() - 1, 2);
                                }
                            }
                        }
                    } else {
                        if (jtxtQty.getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(this, "Please enter quantity to proceed", "Info", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            for (int i = 0; i < jtblItem.getRowCount(); i++) {
                                if (jtblItem.getValueAt(i, 1).equals(jcmbItemName.getSelectedItem().toString())) {
                                    added = true;
                                }
                            }
                            if (added == true) {
                                JOptionPane.showMessageDialog(this, "This item already exist", "Info", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                model1.addRow(new Object[]{});
                                query = "select item_no from mst_item where item_name = '" + jcmbItemName.getSelectedItem().toString() + "' and flag!='D' and year(date_created)='" + Global.year + "' ";
                                rs = stmt.executeQuery(query);
                                if (rs.next()) {
                                    jtblItem.setValueAt(rs.getString(1), jtblItem.getRowCount() - 1, 0);
                                    jtblItem.setValueAt(jcmbItemName.getSelectedItem().toString(), jtblItem.getRowCount() - 1, 1);
                                    jtblItem.setValueAt(jtxtQty.getText().trim(), jtblItem.getRowCount() - 1, 2);
                                }
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            } finally {
                jtxtQty.setText("");
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

    private void jbtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteActionPerformed
        selectedrow = jtblItem.getSelectedRow();
        if (selectedrow > -1) {
            model1.removeRow(selectedrow);
        } else {
            if (jtblItem.getRowCount() > 0) {
                model1.removeRow(jtblItem.getRowCount() - 1);
            }

        }
    }//GEN-LAST:event_jbtnDeleteActionPerformed

    private void jTxtOrderNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtOrderNoKeyPressed
        text = "";
        text = jTxtOrderNo.getText();
        if (text.length() == 5) {
            jTxtOrderNo.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jTxtOrderNo.setEditable(true);
            }
        } else if (evt.getKeyChar() >= evt.VK_0 && evt.getKeyChar() <= evt.VK_9 || evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT || evt.getKeyChar() == evt.VK_PERIOD) {
            jTxtOrderNo.setEditable(true);
        } else {
            jTxtOrderNo.setEditable(false);
        }
    }//GEN-LAST:event_jTxtOrderNoKeyPressed

    private void jtxtDivisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtDivisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtDivisionActionPerformed

    private void jrbtnMaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbtnMaleItemStateChanged
        jtblSkirt.setEnabled(false);
        if (!jCmbClass.getSelectedItem().equals("")) {
            populateItem("Male", jCmbClass.getSelectedItem().toString());
        }
    }//GEN-LAST:event_jrbtnMaleItemStateChanged

    private void jrbtnFemaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jrbtnFemaleItemStateChanged
        jtblSkirt.setEnabled(true);
        if (!jCmbClass.getSelectedItem().equals("")) {
            populateItem("Female", jCmbClass.getSelectedItem().toString());
        }
    }//GEN-LAST:event_jrbtnFemaleItemStateChanged

    private void jCmbClassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCmbClassItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!jCmbClass.getSelectedItem().equals("")) {
                if (jrbtnMale.isSelected()) {
                    populateItem("Male", jCmbClass.getSelectedItem().toString());
                } else if (jrbtnFemale.isSelected()) {
                    populateItem("Female", jCmbClass.getSelectedItem().toString());
                }
            }
        }
    }//GEN-LAST:event_jCmbClassItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jCmbClass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTxtOrderNo;
    private javax.swing.JButton jbtnAdd;
    private javax.swing.JButton jbtnDelete;
    private javax.swing.JButton jbtnSubmit;
    private javax.swing.JComboBox jcmbItemName;
    private javax.swing.JRadioButton jrbtnFemale;
    private javax.swing.JRadioButton jrbtnMale;
    private javax.swing.JTable jtblCoat;
    private javax.swing.JTable jtblItem;
    private javax.swing.JTable jtblPant;
    private javax.swing.JTable jtblShirt;
    private javax.swing.JTable jtblSkirt;
    private javax.swing.JTextField jtxtDivision;
    private javax.swing.JTextField jtxtPhone;
    private javax.swing.JTextField jtxtQty;
    private javax.swing.JTextField jtxtSchoolName;
    private javax.swing.JTextField jtxtStudentName;
    // End of variables declaration//GEN-END:variables
}
