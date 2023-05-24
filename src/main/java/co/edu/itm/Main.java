package co.edu.itm;

import co.edu.itm.common.BinaryTree;
import co.edu.itm.common.Utilities;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Utilities utilities = new Utilities();
        BinaryTree tree = new BinaryTree(scanner, utilities);
        tree.treeOption(tree);
    }
}