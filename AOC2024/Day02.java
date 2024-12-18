import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;

public class Day02 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day2.txt"));

        int part1Safe = 0, part2Safe = 0;
        while (sc.hasNextLine())
        {
            int[] list = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Function<int[], Boolean> func = arr -> {
                boolean ascending = arr[1] - arr[0] > 0;
                for (int i = 1; i < arr.length; i++) {
                    int diff = Math.abs(arr[i]-arr[i-1]);
                    if (diff == 0 || diff > 3) return false;
                    if (arr[i] - arr[i-1] > 0 != ascending) return false;
                }
                return true;
            };

            if (func.apply(list)) part1Safe++;
            for(int i=0;i<list.length;i++){
                int[] newList = new int[list.length-1];
                for(int j=0,idx=0;j<list.length;j++){
                    if (j==i)continue;
                    newList[idx++] = list[j];
                }
                if (func.apply(newList)) {
                    part2Safe++;
                    break;
                }
            }
        }

        System.out.println("Part 1: " + part1Safe);
        System.out.println("Part 2: " + part2Safe);
    }
}
