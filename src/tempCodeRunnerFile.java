import javax.swing.*;
import java.awt.*;

public class DottedPanel extends JPanel {

    public DottedPanel() {
        setPreferredSize(new Dimension(200, 200));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw circles at the corners
        int diameter = 10;
        g.fillOval(0, 0, diameter, diameter); // Top-left corner
        g.fillOval(getWidth() - diameter, 0, diameter, diameter); // Top-right corner
        g.fillOval(0, getHeight() - diameter, diameter, diameter); // Bottom-left corner
        g.fillOval(getWidth() - diameter, getHeight() - diameter, diameter, diameter); // Bottom-right corner
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.add(new DottedPanel());
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}