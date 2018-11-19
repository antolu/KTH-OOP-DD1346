import java.util.*;
public class MainC2{
    public static void main(String[] args){
        try {
            Physicist a = new Physicist(22, "Anton", 15);
            Physicist b = new Physicist(22, "Erik", 14);

            Physicist[] physicists = {a,b};

            Arrays.sort(physicists);
    
            for(Physicist physicist:physicists){
                System.out.println(physicist);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}