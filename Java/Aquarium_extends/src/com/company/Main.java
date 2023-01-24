package com.company;

public class Main {

    public static void main(String[] args) {
        Aquarium aquarium = new Aquarium();

        //add fish
        {
            //ordinary fish:
            aquarium.add_fish(new Fish(), true);
            //ordinary fish:
            aquarium.add_fish(new Fish(), true);
            //Black Jewfish
            aquarium.add_fish(new Black_Jewfish(), true);
            //Sierra Mackerel
            aquarium.add_fish(new Sierra_Mackerel(), true);
            //Swordfish
            aquarium.add_fish(new Swordfish(), true);
            //Flounder
            aquarium.add_fish(new Flounder(), true);
            //Lionfish
            aquarium.add_fish(new Lionfish(), true);
        }

        System.out.println("-------------------------------------------");

        aquarium.get_every_fish(true);

        System.out.println("-------------------------------------------");

        //remove second fish and add with Sierra Mackerel
        aquarium.remove_fish(1, true);
        aquarium.add_fish(new Sierra_Mackerel());
        aquarium.get_fish(6, true);
    }
}
