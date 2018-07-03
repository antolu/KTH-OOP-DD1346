
/**
 * XMLTree
 */

import javax.swing.*;
import javax.swing.tree.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.management.modelmbean.XMLParseException;

public class XMLTree extends JFrame implements ActionListener {

    private static final String closeString = " Close ";
    private static final String showString = " Show Details ";

    private JCheckBox box;
    private JTree tree;
    private MyNode root;
    private JPanel controls;
    private XMLParser xmlparser;

    /**
     * Creates a graphical visualization of the XML file "Liv.xml".
     */
    public XMLTree() {
        Container container = getContentPane();

        /* Read and parse the XML file */
        readFile();

        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);

        /* Add ability to show details for selected node in the GUI */
        MouseListener ml = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (box.isSelected())
                    showDetails(tree.getPathForLocation(e.getX(), e.getY()));
            }
        };
        tree.addMouseListener(ml);

        /* Create the GUI and show it */
        createGUI();
        container.add(controls, BorderLayout.NORTH);
        container.add(tree, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * ActionPerformed for listening to the "close" button.
     */
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals(closeString))
            dispose();
    }

    /**
     * Creates an XMLParser object and parses the XML file.
     */
    private void readFile() {
        xmlparser = new XMLParser("Liv.xml");
        try {
            root = xmlparser.parse();
        } catch (XMLParseException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Creates the GUI for the XML tree.
     */
    private void createGUI() {
        controls = new JPanel();
        box = new JCheckBox(showString);
        tree.setFont(new Font("Dialog", Font.BOLD, 12));

        controls.add(box);
        addButton(closeString);
        controls.setBackground(Color.lightGray);
        controls.setLayout(new FlowLayout());

        setSize(400, 400);
    }

    /**
     * Adds a button to the control bar with label `buttonLabel`.
     */
    private void addButton(String buttonLabel) {
        JButton button = new JButton(buttonLabel);
        button.setFont(new Font("Dialog", Font.BOLD, 12));
        button.addActionListener(this);
        controls.add(button);
    }

    /**
     * Shows details of the node located at `treePath`.
     */
    private void showDetails(TreePath treePath) {
        if (treePath == null)
            return;
        MyNode node = (MyNode) treePath.getLastPathComponent();

        String nodeInfo = node.getContent();
        String parentInfo = "";
        if (!node.isRoot()) {
            parentInfo += "men allt som är " + node.getAttribute();

            /* Iterate until there the root of the tree */
            while (!node.isRoot()) {
                node = (MyNode) node.getParent();
                parentInfo += " är ";
                parentInfo += node.getAttribute();
            }
            parentInfo += ".";
        }

        /* Display node information in a JOptionPane */
        JOptionPane.showMessageDialog(this, nodeInfo + "\n" + parentInfo);
    }

    public static void main(String[] args) {
        new XMLTree();
    }
}
