

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

        for (Model.Particle particle : model.particles) {
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
        // g2.setColor(Color.red);
        // for (Model.Particle particle : model.stuckParticles) {
        //     g2.fill(new Ellipse2D.Double(DIM + particle.x, DIM + particle.y, 2, 2));
        // }
        // g2.setColor(Color.black);
        // for (Model.Particle particle : model.particles) {
        //     g2.fill(new Ellipse2D.Double(DIM + particle.x, DIM + particle.y, 2, 2));
        // }
    }

}