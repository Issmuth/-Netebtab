import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import org.w3c.dom.events.MouseEvent;

public class Line extends JButton {

    int gridSize;
    int gridDimension = 500;
    boolean isClicked = false;

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean isClicked) {
        this.isClicked = isClicked;
    }

    Line(int gridSize, int orientation){
        this.gridSize = gridSize;
        this.setBackground(new Color(0xC9D3D9));
        this.setFocusable(false);
        if (orientation == 1) {
            setBounds(0, ((gridDimension / gridSize) - 7) / 2, (gridDimension / gridSize), 7);
        } else {
            setBounds(((gridDimension / gridSize - 7)) / 2, 0, 7, (gridDimension / gridSize));
        }
    }
}