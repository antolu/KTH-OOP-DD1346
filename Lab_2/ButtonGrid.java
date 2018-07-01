import java.util.*;

import javax.swing.*;

import java.awt.Color;
import java.awt.GridLayout;

import mybutton.*;

public class ButtonGrid extends JPanel {

    private MyButton[][] buttonArray;
    private String[] textFields = { "Push me!", "Push me again!", "Poke me", "Don't you dare!", "Oh no...",
            "Hey it's me!", "Random stuff", "Some text", "Some other text", "Hello" };

    public ButtonGrid(int numberOfButtons) {
        int gridWidth = (int) Math.ceil(Math.sqrt((double) numberOfButtons));
        System.out.println(gridWidth);

        this.setLayout(new GridLayout(gridWidth, gridWidth));
        buttonArray = new MyButton[gridWidth][gridWidth];

        for (int i = 0; i < gridWidth; i++) {
            for (int k = 0; k < gridWidth && i * gridWidth + k < numberOfButtons; k++) {
                buttonArray[i][k] = new MyButton(randomColor(), randomColor(), 
                                                 randomButtonLabel(), randomButtonLabel());
                this.add(buttonArray[i][k]);
            }
        }
    }

    private String randomButtonLabel() {
        int randIndex = (int) Math.floor(10 * Math.random());
        return textFields[randIndex];
    }

    private Color randomColor() {
        Random rand = new Random();

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        return new Color(r, g, b);
    }
}