
/**
 * XMLParser
 */

import java.util.*;
import java.io.*;
import javax.management.modelmbean.XMLParseException;

/**
 * An own-written class to parse a basic XML file, where the XML tags only
 * contains the attribute "name", and every closing tag is located on a separate
 * row.
 */
public class XMLParser {

    private MyNode tree;
    private File file;
    private Scanner scanner;
    private String currentLine;
    private String currentTag = "";

    /**
     * Creates an XMLParser object from the given file
     */
    public XMLParser(String filename) {
        this.file = new File(filename);
    }

    /**
     * Parses the file and returns the root of the tree
     */
    public MyNode parse() throws XMLParseException {
        try {
            scanner = new Scanner(file);
            currentLine = scanner.nextLine();
            if ("?xml".equals(getTag(currentLine))) {
                currentLine = scanner.nextLine();
            }

            tree = readNode();

            if (!tree.getTagName().equals(getEndTag(currentLine)) 
                    || !currentTag.equals(getEndTag(currentLine))) {
                throw new XMLParseException("Mismatched tags!");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tree;
    }

    /**
     * Prints the generated tree using a BFS pattern. For debugging purposes.
     */
    private void printTree(MyNode node) {
        System.out.println(node + " has " + node.getChildCount() + " children.");
        if (node.isLeaf()) {
            return;
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            MyNode child = (MyNode) node.getChildAt(i);
            printTree(child);
        }
    }

    /**
     * The actually parsing method. Reads the XML file line-by-line and creates
     * nodes recursively. Throws XMLParseException if the XML file is incorrectly
     * formatted.
     */
    private MyNode readNode() throws XMLParseException {
        String[] content = getContent(currentLine);
        MyNode node = new MyNode(content[0], content[1], content[2]);

        while (true) {
            /* If the end of the file is reached, stop parsing */
            if (!scanner.hasNext()) {
                return node;
            }

            currentLine = scanner.nextLine();

            /* If a closing tag is found, return the node */
            if (currentTag.equals(getEndTag(currentLine))) {
                return node;
            } else if (!"".equals(getEndTag(currentLine)) 
                && !currentTag.equals(getEndTag(currentLine))) {
                throw new XMLParseException("Mismatched tags!");
            }

            /* If a new opening tag is found, create a new node and append */
            if (!currentTag.equals(getTag(currentLine))) {
                MyNode newNode = readNode();
                node.add(newNode);
                // newNode.setParent(node);

                currentTag = node.getTagName(); // Update tag tracking
            } else {
                throw new XMLParseException("Mismatched tags!");
            }
        }
    }

    /**
     * Internal function. Reads the content of a line and returns them in a String
     * array.
     */
    private String[] getContent(String string) throws XMLParseException {
        string = string.trim();

        /* Only parse the line if it matches the required format */
        if (string.matches("<.+\\s.+=\"(.|\\s)+\">.+")) {
            int tagEnd = string.indexOf(">");

            /* Retrieves data from the line */
            String tagName = string.substring(1, string.indexOf(" "));
            String attribute = "";
            if (string.matches("<.+?>.+")) {
                attribute = string.substring(string.indexOf(" ") + 7, tagEnd - 1);
            }
            String information = string.substring(tagEnd + 1);

            currentTag = tagName; // Update tag tracking

            return new String[] { tagName, attribute, information };
        } else {
            throw new XMLParseException("Row incomplete! Missing braces,"
                    + " quotation signs or information: " + string);
        }
    }

    /**
     * Checks the XML tag of the current line.
     */
    private String getTag(String string) throws XMLParseException {
        if (string.startsWith("<") && !string.startsWith("</")) {
            String[] splitLine = string.split("\\s");
            return splitLine[0].substring(1);
        } else {
            throw new XMLParseException("Beginning brace missing: " + string);
        }
    }

    /**
     * Checks the current line for closing tags. Returns empty string if a closing
     * tag could not be found, otherwise returns the tag in String format.
     */
    private String getEndTag(String string) throws XMLParseException {
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
}