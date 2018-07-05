

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.event.*;
import javax.swing.event.ChangeListener;
import java.awt.Color;

public class Controller extends JPanel 
                        implements ChangeListener, ActionListener {
    Model   model;
    View    view;
    JSlider LSlider;
    JSlider timeSlider;
    Timer   timer;
    int     dT = 1000;

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
    }
}