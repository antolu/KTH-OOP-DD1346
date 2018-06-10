public class Main {
    public static void main(String[] args) {
        Human Pelle = new Human(25, "Pelle");;
        // System.out.println(Pelle.toString());
        System.out.println("E1-E2");
        System.out.println(Pelle);

        /*******************************/

        System.out.println("\nE3");

        int numberOfHumans = 15;

        Human Humans[] = new Human[numberOfHumans];

        for (int i = 0; i < numberOfHumans; i++)
        {
            Humans[i] = new Human();
        }

        for (int i = 0; i < numberOfHumans; i++)
        {
            System.out.println(Humans[i]);
        }

        /*********************************************/

        System.out.println("\nE4");

        Physicist Physicists[] = new Physicist[numberOfHumans];

        for (int i = 0; i < numberOfHumans; i++)
        {
            Physicists[i] = new Physicist();
            while (Physicists[i].getAge() < 15) // If too young to be physicist
            {
                Physicists[i] = new Physicist();
            }
        }

        for (int i = 0; i < numberOfHumans; i++)
        {
            System.out.println(Physicists[i]);
        }

        System.out.println("\nE4.5");

        Human mix[] = new Human[10];
        for (int i = 0; i < 5; i++)
        {
            mix[i] = new Human();
        }
        for (int i = 5; i < 10; i++)
        {
            mix[i] = new Physicist();
            while (mix[i].getAge() < 15) // If too young to be physicist
            {
                mix[i] = new Physicist();
            }
        }

        for (int i = 0; i < 10; i++)
        {
            System.out.println(mix[i]);
        }
    }
}