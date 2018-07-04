

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class View extends JPanel {

    private Model model;
    private final double DIM = 250;

    public View(Model model) {
        this.model = model;

        this.setPreferredSize(new Dimension( 2 * (int) DIM, 2 * (int) DIM));
        this.setForeground(Color.lightGray);

    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);

        Model.Particle[] particles = model.particles;
        for (Model.Particle particle : particles) {
            g2.fill(new Ellipse2D.Double(DIM + particle.x, DIM + particle.y, 5, 5));
        }
    }

}