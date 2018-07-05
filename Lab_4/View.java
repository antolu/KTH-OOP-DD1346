

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class View extends JPanel {

    private Model model;
    private final double DIM = 250;

    public View(Model model) {
        this.model = model;

        this.setPreferredSize(new Dimension( 2 * (int) DIM, 2 * (int) DIM));
        this.setForeground(Color.lightGray);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        Model.Particle[] particles = model.particles;
        for (Model.Particle particle : particles) {
            if (particle.hasReachedBorder())
            {
                g2.setColor(Color.red);
            }
            else
            {
                g2.setColor(Color.black);
            }
            g2.fill(new Ellipse2D.Double(DIM + particle.x, DIM + particle.y, 2, 2));
        }
    }

}