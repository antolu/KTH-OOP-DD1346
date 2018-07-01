import java.util.*;

import javax.swing.*;
import java.io.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

import mybutton.MyButton;

public class ButtonGrid extends JPanel{

    private MyButton[][] buttonArray;
    private int gridWidth;
    private Vector<String> textFields = new Vector<String>();

    public ButtonGrid(int numberOfButtons) {
        readFile();
        gridWidth = (int) Math.ceil(Math.sqrt((double) numberOfButtons));

        this.setLayout(new GridLayout(gridWidth, gridWidth));
        buttonArray = new MyButton[gridWidth][gridWidth];

        ActionListener actionListener = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                MyButton pressedButton = (MyButton) e.getSource();

                for (int i = 0; i < gridWidth; i++) {
                    for (int k = 0; k < gridWidth && i * gridWidth + k < numberOfButtons; k++) {
                        if (pressedButton != buttonArray[i][k]){
                            buttonArray[i][k].toggleState();
                        }
                    }
                }
            }
        };
                        

        for (int i = 0; i < gridWidth; i++) {
            for (int k = 0; k < gridWidth && i * gridWidth + k < numberOfButtons; k++) {
                buttonArray[i][k] = new MyButton(randomColor(), randomColor(), 
                                                 randomButtonLabel(), randomButtonLabel());
                buttonArray[i][k].addActionListener(actionListener);
                this.add(buttonArray[i][k]);
            }
        }
    }

    private void readFile(){
        try{
            BufferedReader buffer = new BufferedReader(new FileReader("TextFields.txt"));
            String line;
            while((line = buffer.readLine()) != null) {
                textFields.add(line);
            }
            buffer.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }  
    }

    private String randomButtonLabel() {
        int randIndex = (int) Math.floor(textFields.size() * Math.random());
        return textFields.get(randIndex);
    }

    private Color randomColor() {
        Random rand = new Random();

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        return new Color(r, g, b);
    }
}