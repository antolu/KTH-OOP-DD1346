import javax.swing.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class MyFrame extends JFrame {
    Model model;
    View view;
    Controller controller;

    public MyFrame(int numberOfParticles) {
        model = new Model(numberOfParticles);
        view = new View(model);

        Container container = getContentPane();

        controller = new Controller(model, view);

        this.setSize(520, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        container.add(view, BorderLayout.NORTH);
        container.add(controller, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        int numberOfParticles = 0;
        if (args.length == 1) {
            try {
                numberOfParticles = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        } else {
            System.err.println("Incorrect number of arguments provided. Should be 1 argument.");
            return;
        }
        new MyFrame(numberOfParticles);
    }
}