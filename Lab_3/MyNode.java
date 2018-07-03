import java.util.*;

import javax.swing.tree.DefaultMutableTreeNode;

public class MyNode extends DefaultMutableTreeNode {
    private String tagName;
    private String attribute;
    private String description;

    public MyNode(String tagName, String attribute, String description) {
        this.tagName = tagName;
        this.attribute = attribute;
        this.description = description;
    }

    public String getTagName() {
        return tagName;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        // return tagName + ": " + attribute + description;
        return attribute;
    }

    public String getContent() {
        return tagName + ": " + attribute + description;
    }
}