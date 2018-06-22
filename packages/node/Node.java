package node;

import java.util.*;

public class Node {
    private String tagName;
    private String attribute;
    private String description;
    private List<Node> children = new ArrayList<Node>();
    private Node parent;
    public Boolean hasChildren = false;
    public Boolean hasParent = false;

    public Node(String tagName, String attribute, String description, Node parent) {
        this.tagName = tagName;
        this.attribute = attribute;
        this.description = description;
        this.parent = parent;
        hasParent = true;
    }

    public Node(String tagName, String attribute, String description) {
        this.tagName = tagName;
        this.attribute = attribute;
        this.description = description;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setDescription(String description) {
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

    public void addChildren(Node child) {
        if (!hasChildren) {
            hasChildren = true;
        }
        children.add(child);

    }

    public int getChildrenCount() {
        if (children.isEmpty()) {
            return 0;
        }
        return children.size();
    }

    public Node getChild(int index) {
        return children.get(index);
    }

    public void setParent(Node parent) {
        this.parent = parent;
        hasParent = true;
    }

    public Node getParent() {
        return parent;
    }

    public String toString() {
        // return tagName + ": " + attribute + description;
        return attribute;
    }

    public String getContent() {
        return tagName + ": " + attribute + description;
    }
}