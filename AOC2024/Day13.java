import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day13 {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new File("day13.txt"));

        int sol1 = 0;
        long sol2 = 0;
        while(sc.hasNextLine())
        {
            String buttonA = sc.nextLine();
            String buttonB = sc.nextLine();
            String prize = sc.nextLine();
            sc.nextLine();

            long x1 = Long.parseLong(buttonA.split(" ")[2].replaceAll("\\D", ""));
            long y1 = Long.parseLong(buttonA.split(" ")[3].replaceAll("\\D", ""));
            long x2 = Long.parseLong(buttonB.split(" ")[2].replaceAll("\\D", ""));
            long y2 = Long.parseLong(buttonB.split(" ")[3].replaceAll("\\D", ""));

            long prizeX = Long.parseLong(prize.split(" ")[1].replaceAll("\\D", ""));
            long prizeY = Long.parseLong(prize.split(" ")[2].replaceAll("\\D", ""));

            long prizeOffset = 10_000_000_000_000L;
            int oo = 1_000_000_000;
            int result = oo;
            for(int i=0;i<=100;i++){
                for (int j=0;j<=100;j++){
                    if (i*x1+j*x2 == prizeX && i*y1+j*y2 == prizeY)
                    {
                        result = Math.min(result, i*3 + j);

                    }
                }
            }
            if (result != oo) sol1 += result;

            prizeX += prizeOffset;
            prizeY += prizeOffset;

            long bNum = prizeY*x1 - prizeX*y1;
            long bDenom = y2*x1-x2*y1;
            if ((bNum % bDenom) == 0)
            {
                long b = bNum / bDenom;
                long aNum = prizeX - b*x2;
                if (aNum % x1 == 0){
                    long a= aNum / x1;

                    sol2 += 3*a+b;
                }
            }
        }

        System.out.println(sol1+" "+sol2);
    }
}