package co.edu.itm.common;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinaryTree {
    private Node root;
    private Scanner scanner;

    private Utilities useful;

    private List<Integer> listInOrder;

    private List<Integer> listPreOrder;

    private List<Integer> listPostOrder;

    public BinaryTree(Scanner scanner, Utilities useful) {
        this.scanner = scanner;
        this.useful = useful;
        listPreOrder = new ArrayList<>();
        listInOrder = new ArrayList<>();
        listPostOrder = new ArrayList<>();
        root = null;
    }

    public void treeOption(BinaryTree tree){
        int selectedOption = useful.showOption("Seleccione una opcion", "Menu principal",
                "Arbol defaul", "Arbol dinamico");

        if (selectedOption != JOptionPane.CLOSED_OPTION) {
            switch (selectedOption) {
                case 0 -> defaultTree();
                case 1 -> buildTree(tree);
            }
        }
    }

    public void defaultTree(){
        Node nodeRoot = new Node(8);
        nodeRoot.setLeft(new Node(3));
        nodeRoot.setRight(new Node(10));
        nodeRoot.getLeft().setLeft(new Node(1));
        nodeRoot.getLeft().setRight(new Node(6));
        nodeRoot.getLeft().getRight().setLeft(new Node(4));
        nodeRoot.getLeft().getRight().setRight(new Node(7));
        nodeRoot.getRight().setRight(new Node(14));
        nodeRoot.getRight().getRight().setLeft(new Node(13));

        printOrder(nodeRoot, "Seleccione el recorrido que quiere usar para el arbol");
    }

    public void buildTree(BinaryTree tree){
        int rootValue = useful.showDialogInputInt(Constants.OPTION_ROOT, "Ingrese el valor del nodo raiz:",
                "Nodo raiz", JOptionPane.INFORMATION_MESSAGE);

        if(rootValue != Constants.CLOSE_APP){
            tree.setRoot(new Node(rootValue));

            fillChildNode(tree.getRoot(), "Seleccione una opcion para el nodo raiz: ("+ rootValue +")");

            printOrder(tree.getRoot(), "Seleccione el recorrido que quiere usar para el arbol");
        }
    }

    private void fillChildNode(Node node, String message){
        int option = useful.showOptionDefault(message, "Opcion de hijos");

        if (option != JOptionPane.CLOSED_OPTION){
            switch (option) {
                case Constants.TWO_CHILDREN -> {
                    fillRightNode(node);
                    fillLeftNode(node);
                    fillChildNode(node.getRight(), "Ingrese una de las siguientes opciones para el nodo derecho: ("
                            + node.getRight().getValue() +")");
                    fillChildNode(node.getLeft(), "Ingrese una de las siguientes opciones para el nodo izquierdo: ("
                            + node.getLeft().getValue() +")");
                }
                case Constants.LEFT_SON -> {
                    fillLeftNode(node);
                    fillChildNode(node.getLeft(), "Ingrese una de las siguientes opciones para el nodo izquierdo: ("
                            + node.getLeft().getValue() +")");
                }
                case Constants.RIGHT_SON -> {
                    fillRightNode(node);
                    fillChildNode(node.getRight(), "Ingrese una de las siguientes opciones para el nodo derecho: ("
                            + node.getRight().getValue() +")");
                }
            }
        }else{
            System.exit(0);
        }
    }

    private void fillRightNode(Node node){
        int value = useful.showDialogInputInt(Constants.OPTION_RIGHT_VALUE, "Ingrese el valor del nodo derecho:",
                "Nodo padre ("+node.getValue()+")", JOptionPane.INFORMATION_MESSAGE);
        if(value != Constants.CLOSE_APP){
            node.setRight(new Node(value));
        }else{
            System.exit(0);
        }
    }

    private void fillLeftNode(Node node){
        int value = useful.showDialogInputInt(Constants.OPTION_LEFT_VALUE, "Ingrese el valor del nodo izquierdo:",
                "Nodo padre ("+node.getValue()+")", JOptionPane.INFORMATION_MESSAGE);
        if(value != Constants.CLOSE_APP){
            node.setLeft(new Node(value));
        }else{
            System.exit(0);
        }
    }

    private void routePreOrder(Node node){
        if(node != null){
            listPreOrder.add(node.getValue());
            routePreOrder(node.getLeft());
            routePreOrder(node.getRight());
        }
    }

    private void routeInOrder(Node node){
        if(node != null){
            routeInOrder(node.getLeft());
            listInOrder.add(node.getValue());
            routeInOrder(node.getRight());
        }
    }

    private void routePostOrder(Node node){
        if(node != null){
            routePostOrder(node.getLeft());
            routePostOrder(node.getRight());
            listPostOrder.add(node.getValue());
        }
    }

    private void printOrder(Node nodeRoot, String message){

        int option = useful.showOption(message, "Recorrido",
                "Pre Orden", "In Orden", "Post Orden", "Todos");

        if (option != JOptionPane.CLOSED_OPTION){
            switch (option){
                case Constants.PRE_ORDER -> {
                    routePreOrder(nodeRoot);
                    useful.showDialogMessage("PreOrden: "+ listPreOrder.toString(),
                            "Recorrido", JOptionPane.INFORMATION_MESSAGE);
                    listPreOrder = new ArrayList<>();
                    //Vuelvo mostrar las opciones de impresión solo por curiosidad de la recursividad
                    printOrder(nodeRoot, useful.randomMessagesTour());
                }
                case Constants.IN_ORDER -> {
                    routeInOrder(nodeRoot);
                    useful.showDialogMessage("InOrden: "+ listInOrder.toString(),
                            "Recorrido", JOptionPane.INFORMATION_MESSAGE);
                    listInOrder = new ArrayList<>();
                    //Vuelvo mostrar las opciones de impresión solo por curiosidad de la recursividad
                    printOrder(nodeRoot, useful.randomMessagesTour());
                }
                case Constants.POST_ORDER -> {
                    routePostOrder(nodeRoot);
                    useful.showDialogMessage("PostOrden: "+ listPostOrder.toString(),
                            "Recorrido", JOptionPane.INFORMATION_MESSAGE);
                    listPostOrder = new ArrayList<>();
                    //Vuelvo mostrar las opciones de impresión solo por curiosidad de la recursividad
                    printOrder(nodeRoot, useful.randomMessagesTour());
                }
                case Constants.ALL_ORDER -> {
                    routePreOrder(nodeRoot);
                    routeInOrder(nodeRoot);
                    routePostOrder(nodeRoot);
                    useful.showDialogMessage("PreOrden: "+ listPreOrder.toString()
                                    +"\nInOrden: "+ listInOrder.toString()
                                    +"\nPostOrden: "+ listPostOrder.toString(),
                            "Recorrido", JOptionPane.INFORMATION_MESSAGE);
                    listPreOrder = new ArrayList<>();
                    listInOrder = new ArrayList<>();
                    listPostOrder = new ArrayList<>();
                    //Vuelvo mostrar las opciones de impresión solo por curiosidad de la recursividad
                    printOrder(nodeRoot, useful.randomMessagesTour());
                }
            }
        }else{
            System.exit(0);
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
