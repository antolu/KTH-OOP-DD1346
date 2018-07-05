import java.io.*;
import java.lang.StringBuilder;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.event.*;

public class Controller extends JPanel 
                        implements ChangeListener, ActionListener {
    private Model   model;
    private View    view;
    private JSlider LSlider;
    private JSlider timeSlider;
    private Timer   timer;
    private int     dT = 1000;
    private int     time = 0;
    private PrintWriter outputStream = null;
    private int     writeIndex = 0;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        LSlider = new JSlider(0, 50, model.getStep());
        timeSlider = new JSlider(0, 2000, dT);

        LSlider.addChangeListener(this);
        LSlider.setMajorTickSpacing(25);
        LSlider.setMinorTickSpacing(5);
        LSlider.setPaintTicks(true);
        LSlider.setPaintLabels(true);

        timeSlider.setMajorTickSpacing(1000);
        timeSlider.setMinorTickSpacing(200);
        timeSlider.setPaintTicks(true);
        timeSlider.setPaintLabels(true);
        timeSlider.addChangeListener(this);

        timer = new Timer(dT, this);
        timer.setInitialDelay(1);

        this.add(LSlider, BorderLayout.WEST);
        this.add(timeSlider, BorderLayout.EAST);
        this.setLayout(new FlowLayout());

        try {
            outputStream = new PrintWriter(new BufferedWriter(new FileWriter("output.csv")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        /* Write lines to logging file */
        StringBuilder outputLine = new StringBuilder(Double.toString(time));
        for (Model.Particle particle : model.particles) {
            outputLine.append(", ");
            outputLine.append(particle.x);
            outputLine.append(", ");
            outputLine.append(particle.y);
        }
        outputStream.println(outputLine);

        timer.start();
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();

        if (source == LSlider) {
            model.setStep(source.getValue());
        }
        else if (source == timeSlider) {
            if (source.getValue() == 0) {
                // Pause
            }
            else {
                timer.setDelay(source.getValue());
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        model.updatePosition();
        view.repaint();

        time += timer.getDelay();

        if (writeIndex < 1000) {
            StringBuilder outputLine = new StringBuilder(Integer.toString(time));
            // String outputLine = Integer.toString(time);
            for (Model.Particle particle : model.particles) {
                // outputLine = outputLine + ", " + particle.x + ", " + particle.y;
                outputLine.append(", ");
                outputLine.append(particle.x);
                outputLine.append(", ");
                outputLine.append(particle.y);
            }
            outputStream.println(outputLine);

            writeIndex++;
        }
    }
}