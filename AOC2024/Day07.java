import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day07 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day7.txt"));

        long ans1 = 0, ans2 = 0;
        while(sc.hasNextLine())
        {
            String line = sc.nextLine();
            String[] parts = line.split(" ");

            long target = Long.parseLong(parts[0].substring(0,parts[0].length()-1));

            long[] vals = new long[parts.length-1];
            for(int i=1;i<parts.length;i++){
                vals[i-1] = Long.parseLong(parts[i]);
            }

            for(int i=0;i<(1<<(vals.length-1));i++)
            {
                long temp = vals[0];
                for(int j=0;j<vals.length-1;j++){
                    if((i & (1<<j)) != 0) {
                        temp *= vals[j+1];
                    } else {
                        temp += vals[j+1];
                    }
                }
                if (temp == target) {
                    ans1 += target;
                    break;
                }
            }

            long[] powTen = {1,10,100,1000,10000};
            for (int i=0;i<Math.pow(3, vals.length-1);i++){
                long temp = vals[0];
                int mask = i;
                for(int j=0;j<vals.length-1;j++){
                    if(mask % 3 == 0) {
                        temp *= vals[j+1];
                    } else if (mask%3 == 1){
                        temp += vals[j+1];
                    } else {
                        temp = temp * powTen[parts[j+2].length()] + vals[j+1];
                    }
                    mask /= 3;
                }
                if (temp == target) {
                    ans2 += target;
                    break;
                }
            }
        }

        System.out.println(ans1 + " " + ans2);
    }
}