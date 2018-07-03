import javax.management.modelmbean.XMLParseException;
import javax.swing.*;
import javax.swing.tree.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class XMLTree extends JFrame implements ActionListener {

    public XMLTree() {
        Container c = getContentPane();
        // *** Build the tree and a mouse listener to handle clicks

        readFile();

        // root = new DefaultMutableTreeNode(inTree);
        root = inTree;
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);

        MouseListener ml = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (box.isSelected())
                    showDetails(tree.getPathForLocation(e.getX(), e.getY()));
            }
        };
        tree.addMouseListener(ml);
        // *** build the tree by adding the nodes
        // buildTree();
        // *** panel the JFrame to hold controls and the tree
        controls = new JPanel();
        box = new JCheckBox(showString);
        init(); // ** set colors, fonts, etc. and add buttons
        c.add(controls, BorderLayout.NORTH);
        c.add(tree, BorderLayout.CENTER);
        setVisible(true); // ** display the framed window
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals(closeString))
            dispose();
    }

    private void init() {
        tree.setFont(new Font("Dialog", Font.BOLD, 12));
        controls.add(box);
        addButton(closeString);
        controls.setBackground(Color.lightGray);
        controls.setLayout(new FlowLayout());
        setSize(400, 400);
    }

    private void addButton(String n) {
        JButton b = new JButton(n);
        b.setFont(new Font("Dialog", Font.BOLD, 12));
        b.addActionListener(this);
        controls.add(b);
    }

    private void readFile() {
        xmlparser = new XMLParser("Liv.xml");
        try{
            inTree = xmlparser.parse();
        }
        catch (XMLParseException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private void showDetails(TreePath p) {
        if (p == null)
            return;
        MyNode node = (MyNode) p.getLastPathComponent();

        String nodeInfo = node.getContent();
        String parentInfo = "";
        if (!node.isRoot()){
            parentInfo += "men allt som är " + node.getAttribute();
            while (!node.isRoot()) {
                node = (MyNode) node.getParent();
                System.out.println(parentInfo);
                parentInfo += " är ";
                parentInfo += node.getAttribute();
            }
            parentInfo += ".";
        }

        JOptionPane.showMessageDialog(this, nodeInfo + "\n" + parentInfo);
    }

    public static void main(String[] args) {
        new XMLTree();
    }

    private JCheckBox box;
    private JTree tree;
    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private JPanel controls;
    private static final String closeString = " Close ";
    private static final String showString = " Show Details ";
    private XMLParser xmlparser;
    private MyNode inTree;
}
