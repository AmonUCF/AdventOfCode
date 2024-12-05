import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day5.txt"));

        boolean[][] adj = new boolean[100][100];
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty())
                break;
            String[] a = line.split("\\|");
            adj[Integer.parseInt(a[0])][Integer.parseInt(a[1])] = true;
        }

        int sum = 0, sum2=0;
        while(sc.hasNextLine()){
            int[] list = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
            boolean valid = true;
            for(int i=0;i<list.length;i++)
                for(int j=i+1;j<list.length;j++)
                    if (adj[list[j]][list[i]])
                        valid = false;

            if (valid) {
                sum += list[list.length/2];
            } else {
                for(int i=0;i<list.length;i++)
                    for (int j=i+1;j<list.length;j++)
                        if (adj[list[j]][list[i]]){
                            int temp = list[i];
                            list[i] = list[j];
                            list[j] = temp;
                        }

                sum2 += list[list.length/2];
            }
        }

        System.out.println("Part 1: " + sum);
        System.out.println("Part 2: " + sum2);
    }
}