package factorytest;

import factory.Human;

/**
 * Factory
 */
public class Factory {

    public static void main(String[] args)  {
        try {
            Human human1 = Human.create("Anton", "F15", 22);
            Human human2 = Human.create("Elaine", "D18", 20);

            System.err.println(human1);
            System.err.println(human2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        // Physicist p = new Physicist(10, "name", 10);
        // ComputerScientist f = new ComputerScientist(10, "name", 10);

    }
}