package com.company;

import java.util.ArrayList;
import java.util.List;

public class Temperature {
    private List<Integer> temperatures;


    public Temperature() {
        this.temperatures = new ArrayList<>();
    }

    public void addTemperature(int temp){ // -273 to 5526
        if (temp >= -273 && temp <= 5526) { temperatures.add(temp); }
    }

    public int getResult(){
        if (temperatures.size() == 0) { return 0; }
        int closestTo0 = Integer.MAX_VALUE; // MAX_VALUE because its the furthest away from 0
        for (int temp : temperatures) {
            if (Math.abs(temp) <= Math.abs(closestTo0)){
                if (Math.abs(temp) == Math.abs(closestTo0)){
                    if (temp > closestTo0) {
                        closestTo0 = temp;
                    }
                }
                else {
                    closestTo0 = temp;
                }
            }
        }
        return closestTo0;
    }

    public void removeAllEntries(){
        this.temperatures = new ArrayList<>();
    }

    public int sizeOftemperatures(){
        return temperatures.size();
    }
}
