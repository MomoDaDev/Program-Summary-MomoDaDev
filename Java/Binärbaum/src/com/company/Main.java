package com.company;

public class Main {
    public static void main(String[] args){
        Node root = new Node(1,new Node(2,
                new Node(4,null,null),
                new Node(5,null,null)),
                new Node(3,null,null));
        root.InOrder();
        System.out.println();
        root.PreOrder();
        System.out.println();
        root.PostOrder();
        System.out.println();
    }
}
