package sample;

public class Laden {
    public int id;
    public String name;
    public double X;
    public double Y;

    public Laden(int id, String name, double x, double y) {
        this.id = id;
        this.name = name;
        X = x;
        Y = y;
    }

    @Override
    public String toString() {
        return "Laden{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", X=" + X +
                ", Y=" + Y +
                '}';
    }
}
