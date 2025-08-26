

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
//This Class is made for stablish the items we need to draw the tree inside the JPanel.
public class Panel extends JPanel implements ActionListener {
	private static final int NODE_SIZE = 20; // Size of each node
    private static final int HORIZONTAL_SPACING = 300; // Horizontal spacing between nodes
    private static final int VERTICAL_SPACING = 50;
    static JFrame frame;
    static JOptionPane message = new JOptionPane();
    // the binary tree
    private BinarySearchTree tree = null;
    // the node location of the tree
    // UI components
    private JTextField inputField;
    private JButton addButton, deleteButton, searchButton;
    private ArrayList<String> allNodes;
    /*When a button is pressed from the menu like "A F S D H" will make a different option
    a= add, f= add from file, s= search, d=Delete, H = Help.*/
    public Panel(BinarySearchTree tree){
        this.tree = tree;
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        inputField = new JTextField(10);
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        searchButton = new JButton("Search");

        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        searchButton.addActionListener(this);

        controlPanel.add(new JLabel("Enter number:"));
        controlPanel.add(inputField);
        controlPanel.add(addButton);
        controlPanel.add(deleteButton);
        controlPanel.add(searchButton);

        add(controlPanel, BorderLayout.NORTH);
    }

    /* event handler for pressing a button from the menu, this will also show
    messageboxes and will make different things,depending of the option selected*/
    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText();
        BinaryNode a;
        if (e.getSource() == addButton) {
            try {
                a = new BinaryNode(Integer.parseInt(input));
                tree.add(a);
                allNodes = tree.fullLevelOrder();
                repaint();
            } catch (NumberFormatException z) {
                JOptionPane.showMessageDialog(frame, "write an integer number");
            }
        } else if (e.getSource() == deleteButton) {
            try {
                a = new BinaryNode(Integer.parseInt(input));
                tree.remove(a.getValue());
                allNodes = tree.fullLevelOrder();
                repaint();
            } catch (NumberFormatException z) {
                JOptionPane.showMessageDialog(frame, "write an integer number");
            }
        } else if (e.getSource() == searchButton) {
            try {
            	a = new BinaryNode(Integer.parseInt(input));
                BinaryNode aux = tree.search(tree.getRoot(), a.getValue());
                if(tree.getRoot().getValue() == a.getValue()) {
                	JOptionPane.showMessageDialog(frame, "The number " + a + " was found");
                }
                else if (aux == null)
                    JOptionPane.showMessageDialog(frame, "The number " + a + " was not found");
                else
                    JOptionPane.showMessageDialog(frame, "The number " + a + " was found");
                repaint();
            } catch (NumberFormatException z) {
                JOptionPane.showMessageDialog(frame, "write an integer number");
            }
        } 
    }
    private void drawTree(Graphics g, int x, int y, int index) {
        if (index >= 31) {
            return; // Stop if we've exceeded the number of nodes or the maximum levels
        }

        // Draw the current node
        g.setColor(Color.BLACK);
        g.drawString(allNodes.get(index), x - 10, y + 5); // Label the node with its value

        // Calculate positions for left and right children
        int childXOffset = (int) (HORIZONTAL_SPACING / Math.pow(2, (int) (Math.log(index + 1) / Math.log(2) + 1)));
        int childY = y + VERTICAL_SPACING;

        // Draw left child branch
        if(index < 15)
        	drawLine(g, x, y, x - childXOffset, childY); // Left branch
        drawTree(g, x - childXOffset, childY, 2 * index + 1); // Draw left child

        // Draw right child branch
        if(index < 15)
        	drawLine(g, x, y, x + childXOffset, childY); // Right branch
        drawTree(g, x + childXOffset, childY, 2 * index + 2); // Draw right child
    }

    // Method to draw a line between parent and child nodes
    private void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1 + NODE_SIZE / 2, x2, y2 - NODE_SIZE / 2); // Draw line from parent to child
    }

    // This method will draw our tree, this receives a graphic called "g"
    public void paint(Graphics g) {
        super.paint(g);
        setBackground(Color.WHITE);
        // if node locations not calculated
//        if (dirty) {
//            calculateLocations();
//            dirty = false;
//        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth() / 2, 30 + 50); // Adjust the translation to move the tree down
        drawTree(g, 20, 20, 0);
    }

    /*At the start of the program will show a messagebox with all the commands that
     can be used to work this program correctly,also set the dimension of the principal
     window */
    public static void main(String[] args) {
        // TODO code application logic here
        BinarySearchTree tree = new BinarySearchTree();
        JFrame f = new JFrame("Binary Tree");
        f.getContentPane().add(new Panel(tree));
        // create and add an event handler for window closing event
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setBounds(50, 50, 700, 700);
        f.show();
    }
}
