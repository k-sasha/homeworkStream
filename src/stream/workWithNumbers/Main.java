package stream.workWithNumbers;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(5);
        list.add(16);
        list.add(-1);
        list.add(-2);
        list.add(0);
        list.add(32);
        list.add(3);
        list.add(5);
        list.add(8);
        list.add(23);
        list.add(4);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > 0 && list.get(i) % 2 == 0) continue;
            list.remove(i);
            i--;
        }

        list.sort(Integer::compareTo);
        System.out.println(list);

    }
}
