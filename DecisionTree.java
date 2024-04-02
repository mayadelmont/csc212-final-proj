import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 *  Implements a binary decision tree
 *
 *  @author <Rachel Yang>
 *  @version Fall 22 (from Assignment 8)
 *
 */
public class DecisionTree extends BinaryTree<String> {
  /** leaf constructor */
  public DecisionTree(String s) {
    super(s);
  }

  /** branch constructor */
  public DecisionTree(String s, BinaryTree<String> left, BinaryTree<String> right) {
    super(s);
    this.setLeft(left);
    this.setRight(right);
  }

  /** deep copy constructor */
  public DecisionTree(DecisionTree tree) {
    super(tree.getData());
    this.setLeft(tree.getLeft());
    this.setRight(tree.getRight());
  }
  
  /** @override */
  public void setLeft(BinaryTree<String> left) {
    if ((left==null)||(left instanceof DecisionTree)) {
      super.setLeft(left);
    } else {
      throw new UnsupportedOperationException();
    }
  }

  /** @override */
  public DecisionTree getLeft() {
    return (DecisionTree)super.getLeft();
  }

  /** @override */
  public void setRight(BinaryTree<String> right) {
    if ((right==null)||(right instanceof DecisionTree)) {
      super.setRight(right);
    } else {
      throw new UnsupportedOperationException();
    }
  }

  /** @override */
  public DecisionTree getRight() {
    return (DecisionTree)super.getRight();
  }

  /** 
  * takes in string of Ys and Ns and returns the 
  * corresponding node from the current node
  * @param input String of Ys and Ns
  * @return DecisionTree node reached after path
  */
  public DecisionTree followPath(String input) {
    DecisionTree node = this;
    for (int i=0; i<input.length(); i++) {
      if (node.isLeaf()) {
        throw new UnsupportedOperationException();
      }
      if (input.charAt(i) == 'Y') {
        node = node.getLeft();
      } else if (input.charAt(i) == 'N') {
        node = node.getRight();
      }
    }
    return node;
  }

  /** 
  * turns a tree of the node object and its 
  * descendants into a file with the path to each 
  * node from the node object and the node data, in
  * breadth-first order
  * @param filename - name of the file to be written 
  *   onto/over
  * @return no return values
  */
  public void fileWrite(String filename) {
    try {
      PrintWriter output = new PrintWriter(new FileWriter(filename));
      ArrayDeque<DecisionTree> nodes = new ArrayDeque<DecisionTree>();
      ArrayDeque<String> paths = new ArrayDeque<String>();
      DecisionTree node = this;
      output.println("  "+getData());
      nodes.add(getLeft());
      nodes.add(getRight());
      paths.add("Y");
      paths.add("N");
      while (nodes.size() > 0) {
        node = nodes.remove();
        String path = paths.remove();
        output.println(path+" "+node.getData());
        if (!node.isLeaf()) {
          nodes.add(node.getLeft());
          nodes.add(node.getRight());
          paths.add(path+"Y");
          paths.add(path+"N");
        }
      }
      output.close();
    } catch (IOException i) {
      System.out.println("File does not exist.");
    }
  }

  /** 
  * Returns a DecisionTree created by reading in a file.
  * @param filename name of file containing tree
  * @return DecisionTree tree created from file
  */
  public static DecisionTree input(String filename) {
    Scanner in = new Scanner(System.in);
    try {
      in = new Scanner(new File(filename));
    } catch (FileNotFoundException e) {
      System.err.println("Cannot locate file.");
      System.exit(-1);
    }
    String root = in.nextLine();
    DecisionTree tree = new DecisionTree(root.stripLeading());
    while (in.hasNextLine()) {
      String line = in.nextLine();
      int spaceIndex = line.indexOf(" ");
      String path = line.substring(0, spaceIndex-1);
      String data = line.substring(spaceIndex+1, line.length());
      DecisionTree node = tree;
      node = node.followPath(path);
      if (node == null) {
        throw new NullPointerException();
      }
      if (line.charAt(spaceIndex-1) == 'Y') {
        node.setLeft(new DecisionTree(data));
      } else {
        node.setRight(new DecisionTree(data));
      }
    }
    in.close();
    return tree;
  }
}