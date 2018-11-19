import java.util.Vector;

public class MainC3 {
    public static void main(String[] args) {
        int index = 0;
        Vector<Human> people = new Vector<Human>();

        if (args.length > 0) {
            while (index < args.length) {
                if (args[index].equals("-H")) {
                    try {
                        String name = args[++index];
                        int age = Integer.parseInt(args[++index]);

                        people.add(new Human(age, name));
                    } catch (NumberFormatException e) {
                        System.err.println("Argument" + args[index] + " must be an integer.");
                        System.exit(1);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Argument incorrectly formatted. Check argument " + index);
                        System.exit(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                }
                if (args[index].equals("-F")) {
                    try {
                        String name = args[++index];
                        int age = Integer.parseInt(args[++index]);
                        int year = Integer.parseInt(args[++index]);

                        people.add(new Physicist(age, name, year));
                    } catch (NumberFormatException e) {
                        System.err.println("Argument" + args[index] + " must be an integer.");
                        System.exit(1);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Argument incorrectly formatted. Check argument " + index);
                        System.exit(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                }
                index++;
            }

        }

        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i));
        }
    }
}