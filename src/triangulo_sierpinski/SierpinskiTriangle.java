package triangulo_sierpinski;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Sebastian Zarate
 */
public class SierpinskiTriangle extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private int maxDepth;

    public SierpinskiTriangle(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[] xPoints = {0, WIDTH, WIDTH / 2};
        int[] yPoints = {HEIGHT, HEIGHT, 0};
        drawSierpinski(g, maxDepth, xPoints, yPoints);
    }

    public void drawSierpinski(Graphics g, int depth, int[] xPoints, int[] yPoints) {
        if (depth == 0) {
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            int[] xMid = new int[3];
            int[] yMid = new int[3];
            for (int i = 0; i < 3; i++) {
                xMid[i] = (xPoints[i] + xPoints[(i + 1) % 3]) / 2;
                yMid[i] = (yPoints[i] + yPoints[(i + 1) % 3]) / 2;
            }

            drawSierpinski(g, depth - 1, new int[]{xPoints[0], xMid[0], xMid[2]}, new int[]{yPoints[0], yMid[0], yMid[2]});
            drawSierpinski(g, depth - 1, new int[]{xMid[0], xPoints[1], xMid[1]}, new int[]{yMid[0], yPoints[1], yMid[1]});
            drawSierpinski(g, depth - 1, new int[]{xMid[1], xPoints[2], xMid[2]}, new int[]{yMid[1], yPoints[2], yMid[2]});
        }
    }

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Ingrese el número de iteraciones:");
        int maxDepth = Integer.parseInt(input);

        JFrame frame = new JFrame("Sierpinski Triangle");
        SierpinskiTriangle triangle = new SierpinskiTriangle(maxDepth);
        frame.add(triangle);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
