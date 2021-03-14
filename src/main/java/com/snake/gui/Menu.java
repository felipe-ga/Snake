package com.snake.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

public class Menu extends JFrame {
    private static final Logger LOG = Logger.getLogger(Menu.class.getName());
    private static Menu INSTANCE;
    public Menu() throws HeadlessException {
        // set the layout
        setLayout(new BorderLayout());
        JPanel panelLeft = new JPanel();
        JPanel panelRight = new JPanel(new BorderLayout());
        JPanel containerBotones = new JPanel(new BorderLayout());
        JPanel panelGroupBotones = new JPanel();
        panelRight.setBorder(new EmptyBorder(20, 10, 10, 10));

        // creates image
        JLabel containerImageLabel = new JLabel();
        try{
            containerImageLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/snake.png")));
        }catch(Exception e){
            LOG.info(e.getMessage());
        }

        containerImageLabel.setBorder(new EmptyBorder(20, 5, 5, 5));
        panelLeft.add(containerImageLabel);

        final DefaultComboBoxModel itemsLevels = new DefaultComboBoxModel();
        itemsLevels.addElement("Select level");
        itemsLevels.addElement("Easy");
        itemsLevels.addElement("Medium");
        itemsLevels.addElement("Hard");
        final JComboBox listLevels = new JComboBox(itemsLevels);
        listLevels.setBorder(BorderFactory.createLineBorder(new Color(209,227,134)));
        panelRight.add(listLevels,BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelGroupBotones.setSize(300,300);
        GridLayout layout = new GridLayout(0,1);
        panelBotones.setBorder(new EmptyBorder(40, 10, 10, 10));
        layout.setHgap(100);
        panelBotones.setLayout(layout);
        JButton start = new JButton("Start");
        start.setBackground(new Color(209,227,134));
        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int levelSelected = listLevels.getSelectedIndex();
                if(levelSelected != 0){
                    Game game = new Game();
                    game.startGame((String)listLevels.getSelectedItem());
                }else{
                    JOptionPane.showMessageDialog(null,"select a level to continue ");
                }
            }
        });
        panelBotones.add(start);
        panelGroupBotones.add(panelBotones);
        containerBotones.add(panelGroupBotones,BorderLayout.CENTER);
        panelRight.add(containerBotones);
        add(panelLeft, "West");
        add(panelRight, "Center");
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent event) {
                if (event.getSource() instanceof Menu) {
                    ((Menu) event.getSource()).exitFrame();
                }
            }
        });
        setTitle("Menu");
        setResizable(false);
        setSize(new Dimension(350,200));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
        setLocationRelativeTo(null);
        pack();
    }
    public static synchronized Menu getInstance(String fileName) {
        if (INSTANCE == null) {
            INSTANCE = new Menu();
        }
        return INSTANCE;
    }
    public void exitFrame(){
        System.exit(0);
    }
}
