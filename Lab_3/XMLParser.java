import java.util.*;
import java.io.*;

import node.*;

public class XMLParser {

    private Node tree;
    private File file;
    private Scanner scanner;
    private String currentLine;
    private String currentTag = "";

    public XMLParser(String filename) {
        this.file = new File(filename);
    }

    public void parse() {
        try {
            scanner = new Scanner(file);
            currentLine = scanner.nextLine();
            if ("?xml".equals(getTag(currentLine))) {
                currentLine = scanner.nextLine();
            }

            tree = readNode();

            scanner.close();

            // printTree(tree);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }

    private void printTree(Node node) {
        System.out.println(node + " has " + node.getChildrenCount() + " children.");
        if (!node.hasChildren) {
            return;
        }

        for (int i = 0; i < node.getChildrenCount(); i++) {
            Node child = node.getChild(i);
            printTree(child);
        }
    }

    private Node readNode() {
        String[] content = getContent(currentLine);
        Node node = new Node(content[0], content[1], content[2]);

        while (true) {
            if (!scanner.hasNext()) {
                return node;
            }

            currentLine = scanner.nextLine();

            if (currentTag.equals(endTag(currentLine))) {
                return node;
            }

            if (!currentTag.equals(getTag(currentLine))) {
                Node newNode = readNode();
                node.addChildren(newNode);
                newNode.setParent(node);

                currentTag = node.getTagName();
            }
        }
    }

    private String[] getContent(String string) {
        string = string.trim();

        if (string.startsWith("<")) {
            int tagEnd = string.indexOf(">");
            String tag = string.substring(0, tagEnd);
            String information = string.substring(tagEnd + 1);

            // Extract information from tag
            String[] splitLine = tag.split("\\p{Blank}");

            String tagName = splitLine[0].substring(1);

            String attribute = splitLine[1].substring(6, splitLine[1].length() - 1);

            currentTag = tagName;

            return new String[] { tagName, attribute, information };
        }
        return new String[] { string };
    }

    private String getTag(String string) {
        if (string.startsWith("<") && !string.startsWith("</")) {
            String[] splitLine = string.split("\\p{Blank}");
            return splitLine[0].substring(1);
        }
        return currentTag;
    }

    private String endTag(String string) {
        string = string.trim();
        if (string.matches("</([A-Za-z0-9]+?)>")) {
            String newString = string.substring(2);
            String returnString = newString.substring(0, newString.indexOf(">"));
            return returnString;
        }
        return "";
    }

    // public static void main(String[] args) {
    // XMLParser parser = new XMLParser("Liv.xml");
    // parser.parse();
    // }

    public Node getTree() {
        return tree;
    }
}