/**
 * HistoryController
 */

import java.net.URL;
import java.util.ArrayList;
import java.io.IOException;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.event.*;
import java.awt.event.*;

/**
 * HistoryController
 * Controlling backend for the "go back", "go forward" browser features.
 * 
 * This is performed by storing visited pages in an ArrayList and going
 * back and forth using an integer index. A secondary ArrayList stores all
 * visited pages, allowing the firstmentioned to be the main "branch" when
 * navigating back and forth between different pages. The secondary ArrayLists
 * saves all visited pages for easy overview.
 * 
 * @author Anton Lu (antolu@kth.se)
 */
public class HistoryController {
    private int pageIndex = 0;
    private ArrayList<URL> currentHistory;
    private ArrayList<URL> allHistory;
    private BrowserWindow browserWindow;
    private Browser browser;
    private BrowserButton backButton;
    private BrowserButton forwardButton;

    /**
     * Takes other browser components as arguments to allow internal
     * methods to act on them.
     */
    public HistoryController(Browser browser, BrowserWindow browserWindow, 
                BrowserButton backButton, BrowserButton forwardButton) {
        this.browser = browser;
        this.browserWindow = browserWindow;
        this.backButton = backButton;
        this.forwardButton = forwardButton;

        currentHistory = new ArrayList<URL>();
        allHistory = new ArrayList<URL>();
    }

    /**
     * Adds a history entry to tracking.
     */
    public void addHistoryEntry(URL URL) {
        currentHistory.add(URL);
        allHistory.add(URL);
        pageIndex += 1;
    }

    /**
     * Constructs a new history "branch" by previous branch members. 
     * Useful when going back one page
     * and then clicking a new page, changing navigational branch.
     */
    public void newHistoryBranch(URL URL) {
        while (pageIndex < currentHistory.size()) {
            currentHistory.remove(pageIndex);
        }
        addHistoryEntry(URL);
    }

    /**
     * Checks whether a backward history entry exists.
     * 
     * @return `true` if backward history exists, otherfise `false`.
     */
    public boolean hasHistory() {
        if (!currentHistory.isEmpty() && pageIndex > 1) {
            return true;
        }
        return false;
    }

    /**
     * Checks whether a forward history entry exists.
     * 
     * @return `true` if forward history exists, otherfise `false`.
     */
    public boolean hasFuture() {
        if (!currentHistory.isEmpty() && pageIndex < currentHistory.size()) {
            return true;
        }
        return false;
    }

    /**
     * Navigates backwards one page, if backward history exists.
     */
    public void back() {
        if (!hasHistory())
            return;

        URL page = currentHistory.get(pageIndex - 2);  // Index offset by 1!

        /** Display the new URL, popup error if it fails */
        try {
            browserWindow.showPage(page);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(browser, "Loading page failed: \n" 
                    + e.getMessage());
            return;
        }
        pageIndex -= 1;
    }

    /**
     * Navigates forwards one page, if forward history exists.
     */
    public void forward() {
        if (!hasFuture())
            return;

        URL page = currentHistory.get(pageIndex);      // Index offset by 1!

        /** Display the new URL, popup error if it fails */
        try {
            browserWindow.showPage(page);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(browser, "Loading page failed: \n" 
                    + e.getMessage());
            return;
        }
        pageIndex += 1;
    }

    /**
     * Updates the state of forward and backward navigating
     * buttons by checking the current history state.
     */
    public void updateButtons() {
        if (hasHistory()) backButton.enableButton();
        else backButton.disableButton();

        if (hasFuture()) forwardButton.enableButton();
        else forwardButton.disableButton();
    }

    /**
     * Opens up a dialog window displaying previously visited pages, 
     * allowing the user to double-click one entry to jump to the selected
     * page immediately, updating history tracking at the same time.
     */
    public void historySelector() {
        /** Create the list swing component */
        JList<URL> historyList = new JList<URL>(allHistory.toArray(new URL[allHistory.size()]));

        /** Create mouse listener; get the selected list element if 
         * double clicked, and display it.
        */
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                JList theList = (JList) me.getSource();
                if (me.getClickCount() == 2) {
                    int index = theList.locationToIndex(me.getPoint());
                    if (index >= 0) {
                        URL URL = (URL) theList.getModel().getElementAt(index);

                        /** Display the new URL, popup error if it fails */
                        try {
                            browserWindow.showPage(URL);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(browser, "Loading page failed: \n" + ex.getMessage());
                        }

                        /** Update history tracking */
                        newHistoryBranch(URL);
                        updateButtons();
                    }
                }
            }
        };
        historyList.addMouseListener(mouseListener);

        /** Create and display the graphical history selector. */
        new HistoryPopup(this, historyList);
    }
}