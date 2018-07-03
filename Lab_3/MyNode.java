
/**
 * MyNode
 */

import java.util.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * A basic DefaultMutableTreeNode subclass able to contain separate XML
 * information like tag, attribute and raw text.
 */
public class MyNode extends DefaultMutableTreeNode {
    private String tagName;
    private String attribute;
    private String description;

    /**
     * Creates a MyNode from the XML tag, attribute and text content.
     */
    public MyNode(String tagName, String attribute, String description) {
        this.tagName = tagName;
        this.attribute = attribute;
        this.description = description;
    }

    /**
     * Returns the XML tag in String form.
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Returns the XML attribute in String form.
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * Returns the XML text in String form.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the XML attribute in String form for the JTree.
     */
    public String toString() {
        return attribute;
    }

    /**
     * Returns the full node description in String form.
     */
    public String getContent() {
        return tagName + ": " + attribute + description;
    }
}