package com.company;

public class Fish {
    public String make_noice(){
        return "Blub, I'm an ordinary fish!";
    }
    @Override
    public String toString(){
        //returns the Name of the class
        return (this.getClass().getSimpleName()) + ": " + this.make_noice();
    }
}
