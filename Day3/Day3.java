import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day3.txt"));

        int sum1 = 0, sum2 = 0;
        boolean isDo = true;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            for (int i=0;i < line.length();i++) {
                if (line.substring(i).matches("^mul\\(\\d{1,3},\\d{1,3}\\).*"))
                {
                    String[] nums = line.substring(i+4, line.indexOf(")", i+4)).split(",");
                    sum1 += Integer.parseInt(nums[0])*Integer.parseInt(nums[1]);
                    if (isDo) sum2 += Integer.parseInt(nums[0])*Integer.parseInt(nums[1]);
                }
                if (line.substring(i).startsWith("do()")) isDo = true;
                if (line.substring(i).startsWith("don't()")) isDo = false;
            }
        }

        System.out.println("Part 1: "+sum1);
        System.out.println("Part 2: "+sum2);
    }
}
