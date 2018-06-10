import java.lang.Math;

public class Human implements Comparable<Human>
{

    private int age;
    private String name;

    private String names[] = {
        "Anton", "Elisabet", "Felicitas", "Gisele", "Chanell", "Coralie", 
        "Raymundo", "Deshawn", "Maryellen", "Alden", "Tena", "Hosea", "Nila", 
        "King", "Adolph", "Phillis", "Treasa", "Jan", "Libbie", "Kristina", "Silva", 
        "Windy", "Lizette", "Zackary", "Evonne", "Ethyl", "Cierra", "Luis", "Roselee", 
        "Maryann", "Molly", "Tinisha", "Chante", "Emmie", "Byron", "Jacque", "Chantelle", 
        "Laura", "Cindi", "Valeri", "Melba", "Alise", "Sanjuana", "Mervin", "Eugena", 
        "Weldon", "Salena", "Marquita", "Dottie", "Nanci", "Trinh", "Tashia", "Maria", 
        "Bailey", "Stanford", "Zonia", "Rose", "Josue", "Jim", "Alaina", "Ambrose", 
        "Merilyn", "Fredrick", "Yesenia", "Jaclyn", "Corrie", "Devora", "Tiffany", 
        "Timothy", "Kristel", "Lecia", "Julene", "Shera", "Crissy", "Vernie", "Willia", 
        "Moises", "Krystyna", "Gwendolyn", "Tomi", "Katy", "Raeann", "Carolina", "Charita", 
        "Alpha", "Margorie", "Kaylee", "Federico", "Callie", "Laurence", "Dedra", "Jody", 
        "Ivana", "Shizue", "Dawna", "Freddie", "Arthur", "Joel", "Isabell", "Antoine", 
        "Annetta", "Edna"
    };
    public Human() // Random
    {
        double randNo = 100*Math.random();
        int index = (int)Math.floor(randNo);
        name = names[index];
        age = index;
    }

    public Human(int ageIn, String nameIn)
    {
        age = ageIn;
        name = nameIn;
    }

    public String toString()
    {
        return "Name: " + name + ", age: " + age;
    }

    public int getAge()
    {
        return age;
    }

    public String getName()
    {
        return name;
    }

    public int compareTo(Human human)
    {
        if (human.getAge() > age) return -1;
        if (human.getAge() < age) return 1;
        return 0;
    }
}