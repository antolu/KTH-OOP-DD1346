package factory;

public abstract class Human implements Comparable<Human>
{

    protected int age;
    protected String name;
    protected static final String names[] = {
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

    Human() {}

    public static final Human create(String name, String year, int age) throws IllegalArgumentException {
        Human human;

        if (year.substring(0, 1).equals("F")) { // Fysiker
            human = new Physicist(age, name, Integer.parseInt(year.substring(1)));
        } else if (year.substring(0, 1).equals("D")) { // Datalog
            human = new ComputerScientist(age, name, Integer.parseInt(year.substring(1)));            
        } else {
            throw new TypeNotPresentException("Invalid argument: " + year, null);
        }

        return human;
    }

    public abstract String toString();

    public final int getAge() {
        return age;
    }

    public final String getName() {
        return name;
    }

    public abstract int compareTo(Human human);
}