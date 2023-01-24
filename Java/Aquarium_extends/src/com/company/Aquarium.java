package com.company;
import java.util.*;

public class Aquarium {
    private List<Fish> fish = new ArrayList<Fish>();

    //add a fish to the arraylist
    public void add_fish(Fish f, boolean print_notification){
        fish.add(f);
        if (print_notification) {
            System.out.println("Fish added:");
            System.out.println(f.toString());
        }
    }
    public void add_fish(Fish f){
        fish.add(f);
    }

    //get specific element from arraylist
    public Fish get_fish(int index, boolean print_notification){
        Fish f = fish.get(index);
        if(print_notification){
            System.out.println("Retrieved fish:");
            System.out.println(f.toString());
        }
        return f;
    }
    public Fish get_fish(int index){
        return fish.get(index);
    }

    //remove element from arraylist
    public void remove_fish(int index, boolean print_notification){
        if(print_notification){
            System.out.println("fish removed:");
            System.out.println(fish.get(index).toString());
        }
        fish.remove(index);
    }
    public void remove_fish(int index){
        fish.remove(index);
    }

    //retrieve every element from arraylist
    public List<Fish> get_every_fish(boolean print_notification){
        System.out.println("Retrieved every fish:");
        for (int i = 0; i < fish.size(); i++) {
            System.out.println(fish.get(i).toString());
        }
        return fish;
    }
    public List<Fish> get_every_fish(){
        return fish;
    }
}
