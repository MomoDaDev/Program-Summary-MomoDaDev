package sample;

public class Tree {
    public int number;
    public Tree left;
    public Tree right;

    public Tree(int number, Tree left, Tree right){
        this.number = number;
        this.left = left;
        this.right = right;
    }
}
