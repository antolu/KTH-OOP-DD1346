package factory;

public class Physicist extends Human implements Comparable<Human> {
    private int year;

    private int birthYear;

    private final static int THIS_YEAR = 2018;

    // public Physicist() throws Exception {

    //     birthYear = THIS_YEAR - getAge();
    //     double randPhysicsYear = (THIS_YEAR - (birthYear + 15)) * Math.random(); // Allowed range of starting years
    //     int randYear = THIS_YEAR - (int) Math.floor(randPhysicsYear); // Only allowed range is selected

    //     if ((randYear - birthYear) < 15)
    //         throw new Exception("Too young to be physicist!");

    //     randYear %= 100; // randYear is in real years, but we only want the last 2 digits

    //     year = randYear;
    // }

    Physicist(int ageIn, String nameIn, int yearIn) throws IllegalArgumentException
    {
        // super(ageIn, nameIn);

        age = ageIn;
        name = nameIn;

        birthYear = THIS_YEAR - ageIn;

        int fysikYear = 0;
        if (yearIn > THIS_YEAR % 100) 
            fysikYear = yearIn + 1900;
        else 
            fysikYear = yearIn + 2000;

        if ((fysikYear - birthYear) < 15)
            throw new IllegalArgumentException("Too young to be physicist!");

        year = yearIn;
    }

    public final int getYear() {
        return year;
    }

    public final String toString() {
        return "Name: " + getName() + ", age: " + getAge() + ", year: F-" + String.format("%02d", year);
    }

    public final int compareTo(Human human) {
        if (human.getAge() > getAge())
            return -1;
        if (human.getAge() < getAge())
            return 1;
        if (human instanceof Physicist) {
            Physicist physicist = (Physicist) human;

            if (physicist.getYear() > year)
                return -1;
            if (physicist.getYear() < year)
                return 1;
            return 0;
        }
        return 0;
    }
}