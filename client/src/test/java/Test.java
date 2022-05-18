import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("dfdf");

        list.add("dfdfdfdfdfdf");

        System.out.println(list);

        for (String s : list) {
            System.out.println(s);
        }
    }
}
