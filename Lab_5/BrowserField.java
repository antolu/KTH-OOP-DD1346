/** 
 * BrowserField
*/

import javax.swing.*;

import java.awt.Dimension;
import java.net.URL;

/**
 * A simple subclass of JTextField. Provides the adress field for the browser.
 * 
 * @author Anton Lu (antolu@kth.se)
 */
public class BrowserField extends JTextField {
    /** 
     * Generic constructor setting the dimension.
    */
    public BrowserField() {
        setPreferredSize(new Dimension(460, 25));
    }
}