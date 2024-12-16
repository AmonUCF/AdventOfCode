import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day11 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day11.txt"));

        HashMap<Long, Long> list = new HashMap<>();
        while(sc.hasNext())list.put(sc.nextLong(), 1L);

        for(int i=0;i<75;i++){
            HashMap<Long, Long> next = new HashMap<>();
            for(long x : list.keySet()) {
                String s = Long.toString(x);
                long z = list.get(x);
                if (s.length() % 2 == 0){
                    long p1 = Long.parseLong(s.substring(0,s.length()/2));
                    long p2 = Long.parseLong(s.substring(s.length()/2,s.length()));
                    next.put(p1, next.getOrDefault(p1, 0L) + z);
                    next.put(p2, next.getOrDefault(p2, 0L) + z);
                } else if (x == 0) {
                    next.put(1L, next.getOrDefault(1L, 0L) + z);
                } else {
                    next.put(x*2024, next.getOrDefault(x*2024, 0L) + z);
                }
            }
            list = next;
        }

        long count = 0;
        for(long x : list.values()) count += x;

        System.out.println(count);
    }
}