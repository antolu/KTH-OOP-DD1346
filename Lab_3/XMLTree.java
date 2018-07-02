import javax.management.modelmbean.XMLParseException;
import javax.swing.*;
import javax.swing.tree.*;
import javax.xml.parsers.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

import node.*;

public class XMLTree extends JFrame implements ActionListener {

    public XMLTree() {
        Container c = getContentPane();
        // *** Build the tree and a mouse listener to handle clicks

        readFile();

        root = new DefaultMutableTreeNode(inTree);
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
        buildTree();
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
            xmlparser.parse();
        }
        catch (XMLParseException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        inTree = xmlparser.getTree();
    }

    private void buildTree() {
        for (int i = 0; i < inTree.getChildrenCount(); i++)
            buildTree(inTree.getChild(i), root);
    }

    private void buildTree(Node f, DefaultMutableTreeNode parent) {
        DefaultMutableTreeNode child = new DefaultMutableTreeNode(f);
        parent.add(child);
        if (!f.hasChildren) {
            return;
        }

        for (int i = 0; i < f.getChildrenCount(); i++)
            buildTree(f.getChild(i), child);
    }

    private void showDetails(TreePath p) {
        if (p == null)
            return;
        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) p.getLastPathComponent();
        Node node = (Node) treeNode.getUserObject();

        String parentInfo = "";
        if (node.hasParent){
            parentInfo += "men allt som";
            while (node.hasParent) {
                node = node.getParent();
                parentInfo += " är ";
                parentInfo += node.getAttribute();
            }
            parentInfo += ".";
        }

        JOptionPane.showMessageDialog(this, node.getContent() + "\n" + parentInfo);
    }

    public static void main(String[] args) {
        new XMLTree();
    }

    private JCheckBox box;
    private JTree tree;
    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private JPanel controls;
    private static String katalog = ".";
    private static final String closeString = " Close ";
    private static final String showString = " Show Details ";
    private XMLParser xmlparser;
    private Node inTree;
}
