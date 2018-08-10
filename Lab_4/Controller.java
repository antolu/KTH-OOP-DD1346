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
    private Model           model;
    private View            view;
    private JSlider         LSlider;
    private JSlider         timeSlider;
    private LoggerButton    loggerButton;
    private Boolean         isLogging       = false;
    private Timer           timer;
    private int             dT              = 250;
    private int             time            = 0;
    private PrintWriter     outputStream    = null;
    private int             writeIndex      = 0;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        LSlider = new JSlider(0, 50, model.getStep());
        timeSlider = new JSlider(0, 1000, dT);

        LSlider.addChangeListener(this);
        LSlider.setMajorTickSpacing(25);
        LSlider.setMinorTickSpacing(5);
        LSlider.setPaintTicks(true);
        LSlider.setPaintLabels(true);

        timeSlider.setMajorTickSpacing(500);
        timeSlider.setMinorTickSpacing(100);
        timeSlider.setPaintTicks(true);
        timeSlider.setPaintLabels(true);
        timeSlider.addChangeListener(this);

        loggerButton = new LoggerButton();
        loggerButton.addActionListener(this);

        timer = new Timer(dT, this);
        timer.setInitialDelay(1);

        this.add(LSlider, BorderLayout.WEST);
        this.add(timeSlider, BorderLayout.WEST);
        this.add(loggerButton, BorderLayout.CENTER);
        this.setLayout(new FlowLayout());

        try {
            outputStream = new PrintWriter(new BufferedWriter(new FileWriter("output.csv")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        logLine();
        timer.start();
    }

    private void logLine() {
        if (!isLogging) return;
        if (writeIndex > 100) return; 
        /* Write lines to logging file */
        StringBuilder outputLine = new StringBuilder(Double.toString(time));

        for (Model.Particle particle : model.particles) {
            outputLine.append(", ");
            outputLine.append(particle.x);
            outputLine.append(", ");
            outputLine.append(particle.y);
        }
        outputStream.println(outputLine);
        writeIndex++;
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
        if (e.getSource() == timer) {
            model.updatePosition();
            view.repaint();

            time += timer.getDelay();

            logLine();
        }
        else {
            loggerButton.toggleState();
            isLogging = !isLogging;
        }
    }
}