import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.*;


public class Netebtab extends JFrame implements ActionListener{
    
    JButton playButton, helpButton;
    JPanel homeJPanel;
    static Font customFont;
    
    public Font getCustomFont() {
        return customFont;
    }

    Netebtab() {
        //Jframe setup

        this.setTitle("Netebtab");
        ImageIcon logo = new ImageIcon("NetebtabLogo.png");
        this.setIconImage(logo.getImage());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setAlwaysOnTop(true);
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

        //Layering and displaying components
        logoPanel.add(homeJLabel);
        buttoPanel.add(playButton);
        buttoPanel.add(helpButton);
        homeJPanel.add(logoPanel);
        homeJPanel.add(buttoPanel);
        this.add(homeJPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            homeJPanel.setVisible(false);
            int height = this.getHeight();
            int width = this.getWidth();
            GameGrid grid = new GameGrid(height, width);
            add(grid);
        } else if (e.getSource()==helpButton) {
            homeJPanel.setVisible(false);
        }
    }

}