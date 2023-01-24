package com.company;

public class Data {
    public int id;
    public int x;
    public int y;
    public int z;
    public double brightness;
    public double result;

    public Data (int id, int x, int y, int z, double brightness){
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.brightness = brightness;
        result = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }
    @Override
    public String toString() {
        return (id + ";" + x + ";" + y + ";" + z + ";" + brightness + ";" + result);
    }
}
