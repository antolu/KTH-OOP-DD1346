import java.util.*;

import javax.management.modelmbean.XMLParseException;

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

    public void parse() throws XMLParseException {
        try {
            scanner = new Scanner(file);
            currentLine = scanner.nextLine();
            if ("?xml".equals(getTag(currentLine))) {
                currentLine = scanner.nextLine();
            }

            tree = readNode();

            if (!tree.getTagName().equals(endTag(currentLine)) || !currentTag.equals(endTag(currentLine)))
            {
                throw new XMLParseException("Mismatched tags!");
            }

            scanner.close();

        } catch (IOException e) {
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

    private Node readNode() throws XMLParseException {
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
            else if (!"".equals(endTag(currentLine)) && !currentTag.equals(endTag(currentLine)))
            {
                throw new XMLParseException("Mismatched tags!");
            }

            if (!currentTag.equals(getTag(currentLine))) {
                Node newNode = readNode();
                node.addChildren(newNode);
                newNode.setParent(node);

                currentTag = node.getTagName();
            }
            else {
                throw new XMLParseException("Mismatched tags!");
            }
        }
    }

    private String[] getContent(String string) throws XMLParseException {
        string = string.trim();

        if (string.matches("<.+\\s.+=\"(.|\\s)+\">.+")) {
            int tagEnd = string.indexOf(">");

            String tagName = string.substring(1, string.indexOf(" "));
            String attribute = "";
            if (string.matches("<.+?>.+")) {
                attribute = string.substring(string.indexOf(" ") + 7, tagEnd-1);
            }
            String information = string.substring(tagEnd + 1);

            currentTag = tagName;

            return new String[] { tagName, attribute, information };
        } else {
            throw new XMLParseException("Row incomplete! Missing braces, quotation signs or information: " + string);
        }
    }

    private String getTag(String string) throws XMLParseException {
        if (string.startsWith("<") && !string.startsWith("</")) {
            String[] splitLine = string.split("\\s");
            return splitLine[0].substring(1);
        }
        else{
            throw new XMLParseException("Beginning brace missing: " + string);
        }
    }

    private String endTag(String string) throws XMLParseException {
        string = string.trim();
        if (string.startsWith("</")) {
            if (string.matches("</.+>")) {
                return string.substring(2, string.indexOf(">"));
            } else {
                throw new XMLParseException("Missing end brace: " + string);
            }
        }
        return "";
    }

    public Node getTree() {
        return tree;
    }

    // public static void main(String[] args) {
    // XMLParser parser = new XMLParser("Liv.xml");
    // parser.parse();
    // printTree(tree);
    // }
}