import java.util.Map;
import java.util.HashMap;

public class MenuOfChampions {
    public static void main(String[] args) {
        decorate();

        System.out.println("\nWelcome to Cool Restaurant, we gots some foods!\n");

        decorate();

        Map<String,Double> menu = new HashMap<String,Double>();

        menu.put("Bagel", 2.2);
        menu.put("BaconEggAndCheeseOnARoll", 4.0);
        menu.put("Scrambled Eggs", 1.5);
        menu.put("Carrot Cake", 6.0);

        for(Map.Entry<String,Double> item: menu.entrySet()){
            System.out.println(item.getKey() + "   -------------->   " + item.getValue());
        }

        decorate();
    }
    
    public static void decorate(){
        System.out.print("\n.");
        for (int i=0; i<20; i++){System.out.print("</\\>.");}
        System.out.println("");
    }
}
