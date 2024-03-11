import java.util.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class GameGrid extends JLayeredPane {

    int gridSize;
    int gridDimension = 500;
    final static float dotScale = 0.4f;
    JPanel dotPanel;
    JPanel hLinePanel;
    JPanel vLinePanel;
    JPanel emptyPanel;
    JPanel boxPanel;
    HashMap<Point, Line> vLineMap;
    HashMap<Point, Line> hLineMap;
    HashMap<Point, Box> boxMap;
    boolean isPlayer1;
    int player1Score = 0;
    int player2Score = 0;
    int linecount = 0;

    GameGrid(int height, int width, int size, Netebtab current) {
        dotPanel = new JPanel();
        hLinePanel = new JPanel();
        vLinePanel = new JPanel();
        boxPanel = new JPanel();
        emptyPanel = new JPanel();

        this.gridSize = size;
        this.isPlayer1 = true;

        setBounds(((width - gridDimension) / 2), ((height - gridDimension) / 2) + 10, gridDimension, gridDimension);

        current.playerTurn.setVisible(true);
        current.player1Label.setVisible(true);
        current.player2Label.setVisible(true);
        current.homeButton.setVisible(true);

        emptyPanel.setBounds(0, 0,gridDimension, gridDimension);
        emptyPanel.setBackground(new Color(0xffffff));

        hLinePanel.setBounds(((((500 / gridSize) - 30) / 2) + 15), 0, (gridDimension - 2 * ((((500 / gridSize) - 30) / 2) + 15)), gridDimension);
        hLinePanel.setLayout(new GridLayout(gridSize, gridSize - 1));
        hLinePanel.setOpaque(false);

        hLineMap = new HashMap<>();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize - 1; j++)
            {
                Line hline = new Line(gridSize, 1, new Point(i, j));
                JPanel hlinebox = new JPanel();
                hlinebox.setLayout(null);
                hlinebox.setOpaque(false);
                hline.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        hline.setBackground(new Color(0x003A4D));
                        hline.setClicked(true);
                        if (hline.isEnabled())
                            updateState(hline, current);
                        hline.setEnabled(false);
                    }
                });
                hLineMap.put(new Point(i, j), hline);
                hlinebox.add(hline);
                hLinePanel.add(hlinebox);
            }
        }
        
        vLinePanel.setBounds(0, ((((500 / gridSize) - 30) / 2) + 15), gridDimension, (gridDimension - 2 * ((((500 / gridSize) - 30) / 2) + 15)));
        vLinePanel.setLayout(new GridLayout(gridSize - 1, gridSize));
        vLinePanel.setOpaque(false);

        vLineMap = new HashMap<>();
        for (int i = 0; i < gridSize - 1; i++) {
            for (int j = 0; j < gridSize; j++)
            {
                Line vline = new Line(gridSize, 2, new Point(i, j));
                JPanel vlinebox = new JPanel();
                vlinebox.setLayout(null);
                vlinebox.setOpaque(false);
                vline.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        vline.setBackground(new Color(0x003A4D));
                        vline.setClicked(true);
                        if (vline.isEnabled())
                            updateState(vline, current);
                        vline.setEnabled(false);
                    }
                });
                vLineMap.put(new Point(i, j), vline);
                vlinebox.add(vline);
                vLinePanel.add(vlinebox);
            }
        }

        boxPanel.setBounds((gridDimension / gridSize / 2), (gridDimension / gridSize / 2), (gridDimension - (gridDimension / gridSize)),(gridDimension - (gridDimension / gridSize)));
        boxPanel.setLayout(new GridLayout(gridSize - 1, gridSize - 1));

        boxMap = new HashMap<>();
        for (int i = 0; i < gridSize - 1; i++){
            for (int j = 0; j < gridSize - 1; j++)
            {
                Line line1 = vLineMap.get(new Point(i, j));
                Line line2 = vLineMap.get(new Point(i, j + 1));
                Line line3 = hLineMap.get(new Point(i, j));
                Line line4 = hLineMap.get(new Point(i + 1, j));
                Box box = new Box(line1, line2, line3, line4);
                boxMap.put(new Point(i, j), box);
                boxPanel.add(box);
            }
        }

        dotPanel.setBounds(0, 0,gridDimension, gridDimension);
        dotPanel.setLayout(new GridLayout(gridSize, gridSize));
        dotPanel.setOpaque(false);
        for (int i = 0; i < gridSize; i++){
            for (int j = 0; j < gridSize; j++){
                Dot dot = new Dot(gridSize, ((gridDimension / gridSize) * dotScale));
                dotPanel.add(dot);
            }
        }
    
        add(emptyPanel, Integer.valueOf(1));
        add(boxPanel, Integer.valueOf(2));
        add(hLinePanel, Integer.valueOf(3));
        add(vLinePanel, Integer.valueOf(4));
        add(dotPanel, Integer.valueOf(5));
    }


    public void updateState(Line line, Netebtab current) {
        int x , y;
        boolean isbox = false;
        int boxedCount = 0;

        x = (int) line.position.getX();
        y = (int) line.position.getY();

        if (line.isVertical()){
            if (y < gridSize - 1) {
                Box boxright = boxMap.get(new Point(x, y));
                if (!boxright.isboxed()){
                    isbox = boxright.check(isPlayer1);
                    if (isbox) {
                        boxedCount++;
                    }
                }
            }
            if (y > 0){
                Box boxleft = boxMap.get(new Point(x, y - 1));
                if (!boxleft.isboxed()){
                    isbox = boxleft.check(isPlayer1);
                    if (isbox) {
                        boxedCount++;
                    }
                }
            }
        } else {
                if (x < gridSize - 1) {
                    Box boxright = boxMap.get(new Point(x, y));
                    if (!boxright.isboxed()){
                        isbox = boxright.check(isPlayer1);
                        if (isbox) {
                            boxedCount++;
                        }
                    }
                }
                if (x > 0){
                    Box boxleft = boxMap.get(new Point(x - 1, y));
                    if (!boxleft.isboxed()){
                        isbox = boxleft.check(isPlayer1);
                        if (isbox) {
                            boxedCount++;
                        }
                    }
                }
        }
        if(boxedCount > 0) {
            if (isPlayer1){  
                player1Score += boxedCount;
            } else {
                player2Score += boxedCount;
            } 
        } else {
            isPlayer1 = !isPlayer1;
            if (isPlayer1){
                current.playerTurn.setText("Player: 1");
                current.playerTurn.setForeground(new Color(0x0085A4));
                current.playerTurn.repaint();
            } else {
                current.playerTurn.setText("Player: 2");
                current.playerTurn.setForeground(new Color(0x327252));
                current.playerTurn.repaint();
            }
        }
        linecount++;
        scoreBoardupdate(current);
        if (linecount == (((gridSize) * (gridSize - 1)) * 2)) {
            String message;

            if (player1Score > player2Score){
                message = "Player 1 Wins";
            } else if (player2Score > player1Score){
                message = "Player 2 Wins";
            } else {
                message = "Draw Game";
            }
            JOptionPane.showMessageDialog(null, message, "Match End", JOptionPane.PLAIN_MESSAGE);
            current.grid.setVisible(false);
            current.player1Label.setVisible(false);
            current.player2Label.setVisible(false);
            current.playerTurn.setVisible(false);
            current.homeButton.setVisible(false);
            current.homeJPanel.setVisible(true);
        }
    }

    public void scoreBoardupdate(Netebtab current) {
        current.player1Label.setText("Player 1 Score: " + player1Score);
        current.player2Label.setText("Player 2 Score: " + player2Score);
        current.player1Label.repaint();
        current.player2Label.repaint();
    }
}