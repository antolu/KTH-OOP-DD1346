import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;   

public class LoggerButton extends JButton
{
    private boolean isPressed = false;

    private String notPressedString = "Start logging";
    private String isPressedString = "Stop logging";

    public LoggerButton()
    {
        setText(notPressedString);
    }

    public void toggleState()
    {
        if (!isPressed)
        {
            setText(isPressedString);
        }
        else
        {
            setText(notPressedString);
        }

        isPressed = !isPressed;
    }
}