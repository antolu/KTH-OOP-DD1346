import javax.swing.*;
import java.awt.Color;

public class MyFrame extends JFrame {
    Model model;
    View view;

    public MyFrame() {
        model = new Model(10);
        view = new View(model);

        this.setSize(520, 520);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(view);
        this.pack();
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new MyFrame();
    }
}