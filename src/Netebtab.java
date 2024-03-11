import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.*;

import org.w3c.dom.events.MouseEvent;


public class Netebtab extends JFrame implements ActionListener{
    
    JButton playButton, helpButton;
    JPanel homeJPanel;
    GameGrid grid;
    JPanel gridOptions;
    static Font customFont;
    JButton gridoption1;
    JButton gridoption2;
    JButton gridoption3;
    JLabel playerTurn;
    JLabel player1Label;
    JLabel player2Label;
    JButton homeButton;
    
    public Font getCustomFont() {
        return customFont;
    }

    Netebtab() {
        //Jframe setup      

        this.setTitle("Netebtab");
        ImageIcon logo = new ImageIcon("NetebtabLogo.png");
        this.setIconImage(logo.getImage());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        this.setSize(d);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(0xdedede));


        Image image = logo.getImage();
        int oWitdth = image.getWidth(null);
        int oHeight = image.getHeight(null);

        int dWidth = 300;
        double scaleFactor = (double)dWidth / oWitdth;
        int newHeight = (int)(oHeight * scaleFactor);

        Image homeImage = image.getScaledInstance(dWidth, newHeight, Image.SCALE_SMOOTH);
        logo = new ImageIcon(homeImage);

        //JLable setup
        JLabel homeJLabel = new JLabel("NETEBTAB");
        homeJLabel.setIcon(logo);
        homeJLabel.setHorizontalTextPosition(JLabel.CENTER);
        homeJLabel.setVerticalTextPosition(JLabel.BOTTOM);
        homeJLabel.setHorizontalAlignment(JLabel.CENTER);
        homeJLabel.setVerticalAlignment(JLabel.CENTER);
        homeJLabel.setForeground(new Color(0x003A4D));
        homeJLabel.setIconTextGap(15);

        //JButtons setup

            //play button
        playButton = new JButton("Play");
        playButton.setPreferredSize(new Dimension(90, 45));
        playButton.setBackground(new Color(0x96CEC8));
        playButton.setForeground(new Color(0x003A4D));
        playButton.setVerticalTextPosition(JButton.BOTTOM);
        playButton.setHorizontalTextPosition(JButton.LEFT);
        playButton.setFocusable(false);
        playButton.setBorder(BorderFactory.createEtchedBorder());
        playButton.addActionListener(this);
        
        //help button
        helpButton = new JButton("Help");
        helpButton.setPreferredSize(new Dimension(70, 35));
        helpButton.setBackground(new Color(0x96CEC8));
        helpButton.setForeground(new Color(0x003A4D));
        helpButton.setHorizontalTextPosition(JButton.CENTER);
        helpButton.setVerticalTextPosition(JButton.CENTER);
        helpButton.setFocusable(false);
        helpButton.setBorder(BorderFactory.createEtchedBorder());
        //helpButton.setBorder(new BorderFactory());
        helpButton.addActionListener(this);

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("KnicknackRegular.otf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            homeJLabel.setFont(new Font(customFont.getName(), Font.PLAIN, 35));
            playButton.setFont(new Font(customFont.getName(), Font.PLAIN, 25));
            helpButton.setFont(new Font(customFont.getName(), Font.PLAIN, 25));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        //Jpanels setup
        homeJPanel = new JPanel();
        int height = this.getHeight();
        int width = this.getWidth();
        homeJPanel.setBounds(((width - 300) / 2), ((height - 500) / 2) + 15, 300, 500);
        homeJPanel.setBackground(null);
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(null);
        JPanel buttoPanel = new JPanel();
        buttoPanel.setLayout(new GridLayout(1, 2, 20, 10));
        buttoPanel.setBackground(null);


        playerTurn = new JLabel("Player: 1");
        playerTurn.setFont(new Font(customFont.getName(), Font.PLAIN, 40));
        playerTurn.setForeground(new Color(0x0085A4));
        playerTurn.setBounds(((this.getWidth() - 160)/ 2), ((this.getHeight() - 650) / 2), 200, 80);
        playerTurn.setVisible(false);

        player1Label = new JLabel("Player 1 Score: 0");
        player1Label.setFont(new Font(customFont.getName(), Font.PLAIN, 25));
        player1Label.setForeground(new Color(0x0085A4));
        player1Label.setBounds(100, ((this.getHeight() - 300) / 2), 200, 80);
        player1Label.setVisible(false);
        player1Label.setHorizontalTextPosition(SwingConstants.CENTER);
    
        player2Label = new JLabel("Player 2 Score: 0");
        player2Label.setFont(new Font(customFont.getName(), Font.PLAIN, 25));
        player2Label.setForeground(new Color(0x327252));
        player2Label.setBounds(width - 300, ((this.getHeight() - 300) / 2), 200, 80);
        player2Label.setVisible(false);
        player2Label.setHorizontalTextPosition(SwingConstants.CENTER);

        homeButton = new JButton("Home");
        homeButton.setBounds(20, 20, 100, 50);
        homeButton.setFont(new Font(customFont.getName(), Font.PLAIN, 20));
        homeButton.setForeground(new Color(0x003A4D));
        homeButton.setBackground(new Color(0x96CEC8));
        homeButton.setFocusable(false);
        homeButton.setBorder(BorderFactory.createEtchedBorder());
        homeButton.setHorizontalTextPosition(SwingConstants.CENTER);
        homeButton.setVerticalTextPosition(SwingConstants.CENTER);
        homeButton.addActionListener(this);
        homeButton.setVisible(false);

        //Layering and displaying components
        logoPanel.add(homeJLabel);
        buttoPanel.add(playButton);
        //buttoPanel.add(helpButton);
        homeJPanel.add(logoPanel);
        homeJPanel.add(buttoPanel);
        this.add(playerTurn);
        this.add(player1Label);
        this.add(player2Label);
        this.add(homeJPanel);
        this.add(homeButton);
        this.setVisible(true);
    }

    public void gridOptionMenu() {
        gridOptions = new JPanel();
        gridOptions.setBounds(((this.getWidth() - 300) / 2), ((this.getHeight() - 400) / 2) + 15, 300, this.getHeight());
        gridOptions.setLayout(new FlowLayout(FlowLayout.CENTER));
        gridOptions.setOpaque(false);
        
        JLabel gridLabel = new JLabel("Choose Grid Size");
        gridLabel.setFont(new Font(customFont.getName(), Font.PLAIN, 35));
        gridLabel.setForeground(new Color(0x003A4D));

        gridoption1 = new JButton("5 x 5");
        gridoption1.setFont(new Font(customFont.getName(), Font.PLAIN, 20));
        gridoption1.setPreferredSize(new Dimension(100, 35));
        gridoption1.setBackground(new Color(0x96CEC8));
        gridoption1.setForeground(new Color(0x003A4D));
        gridoption1.setHorizontalTextPosition(JButton.CENTER);
        gridoption1.setVerticalTextPosition(JButton.BOTTOM);
        gridoption1.setFocusable(false);
        gridoption1.setBorder(BorderFactory.createEtchedBorder());
        gridoption1.addActionListener(this);
        
        
        gridoption2 = new JButton("8 x 8");
        gridoption2.setFont(new Font(customFont.getName(), Font.PLAIN, 20));
        gridoption2.setPreferredSize(new Dimension(100, 35));
        gridoption2.setBackground(new Color(0x96CEC8));
        gridoption2.setForeground(new Color(0x003A4D));
        gridoption2.setHorizontalTextPosition(JButton.CENTER);
        gridoption2.setVerticalTextPosition(JButton.BOTTOM);
        gridoption2.setFocusable(false);
        gridoption2.setBorder(BorderFactory.createEtchedBorder());
        gridoption2.addActionListener(this);
        
        gridoption3 = new JButton("10 x 10");
        gridoption3.setFont(new Font(customFont.getName(), Font.PLAIN, 20));
        gridoption3.setPreferredSize(new Dimension(100, 35));
        gridoption3.setBackground(new Color(0x96CEC8));
        gridoption3.setForeground(new Color(0x003A4D));
        gridoption3.setHorizontalTextPosition(JButton.CENTER);
        gridoption3.setVerticalTextPosition(SwingConstants.CENTER);
        gridoption3.setFocusable(false);
        gridoption3.setBorder(BorderFactory.createEtchedBorder());
        gridoption3.addActionListener(this);

        JPanel gridButtonPanel = new JPanel();
        gridButtonPanel.setLayout(new GridLayout(3, 1, 10, 15));
        gridButtonPanel.setOpaque(false);
        
        gridButtonPanel.add(gridoption1);
        gridButtonPanel.add(gridoption2);
        gridButtonPanel.add(gridoption3);
        gridOptions.add(gridLabel);
        gridOptions.add(gridButtonPanel);
        add(gridOptions);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int width = this.getWidth();
        int height = this.getHeight();
        
        if (e.getSource() == playButton) {
            homeJPanel.setVisible(false);
            gridOptionMenu();
        } else if (e.getSource()==helpButton) {
            homeJPanel.setVisible(false);
        } else if (e.getSource() == gridoption1){
            gridOptions.setVisible(false);
            grid = new GameGrid(height, width, 5, this);
            add(grid);
        } else if (e.getSource() == gridoption2){
            gridOptions.setVisible(false);
            grid = new GameGrid(height, width, 8, this);
            add(grid);
        } else if (e.getSource() == gridoption3){
            gridOptions.setVisible(false);
            grid = new GameGrid(height, width, 10, this);
            add(grid);
        } else if (e.getSource() == homeButton)
        {
            //JOptionPane.showMessageDialog(null, , "Match End", JOptionPane.PLAIN_MESSAGE);
            grid.setVisible(false);
            player1Label.setVisible(false);
            player2Label.setVisible(false);
            playerTurn.setVisible(false);
            homeButton.setVisible(false);
            homeJPanel.setVisible(true);
        }
    }

}