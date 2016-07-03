/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cis_690_report;
import com.itextpdf.text.BadElementException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Shubh Chopra
 */
public class Reporter extends javax.swing.JFrame {
String File1;
   BufferedReader br1;
   BufferedReader b1;
   String bull [][];
   String bull1 [][];
   String File3;
    /**String File1;
     * Creates new form Reporter
     */
    public Reporter() {
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

        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setText("File 1 location");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Generate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Browse");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Age");

        jCheckBox2.setText("Scrotal");

        jCheckBox3.setText("Motality");

        jCheckBox4.setText("Comments");

        jCheckBox5.setText("Classification");

        jCheckBox6.setText("Normal");

        jCheckBox7.setText("M2");

        jCheckBox8.setText("M3");

        jCheckBox9.setText("M4");

        jCheckBox10.setText("M5");

        jCheckBox11.setText("M6");

        jCheckBox12.setText("M7");

        jCheckBox13.setText("M8");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jCheckBox6))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox5)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jCheckBox7))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jCheckBox8))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jCheckBox9)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox11)
                                    .addComponent(jCheckBox10)
                                    .addComponent(jCheckBox12)
                                    .addComponent(jCheckBox13))))
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox6)
                    .addComponent(jCheckBox10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox7)
                    .addComponent(jCheckBox11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox8)
                    .addComponent(jCheckBox12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox9)
                    .addComponent(jCheckBox13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        JFileChooser choose =new JFileChooser();
       
        choose.showOpenDialog(null);
       File f = choose.getSelectedFile();
        String path = f.getAbsolutePath();
        jTextField1.setText(path);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
File1 = jTextField1.getText();
 JFileChooser chooser =new JFileChooser();
            int option = chooser.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
            if(chooser.getSelectedFile()!=null){
            File3 = chooser.getSelectedFile().getAbsolutePath();
            }
            File file = new File(File1);
            
            
   try {
              br1 = new BufferedReader(new FileReader(file));
                     b1 = new BufferedReader(new FileReader(file));
                } catch (FileNotFoundException ex) {
                   
                }
   int number_of_rows1 = 0;
   String line = "";
   String cvsSplitBy = ",";
    try {
        while ((line=br1.readLine()) != null) {
            
            // use comma as separator
            number_of_rows1++;   
        }
        
    } catch (IOException ex) {
        Logger.getLogger(Reporter.class.getName()).log(Level.SEVERE, null, ex);
    }

           bull1=new String [number_of_rows1][];
            int k=0;
            BufferedReader br2 = null;
                try {
                    br2 = new BufferedReader(new FileReader(file));
                } catch (FileNotFoundException ex) {}
                try {
                    while ((line = br2.readLine()) != null) {
                        
                        // use comma as separator
                        String Bull[] = line.split(",");
                        System.out.println(Bull.length);
                        bull1[k]=new String [93];
                        if(Bull.length<10)
                        {    for(int j=0; j<92;j++)
                        {
                            bull1[k][j]="";
                            //System.out.println(Bull[j]);
                        }   
                        }
                        else{
                        for(int j=0; j<Bull.length;j++)
                        {
                            bull1[k][j]=Bull[j];
                            //System.out.println(Bull[j]);
                        }
                        }
                        k++;
                    }   } catch (IOException ex) {
                    }
  Document doc = new Document();
  PdfWriter docWriter = null;

  DecimalFormat df = new DecimalFormat("0.00");

  try {
   
   //special font sizes
   Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 8, Font.BOLD, new BaseColor(0, 0, 0)); 
   Font bf12 = new Font(FontFamily.TIMES_ROMAN, 6); 
   Font bfBold20 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);

   //file path
 
   docWriter = PdfWriter.getInstance(doc , new FileOutputStream(File3));
   
   //document header attributes
   doc.addAuthor("Shubh Chopra");
   doc.addCreationDate();
   doc.addProducer();
   doc.addCreator("Shubh Chopra");
   doc.addTitle("BES");
   doc.setPageSize(PageSize.LETTER.rotate());
  
   //open document
   doc.open();
  //create a paragraph
   Paragraph paragraph = new Paragraph("BULL EVALUATION\n\n");
   paragraph.setFont(bfBold20);
   paragraph.setAlignment(Element.ALIGN_CENTER);
   
   Image img = Image.getInstance("VETMED.png");
   
   img.scaleToFit(300f, 150f);
   doc.add(paragraph);
   PdfPTable table1 = new PdfPTable(2);
    table1.setWidthPercentage(100);
    PdfPCell cell = new PdfPCell(img);
    cell.setBorder(PdfPCell.NO_BORDER);
    table1.addCell(cell);
     
    String temp1="\tOwner: "+bull1[1][78]+" "+bull1[1][79]+"\n\n\tRanch: "+bull1[1][80]+"\n\n\tAddress: "+bull1[1][71]+"\n\n\tCity: "+bull1[1][73]+"\n\n\tState: "+bull1[1][76]+"\tZip: "+bull1[1][77]+"\n\n\tPhone: "+bull1[1][75]+"\n\n";
    
    table1.addCell(getCell(temp1, PdfPCell.ALIGN_LEFT));
            doc.add(table1);
  
   boolean[] checkboxs = {jCheckBox1.isSelected(),jCheckBox2.isSelected(),jCheckBox3.isSelected(),jCheckBox4.isSelected(),jCheckBox5.isSelected(),jCheckBox6.isSelected(),jCheckBox7.isSelected(),jCheckBox8.isSelected(),jCheckBox9.isSelected(),jCheckBox10.isSelected(),jCheckBox11.isSelected(),jCheckBox12.isSelected(),jCheckBox13.isSelected()};
   //specify column widths
   int temp = 0;
   for (int x=0;x<checkboxs.length;x++)
   {
       if(checkboxs[x]==true)
       {
           temp++;
       }
   }
    float[] columnWidths =  new  float[temp+5];
    for (int x=0;x<columnWidths.length;x++)
   {
       if(x<5)
        columnWidths[x] = 1f;
       else if(jCheckBox1.isSelected() && x==5)
           columnWidths[x] = 2f;
       else if(jCheckBox2.isSelected() && jCheckBox3.isSelected() && x==7)
           columnWidths[x] = 2f;
       else if(!jCheckBox2.isSelected() && jCheckBox3.isSelected() && x==6)
           columnWidths[x] = 2f;
       else if (jCheckBox4.isSelected() && x==columnWidths.length-2)
            columnWidths[x] = 2f;
        else if (jCheckBox5.isSelected() && x==columnWidths.length-1)
            columnWidths[x] = 2f;
        else 
            columnWidths[x] = 1f;
   }
          
   //create PDF table with the given widths
   PdfPTable table = new PdfPTable(columnWidths);
   // set table width a percentage of the page width
   table.setWidthPercentage(90f);
   Reporter re;
            re = new Reporter();
   //insert column headings
   re.insertCell(table, "ID-Tag", Element.ALIGN_CENTER, 1, bfBold12);
   re.insertCell(table, "ID-Tatto", Element.ALIGN_CENTER, 1, bfBold12);
   re.insertCell(table, "ID-RFID", Element.ALIGN_CENTER, 1, bfBold12);
   re.insertCell(table, "ID-Brand", Element.ALIGN_CENTER, 1, bfBold12);
   re.insertCell(table, "ID-Other", Element.ALIGN_CENTER, 1, bfBold12);
   if(jCheckBox1.isSelected())
    re.insertCell(table, "Age", Element.ALIGN_CENTER, 1, bfBold12);
   if(jCheckBox2.isSelected())
    re.insertCell(table, "Scrotal", Element.ALIGN_CENTER, 1, bfBold12);
   if(jCheckBox3.isSelected())
    re.insertCell(table, "Motility", Element.ALIGN_CENTER, 1, bfBold12);
   if(jCheckBox6.isSelected())
    re.insertCell(table, "Normal", Element.ALIGN_CENTER, 1, bfBold12);
   if(jCheckBox7.isSelected())
    re.insertCell(table, "M2", Element.ALIGN_CENTER, 1, bfBold12);
   if(jCheckBox8.isSelected())
    re.insertCell(table, "M3", Element.ALIGN_CENTER, 1, bfBold12);
   if(jCheckBox9.isSelected())
    re.insertCell(table, "M4", Element.ALIGN_CENTER, 1, bfBold12);
   if(jCheckBox10.isSelected())
    re.insertCell(table, "M5", Element.ALIGN_CENTER, 1, bfBold12);
   if(jCheckBox11.isSelected())
    re.insertCell(table, "M6", Element.ALIGN_CENTER, 1, bfBold12);
   if(jCheckBox12.isSelected())
    re.insertCell(table, "M7", Element.ALIGN_CENTER, 1, bfBold12);
   if(jCheckBox13.isSelected())
    re.insertCell(table, "M8", Element.ALIGN_CENTER, 1, bfBold12);
   if(jCheckBox4.isSelected())
    re.insertCell(table, "Comments", Element.ALIGN_CENTER, 1, bfBold12);
   if(jCheckBox5.isSelected())
    re.insertCell(table, "Classification", Element.ALIGN_CENTER, 1, bfBold12);
   
   table.setHeaderRows(1);

   //insert an empty row
  
   //create section heading by cell merging
   
   //just some random data to fill 
   for(int x=1; x<number_of_rows1; x++){
    
    re.insertCell(table, bull1[x][7] , Element.ALIGN_CENTER, 1, bf12);
    re.insertCell(table, bull1[x][8] , Element.ALIGN_CENTER, 1, bf12);
    re.insertCell(table, bull1[x][6] , Element.ALIGN_CENTER, 1, bf12);
    re.insertCell(table, bull1[x][2] , Element.ALIGN_CENTER, 1, bf12);
    re.insertCell(table, bull1[x][9] , Element.ALIGN_CENTER, 1, bf12);
    if(jCheckBox1.isSelected())
        re.insertCell(table, bull1[x][0] , Element.ALIGN_CENTER, 1, bf12);
    if(jCheckBox2.isSelected())
        re.insertCell(table, bull1[x][70] , Element.ALIGN_CENTER, 1, bf12);
    if(jCheckBox3.isSelected())
        re.insertCell(table, bull1[x][61] , Element.ALIGN_CENTER, 1, bf12);
    if(jCheckBox6.isSelected())
        re.insertCell(table, bull1[x][43] , Element.ALIGN_CENTER, 1, bf12);
    if(jCheckBox7.isSelected())
        re.insertCell(table, bull1[x][45] , Element.ALIGN_CENTER, 1, bf12);
    if(jCheckBox8.isSelected())
        re.insertCell(table, bull1[x][47] , Element.ALIGN_CENTER, 1, bf12);
    if(jCheckBox9.isSelected())
        re.insertCell(table, bull1[x][49] , Element.ALIGN_CENTER, 1, bf12);
    if(jCheckBox10.isSelected())
        re.insertCell(table, bull1[x][51] , Element.ALIGN_CENTER, 1, bf12);
    if(jCheckBox11.isSelected())
        re.insertCell(table, bull1[x][53] , Element.ALIGN_CENTER, 1, bf12);
    if(jCheckBox12.isSelected())
        re.insertCell(table, bull1[x][55] , Element.ALIGN_CENTER, 1, bf12);
    if(jCheckBox13.isSelected())
        re.insertCell(table, bull1[x][57] , Element.ALIGN_CENTER, 1, bf12);
    if(jCheckBox4.isSelected())
        re.insertCell(table, "" , Element.ALIGN_CENTER, 1, bf12);
    if(jCheckBox5.isSelected())
        re.insertCell(table, bull1[x][35] , Element.ALIGN_CENTER, 1, bf12);
   }
   doc.add(table);
   

  }
  catch (DocumentException dex)
  {
   dex.printStackTrace();
  } catch (FileNotFoundException ex) {
        Logger.getLogger(Reporter.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(Reporter.class.getName()).log(Level.SEVERE, null, ex);
    }
  finally
  {
   if (doc != null){
    //close the document
    doc.close();
   }
   if (docWriter != null){
    //close the writer
    docWriter.close();
   }
  }
          // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])  {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Reporter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reporter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reporter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reporter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Reporter().setVisible(true);
            }
        });
      
        
    }
public PdfPCell getCell(String text, int alignment) {
    PdfPCell cell = new PdfPCell(new Phrase(text));
    cell.setPadding(0);
    cell.setHorizontalAlignment(alignment);
    cell.setBorder(PdfPCell.NO_BORDER);
    return cell;
}
    

 
 private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
  
  //create a new cell with the specified Text and Font
  PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
  //set the cell alignment
  cell.setHorizontalAlignment(align);
  //set the cell column span in case you want to merge two or more cells
  cell.setColspan(colspan);
  //in case there is no text and you wan to create an empty row
  if(text.trim().equalsIgnoreCase("")){
   cell.setMinimumHeight(10f);
  }
  //add the call to the table
  table.addCell(cell);
  
 }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
