import javax.swing.JApplet;
import mybutton.*;

public class AppletButton extends JApplet {
    public void init() {
        MyButton button = new MyButton();

        add(button);
        pack();
        setVisible(true);
    }
}