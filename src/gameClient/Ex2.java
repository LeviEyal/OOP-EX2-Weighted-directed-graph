package gameClient;
import GUI.MyFrame;
import Server.Game_Server_Ex2;
import api.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Thread.sleep;

public class Ex2 implements Runnable{

    public static game_service _game;
    static Arena _ar;
    static MyFrame _mainFrame;
    private static long id = -1;
    private static int scenario_num = 0;
    private JPanel intro = new javax.swing.JPanel();
    private JLabel selectScLBL = new javax.swing.JLabel();
    private TextField id_field = new java.awt.TextField();
    private JComboBox<String> sc_selector = new javax.swing.JComboBox<>();
    private JButton playBTN = new javax.swing.JButton();
    private JLabel enter_ID_LBL = new javax.swing.JLabel();
    private static boolean isClicked = true;

    public static void main(String[] args) {
        try {
            id = Integer.parseInt(args[0]);
            scenario_num = Integer.parseInt(args[1]);
        }
        catch(Exception e) {
            id = -1;
            scenario_num = 0;
            isClicked = false;
        }
        Thread client = new Thread(new Ex2());
        client.start();
    }

    @Override
    public void run() {
        _mainFrame = new MyFrame();
        introPanel();
        _mainFrame.add(intro, BorderLayout.CENTER);
        _mainFrame.pack();

        while (!isClicked) {
            Thread.onSpinWait();
        }

        _game = Game_Server_Ex2.getServer(scenario_num);
        _ar = new Arena(_game);
        _mainFrame.initFrame(_ar);
        intro.setVisible(false);
        _mainFrame.pack();

        _game.login(id);
        _game.startGame();
        int dt;
        while(_game.isRunning()) {
                _ar.moveAgents();
                _mainFrame.repaint();
                dt = isCloseToPokemon()? 50 : 110;
            try {
                sleep(dt);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

    }

    private boolean isCloseToPokemon() {
        for(Agent ag : _ar.JsonToAgents()){
            for(Pokemon p : _ar.getPokemons())
                if(ag.get_curr_edge() == p.get_edge())
                    return true;
        }
        return false;
    }

    private void introPanel() {
        selectScLBL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        selectScLBL.setText("Select Scenario:");

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

        sc_selector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Scenario 0", "Scenario 1", "Scenario 2", "Scenario 3", "Scenario 4", "Scenario 5", "Scenario 6", "Scenario 7", "Scenario 8", "Scenario 9", "Scenario 10", "Scenario 11", "Scenario 12", "Scenario 13", "Scenario 14", "Scenario 15", "Scenario 16", "Scenario 17", "Scenario 18", "Scenario 19", "Scenario 20", "Scenario 21", "Scenario 22", "Scenario 23" }));
        sc_selector.setToolTipText("");
//        sc_selector.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                scenario_num = sc_selector.getSelectedIndex();
//                System.out.println(scenario_num);
//            }
//        });
        sc_selector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scenario_num = sc_selector.getSelectedIndex();
                System.out.println(scenario_num);
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
                                                .addComponent(selectScLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(sc_selector, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                        .addComponent(selectScLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sc_selector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addComponent(playBTN))
        );
//        intro.setPreferredSize(new Dimension(700,1000));
//        intro.setBackground(Color.GREEN);
    }
}
