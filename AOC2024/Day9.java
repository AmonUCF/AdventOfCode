import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Arrays;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day9.txt"));

        int[] input = Arrays.stream(sc.nextLine().split("")).mapToInt(i -> i.charAt(0)-'0').toArray();
        int[] input1 = Arrays.copyOf(input, input.length);
        int[] input2 = Arrays.copyOf(input, input.length);

        long sol1 = 0, sol2 = 0;
        long index = 0;
        for(int i=0;i<input1.length;i++) {
            if (i%2 == 0)
                for (int j=0;j<input1[i];j++)
                    sol1 += index++ * (i/2);
            else {
                for (int j=input1.length-1; j>i; j-=2) {
                    int min = Math.min(input1[i], input1[j]);
                    for (int k=0;k<min; k++)
                        sol1 += index++ * (j/2);
                    input1[j] -= min;
                    input1[i] -= min;
                    if (input1[i] == 0) break;
                }
            }
        }

        index = 0;
        for(int i=0;i<input2.length;i++) {
            if (i%2 == 0) {
                for (int j = 0; j < input2[i]; j++)
                    sol2 += index++ * (i / 2);
                for (int j = 0; j < input[i] - input2[i]; j++)
                    index++;
            }
            else {
                for (int j=input2.length-1; j>i; j-=2) {
                    if (input2[i] < input2[j])continue;
                    for (int k=0;k<input2[j]; k++)
                        sol2 += index++ * (j/2);
                    input2[i] -= input2[j];
                    input2[j] = 0;
                }
                for(int j=0;j<input2[i];j++)index++;
            }
        }

        System.out.println(sol1 + " "+ sol2);
    }
}