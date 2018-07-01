package mybutton;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;   

public class MyButton extends JButton
{
    private boolean isPressed = false;

    private String notPressedString = "Push me!";
    private String isPressedString = "You pushed me!";

    private Color color1;
    private Color color2;

    public MyButton(Color col1, Color col2, String text1, String text2)
    {
        color1 = col1;
        color2 = col2;
        setBackground(col1);

        notPressedString = text1;
        isPressedString = text2;
        setText(text1);
    }

    public MyButton()
    {
        this(Color.blue, Color.red, "Push me!", "Push me again!");
    }

    public void toggleState()
    {
        if (!isPressed)
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
}