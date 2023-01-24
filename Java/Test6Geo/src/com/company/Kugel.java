package com.company;

public class Kugel {
    public double X;
    public double Y;
    public double Z;
    public double r;

    public Kugel(double x, double y, double z, double r) {
        X = x;
        Y = y;
        Z = z;
        this.r = r;
    }

    public boolean IsPunktInKugel(Punkt p) {
        if (Math.sqrt(Math.pow(X - p.X, 2) + Math.pow(Y - p.Y, 2)) <= r) {
            if (Math.sqrt(Math.pow(X - p.X, 2) + Math.pow(Z - p.Z, 2)) <= r) {
                if (Math.sqrt(Math.pow(Z - p.Z, 2) + Math.pow(Y - p.Y, 2)) <= r) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Kugel{" +
                "X=" + X +
                ", Y=" + Y +
                ", Z=" + Z +
                ", r=" + r +
                '}';
    }
}
