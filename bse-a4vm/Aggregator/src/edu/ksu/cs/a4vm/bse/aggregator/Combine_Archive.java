/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ksu.cs.a4vm.bse.aggregator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shubh Chopra
 */
public class Combine_Archive extends javax.swing.JFrame {

    /**
     * Creates new form Combine2
     */
    /**
     * Creates new form Combine
     */
    int number_of_rows1=0;
    int number_of_rows2=0;
    String File1;
    String File2;
    String bull1 [][] ;
    String bull2 [][] ;
    String bull [][];
    public Combine_Archive() {
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

        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setText("File 1 location");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setText("File 2 address");
        jTextField2.setToolTipText("");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton1.setText("Combine");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193))
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed

    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            File1 = jTextField1.getText();
            File2 = jTextField2.getText();
            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ",";
            br = new BufferedReader(new FileReader(File1));

            while ((line = br.readLine()) != null) {

                // use comma as separator
                number_of_rows1++;
            }

            bull1=new String [number_of_rows1-1][];
            int i=0;
            BufferedReader br1 = null;
            br1 = new BufferedReader(new FileReader(File1));
            while ((line = br1.readLine()) != null) {

                // use comma as separator
                String Bull[] = line.split(cvsSplitBy);
                
                if(number_of_rows1!=i+1)
                bull1[i]=new String [102];
                for(int j=0; j<101;j++)
                {
                    if(number_of_rows1!=i+1)
                    bull1[i][j]=Bull[j];
                  
                }
                i++;
            }
           
            BufferedReader br2 = null;
            br2 = new BufferedReader(new FileReader(File2));

            while ((line = br2.readLine()) != null) {

                // use comma as separator
                number_of_rows2++;
            }
            System.out.println(number_of_rows2);
            bull2=new String [number_of_rows2-1][];
            i=0;
            BufferedReader br3 = null;
            br3 = new BufferedReader(new FileReader(File2));
            while ((line = br3.readLine()) != null) {

                // use comma as separator
                String Bull[] = line.split(cvsSplitBy);
                if(number_of_rows2!=i+1)
                bull2[i]=new String [102];
                for(int j=0; j<=101;j++)
                {
                    if(number_of_rows2!=i+1)
                    bull2[i][j]=Bull[j];
                }
                i++;
            }
             System.out.println("345");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Combine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Combine.class.getName()).log(Level.SEVERE, null, ex);
        }
        bull= new String[number_of_rows1+number_of_rows2-2][103];

        for(int m=0;m<number_of_rows1+number_of_rows2-2;m++)
        {
            for(int n=0;n<103;n++)
            {
                bull[m][n]="";
            }
        }
         System.out.println("567");
        for(int m=0;m<number_of_rows1-1;m++)
        {
            for(int n=0;n<102;n++)
            {
                bull[m][n]=bull1[m][n];
            }
        }
        int temp=0;
        int k;
        for(int j=1;j<number_of_rows2-1;j++)
        {
            for(k=1;k<number_of_rows1-1;k++)
            {
                if(bull[k][91].equals(bull2[j][91]))
                {

                    for(int m=0;m<102;m++)
                    {

                        if("".equals(bull[k][m]))
                        {
                            bull[k][m]=bull2[j][m] ;
                            continue;
                        }
                        String temp1=bull[k][m];
                        if("".equals(bull[k][m])!=true && "".equals(bull2[j][m])!=true && temp1.equals(bull2[j][m])!=true )
                        {
                            bull[k][m]=bull[k][m]+" "+bull2[j][m];
                            bull[k][102]= bull[k][102]+","+m;

                        }

                    }

                }
                if(bull[k][91].equals(bull2[j][91])!=true && k==number_of_rows1-2)
                {
                    for (int n=0;n<102;n++)
                    {
                        bull[number_of_rows1+temp-1][n]=bull2[j][n];
                    }
                }
            }
        }
        System.out.println("Hello3");
        for(int m=0;m<number_of_rows1+number_of_rows2-2;m++)
        {
            for(int n=0;n<102;n++)
            {
                System.out.print(bull[m][n]+" ");
            }
            System.out.println();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Combine_Archive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Combine_Archive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Combine_Archive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Combine_Archive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Combine_Archive().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
