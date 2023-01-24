package sample;

public class Family {
    private String father;
    private String mother;
    private String son;
    private String daughter;

    public Family(String father, String mother, String son, String daughter) {
        this.father = father;
        this.mother = mother;
        this.son = son;
        this.daughter = daughter;
    }

    public String getFather() {
        return father;
    }

    public String getMother() {
        return mother;
    }

    public String getSon() {
        return son;
    }

    public String getDaughter() {
        return daughter;
    }

    @Override
    public String toString() {
        return "Family{" +
                "father='" + father + '\'' +
                ", mother='" + mother + '\'' +
                ", son='" + son + '\'' +
                ", daughter='" + daughter + '\'' +
                '}';
    }
}
