package com.company;

public class Node {
    public int Value;
    public Node Left;
    public Node Right;

    public Node(int value, Node left, Node right){
        Value=value;
        Left=left;
        Right=right;
    }
    public void InOrder(){
        if (Left!=null)
            Left.InOrder();
        System.out.print(Value);
        if (Right!=null)
            Right.InOrder();
    }
    public void PreOrder(){
        System.out.print(Value);
        if (Left!=null)
            Left.PreOrder();
        if (Right!=null)
            Right.PreOrder();
    }
    public void PostOrder(){
        if (Left!=null)
            Left.PostOrder();
        if (Right!=null)
            Right.PostOrder();
        System.out.print(Value);
    }
}
