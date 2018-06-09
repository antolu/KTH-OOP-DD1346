public class Main {
    public static void main(String[] args) {
        Human Pelle = new Human(25, "Pelle");;
        // System.out.println(Pelle.toString());
        System.out.println("E1-E2");
        System.out.println(Pelle);

        System.out.println("");

        /*******************************/

        System.out.println("E3");

        int numberOfHumans = 10;

        Human Humans[] = new Human[numberOfHumans];

        for (int i = 0; i < numberOfHumans; i++)
        {
            Humans[i] = new Human();
        }

        for (int i = 0; i < numberOfHumans; i++)
        {
            System.out.println(Humans[i]);
        }
    }
}