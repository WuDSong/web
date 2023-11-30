import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashMap<String,Number> wds=new HashMap<>();
        wds.put("lilith",520);
        wds.put("小恶魔",1);
        if(wds.containsKey("lilith")){
            System.out.println(wds.get("lilith"));
            wds.put("lilith",5656.3);
        }
        System.out.println(wds);
        System.out.println("Hello world!");
    }
}