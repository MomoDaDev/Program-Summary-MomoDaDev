package sample;

import java.util.List;

public class Helper {
    public List<Person> personList;
    public int changes;

    public Helper(List<Person> personList) {
        this.personList = personList;
        this.changes = 0;
    }
}
