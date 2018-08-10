import java.util.*;

import javax.swing.*;  
import java.awt.Color;

import mybutton.*;

public class MainC1 {
    private ButtonGrid buttonGrid;

    public MainC1(int numberOfButtons) {  
        JFrame frame = new JFrame("A grid of buttons.");

        buttonGrid = new ButtonGrid(numberOfButtons);

        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(buttonGrid);
        frame.pack();

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        int numberOfButtons = 0;
        if (args.length == 1) {
            try {
                numberOfButtons = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        } else {
            System.err.println("Incorrect number of arguments provided. Should be 1 argument.");
            return;
        }
        new MainC1(numberOfButtons);
    }
}