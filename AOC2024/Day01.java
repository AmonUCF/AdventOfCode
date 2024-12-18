import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Day01 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day1.txt"));
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        while (sc.hasNext()) {
            a.add(sc.nextInt());
            b.add(sc.nextInt());
        }

        Collections.sort(a);
        Collections.sort(b);

        int sum = 0;
        for (int i = 0 ; i < a.size(); i++) sum += Math.abs(a.get(i) - b.get(i));
        System.out.println("Part 1: "+ sum);

        int sum2 = 0;
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int i : b) count.put(i, count.getOrDefault(i, 0) + 1);
        for (int i : a) sum2 += i * count.getOrDefault(i, 0);
        System.out.println("Part 2: "+ sum2);
    }
}
