import java.util.*;
public class Main 
{
    public static void main(String[] args) 
    {
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

        /****************************/

        System.out.println("\nE5.2");

        Human a = new Human();
        Human b = new Human();

        int compareResult = a.compareTo(b);
        if (compareResult > 0)
        {
            System.out.println(a.getName() + " who is " + a.getAge() + " years old, is older than " 
                                + b.getName() + " who is " + b.getAge() + " years old.");
        }
        else if (compareResult < 0){
            System.out.println(b.getName() + " who is " + b.getAge() + " years old, is older than " 
            + a.getName() + " who is " + a.getAge() + " years old.");
        }
        else
        {
            System.out.println(a.getName() + " and " + b.getName() + " are equally old; " + a.getAge() + " years old.");
        }

        System.out.println("\nE5.4");

        Arrays.sort(mix); // Comparable since both types use the object reference Human

        for (Human human : mix)
        {
            System.out.println(human);
        }
    }
}