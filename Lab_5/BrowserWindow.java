/**
 * BrowserWindow
 */

import javax.swing.JEditorPane;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.*;
import java.io.IOException;

/**
 * A subclass of JEditorPane with pre-set settings and methods to interact 
 * with it.
 * 
 * @author Anton Lu (antolu@kth.se)
 */
public class BrowserWindow extends JEditorPane {
    private BrowserField browserField;
    private URL currentURL = null;
    private URL lastURL = null;

    /**
     * Creates a standard BrowserWindow object with pre-set dimension, 
     * and taking the browser adress field as argument for use by other
     * methods.
     */
    public BrowserWindow(BrowserField browserField) {
        this.browserField = browserField;
        this.setEditable(false);
        this.setPreferredSize(new Dimension(500, 400));
    }

    /**
     * Displays the given URL in the editorpane, and sets the adress field text
     * to match the new page.
     */
    public void showPage(URL URL) throws IOException {
        lastURL = currentURL;
        currentURL = URL;
        browserField.setText(URL.toString());
        setPage(URL);
    }

    /**
     * @return the lastURL
     */
    public URL getLastURL() {
        return lastURL;
    }

    /**
     * @return the currentURL
     */
    public URL getCurrentURL() {
        return currentURL;
    }
}