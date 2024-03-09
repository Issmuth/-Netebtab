import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class GameGrid extends JLayeredPane {

    int gridSize = 5;
    int gridDimension = 500;
    final static float dotScale = 0.4f;
    JPanel dotPanel;
    JPanel hLinePanel;
    JPanel vLinePanel;
    JPanel emptyPanel;
    JPanel boxPanel;

    GameGrid(int height, int width){
        dotPanel = new JPanel();
        hLinePanel = new JPanel();
        vLinePanel = new JPanel();
        boxPanel = new JPanel();
        emptyPanel = new JPanel();

        setBounds(((width - gridDimension) / 2), ((height - gridDimension) / 2) + 10, gridDimension, gridDimension);

        emptyPanel.setBounds(0, 0,gridDimension, gridDimension);
        emptyPanel.setBackground(new Color(0xffffff));

        hLinePanel.setBounds(((((500 / gridSize) - 30) / 2) + 15), 0, (gridDimension - 2 * ((((500 / gridSize) - 30) / 2) + 15)), gridDimension);
        hLinePanel.setLayout(new GridLayout(gridSize, gridSize - 1));
        hLinePanel.setOpaque(false);
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize - 1; j++)
            {
                Line hline = new Line(gridSize, 1);
                JPanel hlinebox = new JPanel();
                hlinebox.setLayout(null);
                hlinebox.setOpaque(false);
                hline.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        hline.setBackground(new Color(0x003A4D));
                        hline.setEnabled(false);
                    }
                });
                hlinebox.add(hline);
                hLinePanel.add(hlinebox);
            }
        }

        vLinePanel.setBounds(0, ((((500 / gridSize) - 30) / 2) + 15), gridDimension, (gridDimension - 2 * ((((500 / gridSize) - 30) / 2) + 15)));
        vLinePanel.setLayout(new GridLayout(gridSize - 1, gridSize));
        vLinePanel.setOpaque(false);
        for (int i = 0; i < gridSize - 1; i++) {
            for (int j = 0; j < gridSize; j++)
            {
                Line vline = new Line(gridSize, 2);
                JPanel vlinebox = new JPanel();
                vlinebox.setLayout(null);
                vlinebox.setOpaque(false);
                vline.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        vline.setBackground(new Color(0x003A4D));
                        vline.setClicked(true);
                        vline.setEnabled(false);
                    }
                });
                vlinebox.add(vline);
                vLinePanel.add(vlinebox);
            }
        }

        boxPanel.setBounds((gridDimension / gridSize / 2), (gridDimension / gridSize / 2), (gridDimension - (gridDimension / gridSize)),(gridDimension - (gridDimension / gridSize)));
        boxPanel.setLayout(new GridLayout(gridSize - 1, gridSize - 1));
        

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
        add(hLinePanel, Integer.valueOf(2));
        add(vLinePanel, Integer.valueOf(3));
        add(dotPanel, Integer.valueOf(4));
    }
}