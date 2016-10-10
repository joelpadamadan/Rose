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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Joel
 */
public class JIFraAddSchoolDetails extends javax.swing.JInternalFrame {

    Connection con = null;
    DefaultTableModel model1;
    DefaultTableModel model2;
    DefaultTableModel model3;
    DefaultTableModel model4;
    ResultSet rs = null;
    Statement stmt = null;
    String query, text;
    int rowcount, c, cat_id, item_no, bh, set_no;
    double cc, sc, bc, ic, pc, me, icost;
    String[] school, material;
     
    String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    boolean added = false;

    public JIFraAddSchoolDetails() {
        initComponents();
        model1 = (DefaultTableModel) jtblItem.getModel();
        model2 = (DefaultTableModel) jtblMaterial.getModel();
        model3 = (DefaultTableModel) jtblItemDetais.getModel();
        model4 = (DefaultTableModel) jtblMaterialDetails.getModel();
        jtblItemDetais.setAutoCreateRowSorter(true);
        jtblItemDetais.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        try {
            con = new DBConnection().connect();
            stmt = con.createStatement();

            query = "select school_no,school_name from mst_school where flag != 'D' and year(date_created)='"+Global.year+"'  ";
            rs = stmt.executeQuery(query);
            jcmbSchool.addItem("");
            while (rs.next()) {
                jcmbSchool.addItem(rs.getString(1) + "   " + rs.getString(2));
            }

            query = "select set_name from mst_set where flag != 'D'";
            rs = stmt.executeQuery(query);
            jcmbSet.addItem("");
            while (rs.next()) {
                jcmbSet.addItem(rs.getString(1));
            }

            query = "select class from mst_class where flag != 'D'";
            rs = stmt.executeQuery(query);
            jcmbClassFrom.addItem("");
            jcmbClassTo.addItem("");
            while (rs.next()) {
                jcmbClassFrom.addItem(rs.getString(1));
                jcmbClassTo.addItem(rs.getString(1));
            }

            query = "select cat_name from mst_category where flag != 'D'";
            rs = stmt.executeQuery(query);
            jcmbCategory.addItem("");
            while (rs.next()) {
                jcmbCategory.addItem(rs.getString(1));
            }

            query = "select material_no,material_name from mst_material where flag != 'D' and year(date_created)='"+Global.year+"'";
            rs = stmt.executeQuery(query);
            jcmbMaterial.addItem("");
            while (rs.next()) {
                jcmbMaterial.addItem(rs.getString(1) + "   " + rs.getString(2));
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

    private void populate_item() {
        try {
            rowcount = model3.getRowCount();
            for (int i = 0; i < rowcount; i++) {
                model3.removeRow(0);
            }
            con = new DBConnection().connect();
            stmt = con.createStatement();
            school = jcmbSchool.getSelectedItem().toString().split("   ");
            query = "select a.item_no,c.item_name,d.cat_name,c.gender,b.cutting_charge,b.stitching_charge,b.button_charge,b.ironing_charge,b.packing_charge,b.material_extra,b.cutting_charge+b.stitching_charge+b.button_charge+b.ironing_charge+b.packing_charge+b.material_extra as cp,a.selling_price,a.class_from,a.class_to,c.buttonhole,e.set_name from mst_school_item_price a join mst_item_detail b on a.item_no = b.item_no join mst_item c on a.item_no = c.item_no join mst_category d on c.cat_id = d.cat_id left join mst_set e on a.set_no = e.set_no where a.school_no = '" + school[0] + "' and a.flag!='D' and b.flag!='D' and c.flag!='D' and d.flag!='D' and e.flag!='D' order by a.sl_no desc";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                rowcount = model3.getRowCount();
                model3.addRow(new Object[]{""});
                jtblItemDetais.setValueAt(rs.getString(1), rowcount, 0);
                jtblItemDetais.setValueAt(rs.getString(2), rowcount, 1);
                jtblItemDetais.setValueAt(rs.getString(3), rowcount, 2);
                jtblItemDetais.setValueAt(rs.getString(4), rowcount, 3);
                jtblItemDetais.setValueAt(rs.getString(5), rowcount, 4);
                jtblItemDetais.setValueAt(rs.getString(6), rowcount, 5);
                jtblItemDetais.setValueAt(rs.getString(7), rowcount, 6);
                jtblItemDetais.setValueAt(rs.getString(8), rowcount, 7);
                jtblItemDetais.setValueAt(rs.getString(9), rowcount, 8);
                jtblItemDetais.setValueAt(rs.getString(10), rowcount, 9);
                jtblItemDetais.setValueAt(rs.getString(11), rowcount, 10);
                jtblItemDetais.setValueAt(rs.getString(12), rowcount, 11);
                jtblItemDetais.setValueAt(rs.getString(13), rowcount, 12);
                jtblItemDetais.setValueAt(rs.getString(14), rowcount, 13);
                if (rs.getString(15).equals("0")) {
                    jtblItemDetais.setValueAt("No", rowcount, 14);
                } else {
                    jtblItemDetais.setValueAt("Yes", rowcount, 14);
                }

                jtblItemDetais.setValueAt(rs.getString(16), rowcount, 15);
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

    private void populate_material() {
        try {
            rowcount = model4.getRowCount();
            for (int i = 0; i < rowcount; i++) {
                model4.removeRow(0);
            }
            con = new DBConnection().connect();
            stmt = con.createStatement();
            school = jcmbSchool.getSelectedItem().toString().split("   ");
            query = "select b.material_name,b.cost_price,a.selling_price from mst_school_mat_price a join mst_material b on a.material_no = b.material_no where a.school_no ='" + school[0] + "' and item_no = '" + jtblItemDetais.getValueAt(jtblItemDetais.getSelectedRow(), 0) + "' and a.flag!='D' and b.flag!='D'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                rowcount = model4.getRowCount();
                model4.addRow(new Object[]{""});
                jtblMaterialDetails.setValueAt(rs.getString(1), rowcount, 0);
                jtblMaterialDetails.setValueAt(rs.getString(2), rowcount, 1);
                jtblMaterialDetails.setValueAt(rs.getString(3), rowcount, 2);
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
        jBtnSave = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtxtItem = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jcmbCategory = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jcmbGender = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblItem = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jtxtICostPrice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtxtISellingPrice = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jcmbClassFrom = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jcmbClassTo = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jcmbButtonhole = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jcmbSet = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jcmbMaterial = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jtxtMCostPrice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtxtMSellingPrice = new javax.swing.JTextField();
        jbtnAdd = new javax.swing.JButton();
        jBtnDelete = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblMaterial = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtblItemDetais = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtblMaterialDetails = new javax.swing.JTable();
        jcmbSchool = new javax.swing.JComboBox();

        setClosable(true);
        setTitle("Create School");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("School");

        jBtnSave.setText("SAVE");
        jBtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSaveActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Item"));

        jLabel2.setText("Item");

        jtxtItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtItemKeyPressed(evt);
            }
        });

        jLabel3.setText("Category");

        jLabel4.setText("Gender");

        jcmbGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Common", "Male", "Female" }));

        jtblItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Cutting Charge", "Stitching Charge", "Buttonhole Charge", "Ironing Charge", "Packing Charge", "Material Extra"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jtblItem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtblItemFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jtblItem);

        jLabel6.setText("Cost Price");

        jtxtICostPrice.setEditable(false);

        jLabel5.setText("Selling Price");

        jtxtISellingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtISellingPriceKeyPressed(evt);
            }
        });

        jLabel10.setText("Class From");

        jLabel11.setText("Class To");

        jLabel12.setText("Buttonhole");

        jcmbButtonhole.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));

        jLabel13.setText("Set");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtItem, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbClassFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbClassTo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbButtonhole, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtICostPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtISellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbSet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jtxtICostPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(jcmbButtonhole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jtxtItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(jcmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(jcmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(jcmbClassFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)
                                .addComponent(jcmbClassTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtxtISellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel13)
                        .addComponent(jcmbSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Material"));

        jLabel7.setText("Material");

        jcmbMaterial.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbMaterialItemStateChanged(evt);
            }
        });

        jLabel8.setText("Cost Price");

        jtxtMCostPrice.setEditable(false);

        jLabel9.setText("Selling Price");

        jtxtMSellingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtMSellingPriceKeyPressed(evt);
            }
        });

        jbtnAdd.setText("Add");
        jbtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddActionPerformed(evt);
            }
        });

        jBtnDelete.setText("Delete");
        jBtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDeleteActionPerformed(evt);
            }
        });

        jtblMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Material Name", "Cost Price", "Selling Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane2.setViewportView(jtblMaterial);
        jtblMaterial.getColumnModel().getColumn(0).setMinWidth(0);
        jtblMaterial.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtblMaterial.getColumnModel().getColumn(0).setMaxWidth(0);
        jtblMaterial.getColumnModel().getColumn(2).setMinWidth(100);
        jtblMaterial.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtblMaterial.getColumnModel().getColumn(2).setMaxWidth(100);
        jtblMaterial.getColumnModel().getColumn(3).setMinWidth(100);
        jtblMaterial.getColumnModel().getColumn(3).setPreferredWidth(100);
        jtblMaterial.getColumnModel().getColumn(3).setMaxWidth(100);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtMCostPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtMSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnAdd)
                    .addComponent(jBtnDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jcmbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jtxtMCostPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jtxtMSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jbtnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnDelete)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jtblItemDetais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Item Name", "Category", "Gender", "Cutting Charge", "Stitching Charge", "Buttonhole Charge", "Ironing Charge", "Packing Charge", "Material Extra", "Cost Price", "Selling Price", "Class From", "Class To", "BH", "Set"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblItemDetais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblItemDetaisMouseClicked(evt);
            }
        });
        jtblItemDetais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtblItemDetaisKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jtblItemDetais);
        jtblItemDetais.getColumnModel().getColumn(0).setMinWidth(0);
        jtblItemDetais.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtblItemDetais.getColumnModel().getColumn(0).setMaxWidth(0);
        jtblItemDetais.getColumnModel().getColumn(1).setPreferredWidth(200);
        jtblItemDetais.getColumnModel().getColumn(2).setPreferredWidth(125);
        jtblItemDetais.getColumnModel().getColumn(3).setPreferredWidth(100);
        jtblItemDetais.getColumnModel().getColumn(4).setPreferredWidth(100);
        jtblItemDetais.getColumnModel().getColumn(5).setPreferredWidth(110);
        jtblItemDetais.getColumnModel().getColumn(6).setPreferredWidth(115);
        jtblItemDetais.getColumnModel().getColumn(7).setPreferredWidth(100);
        jtblItemDetais.getColumnModel().getColumn(8).setPreferredWidth(100);
        jtblItemDetais.getColumnModel().getColumn(9).setPreferredWidth(100);
        jtblItemDetais.getColumnModel().getColumn(10).setPreferredWidth(70);
        jtblItemDetais.getColumnModel().getColumn(11).setPreferredWidth(75);

        jtblMaterialDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Material Name", "Cost Price", "Selling Price"
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
        jScrollPane4.setViewportView(jtblMaterialDetails);
        jtblMaterialDetails.getColumnModel().getColumn(1).setMinWidth(80);
        jtblMaterialDetails.getColumnModel().getColumn(1).setPreferredWidth(80);
        jtblMaterialDetails.getColumnModel().getColumn(1).setMaxWidth(80);
        jtblMaterialDetails.getColumnModel().getColumn(2).setMinWidth(80);
        jtblMaterialDetails.getColumnModel().getColumn(2).setPreferredWidth(80);
        jtblMaterialDetails.getColumnModel().getColumn(2).setMaxWidth(80);

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
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBtnSave))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcmbSchool, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 388, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcmbSchool, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jBtnSave)
                        .addGap(27, 27, 27)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
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

    private void jtxtItemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtItemKeyPressed
        text = jtxtItem.getText();
        if (text.length() == 30) {
            jtxtItem.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtItem.setEditable(true);
            }
        } else {
            jtxtItem.setEditable(true);
        }
    }//GEN-LAST:event_jtxtItemKeyPressed

    private void jtxtISellingPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtISellingPriceKeyPressed
        text = "";
        text = jtxtISellingPrice.getText();
        if (text.length() == 5) {
            jtxtISellingPrice.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtISellingPrice.setEditable(true);
            }
        } else if (evt.getKeyChar() >= evt.VK_0 && evt.getKeyChar() <= evt.VK_9 || evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
            jtxtISellingPrice.setEditable(true);
        } else {
            jtxtISellingPrice.setEditable(false);
        }
    }//GEN-LAST:event_jtxtISellingPriceKeyPressed

    private void jtxtMSellingPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtMSellingPriceKeyPressed
        text = "";
        text = jtxtMSellingPrice.getText();
        if (text.length() == 5) {
            jtxtMSellingPrice.setEditable(false);
            if (evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
                jtxtMSellingPrice.setEditable(true);
            }
        } else if (evt.getKeyChar() >= evt.VK_0 && evt.getKeyChar() <= evt.VK_9 || evt.getKeyChar() == evt.VK_BACK_SPACE || evt.getKeyChar() == evt.VK_DELETE || evt.getKeyChar() == evt.VK_LEFT || evt.getKeyChar() == evt.VK_RIGHT) {
            jtxtMSellingPrice.setEditable(true);
        } else {
            jtxtMSellingPrice.setEditable(false);
        }
    }//GEN-LAST:event_jtxtMSellingPriceKeyPressed

    private void jtblItemDetaisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtblItemDetaisKeyPressed
    }//GEN-LAST:event_jtblItemDetaisKeyPressed

    private void jcmbSchoolItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbSchoolItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            clear();
            if (!jcmbSchool.getSelectedItem().equals("")) {
                populate_item();
            }
        }
    }//GEN-LAST:event_jcmbSchoolItemStateChanged
    private void clear() {
        jtxtItem.setText("");
        jcmbSet.setSelectedItem("");
        jcmbCategory.setSelectedItem("");
        jtxtICostPrice.setText("");
        jtxtISellingPrice.setText("");
        jtxtMCostPrice.setText("");
        jtxtMSellingPrice.setText("");

        rowcount = model2.getRowCount();
        for (int i = 0; i < rowcount; i++) {
            model2.removeRow(0);
        }
        rowcount = model3.getRowCount();
        for (int i = 0; i < rowcount; i++) {
            model3.removeRow(0);
        }
        rowcount = model4.getRowCount();
        for (int i = 0; i < rowcount; i++) {
            model4.removeRow(0);
        }
        jtblItem.setValueAt(0, 0, 0);
        jtblItem.setValueAt(0, 0, 1);
        jtblItem.setValueAt(0, 0, 2);
        jtblItem.setValueAt(0, 0, 3);
        jtblItem.setValueAt(0, 0, 4);
        jtblItem.setValueAt(0, 0, 5);
    }
    private void jbtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddActionPerformed
        if (jcmbMaterial.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a material", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtxtMSellingPrice.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid selling price", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            c = JOptionPane.YES_OPTION;
//            if (Integer.parseInt(jtxtMCostPrice.getText()) > Integer.parseInt(jtxtMSellingPrice.getText())) {
//                c = JOptionPane.showConfirmDialog(this, "cost price is greater then selling price, do you want to continue?", "Info", JOptionPane.YES_NO_OPTION);
//            }
            added = false;
            material = jcmbMaterial.getSelectedItem().toString().split("   ");
            if (c == JOptionPane.YES_OPTION) {
                for (int i = 0; i < jtblMaterial.getRowCount(); i++) {
                    if (material[0] == jtblMaterial.getValueAt(i, 0)) {
                        added = true;
                    }
                }
                if (added) {
                    JOptionPane.showMessageDialog(this, "This material already exists", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    rowcount = model2.getRowCount();
                    model2.addRow(new Object[]{""});
                    jtblMaterial.setValueAt(material[0], rowcount, 0);
                    jtblMaterial.setValueAt(material[1], rowcount, 1);
                    jtblMaterial.setValueAt(jtxtMCostPrice.getText(), rowcount, 2);
                    jtblMaterial.setValueAt(jtxtMSellingPrice.getText(), rowcount, 3);
                    jcmbMaterial.setSelectedItem("");
                    jtxtMCostPrice.setText("");
                    jtxtMSellingPrice.setText("");
                }
            }
        }
    }//GEN-LAST:event_jbtnAddActionPerformed

    private void jBtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDeleteActionPerformed
        int selectedrow = jtblMaterial.getSelectedRow();
        if (selectedrow > -1) {
            model2.removeRow(selectedrow);
        } else {
            if (jtblMaterial.getRowCount() > 0) {
                model2.removeRow(jtblMaterial.getRowCount() - 1);
            }
        }
    }//GEN-LAST:event_jBtnDeleteActionPerformed

    private void jBtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSaveActionPerformed
        if (jcmbSchool.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a school", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtxtItem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter an item name", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jcmbCategory.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a category", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtxtISellingPrice.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a selling price", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtblItem.getValueAt(0, 0) == null || jtblItem.getValueAt(0, 0).equals("")) {
            JOptionPane.showMessageDialog(this, "Please select enter a cutting charge", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtblItem.getValueAt(0, 1) == null || jtblItem.getValueAt(0, 1).equals("")) {
            JOptionPane.showMessageDialog(this, "Please select enter a stitching charge", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtblItem.getValueAt(0, 2) == null || jtblItem.getValueAt(0, 2).equals("")) {
            JOptionPane.showMessageDialog(this, "Please select enter a buttonhole charge", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtblItem.getValueAt(0, 3) == null || jtblItem.getValueAt(0, 3).equals("")) {
            JOptionPane.showMessageDialog(this, "Please select enter an ironing charge", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtblItem.getValueAt(0, 4) == null || jtblItem.getValueAt(0, 4).equals("")) {
            JOptionPane.showMessageDialog(this, "Please select enter a packing charge", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtblItem.getValueAt(0, 5) == null || jtblItem.getValueAt(0, 5).equals("")) {
            JOptionPane.showMessageDialog(this, "Please select enter material extra", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jtblMaterial.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a material for the item", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jcmbClassFrom.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a class from", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jcmbClassTo.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a class to", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jcmbSet.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select a set the item belongs to", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (jcmbButtonhole.getSelectedItem().equals("No") && Double.parseDouble(jtblItem.getValueAt(0, 2).toString()) > 0) {
            JOptionPane.showMessageDialog(this, "Please set the buttonhole charge to 0", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                if (jcmbButtonhole.getSelectedItem().equals("Yes")) {
                    bh = 1;
                } else if (jcmbButtonhole.getSelectedItem().equals("No")) {
                    bh = 0;
                }
                cat_id = 0;
                item_no = 0;
                c = 0;
                set_no = 0;
                school = jcmbSchool.getSelectedItem().toString().split("   ");
                con = new DBConnection().connect();
                con.setAutoCommit(false);
                stmt = con.createStatement();
                query = "select a.item_no from mst_school_item_price a join mst_item b on a.item_no = b.item_no where a.school_no = '" + school[0] + "' and b.item_name = '" + jtxtItem.getText().trim() + "' and a.flag!='D' and b.flag!='D'";
                rs = stmt.executeQuery(query);
                if (!rs.next()) {
                    query = "select cat_id from mst_category where cat_name = '" + jcmbCategory.getSelectedItem() + "' and flag!= 'D'";
                    rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        cat_id = rs.getInt(1);
                    }
                    query = "select set_no from mst_set where set_name = '" + jcmbSet.getSelectedItem() + "' and flag != 'D'";
                    rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        set_no = rs.getInt(1);
                    }
                    query = "INSERT INTO mst_item (ITEM_NAME,FLAG,DATE_CREATED,CAT_ID,GENDER,BUTTONHOLE) VALUES ('" + jtxtItem.getText().trim() + "', 'I', '" + today + "', '" + cat_id + "', '" + jcmbGender.getSelectedItem() + "','" + bh + "')";
                    c = c + stmt.executeUpdate(query);

                    query = "select max(item_no) from mst_item";
                    rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        item_no = rs.getInt(1);
                    }

                    query = "INSERT INTO mst_item_detail (ITEM_NO,YEAR,CUTTING_CHARGE,STITCHING_CHARGE,BUTTON_CHARGE,IRONING_CHARGE,PACKING_CHARGE,MATERIAL_EXTRA,FLAG,DATE_CREATED) VALUES ('" + item_no + " ', '" + Global.year + "', '" + jtblItem.getValueAt(0, 0) + "','" + jtblItem.getValueAt(0, 1) + "','" + jtblItem.getValueAt(0, 2) + "','" + jtblItem.getValueAt(0, 3) + "','" + jtblItem.getValueAt(0, 4) + "','" + jtblItem.getValueAt(0, 5) + "', 'I', '" + today + "');";
                    c = c + stmt.executeUpdate(query);

                    query = "INSERT INTO mst_school_item_price (SCHOOL_NO,ITEM_NO,SELLING_PRICE, FLAG, DATE_CREATED,CLASS_FROM,CLASS_TO,SET_NO) VALUES ('" + school[0] + "', '" + item_no + "', '" + jtxtISellingPrice.getText().trim() + "', 'I', '" + today + "','" + jcmbClassFrom.getSelectedItem() + "','" + jcmbClassTo.getSelectedItem() + "','" + set_no + "')";
                    c = c + stmt.executeUpdate(query);

                    for (int i = 0; i < jtblMaterial.getRowCount(); i++) {
                        query = "INSERT INTO mst_school_mat_price (SCHOOL_NO, ITEM_NO, MATERIAL_NO, SELLING_PRICE, FLAG, DATE_CREATED) VALUES ('" + school[0] + "', '" + item_no + "', '" + jtblMaterial.getValueAt(i, 0) + "', '" + jtblMaterial.getValueAt(i, 3) + "', 'I', '" + today + "')";
                        c = c + stmt.executeUpdate(query);
                    }
                    if (c > 3) {
                        JOptionPane.showMessageDialog(this, "Added successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        con.commit();
                        clear();
                        populate_item();
                        jtxtItem.grabFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "This item already exist!", "Info", JOptionPane.INFORMATION_MESSAGE);
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
    }//GEN-LAST:event_jBtnSaveActionPerformed
    private class MyTraversalPolicy extends FocusTraversalPolicy {

        @Override
        public Component getComponentAfter(Container aContainer, Component aComponent) {
            if (aComponent.equals(jtxtItem)) {
                return jcmbCategory;
            } else if (aComponent.equals(jcmbCategory)) {
                return jcmbGender;
            } else if (aComponent.equals(jcmbGender)) {
                return jcmbClassFrom;
            } else if (aComponent.equals(jcmbClassFrom)) {
                return jcmbClassTo;
            } else if (aComponent.equals(jcmbClassTo)) {
                return jcmbButtonhole;
            } else if (aComponent.equals(jcmbButtonhole)) {
                return jtblItem;
            } else if (aComponent.equals(jtblItem)) {
                return jtxtICostPrice;
            } else if (aComponent.equals(jtxtICostPrice)) {
                return jtxtISellingPrice;
            } else if (aComponent.equals(jtxtISellingPrice)) {
                return jcmbSet;
            } else if (aComponent.equals(jcmbSet)) {
                return jcmbMaterial;
            } else if (aComponent.equals(jcmbMaterial)) {
                return jtxtMCostPrice;
            } else if (aComponent.equals(jtxtMCostPrice)) {
                return jtxtMSellingPrice;
            } else if (aComponent.equals(jtxtMSellingPrice)) {
                return jbtnAdd;
            } else if (aComponent.equals(jbtnAdd)) {
                return jBtnDelete;
            } else if (aComponent.equals(jBtnDelete)) {
                return jBtnSave;
            } else if (aComponent.equals(jBtnSave)) {
                return jtxtItem;
            }
            return jtxtItem;

        }

        @Override
        public Component getComponentBefore(Container aContainer, Component aComponent) {
            if (aComponent.equals(jtxtItem)) {
                return jBtnSave;
            } else if (aComponent.equals(jcmbCategory)) {
                return jtxtItem;
            } else if (aComponent.equals(jcmbGender)) {
                return jcmbCategory;
            } else if (aComponent.equals(jcmbClassFrom)) {
                return jcmbGender;
            } else if (aComponent.equals(jcmbClassTo)) {
                return jcmbClassFrom;
            } else if (aComponent.equals(jcmbButtonhole)) {
                return jcmbClassTo;
            } else if (aComponent.equals(jtblItem)) {
                return jcmbButtonhole;
            } else if (aComponent.equals(jtxtICostPrice)) {
                return jtblItem;
            } else if (aComponent.equals(jtxtISellingPrice)) {
                return jtxtICostPrice;
            } else if (aComponent.equals(jcmbSet)) {
                return jtxtISellingPrice;
            } else if (aComponent.equals(jcmbMaterial)) {
                return jcmbSet;
            } else if (aComponent.equals(jtxtMCostPrice)) {
                return jcmbMaterial;
            } else if (aComponent.equals(jtxtMSellingPrice)) {
                return jtxtMCostPrice;
            } else if (aComponent.equals(jbtnAdd)) {
                return jtxtMSellingPrice;
            } else if (aComponent.equals(jBtnDelete)) {
                return jbtnAdd;
            } else if (aComponent.equals(jBtnSave)) {
                return jBtnDelete;
            }
            return jtxtItem;
        }

        @Override
        public Component getFirstComponent(Container aContainer) {
            return jtxtItem;
        }

        @Override
        public Component getLastComponent(Container aContainer) {
            return jBtnSave;
        }

        @Override
        public Component getDefaultComponent(Container aContainer) {
            return jtxtItem;
        }
    }
    private void jtblItemDetaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblItemDetaisMouseClicked
        if (jtblItemDetais.getSelectedRow() != -1) {
            populate_material();
        }
    }//GEN-LAST:event_jtblItemDetaisMouseClicked

    private void jtblItemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtblItemFocusLost
        cc = 0;
        sc = 0;
        bc = 0;
        ic = 0;
        pc = 0;
        me = 0;
        if (jtblItem.getValueAt(0, 0) != null && jtblItem.getValueAt(0, 0).getClass() == Double.class) {
            cc = Double.parseDouble(jtblItem.getValueAt(0, 0).toString());
        }
        if (jtblItem.getValueAt(0, 1) != null && jtblItem.getValueAt(0, 1).getClass() == Double.class) {
            sc = Double.parseDouble(jtblItem.getValueAt(0, 1).toString());
        }
        if (jtblItem.getValueAt(0, 2) != null && jtblItem.getValueAt(0, 2).getClass() == Double.class) {
            bc = Double.parseDouble(jtblItem.getValueAt(0, 2).toString());
        }
        if (jtblItem.getValueAt(0, 3) != null && jtblItem.getValueAt(0, 3).getClass() == Double.class) {
            ic = Double.parseDouble(jtblItem.getValueAt(0, 3).toString());
        }
        if (jtblItem.getValueAt(0, 4) != null && jtblItem.getValueAt(0, 4).getClass() == Double.class) {
            pc = Double.parseDouble(jtblItem.getValueAt(0, 4).toString());
        }
        if (jtblItem.getValueAt(0, 5) != null && jtblItem.getValueAt(0, 5).getClass() == Double.class) {
            me = Double.parseDouble(jtblItem.getValueAt(0, 5).toString());
        }
        icost = cc + sc + bc + ic + pc + me;
        jtxtICostPrice.setText(String.valueOf(icost));
    }//GEN-LAST:event_jtblItemFocusLost

    private void jcmbMaterialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbMaterialItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!jcmbMaterial.getSelectedItem().equals("")) {
                try {
                    material = jcmbMaterial.getSelectedItem().toString().split("   ");
                    con = new DBConnection().connect();
                    stmt = con.createStatement();
                    query = "select cost_price from mst_material where material_no = '" + material[0] + "' and flag!='D'";
                    rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        jtxtMCostPrice.setText(rs.getString(1));
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
    }//GEN-LAST:event_jcmbMaterialItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnDelete;
    private javax.swing.JButton jBtnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton jbtnAdd;
    private javax.swing.JComboBox jcmbButtonhole;
    private javax.swing.JComboBox jcmbCategory;
    private javax.swing.JComboBox jcmbClassFrom;
    private javax.swing.JComboBox jcmbClassTo;
    private javax.swing.JComboBox jcmbGender;
    private javax.swing.JComboBox jcmbMaterial;
    private javax.swing.JComboBox jcmbSchool;
    private javax.swing.JComboBox jcmbSet;
    private javax.swing.JTable jtblItem;
    private javax.swing.JTable jtblItemDetais;
    private javax.swing.JTable jtblMaterial;
    private javax.swing.JTable jtblMaterialDetails;
    private javax.swing.JTextField jtxtICostPrice;
    private javax.swing.JTextField jtxtISellingPrice;
    private javax.swing.JTextField jtxtItem;
    private javax.swing.JTextField jtxtMCostPrice;
    private javax.swing.JTextField jtxtMSellingPrice;
    // End of variables declaration//GEN-END:variables
}
