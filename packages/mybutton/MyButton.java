package mybutton;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;   

public class MyButton extends JButton implements ActionListener
{
    private boolean isPressed;

    private String notPressedString = "Push me!";
    private String isPressedString = "You pushed me!";

    private Color color1;
    private Color color2;

    public MyButton(Color col1, Color col2, String text1, String text2)
    {
        isPressed = false;

        color1 = col1;
        color2 = col2;
        setBackground(col1);

        notPressedString = text1;
        isPressedString = text2;
        setText(text1);

        addActionListener(this);
    }

    public MyButton()
    {
        notPressedString = "Push me!";
        isPressedString = "Push me again!";
        setText(notPressedString);

        color1 = Color.blue;
        color2 = Color.red;
        setBackground(color1);

        addActionListener(this);
    }

    private void toggleState()
    {
        if (isPressed)
        {
            setText(isPressedString);
            setBackground(color2);
        }
        else
        {
            setText(notPressedString);
            setBackground(color1);
        }

        isPressed = !isPressed;
    }


    public void actionPerformed(ActionEvent e)
    {
        toggleState();
    }
}