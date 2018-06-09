import java.lang.Math;

public class Human {

    private int age;
    private String name;

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
}