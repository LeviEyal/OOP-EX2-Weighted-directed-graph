package GUI;

import gameClient.Arena;
import gameClient.Ex2;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

/**
 *
 * @author happy
 */
public class MyFrame extends javax.swing.JFrame {

    Arena _ar;
    private static boolean sideMenuOpen = true;
    private static Color color_header = new Color(75, 130, 0);
    private static Color color_leftMenu = new Color(0, 0, 0);
    private static Color color_page = new Color(150, 150, 150);
    private static Color color_hoverHeader = new Color(150, 150, 150);
    private static Color color_hoverMenu = new Color(150, 150, 150);
    private static Color color_sideMenu = new Color(177, 255, 159);
    private static Color color_choosenMenu = color_sideMenu;
    private static Color color_left_menu_line = new Color(150, 150, 150);

    /**
     * Creates new form MainMenu
     */
    public MyFrame(Arena ar) {
        _ar = ar;
        initComponents();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000,700);
        this.setLayout(new BorderLayout());

        otherComponents();

        MyPanel panel = new MyPanel();
        panel.setPreferredSize(new Dimension(800,500));
        panel.update(_ar);

        this.add(panel, BorderLayout.CENTER);

        pack();
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        t.repaint();
        Menu.repaint();
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        t.revalidate();
        t.repaint();
        Menu.repaint();
    }

    private void otherComponents() {
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

//        setBackground(new java.awt.Color(153, 153, 153));
        setLocationByPlatform(true);
//        setUndecorated(true);
        setLayout(new BorderLayout());

        //================================ HEADER =================================
        Header.setBackground(color_header);
        Header.setMinimumSize(new Dimension(150, 20));
        Header.setPreferredSize(new Dimension(800, 30));
//        Header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
//            public void mouseDragged(java.awt.event.MouseEvent evt) {
//                HeaderMouseDragged(evt);
//            }
//        });
        Header.setLayout(new BorderLayout());

        iconMinMaxclose.setBackground(color_header);
        iconMinMaxclose.setPreferredSize(new Dimension(150, 50));
        iconMinMaxclose.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        minimizePNL.setBackground(color_header);
        minimizePNL.setLayout(new BorderLayout());

        minimizeWindowBTN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minimizeWindowBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_minimize_window_18px.png"))); // NOI18N
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
        closeWindowBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_close_window_18px.png"))); // NOI18N
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
        maxWindowBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_full_screen_18px.png"))); // NOI18N
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
        hideMenuBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/rightArrow.png"))); // NOI18N
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
        settingsBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/settings_50px.png"))); // NOI18N
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
        helpBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/help.png"))); // NOI18N
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
        jLabel1.setText("Settings");
        jLabel2.setText("Help");

        javax.swing.GroupLayout menuHideLayout = new javax.swing.GroupLayout(menuHide);
        menuHide.setLayout(menuHideLayout);
        menuHideLayout.setHorizontalGroup(
                menuHideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menuHideLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(menuHideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))
        );
        menuHideLayout.setVerticalGroup(
                menuHideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menuHideLayout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(309, Short.MAX_VALUE))
        );

        Menu.add(menuHide, BorderLayout.CENTER);
        getContentPane().add(Menu, BorderLayout.LINE_START);

        t = new JLabel();
        t.setText("ttl: "+ Ex2._game.timeToEnd()/1000 +"");
        Menu.add(t);
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
            hideMenuBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/rightArrow.png")));
        }
        else{
            Menu.setPreferredSize(new Dimension(250, Menu.getHeight()));
            sideMenuOpen = true;
            hideMenuBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/leftArrow.png")));
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
        settingsPNL.setBackground(color_choosenMenu);
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
        helpPNL.setBackground(color_choosenMenu);
        settingsPNL.setBackground(color_leftMenu);
    }

    private void HeaderMouseDragged(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
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
    private JLabel t;
}
