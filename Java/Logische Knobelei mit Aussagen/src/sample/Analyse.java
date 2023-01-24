package sample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Analyse {
    // things to check
    //Frau
    //Sohn
    //Schwester
    //bin
    //Tochter
    //Mutter
    //seine
    //ist
    //Vater
    //Mann
        /*String sS = statement.split(":")[1]; // sS = secondSegment
        sS = sS.trim();
        String name = sS.split(" ")[1];
        people.add(name);*/
    public static List<String> getNamesFromStatementSegment1(String statement) {
        List<String> people = new ArrayList<>();
        // check first segment

        String fS = statement.split(":")[0]; // fS = firstSegment
        if (fS.split(" ")[0].equalsIgnoreCase("Der")) {
            people.add(fS.split(" ")[3]); // name 1
            people.add(fS.split(" ")[5]); // name 2
        } else {
            if (fS.split(" ")[1].equalsIgnoreCase("zu")) {
                people.add(fS.split(" ")[0]); // name 1
                people.add(fS.split(" ")[2]); // name 2
            } else if (fS.split(" ")[1].equalsIgnoreCase("sagt")) {
                people.add(fS.split(" ")[0]); // name 1
                people.add(fS.split(" ")[3]); // name 2
            } else if (fS.split(" ")[1].equalsIgnoreCase("Tochter") ||
                    fS.split(" ")[1].equalsIgnoreCase("Bruder") ||
                    fS.split(" ")[1].equalsIgnoreCase("Vater")) {
                people.add(fS.split(" ")[0] // name 1
                        .substring(0, fS.split(" ")[0].length() - 1)); // remove the s at the end
                people.add(fS.split(" ")[3]); // name 2
            } else if (fS.split(" ")[1].equalsIgnoreCase("seine")) {
                people.add(fS.split(" ")[0]); // name 1
                people.add(fS.split(" ")[4]); // name 2
            } else if (fS.split(" ")[1].equalsIgnoreCase("Frau")) {
                people.add(fS.split(" ")[0]
                        .substring(0, fS.split(" ")[0].length() - 1)); // name 1
                people.add(fS.split(" ")[4]); // name 2
            }
        }

        if (people.size() == 0) {
            System.out.println("Nothing found in the statement: " + statement);
        }

        return people;
    }

    public static List<String> getNamesFromStatementSegment2(String statement) {
        List<String> people = new ArrayList<>();
        // check second segment
        String sS = statement.split(":")[1]; // sS = secondSegment
        sS = sS.trim();
        if (sS.split(" ")[1].equalsIgnoreCase("ist")) {
            people.add(sS.split(" ")[0]);
        } else if (sS.split(" ")[1].equalsIgnoreCase("seine")) {
            people.add(sS.split(" ")[0]);
            people.add(sS.split(" ")[5]);
        } else if (sS.split(" ")[1].equalsIgnoreCase("Frau")) {
            if (!sS.split(" ")[0].equalsIgnoreCase("Ihre")) {
                people.add(sS.split(" ")[0]
                        .substring(0, sS.split(" ")[0].length() - 1));
            }
            people.add(sS.split(" ")[4]);
        } else if (sS.split(" ")[1].equalsIgnoreCase("Sohn")) {
            if (!sS.split(" ")[0].equalsIgnoreCase("Mein") &&
                    !sS.split(" ")[0].equalsIgnoreCase("Ihr")) {
                people.add(sS.split(" ")[0]
                        .substring(0, sS.split(" ")[0].length() - 1));
            }
            people.add(sS.split(" ")[4]);
        } else if (sS.split(" ")[1].equalsIgnoreCase("Schwester")) {
            people.add(sS.split(" ")[4]);
        } else if (sS.split(" ")[1].equalsIgnoreCase("Tochter")) {
            if (sS.split(" ")[0].equalsIgnoreCase("Unsere")) {
                people.add(sS.split(" ")[2]);
            } else if (!sS.split(" ")[0].equalsIgnoreCase("Meine") &&
                    !sS.split(" ")[0].equalsIgnoreCase("Ihre")) {
                people.add(sS.split(" ")[0]
                        .substring(0, sS.split(" ")[0].length() - 1));
                people.add(sS.split(" ")[4]);
            } else {
                people.add(sS.split(" ")[4]);
            }
        } else if (sS.split(" ")[1].equalsIgnoreCase("Mutter")) {
            if (sS.split(" ")[3].equalsIgnoreCase("nicht")) {
                people.add(sS.split(" ")[4]);
            } else {
                people.add(sS.split(" ")[3]);
            }
        } else if (sS.split(" ")[1].equalsIgnoreCase("Vater")) {
            if (sS.split(" ")[3].equalsIgnoreCase("nicht")) {
                people.add(sS.split(" ")[4]);
            } else {
                people.add(sS.split(" ")[3]);
            }
        } else if (sS.split(" ")[1].equalsIgnoreCase("Mann")) {
            if (!sS.split(" ")[0].equalsIgnoreCase("Mein") &&
                    !sS.split(" ")[0].equalsIgnoreCase("Ihr")) {
                people.add(sS.split(" ")[0]
                        .substring(0, sS.split(" ")[0].length() - 1));
            }
            people.add(sS.split(" ")[4]);
        } else if (sS.split(" ")[1].equalsIgnoreCase("Bruder")) {
            people.add(sS.split(" ")[4]);
        } else if (sS.split(" ")[1].equalsIgnoreCase("bin")) {
            people.add(sS.split(" ")[4]);
        }

        if (people.size() == 0) {
            System.out.println("Nothing found in the statement: " + statement);
        }

        return people;
    }

    public static List<Person> eliminateDependenciesSegment1(List<Person> people, String statement) {
        String fS = statement.split(":")[0]; // fS = firstSegment
        String sS = statement.split(":")[1]; // sS = secondSegment
        sS = sS.trim();

        // check first segment
        if (fS.split(" ")[0].equalsIgnoreCase("Der")) {
            Person p = people.get(people.indexOf(gbn(people,
                    getNamesFromStatementSegment1(statement).get(0)))); // get the person with that very name
            p.role.remove(Main.Role.SON);
            p.role.remove(Main.Role.DAUGHTER);
            p.father = new HashSet<>();
            p.mother = new HashSet<>();
            p.brother = new HashSet<>();
            p.sister = new HashSet<>();
            people.set(people.indexOf(gbn(people,
                    getNamesFromStatementSegment1(statement).get(0))), p);
        } else {
            if (fS.split(" ")[1].equalsIgnoreCase("zu")) {
                ; // can't do much here
            } else if (fS.split(" ")[1].equalsIgnoreCase("sagt")) {
                ; // can't do much here
            } else if (fS.split(" ")[1].equalsIgnoreCase("Tochter")) {
                Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(0)))); // get the person with that very name
                p.role.remove(Main.Role.SON);
                p.role.remove(Main.Role.DAUGHTER);
                p.father = new HashSet<>();
                p.mother = new HashSet<>();
                p.brother = new HashSet<>();
                p.sister = new HashSet<>();
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(0))), p);
            } else if (fS.split(" ")[1].equalsIgnoreCase("Bruder")) {
                Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(0)))); // get the person with that very name
                p.role.remove(Main.Role.SON);
                p.role.remove(Main.Role.FATHER);
                p.role.remove(Main.Role.MOTHER);
                p.man = new HashSet<>();
                p.wife = new HashSet<>();
                p.son = new HashSet<>();
                p.daughter = new HashSet<>();
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(0))), p);
            } else if (fS.split(" ")[1].equalsIgnoreCase("Vater")) {
                Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(0)))); // get the person with that very name
                p.role.remove(Main.Role.FATHER);
                p.role.remove(Main.Role.MOTHER);
                p.man = new HashSet<>();
                p.wife = new HashSet<>();
                p.son = new HashSet<>();
                p.daughter = new HashSet<>();
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(0))), p);
            } else if (fS.split(" ")[1].equalsIgnoreCase("seine")) {
                /*Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(0)))); // get the person with that very name
                p.role.remove(Main.Role.SON);
                p.role.remove(Main.Role.DAUGHTER);
                p.role.remove(Main.Role.MOTHER);
                p.man = new HashSet<>();
                p.brother = new HashSet<>();
                p.sister = new HashSet<>();
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(0))), p);*/
            } else if (fS.split(" ")[1].equalsIgnoreCase("Frau")) {
                ; // TODO CHECK SEGMENT
            }
        }

        return people;
    }

    public static List<Person> eliminateDependenciesSegment2(List<Person> people, String statement) {
        String fS = statement.split(":")[0]; // fS = firstSegment
        String sS = statement.split(":")[1]; // sS = secondSegment
        sS = sS.trim();

        // check second segment
        if (sS.split(" ")[1].equalsIgnoreCase("ist")) {
            Person p = people.get(people.indexOf(gbn(people, sS.split(" ")[0]))); // get the person with that very name
            p.role.remove(Main.Role.SON);
            p.role.remove(Main.Role.DAUGHTER);
            p.father = new HashSet<>();
            p.mother = new HashSet<>();
            p.brother = new HashSet<>();
            p.sister = new HashSet<>();
            people.set(people.indexOf(gbn(people, sS.split(" ")[0])), p);
        } else if (sS.split(" ")[1].equalsIgnoreCase("seine")) {
            Person p = people.get(people.indexOf(gbn(people, sS.split(" ")[0]))); // get the person with that very name
            p.role.remove(Main.Role.SON);
            p.role.remove(Main.Role.DAUGHTER);
            p.role.remove(Main.Role.MOTHER);
            p.father = new HashSet<>();
            p.mother = new HashSet<>();
            p.brother = new HashSet<>();
            p.sister = new HashSet<>();
            p.man = new HashSet<>();
            p.wife.remove(sS.split(" ")[5]);
            people.set(people.indexOf(gbn(people, sS.split(" ")[0])), p);
        } else if (sS.split(" ")[1].equalsIgnoreCase("Frau")) {
            if (sS.split(" ")[0].equalsIgnoreCase("Ihre")) {
                Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(1)))); // get the person with that very name
                p.role.remove(Main.Role.SON);
                p.role.remove(Main.Role.DAUGHTER);
                p.role.remove(Main.Role.MOTHER);
                p.father = new HashSet<>();
                p.mother = new HashSet<>();
                p.brother = new HashSet<>();
                p.sister = new HashSet<>();
                p.man = new HashSet<>();
                p.wife.remove(sS.split(" ")[4]);
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(1))), p);
            } else {
                Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(0)))); // get the person with that very name
                p.role.remove(Main.Role.SON);
                p.role.remove(Main.Role.DAUGHTER);
                p.role.remove(Main.Role.MOTHER);
                p.father = new HashSet<>();
                p.mother = new HashSet<>();
                p.brother = new HashSet<>();
                p.sister = new HashSet<>();
                p.man = new HashSet<>();
                p.wife.remove(sS.split(" ")[4]);
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(0))), p);
            }
        } else if (sS.split(" ")[1].equalsIgnoreCase("Sohn")) {
            if (sS.split(" ")[0].equalsIgnoreCase("Mein")) {
                Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(0)))); // get the person with that very name
                p.role.remove(Main.Role.SON);
                p.role.remove(Main.Role.DAUGHTER);
                p.father = new HashSet<>();
                p.mother = new HashSet<>();
                p.brother = new HashSet<>();
                p.sister = new HashSet<>();
                p.son.remove(sS.split(" ")[4]);
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(0))), p);
            } else if (sS.split(" ")[0].equalsIgnoreCase("Ihr")) {
                Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(1)))); // get the person with that very name
                p.role.remove(Main.Role.SON);
                p.role.remove(Main.Role.DAUGHTER);
                p.father = new HashSet<>();
                p.mother = new HashSet<>();
                p.brother = new HashSet<>();
                p.sister = new HashSet<>();
                p.son.remove(sS.split(" ")[4]);
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(1))), p);
            } else {
                Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment2(statement).get(0)))); // get the person with that very name
                p.role.remove(Main.Role.SON);
                p.role.remove(Main.Role.DAUGHTER);
                p.father = new HashSet<>();
                p.mother = new HashSet<>();
                p.brother = new HashSet<>();
                p.sister = new HashSet<>();
                p.son.remove(sS.split(" ")[4]);
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment2(statement).get(0))), p);
            }
        } else if (sS.split(" ")[1].equalsIgnoreCase("Schwester")) {
            ; // can't do much with this
        } else if (sS.split(" ")[1].equalsIgnoreCase("Tochter")) {
            if (sS.split(" ")[0].equalsIgnoreCase("Unsere")) {
                // both people in the first segment are parents
                Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(0)))); // get the person with that very name
                p.role.remove(Main.Role.SON);
                p.role.remove(Main.Role.DAUGHTER);
                p.father = new HashSet<>();
                p.mother = new HashSet<>();
                p.brother = new HashSet<>();
                p.sister = new HashSet<>();
                p.daughter = new HashSet<>();
                p.daughter.add(getNamesFromStatementSegment2(statement).get(0));
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(0))), p);

                Person p2 = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(1)))); // get the person with that very name
                p2.role.remove(Main.Role.SON);
                p2.role.remove(Main.Role.DAUGHTER);
                p2.father = new HashSet<>();
                p2.mother = new HashSet<>();
                p2.brother = new HashSet<>();
                p2.sister = new HashSet<>();
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(1))), p2);
            } else if (sS.split(" ")[0].equalsIgnoreCase("Meine")) {
                if (fS.split(" ")[1].equalsIgnoreCase("Vater")){
                    Person p = people.get(people.indexOf(gbn(people,
                            getNamesFromStatementSegment1(statement).get(0)))); // get the person with that very name
                    p.role.remove(Main.Role.FATHER);
                    p.role.remove(Main.Role.MOTHER);
                    p.man = new HashSet<>();
                    p.wife = new HashSet<>();
                    p.son = new HashSet<>();
                    p.daughter = new HashSet<>();
                    people.set(people.indexOf(gbn(people,
                            getNamesFromStatementSegment1(statement).get(0))), p);
                } else {
                    Person p = people.get(people.indexOf(gbn(people,
                            getNamesFromStatementSegment1(statement).get(0)))); // get the person with that very name
                    p.role.remove(Main.Role.SON);
                    p.role.remove(Main.Role.DAUGHTER);
                    p.father = new HashSet<>();
                    p.mother = new HashSet<>();
                    p.brother = new HashSet<>();
                    p.sister = new HashSet<>();
                    p.daughter.remove(getNamesFromStatementSegment2(statement).get(0));
                    people.set(people.indexOf(gbn(people,
                            getNamesFromStatementSegment1(statement).get(0))), p);
                }
            } else if (sS.split(" ")[0].equalsIgnoreCase("Ihre")) {
                Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(1)))); // get the person with that very name
                p.role.remove(Main.Role.SON);
                p.role.remove(Main.Role.DAUGHTER);
                p.father = new HashSet<>();
                p.mother = new HashSet<>();
                p.brother = new HashSet<>();
                p.sister = new HashSet<>();
                p.daughter.remove(getNamesFromStatementSegment2(statement).get(0));
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(1))), p);
            } else {
                Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment2(statement).get(0)))); // get the person with that very name
                p.role.remove(Main.Role.SON);
                p.role.remove(Main.Role.DAUGHTER);
                p.father = new HashSet<>();
                p.mother = new HashSet<>();
                p.brother = new HashSet<>();
                p.sister = new HashSet<>();
                p.daughter.remove(getNamesFromStatementSegment2(statement).get(1));
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment2(statement).get(0))), p);
            }
        } else if (sS.split(" ")[1].equalsIgnoreCase("Mutter")) {
            if (sS.split(" ")[3].equalsIgnoreCase("nicht")) {
                Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(1)))); // get the person with that very name
                p.role.remove(Main.Role.FATHER);
                p.role.remove(Main.Role.MOTHER);
                p.man = new HashSet<>();
                p.wife = new HashSet<>();
                p.son = new HashSet<>();
                p.daughter = new HashSet<>();
                p.mother.remove(getNamesFromStatementSegment2(statement).get(0));
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(1))), p);
            } else {
                Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(1)))); // get the person with that very name
                p.role.remove(Main.Role.FATHER);
                p.role.remove(Main.Role.MOTHER);
                p.man = new HashSet<>();
                p.wife = new HashSet<>();
                p.son = new HashSet<>();
                p.daughter = new HashSet<>();
                p.mother = new HashSet<>();
                p.mother.add(getNamesFromStatementSegment2(statement).get(0));
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(1))), p);
            }
        } else if (sS.split(" ")[1].equalsIgnoreCase("Vater")) {
            if (sS.split(" ")[0].equalsIgnoreCase("Mein")) {
                if (sS.split(" ")[3].equalsIgnoreCase("nicht")) {
                    String name = getNamesFromStatementSegment1(statement).get(0);
                    /* for debugging
                    System.out.println("-----------");
                    for (int i = 0; i < people.size(); i++) {
                        System.out.println(people.get(i).name);
                    }
                    System.out.println(people.size());
                    */
                    Person p = people.get(people.indexOf(gbn(people,
                            name))); // get the person with that very name
                    p.role.remove(Main.Role.FATHER);
                    p.role.remove(Main.Role.MOTHER);
                    p.man = new HashSet<>();
                    p.wife = new HashSet<>();
                    p.son = new HashSet<>();
                    p.daughter = new HashSet<>();
                    p.father.remove(getNamesFromStatementSegment2(statement).get(0));
                    people.set(people.indexOf(gbn(people,
                            getNamesFromStatementSegment1(statement).get(0))), p);
                } else { // Mein Vater heißt ...
                    Person p = people.get(people.indexOf(gbn(people,
                            getNamesFromStatementSegment1(statement).get(0)))); // get the person with that very name
                    p.role.remove(Main.Role.FATHER);
                    p.role.remove(Main.Role.MOTHER);
                    p.man = new HashSet<>();
                    p.wife = new HashSet<>();
                    p.son = new HashSet<>();
                    p.daughter = new HashSet<>();
                    p.father = new HashSet<>();
                    p.father.add(getNamesFromStatementSegment2(statement).get(0));
                    people.set(people.indexOf(gbn(people,
                            getNamesFromStatementSegment1(statement).get(0))), p);
                }
            } else { // Dein Vater heißt nicht ...
                Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(1)))); // get the person with that very name
                p.role.remove(Main.Role.FATHER);
                p.role.remove(Main.Role.MOTHER);
                p.man = new HashSet<>();
                p.wife = new HashSet<>();
                p.son = new HashSet<>();
                p.daughter = new HashSet<>();
                p.father.remove(getNamesFromStatementSegment2(statement).get(0));
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(1))), p);
            }
        } else if (sS.split(" ")[1].equalsIgnoreCase("Mann")) {
            if (sS.split(" ")[0].equalsIgnoreCase("Mein")) {
                Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(0)))); // get the person with that very name
                p.role.remove(Main.Role.FATHER);
                p.role.remove(Main.Role.SON);
                p.role.remove(Main.Role.DAUGHTER);
                p.man.remove(getNamesFromStatementSegment2(statement).get(0));
                p.wife = new HashSet<>();
                p.brother = new HashSet<>();
                p.sister = new HashSet<>();
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(0))), p);
            } else if (sS.split(" ")[0].equalsIgnoreCase("Ihr")) {
                Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(1)))); // get the person with that very name
                p.role.remove(Main.Role.FATHER);
                p.role.remove(Main.Role.SON);
                p.role.remove(Main.Role.DAUGHTER);
                p.man.remove(getNamesFromStatementSegment2(statement).get(0));
                p.wife = new HashSet<>();
                p.brother = new HashSet<>();
                p.sister = new HashSet<>();
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment1(statement).get(1))), p);
            } else {
                Person p = people.get(people.indexOf(gbn(people,
                        getNamesFromStatementSegment2(statement).get(0)))); // get the person with that very name
                p.role.remove(Main.Role.FATHER);
                p.role.remove(Main.Role.SON);
                p.role.remove(Main.Role.DAUGHTER);
                p.man.remove(getNamesFromStatementSegment2(statement).get(1));
                p.wife = new HashSet<>();
                p.brother = new HashSet<>();
                p.sister = new HashSet<>();
                people.set(people.indexOf(gbn(people,
                        getNamesFromStatementSegment2(statement).get(0))), p);
            }
        } else if (sS.split(" ")[1].equalsIgnoreCase("Bruder")) {
            Person p = people.get(people.indexOf(gbn(people,
                    getNamesFromStatementSegment1(statement).get(0)))); // get the person with that very name
            p.role.remove(Main.Role.FATHER);
            p.role.remove(Main.Role.SON);
            p.role.remove(Main.Role.MOTHER);
            p.brother.remove(getNamesFromStatementSegment2(statement).get(0));
            p.wife = new HashSet<>();
            p.man = new HashSet<>();
            p.sister = new HashSet<>();
            people.set(people.indexOf(gbn(people,
                    getNamesFromStatementSegment1(statement).get(0))), p);
        } else if (sS.split(" ")[1].equalsIgnoreCase("bin")) {
            ; // can't do much here
        }

        /*
        Person p = people.get(people.indexOf(gbn(people,
                "Ute")));
        System.out.println("Dependencies of " + p.getAmountOfDependencies() + "; Statement: " + statement); */

        return people;
    }

    private static Person gbn(List<Person> people, String name) { // gbn => getbyName
        return people.stream()
                .filter(person -> name.equalsIgnoreCase(person.name))
                .findAny()
                .orElse(null);
    }

    public static Helper lookForSingleDependencies(List<Person> people) {
        Helper h = new Helper(people);

        for (int i = 0; i < people.size(); i++) {
            // Father
            if (people.get(i).father.size() == 1) {
                for (int j = 0; j < people.size(); j++) {
                    if (!people.get(j).name.equals(people.get(i).name)) {
                        if (people.get(j).father.contains(people.get(i).father.iterator().next())) {
                            h.changes++;
                            people.get(j).father.remove(people.get(i).father.iterator().next());
                        }
                    }
                }
            }
            // Mother
            if (people.get(i).mother.size() == 1) {
                for (int j = 0; j < people.size(); j++) {
                    if (!people.get(j).name.equals(people.get(i).name)) {
                        if (people.get(j).mother.contains(people.get(i).mother.iterator().next())) {
                            h.changes++;
                            people.get(j).mother.remove(people.get(i).mother.iterator().next());
                        }
                    }
                }
            }
            // Son
            if (people.get(i).son.size() == 1) {
                for (int j = 0; j < people.size(); j++) {
                    if (!people.get(j).name.equals(people.get(i).name)) {
                        if (people.get(j).son.contains(people.get(i).son.iterator().next())) {
                            h.changes++;
                            people.get(j).son.remove(people.get(i).son.iterator().next());
                        }
                    }
                }
            }
            // Daughter
            if (people.get(i).daughter.size() == 1) {
                for (int j = 0; j < people.size(); j++) {
                    if (!people.get(j).name.equals(people.get(i).name)) {
                        if (people.get(j).daughter.contains(people.get(i).daughter.iterator().next())) {
                            h.changes++;
                            people.get(j).daughter.remove(people.get(i).daughter.iterator().next());
                        }
                    }
                }
            }
            // Man
            if (people.get(i).man.size() == 1) {
                for (int j = 0; j < people.size(); j++) {
                    if (!people.get(j).name.equals(people.get(i).name)) {
                        if (people.get(j).man.contains(people.get(i).man.iterator().next())) {
                            h.changes++;
                            people.get(j).man.remove(people.get(i).man.iterator().next());
                        }
                    }
                }
                Person p = people.get(people.indexOf(gbn(people, people.get(i).man.iterator().next())));
            }
            // Wife
            if (people.get(i).wife.size() == 1) {
                for (int j = 0; j < people.size(); j++) {
                    if (!people.get(j).name.equals(people.get(i).name)) {
                        if (people.get(j).wife.contains(people.get(i).wife.iterator().next())) {
                            h.changes++;
                            people.get(j).wife.remove(people.get(i).wife.iterator().next());
                        }
                    }
                }
            }
            // Brother
            if (people.get(i).brother.size() == 1) {
                for (int j = 0; j < people.size(); j++) {
                    if (!people.get(j).name.equals(people.get(i).name)) {
                        if (people.get(j).brother.contains(people.get(i).brother.iterator().next())) {
                            h.changes++;
                            people.get(j).brother.remove(people.get(i).brother.iterator().next());
                        }
                    }
                }
            }
            // Sister
            if (people.get(i).sister.size() == 1) {
                for (int j = 0; j < people.size(); j++) {
                    if (!people.get(j).name.equals(people.get(i).name)) {
                        if (people.get(j).sister.contains(people.get(i).sister.iterator().next())) {
                            h.changes++;
                            people.get(j).sister.remove(people.get(i).sister.iterator().next());
                        }
                    }
                }
            }
        }

        return h;
    }
    public static List<Person> eliminateDependenciesByGender(List<Person> people) {
        for (int i = 0; i < people.size(); i++) {
            Main.Gender gender = GetGenderAPI.GetGenderByName(people.get(i).name);

            if (gender == Main.Gender.MALE) {
                people.get(i).brother = new HashSet<>();
                people.get(i).man = new HashSet<>();
            } else if (gender == Main.Gender.FEMALE) {
                people.get(i).sister = new HashSet<>();
                people.get(i).wife = new HashSet<>();
            }
        }

        return people;
    }
}
