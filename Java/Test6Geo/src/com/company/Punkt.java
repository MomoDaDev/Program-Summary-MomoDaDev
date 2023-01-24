package com.company;

public class Punkt {
    public double X;
    public double Y;
    public double Z;

    public Punkt(double x, double y, double z) {
        X = x;
        Y = y;
        Z = z;
    }

    @Override
    public String toString() {
        return "Punkt{" +
                "X=" + X +
                ", Y=" + Y +
                ", Z=" + Z +
                '}';
    }
}
