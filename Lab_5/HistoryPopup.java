/**
 * HistoryPopup
 */

import java.net.URL;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JList;

/**
 * HistoryPopup
 * The HistoryPopup class constructs a popup window allowing the user
 * to select a previously visited website to jump there immediately.
 * 
 * @author Anton Lu (antolu@kth.se)
 */
public class HistoryPopup {
    HistoryController historyController;
    JPanel panel;
    JList<URL> historyList;
    JDialog dialog;

    /**
     * Constructs and displays a JDialog with the history entries supplied
     * within the argument `list`.
     */
    public HistoryPopup (HistoryController historyController, JList<URL> list) {
        this.historyController = historyController;
        historyList = list;

        panel = new JPanel();
        panel.add(historyList);

        JOptionPane optionpane = new JOptionPane(panel);
        
        dialog = new JDialog();
        dialog = optionpane.createDialog("History");

        dialog.setVisible(true);
    }
}