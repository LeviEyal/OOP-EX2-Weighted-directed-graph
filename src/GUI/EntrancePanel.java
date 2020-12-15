package GUI;

import api.game_service;
import gameClient.Arena;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class EntrancePanel {

    public static game_service _game;
    static Arena _ar;
    static MyFrame _mainFrame;
    private static long id = -1;
    private static int level = 0;
    private JPanel intro = new javax.swing.JPanel();
    private JLabel select_level_LBL = new javax.swing.JLabel();
    private TextField id_field = new java.awt.TextField();
    private JComboBox<String> level_selector = new javax.swing.JComboBox<>();
    private JButton playBTN = new javax.swing.JButton();
    private JLabel enter_ID_LBL = new javax.swing.JLabel();
    private static boolean isClicked = true;

    public EntrancePanel() {
        select_level_LBL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        select_level_LBL.setText("Select level:");

        id_field.addTextListener(new TextListener() {
            @Override
            public void textValueChanged(TextEvent e) {
                try {
                    id  = Long.parseLong(id_field.getText());
                    playBTN.setEnabled(true);
                }catch (Exception ex){
                    playBTN.setEnabled(false);
                }
            }
        });

        level_selector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Level 0", "Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6", "Level 7", "Level 8", "Level 9", "Level 10", "Level 11", "Level 12", "Level 13", "Level 14", "Level 15", "Level 16", "Level 17", "Level 18", "Level 19", "Level 20", "Level 21", "Level 22", "Level 23" }));
        level_selector.setToolTipText("");
        level_selector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = level_selector.getSelectedIndex();
                System.out.println(level);
            }
        });


        playBTN.setText("Play!");
        playBTN.setEnabled(false);
        playBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                isClicked = true;
                System.out.println("starting to play!");
            }
        });

        enter_ID_LBL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        enter_ID_LBL.setText("Enter your ID: ");

        javax.swing.GroupLayout introLayout = new javax.swing.GroupLayout(intro);
        intro.setLayout(introLayout);
        introLayout.setHorizontalGroup(
                introLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(introLayout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(introLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(introLayout.createSequentialGroup()
                                                .addComponent(enter_ID_LBL, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(introLayout.createSequentialGroup()
                                                .addComponent(select_level_LBL, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(level_selector, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(playBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        introLayout.setVerticalGroup(
                introLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(introLayout.createSequentialGroup()
                                .addGap(147, 147, 147)
                                .addGroup(introLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(enter_ID_LBL, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(id_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(introLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(select_level_LBL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(level_selector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addComponent(playBTN))
        );
    }
}
