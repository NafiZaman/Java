
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UserInterface extends javax.swing.JFrame {
    
    AboutOpticPP obj_About;

    FileOutputStream theFileWriter;
    FileFilter filter;
    FileReader theReader;
    BufferedReader reader;
    JFileChooser theFileChooser;
    File newFile,selectedFile;
    
    int fileReturnVal;
    String workingFileName,nameOfNewlyCreatedFile,optimizedCode,textFromTextArea;
    String theDirectory,importedFileName;
    
    CodeInitializer obj_CI;
    Try_Catch_Block objTCB;
    CloseOperation objCO;
    RemovePublicVariables obj_RPV;
    CodeIndenter obj_CIND;
    
    public UserInterface() {
        initComponents();
        obj_About=new AboutOpticPP();
        
        Label_projectNameTab.setText("Untitiled.txt");
        jMenuBar1.setVisible(false);
        this.setLocation(150, 100);
        this.setTitle("Optic++");
        TextEditor_Panel.setVisible(false);
        this.setSize(820,507);
        optimizedCode=workingFileName=nameOfNewlyCreatedFile=textFromTextArea=importedFileName="";
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        Start_Page = new javax.swing.JPanel();
        label_Welcome = new javax.swing.JLabel();
        btn_NewFile = new javax.swing.JButton();
        btn_newCppFile = new javax.swing.JButton();
        btn_Tip = new javax.swing.JButton();
        label_newFile = new javax.swing.JLabel();
        label_importFile = new javax.swing.JLabel();
        label_abt = new javax.swing.JLabel();
        btn_SampleCode = new javax.swing.JButton();
        btn_About2 = new javax.swing.JButton();
        label_license = new javax.swing.JLabel();
        label_tip = new javax.swing.JLabel();
        label_sampleCode = new javax.swing.JLabel();
        TextEditor_Panel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_Editor = new javax.swing.JTextArea();
        OptimizeCode_btn = new javax.swing.JButton();
        Label_projectNameTab = new javax.swing.JLabel();
        btn_backToStartPage = new javax.swing.JButton();
        SampleCode_Panel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ta_IncorrectCode = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        ta_CorrectCode = new javax.swing.JTextArea();
        btn_backToStartPage2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Menu_NewFile = new javax.swing.JMenuItem();
        Menu_ImportFile = new javax.swing.JMenuItem();
        MenuSaveFile = new javax.swing.JMenuItem();
        Menu_Save_As = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Menu_StartPage = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Optic++"); // NOI18N
        setResizable(false);

        Start_Page.setBackground(new java.awt.Color(0, 0, 0));

        label_Welcome.setFont(new java.awt.Font("Niagara Solid", 3, 100)); // NOI18N
        label_Welcome.setForeground(new java.awt.Color(204, 102, 0));
        label_Welcome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sajjad/opticpp/pictures/Logo.png"))); // NOI18N
        label_Welcome.setText("Welcome To Optic++");
        label_Welcome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 0, 204), new java.awt.Color(153, 0, 204), new java.awt.Color(153, 0, 204), new java.awt.Color(153, 0, 204)));

        btn_NewFile.setBackground(new java.awt.Color(204, 102, 0));
        btn_NewFile.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        btn_NewFile.setText("New File");
        btn_NewFile.setBorder(null);
        btn_NewFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NewFileActionPerformed(evt);
            }
        });

        btn_newCppFile.setBackground(new java.awt.Color(204, 102, 0));
        btn_newCppFile.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        btn_newCppFile.setText("Import C++ File");
        btn_newCppFile.setBorder(null);
        btn_newCppFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newCppFileActionPerformed(evt);
            }
        });

        btn_Tip.setBackground(new java.awt.Color(204, 102, 0));
        btn_Tip.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        btn_Tip.setText("Tip Of The Day");
        btn_Tip.setBorder(null);
        btn_Tip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TipActionPerformed(evt);
            }
        });

        label_newFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sajjad/opticpp/pictures/folder.png"))); // NOI18N

        label_importFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sajjad/opticpp/pictures/file.png"))); // NOI18N

        label_abt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sajjad/opticpp/pictures/team.png"))); // NOI18N

        btn_SampleCode.setBackground(new java.awt.Color(204, 102, 0));
        btn_SampleCode.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        btn_SampleCode.setText("View Sample Code");
        btn_SampleCode.setBorder(null);
        btn_SampleCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SampleCodeActionPerformed(evt);
            }
        });

        btn_About2.setBackground(new java.awt.Color(204, 102, 0));
        btn_About2.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        btn_About2.setText("About");
        btn_About2.setBorder(null);
        btn_About2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_About2ActionPerformed(evt);
            }
        });

        label_license.setForeground(new java.awt.Color(204, 102, 0));
        label_license.setText("(c) 2016-2016, The Optic++ Team");

        label_tip.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sajjad/opticpp/pictures/light-bulb.png"))); // NOI18N

        label_sampleCode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sajjad/opticpp/pictures/sampleCode.png"))); // NOI18N

        javax.swing.GroupLayout Start_PageLayout = new javax.swing.GroupLayout(Start_Page);
        Start_Page.setLayout(Start_PageLayout);
        Start_PageLayout.setHorizontalGroup(
            Start_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Start_PageLayout.createSequentialGroup()
                .addGroup(Start_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Start_PageLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_Welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Start_PageLayout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(label_license))
                    .addGroup(Start_PageLayout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addGroup(Start_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Start_PageLayout.createSequentialGroup()
                                .addGroup(Start_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label_sampleCode)
                                    .addComponent(label_tip)
                                    .addComponent(label_abt)
                                    .addComponent(label_newFile))
                                .addGap(18, 18, 18)
                                .addGroup(Start_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_SampleCode, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_newCppFile, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_Tip, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_About2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(Start_PageLayout.createSequentialGroup()
                                .addComponent(label_importFile)
                                .addGap(18, 18, 18)
                                .addComponent(btn_NewFile, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        Start_PageLayout.setVerticalGroup(
            Start_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Start_PageLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(label_Welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(Start_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_importFile)
                    .addComponent(btn_NewFile, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(Start_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_newCppFile, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_newFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Start_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Start_PageLayout.createSequentialGroup()
                        .addGroup(Start_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_SampleCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_sampleCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Tip, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label_tip, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Start_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_About2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_abt, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(label_license, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        TextEditor_Panel.setBackground(new java.awt.Color(0, 0, 0));
        TextEditor_Panel.setPreferredSize(new java.awt.Dimension(689, 520));

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Niagara Solid", 3, 48)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 102, 0));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sajjad/opticpp/pictures/Logo.png"))); // NOI18N
        jLabel9.setText("         Text Editor");
        jLabel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 0, 204), new java.awt.Color(153, 0, 204), new java.awt.Color(153, 0, 204), new java.awt.Color(153, 0, 204)));

        ta_Editor.setBackground(new java.awt.Color(0, 0, 0));
        ta_Editor.setColumns(20);
        ta_Editor.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        ta_Editor.setForeground(new java.awt.Color(0, 102, 0));
        ta_Editor.setRows(5);
        jScrollPane1.setViewportView(ta_Editor);

        OptimizeCode_btn.setBackground(new java.awt.Color(204, 102, 0));
        OptimizeCode_btn.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        OptimizeCode_btn.setText("Optimize Code");
        OptimizeCode_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OptimizeCode_btnActionPerformed(evt);
            }
        });

        Label_projectNameTab.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Label_projectNameTab.setForeground(new java.awt.Color(204, 153, 0));

        btn_backToStartPage.setBackground(new java.awt.Color(255, 102, 0));
        btn_backToStartPage.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        btn_backToStartPage.setText("<<Start Page");
        btn_backToStartPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backToStartPageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TextEditor_PanelLayout = new javax.swing.GroupLayout(TextEditor_Panel);
        TextEditor_Panel.setLayout(TextEditor_PanelLayout);
        TextEditor_PanelLayout.setHorizontalGroup(
            TextEditor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TextEditor_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TextEditor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_projectNameTab, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TextEditor_PanelLayout.createSequentialGroup()
                        .addComponent(btn_backToStartPage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(OptimizeCode_btn)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        TextEditor_PanelLayout.setVerticalGroup(
            TextEditor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TextEditor_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(Label_projectNameTab)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TextEditor_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OptimizeCode_btn)
                    .addComponent(btn_backToStartPage))
                .addContainerGap())
        );

        SampleCode_Panel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Niagara Engraved", 3, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 102, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sajjad/opticpp/pictures/Logo.png"))); // NOI18N
        jLabel2.setText("       Sameple C++ Code");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 0, 204), new java.awt.Color(153, 0, 204), new java.awt.Color(153, 0, 204), new java.awt.Color(153, 0, 204)));

        ta_IncorrectCode.setEditable(false);
        ta_IncorrectCode.setBackground(new java.awt.Color(0, 0, 0));
        ta_IncorrectCode.setColumns(20);
        ta_IncorrectCode.setForeground(new java.awt.Color(0, 102, 0));
        ta_IncorrectCode.setRows(5);
        ta_IncorrectCode.setText("#include <iostream>\n#include <cstdlib>\n#include <fstream>\n\nusing namespace std;\n\nclass myclass{\n\npublic:\n    int x,y,*array;\n\n    void foo()\n     {cout << \"Welcome to Optic++!\" << endl;}\n};\n\nvoid blah(){}\n\nvoid buu()\n{\n    myclass d;\n    d.array=new int[100];\n}\n\nint main(){\n    buu();\n    myclass c;\n    c.x=33;\n\n    cout << c.x << endl;\n\n    ifstream in;\n    in.open(\"File.txt\");\n    return 0;\n}");
        jScrollPane4.setViewportView(ta_IncorrectCode);

        ta_CorrectCode.setEditable(false);
        ta_CorrectCode.setBackground(new java.awt.Color(0, 0, 0));
        ta_CorrectCode.setColumns(20);
        ta_CorrectCode.setForeground(new java.awt.Color(0, 102, 0));
        ta_CorrectCode.setRows(5);
        ta_CorrectCode.setText("#include <iostream>\n#include <fstream>\n\nusing namespace std;\n\n\n\nint temp_xVal;\nclass myclass{\npublic:\n\tint *array;\n\tint get_xVal(){\n\t\treturn x;\n\t}\n\tvoid give_xVal(int newVal_x){\n\t\tx=newVal_x;\n\t}\n\n\tint get_yVal(){\n\t\treturn y;\n\t}\n\tvoid give_yVal(int newVal_y){\n\t\ty=newVal_y;\n\t}\n\n\n\tvoid foo(){\n\t\tcout << \"Welcome to Optic++!\" << endl;\n\t}\n\n\nprivate:\n\tint x,y;\n};\n\n\nvoid buu();\n\nvoid buu() {\n\tmyclass d;\n\ttry{\n\t\td.array=new int[100];\n\t}\n\tcatch(exception& e){\n\t\tcout << e.what() << endl;\n\t}\n\n}\n\n\n\nint main(){\n\tbuu();\n\tmyclass c;\n\ttemp_xVal=c.get_xVal();\n\ttemp_xVal=33;\n\tc.give_xVal(temp_xVal);\n\n\tcout << c.get_xVal() << endl;\n\tifstream in;\n\tin.open(\"File.txt\");\n\treturn 0;\n\tin.close();\n}");
        jScrollPane5.setViewportView(ta_CorrectCode);

        btn_backToStartPage2.setBackground(new java.awt.Color(255, 102, 0));
        btn_backToStartPage2.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        btn_backToStartPage2.setText("<<Start Page");
        btn_backToStartPage2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backToStartPage2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SampleCode_Panel1Layout = new javax.swing.GroupLayout(SampleCode_Panel1);
        SampleCode_Panel1.setLayout(SampleCode_Panel1Layout);
        SampleCode_Panel1Layout.setHorizontalGroup(
            SampleCode_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SampleCode_Panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SampleCode_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(SampleCode_Panel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(SampleCode_Panel1Layout.createSequentialGroup()
                .addGap(326, 326, 326)
                .addComponent(btn_backToStartPage2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SampleCode_Panel1Layout.setVerticalGroup(
            SampleCode_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SampleCode_Panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SampleCode_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_backToStartPage2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(Start_Page, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(TextEditor_Panel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(SampleCode_Panel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(TextEditor_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Start_Page, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SampleCode_Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Start_Page, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextEditor_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SampleCode_Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(519, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(204, 102, 0));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 0, 204), new java.awt.Color(153, 0, 204), new java.awt.Color(153, 0, 204), new java.awt.Color(153, 0, 204)));

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("File");

        Menu_NewFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        Menu_NewFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sajjad/opticpp/pictures/new-file.png"))); // NOI18N
        Menu_NewFile.setText("    New File");
        Menu_NewFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_NewFileActionPerformed(evt);
            }
        });
        jMenu1.add(Menu_NewFile);

        Menu_ImportFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        Menu_ImportFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sajjad/opticpp/pictures/import.png"))); // NOI18N
        Menu_ImportFile.setText("    Import File");
        Menu_ImportFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_ImportFileActionPerformed(evt);
            }
        });
        jMenu1.add(Menu_ImportFile);

        MenuSaveFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        MenuSaveFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sajjad/opticpp/pictures/save.png"))); // NOI18N
        MenuSaveFile.setText("    Save File");
        MenuSaveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSaveFileActionPerformed(evt);
            }
        });
        jMenu1.add(MenuSaveFile);

        Menu_Save_As.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        Menu_Save_As.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sajjad/opticpp/pictures/saveAs.png"))); // NOI18N
        Menu_Save_As.setText("    Save As");
        Menu_Save_As.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_Save_AsActionPerformed(evt);
            }
        });
        jMenu1.add(Menu_Save_As);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sajjad/opticpp/pictures/close.png"))); // NOI18N
        jMenuItem1.setText("    Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("View");

        Menu_StartPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sajjad/opticpp/pictures/startPage.png"))); // NOI18N
        Menu_StartPage.setText("    Start Page");
        Menu_StartPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_StartPageActionPerformed(evt);
            }
        });
        jMenu2.add(Menu_StartPage);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_About2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_About2ActionPerformed
        obj_About.setVisible(true);
    }//GEN-LAST:event_btn_About2ActionPerformed

    private void btn_NewFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NewFileActionPerformed
        
        ta_Editor.setText("");
        this.setSize(689,565);
        this.setLocation(250, 10);
        TextEditor_Panel.setVisible(true);
        jMenuBar1.setVisible(true);
        
    }//GEN-LAST:event_btn_NewFileActionPerformed

    private void Menu_ImportFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_ImportFileActionPerformed
        
        try
        {
            filter=new FileNameExtensionFilter("C++ file, Text files","txt","cpp");
            theDirectory = System.getProperty("user.home");
            theFileChooser=new JFileChooser(theDirectory+"/Desktop");
            theFileChooser.setAcceptAllFileFilterUsed(false);
            theFileChooser.addChoosableFileFilter(filter);
        
            fileReturnVal=theFileChooser.showOpenDialog(null);
            if(fileReturnVal==JFileChooser.APPROVE_OPTION)
            {   
                newFile=theFileChooser.getSelectedFile();
                workingFileName=newFile.getName();
                
                System.out.println(workingFileName);
                theReader=new FileReader(newFile);
                reader=new BufferedReader(theReader);
                
                String line="",file_text="";
                
                while((line=reader.readLine())!=null)
                {
                    if(line!=null)file_text+=line+'\n';
                    else file_text+='\n';
                }
                
                theReader.close();
                reader.close();
                
                ta_Editor.setText(file_text);
                Label_projectNameTab.setText(workingFileName);
            }
            
        }catch(Exception e){}
        
    }//GEN-LAST:event_Menu_ImportFileActionPerformed

    private void MenuSaveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSaveFileActionPerformed
        
        try
        {
            filter=new FileNameExtensionFilter("C++ file, Text files","txt","cpp");
            theDirectory = System.getProperty("user.home")+"/Desktop";
            theFileChooser=new JFileChooser(theDirectory);
            theFileChooser.setAcceptAllFileFilterUsed(false);
            theFileChooser.addChoosableFileFilter(filter);
            
            if(workingFileName.equals("")==false)
            {
                textFromTextArea=ta_Editor.getText();
                textFromTextArea=textFromTextArea.replaceAll("(?!\\r)\\n", "\r\n");
                theFileWriter=new FileOutputStream(newFile);
                new PrintStream(theFileWriter).println(textFromTextArea);
            }
            else
            {   
                fileReturnVal=theFileChooser.showSaveDialog(null);
                
                if(fileReturnVal==JFileChooser.CANCEL_OPTION)return;
                else if(fileReturnVal==JFileChooser.APPROVE_OPTION)
                {
                    selectedFile=theFileChooser.getSelectedFile();
                    nameOfNewlyCreatedFile=selectedFile.getName();
                }
                
                if(nameOfNewlyCreatedFile.endsWith(".cpp"))newFile=new File(nameOfNewlyCreatedFile);
                else nameOfNewlyCreatedFile+=".txt";
                
                theDirectory=theFileChooser.getCurrentDirectory().getAbsolutePath();   
                newFile=new File(theDirectory,nameOfNewlyCreatedFile);
                newFile.createNewFile();
                textFromTextArea=ta_Editor.getText();
                textFromTextArea=textFromTextArea.replaceAll("(?!\\r)\\n", "\r\n");
                theFileWriter=new FileOutputStream(newFile);
                new PrintStream(theFileWriter).println(textFromTextArea);
                workingFileName=newFile.getName();
                Label_projectNameTab.setText(workingFileName);
            }
            
        }catch(Exception e){}

    }//GEN-LAST:event_MenuSaveFileActionPerformed

    private void OptimizeCode_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OptimizeCode_btnActionPerformed

        try {

            optimizedCode=ta_Editor.getText();
            obj_CI=new CodeInitializer();
            optimizedCode = obj_CI.init(optimizedCode);

            objTCB=new Try_Catch_Block(optimizedCode);
            objTCB.analyzeCode();
            optimizedCode=objTCB.returnOptimizedCode();

            objCO=new CloseOperation(optimizedCode);
            objCO.analyzeCode();
            optimizedCode=objCO.returnOptimizedCode();

            obj_RPV=new RemovePublicVariables(optimizedCode);
            obj_RPV.analyzeCode();
            optimizedCode=obj_RPV.returnOptimizedCode();

            obj_CIND=new CodeIndenter(optimizedCode);
            obj_CIND.analyzeCode();
            optimizedCode=obj_CIND.returnIndentedCode();

            ta_Editor.setText(optimizedCode);

        } catch (IOException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_OptimizeCode_btnActionPerformed

    private void Menu_Save_AsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_Save_AsActionPerformed
        
        try
        {
            filter=new FileNameExtensionFilter("C++ file, Text files","txt","cpp");
            theDirectory = System.getProperty("user.home")+"/Desktop";
            theFileChooser=new JFileChooser(theDirectory);
            theFileChooser.setAcceptAllFileFilterUsed(false);
            theFileChooser.addChoosableFileFilter(filter);
            
            fileReturnVal=theFileChooser.showSaveDialog(null);
            if(fileReturnVal==JFileChooser.CANCEL_OPTION)return;
            else if(fileReturnVal==JFileChooser.APPROVE_OPTION)
            {
                selectedFile=theFileChooser.getSelectedFile();
                nameOfNewlyCreatedFile=selectedFile.getName();
            }
            
            if(nameOfNewlyCreatedFile.endsWith(".cpp"))newFile=new File(nameOfNewlyCreatedFile);
            else nameOfNewlyCreatedFile+=".txt";
                
            theDirectory=theFileChooser.getCurrentDirectory().getAbsolutePath();   
            newFile=new File(theDirectory,nameOfNewlyCreatedFile);
            newFile.createNewFile();
            textFromTextArea=ta_Editor.getText();
            textFromTextArea=textFromTextArea.replaceAll("(?!\\r)\\n", "\r\n");
            theFileWriter=new FileOutputStream(newFile);
            new PrintStream(theFileWriter).println(textFromTextArea);
            workingFileName=newFile.getName();
            Label_projectNameTab.setText(workingFileName);
        }
        catch(Exception e){}
    }//GEN-LAST:event_Menu_Save_AsActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void Menu_NewFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_NewFileActionPerformed
        
        RefreshTextEditor();
        
    }//GEN-LAST:event_Menu_NewFileActionPerformed

    private void Menu_StartPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_StartPageActionPerformed
        
        Label_projectNameTab.setText("Untitiled.txt");
        jMenuBar1.setVisible(false);
        this.setLocation(150, 100);
        this.setTitle("Optic++");
        TextEditor_Panel.setVisible(false);
        this.setSize(820,507);
        optimizedCode=workingFileName=nameOfNewlyCreatedFile=textFromTextArea=importedFileName="";
        
    }//GEN-LAST:event_Menu_StartPageActionPerformed

    private void btn_newCppFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newCppFileActionPerformed
        
        try
        {
            filter=new FileNameExtensionFilter("C++ file, Text files","txt","cpp");
            theDirectory = System.getProperty("user.home");
            theFileChooser=new JFileChooser(theDirectory+"/Desktop");
            theFileChooser.setAcceptAllFileFilterUsed(false);
            theFileChooser.addChoosableFileFilter(filter);
        
            fileReturnVal=theFileChooser.showOpenDialog(null);
            if(fileReturnVal==JFileChooser.CANCEL_OPTION)return;
            else if(fileReturnVal==JFileChooser.APPROVE_OPTION)
            {   
                newFile=theFileChooser.getSelectedFile();
                workingFileName=newFile.getName();

                theReader=new FileReader(newFile);
                reader=new BufferedReader(theReader);
                
                String line="",file_text="";
                
                while((line=reader.readLine())!=null)
                {
                    if(line!=null)file_text+=line+'\n';
                    else file_text+='\n';
                }
                
                theReader.close();
                reader.close();
                
                ta_Editor.setText(file_text);
                Label_projectNameTab.setText(workingFileName);
            }
            
        }catch(Exception e){}
        
        this.setSize(689,565);
        this.setLocation(250, 10);
        TextEditor_Panel.setVisible(true);
        jMenuBar1.setVisible(true);
        
    }//GEN-LAST:event_btn_newCppFileActionPerformed

    private void btn_backToStartPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backToStartPageActionPerformed
        
        Label_projectNameTab.setText("Untitiled.txt");
        jMenuBar1.setVisible(false);
        this.setLocation(150, 100);
        TextEditor_Panel.setVisible(false);
        this.setSize(820,507);
        optimizedCode=workingFileName=nameOfNewlyCreatedFile=textFromTextArea=importedFileName="";
    }//GEN-LAST:event_btn_backToStartPageActionPerformed

    private void btn_TipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TipActionPerformed
        
        try
        {
            theReader=new FileReader("tips.txt");
            reader=new BufferedReader(theReader);
            Random rand=new Random();
            int lineNumber=rand.nextInt(5-1)+1;
            String tip="";
            
            for(int i=1;i<=lineNumber;i++)if((tip=reader.readLine())!=null){}
            
            JOptionPane.showMessageDialog(null, tip);
        }
        catch(Exception e){}
    }//GEN-LAST:event_btn_TipActionPerformed

    private void btn_SampleCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SampleCodeActionPerformed
        Font font=new Font("Monospaced",Font.PLAIN,13);
        ta_CorrectCode.setFont(font);
        ta_IncorrectCode.setFont(font);
        Start_Page.setVisible(false);
        this.setSize(792,560);
        SampleCode_Panel1.setVisible(true);
    }//GEN-LAST:event_btn_SampleCodeActionPerformed

    private void btn_backToStartPage2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backToStartPage2ActionPerformed
        
        this.setLocation(150, 100);
        this.setSize(820,507);
        SampleCode_Panel1.setVisible(false);
        Start_Page.setVisible(true);
        
    }//GEN-LAST:event_btn_backToStartPage2ActionPerformed
    
    public void RefreshTextEditor()
    {
        Label_projectNameTab.setText("Untitiled.txt");
        ta_Editor.setText("");
        optimizedCode=workingFileName=nameOfNewlyCreatedFile=textFromTextArea=importedFileName="";
    }
    
    public static void main(String args[]) {
        try
        {UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch(Exception e){}
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label_projectNameTab;
    private javax.swing.JMenuItem MenuSaveFile;
    private javax.swing.JMenuItem Menu_ImportFile;
    private javax.swing.JMenuItem Menu_NewFile;
    private javax.swing.JMenuItem Menu_Save_As;
    private javax.swing.JMenuItem Menu_StartPage;
    private javax.swing.JButton OptimizeCode_btn;
    private javax.swing.JPanel SampleCode_Panel1;
    private javax.swing.JPanel Start_Page;
    private javax.swing.JPanel TextEditor_Panel;
    private javax.swing.JButton btn_About2;
    private javax.swing.JButton btn_NewFile;
    private javax.swing.JButton btn_SampleCode;
    private javax.swing.JButton btn_Tip;
    private javax.swing.JButton btn_backToStartPage;
    private javax.swing.JButton btn_backToStartPage2;
    private javax.swing.JButton btn_newCppFile;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel label_Welcome;
    private javax.swing.JLabel label_abt;
    private javax.swing.JLabel label_importFile;
    private javax.swing.JLabel label_license;
    private javax.swing.JLabel label_newFile;
    private javax.swing.JLabel label_sampleCode;
    private javax.swing.JLabel label_tip;
    private javax.swing.JTextArea ta_CorrectCode;
    private javax.swing.JTextArea ta_Editor;
    private javax.swing.JTextArea ta_IncorrectCode;
    // End of variables declaration//GEN-END:variables
}