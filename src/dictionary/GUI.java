/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author Junbum
 */
public class GUI extends javax.swing.JFrame {
    //integer required for setting up the table
    int counter = 0;
    //partially indexed file - Array of 26 Binary trees
    BinaryTree[] Dictionary = new BinaryTree[26];
    //Integer required to auto-initiate the program. The program needs to figure how many words there are, so that it can add such amount into the tree
    int totalWords = 0;
    String stringToSave = "";
    //checks whether initiated to see whether there is anything written in the save file.
    boolean initiated = false;

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        //Checking how many words there are in the file by reading in the first line.
        initiateTotalWords();
        if (totalWords != 0) {
            initiated = true;
        }
        //Initiating the dictionary to avoid null pointer exceptions
        for (int i = 0; i < Dictionary.length; i++) {
            Dictionary[i] = new BinaryTree();
        }
        //Adding the words into the array of binary trees
        if (initiated) {
            for (int i = 0; i < totalWords; i++) {
                int index = indexing(open()[i].word);
                if (index != -999) {
                    Dictionary[index].addToTree(open()[i]);
                }
            }
            //Set up the table
            setUpTable();
            counter = 0;
        }
    }

    //Hashing words into arrays
    public int indexing(String s) {
        int index = s.charAt(0);
        if (index >= 65 && index <= 90) {
            return index - 65;
        } else {
            return -999;
        }
    }

    //Recursion to set up the table
    public void setUpTable() {
        for (int i = 0; i < Dictionary.length; i++) {
            setUpTableHelper(Dictionary[i].getRoot());
        }
    }
    
    public void setUpTableHelper(Node p) {
        if (p != null) {
            setUpTableHelper(p.getLeft());
            jTable1.setValueAt(p.getValue().word, counter, 0);
            jTable1.setValueAt(p.getValue().meaning, counter, 1);
            counter++;
            setUpTableHelper(p.getRight());
        }
    }
    
    //Refreshing the last line of the table when a word is deleted
    public void refreshLastLine() {
        jTable1.setValueAt(null, counter, 0);
        jTable1.setValueAt(null, counter, 1);
        counter = 0;
    }

    //Saving the words
    public void save() {
        try {
            totalWords=0;
            for (int i = 0; i < Dictionary.length; i++) {
                totalWords = totalWords + Dictionary[i].numberOfWords;
                stringToSave = stringToSave + Dictionary[i].traverseInOrder();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter("Dictionary.txt"));
            bw.write(totalWords + "\n" + stringToSave);
            bw.close();
            totalWords = 0;
            stringToSave = "";
        } catch (IOException ex) {
            System.out.println("Error Saving.");
        }
    }

    public void initiateTotalWords() {
        String numWords = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("Dictionary.txt"));
            numWords = br.readLine();
        } catch (IOException IE) {
            System.out.println("Error bringing in number of words.");
        }
        if (numWords != null) {
            //Words written in Exists
            totalWords = Integer.parseInt(numWords);
        } else {
            //Dictionary Has not been Written.
            totalWords = 0;
        }
    }

    //Auto launching Dictionary
    public Vocabulary[] open() {
        Vocabulary[] wordsToAdd = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader("Dictionary.txt"));
            //Reading in each line
            String[] readIn = new String[totalWords];
            //Two dimensional arrays of String to store the read in info so that we can easily convert them into a Vocabulary.
            String[][] data = new String[totalWords][2];
            //Array of Vocabularies that Strings will be converted from + initiating them
            wordsToAdd = new Vocabulary[totalWords];
            for (int i = 0; i < totalWords; i++) {
                wordsToAdd[i] = new Vocabulary();
            }
            //Skinpping the first line, which is the totalWords
            br.readLine();

            //reading in the file
            for (int i = 0; i < totalWords; i++) {
                readIn[i] = br.readLine();
                //Tokenizing
                StringTokenizer st = new StringTokenizer(readIn[i], ":");
                //Storing the tokens into the two dimensional array
                for (int j = 0; j < 2; j++) {
                    data[i][j] = st.nextElement() + "";
                }
                //converting the read file into a tree
                wordsToAdd[i] = new Vocabulary(data[i][0], data[i][1]);
            }
        } catch (IOException ex) {
            System.out.println("Error Openning");
        }
        return wordsToAdd;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        searchTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTA = new javax.swing.JTextArea();
        SearchButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        AddVocTF = new javax.swing.JTextField();
        AddButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        AddMeanTF = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        DeleteTA = new javax.swing.JTextField();
        DeleteButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Vocabulary : ");

        jLabel2.setText("Meaning :");

        resultTA.setColumns(20);
        resultTA.setRows(5);
        jScrollPane1.setViewportView(resultTA);

        SearchButton.setText("Search");
        SearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SearchButtonMouseReleased(evt);
            }
        });

        jLabel6.setText("***Capitalize the First Letter***");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(49, 49, 49)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(jLabel2))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(jLabel6)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 256, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(searchTF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 185, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 80, Short.MAX_VALUE)
                        .add(SearchButton)
                        .add(62, 62, 62))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(58, 58, 58)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(searchTF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(SearchButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel6)
                .add(25, 25, 25)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel2)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Search", jPanel1);

        jLabel3.setText("Vocabulary : ");

        AddButton.setText("Add");
        AddButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AddButtonMouseReleased(evt);
            }
        });

        jLabel4.setText("Meaning :");

        AddMeanTF.setColumns(20);
        AddMeanTF.setRows(5);
        jScrollPane2.setViewportView(AddMeanTF);

        jLabel7.setText("***Capitalize the First Letter***");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(49, 49, 49)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel3)
                    .add(jLabel4))
                .add(18, 18, 18)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(jLabel7)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 256, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(AddVocTF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 185, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 90, Short.MAX_VALUE)
                        .add(AddButton)
                        .add(62, 62, 62))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(58, 58, 58)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(AddVocTF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(AddButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel7)
                .add(25, 25, 25)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel4)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add", jPanel2);

        jLabel5.setText("Vocabulary : ");

        DeleteButton.setText("Delete");
        DeleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                DeleteButtonMouseReleased(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(55, 55, 55)
                .add(jLabel5)
                .add(18, 18, 18)
                .add(DeleteTA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 185, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 81, Short.MAX_VALUE)
                .add(DeleteButton)
                .add(56, 56, 56))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(106, 106, 106)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(DeleteTA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(DeleteButton))
                .addContainerGap(279, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Delete", jPanel3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Vocabulary", "Definition"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Data Base", jPanel4);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void SearchButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchButtonMouseReleased
        // TODO add your handling code here:
        String key = searchTF.getText();
        int index = indexing(key);
        Vocabulary foundWord = Dictionary[index].search(key);
        if (foundWord.getName().equals("not set yet")) {
            resultTA.setText("Word Not Found");
        } else {
            resultTA.setText(foundWord.meaning);
        }
    }//GEN-LAST:event_SearchButtonMouseReleased

    private void AddButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddButtonMouseReleased
        // TODO add your handling code here:
        String word = AddVocTF.getText();
        int index = indexing(word);
        if (index != -999) {
            Dictionary[index].addToTree(new Vocabulary(AddVocTF.getText(), AddMeanTF.getText()));
        }
        AddVocTF.setText(null);
        AddMeanTF.setText(null);
        setUpTable();
        counter = 0;
        save();
    }//GEN-LAST:event_AddButtonMouseReleased

    private void DeleteButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteButtonMouseReleased
        // TODO add your handling code here:
        String wordToDelete = DeleteTA.getText();
        int index = indexing(wordToDelete);
        if (index != -999) {
            if (!Dictionary[index].remove(wordToDelete)) {
                DeleteTA.setText("Word Not Found");
            } else {
                DeleteTA.setText(null);
            }
        }
        setUpTable();
        refreshLastLine();
        save();
    }//GEN-LAST:event_DeleteButtonMouseReleased

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JTextArea AddMeanTF;
    private javax.swing.JTextField AddVocTF;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JTextField DeleteTA;
    private javax.swing.JButton SearchButton;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JTextArea resultTA;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JTextField searchTF;
    // End of variables declaration//GEN-END:variables
}
