public class Physicist extends Human implements Comparable<Human>
{
    private int year;

    private int birthYear;

    private int thisYear = 2018;

    public Physicist()
    {
        birthYear = thisYear - getAge();
        double randPhysicsYear = (thisYear - (birthYear + 15))*Math.random(); // Allowed range of starting years
        int randYear = thisYear - (int)Math.floor(randPhysicsYear); // Only allowed range is selected

        randYear = randYear%100; // randYear is in real years, but we only want the last 2 digits

        year = randYear;
    }

    public int getYear()
    {
        return year;
    }

    public String toString()
    {
        if (year < 10) // Needed for ex. F-05, so it doesn't print F-5
        {
            return "Name: " + getName() + ", age: " + getAge() + ", year: F-0" + year;
        }
        else
        {
            return "Name: " + getName() + ", age: " + getAge() + ", year: F-" + year;
        }
    }

    public int compareTo(Human human)
    {
        if (human.getAge() > super.getAge()) return -1;
        if (human.getAge() < super.getAge()) return 1;
        return 0;
    }
}