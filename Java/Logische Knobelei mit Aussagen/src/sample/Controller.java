package sample;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // read file
        List<String> statements = ReadFile.readFile("210318LogischeKnobeleiAussagen.csv", "././");

        // find names
        Set<String> names = new HashSet<>();
        for (int i = 0; i < statements.size(); i++) {
            System.out.println(statements.get(i));
            names.addAll(Analyse.getNamesFromStatementSegment1(statements.get(i)));
            names.addAll(Analyse.getNamesFromStatementSegment2(statements.get(i)));
        }

        // print names
        for(String name : names){
            System.out.println(name);
        }
        System.out.println("Number of names: " + names.size()); // print number of names

        // create the people with dependencies
        List<Person> people = new LinkedList<>();
        for(String name : names){
            people.add(new Person(name, names));
        }

        // list all people
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i).name + " has " + people.get(i).getAmountOfDependencies() + " dependencies");
        }
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

        // eliminate dependencies
        System.out.println("Number of dependencies(1):");
        for (int i = 0; i < statements.size(); i++) {
            people = Analyse.eliminateDependenciesSegment1(people, statements.get(i));
        }
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i).name + " has " + people.get(i).getAmountOfDependencies() + " dependencies");
        }
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

        // eliminate dependencies 2
        System.out.println("Number of dependencies(1):");
        for (int i = 0; i < statements.size(); i++) {
            people = Analyse.eliminateDependenciesSegment2(people, statements.get(i));
        }
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i).name + " has " + people.get(i).getAmountOfDependencies() + " dependencies");
        }

        // check the gender of the names
        //people = Analyse.eliminateDependenciesByGender(people);

        // loop through every person and check if there is information that is 100 % true
        System.out.println("Looking for single dependencies");
        Helper h = new Helper(people);
        h.changes = -1;

        while (h.changes != 0){
            h = Analyse.lookForSingleDependencies(people);
            people = h.personList;
            System.out.println("Changes: " + h.changes);
        }
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i).name + " has " + people.get(i).getAmountOfDependencies() + " dependencies");
        }
        // to eliminate even more dependencies from other people
    }
}
