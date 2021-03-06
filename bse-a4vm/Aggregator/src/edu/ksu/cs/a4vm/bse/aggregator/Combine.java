/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ksu.cs.a4vm.bse.aggregator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Shubh Chopra
 */
class ColumnComparator implements Comparator {

    int columnToSort;

    ColumnComparator(int columnToSort) {
        this.columnToSort = columnToSort;
    }

    //overriding compare method

    public int compare(Object o1, Object o2) {
        String[] row1 = (String[]) o1;
        String[] row2 = (String[]) o2;
        //compare the columns to sort
        return row1[columnToSort].compareTo(row2[columnToSort]);
    }
}

public class Combine extends javax.swing.JFrame {

    /**
     * Creates new form Combine
     */
    int number_of_rows1 = 0;
    int number_of_rows2 = 0;
    String File1;
    String File3;
    String File2;
    String header[];
    String bull1[][];
    String bull2[];
    String bull[][];
    String bull3[][];
    File[] listoffiles;
    File[] listoffiles1;

    public Combine() {
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setText("File 1 location");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Combine and save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Browse");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Choose a folder");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Choose files");

        jButton3.setText("Browse");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Aggregator");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jButton3))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(86, 86, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    public void combineandsave(String s1, String s2) {

        if (!"".equals(s1)) {
            File1 = s1;
            File folder = new File(File1);
            listoffiles = folder.listFiles();
        } else if (!"".equals(s2)) {
            listoffiles = listoffiles1;
        }

        BufferedReader br = null;
        String line = "";

        JFileChooser chooser = new JFileChooser();
        int option = chooser.showSaveDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            if (chooser.getSelectedFile() != null) {
                File3 = chooser.getSelectedFile().getAbsolutePath();
            }
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(File3, "UTF-8");
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                Logger.getLogger(Combine.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedReader br1 = null;
            String s;
            header = new String[120];
            for (int x = 0; x < 120; x++) {
                header[x] = "";
            }
            for (int j = 0; j < listoffiles.length; j++) {
                File f = listoffiles[j];
                BufferedReader br2 = null;
                try {
                    br2 = new BufferedReader(new FileReader(f));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Combine.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    int temp2 = 0;
                    while ((line = br2.readLine()) != null) {

                        // use comma as separator
                        while (temp2 == 0) {
                            String Bull[] = line.split(",");
                            //  System.out.println(Bull.length);
                            if (j == 0) {
                                for (int i = 0; i < Bull.length; i++) {
                                    header[i] = Bull[i];
                                }
                            }
                            int k = 77;
                            for (int i = 77; i < Bull.length;) {

                                if (Bull[i].equals(header[k])) {
                                    i++;
                                    k = 77;
                                } else if (!Bull[i].equals(header[k]) && !header[k].equals("")) {
                                    k++;
                                } else if (!Bull[i].equals(header[k]) && header[k].equals("")) {
                                    header[k] = Bull[i];
                                    i++;
                                    k = 77;

                                }
                            }
                            temp2 = 1;

                        }
                        number_of_rows1++;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Combine.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Incorrect File format, please researt the app");
                }
            }
            for (int y = 0; y < header.length; y++) {
                //  System.out.print(header[y]);
            }
            number_of_rows1 = number_of_rows1 - listoffiles.length;
            //    System.out.println(number_of_rows1);
            bull1 = new String[number_of_rows1][];
            int l = 0;
            for (int i = 0; i < listoffiles.length; i++) {

                File file = listoffiles[i];

                try {
                    br1 = new BufferedReader(new FileReader(file));
                    br = new BufferedReader(new FileReader(file));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Combine.class.getName()).log(Level.SEVERE, null, ex);
                }

                BufferedReader br2 = null;
                try {
                    br2 = new BufferedReader(new FileReader(file));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Combine.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    int temp = 0;
                    while ((line = br2.readLine()) != null) {

                        // use comma as separator
                        if (temp == 0) {
                            String Bull[] = line.split(",");
                            //  System.out.println(Bull.length);
                            bull2 = new String[Bull.length + 1];
                            for (int j = 0; j < Bull.length; j++) {
                                bull2[j] = Bull[j];
                            }
                        }

                        if (temp != 0) {
                            String Bull[] = line.split(",");
                            //  System.out.println(Bull.length);
                            bull1[l] = new String[Bull.length + 1];
                            for (int j = 0; j < Bull.length; j++) {
                                if (j < 77) {
                                    bull1[l][j] = Bull[j];
                                }
                                if (j >= 77) {
                                    bull1[l][j] = Bull[j] + ":" + bull2[j];
                                }
                            }
                            l++;
                        }
                        temp++;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Combine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Arrays.sort(bull1, new ColumnComparator(7));
            for (int m = 0; m < number_of_rows1; m++) {
                for (int y = 0; y < bull1[m].length; y++) {
                    //      System.out.print(bull1[m][y]);
                }
                //System.out.println();
            }
            int flag = -1;
            for (int x = 0; x < number_of_rows1; x++) {
                String collision = "";
                for (int y = 0; y < number_of_rows1; y++) {
                    if (x != y) {
                        if ((bull1[x][2].equals(bull1[y][2]) && !bull1[x][2].equals("")) || (bull1[x][6].equals(bull1[y][6]) && !bull1[x][6].equals("")) || (bull1[x][7].equals(bull1[y][7]) && !bull1[x][7].equals("")) || (bull1[x][8].equals(bull1[y][8]) && !bull1[x][8].equals("")) || (bull1[x][9].equals(bull1[y][9]) && !bull1[x][9].equals(""))) {
                            for (int a = 0; a < bull1[y].length - 1 && a < bull1[x].length - 1; a++) {
                                if (!bull1[x][a].equals(bull1[y][a]) && !bull1[x][a].equals("") && !bull1[y][a].equals("")) {
                                    if (a != 34 && a != 65) {
                                        collision += header[a] + " ";

                                    }

                                }
                            }
                            flag = y;
                            break;
                        }
                    }
                }
                bull1[x][bull1[x].length - 1] = collision;
                if (flag != -1) {
                    for (int a = 0; a < bull1[x].length - 1 && a < bull1[flag].length - 1; a++) {
                        if (a != 34 && a != 65) {
                            if (bull1[flag][a].equals("")) {
                                bull1[flag][a] = bull1[x][a];
                                //          System.out.println(bull1[x][a]+" "+bull1[flag][a]);
                                continue;
                            }
                            if (bull1[x][a].equals("")) {
                                bull1[x][a] = bull1[flag][a];
                                //    System.out.println(bull1[flag][a]);
                            }
                        }
                    }
                }
                flag = -1;
                //      System.out.println(collision);
            }

            int temp = 0;

            int x = 0;

            for (int y = 0; y < header.length; y++) {
                if (!header[y].equals("")) {
                    writer.print(header[y] + ",");
                } else {
                    writer.println();
                    break;
                }
            }

            while (x < number_of_rows1) {
                s = "";
                temp++;
                for (int b = 0; b < 77; b++) {
                    if (b < 77) {
                        s += bull1[x][b] + ",";
                    }
                }
                for (int c = 77; c < header.length; c++) {
                            //int a = header.length;
                    // int b = bull1[x].length;
                    int flag1 = 0;
                    for (int d = 77; d < bull1[x].length; d++) {
                        if (bull1[x][d] != null && !bull1[x][d].equals("")) {
                            if (bull1[x][d].indexOf(':') != -1) {
                                String[] parts = bull1[x][d].split(":", 2);
                                String string1 = parts[0];
                                String string2 = parts[1];
                                if (header[c].equals(string2)) {
                                    flag1 = 1;
                                    s += string1 + ",";
                                    break;
                                }
                            }
                        }
                    }
                    if (flag1 == 0) {
                        s += ",";
                    }
                    if (header[c + 1].equals("")) {
                        break;
                    }
                }
                if (bull1[x][bull1[x].length - 1] != "") {
                    writer.print(s + "," + bull1[x][bull1[x].length - 1]);
                } else {
                    writer.print(s);
                }
                writer.println();
                x++;
            }
            writer.close();
            JOptionPane.showMessageDialog(null, "Aggregation Successful");
        }
    }

    public void combineandsavetest(String s1, String s2) {

        if (!"".equals(s1)) {
            File1 = s1;
            File folder = new File(File1);
            listoffiles = folder.listFiles();
        } else if (!"".equals(s2)) {
            listoffiles = listoffiles1;
        }

        BufferedReader br = null;
        String line = "";

        File3 = s1 + "/actual_result.csv";

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(File3, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(Combine.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br1 = null;
        String s;
        header = new String[120];
        for (int x = 0; x < 120; x++) {
            header[x] = "";
        }
        for (int j = 0; j < listoffiles.length; j++) {
            File f = listoffiles[j];
            BufferedReader br2 = null;
            try {
                br2 = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Combine.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                int temp2 = 0;
                while ((line = br2.readLine()) != null) {

                    // use comma as separator
                    while (temp2 == 0) {
                        String Bull[] = line.split(",");
                        //  System.out.println(Bull.length);
                        if (j == 0) {
                            for (int i = 0; i < Bull.length; i++) {
                                header[i] = Bull[i];
                            }
                        }
                        int k = 77;
                        for (int i = 77; i < Bull.length;) {

                            if (Bull[i].equals(header[k])) {
                                i++;
                                k = 77;
                            } else if (!Bull[i].equals(header[k]) && !header[k].equals("")) {
                                k++;
                            } else if (!Bull[i].equals(header[k]) && header[k].equals("")) {
                                header[k] = Bull[i];
                                i++;
                                k = 77;

                            }
                        }
                        temp2 = 1;

                    }
                    number_of_rows1++;
                }
            } catch (IOException ex) {
                Logger.getLogger(Combine.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Incorrect File format, please researt the app");
            }
        }
        for (int y = 0; y < header.length; y++) {
            //  System.out.print(header[y]);
        }
        number_of_rows1 = number_of_rows1 - listoffiles.length;
        //    System.out.println(number_of_rows1);
        bull1 = new String[number_of_rows1][];
        int l = 0;
        for (int i = 0; i < listoffiles.length; i++) {

            File file = listoffiles[i];

            try {
                br1 = new BufferedReader(new FileReader(file));
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Combine.class.getName()).log(Level.SEVERE, null, ex);
            }

            BufferedReader br2 = null;
            try {
                br2 = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Combine.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                int temp = 0;
                while ((line = br2.readLine()) != null) {

                    // use comma as separator
                    if (temp == 0) {
                        String Bull[] = line.split(",");
                        //  System.out.println(Bull.length);
                        bull2 = new String[Bull.length + 1];
                        for (int j = 0; j < Bull.length; j++) {
                            bull2[j] = Bull[j];
                        }
                    }

                    if (temp != 0) {
                        String Bull[] = line.split(",");
                        //  System.out.println(Bull.length);
                        bull1[l] = new String[Bull.length + 1];
                        for (int j = 0; j < Bull.length; j++) {
                            if (j < 77) {
                                bull1[l][j] = Bull[j];
                            }
                            if (j >= 77) {
                                bull1[l][j] = Bull[j] + ":" + bull2[j];
                            }
                        }
                        l++;
                    }
                    temp++;
                }
            } catch (IOException ex) {
                Logger.getLogger(Combine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Arrays.sort(bull1, new ColumnComparator(7));
        for (int m = 0; m < number_of_rows1; m++) {
            for (int y = 0; y < bull1[m].length; y++) {
                //      System.out.print(bull1[m][y]);
            }
            //System.out.println();
        }
        int flag = -1;
        for (int x = 0; x < number_of_rows1; x++) {
            String collision = "";
            for (int y = 0; y < number_of_rows1; y++) {
                if (x != y) {
                    if ((bull1[x][2].equals(bull1[y][2]) && !bull1[x][2].equals("")) || (bull1[x][6].equals(bull1[y][6]) && !bull1[x][6].equals("")) || (bull1[x][7].equals(bull1[y][7]) && !bull1[x][7].equals("")) || (bull1[x][8].equals(bull1[y][8]) && !bull1[x][8].equals("")) || (bull1[x][9].equals(bull1[y][9]) && !bull1[x][9].equals(""))) {
                        for (int a = 0; a < bull1[y].length - 1 && a < bull1[x].length - 1; a++) {
                            if (!bull1[x][a].equals(bull1[y][a]) && !bull1[x][a].equals("") && !bull1[y][a].equals("")) {
                                if (a != 34 && a != 65) {
                                    collision += header[a] + " ";

                                }

                            }
                        }
                        flag = y;
                        break;
                    }
                }
            }
            bull1[x][bull1[x].length - 1] = collision;
            if (flag != -1) {
                for (int a = 0; a < bull1[x].length - 1 && a < bull1[flag].length - 1; a++) {
                    if (a != 34 && a != 65) {
                        if (bull1[flag][a].equals("")) {
                            bull1[flag][a] = bull1[x][a];
                            //          System.out.println(bull1[x][a]+" "+bull1[flag][a]);
                            continue;
                        }
                        if (bull1[x][a].equals("")) {
                            bull1[x][a] = bull1[flag][a];
                            //    System.out.println(bull1[flag][a]);
                        }
                    }
                }
            }
            flag = -1;
            //      System.out.println(collision);
        }

        int temp = 0;

        int x = 0;

        for (int y = 0; y < header.length; y++) {
            if (!header[y].equals("")) {
                writer.print(header[y] + ",");
            } else {
                writer.println();
                break;
            }
        }

        while (x < number_of_rows1) {
            s = "";
            temp++;
            for (int b = 0; b < 77; b++) {
                if (b < 77) {
                    s += bull1[x][b] + ",";
                }
            }
            for (int c = 77; c < header.length; c++) {
                            //int a = header.length;
                // int b = bull1[x].length;
                int flag1 = 0;
                for (int d = 77; d < bull1[x].length; d++) {
                    if (bull1[x][d] != null && !bull1[x][d].equals("")) {
                        if (bull1[x][d].indexOf(':') != -1) {
                            String[] parts = bull1[x][d].split(":", 2);
                            String string1 = parts[0];
                            String string2 = parts[1];
                            if (header[c].equals(string2)) {
                                flag1 = 1;
                                s += string1 + ",";
                                break;
                            }
                        }
                    }
                }
                if (flag1 == 0) {
                    s += ",";
                }
                if (header[c + 1].equals("")) {
                    break;
                }
            }
            if (bull1[x][bull1[x].length - 1] != "") {
                writer.print(s + "," + bull1[x][bull1[x].length - 1]);
            } else {
                writer.print(s);
            }
            writer.println();
            x++;
        }
        writer.close();
        JOptionPane.showMessageDialog(null, "Aggregation Successful");
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        combineandsave(jTextField1.getText(), jTextArea1.getText());
    }//GEN-LAST:event_jButton1ActionPerformed


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jTextArea1.setText("");
        JFileChooser choose = new JFileChooser();
        choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        choose.showOpenDialog(null);
        File f = choose.getSelectedFile();
        String path = f.getAbsolutePath();
        jTextField1.setText(path);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jTextField1.setText("");
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        chooser.showOpenDialog(null);
        listoffiles1 = chooser.getSelectedFiles();
        for (int i = 0; i < listoffiles1.length; i++) {
            File file = listoffiles1[i];
            jTextArea1.setText(file.getAbsolutePath() + "\n" + jTextArea1.getText());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

     // @param args the command line arguments
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
            java.util.logging.Logger.getLogger(Combine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Combine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Combine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Combine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Combine().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
