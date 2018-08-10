import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  

import mybutton.*;

public class Main 
{
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("A button");
        MyButton button = new MyButton();

        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes window when (x) is pressed in OS
        // frame.setPreferredSize(new Dimension(500,500));

        // button.setPreferredSize(new Dimension(200,100));

        frame.getContentPane().add(button);
        frame.pack(); // Size frame to match contents according to layout manager

        frame.setVisible(true);
    }
}