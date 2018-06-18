import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class DirTree extends JFrame implements ActionListener {

  public DirTree() {
    Container c = getContentPane();
    // *** Build the tree and a mouse listener to handle clicks
    root = new DefaultMutableTreeNode("Life");
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

  private void buildTree() {
    File f = new File(katalog);
    String[] list = { "Plants", "Animals", "Mushrooms" };
    for (int i = 0; i < list.length; i++)
      buildTree(list[i], root);
  }

  private void buildTree(String f, DefaultMutableTreeNode parent) {
    DefaultMutableTreeNode child = new DefaultMutableTreeNode(f);
    parent.add(child);

    if (parent.getLevel() > 0) {
      return;
    }
    String list[] = { "Subfamilies", "Subnodes", "Subspecies" };
    for (int i = 0; i < list.length; i++)
      buildTree(list[i], child);
  }

  private void showDetails( TreePath p ) {
  if ( p == null )
  return;
  String f = p.toString();
  JOptionPane.showMessageDialog( this, f +
  "\n ");
  }

  public static void main(String[] args) {
    new DirTree();
  }

  private JCheckBox box;
  private JTree tree;
  private DefaultMutableTreeNode root;
  private DefaultTreeModel treeModel;
  private JPanel controls;
  private static String katalog = ".";
  private static final String closeString = " Close ";
  private static final String showString = " Show Details ";
}
