
/** 
 * Browser
*/

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.io.IOException;

import java.net.HttpURLConnection;

import javax.*;

/**
 * The main class for the browser. Creates and displays the GUI, handling button
 * input.
 * 
 * Sample page: http://www.columbia.edu/~fdc/sample.html
 * 
 * @author Anton Lu (antolu@kth.se)
 */
public class Browser extends JFrame implements ActionListener, HyperlinkListener {
    private JScrollPane browserScrollWindow;
    private JPanel toolbar;
    private BrowserWindow browserWindow;
    private BrowserField browserField;
    private BrowserButton goButton;
    private BrowserButton backButton;
    private BrowserButton forwardButton;
    private BrowserButton historyButton;
    private BrowserButton exitButton;

    private HistoryController historyController;

    /**
     * Generic constructor.
     */
    public Browser() {
        createGUI();
    }

    /**
     * Creates and formats the GUI.
     */
    private void createGUI() {
        browserField = new BrowserField();
        browserWindow = new BrowserWindow(browserField);
        browserScrollWindow = new JScrollPane(browserWindow);

        JPanel container = (JPanel) this.getContentPane();

        browserScrollWindow.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.setSize(500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createToolbar();

        container.add(toolbar, BorderLayout.NORTH);
        container.add(browserScrollWindow, BorderLayout.CENTER);

        historyController = new HistoryController(this, browserWindow, backButton, forwardButton);

        this.pack();
        this.setVisible(true);
    }

    /**
     * Creates and formats the toolbar with all buttons
     */
    private void createToolbar() {
        toolbar = new JPanel();

        /** Create and format buttons */
        goButton = new BrowserButton("Go");
        backButton = new BrowserButton("<-");
        forwardButton = new BrowserButton("->");
        historyButton = new BrowserButton("H");
        exitButton = new BrowserButton("X");
        backButton.disableButton();
        forwardButton.disableButton();

        /** Add action listeners to buttons */
        goButton.addActionListener(this);
        backButton.addActionListener(this);
        forwardButton.addActionListener(this);
        historyButton.addActionListener(this);
        exitButton.addActionListener(this);
        browserWindow.addHyperlinkListener(this);

        /** Add buttons to toolbar */
        toolbar.add(backButton);
        toolbar.add(historyButton);
        toolbar.add(forwardButton);
        toolbar.add(browserField);
        toolbar.add(goButton);
        toolbar.add(exitButton);
        toolbar.setLayout(new FlowLayout());
    }

    private void setPage(String URL) {
        URL currentURL;

        /** Create URL object, popup error if it fails */
        try {
            currentURL = new URL(URL);
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(this, "Loading page failed: \n" + ex.getMessage());
            return;
        }

        /** Check if URL exists */
        try {
            HttpURLConnection huc = (HttpURLConnection) currentURL.openConnection();
            huc.setRequestMethod("GET"); // OR huc.setRequestMethod ("HEAD");
            huc.connect();
            int code = huc.getResponseCode();
            if (code > 300) {
                JOptionPane.showMessageDialog(this, "Loading page failed: \n" + "Error code " + code + ".");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Loading page failed: \n" + "Connection could not be established.");
            return;
        }

        /** Display the new URL, popup error if it fails */
        try {
            browserWindow.showPage(currentURL);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Loading page failed: \n" + ex.getMessage());
            return;
        }

        /** Add history entry and update GUI */
        historyController.addHistoryEntry(currentURL);
        historyController.updateButtons();
    }

    /**
     * Processes button actions.
     */
    public void actionPerformed(ActionEvent e) {
        /** Show the page from the adress field URL */
        if (e.getSource() == goButton) {
            setPage(browserField.getText());
        }
        /** Go back one page, if possible */
        else if (e.getSource() == backButton) {
            historyController.back();
        }
        /** Go forward one page, if possible */
        else if (e.getSource() == forwardButton) {
            historyController.forward();
        }
        /** Close the browser */
        else if (e.getSource() == exitButton) {
            dispose();
        }
        /** Display the graphical history entry selector */
        else if (e.getSource() == historyButton) {
            historyController.historySelector();
        }
        historyController.updateButtons();
    }

    /**
     * Sets the page to the new URL when a hyperlink is clicked.
     */
    public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            setPage(e.getURL().toString());
        }
    }

    public static void main(String[] args) {
        new Browser();
    }
}