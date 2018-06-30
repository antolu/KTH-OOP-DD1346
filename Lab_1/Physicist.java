public class Physicist extends Human implements Comparable<Human> {
    private int year;

    private int birthYear;

    private int thisYear = 2018;

    public Physicist() {
        birthYear = thisYear - getAge();
        double randPhysicsYear = (thisYear - (birthYear + 15)) * Math.random(); // Allowed range of starting years
        int randYear = thisYear - (int) Math.floor(randPhysicsYear); // Only allowed range is selected

        randYear %= 100; // randYear is in real years, but we only want the last 2 digits

        year = randYear;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return "Name: " + getName() + ", age: " + getAge() + ", year: F-" + String.format("%02d", year);
    }

    public int compareTo(Human human) {
        if (human.getAge() > super.getAge())
            return -1;
        if (human.getAge() < super.getAge())
            return 1;
        return 0;
    }
}