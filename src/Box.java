import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Box extends JPanel {

    Point position;
    ArrayList<Line> borders;
    boolean isboxed = false;

    Box(Line line1, Line line2, Line line3, Line line4){
        borders = new ArrayList<>();
        borders.add(line1);    
        borders.add(line2);    
        borders.add(line3);    
        borders.add(line4);
    }

    public boolean isboxed() {
        return isboxed;
    }

    public boolean check(boolean player){
        boolean ischecked = true;

        for (int i = 0; i < 4; i++){
            if (!(borders.get(i).isClicked())) {
                ischecked = false;
            }
        }
        if (ischecked)
        {
            if (player){
                setBackground(new Color(0x0085A4));
                isboxed = true;
            } else{
                setBackground(new Color(0x327252));
                isboxed = true;
            }
            repaint();
            return (true);
        }
        return (false);
    }
}