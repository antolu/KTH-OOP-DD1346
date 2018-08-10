/**
 * BrowserButton
 */

import java.awt.Dimension;
import javax.swing.*;

/**
 * Simple subclass of JButton. Provides necessary features to the
 * buttons required by the assignment, such as enabling and
 * disabling the button.
 * 
 * @author Anton Lu (antolu@kth.se)
 */
public class BrowserButton extends JButton {

    /**
     * Creates a button with the labelled with the `label` argument.
     */
    public BrowserButton(String label) {
        this.setText(label);
    }

    /**
     * Enables the button.
     */
    public void enableButton() {
        setEnabled(true);
    }

    /** 
     * Disables the button.
     */
    public void disableButton() {
        setEnabled(false);
    }
}