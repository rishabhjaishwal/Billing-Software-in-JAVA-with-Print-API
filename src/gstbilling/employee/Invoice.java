/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gstbilling.employee;
import gstbilling.AdminHome;
import gstbilling.Connect;
import gstbilling.EmployeeHome;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Shivam Kumar
 */
public class Invoice extends javax.swing.JInternalFrame {
   public static String name;
String invoiceno;
int sn;
Double Totalamt;
int snum=1;
    /**
     * Creates new form Invoice
     */
    public Invoice() {
        initComponents();
        filldetail();
        show_user();
    }
    public ArrayList<User> usersList(){
      ArrayList<User> userList=new ArrayList();
      int sno=1;
      try
        {  
          Connection con=Connect.connect();
          PreparedStatement ps= con.prepareStatement("Select * from invoicedata where invoiceno=? ");
          ps.setString(1,jinvoice.getText());
          ResultSet rs=ps.executeQuery();
          User user;
          sn=1;
          Totalamt =0.0;
          while(rs.next())
          { 
             snum=rs.getInt("sno");
             snum=snum+1;
             user=new User(rs.getInt("sno"),rs.getDouble("cgst"),rs.getDouble("sgst"),rs.getDouble("igst"),rs.getDouble("quantity"),rs.getDouble("price"),rs.getDouble("amount"),rs.getString("Unit"),rs.getString("productname"),rs.getDouble("discount"));          
             userList.add(user);
             sn=sn+1;
             Double amount=rs.getDouble("amount");
             Double sgst=rs.getDouble("sgst");
             Double cgst=rs.getDouble("cgst");
             Double igst=rs.getDouble("igst");
             Double discount=rs.getDouble("discount");
             Totalamt=Totalamt+amount+sgst+cgst+igst-discount;
             Ttalamt.setText(""+Totalamt);
             Ttalamt.setEditable(false);
//   add.setText(rs.getString("product"));
          }
           String ss=snum+"";
          snumber.setText(ss);
        }
       catch(Exception ex)
       {
         JOptionPane.showMessageDialog(this, ex +"OK");
       }
         return userList;
    }
public void show_user(){
     DefaultTableModel model = (DefaultTableModel)invoicetable.getModel();  
          int rows = model.getRowCount(); 
           for(int i = rows - 1; i >=0; i--)
           {
              model.removeRow(i); 
           }
      
    ArrayList<User> list=usersList();
  //  DefaultTableModel model= (DefaultTableModel)showTable.getModel();
    Object[] row=new Object[9];
    for(int i=0;i<list.size();i++)
    {
       row[0]=list.get(i).getSno();
       row[1]=list.get(i).getProduct();
       row[2]=list.get(i).getPrice();
       row[4]=list.get(i).getCgst();
       row[3]=list.get(i).getSgst();
       row[5]=list.get(i).getIgst();
       row[6]=list.get(i).getQty();
       row[7]=list.get(i).getAmount();
       row[8]=list.get(i).getDiscount();
       model.addRow(row);
    }
    }

    private void filldetail()
    {
    try{
             Connection con=Connect.connect();
             PreparedStatement ps=con.prepareStatement("Select * from companydetail");
             ResultSet rs=ps.executeQuery();
             if(rs.next())
             {
                    jcompname.setText(rs.getString("companyname"));
                    jCombostate.setSelectedItem(rs.getString("state"));
                    jgst.setText(rs.getString("gstinnumber"));
                        gstbilling.admin.DateFill mm=new gstbilling.admin.DateFill();
                        mm.fill();
                        String moni="";
                        if(Integer.parseInt(mm.getMonth())<10){
                        moni="0"+mm.getMonth();}
                     jComboyear.setSelectedItem(mm.getYear());                     
                     jCombomonth.setSelectedItem(moni);
                     jcombodate.setSelectedItem(mm.getDate1());                    
                     jemail.setText(rs.getString("cinnumber"));
                     String hoho=name;
                          jemployeedeclare.setText(hoho);
                   ps=con.prepareStatement("Select invoiceno from invoicecustomer order by sno desc limit 1");
                   ResultSet rs1=ps.executeQuery();
                   if(rs1.next()){
                   String st=rs1.getString("invoiceno");
                   invoiceno=st;
                   String ft=st.substring(0,3);
                   String lt=st.substring(7,10);
                   int lts=Integer.parseInt(lt);
                    lts=lts+1;
                    lt=String.valueOf(lts);
                     st=ft+mm.getYear()+lt;
                     int len=st.length();
                     len=10-len;
                     if(len==1)
                         lt=lt.replace(lt,"0"+lt);
                     if(len==2)                
                        lt=lt.replace(lt, "00"+lt);
                      st=ft+mm.getYear()+lt;
                     jinvoice.setText(st);
                   }
             }
          discount.setText("0");
          Connection con1=Connect.connect();
          PreparedStatement ps1= con1.prepareStatement("Select productname from productcreated");
          ResultSet rs1=ps1.executeQuery();
          Selectitem.addItem("Select Product");
          while(rs1.next()){
               Selectitem.addItem(rs1.getString("productname"));
          }
          String ss=snum+"";
          snumber.setText(ss);
    }catch(Exception ex){
    JOptionPane.showMessageDialog(this,"Error :"+ ex);
}
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jcompname = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCombostate = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jComboyear = new javax.swing.JComboBox();
        jCombomonth = new javax.swing.JComboBox();
        jcombodate = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jgst = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jemail = new javax.swing.JTextField();
        jinvoice = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jfinalamount1 = new javax.swing.JTextField();
        Selectitem = new javax.swing.JComboBox();
        qty = new javax.swing.JComboBox();
        rate = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        unit = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        invoicetable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jcustname = new javax.swing.JTextField();
        jaadharno = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jcustemail = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jphoneno = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jemployeedeclare = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jsavebutton = new javax.swing.JButton();
        newinvoice = new javax.swing.JButton();
        newinvoice1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        Ttalamt = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        discount = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        snumber = new javax.swing.JTextField();

        jLabel2.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel2.setText("Company Name");

        jcompname.setEditable(false);
        jcompname.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel1.setText("Create Invoice");

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel3.setText("Invoice Number");

        jCombostate.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jCombostate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Andra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala", "Madya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Orissa", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Tripura", "Uttaranchal", "Uttar Pradesh", "West Bengal" }));
        jCombostate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombostateActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel5.setText("State");

        jComboyear.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jComboyear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Year", "2017", "2018", "2019", "2020" }));
        jComboyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboyearActionPerformed(evt);
            }
        });

        jCombomonth.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jCombomonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        jCombomonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombomonthActionPerformed(evt);
            }
        });

        jcombodate.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jcombodate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jcombodate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcombodateActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel4.setText("Date");

        jLabel6.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel6.setText("GST/TIN No");

        jgst.setEditable(false);
        jgst.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jgst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jgstActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel7.setText("CIN Number");

        jemail.setEditable(false);
        jemail.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jemailActionPerformed(evt);
            }
        });

        jinvoice.setEditable(false);
        jinvoice.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jinvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jinvoiceActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel12.setText("SNo");

        jLabel8.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel8.setText("Product Name");

        jLabel9.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel9.setText("Quantity");

        jLabel10.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel10.setText("Rate");

        jLabel11.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel11.setText("Amount");

        jfinalamount1.setEditable(false);
        jfinalamount1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jfinalamount1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfinalamount1ActionPerformed(evt);
            }
        });

        Selectitem.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        Selectitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectitemActionPerformed(evt);
            }
        });

        qty.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        qty.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100" }));
        qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtyActionPerformed(evt);
            }
        });

        rate.setEditable(false);
        rate.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel13.setText("unit");

        unit.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        unit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "select unit", "Cm", "Inches", "Piece", "Meter", "KM", "Foot", "Unit", "Feet", "Pounds", "Set", "KG" }));
        unit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitActionPerformed(evt);
            }
        });

        invoicetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.No", "Product", "Qty", "Cgst", "Sgst", "Igst", "Price", "Amount", "Discount"
            }
        ));
        jScrollPane1.setViewportView(invoicetable);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Add Item");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Delete All");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel27.setText("Customer Name");

        jcustname.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jcustname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcustnameFocusLost(evt);
            }
        });
        jcustname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcustnameActionPerformed(evt);
            }
        });

        jaadharno.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jaadharno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jaadharnoFocusLost(evt);
            }
        });
        jaadharno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jaadharnoActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel29.setText("Aadhar No");

        jcustemail.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jcustemail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcustemailFocusLost(evt);
            }
        });
        jcustemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcustemailActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel30.setText("Email Id");

        jphoneno.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jphoneno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jphonenoFocusLost(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel28.setText("Phone No");

        jLabel31.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel31.setText("Invoice Declared By");

        jemployeedeclare.setEditable(false);
        jemployeedeclare.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jemployeedeclare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jemployeedeclareActionPerformed(evt);
            }
        });

        jButton5.setText("Print");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jsavebutton.setText("Save");
        jsavebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsavebuttonActionPerformed(evt);
            }
        });

        newinvoice.setText("New");
        newinvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newinvoiceActionPerformed(evt);
            }
        });

        newinvoice1.setText("Close");
        newinvoice1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newinvoice1ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setText("Delete");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel32.setText("Total Amount");

        Ttalamt.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        Ttalamt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TtalamtActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel33.setText("Discount");

        discount.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        discount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel14.setText("Rs");

        snumber.setEditable(false);
        snumber.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcompname, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jcombodate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCombomonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboyear, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jgst, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jemail, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jinvoice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jCombostate, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(77, 77, 77))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(104, 104, 104)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(115, 115, 115)
                                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(134, 134, 134)
                                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(21, 21, 21))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(148, 148, 148)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jfinalamount1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(30, 30, 30)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(unit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(discount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(64, 64, 64)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jphoneno, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jcustname, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(328, 328, 328)
                                        .addComponent(Ttalamt, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(25, 25, 25)
                                                    .addComponent(jemployeedeclare, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(46, 46, 46)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jaadharno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jcustemail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 21, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(newinvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(107, 107, 107)
                                .addComponent(jsavebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(116, 116, 116)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(newinvoice1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(119, 119, 119))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(snumber, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Selectitem, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rate, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(129, 129, 129))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jinvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcompname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCombostate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jemail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jcombodate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCombomonth, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboyear, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jgst, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Selectitem)
                            .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rate)
                            .addComponent(jfinalamount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(snumber))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(unit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(discount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcustname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ttalamt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jphoneno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcustemail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jaadharno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jemployeedeclare, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jsavebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newinvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newinvoice1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCombostateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombostateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombostateActionPerformed

    private void jComboyearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboyearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboyearActionPerformed

    private void jCombomonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombomonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombomonthActionPerformed

    private void jcombodateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcombodateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcombodateActionPerformed

    private void jgstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jgstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jgstActionPerformed

    private void jemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jemailActionPerformed

    private void jinvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jinvoiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jinvoiceActionPerformed

    private void jfinalamount1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfinalamount1ActionPerformed

    }//GEN-LAST:event_jfinalamount1ActionPerformed
double sgstper;
double igstper;
double cgstper;
double sgstprice;
double igstprice;
double cgstprice;
double price;
double amount;
    private void SelectitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectitemActionPerformed
    try{
          Connection con=Connect.connect();
          PreparedStatement ps=con.prepareStatement("select * from productcreated where productname=?");
          ps.setString(1,Selectitem.getSelectedItem().toString());
          ResultSet rs=ps.executeQuery();
             if(rs.next())
             {
                 rate.setText(rs.getString("price"));
                 sgstper=rs.getInt("sgstper");
                 cgstper=rs.getInt("cgstper");
                 igstper=rs.getInt("igstper"); 
                 price=rs.getInt("price");     
             }
        }
        catch(Exception ex){ JOptionPane.showMessageDialog(this, "Error"+ex,"Information Message",JOptionPane.INFORMATION_MESSAGE);}
    }//GEN-LAST:event_SelectitemActionPerformed

    private void qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtyActionPerformed
          try{  int rate1=Integer.parseInt(rate.getText());
               amount=qty.getSelectedIndex()*rate1;
               jfinalamount1.setText(""+amount);
               sgstprice=sgstper*amount/100;
               igstprice=igstper*amount/100;
               cgstprice=cgstper*amount/100;
          }
        catch(Exception ex){ JOptionPane.showMessageDialog(this, "Error"+ex,"Information Message",JOptionPane.INFORMATION_MESSAGE);         
          }
    }//GEN-LAST:event_qtyActionPerformed

    private void unitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unitActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         try{
          Connection con=Connect.connect();
          PreparedStatement ps=con.prepareStatement("delete from invoicedata where invoiceno=?");
          ps.setString(1,jinvoice.getText());
          int rs=ps.executeUpdate();
          if(rs>0)
          {
               JOptionPane.showMessageDialog(this, "Delete all Products Successfully...");
               Ttalamt.setText("0");
               snum=1;
               show_user();
          }
         }
         catch(Exception ex){
                  JOptionPane.showMessageDialog(this, ""+ex);
         }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jcustnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcustnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcustnameActionPerformed

    private void jcustemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcustemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcustemailActionPerformed

    private void jemployeedeclareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jemployeedeclareActionPerformed

    }//GEN-LAST:event_jemployeedeclareActionPerformed
public class BillPrintable implements Printable {    
  public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
  throws PrinterException 
  {    
      
                
        
      int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    

            double width = pageFormat.getImageableWidth();                    
           
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 
           
            ////////// code by alqama//////////////

            FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
        //    int idLength=metrics.stringWidth("000000");
            //int idLength=metrics.stringWidth("00");
            int idLength=metrics.stringWidth("000");
            int amtLength=metrics.stringWidth("000000");
            int qtyLength=metrics.stringWidth("00000");
            int priceLength=metrics.stringWidth("000000");
            int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;

        //    int idPosition=0;
        //    int productPosition=idPosition + idLength + 2;
        //    int pricePosition=productPosition + prodLength +10;
        //    int qtyPosition=pricePosition + priceLength + 2;
        //    int amtPosition=qtyPosition + qtyLength + 2;
            
            int productPosition = 0;
            int discountPosition= prodLength+5;
            int pricePosition = discountPosition +idLength+10;
            int qtyPosition=pricePosition + priceLength + 4;
            int amtPosition=qtyPosition + qtyLength;
            
            
              
        try{
            /*Draw Header*/
            int y=20;
            int yShift = 10;
            int headerRectHeight=15;
            int headerRectHeighta=40;
            
           
           
            ///////////////// Product price Get ///////////
                
             g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
            
            g2d.drawLine(12, y, 580, y);
            g2d.drawLine(409,y,409,480);
            g2d.drawLine(12, y, 12, 480);y+=yShift;
            g2d.drawString("                                                                  ",12,y);y+=yShift;
            g2d.drawString("                               "+jcompname.getText()+"                                          ",12,y);y+=yShift;
            
            g2d.drawString(" ------------------------------------------------------------------------------------------------",12,y);y+=headerRectHeight;
            g2d.drawString(" Invoice No:- "+jinvoice.getText(),12, y);
            g2d.drawString(" CIN NO:-"+jemail.getText(),290, y);y+=yShift;
            g2d.drawString(" Date:- "+jcombodate.getSelectedItem().toString()+"-"+jCombomonth.getSelectedItem().toString()+"-"+jComboyear.getSelectedItem().toString(),12, y);
            g2d.drawString(" GST NO:-"+jgst.getText(),290, y);y+=yShift;
            g2d.drawString(" -----------------------------------------------------------------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Product             Quantity              Rate                   Amount      ",10,y);y+=yShift;
            g2d.drawString(" --------------------------------------------------------------------------------------------------",10,y);y+=headerRectHeight;
            g2d.drawString("                                                                                                 ",10,y);y+=yShift;
            Connection con=Connect.connect();
            PreparedStatement ps= con.prepareStatement("Select * from invoicedata where invoiceno=? ");
            ps.setString(1,jinvoice.getText());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
            g2d.drawString(""+rs.getString("productname")+"",15,y);
            g2d.drawString(""+rs.getString("quantity")+"",135,y);
            g2d.drawString(""+rs.getString("price")+"",245,y);
            Double cgstprice=rs.getDouble("cgst");
            Double sgstprice=rs.getDouble("igst");
            Double igstprice=rs.getDouble("sgst");
            Double price=rs.getDouble("price");
            Double qty=rs.getDouble("quantity");
            Double discount=rs.getDouble("discount");
            price=price*qty;
            g2d.drawString(""+price+"",365,y);y+=yShift;
            price=price-discount;
            g2d.drawString(""+"cgst price"+"",15,y);
            g2d.drawString(""+cgstprice+"",365,y);y+=yShift;
            g2d.drawString(""+"sgst price"+"",15,y);
            g2d.drawString(""+sgstprice+"",365,y);y+=yShift;
            g2d.drawString(""+"igst price"+"",15,y);
            g2d.drawString(""+igstprice+"",365,y);y+=yShift;
            Double totalprice=cgstprice+sgstprice+igstprice+price;
            g2d.drawString(""+"Discount"+"",15,y);
            g2d.drawString(""+discount+"",365,y);y+=yShift;
            g2d.drawString(""+"Total"+"",15, y);
            g2d.drawString(""+totalprice+"",365, y);
            y+=yShift;    
            }
            g2d.drawString(" ---------------------------------------------------------------------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Other Charges    ",10,y);
            g2d.drawString(discount.getText(),245,y);y+=yShift;
            g2d.drawString(" Net Amount  ",10,y);
            g2d.drawString(Ttalamt.getText(),365,y);y+=yShift;
            g2d.drawString(" ---------------------------------------------------------------------------------------------------",10,y);y+=yShift;
            g2d.drawString("                                                                                                   ",10,y);y+=yShift;
            g2d.drawString(" Customer name: "+jcustname.getText()+"           ",10,y);
            g2d.drawString(" "+"CustomerEmail: "+jcustemail.getText()+"   ",225,y); y+=yShift;
            g2d.drawString(" Customer Phone: "+jphoneno.getText()+"           ",10,y);
            g2d.drawString(" CustomerAadhar: "+jaadharno.getText()+"   ",225,y); y+=yShift;
            g2d.drawString("                                                                                                    ",10,y);y+=yShift;
            g2d.drawString(" ---------------------------------------------------------------------------------------------------",10,y);y+=yShift;
            g2d.drawString("                ",10,y);y+=yShift;
            g2d.drawString(" Invoice Declared By: "+jemployeedeclare.getText()+"            ", 10, y);y+=yShift;
            g2d.drawString("                                                                  ",10,y);y+=yShift;
           g2d.drawString(" Signature:             ", 10, y);y+=yShift;
           g2d.drawString("                                                                  ",10,y);y+=yShift;
           g2d.drawString("                                                                  ",10,y);y+=yShift;
           g2d.drawLine(12, 480, 580, 480);y+=yShift;
          
                   
           
             
           
            
            g2d.setFont(new Font("Monospaced",Font.BOLD,10));
            g2d.drawString("", 30,y);y+=yShift; 
          

    }
    catch(Exception r){
    r.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
      }

   }
  public PageFormat getPageFormat(PrinterJob pj)
{
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double middleHeight =13.0;  
    double headerHeight = 4.0;                  
    double footerHeight = 4.0;                  
    double width = convert_CM_To_PPI(14.8);      //printer know only point per inch.default value is 72ppi
    double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(                    
        0,
        10,
        width-10,            
        height - convert_CM_To_PPI(0.5)
    );   //define boarder size    after that print area width is about 180 points
            
    pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
    pf.setPaper(paper);    

    return pf;
}
    protected static double convert_CM_To_PPI(double cm) {            
	        return toPPI(cm * 0.393600787);            
}
 
protected static double toPPI(double inch) {            
	        return inch * 72d;            
}
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
            String scustname=jcustname.getText();
            String semail=jemail.getText();
            String saadhar=jaadharno.getText();
            String sphone=jphoneno.getText();
        if((!scustname.equals(""))&&(!semail.equals(""))&&(!saadhar.equals(""))&&(!sphone.equals(""))){
        try{            
      String ss=jcustname.getText();
      if(ss.length()>0){
            Connection con=Connect.connect();
             PreparedStatement ps1=con.prepareStatement("Select * from invoicecustomer where invoiceno=? ");
             ps1.setString(1,jinvoice.getText());
             ResultSet rs1=ps1.executeQuery();
             int i=0;
             while(rs1.next()){
                  i=i+1;
                  save=1;
             }
             if(i==0){
             PreparedStatement ps=con.prepareStatement("insert into invoicecustomer(invoiceno,customername,aadharno,phoneno,emailid,invoicedeclaredby) values(?,?,?,?,?,?)");
             ps.setString(1,jinvoice.getText());
             ps.setString(2,jcustname.getText());
             ps.setString(3,jaadharno.getText());
             ps.setString(4,jphoneno.getText());
             ps.setString(5,jcustemail.getText());
             ps.setString(6,jemployeedeclare.getText());
             int rs=ps.executeUpdate();
             if(rs>0)
             {
                     save=1;
                     JOptionPane.showMessageDialog(this, "Save Successfully");
                     PrinterJob pj = PrinterJob.getPrinterJob();
                     pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        
                      pj.print();
             }
             }
             else{
                             PrinterJob pj = PrinterJob.getPrinterJob();
                             pj.setPrintable(new BillPrintable(),getPageFormat(pj));
                             pj.print();
        }
        }
        else{
                 JOptionPane.showMessageDialog(this, "Enter all fields Properly");
        }
        }catch (PrinterException ex) {
            ex.printStackTrace();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Error"+ex,"Information Message",JOptionPane.INFORMATION_MESSAGE);}
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jsavebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsavebuttonActionPerformed
        try{
            String ss=jcustname.getText();
           if(ss.length()>0){
            Connection con=Connect.connect();
             PreparedStatement ps1=con.prepareStatement("Select * from invoicecustomer where invoiceno=? ");
             ps1.setString(1,jinvoice.getText());
             ResultSet rs1=ps1.executeQuery();
             int i=0;
             while(rs1.next()){
                  i=i+1;
                  save=1;
             }
             if(i==0){
             PreparedStatement ps=con.prepareStatement("insert into invoicecustomer(invoiceno,customername,aadharno,phoneno,emailid,invoicedeclaredby) values(?,?,?,?,?,?)");
             ps.setString(1,jinvoice.getText());
             ps.setString(2,jcustname.getText());
             ps.setString(3,jaadharno.getText());
             ps.setString(4,jphoneno.getText());
             ps.setString(5,jcustemail.getText());
             ps.setString(6,jemployeedeclare.getText());
             int rs=ps.executeUpdate();
             if(rs>0)
             {
                     save=1;
                     JOptionPane.showMessageDialog(this, "Save Successfully");
             }     }
             else{
                             JOptionPane.showMessageDialog(this, "Already Save...");
             }
           }
           else{
                               JOptionPane.showMessageDialog(this,"Enter all fields Properly....");
           }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Error"+ex,"Information Message",JOptionPane.INFORMATION_MESSAGE);}
    
    }//GEN-LAST:event_jsavebuttonActionPerformed

    private void jaadharnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jaadharnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jaadharnoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int i=1;
          Connection  con = Connect.connect();
          int no=0;
          PreparedStatement ps1=con.prepareStatement("select * from invoicedata where productname=? and invoiceno=?");
          ps1.setString(1,Selectitem.getSelectedItem().toString());
          ps1.setString(2, jinvoice.getText());
          ResultSet rs1= ps1.executeQuery();
          while(rs1.next()){
                   no=no+1;
          }
          if(no<1){
          PreparedStatement ps=con.prepareStatement("insert into invoicedata(sno,productname,price,sgst,cgst,igst,amount,unit,quantity,invoiceno,discount) values(?,?,?,?,?,?,?,?,?,?,?)");
          ps.setInt(1,snum);
          ps.setString(2,Selectitem.getSelectedItem().toString());
          ps.setString(3,rate.getText());
          ps.setDouble(4,sgstprice);
          ps.setDouble(6,igstprice);
          ps.setDouble(5,cgstprice);
          ps.setString(7,jfinalamount1.getText());
          ps.setString(8,unit.getSelectedItem().toString());
          ps.setString(9,qty.getSelectedItem().toString());
          ps.setString(10,jinvoice.getText());
          ps.setString(11,discount.getText());
          int rs=ps.executeUpdate();
          if(rs>0){
               Selectitem.setSelectedIndex(0);
               qty.setSelectedIndex(0);
               jfinalamount1.setText("");
               rate.setText("");
               unit.setSelectedIndex(0);
               discount.setText("0");
               snum=1;
               show_user();
          }  }
          else{
              snum=1;
              Selectitem.setSelectedIndex(0);
               qty.setSelectedIndex(0);
               jfinalamount1.setText("");
               rate.setText("");
               unit.setSelectedIndex(0);
               discount.setText("0");
               show_user();
              JOptionPane.showMessageDialog(this,"Product is already exist :");
          }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Error"+ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
int save=0;
    private void newinvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newinvoiceActionPerformed
     if(save==1){
               snum=1;
               save=0;
               filldetail();
               Selectitem.setSelectedIndex(0);
               jfinalamount1.setText("");
               rate.setText("");
               jcustname.setText("");
               jaadharno.setText("");
               jphoneno.setText("");
               jcustemail.setText("");
               Ttalamt.setText("");
               discount.setText("0");
               show_user();
     }
     else{
           JOptionPane.showMessageDialog(this, "First Save the Invoice...");
     }
    }//GEN-LAST:event_newinvoiceActionPerformed

    private void newinvoice1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newinvoice1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_newinvoice1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         try{
          Connection con=Connect.connect();
          PreparedStatement ps=con.prepareStatement("delete from invoicedata where invoiceno=? and productname=?");
          ps.setString(1,jinvoice.getText());
          ps.setString(2,Selectitem.getSelectedItem().toString());
          int rs=ps.executeUpdate();
          if(rs>0)
          {
               Selectitem.setSelectedIndex(0);
               qty.setSelectedIndex(0);
               jfinalamount1.setText("");
               rate.setText("");
               unit.setSelectedIndex(0);
               discount.setText("0");
               Ttalamt.setText("0");
               JOptionPane.showMessageDialog(this, "Delete Product Successfully...");
               snum=1;
               show_user();
          }
         }
         catch(Exception ex){
                  JOptionPane.showMessageDialog(this,""+ex);
         }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
          Connection con=Connect.connect();
          PreparedStatement ps=con.prepareStatement("update invoicedata set quantity=?,price=?,amount=?,cgst=?,sgst=?,igst=?,discount=? where invoiceno=? and productname=?");
          ps.setString(1,qty.getSelectedItem().toString());
          ps.setString(2,rate.getText());
          ps.setString(3,jfinalamount1.getText());
          ps.setDouble(4,sgstprice);
          ps.setDouble(6,igstprice);
          ps.setDouble(5,cgstprice);
          ps.setString(7,discount.getText());
          ps.setString(8,jinvoice.getText());
          ps.setString(9,Selectitem.getSelectedItem().toString());

          int rs=ps.executeUpdate();
          if(rs>0)
          {
               Selectitem.setSelectedIndex(0);
               qty.setSelectedIndex(0);
               jfinalamount1.setText("");
               rate.setText("");
               discount.setText("0");
               unit.setSelectedIndex(0);
              JOptionPane.showMessageDialog(this, "Update Product Successfully...");
               snum=1;
               show_user();
          }
         }
         catch(Exception ex){
                  JOptionPane.showMessageDialog(this,""+ex);
         } 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void TtalamtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TtalamtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TtalamtActionPerformed

    private void discountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discountActionPerformed

    private void jphonenoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jphonenoFocusLost
        // TODO add your handling code here:
     /*   String ss=jphoneno.getText();
        
        try{
            int sm=Integer.parseInt(ss);
         int lengo=ss.length();
            if(lengo!=10){JOptionPane.showMessageDialog(this,"Phone number should be of length 10 and can not be blank");}}
        catch(Exception ex){JOptionPane.showMessageDialog(this,"Phone number should contain only number");
        jphoneno.setText("");}
       */
           
        
    }//GEN-LAST:event_jphonenoFocusLost

    private void jcustemailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcustemailFocusLost
        // TODO add your handling code here:
        String ema=jcustemail.getText();
        int emlen=ema.length();
        if(emlen<1){
            JOptionPane.showMessageDialog(this,"Email Column Should Not Be Blank");
        }else if(ema.matches("\\w{1,}@\\w{1,}.[a-zA-Z]{1,}")==false){
            JOptionPane.showMessageDialog(this,"Correct Customer Email");
        jcustemail.setText("");}
    }//GEN-LAST:event_jcustemailFocusLost

    private void jaadharnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jaadharnoFocusLost
        // TODO add your handling code here:
         String ema=jaadharno.getText();
        int emlen=ema.length();
        if(emlen<1){
            JOptionPane.showMessageDialog(this,"Aadhar Column Should Not Be Blank");
        }else if(ema.matches("[0-9]{12}")==false){
            JOptionPane.showMessageDialog(this,"Correct Customer Aadhar");
            jaadharno.setText("");}
        
    }//GEN-LAST:event_jaadharnoFocusLost

    private void jcustnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcustnameFocusLost
         String ema=jcustname.getText();
        int emlen=ema.length();
        if(emlen<1){
            JOptionPane.showMessageDialog(this,"Customer Name Column Should Not Be Blank");
        }else if(ema.matches("[a-zA-Z]{1,}\\s{0,1}[a-zA-Z]{0,}")==false){
            JOptionPane.showMessageDialog(this,"Correct Customer Name");
            jcustname.setText("");
        }
    }//GEN-LAST:event_jcustnameFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Selectitem;
    private javax.swing.JTextField Ttalamt;
    private javax.swing.JTextField discount;
    private javax.swing.JTable invoicetable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jCombomonth;
    private javax.swing.JComboBox jCombostate;
    private javax.swing.JComboBox jComboyear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jaadharno;
    private javax.swing.JComboBox jcombodate;
    private javax.swing.JTextField jcompname;
    private javax.swing.JTextField jcustemail;
    private javax.swing.JTextField jcustname;
    private javax.swing.JTextField jemail;
    private javax.swing.JTextField jemployeedeclare;
    private javax.swing.JTextField jfinalamount1;
    private javax.swing.JTextField jgst;
    private javax.swing.JTextField jinvoice;
    private javax.swing.JTextField jphoneno;
    private javax.swing.JButton jsavebutton;
    private javax.swing.JButton newinvoice;
    private javax.swing.JButton newinvoice1;
    private javax.swing.JComboBox qty;
    private javax.swing.JTextField rate;
    private javax.swing.JTextField snumber;
    private javax.swing.JComboBox unit;
    // End of variables declaration//GEN-END:variables

}
