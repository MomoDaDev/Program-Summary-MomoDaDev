package com.company;

public class MyElement {
    public MyElement next;
    public MyElement before;
    public Integer x = null;

    public MyElement(Integer x){ this.x = x; }
    public MyElement(){}

    public void addnumber(int number) {
        if(this.x == null){
            this.x = number;
        } else {
            if (number >= this.x && next == null){
                next = new MyElement(number);
                next.before = this;
            } else if (number < this.x && before == null){
                before = new MyElement(number);
                before.next = this;
            } else if (number >= this.x){
                if (number > next.x){
                    next.addnumber(number);
                } else {
                    MyElement mynew = new MyElement(number);
                    mynew.before = this;
                    mynew.next = next;
                    next.before = mynew;
                    next = mynew;
                }
            } else if (number < this.x) {
                if (number < next.x) {
                    before.addnumber(number);
                } else {
                    MyElement mynew = new MyElement(number);
                    mynew.next = this;
                    mynew.before = before;
                    before.next = mynew;
                    before = mynew;
                }
            }
        }
    }
    private void print(){
        System.out.print(this.x);
        if (next != null){
            System.out.print(", ");
            next.print();
        }
    }
    public void printAll(){
        if (before == null){
            print();
        } else {
            before.printAll();
        }
    }
}
