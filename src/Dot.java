import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

public class Dot extends JPanel{
    
    int dotSize;
    int gridSize;

    Dot(int gridSize, float dotSize){
        this.gridSize = gridSize;
        this.dotSize = (int) dotSize;
        this.setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d =  (Graphics2D) g;
        g2d.setColor(new Color(0x489A90));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillOval((((500 / gridSize) - dotSize) / 2), (((500 / gridSize) - dotSize) / 2), dotSize, dotSize);
    }
}