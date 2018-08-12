

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class View extends JPanel {

    private Model model;
    private final double DIM;

    public View(Model model) {
        this.model = model;
        DIM = model.DIM;

        this.setPreferredSize(new Dimension( 2 * (int) DIM, 2 * (int) DIM));
        this.setForeground(Color.lightGray);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.red);
        ArrayList<double[]> stuckPositions = model.getStuckPositions();
        for (double[] stuckPosition : stuckPositions) {
            g2.fill(new Ellipse2D.Double(DIM + stuckPosition[0], 
                    DIM + stuckPosition[1], 1, 1));
        }
        g2.setColor(Color.black);
        ArrayList<double[]> particlePositions = model.getPositions();
        for (double[] particlePosition : particlePositions) {
            g2.fill(new Ellipse2D.Double(DIM + particlePosition[0], 
                    DIM + particlePosition[1], 1, 1));
        }
    }

}