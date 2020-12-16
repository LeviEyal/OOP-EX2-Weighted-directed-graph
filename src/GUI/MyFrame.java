package GUI;

import gameClient.Arena;
import gameClient.Ex2;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author happy
 */
public class MyFrame extends javax.swing.JFrame {

    int px, py;
    Arena _ar;
    double time;
    double grade;
    double moves;
    int level;
    JPanel t = new JPanel();
    JLabel[] info = new JLabel[]{
            new JLabel(),
            new JLabel(),
            new JLabel(),
            new JLabel()
    };
    MyPanel gamePanel = new MyPanel();
    EntrancePanel entrancePanel = new EntrancePanel();
    JPanel gameOverPanel;
    private static boolean sideMenuOpen = true;
    private static final Color color_header = new Color(219, 4, 4);
    private static final Color color_leftMenu = new Color(0, 0, 0);
    private static final Color color_page = new Color(150, 150, 150);
    private static final Color color_hoverHeader = new Color(255, 255, 255);
    private static final Color color_hoverMenu = new Color(255, 0, 0);
    private static final Color color_sideMenu = new Color(255, 255, 255, 106);
    private static final Color color_chosenMenu = color_sideMenu;
    private static final Color color_left_menu_line = new Color(255, 255, 255);
    public static JCheckBox show_edges = new JCheckBox("edges",true);
    public static JCheckBox show_nodes = new JCheckBox("nodes",true);
    public static JCheckBox show_nodes_numbers = new JCheckBox("nodes numbers",true);
    public static JCheckBox show_pokemons_values = new JCheckBox("pokemons values",false);
    private JPanel intro = new javax.swing.JPanel();
    private JLabel selectScLBL = new javax.swing.JLabel();
    private TextField id_field = new java.awt.TextField();
    private JComboBox<String> sc_selector = new javax.swing.JComboBox<>();
    private JButton playBTN = new javax.swing.JButton();
    private JLabel enter_ID_LBL = new javax.swing.JLabel();

    /**
     * Creates new form MainMenu
     */
    public MyFrame() {
        super("PokemonCatcher v1.0");
        this.initComponents();
    }

    public void InitGamePanel(Arena ar){
        _ar = ar;
        gamePanel.update(_ar);
        this.add(gamePanel, BorderLayout.CENTER);
    }

    private void initComponents() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1000,500));
        this.setLayout(new BorderLayout());
        this.setLocationByPlatform(true);
        this.setUndecorated(true);
        this.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/pokeball.png"));
        this.setIconImage(icon);

        this.addOtherComponents();
//        this.addGameOverPanel();
        this.addEntrancePanel();

        setWindowResizable(true);
        this.pack();
    }

    private void addGameOverPanel() {
        gameOverPanel = new JPanel();
        gameOverPanel.setVisible(true);
        gameOverPanel.setPreferredSize(new Dimension(100,100));
        Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/background2.png"));
        gameOverPanel.getGraphics().drawImage(img, 0,0, this);
        gameOverPanel.setBackground(Color.GREEN);
        this.add(gameOverPanel, BorderLayout.CENTER);
    }

    private void addEntrancePanel() {
        this.getContentPane().add(entrancePanel, BorderLayout.CENTER);
    }

    public void setWindowResizable(boolean b){
        if(!b) return;
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 4, 4, color_header));
        ComponentResizer cr = new ComponentResizer();
        cr.registerComponent(this);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
//        Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/background.png"));
//        g.drawImage(img, 0,0,null);
    }

    private void fetchData() {
        if(Ex2._game == null) return;
        try {
            JSONObject line = new JSONObject(Ex2._game.toString());
            JSONObject ttt = line.getJSONObject("GameServer");
            grade = ttt.getDouble("grade");
            moves = ttt.getDouble("moves");
            level = ttt.getInt("game_level");
            time = Ex2._game.timeToEnd() / 1000.0;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void addOtherComponents() {
        Header = new JPanel();
        iconMinMaxclose = new JPanel();
        minimizePNL = new JPanel();
        minimizeWindowBTN = new javax.swing.JLabel();
        closePNL = new JPanel();
        closeWindowBTN = new javax.swing.JLabel();
        maximizePNL = new JPanel();
        maxWindowBTN = new javax.swing.JLabel();
        Menu = new JPanel();
        menuIcon = new JPanel();
        lineHide = new JPanel();
        hideMenuPNL = new JPanel();
        hideMenuBTN = new javax.swing.JLabel();
        settingsPNL = new JPanel();
        settingsBTN = new javax.swing.JLabel();
        lineSettings = new JPanel();
        lineHelp = new JPanel();
        helpPNL = new JPanel();
        helpBTN = new javax.swing.JLabel();
        menuHide = new JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dashboard = new JPanel();
        jScrollPane1 = new JScrollPane();
        dashboard1 = new JPanel();
        jPanel1 = new JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jScrollPane2 = new JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        //================================ HEADER =================================
        Header.setBackground(color_header);
        Header.setMinimumSize(new Dimension(150, 20));
        Header.setPreferredSize(new Dimension(800, 30));

        Header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                px = e.getX();
                py = e.getY();
            }
        });
        Header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
                setLocation(e.getXOnScreen()-px, e.getYOnScreen()-py);
            }
        });
        Header.setLayout(new BorderLayout());
        JLabel title = new JLabel("  PokemonCatcher v1.0");
        title.setFont(new Font("Arial", 2, 15));
        title.setForeground(Color.white);
        Header.add(title, BorderLayout.WEST);

        iconMinMaxclose.setBackground(color_header);
        iconMinMaxclose.setPreferredSize(new Dimension(150, 50));
        iconMinMaxclose.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        minimizePNL.setBackground(color_header);
        minimizePNL.setLayout(new BorderLayout());

        minimizeWindowBTN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minimizeWindowBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icons/icons8_minimize_window_18px.png"))); // NOI18N
        minimizeWindowBTN.setPreferredSize(new Dimension(30, 30));
        minimizeWindowBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeWindowBTNMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minimizeWindowBTNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minimizeWindowBTNMouseExited(evt);
            }
        });
        minimizePNL.add(minimizeWindowBTN, BorderLayout.PAGE_START);

        iconMinMaxclose.add(minimizePNL, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        closePNL.setBackground(color_header);
        closePNL.setLayout(new BorderLayout());

        closeWindowBTN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        closeWindowBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icons/icons8_close_window_18px.png"))); // NOI18N
        closeWindowBTN.setAlignmentY(0.0F);
        closeWindowBTN.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        closeWindowBTN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        closeWindowBTN.setMaximumSize(new Dimension(30, 30));
        closeWindowBTN.setPreferredSize(new Dimension(30, 30));
        closeWindowBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeWindowBTNMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeWindowBTNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeWindowBTNMouseExited(evt);
            }
        });
        closePNL.add(closeWindowBTN, BorderLayout.PAGE_START);

        iconMinMaxclose.add(closePNL, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 50, 50));

        maximizePNL.setBackground(color_header);
        maximizePNL.setLayout(new BorderLayout());

        maxWindowBTN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        maxWindowBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icons/icons8_full_screen_18px.png"))); // NOI18N
        maxWindowBTN.setPreferredSize(new Dimension(30, 30));
        maxWindowBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maxWindowBTNMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                maxWindowBTNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                maxWindowBTNMouseExited(evt);
            }
        });
        maximizePNL.add(maxWindowBTN, BorderLayout.PAGE_START);

        iconMinMaxclose.add(maximizePNL, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 50, 50));

        Header.add(iconMinMaxclose, BorderLayout.LINE_END);

        getContentPane().add(Header, BorderLayout.PAGE_START);

        //================================ SIDE MENU =================================
        Menu.setPreferredSize(new Dimension(250, 450));
        Menu.setLayout(new BorderLayout());

        menuIcon.setBackground(color_leftMenu);
        menuIcon.setPreferredSize(new Dimension(50, 450));
        menuIcon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lineHide.setBackground(color_leftMenu);
        lineHide.setPreferredSize(new Dimension(50, 5));

        javax.swing.GroupLayout lineHideLayout = new javax.swing.GroupLayout(lineHide);
        lineHide.setLayout(lineHideLayout);
        lineHideLayout.setHorizontalGroup(
                lineHideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
        );
        lineHideLayout.setVerticalGroup(
                lineHideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 5, Short.MAX_VALUE)
        );
        menuIcon.add(lineHide, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, -1));

        //================================ HIDE PANEL =================================
        hideMenuPNL.setBackground(color_leftMenu);
        hideMenuPNL.setLayout(new BorderLayout());

        hideMenuBTN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hideMenuBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icons/rightArrow.png"))); // NOI18N
        hideMenuBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hideMenuBTNMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hideMenuBTNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hideMenuBTNMouseExited(evt);
            }
        });
        hideMenuPNL.add(hideMenuBTN, BorderLayout.CENTER);

        menuIcon.add(hideMenuPNL, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 50, 50));

        settingsPNL.setBackground(color_leftMenu);
        settingsPNL.setLayout(new BorderLayout());

        settingsBTN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settingsBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icons/settings_50px.png"))); // NOI18N
        settingsBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsBTNMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                settingsBTNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                settingsBTNMouseExited(evt);
            }
        });
        settingsPNL.add(settingsBTN, BorderLayout.CENTER);

        menuIcon.add(settingsPNL, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 50, 50));

        lineSettings.setBackground(color_leftMenu);
        lineSettings.setPreferredSize(new Dimension(50, 5));

        javax.swing.GroupLayout lineSettingsLayout = new javax.swing.GroupLayout(lineSettings);
        lineSettings.setLayout(lineSettingsLayout);
        lineSettingsLayout.setHorizontalGroup(
                lineSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
        );
        lineSettingsLayout.setVerticalGroup(
                lineSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 5, Short.MAX_VALUE)
        );

        menuIcon.add(lineSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 50, -1));

        lineHelp.setBackground(color_leftMenu);
        lineHelp.setPreferredSize(new Dimension(50, 5));

        javax.swing.GroupLayout lineHelpLayout = new javax.swing.GroupLayout(lineHelp);
        lineHelp.setLayout(lineHelpLayout);
        lineHelpLayout.setHorizontalGroup(
                lineHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
        );
        lineHelpLayout.setVerticalGroup(
                lineHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 5, Short.MAX_VALUE)
        );

        menuIcon.add(lineHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 50, -1));

        helpPNL.setBackground(color_leftMenu);
        helpPNL.setLayout(new BorderLayout());
        helpBTN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        helpBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icons/help.png"))); // NOI18N
        helpBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                helpBTNMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                helpBTNMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                helpBTNMouseExited(evt);
            }
        });
        helpPNL.add(helpBTN, BorderLayout.CENTER);

        menuIcon.add(helpPNL, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 50, 50));
        Menu.add(menuIcon, BorderLayout.LINE_START);
        menuHide.setBackground(color_sideMenu);
//        menuHide.setLayout(new BorderLayout());
        jLabel1.setText("Settings");
        jLabel2.setText("Help");

        t.setPreferredSize(new Dimension(250,100));

        fetchData();

        info[0].setText("Level: " + level);
        info[1].setText("Timer: " + time);
        info[2].setText("Grade: " + grade);
        info[3].setText("Moves: " + moves);

        t.add(info[0]);
        t.add(info[1]);
        t.add(info[2]);
        t.add(info[3]);
        t.setVisible(true);

        menuHide.add(info[0]);
        menuHide.add(info[1]);
        menuHide.add(info[2]);
        menuHide.add(info[3]);

        menuHide.add(t);
        menuHide.add(show_nodes);
        menuHide.add(show_nodes_numbers);
        menuHide.add(show_edges);
        menuHide.add(show_pokemons_values);

        Menu.add(menuHide, BorderLayout.CENTER);
        getContentPane().add(Menu, BorderLayout.LINE_START);
    }
    //============================== Header buttons ====================================//

    //close window button
    private void closeWindowBTNMouseEntered(java.awt.event.MouseEvent evt) {
        closePNL.setBackground(color_hoverHeader);
    }
    private void closeWindowBTNMouseExited(java.awt.event.MouseEvent evt) {
        closePNL.setBackground(color_header);
    }
    private void closeWindowBTNMouseClicked(java.awt.event.MouseEvent evt) {
        System.exit(0);
    }

    //maximize window button
    private void maxWindowBTNMouseEntered(java.awt.event.MouseEvent evt) {
        maximizePNL.setBackground(color_hoverHeader);
    }
    private void maxWindowBTNMouseExited(java.awt.event.MouseEvent evt) {
        maximizePNL.setBackground(color_header);
    }
    private void maxWindowBTNMouseClicked(java.awt.event.MouseEvent evt) {
        if(this.getExtendedState() != MyFrame.MAXIMIZED_BOTH){
            this.setExtendedState(MyFrame.MAXIMIZED_BOTH);
        }
        else{
            this.setExtendedState(MyFrame.NORMAL);
        }
    }

    //minimize window button
    private void minimizeWindowBTNMouseEntered(java.awt.event.MouseEvent evt) {
        minimizePNL.setBackground(color_hoverHeader);
    }
    private void minimizeWindowBTNMouseExited(java.awt.event.MouseEvent evt) {
        minimizePNL.setBackground(color_header);
    }
    private void minimizeWindowBTNMouseClicked(java.awt.event.MouseEvent evt) {

    }

//============================== Side Menu ====================================//

    //hide menu button
    private void hideMenuBTNMouseEntered(java.awt.event.MouseEvent evt) {
//        changeColor(lineHideBTN, new Color(153,153,153));
    }
    private void hideMenuBTNMouseExited(java.awt.event.MouseEvent evt) {
//        changeColor(lineHideBTN, new Color(51,51,51));
    }
    private void hideMenuBTNMouseClicked(java.awt.event.MouseEvent evt) {
        if(sideMenuOpen){
            Menu.setPreferredSize(new Dimension(50, Menu.getHeight()));
            sideMenuOpen = false;
            hideMenuBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icons/rightArrow.png")));
        }
        else{
            Menu.setPreferredSize(new Dimension(250, Menu.getHeight()));
            sideMenuOpen = true;
            hideMenuBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icons/leftArrow.png")));
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    //settings button
    private void settingsBTNMouseEntered(java.awt.event.MouseEvent evt) {
        lineSettings.setBackground(color_hoverMenu);
    }
    private void settingsBTNMouseExited(java.awt.event.MouseEvent evt) {
        lineSettings.setBackground(color_leftMenu);
    }
    private void settingsBTNMouseClicked(java.awt.event.MouseEvent evt) {
        settingsPNL.setBackground(color_chosenMenu);
        helpPNL.setBackground(color_leftMenu);
    }

    //help button
    private void helpBTNMouseEntered(java.awt.event.MouseEvent evt) {
        lineHelp.setBackground(color_hoverMenu);
    }
    private void helpBTNMouseExited(java.awt.event.MouseEvent evt) {
        lineHelp.setBackground(color_leftMenu);
    }
    private void helpBTNMouseClicked(java.awt.event.MouseEvent evt) {
        helpPNL.setBackground(color_chosenMenu);
        settingsPNL.setBackground(color_leftMenu);
    }

    // Variables declaration - do not modify
    private JPanel Header;
    private JPanel Menu;
    private JPanel closePNL;
    private javax.swing.JLabel closeWindowBTN;
    private JPanel dashboard;
    private JPanel dashboard1;
    private javax.swing.JLabel helpBTN;
    private JPanel helpPNL;
    private javax.swing.JLabel hideMenuBTN;
    private JPanel hideMenuPNL;
    private JPanel iconMinMaxclose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextPane jTextPane1;
    private JPanel lineHelp;
    private JPanel lineHide;
    private JPanel lineSettings;
    private javax.swing.JLabel maxWindowBTN;
    private JPanel maximizePNL;
    private JPanel menuHide;
    private JPanel menuIcon;
    private JPanel minimizePNL;
    private javax.swing.JLabel minimizeWindowBTN;
    private javax.swing.JLabel settingsBTN;
    private JPanel settingsPNL;

    public void showPanel(int p) {
        switch (p) {
            case 0 -> {
                entrancePanel.setVisible(true);
                gamePanel.setVisible(false);
//                gameOverPanel.setVisible(false);
            }
            case 1 -> {
                entrancePanel.setVisible(false);
                gamePanel.setVisible(true);
//                gameOverPanel.setVisible(false);
            }
            case 2 -> {
                entrancePanel.setVisible(false);
                gamePanel.setVisible(false);
//                gameOverPanel.setVisible(true);
            }
        }
    }
}
