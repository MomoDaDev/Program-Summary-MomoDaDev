package sample;

import java.util.*;

public class Person {
    public String name;
    public List<Main.Role> role;

    public Set<String> father;
    public Set<String> mother;
    public Set<String> son;
    public Set<String> daughter;

    public Set<String> man;
    public Set<String> wife;
    public Set<String> brother;
    public Set<String> sister;

    public Person(String name, Set<String> names) {
        this.name = name;
        role = new LinkedList<>();
        role.add(Main.Role.DAUGHTER);
        role.add(Main.Role.FATHER);
        role.add(Main.Role.SON);
        role.add(Main.Role.MOTHER);

        father = new HashSet<>();
        father.addAll(names);
        mother = new HashSet<>();
        mother.addAll(names);
        son = new HashSet<>();
        son.addAll(names);
        daughter = new HashSet<>();
        daughter.addAll(names);
        man = new HashSet<>();
        man.addAll(names);
        wife = new HashSet<>();
        wife.addAll(names);
        brother = new HashSet<>();
        brother.addAll(names);
        sister = new HashSet<>();
        sister.addAll(names);
    }


    public int getAmountOfDependencies() {
        int dependencies = 0;
        dependencies += role.size();
        dependencies += father.size() + mother.size() + son.size() + daughter.size();
        dependencies += man.size() + wife.size() + brother.size() + sister.size();
        return dependencies;
    }
}
